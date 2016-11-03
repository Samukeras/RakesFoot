package udesc.br.rakesfoot.game.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import udesc.br.rakesfoot.core.util.IntRandomUtils;
import udesc.br.rakesfoot.game.model.Event;
import udesc.br.rakesfoot.game.model.EventType;
import udesc.br.rakesfoot.game.model.Match;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.Team;

import static udesc.br.rakesfoot.game.rules.Match.*;
import static udesc.br.rakesfoot.game.rules.Team.CHEMESTRY_INCREASE_STEP;
import static udesc.br.rakesfoot.game.rules.Team.MOTIVATION_DECREASE_STEP;
import static udesc.br.rakesfoot.game.rules.Team.MOTIVATION_INCREASE_STEP;
import static udesc.br.rakesfoot.game.rules.Team.getAttackOverral;
import static udesc.br.rakesfoot.game.rules.Team.getDefenseOverral;

/**
 * Created by Ricardo on 02/11/2016.
 */

public class MatchSimulator implements Simulator {

    private int currentMinute = 0;

    private List<Match> matches;

    private List<Player> hostPlayers;

    private List<Player> Players;

    private Match currentMath;

    public void registerMatch(Match match) {
        matches.add(match);
    }

    @Override
    public void run() {
        while (currentMinute <= END_TIME) {
            if (currentMinute == HALF_TIME) {
                break;
            }

            runMatches();
            currentMinute++;
        }
    }

    private void updateEndGameVariables() {
        int goalsHost  = currentMath.getEventCount(EventType.GOAL, currentMath.getHost());
        int goalsGuest = currentMath.getEventCount(EventType.GOAL, currentMath.getGuest());

        if (goalsHost > goalsGuest) {
            increaseMotivation(currentMath.getHost(),  MOTIVATION_INCREASE_STEP);
            increaseMotivation(currentMath.getGuest(), MOTIVATION_DECREASE_STEP);
        } else if (goalsHost > goalsGuest) {
            increaseMotivation(currentMath.getGuest(), MOTIVATION_INCREASE_STEP);
            increaseMotivation(currentMath.getHost(),  MOTIVATION_DECREASE_STEP);
        }

        currentMath.getHost().setChemestry(currentMath.getHost().getChemestry() + CHEMESTRY_INCREASE_STEP);
        currentMath.getGuest().setChemestry(currentMath.getGuest().getChemestry() + CHEMESTRY_INCREASE_STEP);
    }

    private void increaseMotivation(Team team, int step) {
        for (Player player : team.getPlayers()) {
            player.setMotivation(player.getMotivation() + step);
        }
    }

    private void runMatches() {
        for (Match match : matches) {
            currentMath = match;
            runEvents();

            if (currentMinute == END_TIME) {
                updateEndGameVariables();
            }
        }
    }

    private void runEvents() {
        double chance = IntRandomUtils.getNetxPercentage();

        for (Map.Entry<EventType, Double> entry : udesc.br.rakesfoot.game.rules.Event.getEventsForSimulator().entrySet()) {
            if (chance <= entry.getValue()) {
                executeEvent(entry.getKey());
                break;
            }
            chance -= entry.getValue();
        }
    }

    private void executeEvent(EventType event) {
        switch (event) {
            case ASSISTANCE:
                tryGoal();
                break;
            default:
                registerEvent(event, getRandonPlayer());
        }
    }

    private void tryGoal() {
        Team attacker = getTeamForRound();

        double bonus = isHost(attacker) ? udesc.br.rakesfoot.game.rules.Match.HOST_TEAM_BONUS_RATE : 1;

        int attackerOverral = (int) (getAttackOverral(getPlayersForRound(attacker)) * bonus);
        int defenderOverral = getDefenseOverral(getPlayersForRound(getTeamAdversary(attacker)));

        int attackChance = IntRandomUtils.getNextIntFromZeroToInterval(attackerOverral + defenderOverral);

        if (attackChance > defenderOverral) {
            registerEvent(EventType.GOAL, getRandonPlayer());
        }
    }

    private void registerEvent(EventType type, Player player) {
        Event event = new Event();
        event.setMatch(currentMath);
        event.setMinute(currentMinute);
        event.setPlayer(player);
        event.setTeam(player.getTeam());
        event.setType(type);

        currentMath.addEvent(event);
    }

    private boolean isHost(Team team) {
        return team.getId() == currentMath.getHost().getId();
    }

    private Team getTeamAdversary(Team team) {
        if (team.getId() == currentMath.getHost().getId()) {
            return currentMath.getGuest();
        }
        return currentMath.getHost();
    }

    private Team getTeamForRound() {
        if (currentMinute % 2 == 0) {
            return currentMath.getHost();
        }
        return currentMath.getGuest();
    }

    private List<Player> getPlayersForRound(Team team) {
        List<Player> players = new ArrayList<>();

        for (Player player : team.getFormation().getFirstTeamPlayers()) {
            if (currentMath.getEventCount(EventType.INJURY, player) == 0) {
                if (currentMath.getEventCount(EventType.RED_CARD, player) == 0) {
                    players.add(player);
                }
            }
        }

        return players;
    }

    private Player getRandonPlayer() {
        List<Player> players = getPlayersForRound(getTeamForRound());
        int randon = IntRandomUtils.getNextIntFromZeroToInterval(players.size() - 1);
        return players.get(randon);
    }
}

package udesc.br.rakesfoot.game.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import udesc.br.rakesfoot.core.util.IntRandomUtils;
import udesc.br.rakesfoot.game.model.Event;
import udesc.br.rakesfoot.game.model.EventType;
import udesc.br.rakesfoot.game.model.Game;
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

public class MatchSimulator extends Simulator<Match> {

    private int currentMinute = 0;

    @Override
    public void run() {
        while (currentMinute <= END_TIME) {
            if (currentMinute == HALF_TIME) {
//                break;
            }

            super.run();
            currentMinute++;
        }
        currentMinute = 0;
    }

    private void updateEndGameVariables() {
        int goalsHost  = getCurrent().getEventCount(EventType.GOAL, getCurrent().getHost());
        int goalsGuest = getCurrent().getEventCount(EventType.GOAL, getCurrent().getGuest());

        if (goalsHost > goalsGuest) {
            increaseMotivation(getCurrent().getHost(),  MOTIVATION_INCREASE_STEP);
            increaseMotivation(getCurrent().getGuest(), MOTIVATION_DECREASE_STEP);
        } else if (goalsHost > goalsGuest) {
            increaseMotivation(getCurrent().getGuest(), MOTIVATION_INCREASE_STEP);
            increaseMotivation(getCurrent().getHost(),  MOTIVATION_DECREASE_STEP);
        }

        getCurrent().getHost().setChemestry(getCurrent().getHost().getChemestry() + CHEMESTRY_INCREASE_STEP);
        getCurrent().getGuest().setChemestry(getCurrent().getGuest().getChemestry() + CHEMESTRY_INCREASE_STEP);


    }

    private void increaseMotivation(Team team, int step) {
        for (Player player : team.getPlayers()) {
            player.setMotivation(player.getMotivation() + step);
        }
    }

    @Override
    void simulate() {
        runEvents();
        if (currentMinute == END_TIME) {
            updateEndGameVariables();
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
//                registerEvent(event, getRandonPlayer());
                tryGoal();
                break;
            default:
                registerEvent(event, getRandonPlayer());
        }
    }

    private void tryGoal() {
        Team attacker = getTeamForRound();
        Team defender = getTeamAdversary(attacker);

        double bonus = isHost(attacker) ? udesc.br.rakesfoot.game.rules.Match.HOST_TEAM_BONUS_RATE : 1;

        int attackerOverral = (int) (getAttackOverral(getPlayersForRound(attacker)) * bonus);
        int defenderOverral = getDefenseOverral(getPlayersForRound(defender));

        int attackChance = IntRandomUtils.getNextIntFromZeroToInterval(attackerOverral + defenderOverral);

        if (defender.getId() == Game.getTeam().getId() || attacker.getId() == Game.getTeam().getId()) {
            attackChance *= 1;
        }

        if (attackChance * udesc.br.rakesfoot.game.rules.Event.GOAL_CHANCE > defenderOverral) {
            registerEvent(EventType.GOAL, getRandonPlayer());
        }
    }

    private void registerEvent(EventType type, Player player) {
        Event event = new Event();
        event.setMatch(getCurrent());
        event.setMinute(currentMinute);
        event.setPlayer(player);
        event.setTeam(player.getTeam());
        event.setType(type);

        getCurrent().addEvent(event);
        notifyListeners(event);
    }

    private boolean isHost(Team team) {
        return team.getId() == getCurrent().getHost().getId();
    }

    private Team getTeamAdversary(Team team) {
        if (team.getId() == getCurrent().getHost().getId()) {
            return getCurrent().getGuest();
        }
        return getCurrent().getHost();
    }

    private Team getTeamForRound() {
        if (currentMinute % 2 == 0) {
            return getCurrent().getHost();
        }
        return getCurrent().getGuest();
    }

    private List<Player> getPlayersForRound(Team team) {
        List<Player> players = new ArrayList<>();

        for (Player player : team.getFormation().getFirstTeamPlayers()) {
            if (getCurrent().getEventCount(EventType.INJURY, player) == 0) {
                if (getCurrent().getEventCount(EventType.RED_CARD, player) == 0) {
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

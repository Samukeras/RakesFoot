package udesc.br.rakesfoot.game.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import udesc.br.rakesfoot.core.util.IntRandomUtils;
import udesc.br.rakesfoot.game.model.EventType;
import udesc.br.rakesfoot.game.model.Match;
import udesc.br.rakesfoot.game.model.Player;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.rules.Event;

import static udesc.br.rakesfoot.game.rules.Match.*;

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

    private void runMatches() {
        for (Match match : matches) {
            currentMath = match;
            runEvents();
        }
    }

    private void runEvents() {
        double chance = IntRandomUtils.getNetxPercentage();

        for (Map.Entry<EventType, Double> entry : Event.getEventsForSimulator().entrySet()) {
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
                executeAssistance();
                break;
            case INJURY:
                executeInjury();
                break;
            case YELLOW_CARD:
                executeYellowCard();
                break;
            case RED_CARD:
                executeRedCard();
                break;
        }
    }

    private void executeRedCard() {

    }

    private void executeYellowCard() {
    }

    private void executeInjury() {
    }

    private void executeAssistance() {
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

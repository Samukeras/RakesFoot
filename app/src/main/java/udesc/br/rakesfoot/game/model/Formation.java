package udesc.br.rakesfoot.game.model;

import java.util.ArrayList;

import static udesc.br.rakesfoot.game.rules.Formation.*;

/**
 * Created by Ricardo on 02/11/2016.
 */

public class Formation {

    private ArrayList<Player> firstTeamPlayers;

    private ArrayList<Player> substitutes;

    public ArrayList<Player> getFirstTeamPlayers() {
        return firstTeamPlayers;
    }

    public boolean addFirstTeamPlayer(Player player) {
        if (isPositionLimitReached(player.position()) || isPlayerLimitReached()) {
            return false;
        }

        return firstTeamPlayers.add(player);
    }

    public boolean removeFirstTeamPlayer(Player player) {
        return firstTeamPlayers.remove(player);
    }

    public ArrayList<Player> getSubstitutes() {
        return substitutes;
    }

    public boolean addSubstitute(Player player) {
        if (substitutes.size() == SUBSTITUTE_LIMIT) {
            return false;
        }
        return substitutes.add(player);
    }

    public boolean removeSubstitute(Player player) {
        return substitutes.remove(player);
    }

    public boolean isReady() {
        for (Position position : Position.values()) {
            if (!isPositionRequidedReached(position)) {
                return false;
            }
        }
        return isPlayerLimitReached();
    }

    public boolean isPlayerLimitReached() {
        return firstTeamPlayers.size() == FIRST_TEAM_COUNT;
    }

    public boolean isPositionLimitReached(Position position) {
        switch (position) {
            case GOALKEEPER:
                return countByPosition(position) == FIRST_TEAM_GOALKEEPER_COUNT;
            case DEFENDER:
                return countByPosition(position) == FIRST_TEAM_DEFENDER_LIMIT;
            case MIDFIELDER:
                return countByPosition(position) == FIRST_TEAM_MIDFIELDER_LIMIT;
            case FORWARD:
                return countByPosition(position) == FIRST_TEAM_FORWARD_LIMIT;
        }
        return false;
    }

    public boolean isPositionRequidedReached(Position position) {
        switch (position) {
            case GOALKEEPER:
                return countByPosition(position) == FIRST_TEAM_GOALKEEPER_COUNT;
            case DEFENDER:
                return countByPosition(position) >= FIRST_TEAM_DEFENDER_REQUIRED;
            case MIDFIELDER:
                return countByPosition(position) >= FIRST_TEAM_MIDFIELDER_REQUIRED;
            case FORWARD:
                return countByPosition(position) >= FIRST_TEAM_FORWARD_REQUIRED;
        }
        return false;
    }

    public int countByPosition(Position position) {
        int count = 0;
        for (Player player : firstTeamPlayers) {
            if (player.position() == position) {
                count++;
            }
        }
        return count;
    }

}

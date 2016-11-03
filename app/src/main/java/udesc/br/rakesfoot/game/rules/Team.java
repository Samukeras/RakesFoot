package udesc.br.rakesfoot.game.rules;

import java.util.List;

import udesc.br.rakesfoot.game.model.*;
import udesc.br.rakesfoot.game.model.Player;

/**
 * Created by Ricardo on 01/11/2016.
 */

public class Team {

    public final static int MOTIVATION_DECREASE_STEP = 3; // on lose
    public final static int MOTIVATION_INCREASE_STEP = 2; //on win

    public final static double CHEMESTRY_DECREASE_RATE = 0.03; // each new player
    public final static int CHEMESTRY_INCREASE_STEP    = 1; // after match

    public final static double GOALKEPPER_ATTACK_INFLUENCE = 0.02;
    public final static double DEFENDER_ATTACK_INFLUENCE   = 0.18;
    public final static double MIDFIELDER_ATTACK_INFLUENCE = 0.30;
    public final static double FORWARD_ATTACK_INFLUENCE    = 0.50;

    public final static double GOALKEPPER_DEFENSE_INFLUENCE = 0.30;
    public final static double DEFENDER_DEFENSE_INFLUENCE   = 0.40;
    public final static double MIDFIELDER_DEFENSE_INFLUENCE = 0.20;
    public final static double FORWARD_DEFENSE_INFLUENCE    = 0.10;

    public static int getAttackOverral(List<Player> players) {
        double overral = 0, playerOverral;
        for (Player player : players) {
            playerOverral = player.getOverral() * getAttackInfluence(player.getPosition());
            playerOverral *= Math.sqrt(player.getMotivation()) / 10;
            playerOverral *= Math.sqrt(player.getPhysical()) / 10;

            overral += playerOverral;
        }
        return (int) overral;
    }

    public static int getDefenseOverral(List<Player> players) {
        double overral = 0, playerOverral;
        for (Player player : players) {
            playerOverral = player.getOverral() * getDefenseInfluence(player.getPosition());
            playerOverral *= Math.sqrt(player.getMotivation()) / 10;
            playerOverral *= Math.sqrt(player.getPhysical()) / 10;

            overral += playerOverral;
        }
        return (int) overral;
    }

    private static double getAttackInfluence(Position position) {
        switch (position) {
            case GOALKEEPER:
                return GOALKEPPER_ATTACK_INFLUENCE;
            case DEFENDER:
                return DEFENDER_ATTACK_INFLUENCE;
            case MIDFIELDER:
                return MIDFIELDER_ATTACK_INFLUENCE;
            case FORWARD:
                return FORWARD_ATTACK_INFLUENCE;
        }
        return 0;
    }

    private static double getDefenseInfluence(Position position) {
        switch (position) {
            case GOALKEEPER:
                return GOALKEPPER_DEFENSE_INFLUENCE;
            case DEFENDER:
                return DEFENDER_DEFENSE_INFLUENCE;
            case MIDFIELDER:
                return MIDFIELDER_DEFENSE_INFLUENCE;
            case FORWARD:
                return FORWARD_DEFENSE_INFLUENCE;
        }
        return 0;
    }



}

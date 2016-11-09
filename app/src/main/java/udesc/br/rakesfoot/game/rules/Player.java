package udesc.br.rakesfoot.game.rules;

/**
 * Created by Ricardo on 01/11/2016.
 */

public class Player {
    public final static double PHYSICAL_DECREASE_STEP = 0.7; // per minute/round
    public final static double PHYSICAL_INCREASE_STEP =  20; // per day
    public final static int    MAX_PHYSICAL           = 100; // PLAYER

    public final static double MOTIVATION_DECREASE_ON_DEFEAT      = 2; // ALL
    public final static double MOTIVATION_DECREASE_ON_INJURY      = 5; // PLAYER
    public final static double MOTIVATION_DECREASE_ON_RED_CARD    = 3; // PLAYER
    public final static double MOTIVATION_DECREASE_ON_YELLOW_CARD = 1; // PLAYER

    public final static double MOTIVATION_INCREASE_ON_VICTORY = 2; // ALL
    public final static double MOTIVATION_INCREASE_ON_GOAL    = 1; // PLAYER

    public int getRehabilitationCost(int overral, int physical) {
        int newPhysical = physical + overral / 4;
        if(newPhysical >= 100) {
            return 100;
        }

        return newPhysical;
    }

}
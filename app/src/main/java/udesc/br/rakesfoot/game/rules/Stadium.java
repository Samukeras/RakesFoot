package udesc.br.rakesfoot.game.rules;

/**
 * Created by Ricardo on 01/11/2016.
 */

public class Stadium {

    public static int MAX_CAPACITY = 100000;

    public static int INCREASE_STEP = 10000;

    public Double getIncreaseCost(int capacity) {
        if (capacity < MAX_CAPACITY) {
            return Math.pow(capacity, 2) / 10000;
        }
        return null;
    }

}

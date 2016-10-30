package udesc.br.rakesfoot.core.util;

import java.util.Random;

/**
 * The generation of random numbers is too important to be left to chance.
 *
 * @author Samuel Felício Adriano
 * @since 30/10/2016
 */
public class IntRandomUtils {

    private static Random random;

    static {
        random = new Random();
    }

    /**
     * Returns an int between 0 and maxValue
     *
     * @param maxValue - max value to be returned
     * @return int
     */
    public static int getNextIntFromZeroToInterval(int maxValue) {
        return random.nextInt(maxValue + 1);
    }

    /**
     * Returns an int between value and maxValue
     *
     * @param value    - min value to be returned
     * @param maxValue - max value to be returned
     *
     * @return int
     */
    public static int getNextIntFromValueToInterval(int value, int maxValue) {
        return value + getNextIntFromZeroToInterval(maxValue - value);
    }

}
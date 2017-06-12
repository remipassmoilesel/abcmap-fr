package org.remipassmoilesel.abcmapfr;

import java.util.Random;

/**
 * Created by remipassmoilesel on 12/06/17.
 */
public class Utils {

    private static Random rand = new Random();

    /**
     * Return a random int
     *
     * @param min
     * @param max
     * @return
     */
    public static int randInt(int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

}

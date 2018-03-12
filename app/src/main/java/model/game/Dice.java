/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.game;

import java.util.Random;

/**
 *
 * @author Toasty Studios
 */
public class Dice {

    private Dice() {
        throw new IllegalStateException("Utility class");
    }

    private static final int SIDES = 20;

    /**
     * Rolls the die, returning a value from 1 to 20.
     *
     * @return
     */
    public static int rollDie() {
        Random rand = new Random();
        return (rand.nextInt(SIDES) + 1);
    }

    /**
     * Rolls the die, given a boundary
     *
     * @param bound
     * @return
     */
    public static int customRollDie(int bound) {
        Random rand = new Random();
        return (rand.nextInt(bound) + 1);
    }
}

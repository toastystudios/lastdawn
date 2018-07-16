/*
 *  Last Dawn (c) by Toasty Studios is licensed under
 *  * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 *  * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 *
 */

package lastdawn.states;

/**
 * Available states - aka windows - for the game.
 * Each state must have an ID (integer) so it's good practice to store them as static variables or enum.
 */
public class States {
    public static final int MENU = 0;
    public static final int OPTIONS = 1;
    public static final int LOAD = 2;
    public static final int NEW = 3;
    public static final int INGAME = 4;
}

/* Last Dawn (c) by Toasty Studios is licensed under
 * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 */
package model.player;

/**
 *
 * @author Toasty Studios
 */
public abstract class PlayerChar extends model.character.Character {

    private static final int STARTING_LEVEL = 1;

    public PlayerChar(String playerClass) {
        super(STARTING_LEVEL, playerClass);
    }


}

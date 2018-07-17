/*
 *  Last Dawn (c) by Toasty Studios is licensed under
 *  * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 *  * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 *
 */

package lastdawn.game;

import lastdawn.engine.Game;

import java.io.Serializable;

public class Save implements Serializable {


    private Game game;

    public Save(Game game) {
        this.game = game;
    }

    public boolean newGame() {
        return false;
    }

    public boolean saveGame() {
        return false;
    }
}

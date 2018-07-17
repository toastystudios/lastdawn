/*
 *  Last Dawn (c) by Toasty Studios is licensed under
 *  * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 *  * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 *
 */

package lastdawn.engine;

import lastdawn.game.Save;
import lastdawn.states.States;
import lastdawn.utils.UserSettings;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.Serializable;

/**
 * Class responsible for initializing the game
 */
public class Game extends StateBasedGame implements Serializable {

    /**
     * Title screen name
     */
    private static final String NAME = "Last Dawn";

    /**
     * Game Container - responsible for running the game
     */
    private AppGameContainer appgc;

    /**
     * User Settings - resolution and fullscreen
     */
    private UserSettings userSettings;

    /**
     * User Save File
     */
    private Save save;


    private final int MAX_FPS=60;

    public Game() {
        super(NAME);

        try {
            appgc = new AppGameContainer(this);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        userSettings = new UserSettings();
        userSettings.readUserSettings();

        this.addState(new MenuState(appgc, userSettings));
        this.addState(new InGame());
    }

    /**
     * Default state to enter
     *
     * @param gameContainer
     * @throws SlickException
     */
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(States.MENU).init(gameContainer, this);
        this.enterState(States.MENU);
    }

    public void run() {
        try {
            appgc.setDisplayMode(userSettings.getResolution()[0], userSettings.getResolution()[1], userSettings.isFullscreen());
            appgc.setTargetFrameRate(MAX_FPS);
            appgc.start();
        } catch (SlickException ex) {
            System.out.println("The thing went skrrraaa.." + ex.getMessage());
        }
    }

    public boolean saveGame() {
        return save.saveGame();
    }

    public boolean newGame() {
        return save.newGame();
    }
}

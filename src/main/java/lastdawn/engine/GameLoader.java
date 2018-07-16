/*
 *  Last Dawn (c) by Toasty Studios is licensed under
 *  * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 *  * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 *
 */

package lastdawn.engine;

import lastdawn.states.States;
import lastdawn.utils.UserSettings;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.io.File;

/**
 * Class responsible for initializing the game
 */
public class GameLoader extends StateBasedGame {

    /**
     * Title screen name
     */
    private static final String NAME = "Last Dawn";

    /**
     * Game Container - responsible for running the game
     */
    private static AppGameContainer appgc;

    /**
     * User Settings - resolution and fullscreen
     */
    private static UserSettings userSettings;

    public GameLoader(String name) {
        super(name);
        this.addState(new MenuState());
        this.addState(new InGame());
        this.addState(new LoadState());
        this.addState(new OptionsState());
        this.addState(new NewState());
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

    /**
     * Sets the window to fullscreen in the game container
     */
    public static void setFullscreen() {
        try {
            appgc.setFullscreen(true);
        } catch (SlickException e) {
            return;
        }
    }

    /**
     * Sets the window to window in the game container
     */
    public static void setWindowed() {
        try {
            appgc.setFullscreen(false);
        } catch (SlickException e) {
            return;
        }
    }

    /**
     * Sets a new resolution
     *
     * @param width
     * @param height
     * @param fullscreen
     */
    public static void changeResolution(int width, int height, boolean fullscreen) {
        try {
            appgc.setDisplayMode(width, height, fullscreen);
        } catch (SlickException e) {
            return;
        }
    }

    /**
     * Saves the current user settings to a .txt file
     *
     * @param fullscreen
     * @param resolution
     */
    public static void saveUserSettings(boolean fullscreen, int[] resolution) {
        userSettings = new UserSettings(fullscreen, resolution);
        userSettings.saveUserSettings();
    }

    /**
     * Returns the user settings.
     *
     * @return
     */
    public static UserSettings getUserSettings() {
        return userSettings;
    }

    /**
     * Runs the game - reads the user settings, sets the display size and starts it.
     */
    public void run() {
        try {

            userSettings = new UserSettings();
            userSettings.readUserSettings();

            appgc = new AppGameContainer(new GameLoader(NAME));
            appgc.setDisplayMode(userSettings.getResolution()[0], userSettings.getResolution()[1], userSettings.isFullscreen());

            appgc.setVSync(true);
            appgc.start();
        } catch (SlickException ex) {
            System.out.println("The thing went skrrraaa.." + ex.getMessage());
        }
    }
}

/*
 *  Last Dawn (c) by Toasty Studios is licensed under
 *  * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 *  * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 *
 */

package lastdawn.engine;

import lastdawn.states.States;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.io.File;

public class GameLoader extends StateBasedGame {

    private static final String NAME = "Last Dawn";
    private static AppGameContainer appgc;

    public GameLoader(String name) {
        super(name);
        this.addState(new MenuState());
        this.addState(new InGame());
        this.addState(new LoadState());
        this.addState(new OptionsState());
        this.addState(new NewState());
    }

    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(States.MENU).init(gameContainer, this);
        this.enterState(States.MENU);
    }

    public static void setFullscreen() {
        try {
            appgc.setFullscreen(true);
        } catch (SlickException e) {
            return;
        }
    }

    public static void setWindowed() {
        try {
            appgc.setFullscreen(false);
        } catch (SlickException e) {
            return;
        }
    }

    public static void changeResolution(int width, int height, boolean fullscreen) {
        try {
            appgc.setDisplayMode(width, height, fullscreen);
        } catch (SlickException e) {
            return;
        }
    }

    public void run() {
        try {
           Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
           appgc = new AppGameContainer(new GameLoader(NAME));
           appgc.setDisplayMode(screenSize.width,screenSize.height, false);
           appgc.setVSync(true);
           appgc.start();
        } catch(SlickException ex) {
            System.out.println("The thing went skrrraaa.." + ex.getMessage());
        }
    }
}

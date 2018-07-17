/*
 *  Last Dawn (c) by Toasty Studios is licensed under
 *  * Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License. (CC BY-NC-ND 4.0)
 *  * For more information, visit the link: http://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 *
 */

package lastdawn.engine;

import lastdawn.states.States;
import lastdawn.utils.FontLoader;
import lastdawn.utils.Resolutions;
import lastdawn.utils.UserSettings;
import lastdawn.view.framework.Button;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * The game menu - responsible for calling the new game, load game and options state.
 */
public class MenuState extends BasicGameState {

    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 50;
    private static final int SPACING = 75;
    private static final int RETURN = 3;
    private static final int FULLSCREEN = 1;
    private static final int CHANGE_RESOLUTION = 2;

    private UnicodeFont headerFont;
    private UnicodeFont subTextFont;
    private UnicodeFont baseFont;
    private Music menuMusic;
    private Sound menuSoundFX;
    private String mouse;
    private int baseX;
    private int baseY;
    private int state;

    /**
     * Options Buttons
     */
    private Button volume;
    private Button fullscreen;
    private Button changeRes;
    private Button returnToMenu;

    /**
     * Main Menu Buttons
     */
    private Button newGame;
    private Button loadGame;
    private Button options;
    private Button exitGame;

    /**
     * New Game Buttons
     */
    private Button confirm;
    private Button returnToMainMenu;

    private Resolutions resolutions;
    private AppGameContainer appgc;
    private UserSettings settings;

    public MenuState(AppGameContainer appgc, UserSettings settings) {
        this.appgc = appgc;
        this.settings = settings;
    }

    public int getID() {
        return States.MENU;
    }

    /**
     * Only runs once - initializes the variables we need to initialize only once
     *
     * @param gameContainer
     * @param stateBasedGame
     * @throws SlickException
     */
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        headerFont = FontLoader.getFont(120, "OldLondon.ttf");
        subTextFont = FontLoader.getFont(30, "OldLondon.ttf");
        baseFont = FontLoader.getFont(30, "Enchanted Land.otf");
        menuMusic = new Music("/assets/sound/menu.wav");
        menuSoundFX = new Sound("/assets/sound/hover.wav");
        state = States.MENU;
        mouse = "No input yet!";
        resolutions = new Resolutions(gameContainer.getHeight(), gameContainer.getWidth());
        if (!menuMusic.playing()) menuMusic.loop();
        initRenderOptions(gameContainer);
        initRenderMenu(gameContainer);
        initNewGameOptions(gameContainer);
    }

    /**
     * Runs 60 times per second, draws what we see on the screen
     *
     * @param gameContainer
     * @param stateBasedGame
     * @param graphics
     * @throws SlickException
     */
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        Image img = new Image("/assets/bg/mainmenu.jpg", false, Image.FILTER_NEAREST);
        graphics.drawImage(img, 0, -400);
        headerFont.drawString(gameContainer.getWidth() / 2 - 235, baseY-250, "Last Dawn", Color.decode("0x916628"));
        subTextFont.drawString(gameContainer.getWidth() / 2 - 245, baseY-130, "The journey of a thousand steps begins with one", Color.lightGray);

        //DEBUG
        graphics.drawString(mouse, 10, 30);
        graphics.drawString(gameContainer.getWidth() + "*" + gameContainer.getHeight(), 10, 50);

        if (state == States.MENU) renderMenu(gameContainer, graphics);
        else if (state == States.OPTIONS) renderOptions(gameContainer, graphics);
        else if (state == States.NEWGAME) renderNewGame(gameContainer, graphics);
    }


    /**
     * Runs an update when the conditions are verified - same logic as render, mostly.
     *
     * @param gameContainer
     * @param stateBasedGame
     * @param i
     * @throws SlickException
     */
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = "X: " + xpos + " Y: " + ypos;

        if (state == States.OPTIONS) updateOptions(gameContainer, stateBasedGame);
        else if (state == States.NEWGAME) updateNewGame(gameContainer, stateBasedGame);
        else if (state == States.MENU) updateMenu(gameContainer, stateBasedGame);
    }

    /**
     * ========================================
     * ========== MAIN MENU METHODS ===========
     * ========================================
     */

    private void initRenderMenu(GameContainer gameContainer) {
        baseX = gameContainer.getWidth() / 2 - BUTTON_WIDTH / 2;
        baseY = gameContainer.getHeight() / 2;
        newGame = new Button(gameContainer, baseX, baseY, BUTTON_WIDTH, BUTTON_HEIGHT, baseX + 70, baseY + 10, "New Game", subTextFont);
        loadGame = new Button(gameContainer, baseX, baseY + (FULLSCREEN * SPACING), BUTTON_WIDTH, BUTTON_HEIGHT, baseX + 70, baseY + (FULLSCREEN * SPACING) + 10, "Load Game", subTextFont);
        options = new Button(gameContainer, baseX, baseY + (CHANGE_RESOLUTION * SPACING), BUTTON_WIDTH, BUTTON_HEIGHT, baseX + 80, baseY + (CHANGE_RESOLUTION * SPACING) + 10, "Options", subTextFont);
        exitGame = new Button(gameContainer, baseX, baseY + (RETURN * SPACING), BUTTON_WIDTH, BUTTON_HEIGHT, baseX + 70, baseY + (RETURN * SPACING) + 10, "Exit Game", subTextFont);
    }

    private void renderMenu(GameContainer gameContainer, Graphics graphics) throws SlickException {
        newGame.render(gameContainer, graphics);
        loadGame.render(gameContainer, graphics);
        options.render(gameContainer, graphics);
        exitGame.render(gameContainer, graphics);
    }

    private void updateMenu(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        newGame.update(gameContainer);
        exitGame.update(gameContainer);
        loadGame.update(gameContainer);
        options.update(gameContainer);
        menuInput(gameContainer, stateBasedGame);
    }

    private void menuInput(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        if (exitGame.isPressed()) {
            menuSoundFX.play();
            gameContainer.sleep(150);
            gameContainer.exit();
        } else if (newGame.isPressed()) {
            menuSoundFX.play();
            state = States.NEWGAME;
        } else if (loadGame.isPressed()) {
            menuSoundFX.play();

            //@TODO LOAD SAVE FILE

            menuMusic.fade(1000, 0, true);
            stateBasedGame.enterState(States.INGAME);

        } else if (options.isPressed()) {
            menuSoundFX.play();
            state = States.OPTIONS;
        }
    }

    /**
     * ========================================
     * ========== MAIN MENU METHODS ===========
     * ========================================
     */


    /**
     * ========================================
     * =========== OPTIONS METHODS ============
     * ========================================
     */

    private void initRenderOptions(GameContainer gameContainer) {
        baseX = gameContainer.getWidth() / 2 - BUTTON_WIDTH / 2;
        baseY = gameContainer.getHeight() / 2;
        volume = new Button(gameContainer, baseX, baseY, BUTTON_WIDTH, BUTTON_HEIGHT, baseX + 40, baseY + 10, "Volume On/Off", subTextFont);
        fullscreen = new Button(gameContainer, baseX, baseY + (FULLSCREEN * SPACING), BUTTON_WIDTH, BUTTON_HEIGHT, baseX + 10, baseY + (FULLSCREEN * SPACING) + 10, "Fullscreen/Windowed", subTextFont);
        changeRes = new Button(gameContainer, baseX, baseY + (CHANGE_RESOLUTION * SPACING), BUTTON_WIDTH, BUTTON_HEIGHT, baseX + 30, baseY + (CHANGE_RESOLUTION * SPACING) + 10, "Change Resolution", subTextFont);
        returnToMenu = new Button(gameContainer, baseX, baseY + (RETURN * SPACING), BUTTON_WIDTH, BUTTON_HEIGHT, baseX + 40, baseY + (RETURN * SPACING) + 10, "Return to Menu", subTextFont);
    }

    private void renderOptions(GameContainer gameContainer, Graphics graphics) throws SlickException {
        volume.render(gameContainer, graphics);
        fullscreen.render(gameContainer, graphics);
        changeRes.render(gameContainer, graphics);
        returnToMenu.render(gameContainer, graphics);
    }

    private void updateOptions(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        volume.update(gameContainer);
        returnToMenu.update(gameContainer);
        fullscreen.update(gameContainer);
        changeRes.update(gameContainer);
        optionsInput(gameContainer, stateBasedGame);
    }

    private void optionsInput(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        if (returnToMenu.isPressed()) {
            menuSoundFX.play();
            settings.saveUserSettings();
            state = States.MENU;
        } else if (volume.isPressed()) {
            menuSoundFX.play();
            if (gameContainer.isMusicOn() || gameContainer.isSoundOn()) {
                gameContainer.setMusicOn(false);
                gameContainer.setSoundOn(false);
            } else {
                gameContainer.setMusicOn(true);
                gameContainer.setSoundOn(true);
            }
        } else if (fullscreen.isPressed()) {
            menuSoundFX.play();

            if (gameContainer.isFullscreen()) {
                appgc.setFullscreen(false);
                settings.setFullscreen(gameContainer.isFullscreen());
            } else {
                appgc.setFullscreen(true);
                settings.setFullscreen(gameContainer.isFullscreen());
            }

        } else if (changeRes.isPressed()) {
            menuSoundFX.play();
            int newRes[] = resolutions.cycleResolutions();
            appgc.setDisplayMode(newRes[0], newRes[1], gameContainer.isFullscreen());
            settings.setFullscreen(gameContainer.isFullscreen());
            settings.setResolution(newRes);
            initRenderOptions(gameContainer);
            initRenderMenu(gameContainer);
        }
    }

    /**
     * ========================================
     * =========== OPTIONS METHODS ============
     * ========================================
     */

    /**
     * ========================================
     * ========== NEW  GAME METHODS ===========
     * ========================================
     */
    private void initNewGameOptions(GameContainer gameContainer) {
        baseX = gameContainer.getWidth() / 2 - BUTTON_WIDTH / 2;
        baseY = gameContainer.getHeight() / 2;
        confirm = new Button(gameContainer, baseX, baseY, BUTTON_WIDTH, BUTTON_HEIGHT, baseX + 85, baseY + 10, "Confirm", subTextFont);
        returnToMainMenu = new Button(gameContainer, baseX, baseY + SPACING, BUTTON_WIDTH, BUTTON_HEIGHT, baseX + 40, baseY + SPACING + 10, "Return to Menu", subTextFont);

    }

    private void renderNewGame(GameContainer gameContainer, Graphics graphics) throws SlickException {
        baseFont.drawString(gameContainer.getWidth() / 2 - 160, baseY-40, "Delete previous save and create a new one?", Color.white);

        confirm.render(gameContainer, graphics);
        returnToMainMenu.render(gameContainer,graphics);
    }

    private void updateNewGame(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        confirm.update(gameContainer);
        returnToMainMenu.update(gameContainer);
        newGameInput(gameContainer, stateBasedGame);
    }

    private void newGameInput(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        if (returnToMainMenu.isPressed()) {

            menuSoundFX.play();
            state = States.MENU;

        } else if (confirm.isPressed()) {

            //@Todo CREATE NEW SAVE FILE

            menuSoundFX.play();
            menuMusic.fade(1000, 0, true);
            stateBasedGame.enterState(States.INGAME);
        }
    }
    /**
     * ========================================
     * ========== NEW  GAME METHODS ===========
     * ========================================
     */

}

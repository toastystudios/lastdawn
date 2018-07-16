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
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Responsible for showing the options
 */
public class OptionsState extends BasicGameState {

    private static final int MENU_OPTIONS = 4;
    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 50;
    private static final int SPACING = 75;
    private static final int MIN_X = 0;
    private static final int MAX_X = 1;
    private static final int MIN_Y = 2;
    private static final int MAX_Y = 3;
    private static final int RETURN = 0;
    private static final int FULLSCREEN = 2;
    private static final int CHANGE_RESOLUTION = 1;
    private static final int VOLUME = 3;

    private UnicodeFont headerFont;
    private UnicodeFont subTextFont;
    private UnicodeFont baseFont;
    private Sound menuSoundFX;
    private Input input;
    private int baseX;
    private int baseY;
    private int[][] buttonCoordinates = new int[MENU_OPTIONS][MENU_OPTIONS * 4];
    private Resolutions resolutions;

    public int getID() {
        return States.OPTIONS;
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
        menuSoundFX = new Sound("/assets/sound/hover.wav");
        input = gameContainer.getInput();
        resolutions = new Resolutions(gameContainer.getHeight(), gameContainer.getWidth());
        buttonPos(gameContainer);
    }

    private void buttonPos(GameContainer gameContainer) {
        baseX = gameContainer.getWidth() / 2 - BUTTON_WIDTH / 2;
        baseY = gameContainer.getHeight() / 2;
        int count = 0;
        for (int i = MENU_OPTIONS - 1; i >= 0; i--) {
            buttonCoordinates[i][MIN_X] = baseX;
            buttonCoordinates[i][MAX_X] = baseX + BUTTON_WIDTH;
            buttonCoordinates[i][MIN_Y] = baseY - (count * SPACING);
            buttonCoordinates[i][MAX_Y] = baseY - BUTTON_HEIGHT - (count * SPACING);
            count++;
        }
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
        headerFont.drawString(gameContainer.getWidth() / 2 - 225, 30, "Last Dawn", Color.decode("0x916628"));
        subTextFont.drawString(gameContainer.getWidth() / 2 - 245, 150, "The journey of a thousand steps begins with one", Color.lightGray);

        //Rectangles for the menus
        for (int i = 0; i < MENU_OPTIONS; i++) {
            createMenuRectangle(baseX, baseY + (i * SPACING), graphics);
            createBorderRectangle(baseX, baseY + (i * SPACING), graphics);
            createTextFields(baseX, baseY, graphics, i);
        }

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
        checkForInput(gameContainer, stateBasedGame);
    }

    /**
     * Creates a button for the menu
     *
     * @param x
     * @param y
     * @param g
     */
    private void createMenuRectangle(int x, int y, Graphics g) {
        Rectangle rectangle = new Rectangle(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        g.fill(rectangle);
        g.setColor(Color.darkGray);
        g.draw(rectangle);
    }

    /**
     * Creates a border for the previously created button
     *
     * @param x
     * @param y
     * @param g
     */
    private void createBorderRectangle(int x, int y, Graphics g) {
        Rectangle rectangle = new Rectangle(x, y, BUTTON_WIDTH + 5, BUTTON_HEIGHT + 5);
        g.fill(rectangle);
        g.setColor(Color.lightGray);
        g.draw(rectangle);
    }

    /**
     * Adds texts to the button
     *
     * @param x
     * @param y
     * @param g
     * @param i
     */
    private void createTextFields(int x, int y, Graphics g, int i) {

        switch (i) {
            case 0:
                baseFont.drawString(x + 55, y + 10, "Volume   On/Off");
                break;
            case 1:
                baseFont.drawString(x + 35, y + 10 + SPACING * i, "FullScreen/Windowed");
                break;
            case 2:
                baseFont.drawString(x + 55, y + 10 + SPACING * i, "Change   Resolution");
                break;
            case 3:
                baseFont.drawString(x + 100, y + 10 + SPACING * i, "Return");
                break;
            default:
                break;
        }
    }

    /**
     * When the cursor is in the bounds of a rectangle - aka a button - and the mouse is pressed, runs the code that the button is supposed to.
     *
     * @param gc
     * @param sbg
     */
    private void checkForInput(GameContainer gc, StateBasedGame sbg) {
        if (Mouse.getX() >= buttonCoordinates[RETURN][MIN_X]
                && Mouse.getX() <= buttonCoordinates[RETURN][MAX_X]
                && Mouse.getY() <= buttonCoordinates[RETURN][MIN_Y]
                && Mouse.getY() >= buttonCoordinates[RETURN][MAX_Y]) {

            if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {

                menuSoundFX.play();
                gc.sleep(200);

                int[] resolution = new int[2]; //updates the resolution of the menu state when we press return
                resolution[0] = gc.getWidth();
                resolution[1] = gc.getHeight();
                GameLoader.saveUserSettings(gc.isFullscreen(), resolution); //saves settings to the .txt file
                sbg.enterState(States.MENU); //enters menu state

            }
        } else if (Mouse.getX() >= buttonCoordinates[VOLUME][MIN_X]
                && Mouse.getX() <= buttonCoordinates[VOLUME][MAX_X]
                && Mouse.getY() <= buttonCoordinates[VOLUME][MIN_Y]
                && Mouse.getY() >= buttonCoordinates[VOLUME][MAX_Y]
                && input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {

            menuSoundFX.play();
            gc.sleep(200);

            //activates or deactivates all sound and music
            if (gc.isMusicOn() || gc.isSoundOn()) {
                gc.setMusicOn(false);
                gc.setSoundOn(false);
            } else {
                gc.setMusicOn(true);
                gc.setSoundOn(true);
            }

        } else if (Mouse.getX() >= buttonCoordinates[FULLSCREEN][MIN_X]
                && Mouse.getX() <= buttonCoordinates[FULLSCREEN][MAX_X]
                && Mouse.getY() <= buttonCoordinates[FULLSCREEN][MIN_Y]
                && Mouse.getY() >= buttonCoordinates[FULLSCREEN][MAX_Y]
                && input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {

            menuSoundFX.play();
            gc.sleep(200);

            //sets the window to fullscreen or windowed
            if (gc.isFullscreen()) {
                GameLoader.setWindowed();
            } else {
                GameLoader.setFullscreen();
            }
        } else if (Mouse.getX() >= buttonCoordinates[CHANGE_RESOLUTION][MIN_X]
                && Mouse.getX() <= buttonCoordinates[CHANGE_RESOLUTION][MAX_X]
                && Mouse.getY() <= buttonCoordinates[CHANGE_RESOLUTION][MIN_Y]
                && Mouse.getY() >= buttonCoordinates[CHANGE_RESOLUTION][MAX_Y]
                && input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {

            menuSoundFX.play();
            gc.sleep(200);

            //Cycles through the resolutions e.g if we're at 1920x1080(biggest) it'll return the next resolution - 800x600.
            //If we're on 800x600 it'll return 1280x720 and so on..
            int newRes[] = resolutions.cycleResolutions();

            //Sets the new resolution in the game container and saves it in the user settings
            GameLoader.changeResolution(newRes[0], newRes[1], gc.isFullscreen());
            GameLoader.getUserSettings().setFullscreen(gc.isFullscreen());
            GameLoader.getUserSettings().setResolution(newRes);

            //Updates the button position array because now we have a different resolution and need to re-check our input pixel by pixel
            buttonPos(gc);

            //Re-initlizes the menu state - not the best practice as it also restarts the music - @TODO - fix this problem
            try {
                sbg.getState(States.MENU).init(gc, sbg);
            } catch (SlickException e) {
                e.printStackTrace();
            }

        }
    }
}

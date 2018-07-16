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


public class MenuState extends BasicGameState {

    private static final int MENU_OPTIONS = 4;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 50;
    private static final int SPACING = 75;
    private static final int MIN_X = 0;
    private static final int MAX_X = 1;
    private static final int MIN_Y = 2;
    private static final int MAX_Y = 3;
    private static final int EXIT_GAME = 0;
    private static final int OPTIONS = 1;
    private static final int LOAD_GAME = 2;
    private static final int NEW_GAME = 3;

    private UnicodeFont headerFont;
    private UnicodeFont subTextFont;
    private UnicodeFont baseFont;
    private Music menuMusic;
    private Sound menuSoundFX;
    private String mouse;
    private Input input;
    private int baseX;
    private int baseY;
    private int[][] buttonCoordinates = new int[MENU_OPTIONS][MENU_OPTIONS * 4];

    public int getID() {
        return States.MENU;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        headerFont = FontLoader.getFont(120, "OldLondon.ttf");
        subTextFont = FontLoader.getFont(30, "OldLondon.ttf");
        baseFont = FontLoader.getFont(30, "Enchanted Land.otf");
        menuMusic = new Music("/assets/sound/menu.wav");
        menuSoundFX = new Sound("/assets/sound/hover.wav");
        input = gameContainer.getInput();
        if (!menuMusic.playing()) menuMusic.loop();
        mouse = "No input yet!";


        baseX = gameContainer.getWidth() / 2 - BUTTON_WIDTH/2;
        baseY = gameContainer.getHeight() / 2;

        int count = 0;
        for (int i = MENU_OPTIONS-1; i >= 0; i--) {
            buttonCoordinates[i][MIN_X] = baseX;
            buttonCoordinates[i][MAX_X] = baseX + BUTTON_WIDTH;
            buttonCoordinates[i][MIN_Y] = baseY - (count * SPACING);
            buttonCoordinates[i][MAX_Y] = baseY - BUTTON_HEIGHT - (count * SPACING);
            count++;
        }
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        Image img = new Image("/assets/bg/mainmenu.jpg", false, Image.FILTER_NEAREST);
        graphics.drawImage(img, 0, -400);
        headerFont.drawString(baseX - BUTTON_WIDTH, 30, "Last Dawn", Color.decode("0x916628"));
        subTextFont.drawString(baseX - BUTTON_WIDTH - 20, 150, "The journey of a thousand steps begins with one", Color.lightGray);


        //Rectangles for the menus
        for (int i = 0; i < MENU_OPTIONS; i++) {
            createMenuRectangle(baseX, baseY + (i * SPACING), graphics);
            createBorderRectangle(baseX, baseY + (i * SPACING), graphics);
            createTextFields(baseX, baseY, graphics, i);
        }

        //DEBUG
        graphics.drawString(mouse, 10, 30);
        graphics.drawString(gameContainer.getWidth() + "*" + gameContainer.getHeight(), 10, 50);

    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = "X: " + xpos + " Y: " + ypos;
        checkForInput(gameContainer, stateBasedGame);
    }

    private void createMenuRectangle(int x, int y, Graphics g) {
        Rectangle rectangle = new Rectangle(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        g.fill(rectangle);
        g.setColor(Color.darkGray);
        g.draw(rectangle);
    }

    private void createBorderRectangle(int x, int y, Graphics g) {
        Rectangle rectangle = new Rectangle(x, y, BUTTON_WIDTH + 5, BUTTON_HEIGHT + 5);
        g.fill(rectangle);
        g.setColor(Color.lightGray);
        g.draw(rectangle);
    }

    private void createTextFields(int x, int y, Graphics g, int i) {

        switch (i) {
            case 0:
                baseFont.drawString(x + 30, y + 10, "New   Game");
                break;
            case 1:
                baseFont.drawString(x + 30, y + 10 + SPACING * i, "Load  Game");
                break;
            case 2:
                baseFont.drawString(x + 40, y + 10 + SPACING * i, "Options");
                break;
            case 3:
                baseFont.drawString(x + 30, y + 10 + SPACING * i, "Exit  Game");
                break;
            default:
                break;
        }
    }

    private void checkForInput(GameContainer gc, StateBasedGame sbg) {

        //Exit Game
        if (Mouse.getX() >= buttonCoordinates[EXIT_GAME][MIN_X]
                && Mouse.getX() <= buttonCoordinates[EXIT_GAME][MAX_X]
                && Mouse.getY() <= buttonCoordinates[EXIT_GAME][MIN_Y]
                && Mouse.getY() >= buttonCoordinates[EXIT_GAME][MAX_Y]) {

            if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
                menuSoundFX.play();
                gc.sleep(200);
                gc.exit();
            }

        } else if (Mouse.getX() >= buttonCoordinates[OPTIONS][MIN_X]
                && Mouse.getX() <= buttonCoordinates[OPTIONS][MAX_X]
                && Mouse.getY() <= buttonCoordinates[OPTIONS][MIN_Y]
                && Mouse.getY() >= buttonCoordinates[OPTIONS][MAX_Y]
                && input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {

            menuSoundFX.play();
            gc.sleep(200);
            sbg.enterState(States.OPTIONS);

        } else if (Mouse.getX() >= buttonCoordinates[LOAD_GAME][MIN_X]
                && Mouse.getX() <= buttonCoordinates[LOAD_GAME][MAX_X]
                && Mouse.getY() <= buttonCoordinates[LOAD_GAME][MIN_Y]
                && Mouse.getY() >= buttonCoordinates[LOAD_GAME][MAX_Y]
                && input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {

            menuSoundFX.play();
            gc.sleep(200);
            sbg.enterState(States.LOAD);

        } else if (Mouse.getX() >= buttonCoordinates[NEW_GAME][MIN_X]
                && Mouse.getX() <= buttonCoordinates[NEW_GAME][MAX_X]
                && Mouse.getY() <= buttonCoordinates[NEW_GAME][MIN_Y]
                && Mouse.getY() >= buttonCoordinates[NEW_GAME][MAX_Y]
                && input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {

            menuSoundFX.play();
            gc.sleep(200);
            sbg.enterState(States.NEW);
        }
    }

}

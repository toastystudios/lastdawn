package lastdawn.engine;

import javafx.stage.Screen;
import lastdawn.states.States;
import lastdawn.utils.FontLoader;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;

public class OptionsState extends BasicGameState {

    private static final int MENU_OPTIONS = 3;
    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BASE_X = 275;
    private static final int BASE_Y = 250;
    private static final int SPACING = 75;
    private static final int MIN_X = 0;
    private static final int MAX_X = 1;
    private static final int MIN_Y = 2;
    private static final int MAX_Y = 3;
    private static final int RETURN = 2;
    private static final int VOLUME_OFF = 1;
    private static final int VOLUME_ON = 0;

    private UnicodeFont headerFont;
    private UnicodeFont subTextFont;
    private UnicodeFont baseFont;
    private Sound menuSoundFX;
    private Input input;
    private int[][] buttonCoordinates = new int[MENU_OPTIONS][MENU_OPTIONS * 4];

    public int getID() {
        return States.OPTIONS;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        headerFont = FontLoader.getFont(120, "OldLondon.ttf");
        subTextFont = FontLoader.getFont(30, "OldLondon.ttf");
        baseFont = FontLoader.getFont(30, "Enchanted Land.otf");
        menuSoundFX = new Sound("/assets/sound/hover.wav");
        input = gameContainer.getInput();

        int count = 0;
        for (int i = MENU_OPTIONS - 1; i >= 0; i--) {
            buttonCoordinates[i][MIN_X] = BASE_X;
            buttonCoordinates[i][MAX_X] = BASE_X + BUTTON_WIDTH;
            buttonCoordinates[i][MIN_Y] = 145 + (count * SPACING);
            buttonCoordinates[i][MAX_Y] = 145 + BUTTON_HEIGHT + (count * SPACING);
            count++;
        }
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        Image img = new Image("/assets/bg/mainmenu.jpg");
        graphics.drawImage(img, 0, -100);
        headerFont.drawString(150, 30, "Last Dawn", Color.decode("0x916628"));
        subTextFont.drawString(140, 150, "The journey of a thousand steps begins with one", Color.lightGray);
        //Rectangles for the menus
        for (int i = 0; i < MENU_OPTIONS; i++) {
            createMenuRectangle(BASE_X, BASE_Y + (i * SPACING), graphics);
            createBorderRectangle(BASE_X, BASE_Y + (i * SPACING), graphics);
            createTextFields(BASE_X, BASE_Y, graphics, i);
        }

    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
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
                baseFont.drawString(x + 80, y + 10, "Volume   On");
                break;
            case 1:
                baseFont.drawString(x + 80, y + 10 + SPACING * i, "Volume   Off");
                break;
            case 2:
                baseFont.drawString(x + 100, y + 10 + SPACING * i, "Return");
                break;
            default:
                break;
        }
    }

    private void checkForInput(GameContainer gc, StateBasedGame sbg) {
        if (Mouse.getX() >= buttonCoordinates[RETURN][MIN_X]
                && Mouse.getX() <= buttonCoordinates[RETURN][MAX_X]
                && Mouse.getY() >= buttonCoordinates[RETURN][MIN_Y]
                && Mouse.getY() <= buttonCoordinates[RETURN][MAX_Y]) {

            if (input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
                menuSoundFX.play();
                gc.sleep(200);
                sbg.enterState(States.MENU);
            }
        } else if (Mouse.getX() >= buttonCoordinates[VOLUME_OFF][MIN_X]
                && Mouse.getX() <= buttonCoordinates[VOLUME_OFF][MAX_X]
                && Mouse.getY() >= buttonCoordinates[VOLUME_OFF][MIN_Y]
                && Mouse.getY() <= buttonCoordinates[VOLUME_OFF][MAX_Y]
                && input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {

            menuSoundFX.play();
            gc.sleep(200);
            gc.setMusicOn(false);
            gc.setSoundOn(false);

        } else if (Mouse.getX() >= buttonCoordinates[VOLUME_ON][MIN_X]
                && Mouse.getX() <= buttonCoordinates[VOLUME_ON][MAX_X]
                && Mouse.getY() >= buttonCoordinates[VOLUME_ON][MIN_Y]
                && Mouse.getY() <= buttonCoordinates[VOLUME_ON][MAX_Y]
                && input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {

            menuSoundFX.play();
            gc.sleep(200);
            gc.setMusicOn(true);
            gc.setSoundOn(true);
        }
    }
}

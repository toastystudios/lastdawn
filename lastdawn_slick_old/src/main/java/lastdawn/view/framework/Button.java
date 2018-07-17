package lastdawn.view.framework;

import lastdawn.utils.FontLoader;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

public class Button extends AbstractComponent {

    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected int mouseX;
    protected int mouseY;
    protected boolean pressed;
    protected Shape hitbox;
    protected boolean over;
    protected String text;
    protected UnicodeFont font;
    protected int textPosX;
    protected int textPosY;

    public Button(GUIContext container, int x, int y, int width, int height, String text, int textPosX, int textPosY) {
        super(container);
        setLocation(x, y);
        this.width = width;
        this.height = height;
        this.text = text;
        hitbox = new Rectangle(x, y, width, height);
        this.font = null;
        this.textPosX = textPosX;
        this.textPosY = textPosY;
    }


    public Button(GUIContext container, int x, int y, int width, int height, int textPosX, int textPosY, String text, UnicodeFont font) {
        super(container);
        setLocation(x, y);
        this.width = width;
        this.height = height;
        this.text = text;
        hitbox = new Rectangle(x, y, width, height);
        this.font = font;
        this.textPosX = textPosX;
        this.textPosY = textPosY;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void update(GUIContext container) {
        mouseX = container.getInput().getMouseX();
        mouseY = container.getInput().getMouseY();
        over = hitbox.contains(mouseX, mouseY);

        if (over && container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            pressed = true;
        } else {
            pressed = false;
        }
    }

    private void createButton(Graphics g) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        g.fill(rectangle);
        g.setColor(Color.darkGray);
        g.draw(rectangle);
    }

    private void createBorder(Graphics g) {
        Rectangle rectangle = new Rectangle(x, y, width + 5, height + 5);
        g.fill(rectangle);
        g.setColor(Color.lightGray);
        g.draw(rectangle);
    }

    @Override
    public void render(GUIContext container, Graphics g) throws SlickException {
        createButton(g);
        createBorder(g);
        if (font == null) {
            g.setColor(Color.white);
            g.drawString(text, textPosX, textPosY);
        } else {
            g.setColor(Color.white);
            font.drawString(textPosX, textPosY, text);
        }
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
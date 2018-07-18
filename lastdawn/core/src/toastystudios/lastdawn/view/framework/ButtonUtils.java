package toastystudios.lastdawn.view.framework;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ButtonUtils {

    private static final int BUTTON_PADDING = 5;

    public static void addButton(TextButton button, Table table, Skin skin) {
        table.row().pad(BUTTON_PADDING, 0, BUTTON_PADDING, 0);
        table.add(button).fillX().uniformX();
        table.row().pad(BUTTON_PADDING, 0, BUTTON_PADDING, 0);
    }

}

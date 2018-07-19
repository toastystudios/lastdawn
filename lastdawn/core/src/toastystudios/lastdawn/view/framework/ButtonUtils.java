package toastystudios.lastdawn.view.framework;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ButtonUtils {

    public static void addButton(TextButton button, Table table, int width, int height, int padX, int padY) {
        table.row().pad(padX, 0, padY, 0);
        table.add(button).width(width).height(height);
        table.row().pad(padX, 0, padY, 0);
    }

}

package toastystudios.lastdawn.view.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontLoader {

    public static final String OLD_LONDON = "assets/fonts/OldLondon.ttf";
    public static final String JOSEFIN_SANS = "assets/fonts/JosefinSans.ttf";

    public static BitmapFont loadFont(String path, int size, Color fontColor, Color borderColor, float borderWith) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = fontColor;
        parameter.borderColor = borderColor;
        parameter.borderWidth = borderWith;
        return generator.generateFont(parameter);
    }
}

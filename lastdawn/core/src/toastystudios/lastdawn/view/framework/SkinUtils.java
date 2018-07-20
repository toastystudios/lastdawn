package toastystudios.lastdawn.view.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SkinUtils {

    public static Skin loadCustomButtonFont(BitmapFont buttonFont, String atlas, String path) {

        Skin skin = new Skin();

        skin.add("default-font", buttonFont, BitmapFont.class);
        skin.add("small-font", buttonFont, BitmapFont.class);

        FileHandle fileHandle = Gdx.files.local(path);
        FileHandle atlasFile = fileHandle.sibling(atlas);

        if (atlasFile.exists()) {
            skin.addRegions(new TextureAtlas(atlasFile));
        } else {
            return null;
        }

        skin.load(fileHandle);

        return skin;
    }
}

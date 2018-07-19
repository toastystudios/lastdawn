package toastystudios.lastdawn.view.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SkinUtils {

    public static final String DEFAULT = "assets/skin/default/uiskin.json";
    public static final String VIS_SKIN = "assets/sgxskin/vis/x1/uiskin.json";

    public static Skin loadCustomButtonFont(BitmapFont buttonFont, String atlas, String path) {
        Skin skin = new Skin();
        skin.add("default-font", buttonFont, BitmapFont.class);
        skin.add("small-font", buttonFont, BitmapFont.class);
        FileHandle fileHandle = Gdx.files.local(path);
        FileHandle atlasFile = fileHandle.sibling(atlas);

        if (atlasFile.exists()) {
            skin.addRegions(new TextureAtlas(atlasFile));
        }

        skin.load(fileHandle);

        return skin;
    }

    public static Skin loadSkin(String path) {
        return new Skin(Gdx.files.local(path));
    }
}

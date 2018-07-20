package toastystudios.lastdawn.Controller;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import toastystudios.lastdawn.view.framework.SkinUtils;

public class WorldAssetManager {

    public final AssetManager manager = new AssetManager();

    //Sound
    public static final String hover = "assets/sound/ingame/hover.wav";
    public static final String ping = "assets/sound/ingame/ping.wav";

    //Textures
    public static final String playerImage = "assets/ingame/player_19.png";
    public static final String enemyImage = "assets/ingame/Featured_Executioner.png";

    //Menu
    public static final String menuMusic = "assets/sound/menu/menu.wav";
    public static final String menuBackgroundImage = "assets/bg/mainmenu.jpg";

    //Skin
    public static final String skin = "assets/skin/default/uiskin.json";
    public static final String skinAtlas = "uiskin.atlas";

    //Que items carregar...
    public void queueAddImages(){
        manager.load(playerImage, Texture.class);
        manager.load(enemyImage, Texture.class);
    }

    public void queueAddBackground() {
        manager.load(menuBackgroundImage, Texture.class);
    }

    public void queueAddSounds(){
        manager.load(new AssetDescriptor(hover,Sound.class));
        manager.load(new AssetDescriptor(ping,Sound.class));
    }

    public void queueMenuBackgroundMusic() {
        manager.load(new AssetDescriptor(menuMusic, Music.class));
    }

    public void queueAddDefaultSkin() {
        SkinLoader.SkinParameter params = new SkinLoader.SkinParameter(skinAtlas);
        manager.load(skin, Skin.class, params);
        manager.finishLoading();
    }

    public Skin queueAddSkinWithCustomFont(BitmapFont font) {
        return SkinUtils.loadCustomButtonFont(font, skinAtlas, skin);
    }

}

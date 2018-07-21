package toastystudios.lastdawn.Controller;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import toastystudios.lastdawn.view.framework.SkinUtils;

public class WorldAssetManager {

    public final AssetManager manager = new AssetManager();

    public final static String assetsPrefix = "assets/";

    public final static String gameImages = assetsPrefix + "images/ingame.atlas";
    public final static String loadingImages = assetsPrefix +"images/loading.atlas";

    public final static String backgroundMenuImage = assetsPrefix + "images/mainmenu.png";

    //Sound
    public static final String hover = "assets/sound/ingame/hover.wav";
    public static final String ping = "assets/sound/ingame/ping.wav";
    public static final String menuMusic = "assets/sound/menu/menu.wav";

    //Skin
    public static final String skin = "assets/skin/default/uiskin.json";
    public static final String skinAtlas = "uiskin.atlas";



    public void queueAddImages(){
        manager.load(gameImages, TextureAtlas.class);
    }

    public void queueMenuBackgroundImage() {
        manager.load(backgroundMenuImage, Texture.class);
    }

    // a small set of images used by the loading screen
    public void queueAddLoadingImages(){
        manager.load(loadingImages, TextureAtlas.class);
    }

    public void queueAddFonts(){
    }

    public void queueAddParticleEffects(){
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

package toastystudios.lastdawn.Controller;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import toastystudios.lastdawn.view.framework.SkinUtils;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;

public class WorldAssetManager {

    public final com.badlogic.gdx.assets.AssetManager manager = new com.badlogic.gdx.assets.AssetManager();


    //Images
    public final static String backgroundMenuImage = "assets/images/mainmenu.png";
    public final static String newGameBackgroundMenuImage = "assets/images/newgame.png";
    public final static String playerRunningUp = "assets/images/player-anim/running-up.png";
    public final static String playerRunningDown = "assets/images/player-anim/running-down.png";
    public final static String playerRunningLeft = "assets/images/player-anim/running-left.png";
    public final static String playerRunningRight = "assets/images/player-anim/running-right.png";

    //Maps
    public final static String level_1 = "assets/level/level1.tmx";
    public final static String level_2 = "assets/level/level2.tmx";

    //Sound
    public static final String hover = "assets/sound/ingame/hover.wav";
    public static final String ping = "assets/sound/ingame/ping.wav";
    public static final String menuMusic = "assets/sound/menu/menu.wav";

    //Skin
    public static final String skin = "assets/skin/default/uiskin.json";
    public static final String skinAtlas = "uiskin.atlas";


    public void queueAddImages(){
        manager.load(backgroundMenuImage, Texture.class);
        manager.load(playerRunningUp, Texture.class);
        manager.load(playerRunningDown, Texture.class);
        manager.load(playerRunningLeft, Texture.class);
        manager.load(playerRunningRight, Texture.class);
    }

    public void queueNewGameBackgroundImage() {
        manager.load(newGameBackgroundMenuImage, Texture.class);
    }

    public void queueAddTiledMaps() {
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        TmxMapLoader.Parameters params = new TmxMapLoader.Parameters();
        params.generateMipMaps = true;
        manager.load(level_1, TiledMap.class, params);
        manager.load(level_2, TiledMap.class, params);
    }

    public void queueAddParticleEffects(){

    }

    public void queueAddMusic() {

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

    public String readFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                }
                return sb.toString();
            } catch (IOException e) {
                System.out.println("Error reading file!");
            } finally {
                br.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}

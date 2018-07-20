package toastystudios.lastdawn.Controller;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class WorldAssetManager {

    public final AssetManager manager = new AssetManager();

    //Sound
    public final String hover = "assets/sound/hover.wav";
    public final String ping = "assets/sound/ping.wav";

    //Textures
    public final String playerImage = "assets/ingame/player_19.png";
    public final String enemyImage = "assets/ingame/Featured_Executioner.png";

    //Que items carregar...
    public void queueAddImages(){
        manager.load(playerImage, Texture.class);
        manager.load(enemyImage, Texture.class);
    }

    public void queueAddSounds(){
        manager.load(new AssetDescriptor(hover,Sound.class));
        manager.load(new AssetDescriptor(ping,Sound.class));
    }

}

package toastystudios.lastdawn.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Array;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.view.*;

public class GameLoader extends Game {

    private LoadingScreen loadingScreen;
    private OptionScreen optionScreen;
    private MenuScreen menuScreen;
    private NewGameScreen newGameScreen;
    private LoadGameScreen loadGameScreen;
    private AppSettings settings;

    public WorldAssetManager assMan = new WorldAssetManager();

    private Music backgroundMusic;

    public final static int MENU = 0;
    public final static int OPTIONS = 1;
    public final static int NEWGAME = 2;
    public final static int LOADGAME = 3;

    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        settings = new AppSettings();
        setScreen(loadingScreen);

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.local("assets/sound/menu.wav"));
        backgroundMusic.setVolume(settings.getMusicVolume());
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        if (!settings.isMusicEnabled()) backgroundMusic.stop();

        Gdx.graphics.setWindowedMode(settings.getResolution()[0], settings.getResolution()[1]);

        if (settings.isFullscreen()) Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());

    }

    public AppSettings getSettings() {
        return this.settings;
    }

    public void changeScreen(int screen) {
        switch (screen) {
            case MENU:
                if (menuScreen == null) menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case OPTIONS:
                if (optionScreen == null) optionScreen = new OptionScreen(this, backgroundMusic);
                this.setScreen(optionScreen);
                break;
            case NEWGAME:
                if (newGameScreen == null) newGameScreen = new NewGameScreen();
                this.setScreen(newGameScreen);
                break;
            case LOADGAME:
                if (loadGameScreen == null) loadGameScreen = new LoadGameScreen(this);
                this.setScreen(loadGameScreen);
                break;
        }
    }

    @Override
    public void render() {
        super.render();
    }

    public void stopMusic() {
        this.backgroundMusic.stop();
    }

    public Array<String> availableResolutions() {
        Array<String> resolutions = new Array<String>();
        resolutions.add("800x600");
        resolutions.add("1024x768");
        resolutions.add("1280x720");
        resolutions.add("1360x768");
        resolutions.add("1440x900");
        resolutions.add("1600x900");
        resolutions.add("1920x1080");
        return resolutions;
    }
}

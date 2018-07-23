package toastystudios.lastdawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.view.framework.FontLoader;


public class OptionScreen implements Screen {

    private GameLoader parent;
    private Stage stage;
    private TextButton returnToMenu;
    private Table table;
    private Skin buttonSkin;
    private Slider volumeMusicSlider;
    private Slider soundMusicSlider;
    private CheckBox soundEffectsCheckbox;
    private CheckBox musicCheckbox;
    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;
    private Texture backgroundImage;
    private BitmapFont buttonFont;
    private BitmapFont textFont;
    private Skin textSkin;
    private BitmapFont subTextFont;
    private BitmapFont headerFont;
    private BitmapFont baseFont;
    private Label fullScreenLabel;
    private CheckBox fullscreen;
    private Music backgroundMusic;
    private SelectBox<String> selectBox;
    private Label resolutionLabel;
    private BitmapFont selectorFont;
    private Skin selectorSkin;

    public OptionScreen(GameLoader gameLoader, Music backgroundMusic) {
        this.parent = gameLoader;
        stage = new Stage(new ScreenViewport());
        this.backgroundMusic = backgroundMusic;
    }


    @Override
    public void show() {

        stage.clear();


        // Create a table that fills the screen. Everything else will go inside this table.
        table = new Table();
        table.setFillParent(true);
        table.setPosition(0, -100);
        stage.addActor(table);

        subTextFont = FontLoader.loadFont(FontLoader.OLD_LONDON, 30, Color.LIGHT_GRAY, Color.BLACK, 0.0f);
        headerFont = FontLoader.loadFont(FontLoader.OLD_LONDON, 100, Color.valueOf("916628"), Color.BLACK, 1.1f);
        baseFont = FontLoader.loadFont(FontLoader.JOSEFIN_SANS, 10, Color.WHITE, Color.BLACK, 0.0f);

        selectorFont = FontLoader.loadFont(FontLoader.JOSEFIN_SANS, 20, Color.WHITE, Color.BLACK, 0.0f);
        selectorSkin = parent.assMan.queueAddSkinWithCustomFont(selectorFont);

        buttonFont = FontLoader.loadFont(FontLoader.JOSEFIN_SANS, 25, Color.WHITE, Color.BLACK, 0.0f);
        buttonSkin =  parent.assMan.queueAddSkinWithCustomFont(buttonFont);

        textFont = FontLoader.loadFont(FontLoader.JOSEFIN_SANS, 25, Color.WHITE, Color.BLACK, 1.3f);
        textSkin = parent.assMan.queueAddSkinWithCustomFont(textFont);


        selectBox = new SelectBox<String>(selectorSkin);
        selectBox.setItems(parent.availableResolutions());
        selectBox.setSelected(parent.getSettings().getSelectedResolution());

        if (parent.getSettings().isFullscreen()) {
            selectBox.setTouchable(Touchable.disabled);
        } else {
            selectBox.setTouchable(Touchable.enabled);
        }

        selectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                String resolution = selectBox.getSelected();

                String[] split = resolution.split("x");

                int width = Integer.parseInt(split[0]);
                int height = Integer.parseInt(split[1]);

                int currentWidth = parent.getSettings().getResolution()[0];
                int currentHeight = parent.getSettings().getResolution()[1];

                if (width != currentWidth || currentHeight != height) {
                    parent.getSettings().setResolution(width, height);
                    Gdx.graphics.setWindowedMode(parent.getSettings().getResolution()[0], parent.getSettings().getResolution()[1]);

                    if (parent.getSettings().isFullscreen()) Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                }
            }
        });

        volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, buttonSkin);
        volumeMusicSlider.setValue(parent.getSettings().getMusicVolume());
        volumeMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getSettings().setMusicVolume(volumeMusicSlider.getValue());
                backgroundMusic.setVolume(parent.getSettings().getMusicVolume());
                return false;
            }
        });

        soundMusicSlider = new Slider(0f, 1f, 0.1f, false, buttonSkin);
        soundMusicSlider.setValue(parent.getSettings().getSoundVolume());
        soundMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getSettings().setSoundVolume(soundMusicSlider.getValue());
                return false;
            }
        });

        musicCheckbox = new CheckBox(null, buttonSkin);
        musicCheckbox.setChecked(parent.getSettings().isMusicEnabled());
        musicCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                parent.getSettings().setMusicEnabled(enabled);

                if (!backgroundMusic.isPlaying() && enabled) backgroundMusic.play();
                else if (backgroundMusic.isPlaying() && !enabled) backgroundMusic.pause();

                return false;
            }
        });

        fullscreen = new CheckBox(null, buttonSkin);
        fullscreen.setChecked(parent.getSettings().isFullscreen());
        fullscreen.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = fullscreen.isChecked();
                parent.getSettings().setFullscreen(enabled);

                if (parent.getSettings().isFullscreen()) {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                    selectBox.setTouchable(Touchable.disabled);
                }
                else if (!parent.getSettings().isFullscreen()) {
                    Gdx.graphics.setWindowedMode(parent.getSettings().getResolution()[0], parent.getSettings().getResolution()[1]);
                    selectBox.setTouchable(Touchable.enabled);
                }


                return false;
            }
        });

        soundEffectsCheckbox = new CheckBox(null, buttonSkin);
        soundEffectsCheckbox.setChecked(parent.getSettings().isSoundEffectsEnabled());
        soundEffectsCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundEffectsCheckbox.isChecked();
                parent.getSettings().setSoundEffectsEnabled(enabled);
                return false;
            }
        });

        returnToMenu = new TextButton("Return", buttonSkin);
        returnToMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(GameLoader.MENU);
            }
        });

        resolutionLabel = new Label("Resolution", textSkin);
        fullScreenLabel = new Label("Enable FullScreen", textSkin);
        volumeMusicLabel = new Label("Music Volume", textSkin);
        volumeSoundLabel = new Label("Sound Volume", textSkin);
        musicOnOffLabel = new Label("Music Enabled", textSkin);
        soundOnOffLabel = new Label("Sound Enabled", textSkin);

        table.add(resolutionLabel);
        table.add();
        table.add(selectBox);
        table.row().pad(10, 0, 10, 10);

        table.add(fullScreenLabel);
        table.add();
        table.add(fullscreen);
        table.row().pad(10, 0, 10, 10);

        table.add(volumeMusicLabel);
        table.add();
        table.add(volumeMusicSlider);
        table.row().pad(10, 0, 10, 10);

        table.add(musicOnOffLabel);
        table.add();
        table.add(musicCheckbox);
        table.row().pad(10, 0, 10, 10);

        table.add(volumeSoundLabel);
        table.add();
        table.add(soundMusicSlider);
        table.row().pad(10, 0, 10, 10);

        table.add(soundOnOffLabel);
        table.add();
        table.add(soundEffectsCheckbox);
        table.row().pad(15, 0, 0, 10);

        table.add();
        table.add(returnToMenu).width(150).height(50);
        table.add();
        backgroundImage = parent.assMan.manager.get(WorldAssetManager.backgroundMenuImage);
    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(backgroundImage, 0, 0, stage.getWidth(), stage.getHeight());
        headerFont.draw(stage.getBatch(), "Options", 0, stage.getHeight() / 2 + 200, stage.getWidth(), Align.center, false);
        baseFont.draw(stage.getBatch(), "Artwork by hongqi zhang: https://www.artstation.com/artwork/WY9YD", 0, 10, stage.getWidth(), Align.bottomLeft, false);
        stage.getBatch().end();

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

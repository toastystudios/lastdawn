package toastystudios.lastdawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.view.framework.SkinSelector;


public class OptionScreen implements Screen {

    private GameLoader parent;
    private Stage stage;
    private TextButton returnToMenu;
    private Table table;
    private Skin skin;
    private Slider volumeMusicSlider;
    private Slider soundMusicSlider;
    private CheckBox soundEffectsCheckbox;
    private CheckBox musicCheckbox;
    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;

    public OptionScreen(GameLoader gameLoader) {
        this.parent = gameLoader;
        stage = new Stage(new ScreenViewport());
    }



    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal(SkinSelector.DEFAULT));
        volumeMusicSlider = new Slider( 0f, 1f, 0.1f,false, skin );

        volumeMusicSlider.setValue( parent.getSettings().getMusicVolume() );

        volumeMusicSlider.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getSettings().setMusicVolume( volumeMusicSlider.getValue() );
                return false;
            }
        });

        soundMusicSlider = new Slider( 0f, 1f, 0.1f,false, skin );

        soundMusicSlider.setValue( parent.getSettings().getMusicVolume() );

        soundMusicSlider.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                parent.getSettings().setMusicVolume( soundMusicSlider.getValue() );
                return false;
            }
        });

        musicCheckbox = new CheckBox(null, skin);
        musicCheckbox.setChecked( parent.getSettings().isMusicEnabled() );
        musicCheckbox.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                parent.getSettings().setMusicEnabled( enabled );
                return false;
            }
        });

        soundEffectsCheckbox = new CheckBox(null, skin);
        soundEffectsCheckbox.setChecked( parent.getSettings().isMusicEnabled() );
        soundEffectsCheckbox.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundEffectsCheckbox.isChecked();
                parent.getSettings().setSoundEffectsEnabled(enabled);
                return false;
            }
        });

        returnToMenu = new TextButton("Return", skin);
        returnToMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(GameLoader.MENU);
            }
        });

        titleLabel = new Label("Options", skin);
        volumeMusicLabel = new Label("Music Volume", skin);
        volumeSoundLabel = new Label("Sound Volume", skin);
        musicOnOffLabel = new Label("Music Enabled", skin);
        soundOnOffLabel = new Label("Sound Enabled", skin);

        table.add();
        table.add(titleLabel);
        table.add();
        table.row().pad(50, 0, 0, 0);
        table.add(volumeMusicLabel);
        table.add();
        table.add(volumeMusicSlider);
        table.row();
        table.add(musicOnOffLabel);
        table.add();
        table.add(musicCheckbox);
        table.row();
        table.add(volumeSoundLabel);
        table.add();
        table.add(soundMusicSlider);
        table.row();
        table.add(soundOnOffLabel);
        table.add();
        table.add(soundEffectsCheckbox);
        table.row().pad(10, 0, 0, 0);
        table.add();
        table.add(returnToMenu);
        table.add();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    }
}

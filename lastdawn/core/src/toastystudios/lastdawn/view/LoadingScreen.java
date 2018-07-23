package toastystudios.lastdawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.view.framework.FontLoader;

public class LoadingScreen implements Screen {

    public final int IMAGE = 0;        // loading images
    public final int FONT = 1;        // loading fonts
    public final int PARTY = 2;        // loading particle effects
    public final int SOUND = 3;        // loading sounds
    public final int MUSIC = 4;        // loading music

    private int currentLoadingStage = -1;
    public float countDown = 0f;
    private Stage stage;
    private GameLoader parent; // a field to store our orchestrator
    private BitmapFont headerFont;
    private BitmapFont subTextFont;
    private Texture backgroundImage;

    // our constructor with a Box2DTutorial argument
    public LoadingScreen(GameLoader gameLoader) {
        parent = gameLoader;
        stage = new Stage(new ScreenViewport());
    }


    @Override
    public void show() {
        headerFont = FontLoader.loadFont(FontLoader.OLD_LONDON, 120, Color.valueOf("916628"), Color.BLACK, 1.1f);
        subTextFont = FontLoader.loadFont(FontLoader.OLD_LONDON, 30, Color.LIGHT_GRAY, Color.BLACK, 0.0f);
        parent.assMan.queueMenuBackgroundImage();
        parent.assMan.manager.finishLoading();
        backgroundImage = parent.assMan.manager.get(WorldAssetManager.backgroundMenuImage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(backgroundImage, 0, 0, stage.getWidth(), stage.getHeight());
        headerFont.draw(stage.getBatch(), "Last Dawn", 0, stage.getHeight() / 2 + 200, stage.getWidth(), Align.center, false);
        subTextFont.draw(stage.getBatch(), "Now Loading...", 0, stage.getHeight() / 2 + 100, stage.getWidth(), Align.center, false);
        stage.getBatch().end();


        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
        Gdx.input.setInputProcessor(stage);

        // check if the asset manager has finished loading
        if (parent.assMan.manager.update()) { // Load some, will return true if done loading
            currentLoadingStage += 1;
            switch (currentLoadingStage) {
                case IMAGE:
                    stage.getBatch().begin();
                    subTextFont.draw(stage.getBatch(), "Loading images..", 0, stage.getHeight() / 2, stage.getWidth(), Align.center, false);
                    stage.getBatch().end();
                    parent.assMan.queueAddImages();
                case FONT:
                    stage.getBatch().begin();
                    subTextFont.draw(stage.getBatch(), "Loading fonts..", 0, stage.getHeight() / 2, stage.getWidth(), Align.center, false);
                    stage.getBatch().end();
                    parent.assMan.queueAddFonts(); // first load done, now start fonts
                    break;
                case PARTY:
                    stage.getBatch().begin();
                    subTextFont.draw(stage.getBatch(), "Loading particles..", 0, stage.getHeight() / 2, stage.getWidth(), Align.center, false);
                    stage.getBatch().end();
                    parent.assMan.queueAddParticleEffects(); // fonts are done now do party effects
                    break;
                case SOUND:
                    stage.getBatch().begin();
                    subTextFont.draw(stage.getBatch(), "Loading sound..", 0, stage.getHeight() / 2, stage.getWidth(), Align.center, false);
                    stage.getBatch().end();
                    parent.assMan.queueAddSounds();
                    break;
                case MUSIC:
                    stage.getBatch().begin();
                    subTextFont.draw(stage.getBatch(), "Loading music..", 0, stage.getHeight() / 2, stage.getWidth(), Align.center, false);
                    stage.getBatch().end();
                    parent.assMan.queueAddMusic();
                    break;
            }

            if (currentLoadingStage > 5) {
                countDown -= delta;  // timer to stay on loading screen for short preiod once done loading
                currentLoadingStage = 5;  // cap loading stage to 5 as will use later to display progress bar anbd more than 5 would go off the screen

                stage.getBatch().begin();
                subTextFont.draw(stage.getBatch(), "Finishing up..", 0, stage.getHeight() / 2, stage.getWidth(), Align.center, false);
                stage.getBatch().end();

                if (countDown < 0) { // countdown is complete
                    parent.changeScreen(parent.MENU);  /// go to menu screen
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
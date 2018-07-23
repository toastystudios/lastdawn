package toastystudios.lastdawn.view;

import com.badlogic.gdx.Screen;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.stages.StageOne;

public class GameScreen implements Screen {

    private GameLoader parent;
    private StageOne stage;

<<<<<<< HEAD
    @Override
    public void show () {

        Gdx.input.setInputProcessor(null);

        // load the koala frame
        playerTexture = new TextureRegion(new Texture("player.png"));

        // figure out the width and height of the player for collision
        // detection and rendering by converting a player frames pixel
        // size into world units (1 unit == 16 pixels)
        playerWidth = 1 / SCALE * playerTexture.getRegionWidth();
        playerHeight = 1 / SCALE * playerTexture.getRegionHeight();
        playerX = 50;
        playerY = 50;

        // load the map, set the unit scale to 1/16 (1 unit == 16 pixels)
        map = new TmxMapLoader().load("level3.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / SCALE);

        // create an orthographic camera, shows us 30x30 units of the world
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 20, 15);
        camera.update();
=======
    public GameScreen(GameLoader parent) {
        this.parent = parent;
        stage = new StageOne(parent);
>>>>>>> 7c8f35622482529ce4d0cf37f669c19169944c64
    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public void render(float delta) {
        stage.render();
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

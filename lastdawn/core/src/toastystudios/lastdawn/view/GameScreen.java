package toastystudios.lastdawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen implements Screen {

    private static final float SCALE = 32f;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TextureRegion playerTexture;
    private float playerWidth;
    private float playerHeight;
    private float playerX;
    private float playerY;

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
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render (float dt) {
        // clear the screen
        Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update the koala (process input, collision detection, position update)
        updatePlayer();

        // let the camera follow the koala, x-axis only
        camera.position.x = playerX;
        camera.position.y = playerY;
        camera.update();

        // set the TiledMapRenderer view based on what the
        // camera sees, and render the map
        renderer.setView(camera);
        renderer.render();

        // render the koala
        renderPlayer();
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

    private void updatePlayer () {
        if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
            playerX -= 0.1f;
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
            playerX += 0.1f;
        }

        if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) {
            playerY += 0.1f;
        }

        if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
            playerY -= 0.1f;
        }
    }

    private void renderPlayer() {
        // draw the player
        Batch batch = renderer.getBatch();
        batch.begin();
        batch.draw(playerTexture, playerX, playerY, playerWidth, playerHeight);
        batch.end();
    }

    @Override
    public void dispose () {
    }
}

package toastystudios.lastdawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import toastystudios.lastdawn.model.GameWorld;

public class GameScreen implements Screen {

    private GameWorld gameWorld;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    public GameScreen() {
        this.gameWorld = new GameWorld();
        this.camera = new OrthographicCamera(32,24);
        debugRenderer = new Box2DDebugRenderer(true, true,true,true,true,true);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gameWorld.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(gameWorld.world, camera.combined);
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

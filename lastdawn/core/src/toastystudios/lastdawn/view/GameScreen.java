package toastystudios.lastdawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.stages.StageOne;

import static com.sun.webkit.graphics.GraphicsDecoder.SCALE;

public class GameScreen implements Screen {

    private GameLoader parent;
    private StageOne stage;

    public GameScreen(GameLoader parent) {
        this.parent = parent;
        stage = new StageOne(parent);
    }

    @Override
    public void show () {
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

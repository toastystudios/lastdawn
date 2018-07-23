package toastystudios.lastdawn.view;

import com.badlogic.gdx.Screen;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.stages.StageOne;

public class GameScreen implements Screen {

    private GameLoader parent;
    private StageOne stage;

    public GameScreen(GameLoader parent) {
        this.parent = parent;
        stage = new StageOne(parent);
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

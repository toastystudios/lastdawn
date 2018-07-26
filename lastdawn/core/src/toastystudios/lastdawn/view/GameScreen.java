package toastystudios.lastdawn.view;

import com.badlogic.gdx.Screen;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.stages.Stage;
import toastystudios.lastdawn.stages.StageOne;

public class GameScreen implements Screen {

    private GameLoader parent;
    private Stage stage;

    public GameScreen(GameLoader parent) {
        this.parent = parent;
        stage = new Stage(parent);
    }

    @Override
    public void show (){
        stage.create();
    }

    @Override
    public void render(float delta) {
        stage.render();
    }

    @Override
    public void resize(int width, int height) {
        stage.resize(width, height);
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

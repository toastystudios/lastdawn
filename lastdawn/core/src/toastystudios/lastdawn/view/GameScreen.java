package toastystudios.lastdawn.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.areas.Area;
import toastystudios.lastdawn.areas.AreaOne;

public class GameScreen implements Screen {

    private GameLoader parent;
    private Area area;
    private Stage stage;

    public GameScreen(GameLoader parent) {
        this.parent = parent;
        area = new AreaOne(parent);
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show (){
        area.create();
    }

    @Override
    public void render(float delta) {
        area.render();
    }

    @Override
    public void resize(int width, int height) {
        area.resize(width, height);
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
        area.dispose();
    }
}

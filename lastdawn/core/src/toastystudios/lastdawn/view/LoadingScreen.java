package toastystudios.lastdawn.view;

import com.badlogic.gdx.Screen;
import toastystudios.lastdawn.engine.GameLoader;

public class LoadingScreen implements Screen {

    private GameLoader parent; // a field to store our orchestrator

    // our constructor with a Box2DTutorial argument
    public LoadingScreen(GameLoader box2dTutorial){
        parent = box2dTutorial;     // setting the argument to our field.
    }
    @Override
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(float delta) {
        parent.changeScreen(GameLoader.MENU);
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
    }
}
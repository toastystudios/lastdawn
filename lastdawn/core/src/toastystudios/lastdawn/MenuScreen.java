package toastystudios.lastdawn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {

    private GameLoader parent;
    private Stage stage;

    public MenuScreen(GameLoader box2dTutorial){
        parent = box2dTutorial;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        TextButton newGame = new TextButton("New Game", skin);
        TextButton loadGame = new TextButton("Load Game",  skin);
        TextButton options = new TextButton("Options",  skin);
        TextButton exit = new TextButton("Exit", skin);

        table.row().pad(5, 0, 5, 0);
        table.add(newGame).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
        table.add(loadGame).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
        table.add(options).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
        table.add(exit).fillX().uniformX();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        System.out.println(Gdx.graphics.getFramesPerSecond());
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

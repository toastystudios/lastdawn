package toastystudios.lastdawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.utils.GifDecoder;
import toastystudios.lastdawn.view.framework.ButtonUtils;
import toastystudios.lastdawn.view.framework.SkinSelector;

public class MenuScreen implements Screen {

    private GameLoader parent;
    private Stage stage;

    private TextButton newGame;
    private TextButton loadGame;
    private TextButton options;
    private TextButton exit;
    private Table table;
    private Skin skin;

    public MenuScreen(GameLoader gameLoader){
        parent = gameLoader;
        stage = new Stage(new ScreenViewport());
    }


    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal(SkinSelector.DEFAULT));

        newGame = new TextButton("New Game", skin);
        loadGame = new TextButton("Load Game",  skin);
        options = new TextButton("Options",  skin);
        exit = new TextButton("Exit", skin);

        ButtonUtils.addButton(newGame, table, skin);
        ButtonUtils.addButton(loadGame, table, skin);
        ButtonUtils.addButton(options, table, skin);
        ButtonUtils.addButton(exit, table, skin);

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(GameLoader.NEWGAME);
            }
        });

        loadGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(GameLoader.LOADGAME);
            }
        });

        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(GameLoader.OPTIONS);
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
        Gdx.input.setInputProcessor(stage);
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
        stage.dispose();
    }
}

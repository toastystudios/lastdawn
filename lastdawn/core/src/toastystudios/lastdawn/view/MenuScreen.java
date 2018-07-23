package toastystudios.lastdawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.view.framework.ButtonUtils;
import toastystudios.lastdawn.view.framework.FontLoader;

public class MenuScreen implements Screen {

    private GameLoader parent;
    private Stage stage;

    private TextButton newGame;
    private TextButton loadGame;
    private TextButton options;
    private TextButton exit;
    private Table table;
    private Skin skin;
    private Texture backgroundImage;
    private BitmapFont headerFont;
    private BitmapFont subTextFont;
    private BitmapFont buttonFont;
    private BitmapFont baseFont;

    public MenuScreen(GameLoader gameLoader) {
        parent = gameLoader;
        stage = new Stage(new ScreenViewport());
    }


    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.setPosition(0, -100);

        buttonFont = FontLoader.loadFont(FontLoader.JOSEFIN_SANS, 25, Color.WHITE, Color.BLACK, 0.0f);
        headerFont = FontLoader.loadFont(FontLoader.OLD_LONDON, 120, Color.valueOf("916628"), Color.BLACK, 1.1f);
        subTextFont = FontLoader.loadFont(FontLoader.OLD_LONDON, 30, Color.LIGHT_GRAY, Color.BLACK, 0.0f);
        baseFont = FontLoader.loadFont(FontLoader.JOSEFIN_SANS, 10, Color.WHITE, Color.BLACK, 0.0f);

        backgroundImage = parent.assMan.manager.get(WorldAssetManager.backgroundMenuImage);

        skin = parent.assMan.queueAddSkinWithCustomFont(buttonFont);
        newGame = new TextButton("New Game", skin);
        loadGame = new TextButton("Load Game", skin);
        options = new TextButton("Options", skin);
        exit = new TextButton("Exit", skin);

        ButtonUtils.addButton(newGame, table, 150, 50, 5, 5);
        ButtonUtils.addButton(loadGame, table, 150, 50, 5, 5);
        ButtonUtils.addButton(options, table, 150, 50, 5, 5);
        ButtonUtils.addButton(exit, table, 150, 50, 5, 5);


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
                parent.stopMusic();
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

        stage.getBatch().begin();
        stage.getBatch().draw(backgroundImage, 0, 0, stage.getWidth(), stage.getHeight());
        headerFont.draw(stage.getBatch(), "Last Dawn", 0, stage.getHeight() / 2 + 200, stage.getWidth(), Align.center, false);
        subTextFont.draw(stage.getBatch(), "The journey of a thousand steps begins with one", 0, stage.getHeight() / 2 + 100, stage.getWidth(), Align.center, false);
        baseFont.draw(stage.getBatch(), "Artwork by hongqi zhang: https://www.artstation.com/artwork/WY9YD", 0, 10, stage.getWidth(), Align.bottomLeft, false);
        stage.getBatch().end();


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
        headerFont.dispose();
        subTextFont.dispose();
        buttonFont.dispose();
        stage.dispose();
    }
}

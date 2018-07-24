package toastystudios.lastdawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.view.framework.FontLoader;

public class NewGameScreen implements Screen {

    private GameLoader parent;
    private Stage stage;

    private Table table;
    private Table buttonTable;
    private Table labelTable;
    private Skin skin;
    private Texture backgroundImage;
    private Music backgroundMusic;
    private Label introTextLabel;

    private BitmapFont headerFont;
    private BitmapFont subTextFont;
    private BitmapFont buttonFont;
    private BitmapFont baseFont;

    private TextButton nextButton;
    private TextButton backButton;
    private static int flag;


    public NewGameScreen(GameLoader gameLoader, Music bgMusic){
        this.parent = gameLoader;
        stage = new Stage(new ScreenViewport());
        this.backgroundMusic=bgMusic;
        this.flag=0;
    }

    @Override
    public void show() {

        stage.clear();

        buttonFont = FontLoader.loadFont(FontLoader.JOSEFIN_SANS, 25, Color.WHITE, Color.BLACK, 0.0f);
        headerFont = FontLoader.loadFont(FontLoader.OLD_LONDON, 120, Color.valueOf("916628"), Color.BLACK, 1.1f);
        subTextFont = FontLoader.loadFont(FontLoader.OLD_LONDON, 30, Color.LIGHT_GRAY, Color.BLACK, 0.0f);
        baseFont = FontLoader.loadFont(FontLoader.JOSEFIN_SANS, 10, Color.WHITE, Color.BLACK, 0.0f);

        parent.assMan.queueNewGameBackgroundImage();
        parent.assMan.manager.finishLoading();
        backgroundImage = parent.assMan.manager.get(WorldAssetManager.newGameBackgroundMenuImage);
        skin = parent.assMan.queueAddSkinWithCustomFont(buttonFont);

        //CREATION ZONE
        table = new Table();
        buttonTable = new Table();
        introTextLabel = new Label("HERE I AM!!!",skin);

        //SETTINGS ZONE
        table.setFillParent(true);
        table.setPosition(0, 0);
        table.defaults().pad(10f);

        buttonTable.setPosition(0,0);

        nextButton = new TextButton("Next",skin);
        backButton = new TextButton("Main Menu",skin);

        //ADDING ZONE
        buttonTable.add(backButton).width(150).height(50);
        buttonTable.add(nextButton).width(150).height(50);

        table.add(introTextLabel).colspan(2).fillX();
        table.row();
        table.add(buttonTable);

        stage.addActor(table);
        stage.setDebugAll(true);


        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println(flag);
                flag++;
                System.out.println(flag);
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(GameLoader.MENU);
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(backgroundImage, 0, 0, stage.getWidth(), stage.getHeight());
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

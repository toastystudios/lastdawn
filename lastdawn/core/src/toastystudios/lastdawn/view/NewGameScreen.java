package toastystudios.lastdawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.domain.model.enums.ClassTypes;
import toastystudios.lastdawn.domain.model.player.PlayerChar;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.view.framework.FontLoader;
import toastystudios.lastdawn.domain.model.character.Character;

public class NewGameScreen implements Screen {

    private GameLoader parent;
    private Stage stage;

    private Table table;
    private Table buttonTable;
    private Table mainTable;
    private Table charCreationTable;
    private Skin skin;
    private Skin labelSkin;
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
    private static boolean charCreationDoorOpen;

    private String introString;
    private Table classPickTable;
    private Label classPickLabel;
    private TextButton sciBtn;
    private TextButton palaBtn;
    private TextButton knigBtn;
    private TextButton arcBtn;
    private Table classTextInputTable;
    private Label inputName;
    private TextField nameInserted;
    private ScrollPane scroll;

    private ClassTypes chosenClass;

    public NewGameScreen(GameLoader gameLoader, Music bgMusic){
        this.parent = gameLoader;
        stage = new Stage(new ScreenViewport());
        this.backgroundMusic=bgMusic;
    }

    @Override
    public void show() {
        this.flag=3;
        this.charCreationDoorOpen=true;

        stage.clear();

        //TODO: arrumar a casa <-<-<-<-<-<-<-<-<-<-<-<-<-<-<

        introString = parent.assMan.readFile("assets/text/english/intro/intro_test1.txt");

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

        mainTable = new Table();
        introTextLabel = new Label("HERE I AM!!!",skin);

        buttonTable = new Table();
        nextButton = new TextButton("Next",skin);
        backButton = new TextButton("Main Menu",skin);

        Actor splitter = new Actor();
        Actor splitterMain = new Actor();

        charCreationTable = new Table();
        classPickTable = new Table();
        classTextInputTable = new Table();

        classPickLabel = new Label("Class context",skin);
        inputName = new Label("Input name",skin);
        nameInserted = new TextField("",skin);

        knigBtn = new TextButton("Knight",skin);
        palaBtn = new TextButton("Paladin",skin);
        arcBtn = new TextButton("Archer",skin);
        sciBtn = new TextButton("Scientist",skin);

        scroll = new ScrollPane(classPickLabel);

        //SETTINGS ZONE
        table.setFillParent(true);
        table.setPosition(0, 0);
        table.defaults().pad(10f);

        mainTable.setName("mainTable");

        buttonTable.bottom().padBottom(10f);
        introTextLabel.setAlignment(Align.center);

        classPickTable.defaults().pad(5f);
        classTextInputTable.defaults().pad(5f);

        splitter.setName("splitter");
        splitterMain.setName("splitterMain");

        //ADDING ZONE
        classPickTable.row().pad(10);
        classPickTable.add(knigBtn).width(150).height(50);
        classPickTable.row().pad(10);
        classPickTable.add(palaBtn).width(150).height(50);
        classPickTable.row().pad(10);
        classPickTable.add(arcBtn).width(150).height(50);
        classPickTable.row().pad(10);
        classPickTable.add(sciBtn).width(150).height(50);
        classPickTable.row().pad(10);
        classPickTable.add(scroll).width(250).height(150);
        classPickTable.row().pad(10);

        classTextInputTable.add(inputName).width(150).height(50);
        classTextInputTable.row();
        classTextInputTable.add(nameInserted).fillX();

        charCreationTable.add(classPickTable);
        charCreationTable.add(splitterMain);
        charCreationTable.add(classTextInputTable);

        mainTable.add(introTextLabel);

        buttonTable.add(backButton).width(150).height(50);
        buttonTable.add(splitter);
        buttonTable.add(nextButton).width(150).height(50);

        table.add(mainTable).expand();
        table.row();
        table.add(buttonTable).fillX();

        stage.addActor(table);
        stage.setDebugAll(true);


        //EVENT LISTENERS

        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                flag--;
                introString = parent.assMan.readFile("assets/text/english/intro/intro_test2.txt");
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(GameLoader.MENU);
            }
        });

        knigBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                chosenClass = ClassTypes.KNIGHT;
                System.out.println("Picked Knight");
                classPickLabel.setText(parent.assMan.readFile("assets/text/english/character/classes/presentation/knight_test.txt"));
            }
        });

        palaBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                chosenClass = ClassTypes.PALADIN;
                System.out.println("Picked Paladin");
                classPickLabel.setText(parent.assMan.readFile("assets/text/english/character/classes/presentation/paladin_test.txt"));
            }
        });

        arcBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                chosenClass = ClassTypes.RANGER;
                System.out.println("Picked Archer/Ranger");
                classPickLabel.setText(parent.assMan.readFile("assets/text/english/character/classes/presentation/archer_test.txt"));
            }
        });

        sciBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                chosenClass = ClassTypes.SCIENTIST;
                System.out.println("Picked Scientist");
                classPickLabel.setText(parent.assMan.readFile("assets/text/english/character/classes/presentation/scientist_test.txt"));
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(backgroundImage, 0, 0, stage.getWidth(), stage.getHeight());
        introTextLabel.setText(introString);

        if(flag==1){
            if(charCreationDoorOpen) {
                for (Cell c : table.getCells()) {
                    if (c.getActor().getName() == "mainTable") {
                        c.setActor(charCreationTable).fillX().fillY();
                    }
                }
            }
            charCreationDoorOpen=false;
            nextButton.setText("Finish");
        }
        if(flag==0){
            //TODO: garantir que nome é inserido && class é escolhida
            String name = nameInserted.getText();
            parent.gameManager.createNewGame(name,chosenClass);
            parent.stopMusic();
            this.dispose();
            parent.changeScreen(GameLoader.LOADGAME);
        }
        stage.getBatch().end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
        stage.draw();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        for(Cell c : buttonTable.getCells()){
            if(c.getActor().getName()=="splitter"){
                c.width(Gdx.graphics.getWidth()/2);
            }
        }
        for(Cell e : charCreationTable.getCells()){
            if(e.getActor().getName()=="splitterMain"){
                e.width(Gdx.graphics.getWidth()/4);
            }
        }
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

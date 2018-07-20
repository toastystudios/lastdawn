package toastystudios.lastdawn.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import toastystudios.lastdawn.Controller.KeyboardController;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.model.WorldController;

public class LoadGameScreen implements Screen {

    private SpriteBatch sb;
    private Texture playerTex;
    private WorldController model;
    private OrthographicCamera cam;
    private Box2DDebugRenderer debugRenderer;
    private GameLoader parent;
    private KeyboardController controller;

    public LoadGameScreen(GameLoader loader) {
        parent = loader;
        cam = new OrthographicCamera(32,24);
        controller = new KeyboardController();
        model = new WorldController(controller,cam, parent.assMan);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);

        //carrega as imagens
        parent.assMan.queueAddImages();
        //obriga a terminar as imagens
        parent.assMan.manager.finishLoading();
        playerTex = parent.assMan.manager.get(WorldAssetManager.playerImage);
        //1 spriteBatch para tudo?!
        sb = new SpriteBatch();
        sb.setProjectionMatrix(cam.combined);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f,0f,0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, cam.combined);

        //sprite draw
        sb.begin();
        sb.draw(playerTex, model.player.getPosition().x-1,model.player.getPosition().y-2,2,4);
        sb.end();
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

package toastystudios.lastdawn.view;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import toastystudios.lastdawn.Controller.KeyboardController;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.engine.components.*;
import toastystudios.lastdawn.engine.systems.*;
import toastystudios.lastdawn.utils.BodyFactory;
import toastystudios.lastdawn.utils.WorldContactListener;
import toastystudios.lastdawn.view.framework.Assets;

public class GameScreen implements Screen {

    private GameLoader parent;
    private OrthographicCamera cam;
    private KeyboardController controller;
    private SpriteBatch sb;
    private PooledEngine engine;
    private World world;
    private BodyFactory bodyFactory;
    private TextureAtlas atlas;


    public GameScreen(GameLoader gameLoader) {
        parent = gameLoader;
        controller = new KeyboardController();
        world = new World(new Vector2(0, -70f), true);
        world.setContactListener(new WorldContactListener());
        bodyFactory = BodyFactory.getBodyFactory(world);

        parent.assMan.queueAddSounds();
        parent.assMan.manager.finishLoading();
        atlas = parent.assMan.manager.get(WorldAssetManager.gameImages, TextureAtlas.class);

        sb = new SpriteBatch();
        RenderingSystem renderingSystem = new RenderingSystem(sb);
        cam = renderingSystem.getCamera();
        sb.setProjectionMatrix(cam.combined);


        engine = new PooledEngine();

        engine.addSystem(new AnimationSystem());
        engine.addSystem(renderingSystem);
        engine.addSystem(new PhysicsSystem(world));
        engine.addSystem(new PhysicsDebugSystem(world, renderingSystem.getCamera()));
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new PlayerControlSystem(controller));

        createPlayer();


        //64 pixels (tile width) * 30 = 1920 PIXELS WIDTH
        for (int i = 0; i < 30; i++) {
            createTile(1f + (float) (2 * i), 1f, Assets.brickWall);
        }

        //64 pixels (tile height) * 17 = 1088 PIXELS HEIGHT - close enough to 1080


        for (int i = 0; i < 30; i++) {
            createTile(5 + (i*2), 3 + (i*2), Assets.brickWall);
        }

    }

    private void createTile(float x, float y, String textureName) {
        Entity entity = engine.createEntity();

        B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
        b2dbody.body = bodyFactory.makeBoxPolyBody(x, y, 2f, 2f, BodyFactory.STONE, BodyDef.BodyType.StaticBody);
        b2dbody.body.setUserData(entity);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = atlas.findRegion(textureName);

        TypeComponent type = engine.createComponent(TypeComponent.class);
        type.type = TypeComponent.SCENERY;

        TransformComponent trans = engine.createComponent(TransformComponent.class);
        trans.position.set(x, y, 0);

        entity.add(trans);
        entity.add(b2dbody);
        entity.add(texture);
        entity.add(type);

        engine.addEntity(entity);
    }

    private void createPlayer() {

        Entity entity = engine.createEntity();
        B2dBodyComponent b2dbody = engine.createComponent(B2dBodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        StateComponent stateCom = engine.createComponent(StateComponent.class);

//        b2dbody.body = bodyFactory.makeBoxPolyBody(10, 10, 2, 3.5F, BodyFactory.PLAYER, BodyDef.BodyType.DynamicBody, true);// set object position (x,y,z) z used to define draw order 0 first drawn
        b2dbody.body = bodyFactory.makeCirclePolyBody(10, 10, 1, BodyFactory.STONE, BodyDef.BodyType.DynamicBody, true);// set object position (x,y,z) z used to define draw order 0 first drawn
        position.position.set(10, 10, 0);
        texture.region = atlas.findRegion(Assets.player);
        type.type = TypeComponent.PLAYER;
        stateCom.set(StateComponent.STATE_NORMAL);
        b2dbody.body.setUserData(entity);

        entity.add(b2dbody);
        entity.add(position);
        entity.add(texture);
        entity.add(player);
        entity.add(colComp);
        entity.add(type);
        entity.add(stateCom);

        engine.addEntity(entity);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(delta);

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

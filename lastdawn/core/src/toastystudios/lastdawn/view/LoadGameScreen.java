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
import toastystudios.lastdawn.model.WorldController;
import toastystudios.lastdawn.utils.BodyFactory;
import toastystudios.lastdawn.utils.WorldContactListener;
import toastystudios.lastdawn.view.framework.Assets;

public class LoadGameScreen implements Screen {


    private PooledEngine engine;
    private BodyFactory bodyFactory;
    private World world;
    private TextureAtlas atlas;
    private SpriteBatch sb;
    private TextureAtlas.AtlasRegion playerTex;
    private WorldController model;
    private OrthographicCamera cam;
    private Box2DDebugRenderer debugRenderer;
    private GameLoader parent;
    private KeyboardController controller;

    public LoadGameScreen(GameLoader loader) {
        this.parent = loader;
        this.controller = new KeyboardController();
        this.world = new World(new Vector2(0,-10f), true);
        this.world.setContactListener(new WorldContactListener());
        this.bodyFactory = BodyFactory.getBodyFactory(world);
        this.sb = new SpriteBatch();

        this.atlas = parent.assMan.manager.get(WorldAssetManager.gameImages);
        this.playerTex = atlas.findRegion(Assets.player);

        RenderingSystem renderingSystem = new RenderingSystem(sb);
        this.cam = renderingSystem.getCamera();
        sb.setProjectionMatrix(cam.combined);
        
        this.engine = new PooledEngine();
        this.engine.addSystem(new AnimationSystem());
        this.engine.addSystem(new PhysicsSystem(world));
        this.engine.addSystem(new PhysicsDebugSystem(world, renderingSystem.getCamera()));
        this.engine.addSystem(new CollisionSystem());
        this.engine.addSystem(new PlayerControlSystem(controller));

        // create some game objects
        createPlayer();
        createPlatform(2,2);
        createPlatform(2,7);
        createPlatform(7,2);
        createPlatform(7,7);

        createFloor();
    }

    private void createPlayer() {

        // Create the Entity and all the components that will go in the entity
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        StateComponent stateCom = engine.createComponent(StateComponent.class);

        body.body = bodyFactory.makeCirclePolyBody(10, 10, 1, BodyFactory.STONE, BodyDef.BodyType.DynamicBody, true);
        position.position.set(10, 10, 0);
        texture.region = playerTex;
        type.type = TypeComponent.PLAYER;
        stateCom.set(StateComponent.STATE_NORMAL);
        body.body.setUserData(entity);

        entity.add(body);
        entity.add(position);
        entity.add(texture);
        entity.add(player);
        entity.add(colComp);
        entity.add(type);
        entity.add(stateCom);

        engine.addEntity(entity);
    }


    private void createPlatform(float x, float y){
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        body.body = bodyFactory.makeBoxPolyBody(x, y, 3, 0.2f, BodyFactory.STONE, BodyDef.BodyType.StaticBody);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = atlas.findRegion(Assets.player);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        type.type = TypeComponent.SCENERY;
        body.body.setUserData(entity);

        entity.add(body);
        entity.add(texture);
        entity.add(type);

        engine.addEntity(entity);

    }

    private void createFloor(){
        Entity entity = engine.createEntity();
        BodyComponent body = engine.createComponent(BodyComponent.class);
        body.body = bodyFactory.makeBoxPolyBody(0, 0, 100, 0.2f, BodyFactory.STONE, BodyDef.BodyType.StaticBody);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = atlas.findRegion(Assets.player);
        TypeComponent type = engine.createComponent(TypeComponent.class);
        type.type = TypeComponent.SCENERY;

        body.body.setUserData(entity);

        entity.add(body);
        entity.add(texture);
        entity.add(type);

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

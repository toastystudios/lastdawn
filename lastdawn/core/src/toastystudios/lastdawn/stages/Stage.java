package toastystudios.lastdawn.stages;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import toastystudios.lastdawn.Controller.KeyboardController;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.engine.OrthoTiledRenderWithSprites;
import toastystudios.lastdawn.utils.BodyFactory;
import toastystudios.lastdawn.utils.Constants;
import toastystudios.lastdawn.utils.TiledObjectUtil;

public class Stage extends ApplicationAdapter {

    protected final GameLoader parent;

    protected boolean DEBUG = false;
    protected final float SCALE = 2.0f;

    protected Box2DDebugRenderer b2dr;
    protected KeyboardController input;
    protected OrthographicCamera camera;
    protected SpriteBatch batch;
    protected World world;
    protected Viewport viewport;

    protected OrthogonalTiledMapRenderer tmr;
    protected TiledMap map;
    protected Body player;
    protected TextureMapObject tmo;
    protected MapLayer objectLayer;

    protected Texture walk_up_texture;
    protected Texture walk_down_texture;
    protected Texture walk_left_texture;
    protected Texture walk_right_texture;

    protected Animation<TextureRegion> walk_up;
    protected Animation<TextureRegion> walk_down;
    protected Animation<TextureRegion> walk_right;
    protected Animation<TextureRegion> walk_left;

    protected TextureRegion frame;
    protected TextureRegion[] walk_up_regions;
    protected TextureRegion[] walk_down_regions;
    protected TextureRegion[] walk_left_regions;
    protected TextureRegion[] walk_right_regions;

    protected enum Turned {
        UP, DOWN, LEFT, RIGHT;
    }

    protected Turned facing;
    protected float stateTime;
    protected float deltaTime;
    protected boolean moving;


    public Stage(GameLoader gameLoader) {
        this.parent = gameLoader;
    }

    @Override
    public void create() {

        deltaTime = Gdx.graphics.getDeltaTime();

        input = new KeyboardController();
        Gdx.input.setInputProcessor(input);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / SCALE, h / SCALE);
        viewport = new ScreenViewport(camera);
        world = new World(new Vector2(0, 0), false);
        b2dr = new Box2DDebugRenderer(true, true, true, true, true, true);
        batch = new SpriteBatch();

        loadWalkAnimations();

        player = BodyFactory.getBodyFactory(world).createBox(10, 10, 16, 16, false);

        map = new TmxMapLoader().load("assets/level/level1.tmx");
        tmr = new OrthoTiledRenderWithSprites(map);
        TiledObjectUtil.parseTiledObjectLayer(world, map.getLayers().get("collision").getObjects());

        objectLayer = map.getLayers().get("npcs");
        tmo = new TextureMapObject(walk_up_regions[0]);
        objectLayer.getObjects().add(tmo);
    }

    private void loadWalkAnimations() {
        //walk animations
        walk_up_texture = parent.assMan.manager.get(WorldAssetManager.playerRunningUp);
        walk_up_regions = TextureRegion.split(walk_up_texture, 32, 32)[0];
        walk_up = new Animation<>(0.15f, walk_up_regions[0], walk_up_regions[1], walk_up_regions[2], walk_up_regions[3], walk_up_regions[4]);
        walk_up.setPlayMode(Animation.PlayMode.LOOP);

        walk_down_texture = parent.assMan.manager.get(WorldAssetManager.playerRunningDown);
        walk_down_regions = TextureRegion.split(walk_down_texture, 32, 32)[0];
        walk_down = new Animation<>(0.15f, walk_down_regions[0], walk_down_regions[1], walk_down_regions[2], walk_down_regions[3], walk_down_regions[4]);
        walk_down.setPlayMode(Animation.PlayMode.LOOP);

        walk_left_texture = parent.assMan.manager.get(WorldAssetManager.playerRunningLeft);
        walk_left_regions = TextureRegion.split(walk_left_texture, 32, 32)[0];
        walk_left = new Animation<>(0.15f, walk_left_regions[0], walk_left_regions[1], walk_left_regions[2], walk_left_regions[3], walk_left_regions[4]);
        walk_left.setPlayMode(Animation.PlayMode.LOOP);

        walk_right_texture = parent.assMan.manager.get(WorldAssetManager.playerRunningRight);
        walk_right_regions = TextureRegion.split(walk_right_texture, 32, 32)[0];
        walk_right = new Animation<>(0.15f, walk_right_regions[0], walk_right_regions[1], walk_right_regions[2], walk_right_regions[3], walk_right_regions[4]);
        walk_right.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b2dr.render(world, camera.combined.scl(Constants.PPM));
        camera.update();
        renderPlayer();
        tmr.setView(camera);
        tmr.render();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.setToOrtho(false, width / SCALE, height / SCALE);
    }

    public void update(float delta) {
        world.step(1 / 60f, 6, 2);
        cameraUpdate();
        inputUpdate();
        tmr.setView(camera);
        batch.setTransformMatrix(camera.view);
        batch.setProjectionMatrix(camera.projection);
    }

    private void inputUpdate() {

        if (deltaTime == 0) return;

        if (deltaTime > 0.1f)
            deltaTime = 0.1f;

        stateTime += deltaTime;

        int horizontalForce = 0;
        int verticalForce = 0;

        if (input.up) {
            horizontalForce += 1;
            facing = Turned.UP;
            moving = true;
        }

        if (input.down) {
            horizontalForce -= 1;
            facing = Turned.DOWN;
            moving = true;
        }

        if (input.left) {
            verticalForce -= 1;
            facing = Turned.LEFT;
            moving = true;
        }

        if (input.right) {
            verticalForce += 1;
            facing = Turned.RIGHT;
            moving = true;
        }

        player.setLinearVelocity(horizontalForce * 5, player.getLinearVelocity().y);
        player.setLinearVelocity(verticalForce * 5, player.getLinearVelocity().x);

        if (!input.up && !input.down && !input.left && !input.right) moving = false;
    }

    private void cameraUpdate() {
        Vector3 position = camera.position;
        position.x = player.getPosition().x * Constants.PPM;
        position.y = player.getPosition().y * Constants.PPM;
        camera.position.set(position);
        camera.update();
    }

    protected void renderPlayer() {

        frame = walk_down.getKeyFrames()[0];

        if (moving && facing == Turned.UP) {
            frame = walk_up.getKeyFrame(stateTime);
        } else if (moving && facing == Turned.LEFT) {
            frame = walk_left.getKeyFrame(stateTime);
        } else if (moving && facing == Turned.RIGHT) {
            frame = walk_right.getKeyFrame(stateTime);
        } else if (moving && facing == Turned.DOWN) {
            frame = walk_down.getKeyFrame(stateTime);
        } else if (facing == Turned.UP && !moving) {
            frame = walk_up.getKeyFrames()[0];
        } else if (facing == Turned.DOWN && !moving) {
            frame = walk_down.getKeyFrames()[0];
        } else if (facing == Turned.RIGHT && !moving) {
            frame = walk_right.getKeyFrames()[0];
        } else if (facing == Turned.LEFT && !moving) {
            frame = walk_left.getKeyFrames()[0];
        }

        tmo.setTextureRegion(frame);
        tmo.setX(player.getPosition().x * Constants.PPM - (frame.getRegionWidth() / 2));
        tmo.setY(player.getPosition().y * Constants.PPM - (frame.getRegionHeight() / 3));
    }


    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        batch.dispose();
        tmr.dispose();
        map.dispose();
    }
}

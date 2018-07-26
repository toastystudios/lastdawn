package toastystudios.lastdawn.areas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import toastystudios.lastdawn.Controller.KeyboardController;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.engine.WorldContactListener;
import toastystudios.lastdawn.utils.BodyFactory;
import toastystudios.lastdawn.utils.Constants;

public class Area extends ApplicationAdapter {

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
    protected RenderCharacter player;
    protected MapLayer objectLayer;

    protected float stateTime;
    protected float deltaTime;
    protected boolean isPaused;


    public Area(GameLoader gameLoader) {
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
        world.setContactListener(new WorldContactListener());
        b2dr = new Box2DDebugRenderer(true, true, true, true, true, true);
        batch = new SpriteBatch();

        String[] animationsPath = {WorldAssetManager.playerRunningUp, WorldAssetManager.playerRunningDown, WorldAssetManager.playerRunningLeft, WorldAssetManager.playerRunningRight};
        player = new RenderCharacter(parent, world, 10, 10, 16, 16, animationsPath, BodyDef.BodyType.DynamicBody);
    }


    @Override
    public void render() {
        update(deltaTime);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.setToOrtho(false, width / SCALE, height / SCALE);
    }

    public void update(float delta) {
        world.step(1 / 60f, 6, 2);
        cameraUpdate();
        uiUpdate();

        if (!isPaused) {
            movementUpdate();
            tmr.setView(camera);
            batch.setTransformMatrix(camera.view);
            batch.setProjectionMatrix(camera.projection);
        }
    }

    private void uiUpdate() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            isPaused = !isPaused;
        }
    }

    private void movementUpdate() {

        if (deltaTime == 0) return;

        if (deltaTime > 0.1f)
            deltaTime = 0.1f;

        stateTime += deltaTime;

        int horizontalForce = 0;
        int verticalForce = 0;

        if (input.up) {
            verticalForce += 1;
            player.setTurnedUp();
            player.setMoving();
        }

        if (input.down) {
           verticalForce -= 1;
            player.setTurnedDown();
            player.setMoving();
        }

        if (input.left) {
            horizontalForce -= 1;
            player.setTurnedLeft();
            player.setMoving();
        }

        if (input.right) {
            horizontalForce += 1;
            player.setTurnedRight();
            player.setMoving();
        }

        player.applyLinearForce(horizontalForce, verticalForce);

        if (!input.up && !input.down && !input.left && !input.right) player.setStanding();
    }

    private void cameraUpdate() {
        Vector3 position = camera.position;
        position.x = player.getPosition().x * Constants.PPM;
        position.y = player.getPosition().y * Constants.PPM;
        camera.position.set(position);
        camera.update();
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

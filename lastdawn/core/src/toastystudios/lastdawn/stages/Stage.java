package toastystudios.lastdawn.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import toastystudios.lastdawn.Controller.KeyboardController;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.engine.GameLoader;

public abstract class Stage {


    protected static final float SCALE = 32f;
    protected GameLoader parent;
    protected OrthogonalTiledMapRenderer renderer;
    protected OrthographicCamera camera;

    protected float playerWidth;
    protected float playerHeight;
    protected float playerX;
    protected float playerY;

    protected Texture walk_up_texture;
    protected Texture walk_down_texture;
    protected Texture walk_left_texture;
    protected Texture walk_right_texture;

    protected Animation<TextureRegion> walk_up;
    protected Animation<TextureRegion> walk_down;
    protected Animation<TextureRegion> walk_right;
    protected Animation<TextureRegion> walk_left;

    protected TextureRegion[] walk_up_regions;
    protected TextureRegion[] walk_down_regions;
    protected TextureRegion[] walk_left_regions;
    protected TextureRegion[] walk_right_regions;

    protected enum Turned {
        UP, DOWN, LEFT, RIGHT;
    }
    protected Turned facing;

    protected boolean moving;
    protected boolean runningUp;
    protected boolean runningDown;
    protected boolean runningLeft;
    protected boolean runningRight;


    protected float stateTime;


    private KeyboardController controller;

    public Stage(GameLoader parent) {
        this.parent = parent;
        this.controller = new KeyboardController();
    }

    public void show() {

        Gdx.input.setInputProcessor(controller);

        //walk animations
        walk_up_texture = parent.assMan.manager.get(WorldAssetManager.playerRunningUp);
        walk_up_regions = TextureRegion.split(walk_up_texture, 32, 32)[0];
        walk_up = new Animation<TextureRegion>(0.15f, walk_up_regions[0], walk_up_regions[1], walk_up_regions[2], walk_up_regions[3], walk_up_regions[4]);
        walk_up.setPlayMode(Animation.PlayMode.LOOP);

        walk_down_texture = parent.assMan.manager.get(WorldAssetManager.playerRunningDown);
        walk_down_regions = TextureRegion.split(walk_down_texture, 32, 32)[0];
        walk_down = new Animation<TextureRegion>(0.15f, walk_down_regions[0], walk_down_regions[1], walk_down_regions[2], walk_down_regions[3], walk_down_regions[4]);
        walk_down.setPlayMode(Animation.PlayMode.LOOP);

        walk_left_texture = parent.assMan.manager.get(WorldAssetManager.playerRunningLeft);
        walk_left_regions = TextureRegion.split(walk_left_texture, 32, 32)[0];
        walk_left = new Animation<TextureRegion>(0.15f, walk_left_regions[0], walk_left_regions[1], walk_left_regions[2], walk_left_regions[3], walk_left_regions[4]);
        walk_left.setPlayMode(Animation.PlayMode.LOOP);

        walk_right_texture = parent.assMan.manager.get(WorldAssetManager.playerRunningRight);
        walk_right_regions = TextureRegion.split(walk_right_texture, 32, 32)[0];
        walk_right = new Animation<TextureRegion>(0.15f, walk_right_regions[0], walk_right_regions[1], walk_right_regions[2], walk_right_regions[3], walk_right_regions[4]);
        walk_right.setPlayMode(Animation.PlayMode.LOOP);


        // figure out the width and height of the player for collision
        // detection and rendering by converting a player frames pixel
        // size into world units (1 unit == 16 pixels)
        playerWidth = 1 / SCALE * walk_up_regions[0].getRegionWidth();
        playerHeight = 1 / SCALE * walk_up_regions[0].getRegionHeight();

        // create an orthographic camera, shows us 25x20 units of the world
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 20, 15);
        camera.update();
    }


    public void render() {
        // get the delta time
        float deltaTime = Gdx.graphics.getDeltaTime();

        // clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update the koala (process input, collision detection, position update)
        updatePlayer(deltaTime);

        // let the camera follow the koala, x-axis only
        camera.position.x = playerX;
        camera.position.y = playerY;
        camera.update();

        // set the TiledMapRenderer view based on what the
        // camera sees, and render the map
        renderer.setView(camera);
        renderer.render();

        // render the koala
        renderPlayer();
    }

    protected void updatePlayer (float deltaTime) {

        if (deltaTime == 0) return;

        if (deltaTime > 0.1f)
            deltaTime = 0.1f;

        stateTime += deltaTime;

        if (controller.up) {
            playerY += 0.1f;
            facing = Turned.UP;
            runningUp = true;
            moving = true;
        }
        if (controller.down) {
            playerY -= 0.1f;
            facing = Turned.DOWN;
            runningDown = true;
            moving = true;
        }

        if (controller.left) {
            playerX -= 0.1f;
            facing = Turned.LEFT;
            runningLeft = true;
            moving = true;
        }

        if (controller.right) {
            playerX += 0.1f;
            facing = Turned.RIGHT;
            runningRight = true;
            moving = true;
        }

        if (!controller.up){
            runningUp = false;
            moving = false;
        }

        if (!controller.down) {
            runningDown = false;
            moving = false;
        }

        if (!controller.left) {
            runningLeft = false;
            moving = false;
        }

        if (!controller.right){
            runningRight = false;
            moving = false;
        }

    }

    protected void renderPlayer() {

        TextureRegion frame = walk_down.getKeyFrames()[0];

        if (runningUp) {
            frame = walk_up.getKeyFrame(stateTime);
        } else if (runningLeft) {
            frame = walk_left.getKeyFrame(stateTime);
        } else if (runningRight) {
            frame = walk_right.getKeyFrame(stateTime);
        } else if (runningDown) {
            frame = walk_down.getKeyFrame(stateTime);
        } else if (facing == Turned.UP && !moving){
            frame = walk_up.getKeyFrames()[0];
        } else if (facing == Turned.DOWN && !moving) {
            frame = walk_down.getKeyFrames()[0];
        } else if (facing == Turned.RIGHT && !moving) {
            frame = walk_right.getKeyFrames()[0];
        } else if (facing == Turned.LEFT && !moving) {
            frame = walk_left.getKeyFrames()[0];
        }

        // draw the player
        Batch batch = renderer.getBatch();
        batch.begin();
        batch.draw(frame, playerX, playerY, playerWidth, playerHeight);
        batch.end();
    }




}

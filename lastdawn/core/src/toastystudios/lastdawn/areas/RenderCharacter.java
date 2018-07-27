package toastystudios.lastdawn.areas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.utils.BodyFactory;
import toastystudios.lastdawn.utils.Constants;

public class RenderCharacter {

    protected enum Turned {
        UP, DOWN, LEFT, RIGHT;
    }

    protected Turned facing;

    protected boolean moving;

    protected World world;
    protected Body body;
    protected GameLoader parent;
    protected TextureMapObject tmo;

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

    String[] animations;

    public RenderCharacter(GameLoader parent, World world, int x, int y, int width, int height, String[] path, BodyDef.BodyType type) {
        this.world = world;
        this.body = BodyFactory.getBodyFactory(world).createBox(x, y, width, height, type);
        this.parent = parent;
        this.animations = path;
        loadWalkAnimations();
        this.tmo = new TextureMapObject(walk_up_regions[0]);
    }

    private void loadWalkAnimations() {
        //walk animations
        walk_up_texture = parent.assMan.manager.get(animations[0]);
        walk_up_regions = TextureRegion.split(walk_up_texture, 32, 32)[0];
        walk_up = new Animation<>(0.15f, walk_up_regions[0], walk_up_regions[1], walk_up_regions[2], walk_up_regions[3], walk_up_regions[4]);
        walk_up.setPlayMode(Animation.PlayMode.LOOP);

        walk_down_texture = parent.assMan.manager.get(animations[1]);
        walk_down_regions = TextureRegion.split(walk_down_texture, 32, 32)[0];
        walk_down = new Animation<>(0.15f, walk_down_regions[0], walk_down_regions[1], walk_down_regions[2], walk_down_regions[3], walk_down_regions[4]);
        walk_down.setPlayMode(Animation.PlayMode.LOOP);

        walk_left_texture = parent.assMan.manager.get(animations[2]);
        walk_left_regions = TextureRegion.split(walk_left_texture, 32, 32)[0];
        walk_left = new Animation<>(0.15f, walk_left_regions[0], walk_left_regions[1], walk_left_regions[2], walk_left_regions[3], walk_left_regions[4]);
        walk_left.setPlayMode(Animation.PlayMode.LOOP);

        walk_right_texture = parent.assMan.manager.get(animations[3]);
        walk_right_regions = TextureRegion.split(walk_right_texture, 32, 32)[0];
        walk_right = new Animation<>(0.15f, walk_right_regions[0], walk_right_regions[1], walk_right_regions[2], walk_right_regions[3], walk_right_regions[4]);
        walk_right.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void render(float stateTime) {

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
        tmo.setX(body.getPosition().x * Constants.PPM - (frame.getRegionWidth() / 2));
        tmo.setY(body.getPosition().y * Constants.PPM - (frame.getRegionHeight() / 3));
    }

    public void setTurnedUp() {
        this.facing = Turned.UP;
    }

    public void setTurnedDown() {
        this.facing = Turned.DOWN;
    }

    public void setTurnedLeft() {
        this.facing = Turned.LEFT;
    }

    public void setTurnedRight() {
        this.facing = Turned.RIGHT;
    }

    public TextureMapObject getTMO() {
        return this.tmo;
    }

    public void setMoving() {
        this.moving = true;
    }

    public void setStanding() {
        this.moving = false;
    }

    public void applyLinearForce(int horizontalForce, int verticalForce) {
        body.setLinearVelocity( verticalForce* 5, body.getLinearVelocity().y);
        body.setLinearVelocity(horizontalForce * 5, body.getLinearVelocity().x);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }
}

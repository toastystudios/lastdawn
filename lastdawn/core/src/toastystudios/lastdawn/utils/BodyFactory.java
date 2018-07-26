package toastystudios.lastdawn.utils;

import com.badlogic.gdx.physics.box2d.*;

public class BodyFactory {

    private World world;
    private static BodyFactory thisInstance;

    private BodyFactory(World world){
        this.world = world;
    }

    public static BodyFactory getBodyFactory(World world){
        if( thisInstance == null){
            thisInstance = new BodyFactory(world);
        }
        return thisInstance;
    }


    public Body createBox(int x, int y, int width, int height, BodyDef.BodyType type) {
        Body playerBody;

        BodyDef def = new BodyDef();

        def.type = type;

        def.position.set(x / Constants.PPM, y / Constants.PPM);
        def.fixedRotation = true;
        playerBody = world.createBody(def);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / Constants.PPM, height / 2 / Constants.PPM);

        playerBody.createFixture(shape, 1.0f);
        shape.dispose();

        return playerBody;
    }
}

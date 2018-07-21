package toastystudios.lastdawn.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import toastystudios.lastdawn.Controller.KeyboardController;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.utils.BodyFactory;
import toastystudios.lastdawn.utils.WorldContactListener;

public class WorldController {

    private static final int HOVER_SOUND = 0;
    private static final int PING_SOUND = 1;
    private Sound ping;
    private Sound hover;

    public World world;
    private Body bodyd;
    private Body bodys;
    private Body bodyk;
    public Body player;
    private KeyboardController controller;
    private OrthographicCamera camera;

    private WorldAssetManager assMan;

    public boolean isSwimming = false;

    public WorldController(KeyboardController cont, OrthographicCamera cam, WorldAssetManager assetManager){
        controller = cont;
        camera = cam;
        world = new World(new Vector2(0,-10f), true);
        world.setContactListener(new WorldContactListener());
        this.assMan = assetManager;
        BodyFactory bodyFactory = BodyFactory.getBodyFactory(world);

        player = bodyFactory.makeBoxPolyBody(1,1,2,4,BodyFactory.RUBBER,BodyDef.BodyType.DynamicBody,false);
//        Body water = bodyFactory.makeBoxPolyBody(1,-8,40,10,BodyFactory.RUBBER,BodyDef.BodyType.StaticBody,false);
//        water.setUserData("SEA");
//        bodyFactory.makeAllFixturesSensors(water);
        createFloor();

        // tells our asset manger that we want to load the images set in loadImages method
        assMan.queueAddSounds();
        // tells the asset manager to load the images and wait until finsihed loading.
        assMan.manager.finishLoading();
        // loads the 2 sounds we use
        ping = assMan.manager.get(WorldAssetManager.ping, Sound.class);
        hover = assMan.manager.get(WorldAssetManager.hover, Sound.class);

    }

    private void createFloor() {

        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -10);

        // add it to the world
        bodyd = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);

        // create the physical object in our body)
        // without this our body would just be data in the world
        bodyd.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

    private void createObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);


        // add it to the world
        bodys = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        bodys.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

    private void createMovingObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,-12);


        // add it to the world
        bodyk = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        bodyk.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();

        bodyk.setLinearVelocity(0, 0.75f);
    }


    public boolean pointIntersectsBody(Body body, Vector2 mouseLocation){
        Vector3 mousePos = new Vector3(mouseLocation,0); //convert mouseLocation to 3D position
        camera.unproject(mousePos); // convert from screen potition to world position
        if(body.getFixtureList().first().testPoint(mousePos.x, mousePos.y)){
            return true;
        }
        return false;
    }

    public void playSound(int sound){
        switch(sound){
            case HOVER_SOUND:
                hover.play();
                break;
            case PING_SOUND:
                ping.play();
                break;
        }
    }

    // our game logic here
    public void logicStep(float delta){

        if(controller.left){
            player.applyForceToCenter(-10, 0,true);
        }else if(controller.right){
            player.applyForceToCenter(10, 0,true);
        }else if(controller.up){
            player.applyForceToCenter(0, 100,true);
        }else if(controller.down){
            player.applyForceToCenter(0, -10,true);
        }

        if(isSwimming){
            player.applyForceToCenter(0, 100, true);
        }

        // check if mouse1 is down (player click) then if true check if point intersects
        if(controller.isMouse1Down && pointIntersectsBody(player,controller.mouseLocation)){
            System.out.println("Player was clicked");
        }

        world.step(delta , 3, 3);
    }
}
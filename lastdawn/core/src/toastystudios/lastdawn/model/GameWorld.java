package toastystudios.lastdawn.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static toastystudios.lastdawn.model.BodyFactory.makeFixture;

public class GameWorld {
    private final float DEGTORAD = 0.0174533f;
    public World world;
    private Body dynamicBody; //e.g affected by gravity - player character
    private Body staticBody; //static objects, they don't move - walls, floor..
    private Body kinematicBody; //moving platforms, things with wheels..

    public GameWorld() {
        world = new World(new Vector2(0, -10f), true);
        world.setContactListener(new GameContactListener(this));
        createFloor();
        createObject();
        createMovingObject();// get our body factory singleton and store it in bodyFactory



        BodyFactory bodyFactory = BodyFactory.getInstance(world);

        // add a new rubber ball at position 1, 1
        bodyFactory.makeCirclePolyBody(1, 1, 2, BodyFactory.RUBBER, BodyDef.BodyType.DynamicBody,false);

        // add a new steel ball at position 4, 1
        bodyFactory.makeCirclePolyBody(4, 1, 2, BodyFactory.STEEL, BodyDef.BodyType.DynamicBody,false);

        // add a new stone at position -4,1
        bodyFactory.makeCirclePolyBody(-4, 1, 2, BodyFactory.STONE, BodyDef.BodyType.DynamicBody,false);

        bodyFactory.makeCirclePolyBody(1, 1, 2, BodyFactory.RUBBER, BodyDef.BodyType.DynamicBody);
    }

    public void logicStep(float delta) {
        world.step(delta, 3, 3);
    }

    private void createObject(){


        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);


        // add it to the world
        dynamicBody = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        dynamicBody.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

    private void createFloor() {
        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -10);
        // add it to the world
        staticBody = world.createBody(bodyDef);
        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);
        // create the physical object in our body)
        // without this our body would just be data in the world
        staticBody.createFixture(shape, 0.0f);
        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

    private void createMovingObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,-12);


        // add it to the world
        kinematicBody = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        kinematicBody.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();

        kinematicBody.setLinearVelocity(0, 0.75f);
    }


    public Body makeBoxPolyBody(float posx, float posy, float width, float height,int material, BodyDef.BodyType bodyType){
        return makeBoxPolyBody(posx, posy, width, height, material, bodyType, false);
    }

    public Body makeBoxPolyBody(float posx, float posy, float width, float height, int material, BodyDef.BodyType bodyType, boolean fixedRotation){
        // create a definition
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posx;
        boxBodyDef.position.y = posy;
        boxBodyDef.fixedRotation = fixedRotation;

        //create the body to attach said definition
        Body boxBody = world.createBody(boxBodyDef);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width/2, height/2);
        boxBody.createFixture(makeFixture(material,poly));
        poly.dispose();

        return boxBody;
    }

    public Body makePolygonShapeBody(Vector2[] vertices, float posx, float posy, int material, BodyDef.BodyType bodyType){
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posx;
        boxBodyDef.position.y = posy;
        Body boxBody = world.createBody(boxBodyDef);

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertices);
        boxBody.createFixture(makeFixture(material,polygon));
        polygon.dispose();

        return boxBody;
    }

    public void makeConeSensor(Body body, float size){

        FixtureDef fixtureDef = new FixtureDef();
        //fixtureDef.isSensor = true; // will add in future

        PolygonShape polygon = new PolygonShape();

        float radius = size;
        Vector2[] vertices = new Vector2[5];
        vertices[0] = new Vector2(0,0);
        for (int i = 2; i < 6; i++) {
            float angle = (float) (i  / 6.0 * 145 * DEGTORAD); // convert degrees to radians
            vertices[i-1] = new Vector2( radius * ((float)Math.cos(angle)), radius * ((float)Math.sin(angle)));
        }
        polygon.set(vertices);
        fixtureDef.shape = polygon;
        body.createFixture(fixtureDef);
        polygon.dispose();
    }

}

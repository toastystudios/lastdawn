package toastystudios.lastdawn.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import toastystudios.lastdawn.model.WorldController;

public class WorldContactListener implements ContactListener {

    private WorldController parent;

    public WorldContactListener(WorldController world){
        this.parent = world;
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        System.out.println(fa.getBody().getType()+" has hit "+ fb.getBody().getType());

        if(fa.getBody().getUserData() == "SEA"){
            parent.isSwimming = true;
            return;
        }else if(fb.getBody().getUserData() == "SEA"){
            parent.isSwimming = true;
            return;
        }else{
            //do nothing
        }

        if(fa.getBody().getType() == BodyDef.BodyType.StaticBody){
            //this.shootUpInAir(fa, fb);
        }else if(fb.getBody().getType() == BodyDef.BodyType.StaticBody){
            //this.shootUpInAir(fb, fa);
        }else{
            // neither a nor b are static so do nothing
        }
    }

    private void shootUpInAir(Fixture staticFixture, Fixture otherFixture){
        System.out.println("Adding Force");
        otherFixture.getBody().applyForceToCenter(new Vector2(-1000,-1000), true);
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("No Contact");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        if(fa.getBody().getUserData() == "SEA"){
            parent.isSwimming = false;
            return;
        }else if(fb.getBody().getUserData() == "SEA"){
            parent.isSwimming = false;
            return;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

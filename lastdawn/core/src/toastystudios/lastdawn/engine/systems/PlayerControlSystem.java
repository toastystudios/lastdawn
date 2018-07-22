package toastystudios.lastdawn.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import toastystudios.lastdawn.Controller.KeyboardController;
import toastystudios.lastdawn.engine.components.B2dBodyComponent;
import toastystudios.lastdawn.engine.components.PlayerComponent;
import toastystudios.lastdawn.engine.components.StateComponent;

public class PlayerControlSystem extends IteratingSystem{

	ComponentMapper<PlayerComponent> pm;
	ComponentMapper<B2dBodyComponent> bodm;
	ComponentMapper<StateComponent> sm;
	KeyboardController controller;
	
	
	@SuppressWarnings("unchecked")
	public PlayerControlSystem(KeyboardController keyCon) {
		super(Family.all(PlayerComponent.class).get());
		controller = keyCon;
		pm = ComponentMapper.getFor(PlayerComponent.class);
		bodm = ComponentMapper.getFor(B2dBodyComponent.class);
		sm = ComponentMapper.getFor(StateComponent.class);
	}
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		B2dBodyComponent b2body = bodm.get(entity);
		StateComponent state = sm.get(entity);
		
		if(b2body.body.getLinearVelocity().y > 0){
			state.set(StateComponent.STATE_FALLING);
		}
		
		if(b2body.body.getLinearVelocity().y == 0){
			if(state.get() == StateComponent.STATE_FALLING){
				state.set(StateComponent.STATE_NORMAL);
			}
			if(b2body.body.getLinearVelocity().x != 0){
				state.set(StateComponent.STATE_MOVING);
			}
		}
		
		if(controller.left && !controller.right){
			b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, -13f, 0.6f),b2body.body.getLinearVelocity().y);
		}
		if(controller.right && !controller.left){
			b2body.body.setLinearVelocity(MathUtils.lerp(b2body.body.getLinearVelocity().x, 13f, 0.6f),b2body.body.getLinearVelocity().y);
		}

		if(controller.down && !controller.up){
			b2body.body.applyLinearImpulse(0, -20f, b2body.body.getWorldCenter().x,b2body.body.getWorldCenter().y, true);
		}
		
		if(controller.up && !controller.down &&
				(state.get() == StateComponent.STATE_NORMAL || state.get() == StateComponent.STATE_MOVING)){
			//b2body.body.applyForceToCenter(0, 3000,true);
			b2body.body.applyLinearImpulse(0, 230f, b2body.body.getWorldCenter().x,b2body.body.getWorldCenter().y, true);
			state.set(StateComponent.STATE_JUMPING);
		}
	}
}

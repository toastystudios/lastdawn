package toastystudios.lastdawn.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import toastystudios.lastdawn.engine.components.AnimationComponent;
import toastystudios.lastdawn.engine.components.StateComponent;
import toastystudios.lastdawn.engine.components.TextureComponent;

public class AnimationSystem extends IteratingSystem {

    ComponentMapper<TextureComponent> tm;
    ComponentMapper<AnimationComponent> am;
    ComponentMapper<StateComponent> sm;

    @SuppressWarnings("unchecked")
	public AnimationSystem(){
        super(Family.all(TextureComponent.class,
                AnimationComponent.class,
                StateComponent.class).get());

        tm = ComponentMapper.getFor(TextureComponent.class);
        am = ComponentMapper.getFor(AnimationComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        AnimationComponent ani = am.get(entity);
        StateComponent state = sm.get(entity);

        if(ani.animations.containsKey(state.get())){
            TextureComponent tex = tm.get(entity);
            tex.region = ani.animations.get(state.get()).getKeyFrame(state.time, state.isLooping);
        }

        state.time += deltaTime;
    }
}
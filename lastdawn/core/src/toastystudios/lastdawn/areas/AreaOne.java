package toastystudios.lastdawn.areas;

import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.engine.OrthoTiledRenderWithSprites;
import toastystudios.lastdawn.utils.BodyFactory;
import toastystudios.lastdawn.utils.Constants;
import toastystudios.lastdawn.utils.TiledObjectUtil;

public class AreaOne extends Area {

    protected RenderCharacter npcTest;

    public AreaOne(GameLoader gameLoader) {
        super(gameLoader);
    }

    @Override
    public void create() {
        super.create();

        String[] animationsPath = {WorldAssetManager.playerRunningUp, WorldAssetManager.playerRunningDown, WorldAssetManager.playerRunningLeft, WorldAssetManager.playerRunningRight};
        this.npcTest = new RenderCharacter(parent, world, 30, 30, 16, 16, animationsPath, BodyDef.BodyType.KinematicBody);

        map = new TmxMapLoader().load("assets/level/level1.tmx");
        tmr = new OrthoTiledRenderWithSprites(map);
        TiledObjectUtil.parseTiledObjectLayer(world, map.getLayers().get("collision").getObjects());

        objectLayer = map.getLayers().get("characters");
        objectLayer.getObjects().add(player.getTMO());
        objectLayer.getObjects().add(npcTest.getTMO());
    }

    @Override
    public void render() {
        super.render();

        player.render(stateTime);
        npcTest.render(stateTime);

        tmr.setView(camera);
        tmr.render();
        b2dr.render(world, camera.combined.scl(Constants.PPM));
    }


    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

}

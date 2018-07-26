package toastystudios.lastdawn.areas;

import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import toastystudios.lastdawn.engine.GameLoader;
import toastystudios.lastdawn.engine.OrthoTiledRenderWithSprites;
import toastystudios.lastdawn.utils.TiledObjectUtil;

public class AreaOne extends Area {

    public AreaOne(GameLoader gameLoader) {
        super(gameLoader);
    }

    @Override
    public void create() {
        super.create();

        map = new TmxMapLoader().load("assets/level/level1.tmx");
        tmr = new OrthoTiledRenderWithSprites(map);
        TiledObjectUtil.parseTiledObjectLayer(world, map.getLayers().get("collision").getObjects());

        objectLayer = map.getLayers().get("npcs");
        tmo = new TextureMapObject(walk_up_regions[0]);
        objectLayer.getObjects().add(tmo);
    }

    @Override
    public void render() {
        super.render();
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

package toastystudios.lastdawn.stages;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.engine.GameLoader;

public class StageOne extends Stage{

    protected TiledMap map;

    public StageOne(GameLoader parent) {
        super(parent);
    }

    @Override
    public void render() {
    super.render();
    }

    @Override
    public void show() {
        super.show();

        // load the map, set the unit scale to 1/16 (1 unit == 16 pixels)
        map = parent.assMan.manager.get(WorldAssetManager.level_1);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / SCALE);

        //se quisermos adicionar mais coisas...
    }
}

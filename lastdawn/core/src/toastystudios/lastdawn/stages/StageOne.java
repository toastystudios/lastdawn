package toastystudios.lastdawn.stages;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import toastystudios.lastdawn.Controller.WorldAssetManager;
import toastystudios.lastdawn.engine.GameLoader;

public class StageOne extends Stage {

    protected TiledMap map;
    protected Array<Rectangle> tiles = new Array<Rectangle>();
    protected Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };

    public StageOne(GameLoader parent) {
        super(parent);
    }

    @Override
    public void render() {
        super.render();
        checkCollision();
    }

    @Override
    public void show() {
        super.show();

        // load the map, set the unit scale to 1/16 (1 unit == 16 pixels)
        map = parent.assMan.manager.get(WorldAssetManager.level_1);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / SCALE);

        //se quisermos adicionar mais coisas...
    }

    private void checkCollision() {
        Rectangle player = rectPool.obtain();
        player.set(position.x, position.y, playerWidth, playerHeight/5);
        int startX, startY, endX, endY;


        //Check X collision
        if (velocity.x > 0) startX = endX = (int) (position.x + playerWidth + velocity.x);
        else startX = endX = (int) (position.x + velocity.x);


        startY = (int) position.y;
        endY = (int) (position.y + playerHeight);

        getTiles(startX, startY, endX, endY, tiles, "CollisionLayer");
        player.x += velocity.x;

        for (Rectangle tile : tiles) {
            if (player.overlaps(tile)) {
                velocity.x = 0;
                break;
            }
        }

        player.x = position.x;

        //Check Y collision
        if (velocity.y > 0) startY = endY = (int) (position.y + playerHeight + velocity.y);
        else startY = endY = (int) (position.y + velocity.y);

        startX = (int) position.x;
        endX = (int) (position.x + playerWidth);

        getTiles(startX, startY, endX, endY, tiles, "CollisionLayer");
        player.x += velocity.x;

        for (Rectangle tile : tiles) {
            if (player.overlaps(tile)) {
                velocity.y = 0;
                break;
            }
        }

        player.y = position.y;

        rectPool.free(player);

        position.add(velocity);
        velocity.scl(1 / deltaTime);
        velocity.x *= DAMPING;
        velocity.y *= DAMPING;
    }

    private void getTiles(int startX, int startY, int endX, int endY, Array<Rectangle> tiles, String layerName) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(layerName);
        rectPool.freeAll(tiles);
        tiles.clear();
        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = rectPool.obtain();
                    rect.set(x, y, 1, 1);
                    tiles.add(rect);
                }
            }
        }
    }
}

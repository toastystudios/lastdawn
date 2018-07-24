package toastystudios.lastdawn.stages;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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

    protected float oldX;
    protected float oldY;

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

        // perform collision detection & response, on each axis, separately
        // if the koala is moving right, check the tiles to the right of it's
        // right bounding box edge, otherwise check the ones to the left
        Rectangle playerRect = rectPool.obtain();
        playerRect.set(playerX, playerY, playerWidth, playerHeight);
        int auxX = (int) playerX, auxY = (int) playerY;
        int startX, startY, endX, endY;

        if (runningLeft) {
            startX = auxX;
            endX = auxX--;
        } else if (runningRight) {
            startX = auxX;
            endX = auxX++;
        } else {
            startX = auxX;
            endX = auxX;
        }

        if (runningUp) {
            startY = auxY;
            endY = auxY++;
        } else if (runningDown) {
            startY = auxY;
            endY = auxY--;
        } else {
            startY = auxY;
            endY = auxY;
        }

        getTiles(startX, startY, endX, endY, tiles, "CollisionLayer");
        for (Rectangle tile : tiles) {

            int tileXLeft = (int) tile.x;
            int tileXRight = (int) tile.x;
            int tileYUp = (int) tile.y;
            int tileYDown = (int) tile.y;


            tileXLeft--;
            if (tileXLeft == auxX) {
            System.out.println(tileXLeft + " - " + auxX);
                System.out.println("colliding left");
                collidingLeft = true;
            }
            tileXRight++;
            if (tileXRight == auxX) {
                System.out.println(tileXRight + " - " + auxX);
                System.out.println("colliding right");
                collidingRight = true;
            }
            tileYDown--;
            if (tileYDown == auxY) {
                System.out.println(tileYDown + " - " + auxY);
                System.out.println("colliding down");
                collidingDown = true;
            }
            tileYUp++;
            if (tileYUp == auxY) {
                System.out.println(tileYUp + " - " + auxY);
                System.out.println("colliding up");
                collidingUp = true;
            }

            break;
        }
        rectPool.free(playerRect);

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

package toastystudios.lastdawn.engine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import toastystudios.lastdawn.engine.components.TextureComponent;
import toastystudios.lastdawn.engine.components.TransformComponent;
import toastystudios.lastdawn.engine.components.TypeComponent;

import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {

    private static final int VIRTUAL_WIDTH = 1920;
    private static final int VIRTUAL_HEIGHT =  1080;

    static final float PPM = 32.0f;
    static final float FRUSTUM_WIDTH = VIRTUAL_WIDTH / PPM;//37.5f;
    static final float FRUSTUM_HEIGHT = VIRTUAL_HEIGHT / PPM;//.0f;

    public static final float PIXELS_TO_METRES = 1.0f / PPM;

    private static Vector2 meterDimensions = new Vector2();
    private static Vector2 pixelDimensions = new Vector2();

    public static Vector2 getScreenSizeInMeters() {
        meterDimensions.set(VIRTUAL_WIDTH * PIXELS_TO_METRES,
                VIRTUAL_HEIGHT * PIXELS_TO_METRES);
        return meterDimensions;
    }

    public static Vector2 getScreenSizeInPixesl() {
        pixelDimensions.set(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        return pixelDimensions;
    }

    public static float PixelsToMeters(float pixelValue) {
        return pixelValue * PIXELS_TO_METRES;
    }

    private SpriteBatch batch;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private OrthographicCamera cam;

    private ComponentMapper<TextureComponent> textureM;
    private ComponentMapper<TransformComponent> transformM;

    @SuppressWarnings("unchecked")
    public RenderingSystem(SpriteBatch batch) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get(), new ZComparator());

        textureM = ComponentMapper.getFor(TextureComponent.class);
        transformM = ComponentMapper.getFor(TransformComponent.class);

        renderQueue = new Array<Entity>();
        comparator = new ZComparator();
        this.batch = batch;

        cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        cam.position.set(FRUSTUM_WIDTH / 2f, FRUSTUM_HEIGHT / 2f, 0);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator);


        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();

        for (Entity entity : renderQueue) {
            TextureComponent tex = textureM.get(entity);
            TransformComponent t = transformM.get(entity);

            updateCamera(entity);

            if (tex.region == null || t.isHidden) {
                continue;
            }


            float width = tex.region.getRegionWidth();
            float height = tex.region.getRegionHeight();

            float originX = width / 2f;
            float originY = height / 2f;
                batch.draw(tex.region,
                        t.position.x - originX, t.position.y - originY,
                        originX, originY,
                        width, height,
                        PixelsToMeters(t.scale.x), PixelsToMeters(t.scale.y),
                        t.rotation);
        }

        batch.end();
        renderQueue.clear();
    }

    private void updateCamera(Entity entity) {
        if (entity.getComponent(TypeComponent.class).type == TypeComponent.PLAYER) {

            float x =  transformM.get(entity).position.x;
            float y =  transformM.get(entity).position.y;

            System.out.println("X: " + x +  " Y: " + y);

            if (x >= 30) {
                cam.position.x = x;
            }

            if (y >= 17) {
                cam.position.y = y;
            }
        }
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    public OrthographicCamera getCamera() {
        return cam;
    }
}
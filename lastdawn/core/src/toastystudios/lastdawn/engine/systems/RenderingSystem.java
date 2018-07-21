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

import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {

    private static final float PPM = 32.0f; //pixel per meter - amount of pixels each meter of objects contains

    // this gets the height and width of our camera frustrum based off the width and height of the screen and our pixel per meter ratio
    static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth()/PPM;
    static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight()/PPM;

    public static final float PIXELS_TO_METRES = 1.0f / PPM; // get the ratio for converting pixels to metres

    // static method to get screen width in metres
    private static Vector2 meterDimensions = new Vector2();
    private static Vector2 pixelDimensions = new Vector2();

    public static Vector2 getScreenSizeInMeters(){
        meterDimensions.set(Gdx.graphics.getWidth()*PIXELS_TO_METRES, Gdx.graphics.getHeight()*PIXELS_TO_METRES);
        return meterDimensions;
    }

    // static method to get screen size in pixels
    public static Vector2 getScreenSizeInPixesl(){
        pixelDimensions.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return pixelDimensions;
    }

    private SpriteBatch batch; // a reference to our spritebatch
    private Array<Entity> renderQueue; // an array used to allow sorting of images allowing us to draw images on top of each other
    private Comparator<Entity> comparator; // a comparator to sort images based on the z position of the transfromComponent
    private OrthographicCamera cam; // a reference to our camera

    // components mappers to get components from entities
    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<TransformComponent> transformMapper;

    public RenderingSystem(SpriteBatch batch) {

        // gets all entities with a TransofmComponent and TextureComponent
        super(Family.all(TransformComponent.class, TextureComponent.class).get(), new ZComparator());

        //creates out componentMappers
        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);

// create the array for sorting entities
        renderQueue = new Array<Entity>();

        this.batch = batch;  // set our batch to the one supplied in constructor

        // set up the camera to match our screen size
        cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        cam.position.set(FRUSTUM_WIDTH / 2f, FRUSTUM_HEIGHT / 2f, 0);

    }

    // convenience method to convert pixels to meters
    public static float PixelsToMeters(float pixelValue){
        return pixelValue * PIXELS_TO_METRES;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator); //sort the render queue based on z index

        // update camera and sprite batch
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();

        // loop through each entity in our render queue
        for (Entity entity : renderQueue) {
            TextureComponent tex = textureMapper.get(entity);
            TransformComponent trans = transformMapper.get(entity);

            if (tex.region == null || trans.isHidden) {
                continue;
            }

            //get object size
            float width = tex.region.getRegionWidth();
            float height = tex.region.getRegionHeight();

            //get object center
            float originX = width/2f;
            float originY = height/2f;

            batch.draw(tex.region,
                    trans.position.x - originX, trans.position.y - originY,
                    originX, originY, width, height,
                    PixelsToMeters(trans.scale.x), PixelsToMeters(trans.scale.y),
                    trans.rotation);

        }

        batch.end();
        renderQueue.clear();
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    // convenience method to get camera
    public OrthographicCamera getCamera() {
        return cam;
    }
}

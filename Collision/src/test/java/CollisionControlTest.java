import dk.sdu.mmmi.cbse.collision.CollisionControl;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CollisionControlTest {

    private CollisionControl collisionControl;
    private GameData gameData;
    private World world;
    private Entity entity1;
    private Entity entity2;

    @BeforeEach
    void setUp() {
        collisionControl = new CollisionControl();
        gameData = new GameData();
        world = new World();


        entity1 = new Entity(2);
        entity2 = new Entity(2);

        //set coordinates for the entities
        entity1.setPolygonCoordinates(12, -1, 8, -1, 8, -3, 6, -3, 6, -5, -2,
                -5, -2, -7, 0, -7, 0, -9, -10, -9, -10, -5, -8, -5, -8, -3, -6, -3,
                -6, -1, -10, -1, -10, 1, -6, 1, -6, 3, -8, 3, -8, 5, -10, 5, -10, 9,
                0, 9, 0, 7, -2, 7, -2, 5, 2, 5, 2, 1, 4, 1, 4, -1, 2, -1, 2, -3, 4,
                -3, 4, -1, 6, -1, 6, 1, 4, 1, 4, 3, 2, 3, 2, 5, 6, 5, 6, 3, 8, 3, 8, 1, 12, 1);

        entity2.setPolygonCoordinates(12, -1, 8, -1, 8, -3, 6, -3, 6, -5, -2,
                -5, -2, -7, 0, -7, 0, -9, -10, -9, -10, -5, -8, -5, -8, -3, -6, -3,
                -6, -1, -10, -1, -10, 1, -6, 1, -6, 3, -8, 3, -8, 5, -10, 5, -10, 9,
                0, 9, 0, 7, -2, 7, -2, 5, 2, 5, 2, 1, 4, 1, 4, -1, 2, -1, 2, -3, 4,
                -3, 4, -1, 6, -1, 6, 1, 4, 1, 4, 3, 2, 3, 2, 5, 6, 5, 6, 3, 8, 3, 8, 1, 12, 1);


        world.addEntity(entity1);
        world.addEntity(entity2);
    }

    @AfterEach
    void tearDown() {
        collisionControl = null;
        gameData = null;
        world = null;
        entity1 = null;
        entity2 = null;
    }

    //test entities loose life when they collide
    @Test
    void testCollision() {
        entity1.setX(0.0);
        entity1.setY(0.0);

        entity2.setX(0.0);
        entity2.setY(0.0);

        collisionControl.process(gameData, world);

        assertEquals(1, entity1.getLife());
        assertEquals(1, entity2.getLife());

    }

    //test entities dont loose life when they dont collide
    @Test
    void testNoCollision() {
        entity1.setX(1000.0);
        entity1.setY(1000.0);

        entity2.setX(0.0);
        entity2.setY(0.0);

        collisionControl.process(gameData, world);

        assertEquals(2, entity1.getLife());
        assertEquals(2, entity2.getLife());

    }

    //test entities get removed when they have no life
    @Test
    void testRemovalWhenNoLife() {
        entity1.setX(0.0);
        entity1.setY(0.0);

        entity2.setX(0.0);
        entity2.setY(0.0);

        collisionControl.process(gameData, world);

        assertEquals(1, entity1.getLife());
        assertEquals(1, entity2.getLife());

        entity1.setX(0.0);
        entity1.setY(0.0);

        entity2.setX(0.0);
        entity2.setY(0.0);

        collisionControl.process(gameData, world);

        assertEquals(0, entity1.getLife());
        assertEquals(0, entity2.getLife());
        assertFalse(entity1.getEntityState());
        assertFalse(entity2.getEntityState());

    }
}

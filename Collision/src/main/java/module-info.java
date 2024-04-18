import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collison {
    exports dk.sdu.mmmi.cbse.collision;
    uses dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
    requires Common;
    requires CommonBullet;
    requires CommonAsteroid;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collision.CollisionControl;
}
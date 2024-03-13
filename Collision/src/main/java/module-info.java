import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collison {
    uses dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
    requires Common;
    requires CommonBullet;
    requires Asteroids;
    requires CommonAsteroid;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collision.CollisionControl;
}
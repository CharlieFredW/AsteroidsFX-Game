import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collison {
    requires Common;
    requires CommonBullet;
    requires Asteroids;
    requires CommonAsteroid;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collision.CollisionControl;
}
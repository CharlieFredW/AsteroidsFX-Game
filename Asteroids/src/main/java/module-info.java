import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroids {
    exports dk.sdu.mmmi.cbse.asteroids;
    requires Common;
    requires CommonAsteroid;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroids.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroids.AsteroidControlSystem;
    provides AsteroidSPI with dk.sdu.mmmi.cbse.asteroids.AsteroidPlugin;
}
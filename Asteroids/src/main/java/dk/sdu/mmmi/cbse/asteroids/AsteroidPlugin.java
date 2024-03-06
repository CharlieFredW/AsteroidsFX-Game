package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    public AsteroidPlugin() {
    }

    Random r = new Random();

    @Override
    public void start(GameData gameData, World world) {

        int randomNum = r.nextInt(5, 15);

        for (int i = 0; i < randomNum; i++) {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }

    }


    private Entity createAsteroid(GameData gameData) {

        Asteroid asteroid = new Asteroid(r.nextInt(2,4), 1);

        asteroid.setPolygonCoordinates();
        asteroid.setX(r.nextDouble(gameData.getDisplayHeight()));
        asteroid.setY(r.nextDouble(gameData.getDisplayWidth()));
        asteroid.setRotation(r.nextDouble(361));
        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {



    }




}

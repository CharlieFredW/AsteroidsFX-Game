package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AsteroidPlugin implements IGamePluginService, AsteroidSPI {

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

    private double[] createAsteroidsCoordinates() {

        double randomSize = r.nextDouble(5,10);

        double c1 = Math.cos(Math.PI * 2 / 5);
        double c2 = Math.cos(Math.PI / 5);
        double s1 = Math.sin(Math.PI * 2 / 5);
        double s2 = Math.sin(Math.PI * 4 / 5);

        return new double[]{
                randomSize, 0.0,
                randomSize/3, -1 * randomSize/3,
                randomSize * c1, -1 * randomSize * s1,
                -1 * randomSize * c2, -1 * randomSize * s2,
                -1 * randomSize/4, randomSize/4,
                -1 * randomSize * c2, randomSize * s2,
                randomSize * c1, randomSize * s1
        };
    }

    private Entity createAsteroid(GameData gameData) {

        Asteroid asteroid = new Asteroid(r.nextInt(2,4), 1);

        asteroid.setPolygonCoordinates(Arrays.stream(createAsteroidsCoordinates()).map(i -> i * Math.pow(2, asteroid.getSize())).toArray());
        asteroid.setX(r.nextDouble(gameData.getDisplayHeight()));
        asteroid.setY(r.nextDouble(gameData.getDisplayWidth()));
        asteroid.setRotation(r.nextDouble(361));
        return asteroid;
    }

    @Override
    public void splitAsteroids(World world, Asteroid oldAsteroid) {
        int randomDistance = r.nextInt(-30, 30);
        int amountOfAsteroids = 2;
        if (oldAsteroid.getSize() > 1) {
            for (int i = 0; i < amountOfAsteroids; i++) {
                System.out.println("New asteroid" + i);
                Asteroid asteroid = new Asteroid(oldAsteroid.getSize() - 1, 1);
                asteroid.setPolygonCoordinates(Arrays.stream(createAsteroidsCoordinates()).map(g -> g * Math.pow(2, asteroid.getSize())).toArray());
                asteroid.setX(oldAsteroid.getX() + randomDistance);
                asteroid.setY(oldAsteroid.getY() + randomDistance);
                asteroid.setRotation(oldAsteroid.getRotation() + (i == 0 ? 45 : -45));
                world.addEntity(asteroid);
            }
        }
    }

    @Override
    public void stop(GameData gameData, World world) {


    }
}

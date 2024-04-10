package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {

    Random r = new Random();
    double speed = 0.2;

    @Override
    public void process(GameData gameData, World world) {

        double randomRot = r.nextDouble(361);

        for (Entity asteroid : world.getEntities(Asteroid.class)) {


            autoPresUp(asteroid);


            if (asteroid.getX() < 0) {
                asteroid.setX(1);
                asteroid.setRotation(randomRot);
            }

            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(gameData.getDisplayWidth()-1);
                asteroid.setRotation(randomRot);
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(1);
                asteroid.setRotation(randomRot);
            }

            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(gameData.getDisplayHeight()-1);
                asteroid.setRotation(randomRot);
            }




        }


    }

    private void autoPresUp(Entity asteroid) {
        double changeX = speed * Math.cos(Math.toRadians(asteroid.getRotation()));
        double changeY = speed * Math.sin(Math.toRadians(asteroid.getRotation()));
        asteroid.setX(asteroid.getX() + changeX);
        asteroid.setY(asteroid.getY() + changeY);
    }


}

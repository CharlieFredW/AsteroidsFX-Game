package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Arrays;
import java.util.Random;

public class Asteroid extends Entity{

    int size;

    Random r = new Random();

    public Asteroid(int size, int life) {
        super(life);
        this.size = size;
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

    public void setPolygonCoordinates() {
        setPolygonCoordinates(Arrays.stream(createAsteroidsCoordinates()).map(i -> i * Math.pow(2, size)).toArray());
    }

    public void splitAsteroid(World world) {
        int randomDistance = r.nextInt(-30, 30);
        int amountOfAsteroids = 2;
        if (size > 1) {
            for (int i = 0; i < amountOfAsteroids; i++) {
                System.out.println("New asteroid" + i);
                Asteroid asteroid = new Asteroid(size - 1, 1);
                asteroid.setPolygonCoordinates();
                asteroid.setX(this.getX() + randomDistance);
                asteroid.setY(this.getY() + randomDistance);
                asteroid.setRotation(this.getRotation() + (i == 0 ? 45 : -45));
                world.addEntity(asteroid);
            }
        }
    }

}
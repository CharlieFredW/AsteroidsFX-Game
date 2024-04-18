package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class CollisionControl implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        List<Entity> entities = new ArrayList<>(world.getEntities());

        for (int i = 0; i < entities.size(); i++) {
            Entity e1 = entities.get(i);
            for (int j = i + 1; j < entities.size(); j++) {
                Entity e2 = entities.get(j);
                if (intersects(e1, e2, world)) {

                    e1.removeLife();
                    System.out.println("entity1 has life left: " + e1.getLife());
                    e2.removeLife();
                    System.out.println("entity2 has life left: " + e2.getLife());

                    if (e2.getLife() == 0) {
                        e2.setEntityState(false);
                        System.out.println("Entity2 state: " + e2.getEntityState());
                    }
                    if (e1.getLife() == 0) {
                        e1.setEntityState(false);
                        System.out.println("Entity1 state: " + e1.getEntityState());
                    }
                }
            }
        }
    }

    private double getEntityRadius(double[] coordinates) {
        double maxCoordinate = Math.abs(Double.MIN_VALUE);

        for (double coordinate : coordinates) {
            maxCoordinate = Math.max(maxCoordinate, coordinate);
        }

        return maxCoordinate;
    }



    private boolean intersects(Entity entity, Entity entity2, World world) {

        double dx = entity.getX() - entity2.getX();
        double dy = entity.getY() - entity2.getY();

        double distance = Math.sqrt(dx*dx+dy*dy);

        if (distance < getEntityRadius(entity.getPolygonCoordinates()) + getEntityRadius(entity2.getPolygonCoordinates())) {

            if (entity instanceof Bullet && entity2 instanceof Bullet) {
                return false;
            }

            if (entity instanceof Bullet) {
                Entity shooter = ((Bullet) entity).getShooter();
                if (shooter != null && shooter.equals(entity2)) {
                    return false;
                }
            }

            if (entity2 instanceof Bullet) {
                Entity shooter = ((Bullet) entity2).getShooter();
                if (shooter != null && shooter.equals(entity)) {
                    return false;
                }
            }

            if (entity instanceof Bullet && entity2 instanceof Asteroid) {
                getAsteroidSPIs().forEach( asteroidSPI ->  {
                    Asteroid asteroid = (Asteroid) entity2;
                    asteroidSPI.splitAsteroids(world, asteroid);
                        });
                return true;
            }

            if (entity2 instanceof Bullet && entity instanceof Asteroid) {
                getAsteroidSPIs().forEach( asteroidSPI ->  {
                    Asteroid asteroid = (Asteroid) entity;
                    asteroidSPI.splitAsteroids(world, asteroid);
                });
                return true;
            }

            if ( entity instanceof Asteroid && entity2 instanceof Asteroid) {
                return false;
            }


            return true;
        }

        return false;

    }

    private Collection<? extends AsteroidSPI> getAsteroidSPIs() {
        return ServiceLoader.load(AsteroidSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}

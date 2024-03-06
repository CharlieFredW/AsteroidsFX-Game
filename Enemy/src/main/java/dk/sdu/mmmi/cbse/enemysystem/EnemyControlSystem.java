package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {

    Random r = new Random();

    int randomNumMove = r.nextInt((7-1) + 1) + 1;

    int randomNumShoot = r.nextInt((80-1) + 1) + 1;

    double randomRot = r.nextDouble(361);

    double speed = 0.5;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {


            autoPresUp(enemy);

            autoPresLeftRight(enemy, randomNumMove);

            autoShoot(world, gameData, enemy, randomNumShoot);


            if (enemy.getX() < 0) {
                enemy.setX(1);
                enemy.setRotation(randomRot);
            }

            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(gameData.getDisplayWidth()-1);
                enemy.setRotation(randomRot);
            }

            if (enemy.getY() < 0) {
                enemy.setY(1);
                enemy.setRotation(randomRot);
            }

            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(gameData.getDisplayHeight()-1);
                enemy.setRotation(randomRot);
            }




        }


    }

    private void autoPresUp(Entity enemy) {
        double changeX = speed * Math.cos(Math.toRadians(enemy.getRotation()));
        double changeY = speed * Math.sin(Math.toRadians(enemy.getRotation()));
        enemy.setX(enemy.getX() + changeX);
        enemy.setY(enemy.getY() + changeY);
    }

    private void autoPresLeftRight(Entity enemy, int leftRight) {

        if (leftRight == 1) {
            enemy.setRotation(enemy.getRotation() - 4);
        }

        if (leftRight == 2) {
            enemy.setRotation(enemy.getRotation() + 4);
        }

    }

    public void autoShoot(World world, GameData gameData, Entity enemy, int shoot) {

        if (shoot == 3) {
            getBulletSPIs().forEach(bulletSPI -> {
                Entity bullet = bulletSPI.createBullet(enemy, gameData);
                world.addEntity(bullet);
            });
        }

    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }





}

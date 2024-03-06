package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    private int bulletSpeed = 5;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = bulletSpeed * Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = bulletSpeed *Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX);
            bullet.setY(bullet.getY() + changeY);

            if (bullet.getX() < 0) {
                bullet.setBulletState(false);

            }

            if (bullet.getX() > gameData.getDisplayWidth()) {
                bullet.setBulletState(false);
            }

            if (bullet.getY() < 0) {
                bullet.setBulletState(false);
            }

            if (bullet.getY() > gameData.getDisplayHeight()) {
                bullet.setBulletState(false);
            }
        }

    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Bullet bullet = new Bullet(1);
        setShape(bullet);
        bullet.setBulletState(true);
        bullet.setX(shooter.getX());
        bullet.setY(shooter.getY());
        bullet.setRotation(shooter.getRotation());

        bullet.setShooter(shooter);

        return bullet;
    }

    private void setShape(Entity entity) {
        entity.setPolygonCoordinates(-2,-2,2,0,-2,2);
    }

}

package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy1;

    private Entity enemy2;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Entity enemyShip = createEnemyShip(gameData);
                world.addEntity(enemyShip);
            }
        }, 0, 10000);
    }

    private Entity createEnemyShip(GameData gameData) {

        Random r = new Random();

        Entity enemyShip = new Enemy(2);
        System.out.println(enemyShip.getLife() + "this is initial ship life");
        enemyShip.setPolygonCoordinates(8, -5, 5, -5, 0, -7, -5, -7, -8, -5, -8, -3,
                -5, -3, 0, -1, 5, -1, 8, -3

        );
        enemyShip.setX(r.nextDouble(gameData.getDisplayHeight()));
        enemyShip.setY(r.nextDouble(gameData.getDisplayWidth()));
        enemyShip.setRotation(r.nextDouble(361));
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy1);
        world.removeEntity(enemy2);
    }


}

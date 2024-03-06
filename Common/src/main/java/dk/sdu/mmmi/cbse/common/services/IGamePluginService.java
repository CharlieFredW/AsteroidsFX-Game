package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Used to handle the games start and stop conditions
 */

public interface IGamePluginService {

    /**
     * Start is called when the game should start
     * @param gameData
     * @param world
     *
     * @precondition the game plugin service is not loaded
     * @postcondition the game plugin service is loaded
     */

    void start(GameData gameData, World world);

    /**
     * Stop is called when the game should stop
     * @param gameData
     * @param world
     *
     * @precondition the plugin service is loaded and running
     * @postcondition the plugin service is not running
     */
    void stop(GameData gameData, World world);
}

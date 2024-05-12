package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Used to handle the games start and stop conditions
 */

public interface IGamePluginService {

    /**
     * Start is called when the game should start, and is responsible for instantiating the entities that implements the service into the world
     * Prepares the game environment for gameplay
     * @param gameData Information about the game data e.g. game keys
     * @param world
     *
     * @precondition the game plugin service is not loaded. gameData is not null. world is not null
     * @postcondition the game plugin service is loaded and no error occurs making the game ready for operation
     */

    void start(GameData gameData, World world);

    /**
     * Stop is called when the game should stop, and is responsible for removing the entities that implements the service from the world
     * Cleans the game environment up after gameplay
     * @param gameData Information about the game data e.g. game keys
     * @param world Information about the world e.g. entities in the world
     *
     * @precondition the plugin service is loaded and running
     * @postcondition the plugin service is not running
     */
    void stop(GameData gameData, World world);
}

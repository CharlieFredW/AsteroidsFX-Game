package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Processes the entities in the game
 */
public interface IEntityProcessingService {

    /**
     * Used to process all the entities that have been registered
     * Called before entities get drawn/initialized
     * Is used to update the entities and is not correlated to drawing them
     * @param gameData Information about the game data e.g. game keys
     * @param world Information about the world e.g. entities in the world
     * @throws
     *
     * @precondition gameData and world must be in a valid state
     * @postcondition Processing for the entities is done
     */
    void process(GameData gameData, World world);
}

package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Is called every game state refresh, IEntityProcessingService will always have been called before
 *
 */
public interface IPostEntityProcessingService {

    /**
     * Is called after the entity process is done
     * @param gameData Information about the game data e.g. game keys
     * @param world Information about the world e.g. entities in the world
     *
     * @precondition the entitiy processing is done
     * @postcondition the post entity processing is done, changes to the current game state refresh have been carried out
     */

    void process(GameData gameData, World world);
}

package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Is used to process entities after the process is stopped
 *
 */
public interface IPostEntityProcessingService {

    /**
     * Is called after the entity process is done
     * @param gameData
     * @param world
     *
     * @precondition the entitiy processing is done
     * @postcondition the post entity processing is done
     */

    void process(GameData gameData, World world);
}

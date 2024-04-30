module Common {
    exports dk.sdu.mmmi.cbse.common.services;
    exports dk.sdu.mmmi.cbse.common.data;
    exports dk.sdu.mmmi.cbse.common.servicelocator;

    //service loader singleton uses the services
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
}
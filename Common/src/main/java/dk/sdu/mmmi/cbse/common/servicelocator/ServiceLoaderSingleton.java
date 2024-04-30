package dk.sdu.mmmi.cbse.common.servicelocator;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class ServiceLoaderSingleton {

    private static ServiceLoaderSingleton instance;
    private List<IGamePluginService> gamePluginServices;
    private List<IEntityProcessingService> entityProcessingServices;
    private List<IPostEntityProcessingService> postEntityProcessingServices;

    // create loader singleton constructor
    private ServiceLoaderSingleton() {
        gamePluginServices = loadServices(IGamePluginService.class);
        entityProcessingServices = loadServices(IEntityProcessingService.class);
        postEntityProcessingServices = loadServices(IPostEntityProcessingService.class);
    }

    // create loader singleton method
    public static synchronized ServiceLoaderSingleton getInstance() {
        if (instance == null) {
            instance = new ServiceLoaderSingleton();
        }
        return instance;
    }

    public List<IGamePluginService> getGamePluginServices() {
        return gamePluginServices;
    }

    public List<IEntityProcessingService> getEntityProcessingServices() {
        return entityProcessingServices;
    }

    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return postEntityProcessingServices;
    }


    // load implementations of service interfaces using serviceloader
    private <T> List<T> loadServices(Class<T> serviceClass) {
        List<T> services = new ArrayList<>();
        ServiceLoader<T> serviceLoader = ServiceLoader.load(serviceClass);
        for (T service : serviceLoader) {
            services.add(service);
        }
        return services;
    }
}


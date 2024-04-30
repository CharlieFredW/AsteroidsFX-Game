module Core {
    requires Common;
    requires CommonBullet;    
    requires javafx.graphics;    
    opens dk.sdu.mmmi.cbse.main to javafx.graphics;
    // core now just uses the service loader singleton
    uses dk.sdu.mmmi.cbse.common.servicelocator.ServiceLoaderSingleton;
}



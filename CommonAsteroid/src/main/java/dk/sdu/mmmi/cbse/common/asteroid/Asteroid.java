package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Arrays;
import java.util.Random;

public class Asteroid extends Entity {

    public int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Random r = new Random();

    public Asteroid(int size, int life) {
        super(life);
        this.size = size;
    }



}
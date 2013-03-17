package com.sjodahl.game.world;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * World manager, manages our GameObject:s
 *
 * @author Robert Sjödahl
 */
public class WorldManager {
    
    /**
     * Vector of GameObject:s, that represents our world.
     */
    private Vector<GameObject> objectList;
    
    /**
     * Creates a new instance of WorldManager
     */
    public WorldManager() {
        objectList = new Vector<GameObject>();
    }
    
    /**
     * Add a game object to the world
     */
    public void addGameObject(GameObject gameObject) {
        objectList.add(gameObject);
    }
    
    /**
     * Updates the world, iterates through all game objects
     * and calls their onUpdate function.
     */
    public void update(long elapsedTime) {
        Iterator iterator = objectList.iterator();
        
        while (iterator.hasNext()) {
            GameObject go = (GameObject) iterator.next();
            
            if (go.isDead())
                iterator.remove();
            else
                go.update(elapsedTime);
        }
        
        /* Test for collisions between game objects */
        collisionTest();
    }
    
    /**
     * Test objects for collision against each other.
     */
    private void collisionTest() {
        int i = 1;

        for (GameObject thisObject : objectList) {
            CollisionVisitor visitor = (CollisionVisitor) thisObject;

            for (int j = i; j < objectList.size(); j++) {
                GameObject thatObject = objectList.get(j);

                if (thisObject.getBoundingVolume().intersects(thatObject.getBoundingVolume())) {
                    thatObject.collidedWith(visitor);
                }
            }

            i++;
        }
    }
    
    /**
     * Draw the world, iterates through the game objects
     * stored in the world and calls their onDraw function.
     */
    public void draw(Graphics graphics) {
        for (GameObject go : objectList) {
            go.draw(graphics);
        }
    }
}

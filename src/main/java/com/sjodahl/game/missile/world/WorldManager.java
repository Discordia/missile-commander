/*
 * WorldManager.java
 *
 * Created on February 11, 2005, 10:10 PM
 */

package com.sjodahl.game.missile.world;

import java.awt.Graphics;
import java.util.*;

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
    public void addGameObject(GameObject go) {
        GameObject gameObject = (GameObject) go.clone();
        objectList.add(gameObject);
    }
    
    /**
     * Remove a game object from the world.
     */
    public void removeGameObject(GameObject go) {
        objectList.remove(go);
    }
    
    /**
     * Updates the world, iterates through all game objects
     * and calls their update function.
     */
    public void update(long elapsedTime) {
        Iterator iter = objectList.iterator();
        
        while(iter.hasNext()) {
            GameObject go = (GameObject) iter.next();
            
            if (go.isDead())
                iter.remove();
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
        Iterator iterator = objectList.iterator();
        
        while(iterator.hasNext()) {
            CollisionVisitor visitor = (CollisionVisitor) iterator.next();
            
            for (int j = i; j < objectList.size(); j++) {
                GameObject go = objectList.get(j);
                go.collidedWith(visitor);
            }
            
            i++;
        }
    }
    
    /**
     * Draw the world, iterates through the game objects
     * stored in the world and calls their draw function.
     */
    public void draw(Graphics graphics) {
        Iterator iterator = objectList.iterator();
        
        while(iterator.hasNext()) {
            GameObject go = (GameObject) iterator.next();
            go.draw(graphics);
        }
    }
}

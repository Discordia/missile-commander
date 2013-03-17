/*
 * WorldManager.java
 *
 * Created on February 11, 2005, 10:10 PM
 */

package discordia.world;

import java.awt.Graphics;
import java.util.*;

/**
 * World manager, manages our GameObject:s
 * @author Robert Sjödahl
 */
public class WorldManager {
    
    /**
     * Vector of GameObject:s, that represents our world.
     */
    private Vector objectList;
    
    /**
     * Creates a new instance of WorldManager
     */
    public WorldManager() {
        objectList = new Vector();
    }
    
    /**
     * Add a game obejct to the world
     */
    public void addGameObject(GameObject go) {
        GameObject gameObject = (GameObject) go.clone();
        objectList.add(gameObject);
    }
    
    /**
     * Remove a gameobejct from the world.
     */
    public void removeGameObject(GameObject go) {
        objectList.remove(go);
    }
    
    /**
     * Updates the world, iterates through all game obejcts
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
        
        /* Test for collisons betwenn game objects */
        CollisonTest();
    }
    
    /**
     * Test objects for collision against each other.
     */
    private void CollisonTest() {
        int i = 1;
        Iterator iter = objectList.iterator();
        
        while(iter.hasNext()) {
            CollisionVisitor visitor = (CollisionVisitor) iter.next();
            
            for (int j = i; j < objectList.size(); j++) {
                GameObject go = (GameObject) objectList.get(j);
                go.collidedWith(visitor);
            }
            
            i++;
        }
    }
    
    /**
     * Draw the world, iterates through the game obejcts 
     * stored in the world and calls their draw function.
     */
    public void draw(Graphics graphics) {
        Iterator iter = objectList.iterator();
        
        while(iter.hasNext()) {
            GameObject go = (GameObject) iter.next();
            go.draw(graphics);
        }
    }
    
}

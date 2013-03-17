package com.sjodahl.game.world;

import com.sjodahl.game.missile.collision.MissileCommanderCollisionVisitor;

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
    private Vector<GameObject<MissileCommanderCollisionVisitor>> objectList;
    
    /**
     * Creates a new instance of WorldManager
     */
    public WorldManager() {
        objectList = new Vector<GameObject<MissileCommanderCollisionVisitor>>();
    }
    
    /**
     * Add a game object to the world
     */
    public void addGameObject(GameObject<MissileCommanderCollisionVisitor> gameObject) {
        objectList.add(gameObject);
    }
    
    /**
     * Updates the world, iterates through all game objects
     * and calls their onUpdate function.
     */
    public void update(long elapsedTime) {
        Iterator<GameObject<MissileCommanderCollisionVisitor>> iterator = objectList.iterator();
        
        while (iterator.hasNext()) {
            GameObject<MissileCommanderCollisionVisitor> go = iterator.next();
            
            if (go.isDead())
                iterator.remove();
            else
                go.update(elapsedTime);
        }
        
        // Test for collisions between game objects
        collisionTest();
    }
    
    /**
     * Test objects for collision against each other.
     */
    private void collisionTest() {
        int i = 1;

        for (GameObject<MissileCommanderCollisionVisitor> thisObject : objectList) {
            MissileCommanderCollisionVisitor visitor = (MissileCommanderCollisionVisitor) thisObject;

            for (int j = i; j < objectList.size(); j++) {
                GameObject<MissileCommanderCollisionVisitor> thatObject = objectList.get(j);

                if (thisObject.getBoundingVolume().intersects(thatObject.getBoundingVolume())) {
                    thatObject.collision(visitor);
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
        for (GameObject<MissileCommanderCollisionVisitor> go : objectList) {
            go.draw(graphics);
        }
    }
}

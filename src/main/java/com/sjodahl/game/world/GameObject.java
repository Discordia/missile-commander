/*
 * GameObject.java
 *
 * Created on February 11, 2005, 10:21 PM
 */

package com.sjodahl.game.world;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * GameObject, can be draw, updated and collided with. In a more complex game
 * these may have been separated, perhaps then: GameObject implements Renderable, Collideable.
 *
 * @author Robert Sjödahl
 */
public interface GameObject extends java.lang.Cloneable {
    
    /**
     * Update the game object.
     */
    public void update(long elapsedTime);
    
    /**
     * Tell the game object to draw itself to the screen.
     *
     * @param graphics the window's Graphics2D.
     */
    public void draw(Graphics graphics);
    
    /**
     * Get the bounding volume of the game object, for collision tests.
     *
     * @return a Rectangle representing the game objects bounding volume.
     */
    public Rectangle getBoundingVolume();
    
    /**
     * Method to get the position of the game object.
     *
     * @return position of the game obejct.
     */
    public Point2D.Double getPosition();
    
    /**
     * Method for checking whether a object is dead, and should then be
     * removed from the game.
     *
     * @return true if it is dead and should be removed from the game, false
     * otherwise.
     */
    public boolean isDead();
    
    /**
     * Represents the accept(Visitor visitor) you often see in explanation of the
     * visitor pattern.
     */
    public void collidedWith(CollisionVisitor visitor);
    
    /**
     * Make game objects Cloneable.
     */
    public Object clone();
}

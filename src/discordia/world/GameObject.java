/*
 * GameObject.java
 *
 * Created on February 11, 2005, 10:21 PM
 */

package discordia.world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

/**
 * GameObject, can be draw, upadted and collided wtih. In a more complexed game
 * these may have meen seperated, prehaps then: GameObject implements Renderable, Collideable.
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
     * @param the widnow Graphics2D.
     */
    public void draw(Graphics graphics);
    
    /**
     * Get the bounding volume of the game objet, for collision tests.
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
     * Method for checking wheter a object is dead, and should then be
     * reoved from the game.
     *
     * @return true if it is dead and should be reoved from the game, false
     * otherwise.
     */
    public boolean isDead();
    
    /**
     * Represents the accept(Visitor visitor) you often see in explenation of the
     * visitor paatern.
     */
    public void collidedWith(CollisionVisitor visitor);
    
    /**
     * Make game objects Cloneable.
     */
    public Object clone();
}

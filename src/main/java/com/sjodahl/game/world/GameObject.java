package com.sjodahl.game.world;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * GameObject, can be drawn, updated and collided with. In a more complex game
 * these may have been separated, perhaps then: GameObject implements Renderable, Collideable.
 *
 * @author Robert Sjödahl
 */
public interface GameObject {
    
    /**
     * Update the game object.
     */
    public void update(long elapsedTime);
    
    /**
     * Tell the game object to onDraw itself to the screen.
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
}

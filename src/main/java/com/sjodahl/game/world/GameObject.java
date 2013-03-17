package com.sjodahl.game.world;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * GameObject, can be drawn, updated and collided with. In a more complex game
 * these may have been separated, perhaps then: GameObject implements Renderable, Collideable.
 *
 * @author Robert Sjödahl
 */
public abstract class GameObject<Visitor extends CollisionVisitor> {

    /**
     * The game objects bounding volume;
     */
    protected Rectangle boundingVolume;

    /**
     * The game objects position
     */
    protected Point2D.Double position;

    /**
     * Variable that tells if the object is dead.
     */
    protected boolean dead;

    /**
     * Creates a new instance of GameObject
     */
    public GameObject(Point2D.Double pos) {
        position = pos;
        dead = false;
        boundingVolume = new Rectangle();
    }

    /**
     * Get the bounding volume of the game object, for collision tests.
     */
    public Rectangle getBoundingVolume() {
        return boundingVolume;
    }

    /**
     * Tells whether a game object is dead, that is should
     * be removed from the game.
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Calculate the objects bounding volume.
     */
    protected abstract void calcBoundingVolume();

    /**
     * Update the game object.
     */
    public abstract void update(long elapsedTime);

    /**
     * Tell the game object to draw itself to the screen.
     *
     * @param graphics the window's Graphics2D.
     */
    public abstract void draw(Graphics graphics);

    /**
     * This si the Accept method in the collision visitor. Uses double dispatch in the
     * derived objects to collided with the right object.
     */
    public abstract void collidedWith(Visitor visitor);
}

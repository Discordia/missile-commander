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
    private Rectangle boundingVolume;

    /**
     * The game objects position
     */
    private Point2D.Double position;

    /**
     * Variable that tells if the object is dead.
     */
    private  boolean dead;

    /**
     * Creates a new instance of GameObject
     */
    public GameObject(Point2D.Double position) {
        this.position = position;
        this.dead = false;
        this.boundingVolume = new Rectangle();
    }

    /**
     * Get the position
     *
     * @return the current position
     */
    public Point2D.Double getPosition() {
        return position;
    }

    /**
     * Set the position
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setPosition(double x, double y) {
        setPosition(new Point2D.Double(x, y));
    }

    /**
     * Set position
     *
     * @param position the new position
     */
    public void setPosition(Point2D.Double position) {
        this.position = position;
    }

    /**
     * Get the bounding volume of the game object, for collision tests.
     */
    public Rectangle getBoundingVolume() {
        return boundingVolume;
    }

    public void setBoundingVolume(int x, int y, int width, int height) {
        setBoundingVolume(new Rectangle(x, y, width, height));
    }

    /**
     * Set the bounding volume of this game object
     *
     * @param boundingVolume the new bounding volume
     */
    public void setBoundingVolume(Rectangle boundingVolume) {
        this.boundingVolume = boundingVolume;
    }

    /**
     * Tells whether a game object is dead, that is should
     * be removed from the game.
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Set the game object to be dead.
     */
    public void setDead() {
        this.dead = true;
    }

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

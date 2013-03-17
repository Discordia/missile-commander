package com.sjodahl.game.missile;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 *
 * @author Robert Sjödahl
 */
public class Explosion {
    
    /**
     * Constant, default explosion radius in pixels.
     */
    public static final double DEFAULT_EXPLOSION_RADIUS = 20;
    
    /**
     *
     */
    private Point2D.Double position;
    
    /**
     * The current radius.
     */
    private double currentRadius;
    
    /**
     * The radius that a explosion will reach.
     */
    private double explosionRadius;
    
    /**
     * Creates a new instance of Explosion
     */
    public Explosion(Point2D.Double pos) {
        currentRadius = 1;
        explosionRadius = DEFAULT_EXPLOSION_RADIUS;
        position = new Point2D.Double(pos.x, pos.y);
    }
    
    /**
     * Updates the explosion, that is it's radius will expand.
     */
    public void update(long elapsedTime) {
        if (currentRadius < explosionRadius)
            currentRadius +=4;
    }
    
    /**
     * Draws the explosion
     *
     * @param graphics the graphics object which the explosion should be drawn to.
     */
    public void draw(Graphics graphics) {
        graphics.fillOval((int)(position.x - currentRadius), (int)(position.y - currentRadius), (int) currentRadius*2, (int) currentRadius*2);
    }
    
    /**
     * Gets the current radius of the explosion.
     */
    public double getCurrentRadius() {
        return currentRadius;
    }
    
    /**
     * Set the radius that the explosion will rach at maximum.
     */
    public void setExplosionRadius(double er) {
        if (er > currentRadius)
            explosionRadius = er;
    }
    
    /**
     * Check whether the explosion is over.
     *
     * @return true if the explosion is over, false otherwise.
     */
    public boolean isOver() {
        return currentRadius >= explosionRadius;
    }
}

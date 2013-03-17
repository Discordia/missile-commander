package com.sjodahl.game.missile;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 *
 * @author Robert Sjödahl
 */
public class Enemy {
    
    /**
     *
     */
    Random random;
    
    /**
     *
     */
    private Dimension screenSize;
    
    /** 
     * Creates a new instance of Enemy 
     */
    public Enemy(Dimension screenSize) {
        this.screenSize = screenSize;
        random = new Random(System.currentTimeMillis());
    }
    
    /**
     * generate a new missile to send against the players towns.
     * Random speed, origin and direction.
     */
    public Missile generateNewMissile() {
        Point2D.Double origin = new Point2D.Double((double)random.nextInt(screenSize.width), 0);
        Point2D.Double target = new Point2D.Double((double)random.nextInt(screenSize.width), (double)screenSize.height);
        Point2D.Double dir = new Point2D.Double();
        dir.x = target.x - origin.x;
        dir.y = target.y - origin.y;
        double length = Math.sqrt(Math.pow(dir.x, 2) + Math.pow(dir.y, 2));
        dir.x /= length;
        dir.y /= length;
        int speed = random.nextInt(20);
        
        
        Missile missile = new Missile(origin);
        missile.setDirection(dir);
        missile.setSpeed(speed);
        
        return missile;
    }
    
}

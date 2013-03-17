package com.sjodahl.game.missile;

import com.sjodahl.game.missile.collision.MissileCommanderCollisionVisitor;
import com.sjodahl.game.world.GameObject;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 *
 * @author Robert Sjödahl
 */
public class Ground extends GameObject<MissileCommanderCollisionVisitor> implements MissileCommanderCollisionVisitor
{
    
    /**
     *
     */
    private Dimension size;
    
    /**
     * Creates a new instance of Ground
     */
    public Ground(Point2D.Double pos, Dimension size) {
        super(pos);
        this.size = new Dimension(size);
        calcBoundingVolume();
    }
    
    /**
     * Calculate the objects bounding volume.
     */
    protected void calcBoundingVolume() {
        boundingVolume.setBounds((int)position.x, (int)position.y, (int)position.x + size.width, (int)position.y - size.height);
    }
    
    /**
     * Update the game object.
     */
    public void update(long elapsedTime) {
        
    }
    
    /**
     * Tell the game object to draw itself to the screen.
     *
     *@param graphics the window's Graphics2D.
     */
    public void draw(Graphics graphics) {
        
    }
    
    /**
     *
     */
    public  void collidedWith(MissileCommanderCollisionVisitor visitor) {
        visitor.collidedWith(this);
    }
    
    /**
     * @param missile the Missile that the Ground collided with
     */
    public void collidedWith(Missile missile) {
        missile.explode();
    }

    public void collidedWith(LuftWaffe luftWaffe) {
    }

    public void collidedWith(City city) {
    }

    public void collidedWith(Ground ground) {
    }
    
}

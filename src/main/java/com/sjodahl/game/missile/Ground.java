package com.sjodahl.game.missile;

import com.sjodahl.game.world.CollisionVisitor;
import com.sjodahl.game.world.GameObject;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 *
 * @author Robert Sjödahl
 */
public class Ground extends GameObject implements CollisionVisitor
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
    public  void collidedWith(CollisionVisitor visitor) {
        visitor.collidedWithGround(this);
    }
    
    /**
     *
     */
    public void collidedWithMissile(GameObject go) {
        if (go.getBoundingVolume().intersects(getBoundingVolume())) {
            Missile missile = (Missile) go;
            missile.explode();
        }
    }

    public void collidedWithLuftWaffe(GameObject go) {
    }

    public void collidedWithCity(GameObject go) {
    }

    public void collidedWithGround(GameObject go) {
    }
    
}

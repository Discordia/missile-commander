package com.sjodahl.game.missile;

import com.sjodahl.game.missile.collision.MissileCommanderCollisionVisitor;
import com.sjodahl.game.world.GameObject;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Antiaircraft defence, the german way Luftwaffe.
 *
 * @author Robert Sjödahl
 */
public class LuftWaffe extends GameObject<MissileCommanderCollisionVisitor> implements MissileCommanderCollisionVisitor
{
    
    /**
     * Luftwaffe explosion.
     */
    public Explosion explosion;
    
    /**
     * Creates a new instance of Luftwaffe
     */
    public LuftWaffe(Point2D.Double pos) {
        super(pos);
        explosion = new Explosion(pos);
        calcBoundingVolume();
    }
    
    /**
     *
     */
    public void update(long elapsedTime) {
        explosion.update(elapsedTime);
        
        calcBoundingVolume();
        
        if (explosion.isOver()) {
            setDead();
        }
    }
    
    /**
     * Draws this object.
     */
    public void draw(Graphics graphics) {
        graphics.setColor(Color.green);
        explosion.draw(graphics);
    }
    
    /**
     * The game object has collided with another one.
     *
     * @param visitor the other game object this one has collided with.
     */
    public void collidedWith(MissileCommanderCollisionVisitor visitor) {
        visitor.collidedWith(this);
    }
    
    /**
     *
     */
    public void calcBoundingVolume() {
        Point2D.Double position = getPosition();

        setBoundingVolume((int) (position.x - explosion.getCurrentRadius()),
                (int) (position.y - explosion.getCurrentRadius()),
                (int) explosion.getCurrentRadius() * 2,
                (int) explosion.getCurrentRadius() * 2);
    }
    
    /**
     * @param missile the Missile that the LuftWaffe collided with
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

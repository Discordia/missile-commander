package com.sjodahl.game.missile.game.missle;

import com.sjodahl.game.missile.world.CollisionVisitor;
import com.sjodahl.game.missile.world.GameObject;
import com.sjodahl.game.missile.world.GameObjectStub;
import java.awt.*;
import java.awt.geom.Point2D;


/**
 *
 * @author Robert Sjödahl
 */
public class Missile extends GameObjectStub implements CollisionVisitor
{
    
    /**
     *
     */
    private static final int DEFAULT_SPEED = 6;
    
    /**
     * Explosion for when the missile hits something.
     */
    private Explosion explosion;
    
    /**
     * The origin of the missile.
     */
    private Point2D.Double origin;
    
    /**
     * The direction of the missile.
     */
    private Point2D.Double direction;
    
    /**
     * Missile speed.
     */
    private int speed;
    
    /**
     * Creates a new instance of Missile
     */
    public Missile(Point2D.Double pos) {
        super(pos);
        origin = new Point2D.Double(pos.x, pos.y);
        explosion = null;
        speed = DEFAULT_SPEED;
        calcBoundingVolume();
    }
    
    /**
     * Update the missile.
     */
    public void update(long elapsedTime) {
        if (explosion != null)
            explosion.update(elapsedTime);
        else {
            position.x += direction.x*speed;
            position.y += direction.y*speed;
        }
        
        calcBoundingVolume();
        
        if (explosion != null) {
            if (explosion.isOver())
                dead = true;
        }
    }
    
    /**
     *
     */
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawLine((int)origin.x, (int)origin.y, (int)position.x, (int)position.y);
        
        if (explosion != null) {
            graphics.setColor(Color.red);
            explosion.draw(graphics);
        }
    }
    
    /**
     * Tell visitor that it has collided with a missile.
     */
    public void collidedWith(CollisionVisitor visitor) {
        visitor.collidedWithMissile(this);
    }
    
    /**
     *
     */
    public Object clone() {
        GameObjectStub missile =  new Missile(position);
        ((Missile)missile).setDirection(this.direction);
        ((Missile)missile).setSpeed(this.speed);
        return missile;
    }
    
    /**
     *
     */
    public void calcBoundingVolume() {
        if (explosion != null) {
            boundingVolume.setBounds((int)(position.x - explosion.getCurrentRadius()),
                    (int)(position.y - explosion.getCurrentRadius()),
                    (int)explosion.getCurrentRadius()*2,
                    (int)explosion.getCurrentRadius()*2);
        } else
            boundingVolume.setBounds((int)position.x - 2, (int)position.y - 2, 4, 4);
        
    }
    
    /**
     *
     */
    public void setSpeed(int s) {
        if (s > 0)
            speed = s;
    }
    
    /**
     *
     */
    public void setDirection(Point2D.Double dir) {
        direction = new Point2D.Double(dir.x, dir.y);
    }
    
    /**
     * AMke the missile explode.
     */
    public void explode() {
        if (explosion == null) {
            explosion = new Explosion(position);
        }
    }
    
    /**
     *
     */
    public void collidedWithMissile(GameObject go) {
        if (go.getBoundingVolume().intersects(getBoundingVolume())) {
            explode();
            Missile missile = (Missile) go;
            missile.explode();
        }
        
    }
    
    /**
     *
     */
    public void collidedWithLuftWaffe(GameObject go) {
        if (go.getBoundingVolume().intersects(getBoundingVolume())) {
            explode();
        }
    }
    
    /**
     *
     */
    public void collidedWithCity(GameObject go) {
        if (go.getBoundingVolume().intersects(getBoundingVolume())) {
            explode();
            City city = (City) go;
            city.destroy();
        }
    }
    
    /**
     *
     */
    public void collidedWithGround(GameObject go) {
        if (getBoundingVolume().intersects(go.getBoundingVolume()))
            explode();
    }
}

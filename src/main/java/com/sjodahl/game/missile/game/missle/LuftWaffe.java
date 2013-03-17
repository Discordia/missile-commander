package com.sjodahl.game.missile.game.missle;

import com.sjodahl.game.missile.world.CollisionVisitor;
import com.sjodahl.game.missile.world.GameObject;
import com.sjodahl.game.missile.world.GameObjectStub;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.geom.Point2D;

/**
 * Antiaircraft defence, the german way Luftwaffe.
 *
 * @author Robert Sjödahl
 */
public class LuftWaffe extends GameObjectStub implements CollisionVisitor
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
        
        if (explosion.isOver())
            dead = true;
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
    public void collidedWith(CollisionVisitor visitor) {
        visitor.collidedWithLuftWaffe(this);
    }
    
    /**
     *
     */
    public Object clone() {
        GameObject luftWaffe = new LuftWaffe(this.getPosition());
        return luftWaffe;
    }
    
    /**
     *
     */
    public void calcBoundingVolume() {
        boundingVolume.setBounds((int)(position.x - explosion.getCurrentRadius()),
                (int)(position.y - explosion.getCurrentRadius()),
                (int)explosion.getCurrentRadius()*2,
                (int)explosion.getCurrentRadius()*2);
    }
    
    /**
     *
     */
    public void collidedWithMissile(GameObject go) {
        if (getBoundingVolume().intersects(go.getBoundingVolume())) {
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

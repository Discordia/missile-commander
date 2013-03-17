/*
 * Ground.java
 *
 * Created on February 15, 2005, 6:27 PM
 */

package discordia.game.missle;

import discordia.world.CollisionVisitor;
import discordia.world.GameObject;
import discordia.world.GameObjectStub;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 *
 * @author Robert Sjödahl
 */
public class Ground extends GameObjectStub implements CollisionVisitor {
    
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
     *@param the widnow Graphics2D.
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
     * Make game objects Cloneable.
     */
    public Object clone() {
        GameObjectStub ground = new Ground(getPosition(), size);
        return ground;
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
    
    /**
     *
     */
    public void collidedWithLuftWaffe(GameObject go) {
        
    }
    
    /**
     *
     */
    public void collidedWithCity(GameObject go) {
        
    }
    
    /**
     *
     */
    public void collidedWithGround(GameObject go) {
        
    }
    
}

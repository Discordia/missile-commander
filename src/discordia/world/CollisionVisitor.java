/*
 * CollisionVisitor.java
 *
 * Created on February 14, 2005, 1:29 PM
 */

package discordia.world;

/**
 *
 * @author Robert Sjödahl
 */
public interface CollisionVisitor {
    
    /**
     * Called if an obejct has collided iwth an missile.
     */
    public void collidedWithMissile(GameObject go);
    
    /**
     * Called if an obejct has collided iwth an LuftWaffe.
     */
    public void collidedWithLuftWaffe(GameObject go);
    
    /**
     * Called if an obejct has collided iwth an City.
     */
    public void collidedWithCity(GameObject go);
    
    /**
     * Called if an obejct has collided iwth the ground.
     */
    public void collidedWithGround(GameObject go);
}

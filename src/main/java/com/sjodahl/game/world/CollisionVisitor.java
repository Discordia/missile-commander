package com.sjodahl.game.world;

/**
 *
 * @author Robert Sjödahl
 */
public interface CollisionVisitor {
    
    /**
     * Called if an obejct has collided with an missile.
     */
    public void collidedWithMissile(GameObject go);
    
    /**
     * Called if an object has collided with an LuftWaffe.
     */
    public void collidedWithLuftWaffe(GameObject go);
    
    /**
     * Called if an object has collided with an City.
     */
    public void collidedWithCity(GameObject go);
    
    /**
     * Called if an object has collided with the ground.
     */
    public void collidedWithGround(GameObject go);
}

package com.sjodahl.game.world;

/**
 *
 * @author Robert Sjödahl
 */
public interface CollisionVisitor {
    
    /**
     * Called if an object has collided with an missile.
     */
    void collidedWithMissile(GameObject go);
    
    /**
     * Called if an object has collided with an LuftWaffe.
     */
    void collidedWithLuftWaffe(GameObject go);
    
    /**
     * Called if an object has collided with an City.
     */
    void collidedWithCity(GameObject go);
    
    /**
     * Called if an object has collided with the ground.
     */
    void collidedWithGround(GameObject go);
}

package com.sjodahl.game.world;

/**
 *
 * @author Robert Sjödahl
 */
public interface CollisionVisitor {
    
    /**
     * Called if an object has collided with a Missile.
     */
    void collidedWithMissile(GameObject go);
    
    /**
     * Called if an object has collided with a LuftWaffe.
     */
    void collidedWithLuftWaffe(GameObject go);
    
    /**
     * Called if an object has collided with a City.
     */
    void collidedWithCity(GameObject go);
    
    /**
     * Called if an object has collided with the ground.
     */
    void collidedWithGround(GameObject go);
}

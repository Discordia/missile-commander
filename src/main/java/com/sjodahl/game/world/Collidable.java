package com.sjodahl.game.world;

/**
 * @author robban
 */
public interface Collidable<Visitor extends CollisionVisitor> {

    /**
     * Collision has happened
     *
     * @param visitor the other collidable object that we collided with
     */
    void collision(Visitor visitor);
}

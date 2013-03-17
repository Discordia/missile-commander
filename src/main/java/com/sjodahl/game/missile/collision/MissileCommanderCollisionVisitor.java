package com.sjodahl.game.missile.collision;

import com.sjodahl.game.missile.City;
import com.sjodahl.game.missile.Ground;
import com.sjodahl.game.missile.LuftWaffe;
import com.sjodahl.game.missile.Missile;
import com.sjodahl.game.world.CollisionVisitor;

/**
 * @author robban
 */
public interface MissileCommanderCollisionVisitor extends CollisionVisitor {

    /**
     * Called if an object has collided with a Missile.
     *
     * @param missile the Missile that the GameObject collided with
     */
    void collidedWith(Missile missile);

    /**
     * Called if an object has collided with a LuftWaffe.
     *
     * @param luftWaffe the luftWasse that the GameObject collided with
     */
    void collidedWith(LuftWaffe luftWaffe);

    /**
     * Called if an object has collided with a City.
     *
     * @param city the City that the GameObject collided with
     */
    void collidedWith(City city);

    /**
     * Called if an object has collided with the ground.
     *
     * @param ground the Ground that the GameObject collided with
     */
    void collidedWith(Ground ground);
}

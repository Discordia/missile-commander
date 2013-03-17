package com.sjodahl.game.missile;

import com.sjodahl.game.missile.collision.MissileCommanderCollisionVisitor;
import com.sjodahl.game.world.GameObject;

import java.awt.*;
import java.awt.geom.Point2D;


/**
 * @author Robert Sjödahl
 */
public class Missile extends GameObject<MissileCommanderCollisionVisitor> implements MissileCommanderCollisionVisitor {

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
            Point2D.Double position = getPosition();

            setPosition(position.x + direction.x * speed, position.y + direction.y * speed);
        }

        calcBoundingVolume();

        if (explosion != null && explosion.isOver()) {
            setDead();
        }
    }

    /**
     *
     */
    public void draw(Graphics graphics) {
        Point2D.Double position = getPosition();

        graphics.setColor(Color.BLACK);
        graphics.drawLine((int) origin.x, (int) origin.y, (int) position.x, (int) position.y);

        if (explosion != null) {
            graphics.setColor(Color.red);
            explosion.draw(graphics);
        }
    }

    /**
     * Tell visitor that it has collided with a missile.
     */
    public void collidedWith(MissileCommanderCollisionVisitor visitor) {
        visitor.collidedWith(this);
    }

    /**
     *
     */
    public void calcBoundingVolume() {
        Point2D.Double position = getPosition();

        if (explosion != null) {
            setBoundingVolume((int) (position.x - explosion.getCurrentRadius()),
                    (int) (position.y - explosion.getCurrentRadius()),
                    (int) explosion.getCurrentRadius() * 2,
                    (int) explosion.getCurrentRadius() * 2);
        } else {
            setBoundingVolume((int) position.x - 2, (int) position.y - 2, 4, 4);
        }
    }

    /**
     *
     */
    public void setSpeed(int s) {
        if (s > 0) {
            speed = s;
        }
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
            Point2D.Double position = getPosition();
            explosion = new Explosion(position);
        }
    }

    /**
     * @param missile the Missile that this Missile collided with
     */
    public void collidedWith(Missile missile) {
        explode();
        missile.explode();
    }

    /**
     * @param luftWaffe the LuftWaffe that the Missile Collided with
     */
    public void collidedWith(LuftWaffe luftWaffe) {
        explode();
    }

    /**
     * @param city the City that this Missile collided with
     */
    public void collidedWith(City city) {
        explode();
        city.destroy();
    }

    /**
     * @param ground the Ground that this Missile collided with
     */
    public void collidedWith(Ground ground) {
        explode();
    }
}

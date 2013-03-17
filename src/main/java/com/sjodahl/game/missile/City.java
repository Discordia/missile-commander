package com.sjodahl.game.missile;

import com.sjodahl.game.missile.collision.MissileCommanderCollisionVisitor;
import com.sjodahl.game.world.GameObject;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 *
 * @author Robert Sjödahl
 */
public class City extends GameObject<MissileCommanderCollisionVisitor> implements MissileCommanderCollisionVisitor
{
    
    /**
     * Default city height.
     */
    public static final int DEFAULT_CITY_HEIGHT = 60;
    
    /**
     * Default city width
     */
    public static final int DEFAULT_CITY_WIDTH = 40;
    
    /**
     * The city size
     */
    private Dimension citySize;
    
    /**
     * Creates a new instance of City
     */
    public City(Point2D.Double pos) {
        super(pos);
        citySize = new Dimension(DEFAULT_CITY_WIDTH, DEFAULT_CITY_HEIGHT);
        calcBoundingVolume();
    }
    
    /**
     *
     */
    public City(Point2D.Double pos, Dimension size) {
        super(pos);
        citySize = new Dimension(size);
    }
    
    /**
     * Update city does nothing, a city is a static game object.
     */
    public void update(long elapsedTime) {
        
    }
    
    /**
     * Draws the object.
     */
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GRAY);

        int diff = citySize.height - citySize.width;
        int x = (int) position.x;
        int y = (int) position.y;

        int xCoord[] = {x, x, x + citySize.width/2, x  + citySize.width, x + citySize.width};
        int yCoord[] = {y, y - citySize.height + diff, y - citySize.height, y - citySize.height + diff, y};

        graphics.fillPolygon(new Polygon(xCoord, yCoord, 5));
    }
    
    /**
     * The game object has collided with another one.
     *
     * @param visitor the game objects Collision visitor.
     */
    public void collidedWith(MissileCommanderCollisionVisitor visitor) {
        visitor.collidedWith(this);
    }
    
    /**
     *
     */
    public void calcBoundingVolume() {
        boundingVolume.setBounds((int) position.x, (int) position.y, citySize.width, citySize.height);
    }
    
    /**
     * Destroy city, because it has been hit.
     */
    public void destroy() {
        dead = true;
    }
    
    /**
     *
     * @param missile the Missile that the City collided with
     */
    public void collidedWith(Missile missile) {
        missile.explode();
        destroy();
    }

    public void collidedWith(LuftWaffe luftWaffe) {
    }
    
    public void collidedWith(City city) {
    }

    public void collidedWith(Ground ground) {
    }
}

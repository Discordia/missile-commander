package com.sjodahl.game.missile;

import com.sjodahl.game.world.CollisionVisitor;
import com.sjodahl.game.world.GameObject;
import com.sjodahl.game.world.GameObjectStub;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 *
 * @author Robert Sjödahl
 */
public class City extends GameObjectStub implements CollisionVisitor
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
     *
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
    public void collidedWith(CollisionVisitor visitor) {
        visitor.collidedWithCity(this);
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
     */
    public void collidedWithMissile(GameObject go) {
        if (go.getBoundingVolume().intersects(getBoundingVolume())) {
            Missile missile = (Missile) go;
            missile.explode();
            destroy();
        }
    }

    public void collidedWithLuftWaffe(GameObject go) {
    }
    
    public void collidedWithCity(GameObject go) {
    }

    public void collidedWithGround(GameObject go) {
    }
}

/*
 * City.java
 *
 * Created on February 11, 2005, 10:24 PM
 */

package discordia.game.missle;

import discordia.world.CollisionVisitor;
import discordia.world.GameObject;
import discordia.world.GameObjectStub;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

/**
 *
 * @author Robert Sjödahl
 */
public class City extends GameObjectStub implements CollisionVisitor {
    
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
        int posx = (int) position.x;
        int posy = (int) position.y;
        int xcoord[] = {posx, posx, posx + citySize.width/2, posx  + citySize.width, posx + citySize.width};
        int ycoord[] = {posy, posy - citySize.height + diff, posy - citySize.height, posy - citySize.height + diff, posy};
        graphics.fillPolygon(new Polygon(xcoord, ycoord, 5));
    }
    
    /**
     * The game object has collided with another one.
     *
     * @param the other game objects Collsion visitor.
     */
    public void collidedWith(CollisionVisitor visitor) {
        visitor.collidedWithCity(this);
    }
    
    /**
     *
     */
    public Object clone() {
        GameObject city = new City(this.getPosition());
        return city;
    }
    
    /**
     *
     */
    public void calcBoundingVolume() {
        boundingVolume.setBounds((int) position.x, (int) position.y, citySize.width, citySize.height);
    }
    
    /**
     * Destory citry, because it has been hit.
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

/*
 * MissileCommander.java
 *
 * Created on February 11, 2005, 10:10 PM
 */

package discordia.game.missle;

import discordia.core.GameManager;
import discordia.core.InputManager;
import discordia.world.WorldManager;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;


/**
 *
 * @author Robert Sj�dahl
 */
public class MissileCommander extends GameManager {
    
    /**
     * Main method for the game.
     */
    public static void main(String args[]) {
        MissileCommander game = new MissileCommander();
        game.run();
    }
    
    /**
     * Our enemy that shots missiles at us.
     */
    private Enemy enemy;
    
    /**
     * Used for updating.
     */
    private long currentTime;
    
    /**
     * Creates a new instance of MissileCommander that
     * implements the old arcade game missile commander.
     */
    public MissileCommander() {
        currentTime = 0;
    }
    
    /**
     * Initilazation method.
     */
    public void onInit() {
        inputManager = new InputManager(screen);
        worldManager = new WorldManager();
        enemy = new Enemy(new Dimension(800, 700));
        
        // Add citys
        worldManager.addGameObject(new City(new Point2D.Double(100, 700)));
        worldManager.addGameObject(new City(new Point2D.Double(260, 700)));
        worldManager.addGameObject(new City(new Point2D.Double(420, 700)));
        worldManager.addGameObject(new City(new Point2D.Double(580, 700)));
        worldManager.addGameObject(new Ground(new Point2D.Double(0, 700), new Dimension(800, 40)));
    }
    
    /**
     * Implements the update method from the GameManager interface.
     */
    public void update(long elapsedTime) {
        if (inputManager.isMouseDown()) {
            Point pos = inputManager.getMousePos();
            worldManager.addGameObject(new LuftWaffe(new Point2D.Double(pos.x, pos.y)));
        }
        
        currentTime += elapsedTime;
        
       if (currentTime >= 500) {
            Missile missile = enemy.generateNewMissile();
            worldManager.addGameObject(missile);
            currentTime = 0;
        }
        
        worldManager.update(elapsedTime);
    }
    
    /**
     * Implements draw method from the GameManager interface.
     */
    public void draw(Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillRect(0, 0, screen.getWidth(), screen.getHeight());
        
        worldManager.draw(graphics);
    }
    
    /**
     * 
     */
    public void onDestroy() {
        
    }
    
}

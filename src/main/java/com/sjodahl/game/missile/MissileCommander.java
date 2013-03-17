package com.sjodahl.game.missile;

import com.sjodahl.game.core.Game;
import com.sjodahl.game.core.GameManager;
import com.sjodahl.game.core.InputManager;
import com.sjodahl.game.world.WorldManager;

import java.awt.*;
import java.awt.geom.Point2D;


/**
 * Entry-point of the application
 *
 * @author Robert Sjödahl
 */
public class MissileCommander implements Game {

    /**
     * Screen size
     */
    private static final Dimension SCREEN_SIZE = new Dimension(800, 700);

    /**
     * The game manager that runs our game
     */
    private final GameManager gameManager;

    /**
     * WorldManager holds our world/scene.
     */
    private WorldManager worldManager;

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
        gameManager = new GameManager(SCREEN_SIZE, this);

        currentTime = 0;
    }

    /**
     * Start Missile Commander game
     */
    public void start() {
        gameManager.start();
    }

    /**
     * Stop Missile Commander game
     */
    public void stop() {
        gameManager.stop();
    }

    /**
     * Initialization method.
     */
    public void onInit() {
        worldManager = new WorldManager();
        enemy = new Enemy(SCREEN_SIZE);
        
        // Add cities
        worldManager.addGameObject(new City(new Point2D.Double(100, 700)));
        worldManager.addGameObject(new City(new Point2D.Double(260, 700)));
        worldManager.addGameObject(new City(new Point2D.Double(420, 700)));
        worldManager.addGameObject(new City(new Point2D.Double(580, 700)));

        // Add ground
        Ground ground = new Ground(new Point2D.Double(0, SCREEN_SIZE.height), new Dimension(SCREEN_SIZE.width, 40));
        worldManager.addGameObject(ground);
    }
    
    /**
     * Implements the onUpdate method from the GameManager interface.
     */
    public void onUpdate(long elapsedTime, InputManager inputManager) {
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
     * Implements onDraw method from the GameManager interface.
     */
    public void onDraw(Graphics graphics) {
        // Draw background
        graphics.setColor(Color.blue);
        graphics.fillRect(0, 0, SCREEN_SIZE.width, SCREEN_SIZE.height);

        // Draw world
        worldManager.draw(graphics);
    }

    public void onDestroy() {
    }

    /**
     * Main method for the game.
     */
    public static void main(String args[]) {
        final MissileCommander game = new MissileCommander();
        game.start();
    }
}

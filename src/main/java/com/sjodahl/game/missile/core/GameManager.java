package com.sjodahl.game.missile.core;

import com.sjodahl.game.missile.world.WorldManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


/**
 *
 * @author Robert Sjödahl
 */
public abstract class GameManager {
    
    /**
     * Last time, used for calculating how long a frame takes
     * good to know when updating.
     */
    private long lastTime;
    
    /**
     * True if the game is running, false otherwise.
     */
    private boolean isRunning;
    
    /**
     * Input manager.
     */
    protected InputManager inputManager;
    
    /**
     * Our screen.
     */
    protected JFrame screen;
    
    /**
     * BufferStrategy for double buffering.
     */
    private BufferStrategy bufferStrategy;
    
    /**
     * WorldManager holds our world/scene.
     */
    protected WorldManager worldManager;
    
    /**
     * Creates a new instance of GameManager
     */
    public GameManager() {
    }
    
    /**
     * Run game, starts a new thread and runs the game in there. Calls
     * customized gameLoop in game that implements the GameManager.
     */
    public void run() {
        try {
            init();
            gameLoop();
        } finally {
            onDestroy();
            screen.dispose();
        }
        
    }
    
    /**
     * Used to stop the game manager, i.e stop the
     * game loop.
     */
    public void stop() {
        isRunning = false;
    }
    
    /**
     * Init, initalizes our game manager. Setup the screen etc.
     */
    public void init() {
        screen = new JFrame("Missile Commander");
        screen.setBackground(Color.blue);
        screen.setForeground(Color.white);
        screen.setSize(800, 700);
        screen.setResizable(false);
        screen.setVisible(true);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
            screen.createBufferStrategy(2);
            bufferStrategy = screen.getBufferStrategy();
        } catch(IllegalArgumentException iae) {
            System.exit(-1);
        } catch (IllegalStateException ise) {
            System.exit(-1);
        }
        
        isRunning = true;
        onInit();
    }
    
    /**
     * GameLoop.
     */
    public void gameLoop() {
        
        lastTime = System.currentTimeMillis();
        
        while(isRunning) {
            long elapsedTime = System.currentTimeMillis() - lastTime;
            lastTime += elapsedTime;
            
            update(elapsedTime);
            
            Graphics g = bufferStrategy.getDrawGraphics();
            draw(g);
            bufferStrategy.show();
            
            screen.getContentPane().paintComponents(g);
            g.dispose();
            
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {}
        }
    }
    
    /**
     * Method that specific game managers should override to make initialization.
     */
    public abstract void onInit();
    
    /**
     * Update method should be overridden by games that extends the GameManager.
     */
    public abstract void update(long elapsedTime);
    
    /**
     * Draw method should be overridden by games that extends the GameManager.
     */
    public abstract void draw(Graphics graphics);
    
    /**
     * Method that specific game managers should override to make destruction.
     */
    public abstract void onDestroy();
}

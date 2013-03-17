package com.sjodahl.game.core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


/**
 *
 * @author Robert Sjödahl
 */
public abstract class GameManager {

    /**
     * True if the game is running, false otherwise.
     */
    private boolean isRunning;

    /**
     * Our screen.
     */
    private JFrame screen;

    /**
     * Input manager.
     */
    private InputManager inputManager;

    /**
     * BufferStrategy for double buffering.
     */
    private BufferStrategy bufferStrategy;

    /**
     * Screen size
     */
    private final Dimension size;

    /**
     * Creates a new instance of GameManager
     */
    public GameManager(Dimension size) {
        this.size = size;
    }
    
    /**
     * Run game, starts a new thread and runs the game in there. Calls
     * customized gameLoop in game that implements the GameManager.
     */
    public void start() {
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
     * Init, initializes our game manager. Setup the screen etc.
     */
    private void init() {
        screen = new JFrame("Missile Commander");
        screen.setBackground(Color.blue);
        screen.setForeground(Color.white);
        screen.setSize(size);
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

        inputManager = new InputManager(screen);

        isRunning = true;

        onInit();
    }
    
    /**
     * GameLoop.
     */
    private void gameLoop() {
        long lastTime = System.currentTimeMillis();
        
        while(isRunning) {
            long elapsedTime = System.currentTimeMillis() - lastTime;
            lastTime += elapsedTime;
            
            onUpdate(elapsedTime, inputManager);
            
            Graphics g = bufferStrategy.getDrawGraphics();
            onDraw(g);
            bufferStrategy.show();
            
            screen.getContentPane().paintComponents(g);
            g.dispose();
            
            try {
                Thread.sleep(100);
            } catch(InterruptedException ignored) {}
        }
    }
    
    /**
     * Method that specific game managers should override to make initialization.
     */
    public abstract void onInit();
    
    /**
     * Update method should be overridden by games that extends the GameManager.
     */
    public abstract void onUpdate(long elapsedTime, InputManager inputManager);
    
    /**
     * Draw method should be overridden by games that extends the GameManager.
     */
    public abstract void onDraw(Graphics graphics);
    
    /**
     * Method that specific game managers should override to make destruction.
     */
    public abstract void onDestroy();
}

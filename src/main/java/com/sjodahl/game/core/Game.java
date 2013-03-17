package com.sjodahl.game.core;

import java.awt.*;

/**
 * @author robban
 */
public interface Game {

    /**
     * Method that specific game managers should override to make initialization.
     */
    void onInit();

    /**
     * Update method should be overridden by games that extends the GameManager.
     */
    void onUpdate(long elapsedTime, InputManager inputManager);

    /**
     * Draw method should be overridden by games that extends the GameManager.
     */
    void onDraw(Graphics graphics);

    /**
     * Method that specific game managers should override to make destruction.
     */
    void onDestroy();
}

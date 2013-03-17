package com.sjodahl.game.core;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * InputManager listens for input. Could be much more advanced than it is
 * at the moment, but this works for what we need for missile commander.
 *
 * @author Robert Sjödahl
 */
public class InputManager extends MouseInputAdapter {

    /**
     * Boolean that tells us if the first mouse button is down.
     */
    private boolean mouseDown;
    
    /**
     * The mouse pointers position
     */
    private Point mousePosition;
    
    /**
     * Creates a new instance of InputManager
     */
    public InputManager(Component component) {
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        mouseDown = false;
        component.setFocusTraversalKeysEnabled(false);
    }
    
    /**
     * Tells whether the first mouse button is down or not.
     */
    public boolean isMouseDown() {
        if (mouseDown) {
            mouseDown = false;
            return !mouseDown;
        }
        
        return false;
    }
    
    /**
     * Gets the mouse pointers position.
     */
    public Point getMousePos() {
        return mousePosition;
    }
    
    /**
     * Invoked when the mouse has been pressed in the window.
     */
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            mouseDown = true;
    }
    
    /**
     * Invoked when the mouse has been moved.
     */
    public void mouseMoved(MouseEvent e) {
        mousePosition = e.getPoint();
    }
}

/*
 * InputManager.java
 *
 * Created on February 11, 2005, 10:09 PM
 */

package discordia.core;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 * InputManager listens for input. Could be much more advanced than it is
 * at the moment, but this works for what we need for missile commander.
 * @author Robert Sjödahl
 */
public class InputManager extends MouseInputAdapter {
    
    /**
     * Component the imput manager listens til.
     */
    private Component component;
    
    /**
     * Boolean that tells us if the first mousebutton is down.
     */
    private boolean mouseDown;
    
    /**
     * The mouse pointers position
     */
    private Point mousePosition;
    
    /**
     * Creates a new instance of InputManager
     */
    public InputManager(Component comp) {
        component = comp;
        comp.addMouseListener(this);
        comp.addMouseMotionListener(this);
        mouseDown = false;
        comp.setFocusTraversalKeysEnabled(false);
    }
    
    /**
     * Tells wheter the first mouse button is down or not.
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
     * Invoced when the mouse has been pressed in the window.
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

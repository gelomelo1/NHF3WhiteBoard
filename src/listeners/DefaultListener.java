package listeners;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import canvasmodes.DefaultCanvasMode;

public class DefaultListener extends MouseAdapter {
    
    protected Point mousePos;
    protected DefaultCanvasMode defaultCanvasMode;
    protected int pressedMouse;

    public DefaultListener(DefaultCanvasMode defaultCanvasMode)
    {
        this.defaultCanvasMode = defaultCanvasMode;
    }

    public int getPressedMouse()
    {
        return pressedMouse;
    }

    public void mousePressed(MouseEvent e)
    {
        mousePos = e.getPoint();
        pressedMouse = e.getButton();
    }

    public void mouseDragged(MouseEvent e)
    {
        defaultCanvasMode.Move(new Point(Double.valueOf(e.getX() - mousePos.getX()).intValue(), Double.valueOf(e.getY() - mousePos.getY()).intValue()));
    }

    public void mouseMoved(MouseEvent e)
    {
        defaultCanvasMode.setMousePos(e.getPoint());
        if(defaultCanvasMode.getIsMenuUpdateable())
           defaultCanvasMode.update();
    }

}

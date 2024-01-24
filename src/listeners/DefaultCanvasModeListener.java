/**
 * Függvények:
 * 
 * DefaultCanvasModeListener(DefaultCanvasMode defaultCanvasMode):
 *     Az osztály konstruktora, amely inicializálja az egérfigyelőt az adott alapértelmezett vászon móddal.
 * 
 * getPressedMouse():
 *     Visszaadja a legutóbbi egérnyomógomb állapotát (gombnyomás érzékelésekor).
 * 
 * mousePressed(MouseEvent e):
 *     Az egér gombnyomás eseményére meghívódó metódus. Beállítja az egér pozícióját és rögzíti a lenyomott egérgombot.
 * 
 * mouseDragged(MouseEvent e):
 *     Az egér húzás eseményére meghívódó metódus. Mozgatja az alapértelmezett vászon módot a megfelelő egérmozgatásnak megfelelően.
 * 
 * mouseMoved(MouseEvent e):
 *     Az egér mozgás eseményére meghívódó metódus. Beállítja az egér pozícióját az alapértelmezett vászon mód számára,
 *     majd frissíti azt, ha az menü frissíthető állapotban van.
 * 
 */
package listeners;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import canvasmodes.DefaultCanvasMode;

public class DefaultCanvasModeListener extends DefaultListener {

    private Point frameMousePos;
    private Point mouseCurrentPoint;
    private boolean resizing = false;

    public DefaultCanvasModeListener(DefaultCanvasMode defaultCanvasMode)
    {
        super(defaultCanvasMode);
    }

    public void mouseClicked(MouseEvent e)
    {
        defaultCanvasMode.resetSelection();
    }

    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        frameMousePos = e.getPoint();
        if(defaultCanvasMode.getIsSelectionModeActive())
        resizing = defaultCanvasMode.isResize(e.getPoint());
    }

    public void mouseReleased(MouseEvent e)
    {
        if(getPressedMouse() == MouseEvent.BUTTON1)
            defaultCanvasMode.confirmSelection();
    }

    public void mouseDragged(MouseEvent e)
    {
        if(getPressedMouse() == MouseEvent.BUTTON3)
        defaultCanvasMode.Move(new Point(Double.valueOf(e.getX() - mousePos.getX()).intValue(), Double.valueOf(e.getY() - mousePos.getY()).intValue()));
        else if(getPressedMouse() == MouseEvent.BUTTON1)
        {
            if(defaultCanvasMode.getIsSelectionModeActive())
            {
                if(resizing)
                defaultCanvasMode.resize(new Point(Double.valueOf(e.getX() - frameMousePos.getX()).intValue(), Double.valueOf(e.getY() - frameMousePos.getY()).intValue()));
                else
                defaultCanvasMode.moveObjects(new Point(Double.valueOf(e.getX() - frameMousePos.getX()).intValue(), Double.valueOf(e.getY() - frameMousePos.getY()).intValue()));
                frameMousePos = e.getPoint();
            }
            else
            {
            Rectangle rectangle = null;
            if(mousePos.getX() <= e.getX() && mousePos.getY() <= e.getY())
            rectangle = new Rectangle(mousePos.x, mousePos.y, e.getX() - mousePos.x, e.getY() - mousePos.y);
            else if(mousePos.getX() <= e.getX() && mousePos.getY() >= e.getY())
            rectangle = new Rectangle(mousePos.x, e.getY(), e.getX() - mousePos.x, mousePos.y - e.getY());
            else if(mousePos.getX() >= e.getX() && mousePos.getY() <= e.getY())
            rectangle = new Rectangle(e.getX(), mousePos.y, mousePos.x - e.getX(), e.getY() - mousePos.y);
            else if(mousePos.getX() >= e.getX() && mousePos.getY() >= e.getY())
            rectangle = new Rectangle(e.getX(), e.getY(), mousePos.x - e.getX(), mousePos.y - e.getY());
            defaultCanvasMode.Selection(rectangle);
            }
        }
    }

    public void mouseMoved(MouseEvent e)
    {
        mouseCurrentPoint = e.getPoint();
    }

    public Point getMouseCurrentPoint()
    {
        return mouseCurrentPoint;
    }
}

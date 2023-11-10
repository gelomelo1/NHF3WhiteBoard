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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import canvasmodes.DefaultCanvasMode;

public class DefaultCanvasModeListener extends MouseAdapter {

    private Point mousePos;
    private DefaultCanvasMode defaultCanvasMode;
    private int pressedMouse;

    public DefaultCanvasModeListener(DefaultCanvasMode defaultCanvasMode)
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

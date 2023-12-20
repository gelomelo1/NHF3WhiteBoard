/**
 * Függvények:
 * 
 * DrawCanvasModeListener(DrawCanvasMode drawCanvasMode):
 *     Az osztály konstruktora, inicializálja az egérfigyelőt a rajz vászon móddal.
 * 
 * mousePressed(MouseEvent e):
 *     Az egér gombnyomás eseményére meghívódó metódus. Az egér bal gombjának lenyomásakor indít egy új vonalat a rajz vászon módban.
 * 
 * mouseDragged(MouseEvent e):
 *     Az egér húzás eseményére meghívódó metódus. Az egér jobb gombjának lenyomása esetén hívja az ősosztály mouseDragged metódusát,
 *     bal gomb lenyomása esetén hozzáad egy pontot a rajz vászon módban.
 * 
 */
package listeners;

import java.awt.event.MouseEvent;
import canvasmodes.DrawCanvasMode;

public class DrawCanvasModeListener extends DefaultListener {
    
    private DrawCanvasMode drawCanvasMode;
    
    public DrawCanvasModeListener(DrawCanvasMode drawCanvasMode)
    {
        super(drawCanvasMode);
        this.drawCanvasMode = drawCanvasMode;
    }

    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        if(getPressedMouse() == MouseEvent.BUTTON1)
           drawCanvasMode.StartNewCurve(e.getPoint());
    }

    public void mouseDragged(MouseEvent e)
    {
        if(getPressedMouse() == MouseEvent.BUTTON3)
        {
           super.mouseDragged(e);
        }
        else if(getPressedMouse() == MouseEvent.BUTTON1)
            drawCanvasMode.AddPoint(e.getPoint());
    }
}

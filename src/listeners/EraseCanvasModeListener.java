/**
 * Függvények:
 * 
 * EraseCanvasModeListener(EraseCanvasMode eraseCanvasMode):
 *     Az osztály konstruktora, inicializálja az egérfigyelőt az eltörlés vászon móddal.
 * 
 * mousePressed(MouseEvent e):
 *     Az egér gombnyomás eseményére meghívódó metódus. Az egér bal gombjának lenyomásakor törli a vászonon a megadott pont környékét.
 * 
 * mouseDragged(MouseEvent e):
 *     Az egér húzás eseményére meghívódó metódus. Az egér jobb gombjának lenyomása esetén hívja az ősosztály mouseDragged metódusát,
 *     bal gomb lenyomása esetén törli a vászonon a megadott pont környékét.
 * 
 */
package listeners;

import java.awt.event.MouseEvent;
import canvasmodes.EraseCanvasMode;

public class EraseCanvasModeListener extends DefaultListener {

    private EraseCanvasMode eraseCanvasMode;

        public EraseCanvasModeListener(EraseCanvasMode eraseCanvasMode)
    {
        super(eraseCanvasMode);
        this.eraseCanvasMode = eraseCanvasMode;
    }

    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        if(getPressedMouse() == MouseEvent.BUTTON1)
          eraseCanvasMode.erase(e.getPoint());
    }

    public void mouseDragged(MouseEvent e)
    {
        if(getPressedMouse() == MouseEvent.BUTTON3)
           super.mouseDragged(e);
        else if(getPressedMouse() == MouseEvent.BUTTON1)
            eraseCanvasMode.erase(e.getPoint());
    }
}

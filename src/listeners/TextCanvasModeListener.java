/**
 * Függvények:
 * 
 * mouseClicked(MouseEvent e):
 *     Az egérgomb lenyomásakor meghívódó metódus. Helyez egy szöveget a megadott ponton,
 *     majd frissíti a mód állapotát az update() metódussal.
 * 
 * mouseDragged(MouseEvent e):
 *     Az egér húzása közben meghívódó metódus. Az egérjobb gomb lenyomásakor a szülő osztály
 *     metódusát hívja meg, különben nem történik semmi.
 * 
 * mouseMoved(MouseEvent e):
 *     Az egér mozgása közben meghívódó metódus. Nem végez semmilyen műveletet.
 * 
 */
package listeners;

import java.awt.event.MouseEvent;
import canvasmodes.TextCanvasMode;

public class TextCanvasModeListener extends DefaultCanvasModeListener {
    private TextCanvasMode textCanvasMode;

    public TextCanvasModeListener(TextCanvasMode textCanvasMode)
    {
        super(textCanvasMode);
        this.textCanvasMode = textCanvasMode;
    }

    public void mouseClicked(MouseEvent e)
    {
        if(getPressedMouse() == MouseEvent.BUTTON1)
        {
            textCanvasMode.placeText(e.getPoint());
            textCanvasMode.update();
        }
    }

    public void mouseDragged(MouseEvent e)
    {
        if(getPressedMouse() == MouseEvent.BUTTON3)
            super.mouseDragged(e);
    }

    public void mouseMoved(MouseEvent e)
    {

    }
}
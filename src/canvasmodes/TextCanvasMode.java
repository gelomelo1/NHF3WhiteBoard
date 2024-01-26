/**
 * Függvények:
 * 
 * TextCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu):
 *     A TextCanvasMode osztály konstruktora. Beállítja a szövegobjektumokat kezelő mód alapértelmezett tulajdonságait.
 * 
 * placeText(Point point):
 *     Létrehoz egy szövegobjektumot a megadott pont alapján, hozzáadja a vászonhoz, beállítja a szükséges tulajdonságokat, és frissíti a vásznat.
 * 
 * setTextFocus(CanvasText text):
 *     Beállítja a megadott szövegobjektumot a kijelölt szövegobjektumnak a vásznon.
 * 
 */
package canvasmodes;

import java.awt.Point;
import containers.CanvasText;
import listeners.CanvasTextListener;
import listeners.TextCanvasModeListener;
import panels.Canvas;
import panels.ToolPropertiesMenu;

public class TextCanvasMode extends DefaultCanvasMode {
    
    private static boolean listenerExist = false;
    private CanvasTextListener canvasTextListener;

public TextCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu)
{
    super(canvas, toolPropertiesMenu, false);
    if(!listenerExist)
    canvasTextListener = new CanvasTextListener(this, false);
    listenerExist = true;
    for (CanvasText canvasText : canvas.getTexts()) {
        for (int i = 0; i < canvasText.getMouseListeners().length; i++) {
            if(canvasText.getMouseListeners()[i] instanceof CanvasTextListener)
            {
                CanvasTextListener canvasTextListener = (CanvasTextListener) canvasText.getMouseListeners()[i];
                canvasTextListener.setDefaultCanvasMode(this);
                canvasTextListener.setIsDefaultMode(false);
                break;
            }
        }
    }
    setMouseListener(new TextCanvasModeListener(this));
}    

public void placeText(Point point)
{
    CanvasText text = getCanvas().addText(new Point(point.x - 50, point.y - 50));
    text.setLineWrap(true);
    text.addMouseListener(canvasTextListener);
    getCanvas().repaint();
    getCanvas().resetActivitiesSelection();
    getCanvas().addSelectedCanvasActivity(text);
}
}

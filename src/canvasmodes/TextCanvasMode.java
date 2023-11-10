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

    private CanvasTextListener canvasTextListener;
public TextCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu)
{
    super(canvas, toolPropertiesMenu, false);
    canvasTextListener = new CanvasTextListener(this);
    setMouseListener(new TextCanvasModeListener(this));
}    

public void placeText(Point point)
{

    CanvasText text = getCanvas().addText(new Point(point.x - 50, point.y - 50));
    text.setLineWrap(true);
    text.addFocusListener(canvasTextListener);
    getCanvas().repaint();
    getCanvas().setSelectedText(text);

}

public void setTextFocus(CanvasText text)
{
    getCanvas().setSelectedText(text);
}

}

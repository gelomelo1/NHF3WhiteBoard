/**
 * Függvények:
 * 
 * EraseCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu):
 *     Az EraseCanvasMode osztály konstruktora. Beállítja a törlési mód alapértelmezett tulajdonságait.
 * 
 * erase(Point point):
 *     A megadott pontra kattintva eltávolítja a kijelölt objektumot a vászonról.
 * 
 */
package canvasmodes;

import java.awt.Point;
import java.util.ArrayList;
import additions.CanvasActivity;
import additions.PlaceEraseTransaction;
import additions.PlaceEraseTransaction.PlaceEraseForwardMode;
import listeners.EraseCanvasModeListener;
import panels.Canvas;
import panels.ToolPropertiesMenu;

public class EraseCanvasMode extends DefaultCanvasMode {

    public EraseCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu)
{
    super(canvas, toolPropertiesMenu, false);
    setMouseListener(new EraseCanvasModeListener(this));
}



public void erase()
{
    getCanvas().addTransactionToQueue(new PlaceEraseTransaction(getCanvas(), getCanvas().getSelectedCanvasActivities(), PlaceEraseForwardMode.Erase));
    for (CanvasActivity canvasActivity : getCanvas().getSelectedCanvasActivities()) {
        canvasActivity.erase(getCanvas());
    }
    resetSelection();
}

public void erase(ArrayList<CanvasActivity> canvasActivities, boolean isTransaction)
{
    if(!isTransaction)
    getCanvas().addTransactionToQueue(new PlaceEraseTransaction(getCanvas(), canvasActivities, PlaceEraseForwardMode.Erase));
    for (CanvasActivity activities : canvasActivities) {     
        activities.erase(getCanvas());
    }
    resetSelection();
}

 public void erase(Point point)
 {
    {
        ArrayList<CanvasActivity> canvObjects = getCanvas().getCanvasObjects();
        for (CanvasActivity canvasObject : canvObjects) {
            if(canvasObject.isCollided(point))
            {
                canvasObject.erase(getCanvas());
                break;
            }
        }
    }
    getCanvas().revalidate();
    getCanvas().repaint();
 }
}


/**
 * Függvények:
 * 
 * DrawCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu):
 *     A DrawCanvasMode osztály konstruktora. Beállítja a rajzolási mód alapértelmezett tulajdonságait.
 * 
 * StartNewCurve(Point point):
 *     Új vonal (kurva) kezdése a megadott pontról.
 * 
 * AddPoint(Point point):
 *     Hozzáad egy pontot a jelenlegi vonalhoz.
 * 
 */
package canvasmodes;

import listeners.DrawCanvasModeListener;
import java.awt.Point;
import java.util.ArrayList;

import additions.Brush;
import additions.CanvasActivity;
import additions.PlaceEraseTransaction;
import additions.PlaceEraseTransaction.PlaceEraseForwardMode;
import containers.Drawing;
import panels.Canvas;
import panels.ToolPropertiesMenu;

public class DrawCanvasMode extends DefaultCanvasMode {
private Drawing tempCurve;

public DrawCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu)
{
    super(canvas, toolPropertiesMenu, false);
    setMouseListener(new DrawCanvasModeListener(this));
}

public void StartNewCurve(Point point)
{
    tempCurve = new Drawing();
    tempCurve.setBrush(new Brush(getCanvas().getBrush()));
    getCanvas().addCurve(tempCurve);
    ArrayList<CanvasActivity> curves = new ArrayList<CanvasActivity>();
    curves.add(tempCurve);
    getCanvas().addTransactionToQueue(new PlaceEraseTransaction(getCanvas(), curves, PlaceEraseForwardMode.Place));
}

public void AddPoint(Point point)
{
    tempCurve.addPoint(point);
    getCanvas().repaint();
}

}

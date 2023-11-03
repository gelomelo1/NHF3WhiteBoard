package canvasmodes;

import listeners.DrawCanvasModeListener;
import java.awt.Point;
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
    getCanvas().addCurve(tempCurve);
}

public void AddPoint(Point point)
{
    tempCurve.addPoint(point);
    getCanvas().repaint();
}

}

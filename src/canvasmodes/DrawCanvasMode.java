package canvasmodes;

import listeners.DrawCanvasModeListener;
import java.awt.Point;
import java.util.ArrayList;

import panels.Canvas;

public class DrawCanvasMode extends DefaultCanvasMode {
private int stroke = 10;
private ArrayList<Point> tempCurve;

public DrawCanvasMode(Canvas canvas)
{
    super(canvas);
    setMouseListener(new DrawCanvasModeListener(this));
}

public void StartNewCurve(Point point)
{
    tempCurve = new ArrayList<Point>();
    getCanvas().addCurve(tempCurve);
    tempCurve.add(point);
}

public void AddPoint(Point point)
{
    tempCurve.add(point);
    getCanvas().repaint();
}

}

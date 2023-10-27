package canvasmodes;

import java.awt.Point;
import java.awt.event.MouseAdapter;

import listeners.DefaultCanvasModeListener;
import panels.Canvas;

public class DefaultCanvasMode {

    private Canvas canvas;

    public DefaultCanvasMode(Canvas canvas)
    {
        initCanvasMode(canvas, new DefaultCanvasModeListener(this));
    }

    private void initCanvasMode(Canvas canvas, MouseAdapter mouseListener)
    {
        this.canvas = canvas;
        this.canvas.setMouseListener(mouseListener);
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public void setMouseListener(MouseAdapter mouseListener)
    {
        canvas.setMouseListener(mouseListener);
    }

    public void Move(Point point)
    {
        canvas.setLocation(Double.valueOf(canvas.getX() + point.getX()).intValue(), Double.valueOf( canvas.getY() + point.getY()).intValue());
    }

}

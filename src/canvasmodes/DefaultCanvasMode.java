package canvasmodes;

import java.awt.Point;
import java.awt.event.MouseAdapter;

import listeners.DefaultCanvasModeListener;
import panels.Canvas;
import panels.ToolPropertiesMenu;

public class DefaultCanvasMode {

    private Canvas canvas;
    private boolean isMenuUpdateable;

    protected DefaultCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu, boolean isMenuUpdateable)
    {
        initCanvasMode(canvas, new DefaultCanvasModeListener(this), toolPropertiesMenu, isMenuUpdateable);
    }

     public DefaultCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu)
    {
        this(canvas, toolPropertiesMenu, true);
    }

    private void initCanvasMode(Canvas canvas, MouseAdapter mouseListener, ToolPropertiesMenu toolPropertiesMenu, boolean isMenuUpdateable)
    {
        this.canvas = canvas;
        this.canvas.setMouseListener(mouseListener);
        this.canvas.setToolPropertiesMenu(toolPropertiesMenu);
        this.isMenuUpdateable = isMenuUpdateable;
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
        int x = canvas.getX();
        int y = canvas.getY();
        if(Double.valueOf(canvas.getX() + point.getX()).intValue() <= 0 && Double.valueOf(canvas.getX() + point.getX()).intValue() >= -canvas.getMaxWidth() + canvas.getCanvasLayout().getWidth())
        x = Double.valueOf(canvas.getX() + point.getX()).intValue();
        if(Double.valueOf( canvas.getY() + point.getY()).intValue() <= 0 && Double.valueOf( canvas.getY() + point.getY()).intValue() >= -canvas.getMaxHeight() + canvas.getCanvasLayout().getHeight())
        y = Double.valueOf( canvas.getY() + point.getY()).intValue();
        canvas.setLocation(x, y);
    }

    public void update()
    {
        canvas.updateMenu();
    }

    public void setMousePos(Point point)
    {
        canvas.setMousePos(point);
    }

    public boolean getIsMenuUpdateable()
    {
        return isMenuUpdateable;
    }

}

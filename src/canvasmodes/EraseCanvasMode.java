package canvasmodes;

import java.awt.Point;
import java.util.ArrayList;
import additions.CanvasActivity;
import listeners.EraseCanvasModeListener;
import panels.Canvas;
import panels.ToolPropertiesMenu;

public class EraseCanvasMode extends DefaultCanvasMode {

    public EraseCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu)
{
    super(canvas, toolPropertiesMenu, false);
    setMouseListener(new EraseCanvasModeListener(this));
}

 public void erase(Point point)
 {
    ArrayList<CanvasActivity> canvObjects = getCanvas().getCanvasObjects();
    for (CanvasActivity canvasObject : canvObjects) {
        if(canvasObject.isCollided(point))
        {
            canvasObject.erase(getCanvas());
            break;
        }
    }
    getCanvas().revalidate();
    getCanvas().repaint();
 }
}


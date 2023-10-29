package canvasmodes;

import java.awt.Point;
import listeners.TextCanvasModeListener;
import panels.Canvas;
import panels.ToolPropertiesMenu;

public class TextCanvasMode extends DefaultCanvasMode {
public TextCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu)
{
    super(canvas, toolPropertiesMenu, false);
    setMouseListener(new TextCanvasModeListener(this));
}    

public void placeText(Point point)
{

}
}

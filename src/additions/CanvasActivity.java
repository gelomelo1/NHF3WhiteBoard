package additions;

import java.awt.Point;
import panels.Canvas;

public interface CanvasActivity {
    public boolean isCollided(Point point);
    public void erase(Canvas canvas);
}

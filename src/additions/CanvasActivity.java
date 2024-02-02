/**
 * Függvények:
 * 
 * isCollided(Point point):
 *     Megadja, hogy a tevékenységnek ütközik-e a megadott pont.
 * 
 * erase(Canvas canvas):
 *     Eltávolítja a tevékenységet a vászonról.
 */
package additions;

import java.awt.Point;
import java.awt.Rectangle;

import panels.Canvas;

public interface CanvasActivity extends MoveAndResize {
    public boolean isCollided(Point point);
    public boolean isCollided(Rectangle rectangle);
    public void erase(Canvas canvas);
    public void setSelected();
    public void resetSelected();
    public Rectangle getSelectedBounds();
    public String toString();
}

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
import panels.Canvas;

public interface CanvasActivity {
    public boolean isCollided(Point point);
    public void erase(Canvas canvas);
}

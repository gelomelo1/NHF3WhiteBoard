/**
 * Függvények:
 * 
 * CanvasText():
 *     A CanvasText osztály konstruktora. Beállítja a szövegobjektum alapértelmezett tulajdonságait.
 * 
 * isCollided(Point point):
 *     Ellenőrzi, hogy a megadott pont ütközik-e a szövegobjektum területével.
 * 
 * erase(Canvas canvas):
 *     Törli a szövegobjektumot a megadott vászonról.
 * 
 * changeFontSize(int size):
 *     Megváltoztatja a szövegobjektum betűméretét a megadott méretre, ha az a megengedett tartományban van.
 * 
 */
package containers;

import java.awt.Font;
import java.awt.Point;
import javax.swing.JTextArea;
import additions.CanvasActivity;
import panels.Canvas;

public class CanvasText extends JTextArea implements CanvasActivity {

  private int maxTextSize = 100;
  private int minTextSize = 10;

  public CanvasText()
  {
    setFont(new Font("Verdana", Font.PLAIN, minTextSize));
    setLayout(null);
  }

    @Override
    public boolean isCollided(Point point) {
      return getBounds().contains(point);
    }

    @Override
    public void erase(Canvas canvas) {
        canvas.getTexts().remove(this);
        canvas.remove(this);
    }

    public void changeFontSize(int size)
    {
      if(size >= minTextSize && size <= maxTextSize)
      setFont(new Font("Verdana", Font.PLAIN, size));
    }
    
}

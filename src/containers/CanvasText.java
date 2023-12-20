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

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import additions.CanvasActivity;
import panels.Canvas;

public class CanvasText extends JTextArea implements CanvasActivity {

  private transient boolean invertedWidth = false;
  private transient boolean invertedHeight = false;
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

        @Override
    public void setSelected() {
          setBorder(BorderFactory.createLineBorder(Color.black, 5));
    }

        @Override
    public void resetSelected() {
      setBorder(null);
    }

        @Override
    public boolean isCollided(Rectangle rectangle) {
      return getBounds().intersects(rectangle);
    }

        @Override
    public void moveCanvasObject(Point point) {
      Rectangle rectangle = getBounds();
      setBounds(new Rectangle(rectangle.x + point.x, rectangle.y + point.y, rectangle.width, rectangle.height));
    }

    @Override
    public void resizeCanvasObject(Point point) {
      Rectangle rectangle = getBounds();
      if(invertedWidth)
      {
          if(rectangle.x + point.x >= rectangle.x + rectangle.width)
          invertedWidth = false;
      }
      else
      {
          if(rectangle.width + point.x < 0)
          invertedWidth = true;
      }
      if(invertedHeight)
      {
          if(rectangle.y + point.y >= rectangle.y + rectangle.height)
          invertedHeight = false;
      }
      else
      {
          if(rectangle.height + point.y < 0)
          invertedHeight = true;
      }
      if(invertedWidth)
      {
          rectangle.x += point.x;
          rectangle.width += point.x * -1;
      }
      else
      {
          rectangle.width += point.x;
      }
      if(invertedHeight)
      {
          rectangle.y += point.y;
          rectangle.height += point.y * -1;
      }
      else
      {
          rectangle.height += point.y;
      }
      setBounds(rectangle);
    }

       @Override
    public Rectangle getSelectedBounds() {
      return getBounds();
    }

    public void changeFontSize(int size)
    {
      if(size >= minTextSize && size <= maxTextSize)
      setFont(new Font("Verdana", Font.PLAIN, size));
    }
}

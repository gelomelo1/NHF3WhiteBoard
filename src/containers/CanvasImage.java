/**
 * Függvények:
 * 
 * CanvasImage(String path):
 *     A CanvasImage osztály konstruktora. Beállítja a képobjektum alapértelmezett tulajdonságait és betölti a képet a megadott elérési útról.
 * 
 * setBounds(Rectangle rectangle):
 *     Beállítja a képobjektum határait a megadott téglalap alapján.
 * 
 * getPath():
 *     Visszaadja a képobjektumhoz tartozó elérési utat.
 * 
 * setPath(String path):
 *     Beállítja a képobjektumhoz tartozó elérési utat.
 * 
 * loadImage():
 *     Betölti a képet a képobjektumhoz tartozó elérési útról.
 * 
 * isCollided(Point point):
 *     Ellenőrzi, hogy a megadott pont ütközik-e a képobjektum területével.
 * 
 * erase(Canvas canvas):
 *     Törli a képobjektumot a megadott vászonról.
 * 
 * paintComponent(Graphics g):
 *     Kirajzolja a képet a komponensen belül.
 * 
 */
package containers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.nio.file.Paths;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import additions.CanvasActivity;
import panels.Canvas;
import uiholders.Menu;

public class CanvasImage extends JPanel implements CanvasActivity {

private transient boolean invertedWidth = false;
private transient boolean invertedHeight = false;
private transient ImageIcon image;
private String path;


public CanvasImage(String path)
{
    this.path = path;
    image = new ImageIcon(path);
    setLayout(null);
    setOpaque(false);
}

public void setBounds(Rectangle rectangle)
{
    super.setBounds(rectangle);
}

public String getPath()
{
    return path;
}

public void setPath(String path)
{
    this.path = path;
}

public void loadImage()
{
    File file = new File(Paths.get("").toAbsolutePath().resolve(path).toString());
    if(file.exists())
    image = new ImageIcon(Paths.get("").toAbsolutePath().resolve(path).toString());
    else
    image = new ImageIcon(Menu.getResourceFiles().get("CannotLoadImage"));
}

@Override
public boolean isCollided(Point point) {
    return getBounds().contains(point);
}

@Override
public void erase(Canvas canvas) {
    canvas.getImages().remove(this);
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

protected void paintComponent(Graphics g)
{
    super.paintComponent(g);
    if(image != null)
    g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
}

}

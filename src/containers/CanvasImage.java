package containers;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import additions.CanvasActivity;
import panels.Canvas;

public class CanvasImage extends JPanel implements CanvasActivity {

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

@Override
public boolean isCollided(Point point) {
    return getBounds().contains(point);
}

@Override
public void erase(Canvas canvas) {
    canvas.getImages().remove(this);
    canvas.remove(this);
}

protected void paintComponent(Graphics g)
{
    super.paintComponent(g);
    if(image != null)
    g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
}

}

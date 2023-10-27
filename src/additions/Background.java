package additions;

import java.awt.Color;
import java.awt.Graphics2D;

public class Background {
    
    //base properties
    private int size = 5;
    private int step = 200;
    private Color backgroundColor = new Color(0, 0, 0, 100);

    public void drawBackground(Graphics2D g, int width, int height)
    {
        Color originalColor = g.getColor();
        g.setColor(backgroundColor);
        for(int i = 0; i < width; i += step)
        {
            for(int j = 0; j < height; j += step)
            {
                g.fillOval(i, j, size, size);
            }
        }
        g.setColor(originalColor);
    }
}

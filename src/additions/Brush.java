package additions;

import java.awt.BasicStroke;
import java.awt.Color;

public class Brush {

    private Color color = Color.black;
    private BasicStroke stroke = new BasicStroke(1);

    public void setColor(Color c)
    {
        color = c;
    }

    public void setStroke(BasicStroke st)
    {
        stroke = st;
    }

    public Color getColor()
    {
        return color;
    }

    public BasicStroke getStroke()
    {
        return stroke;
    }

}

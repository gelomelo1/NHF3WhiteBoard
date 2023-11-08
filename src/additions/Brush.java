package additions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.Serializable;

public class Brush implements Serializable {

    private Color color = Color.black;
    private transient BasicStroke stroke = new BasicStroke(1);
    private int maxStroke = 20;
    private float currentStroke;

    public Brush()
    {

    }

    public Brush(Brush brush)
    {
        this.color = brush.color;
        this.stroke = brush.stroke;
        currentStroke = brush.stroke.getLineWidth();
    }

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

    public int getMaxStroke()
    {
        return maxStroke;
    }

    public float getCurrentStroke()
    {
        return currentStroke;
    }

}

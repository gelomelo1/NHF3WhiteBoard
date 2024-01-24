package additions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class SelectionRectangle implements MoveAndResize {
    private boolean invertedWidth = false;
    private boolean invertedHeight = false;
    private Rectangle selectionRectangle;
    private BasicStroke stroke = new BasicStroke(5);
    private int offset = 5;

    public SelectionRectangle(Rectangle selectionRectangle)
    {
        this.selectionRectangle = selectionRectangle;
    }

    public Rectangle getSelectionRectangle()
    {
        return selectionRectangle;
    }

    public float getSize()
    {
        return stroke.getLineWidth();
    }

    public int getOffset()
    {
        return offset;
    }

    public void drawSelectionRectangle(Graphics2D g2)
    {
        g2.setStroke(stroke);
        Color color = g2.getColor();
        g2.setColor(Color.gray);
        g2.draw(new Rectangle(selectionRectangle));
        g2.setColor(color);
    }

    @Override
    public void moveCanvasObject(Point point) {
        Rectangle rectangle = selectionRectangle.getBounds();
        selectionRectangle.setBounds(new Rectangle(rectangle.x + point.x, rectangle.y + point.y, rectangle.width, rectangle.height));
    }

    @Override
    public void resizeCanvasObject(Point point) {
        Rectangle rectangle = selectionRectangle.getBounds();
        if(invertedWidth)
        {
            if(rectangle.x + point.x >= rectangle.x + rectangle.width)
            invertedWidth = false;
        }
        else
        {
            if(rectangle.width + point.x <= 0)
            invertedWidth = true;
        }
        if(invertedHeight)
        {
            if(rectangle.y + point.y >= rectangle.y + rectangle.height)
            invertedHeight = false;
        }
        else
        {
            if(rectangle.height + point.y <= 0)
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
        selectionRectangle.setBounds(rectangle);
    }
}

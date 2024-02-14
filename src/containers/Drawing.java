/**
 * Függvények:
 * 
 * Drawing():
 *     A Drawing osztály konstruktora. Inicializálja a rajzobjektumot.
 * 
 * setBrush(Brush brush):
 *     Beállítja a rajzobjektum ecsetét a megadott ecsetre.
 * 
 * addPoint(Point point):
 *     Hozzáad egy pontot a rajzobjektum görbéjéhez.
 * 
 * getColor():
 *     Visszaadja a rajzobjektum színét.
 * 
 * getStroke():
 *     Visszaadja a rajzobjektum ecsetvastagságát.
 * 
 * getCurve():
 *     Visszaadja a rajzobjektum görbéjét.
 * 
 * initializeBrush():
 *     Inicializálja az ecsetet a jelenlegi ecsetvastagsággal.
 * 
 * isCollided(Point point):
 *     Megvizsgálja, hogy a megadott pont ütközik-e a rajzobjektum területével.
 * 
 * erase(Canvas canvas):
 *     Törli a rajzobjektumot a megadott vászonról.
 * 
 */
package containers;

import java.util.ArrayList;
import additions.Brush;
import additions.CanvasActivity;
import panels.Canvas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

public class Drawing implements CanvasActivity, Serializable {
    private Brush brush;
    private Rectangle selectionRectangle;
    private ArrayList<Point> curve;

    public Drawing()
    {
        curve = new ArrayList<Point>();
        brush = new Brush();
    }

    public void setBrush(Brush brush)
    {
        this.brush = brush;
    }

    public void addPoint(Point point)
    {
        curve.add(point);
    }

    public Color getColor()
    {
        return brush.getColor();
    }

    public BasicStroke getStroke()
    {
        return brush.getStroke();
    }

    public ArrayList<Point> getCurve()
    {
        return curve;
    }

    public void initializeBrush()
    {
        brush.setStroke(new BasicStroke(brush.getCurrentStroke()));
    }

    @Override
    public boolean isCollided(Point point) {
        int radius;
        if(brush.getStroke().getLineWidth() > 5)
        radius = Float.valueOf(brush.getStroke().getLineWidth()).intValue();
        else
        radius = 5;
        for (Point drawPoints : curve) {
            if(point.x >= drawPoints.x - radius && point.x <= drawPoints.x + radius && point.y >= drawPoints.y - radius && point.y <= drawPoints.y + radius)
              return true;
        }
        return false;
    }

    @Override
    public void erase(Canvas canvas) {
        canvas.getCurves().remove(this);
    }

    @Override
    public void setSelected() {
        Point minPoint = getMinCoordinate();
        Point maxPoint = getMaxCoordinate();

        selectionRectangle = new Rectangle(minPoint.x, minPoint.y, maxPoint.x - minPoint.x, maxPoint.y - minPoint.y);
    }

        @Override
    public void resetSelected() {
        selectionRectangle = null;
    }

    @Override
    public boolean isCollided(Rectangle rectangle) {
        for (Point drawPoints : curve) {
            if(rectangle.contains(drawPoints))
              return true;
        }
        return false;
    }

    @Override
    public void moveCanvasObject(Point point) {
        for (Point curvePoint : curve) {
            curvePoint.x += point.x;
            curvePoint.y += point.y;
        }
        selectionRectangle.x += point.x;
        selectionRectangle.y += point.y;
    }

    @Override
    public void resizeCanvasObject(Point point) {

    }

    @Override
    public Rectangle getSelectedBounds() {
        return selectionRectangle;
    }

    @Override
    public String toString()
    {
        return "Draw";
    }

    @Override
    public void placeItself(Canvas canvas) {
        canvas.addCurve(this);
    }

    public void draw(Graphics2D g2)
    {
        g2.setColor(getColor());
        g2.setStroke(getStroke());
        for(int i = 1; i < curve.size(); i++)
        {
            int x1 = curve.get(i - 1).x;
            int y1 = curve.get(i - 1).y;
            int x2 = curve.get(i).x;
            int y2 = curve.get(i).y;
            g2.drawLine(x1, y1, x2, y2);
        }
        if(selectionRectangle != null)
        {
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(Canvas.getSelectionWidth()));
            g2.draw(selectionRectangle);
        }
    }

    private Point getMinCoordinate()
    {
        int minX = curve.get(0).x;
        int minY = curve.get(0).y;
        for (Point point : curve) {
            if(point.x < minX)
            minX = point.x;
            if(point.y < minY)
            minY = point.y;
        }
        int offset = Canvas.getSelectionWidth();
        return new Point(minX - offset, minY - offset);
    }

    private Point getMaxCoordinate()
    {
        int maxX = curve.get(0).x;
        int maxY = curve.get(0).y;
        for (Point point : curve) {
            if(point.x > maxX)
            maxX = point.x;
            if(point.y > maxY)
            maxY = point.y;
        }
        int offset = Canvas.getSelectionWidth();
        return new Point(maxX + offset, maxY + offset);
    }
}

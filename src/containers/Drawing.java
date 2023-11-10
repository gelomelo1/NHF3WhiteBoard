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
import java.awt.Point;
import java.io.Serializable;

public class Drawing implements CanvasActivity, Serializable {
    private Brush brush;
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
}

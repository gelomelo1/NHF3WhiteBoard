/**
 * Függvények:
 * 
 * DefaultCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu, boolean isMenuUpdateable):
 *     A DefaultCanvasMode osztály konstruktora. Inicializálja az alapvető tulajdonságokat és beállítja a vásznat, egérfigyelőt, és tulajdonságmenüt.
 * 
 * DefaultCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu):
 *     A DefaultCanvasMode osztály konstruktora. Hívja a második konstruktort, az isMenuUpdateable értékét true-ra állítva.
 * 
 * initCanvasMode(Canvas canvas, MouseAdapter mouseListener, ToolPropertiesMenu toolPropertiesMenu, boolean isMenuUpdateable):
 *     Inicializálja az osztály alapvető tulajdonságait: vászon, egérfigyelő, tulajdonságmenü és a menü frissíthetőségét.
 * 
 * getCanvas():
 *     Visszaadja a kezelt vásznat.
 * 
 * setMouseListener(MouseAdapter mouseListener):
 *     Beállítja az egérfigyelőt a vászonhoz.
 * 
 * Move(Point point):
 *     Elmozgatja a vásznat a megadott pont mennyiségével, figyelembe véve a vászon szélein való mozgást.
 * 
 * update():
 *     Frissíti a menüt a vászonon.
 * 
 * setMousePos(Point point):
 *     Beállítja az egér pozícióját a vásznon.
 * 
 * getIsMenuUpdateable():
 *     Visszaadja, hogy a menü frissíthető-e.
 * 
 */
package canvasmodes;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JTextArea;
import additions.CanvasActivity;
import additions.SelectionRectangle;
import listeners.DefaultCanvasModeListener;
import panels.Canvas;
import panels.ToolPropertiesMenu;

public class DefaultCanvasMode {

    private Canvas canvas;
    private boolean isMenuUpdateable;
    private boolean isSelectionModeActive = false;

    protected DefaultCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu, boolean isMenuUpdateable)
    {
        initCanvasMode(canvas, new DefaultCanvasModeListener(this), toolPropertiesMenu, isMenuUpdateable);
    }

     public DefaultCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu)
    {
        this(canvas, toolPropertiesMenu, true);
    }

    private void initCanvasMode(Canvas canvas, MouseAdapter mouseListener, ToolPropertiesMenu toolPropertiesMenu, boolean isMenuUpdateable)
    {
        this.canvas = canvas;
        this.canvas.setMouseListener(mouseListener);
        this.canvas.setToolPropertiesMenu(toolPropertiesMenu);
        this.isMenuUpdateable = isMenuUpdateable;
        resetSelection();
    }

    private SelectionRectangle getSumRectangle(ArrayList<CanvasActivity> selectedActivities)
    {
        int space = 5;
        int minX = selectedActivities.get(0).getSelectedBounds().x;
        int minY = selectedActivities.get(0).getSelectedBounds().y;
        int maxX = 0;
        int maxY = 0;
        for (CanvasActivity canvasActivity : selectedActivities) {
            Rectangle rectangle = canvasActivity.getSelectedBounds();
            if(rectangle.x < minX)
            minX = rectangle.x;
            if(rectangle.y < minY)
            minY = rectangle.y;
            if(rectangle.x + rectangle.width > maxX)
            maxX = rectangle.x + rectangle.width;
            if(rectangle.y + rectangle.height > maxY)
            maxY = rectangle.y + rectangle.height;
        }
        minX -= space;
        minY -= space;
        maxX = maxX - minX + space;
        maxY = maxY - minY + space;
        return new SelectionRectangle(new Rectangle(minX, minY, maxX, maxY));
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public void setMouseListener(MouseAdapter mouseListener)
    {
        canvas.setMouseListener(mouseListener);
    }

    public void Move(Point point)
    {
        int x = canvas.getX();
        int y = canvas.getY();
        if(Double.valueOf(canvas.getX() + point.getX()).intValue() <= 0 && Double.valueOf(canvas.getX() + point.getX()).intValue() >= -canvas.getMaxWidth() + canvas.getCanvasLayout().getWidth())
        x = Double.valueOf(canvas.getX() + point.getX()).intValue();
        if(Double.valueOf( canvas.getY() + point.getY()).intValue() <= 0 && Double.valueOf( canvas.getY() + point.getY()).intValue() >= -canvas.getMaxHeight() + canvas.getCanvasLayout().getHeight())
        y = Double.valueOf( canvas.getY() + point.getY()).intValue();
        canvas.setLocation(x, y);
    }

    public void update()
    {
        canvas.updateMenu();
    }

    public void setMousePos(Point point)
    {
        canvas.setMousePos(point);
    }

    public boolean getIsMenuUpdateable()
    {
        return isMenuUpdateable;
    }

    public boolean Selection(Rectangle rectangle)
    {
        canvas.setSelectionRectangle(new SelectionRectangle(rectangle));
        boolean activityAdded = false;
        Component focusedComp = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        canvas.requestFocusInWindow();
        if(focusedComp instanceof JTextArea)
        {
        focusedComp.setEnabled(false);
        focusedComp.setEnabled(true);
        }
        for(int i = 0; i < canvas.getSelectedCanvasActivities().size(); i++)
        {
            if(!canvas.getSelectedCanvasActivities().get(i).isCollided(rectangle))
            canvas.removeSelectedCanvasActivity(canvas.getSelectedCanvasActivities().get(i));
        }
        for (CanvasActivity canvasActivity : canvas.getCanvasObjects()) {
            if(!canvas.getSelectedCanvasActivities().contains(canvasActivity) && canvasActivity.isCollided(rectangle))
            {
                canvas.addSelectedCanvasActivity(canvasActivity);
                activityAdded = true;
            }
        }
        canvas.repaint();
        return activityAdded;
    }

    public void confirmSelection()
    {
        if(canvas.getSelectedCanvasActivities().isEmpty())
            canvas.setSelectionRectangle(null);
        else
        {
            canvas.setSelectionRectangle(getSumRectangle(canvas.getSelectedCanvasActivities()));
            isSelectionModeActive = true;
        }
        canvas.repaint();
    }

    public void resetSelection()
    {
        isSelectionModeActive = false;
        canvas.resetActivitiesSelection();
        canvas.setSelectionRectangle(null);
        canvas.repaint();
    }

    public boolean getIsSelectionModeActive()
    {
        return isSelectionModeActive;
    }

    public void moveObjects(Point point)
    {
        for (CanvasActivity canvasActivity : canvas.getSelectedCanvasActivities()) {
            canvasActivity.moveCanvasObject(point);
        }
        canvas.getSelectionRectangle().moveCanvasObject(point);
        canvas.repaint();
    }

    public void resize(Point point)
    {
            for (CanvasActivity canvasActivity : canvas.getSelectedCanvasActivities()) {
            canvasActivity.resizeCanvasObject(point);
            canvasActivity.moveCanvasObject(point);
        }
        canvas.getSelectionRectangle().resizeCanvasObject(point);
        canvas.repaint();
    }

    public boolean isResize(Point point)
    {
        Rectangle selectionRectangle = canvas.getSelectionRectangle().getSelectionRectangle();
        int radius = 5;
        if(selectionRectangle != null)
        {
            int stroke = (int)canvas.getSelectionRectangle().getSize();
            int x1 = selectionRectangle.x;
            int x2 = selectionRectangle.x + selectionRectangle.width;
            int y1 = selectionRectangle.y;
            int y2 = selectionRectangle.y + selectionRectangle.height;
            if((((point.x >= x1 - radius && point.x <= x1 + stroke + radius) || (point.x >= x2 - stroke - radius && point.x <= x2 + radius)) && (point.y >= y1 && point.y <= y2)) || (((point.y >= y1 - radius && point.y <= y1 + stroke + radius) || (point.y >= y2 - stroke - radius && point.y <= y2 + radius)) && (point.x >= x1 && point.x <= x2)))
            return true;
        }
        return false;
    }

}

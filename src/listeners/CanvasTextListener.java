/**
 * Függvények:
 * 
 * CanvasTextListener(TextCanvasMode textCanvasMode):
 *     A CanvasTextListener osztály konstruktora. Beállítja a kapott TextCanvasMode-ot az osztály attribútumaként.
 * 
 * focusGained(FocusEvent e):
 *     A fókusz megszerzésekor meghívódó eseménykezelő. Az esemény során beállítja a kapott CanvasText fókuszát a
 *     textCanvasMode számára, majd frissíti azt a update() metódus segítségével.
 * 
 * focusLost(FocusEvent e):
 *     A fókusz elvesztésekor meghívódó eseménykezelő. Nincs különleges művelet a fókusz elvesztésekor.
 * 
 * getCanvasTextMode():
 *     Visszaadja a hozzátartozó TextCanvasMode-ot.
 * 
 */
package listeners;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import canvasmodes.DefaultCanvasMode;
import canvasmodes.TextCanvasMode;
import containers.CanvasText;

public class CanvasTextListener extends MouseAdapter {

    private DefaultCanvasMode defaultCanvasMode;
    private boolean isDefaultMode;

    public CanvasTextListener(TextCanvasMode defaultCanvasMode, boolean isDefaultMode)
    {
        this.defaultCanvasMode = defaultCanvasMode;
        this.isDefaultMode = isDefaultMode;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        defaultCanvasMode.resetSelection();
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            if(e.getClickCount() == 1)
            {
                defaultCanvasMode.setTextFocus((CanvasText)e.getSource());
            }
            else if(e.getClickCount() == 2 && isDefaultMode)
            {
                CanvasText canvasText = (CanvasText)e.getSource();
                defaultCanvasMode.Selection(new Rectangle(canvasText.getX(), canvasText.getY(), 1, 1));
                defaultCanvasMode.confirmSelection();
            }
        }
        defaultCanvasMode.update();
    }

    public void setIsDefaultMode(boolean isDefaultMode)
    {
        this.isDefaultMode = isDefaultMode;
    }

    public void setDefaultCanvasMode(DefaultCanvasMode defaultCanvasMode)
    {
        this.defaultCanvasMode = defaultCanvasMode;
    }
    
}

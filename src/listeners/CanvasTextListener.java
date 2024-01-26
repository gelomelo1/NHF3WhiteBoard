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

    public CanvasTextListener(TextCanvasMode defaultCanvasMode)
    {
        this.defaultCanvasMode = defaultCanvasMode;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        defaultCanvasMode.resetSelection();
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            if(e.getClickCount() == 1 && defaultCanvasMode instanceof TextCanvasMode)
            {
                TextCanvasMode textCanvasMode = (TextCanvasMode) defaultCanvasMode;
                textCanvasMode.setTextFocus((CanvasText)e.getSource());
            }
            else if(e.getClickCount() == 2 && defaultCanvasMode instanceof DefaultCanvasMode)
            {
                CanvasText canvasText = (CanvasText)e.getSource();
                defaultCanvasMode.Selection(new Rectangle(canvasText.getX(), canvasText.getY(), 1, 1));
                defaultCanvasMode.confirmSelection();
            }
        }
        defaultCanvasMode.update();
    }

    public void setDefaultCanvasMode(DefaultCanvasMode defaultCanvasMode)
    {
        this.defaultCanvasMode = defaultCanvasMode;
    }
    
}

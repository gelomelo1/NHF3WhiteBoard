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

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import canvasmodes.TextCanvasMode;
import containers.CanvasText;

public class CanvasTextListener implements FocusListener {

    private TextCanvasMode textCanvasMode;

    public CanvasTextListener(TextCanvasMode textCanvasMode)
    {
        this.textCanvasMode = textCanvasMode;
    }

    @Override
    public void focusGained(FocusEvent e) {
        textCanvasMode.resetSelection();
        textCanvasMode.setTextFocus((CanvasText)e.getSource());
        textCanvasMode.update();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
    
}

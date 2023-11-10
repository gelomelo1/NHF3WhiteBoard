/**
 * Függvények:
 * 
 * FrameMenus(String name, JFrame mainFrame, int width, int height):
 *     Az abstract FrameMenus osztály konstruktora. Inicializálja az ablakot a megadott paraméterek alapján,
 *     és beállítja a főablak letiltását. Hozzáad egy ablakkezelőt az ablak bezárásához.
 * 
 * FrameMenusListener:
 *     Az ablakkezelő osztály, amely a FrameMenus osztályhoz tartozik.
 * 
 * windowClosed(WindowEvent e):
 *     Az ablak bezárásakor hívódik meg. Engedélyezi a főablakot.
 * 
 * windowClosing(WindowEvent e):
 *     Az ablak bezárásakor hívódik meg. Engedélyezi a főablakot.
 */
package panels;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public abstract class FrameMenus extends JFrame {
   
    private JFrame mainFrame;

    public FrameMenus(String name, JFrame mainFrame, int width, int height)
    {
        super(name);
        this.mainFrame = mainFrame;
        this.mainFrame.setEnabled(false);
        setSize(width, height);
        setVisible(true);
        addWindowListener(new FrameMenusListener());
    }

    private class FrameMenusListener extends WindowAdapter
    {
        @Override
        public void windowClosed(WindowEvent e) {
            mainFrame.setEnabled(true);
        }

        @Override
        public void windowClosing(WindowEvent e) {
            mainFrame.setEnabled(true);
        }
    }
}

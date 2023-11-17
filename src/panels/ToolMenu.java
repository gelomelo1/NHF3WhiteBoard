/**
 * Függvények:
 * 
 * ToolMenu(JFrame jf, ModesController modesController):
 *     A ToolMenu osztály konstruktora. Inicializálja a menüt a megadott módvezérlővel, majd hozzáadja azt a megadott JFrame-hez.
 * 
 * initMenu(ModesController modesController):
 *     Inicializálja a menüt a megadott módvezérlővel. Beállítja a menü elrendezését BoxLayout-ra (függőleges). Beállítja a háttérszínt és az előnyomott méretet.
 *     Létrehozza és hozzáadja a menühöz a ToolButtonsMenu-t.
 * 
 * setToolPropertiesMenu(JPanel toolPropertiesMenu):
 *     Beállítja a ToolPropertiesMenu-t a megadott panelre. Ha már volt beállítva egy ToolPropertiesMenu, eltávolítja azt a menüből, majd hozzáadja az újat.
 *     Frissíti a menüt (revalidate).
 * 
 */
package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controllers.ModesController;

public class ToolMenu extends JPanel {

    //base properties
    private int toolMenuWidth = 200;

    //toolmenu items
    private JPanel toolButtonsMenu;
    private JPanel toolPropertiesMenu;

    public ToolMenu(JFrame jf, ModesController modesController)
    {
        initMenu(modesController);
        jf.add(this, BorderLayout.WEST);

    }

    private void initMenu(ModesController modesController)
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(toolMenuWidth, 0));
        toolButtonsMenu = new ToolButtonsMenu(modesController);
        add(toolButtonsMenu);
    }

    public void setToolPropertiesMenu(JPanel toolPropertiesMenu)
    {
        if(this.toolPropertiesMenu != null)
        {
           remove(this.toolPropertiesMenu);
        }
        this.toolPropertiesMenu = toolPropertiesMenu;
        add(this.toolPropertiesMenu);
        revalidate();
    }
}

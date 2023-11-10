/**
 * Függvények:
 * 
 * ToolButtonsMenu(ModesController modesController):
 *     A ToolButtonsMenu osztály konstruktora. Inicializálja a menüt és a gombokat a megadott módvezérlővel.
 * 
 * initMenu(ModesController modesController):
 *     Inicializálja a menüt a megadott módvezérlővel. Beállítja a menü elrendezését GridLayout-ra (egy oszlopos).
 * 
 * initButtons():
 *     Inicializálja és hozzáadja a gombokat a menühöz. Minden gombhoz létrehoz egy ModeChangerButtonListener-t, majd hozzárendeli a gombhoz és hozzáadja 
 *     a menühöz.
 * 
 * assignButton(ModeChangerButton modeChangerButton, ModeChangerButtonListener modeChangerButtonListener):
 *     Hozzárendeli a megadott gombot és hallgatót a menühöz. Beállítja a hallgató gomblistáját, majd hozzárendeli a hallgatót a gombhoz, és hozzáadja a gombot 
 *     a menühöz.
 * 
 * getModesController():
 *     Visszaadja a menühöz tartozó módvezérlőt.
 * 
 */
package panels;

import java.awt.GridLayout;
import javax.swing.JPanel;
import additions.ModeChangerButton;
import additions.ModeChangerButton.ModesChanger;
import controllers.ModesController;
import listeners.ModeChangerButtonListener;
import uiholders.Menu;

public class ToolButtonsMenu extends JPanel {

    private ModesController modesController;

    public ToolButtonsMenu(ModesController modesController)
    {
        initMenu(modesController);
        initButtons();
    }

    private void initMenu(ModesController modesController)
    {
        this.modesController = modesController;
        setLayout(new GridLayout(0, 1));
    }

    private void initButtons()
    {
        ModeChangerButtonListener modeChangerButtonListener = new ModeChangerButtonListener();
        ModeChangerButton modeChangerButton = new ModeChangerButton(Menu.getResourceFiles().get("Move"), this, ModesChanger.Move);
        assignButton(modeChangerButton, modeChangerButtonListener);
        modeChangerButton = new ModeChangerButton(Menu.getResourceFiles().get("Draw"), this, ModesChanger.Draw);
        assignButton(modeChangerButton, modeChangerButtonListener);
        modeChangerButton = new ModeChangerButton(Menu.getResourceFiles().get("Erase"), this, ModesChanger.Erase);
        assignButton(modeChangerButton, modeChangerButtonListener);
        modeChangerButton = new ModeChangerButton(Menu.getResourceFiles().get("Text"), this, ModesChanger.Text);
        assignButton(modeChangerButton, modeChangerButtonListener);
        modeChangerButton = new ModeChangerButton(Menu.getResourceFiles().get("Image"), this, ModesChanger.Image);
        assignButton(modeChangerButton, modeChangerButtonListener);
    }

    private void assignButton(ModeChangerButton modeChangerButton, ModeChangerButtonListener modeChangerButtonListener)
    {
        modeChangerButtonListener.addButton(modeChangerButton);
        modeChangerButton.addActionListener(modeChangerButtonListener);
        add(modeChangerButton);
    }

    public ModesController getModesController()
    {
        return modesController;
    }
}

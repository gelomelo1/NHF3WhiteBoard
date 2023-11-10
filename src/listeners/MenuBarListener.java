/**
 * Függvények:
 * 
 * MenuBarListener(ArrayList<FileMenu> menuPoints, ArrayList<FileMenuItem> menuItems):
 *     Az osztály konstruktora. Beállítja a menüpontokat és menüelemeket.
 * 
 * mouseClicked(MouseEvent e):
 *     Az egér kattintás eseményére meghívódó metódus. Megkeresi a kattintás helyén lévő menüpontot, majd végrehajtja a hozzá
 *     rendelt műveletet a menuPoints listában található objektumon.
 * 
 * actionPerformed(ActionEvent e):
 *     Az eseményre meghívódó metódus. Megkeresi az eseményt kiváltó menüelemet, majd végrehajtja a hozzá rendelt műveletet a
 *     menuItems listában található objektumon.
 * 
 */
package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import additions.FileMenu;
import additions.FileMenuItem;

public class MenuBarListener extends MouseAdapter implements ActionListener {

    private ArrayList<FileMenu> menuPoints = new ArrayList<FileMenu>();
    private ArrayList<FileMenuItem> menuItems = new ArrayList<FileMenuItem>();

    public MenuBarListener(ArrayList<FileMenu> menuPoints, ArrayList<FileMenuItem> menuItems)
    {
        this.menuPoints = menuPoints;
        this.menuItems = menuItems;
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
        for (FileMenu menu : menuPoints) {
            if(e.getSource() == menu)
            {
                menu.operate();
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (FileMenuItem fileMenuItem : menuItems) {
            if(e.getSource() == fileMenuItem)
            {
                fileMenuItem.operate();
                break;
            }
        }
    }
    
}

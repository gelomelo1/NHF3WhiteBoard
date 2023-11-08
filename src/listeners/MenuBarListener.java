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

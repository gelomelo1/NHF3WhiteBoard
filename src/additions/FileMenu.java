package additions;

import javax.swing.JMenu;
import panels.MenuBar;
import uiholders.Menu;

public class FileMenu extends JMenu {

    public enum MenuOperation
    {
        New,
        Load,
        Delete,
        Help
    }

    private MenuOperation operation;
    private MenuBar menuBar;

    public FileMenu(String name, MenuBar menuBar, MenuOperation operation)
    {
        super(name);
        this.menuBar = menuBar;
        this.operation = operation;
    }

    public void operate()
    {
        if(operation != null)
        {
        switch(operation)
        {
            case New:
            {
                if(Menu.showMoveOnConfirm() == 0)
                menuBar.createNew();
                break;
            }
            case Load:
            {
                if(Menu.showMoveOnConfirm() == 0)
                menuBar.startLoad();
                break;
            }
            case Delete:
            {
                menuBar.startDelete();
                break;
            }
            case Help:
            {
                menuBar.help();
                break;
            }
        }
        }
    }
}


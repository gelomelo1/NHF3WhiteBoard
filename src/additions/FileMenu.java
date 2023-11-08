package additions;

import javax.swing.JMenu;
import panels.MenuBar;

public class FileMenu extends JMenu {

    public enum MenuOperation
    {
        New,
        Load
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
                menuBar.createNew();
                break;
            }
            case Load:
            {
                System.out.println("Load");
                break;
            }
        }
        }
    }
}


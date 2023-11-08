package additions;

import panels.MenuBar;
import javax.swing.JMenuItem;

public class FileMenuItem extends JMenuItem {
    
    public enum ItemOperation
    {
        Save,
        SaveAs
    }

    private ItemOperation operation;
    private MenuBar menuBar;

    public FileMenuItem(String name, MenuBar menuBar, ItemOperation operation)
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
                case Save:
                {
                    menuBar.Save(false);
                    break;
                }
                case SaveAs:
                {
                    menuBar.Save(true);
                    break;
                }
            }
        }
    }
}

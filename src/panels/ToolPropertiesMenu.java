package panels;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public abstract class ToolPropertiesMenu extends JPanel {
    
    public ToolPropertiesMenu()
    {
        initMenu();
    }

    private void initMenu()
    {
        setBorder(BorderFactory.createTitledBorder("Properties"));
    }
}

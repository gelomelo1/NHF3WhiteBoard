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

    public void initMenu(ModesController modesController)
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.blue);
        setPreferredSize(new Dimension(toolMenuWidth, 0));
        toolButtonsMenu = new ToolButtonsMenu(modesController);
        add(toolButtonsMenu);
        toolPropertiesMenu = null;
    }
}

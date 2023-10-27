package uiholders;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import controllers.ModesController;
import panels.MenuBar;

public class Menu {
    //Some base attributes
    private int width = 1280;
    private int heigth = 960;

    //JFrames and Panels for the layout
    private JFrame jf;
    private JMenuBar menuBar;

    //Controller
    private ModesController modesController;

    public Menu()
    {
        initApp();
        initPanels();
        jf.setVisible(true);
    }

    private void initApp()
    {
        jf = new JFrame("Whiteboard");
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setSize(width, heigth);
    }

    private void initPanels()
    {
        menuBar = new MenuBar(jf);
        modesController = new ModesController(this, jf);
    }
}

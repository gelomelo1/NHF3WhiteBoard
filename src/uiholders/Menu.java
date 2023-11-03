package uiholders;

import java.io.File;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import controllers.ModesController;
import fileios.FileHandler;
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
        initFolders();
    }

    private void initPanels()
    {
        menuBar = new MenuBar(jf);
        modesController = new ModesController(this, jf);
    }

    private void initFolders()
    {
        if(!(FileHandler.createFolder("Temp") && FileHandler.createFolder("Boards") && FileHandler.isFileExist("Resources")))
        {
           JOptionPane.showMessageDialog(null, "Error occured while verifying folders!", "Error", JOptionPane.ERROR_MESSAGE);
           Exit();
        }
        deleteTempFolder();
    }

    public void deleteTempFolder()
    {
        File dir = Paths.get("").toAbsolutePath().resolve("Temp").toFile();
        File[] files = dir.listFiles();
        for (File file : files) {
            FileHandler.deleteFile(file.getAbsolutePath());
        }
    }

    public static void Exit()
    {
        System.exit(0);
    }
}

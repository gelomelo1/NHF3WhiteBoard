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
    private static int width = 1280;
    private static int heigth = 960;
    private static String tempFolder = "Temp";
    private static String boardsFolder = "Boards";
    private static String resourcesFolder = "Resources";

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
        modesController = new ModesController(this, jf);
        menuBar = new MenuBar(jf, modesController.getCanvas());
    }

    private void initFolders()
    {
        if(!(FileHandler.createFolder(tempFolder) && FileHandler.createFolder(boardsFolder) && FileHandler.isFileExist(resourcesFolder)))
        {
           JOptionPane.showMessageDialog(null, "Error occured while verifying folders!", "Error", JOptionPane.ERROR_MESSAGE);
           Exit();
        }
        deleteTempFolder();
    }

    public static void deleteTempFolder()
    {
        File dir = Paths.get("").toAbsolutePath().resolve(tempFolder).toFile();
        File[] files = dir.listFiles();
        for (File file : files) {
            FileHandler.deleteFile(file.getAbsolutePath());
        }
    }

    public static String getTempFolder()
    {
        return tempFolder;
    }

    public static String getBoardsFolder()
    {
        return boardsFolder;
    }

    public static String getResourcesFolder()
    {
        return resourcesFolder;
    }

    public static void Exit()
    {
        System.exit(0);
    }
}

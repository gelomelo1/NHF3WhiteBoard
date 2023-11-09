package uiholders;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
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
    private static HashMap<String, String> resourceFiles = new HashMap<String, String>();

    static
    {
        String path = Paths.get("").toAbsolutePath().resolve(resourcesFolder).toString();
        addPathToMap("Move", path, "png");
        addPathToMap("Draw", path, "png");
        addPathToMap("Erase", path, "png");
        addPathToMap("Text", path, "png");
        addPathToMap("Image", path, "png");
        addPathToMap("CannotLoadImage", path, "png");
        addPathToMap("UserDocumentation", path, "pdf");
    }

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
        jf.setDefaultCloseOperation(jf.DO_NOTHING_ON_CLOSE);
        jf.setSize(width, heigth);
        jf.addWindowListener(new MainFrameListener());
        initFolders();
    }

    private void initPanels()
    {
        modesController = new ModesController(this, jf);
        menuBar = new MenuBar(jf, modesController.getCanvas());
    }

    private void initFolders()
    {
        if(!(FileHandler.createFolder(tempFolder) && FileHandler.createFolder(boardsFolder) && FileHandler.isFileExist(resourcesFolder) && checkResourceFiles()))
        {
           JOptionPane.showMessageDialog(null, "Error occured while verifying folders!", "Error", JOptionPane.ERROR_MESSAGE);
           Exit();
        }
        deleteTempFolder();
    }

    private boolean checkResourceFiles()
    {
        for(HashMap.Entry<String, String> set : resourceFiles.entrySet())
        {
            if(!FileHandler.isFileExist(set.getValue()))
            return false;
        }
        return true;
    }

    private static void addPathToMap(String key, String folder, String extension)
    {
        resourceFiles.put(key, folder + "\\" + key + "." + extension);
    }

    private class MainFrameListener extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent e) {
            if(showMoveOnConfirm() == 0)
                e.getWindow().dispose();
        }
    }

    public static void deleteTempFolder()
    {
        File dir = Paths.get("").toAbsolutePath().resolve(tempFolder).toFile();
        File[] files = dir.listFiles();
        for (File file : files) {
            FileHandler.deleteFile(file.getAbsolutePath());
        }
    }

    public static int showMoveOnConfirm()
    {
        return JOptionPane.showConfirmDialog(null, "Are you sure, you want to continue without saving?", "Continue", JOptionPane.YES_NO_OPTION);
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

    public static HashMap<String, String> getResourceFiles()
    {
        return resourceFiles;
    }

    public static void Exit()
    {
        System.exit(0);
    }
}

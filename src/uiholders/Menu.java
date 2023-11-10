/**
 * Függvények:
 * 
 * Menu():
 * A Menu osztály konstruktora inicializálja a működéshez szükséges változókat.
 * 
 * initApp():
 *     A függvény létrehoz egy JFrame objektumot a "Whiteboard" címmel,
 *     beállítja a kilépési működést és a méretet, majd hozzáad egy ablakfigyelőt.
 *     Az initFolders() függvényt is meghívja.
 * 
 * initPanels():
 *     Inicializálja a modesController-t és a menuBar-t a JFrame és a canvas referenciajával.
 * 
 * initFolders():
 *     Ellenőrzi a szükséges mappák létezését és sikeres létrehozását.
 *     Ha valamelyik létrehozás vagy ellenőrzés sikertelen, hibaüzenet jelenik meg, és kilép a program.
 *     A deleteTempFolder() függvényt is meghívja.
 * 
 * checkResourceFiles():
 *     Ellenőrzi, hogy az összes szükséges erőforrásfájl létezik-e a megadott elérési útvonalakon.
 *     Ha bármelyik hiányzik, false-szal tér vissza, különben true-val.
 * 
 * addPathToMap(String key, String folder, String extension):
 *     Hozzáad egy kulcs-érték párt a resourceFiles térképhez, ahol a kulcs a fájl neve, az érték pedig az elérési útvonal.
 * 
 * windowClosing(WindowEvent e):
 *     Ablak bezárásakor meghívódó eseménykezelő. Megjelenít egy megerősítő párbeszédpanelt,
 *     amely megkérdezi a felhasználót, hogy biztosan folytatja-e a kilépést mentés nélkül.
 *     Ha a válasz igen, akkor az ablak bezáródik.
 * 
 * deleteTempFolder():
 *     Törli a temporális mappát és annak tartalmát.
 * 
 * showMoveOnConfirm():
 *     Megjelenít egy megerősítő párbeszédpanelt a "Continue" címmel, ami megkérdezi a felhasználót,
 *     hogy biztosan folytatja-e a mentés nélküli kilépést. Visszaadja a felhasználó válaszát.
 * 
 * getTempFolder():
 *     Visszaadja a temporális mappa elérési útvonalát.
 * 
 * getBoardsFolder():
 *     Visszaadja a "Boards" mappa elérési útvonalát.
 * 
 * getResourcesFolder():
 *     Visszaadja a "Resources" mappa elérési útvonalát.
 * 
 * getResourceFiles():
 *     Visszaadja a resourceFiles térképet, amely tartalmazza az összes szükséges erőforrásfájl elérési útvonalát.
 * 
 * Exit():
 *     Kilép a programból.
 */
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

    private JFrame jf;
    private JMenuBar menuBar;
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

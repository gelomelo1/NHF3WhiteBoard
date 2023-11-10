/**
 * Függvények:
 * 
 * MenuBar(JFrame jf, Canvas canvas):
 *     A MenuBar osztály konstruktora. Inicializálja a menüpontokat, menüelemeket, szövegmezőket és eseménykezelőket.
 * 
 * initMenuPoints():
 *     Inicializálja a menüpontokat és menüelemeket, majd hozzáadja azokat a menüsorhoz. Beállítja az eseménykezelőket.
 * 
 * initMenuTexts():
 *     Inicializálja a szövegmezőket, hozzáadja azokat a menüsorhoz, és beállítja azok szerkeszthetőségét.
 * 
 * copyImages(String source, String destination):
 *     Másolja azokat a képeket a forrás mappából a cél mappába, amelyek megfelelnek a megadott kiterjesztésekre.
 * 
 * getJF():
 *     Visszaadja a JFrame-t, amelyhez a MenuBar tartozik.
 * 
 * changeTexts(String name, String description):
 *     Beállítja a szövegmezők szövegét a megadott névre és leírásra.
 * 
 * Save(boolean copy):
 *     Elindítja a mentés folyamatot. Ha a fájlnév üres vagy a másolás opció be van kapcsolva, megnyitja a SaveMenu-t, különben elmenti az állapotot.
 * 
 * saveObject(String name, String description, boolean copy):
 *     Elmenti a tábla állapotát a megadott név és leírás alapján. Ha a másolás opció be van kapcsolva, másolja a fájlokat a Temp mappából a Boards mappába.
 * 
 * createNew():
 *     Létrehoz egy új táblát, törli a Temp mappát és törli a szövegmezők tartalmát.
 * 
 * startLoad():
 *     Elindítja a betöltés folyamatát, megnyitja a LoadMenu-t.
 * 
 * loadObjects():
 *     Betölti az elmentett táblák listáját a Boards mappából.
 * 
 * load(SaveContainer container):
 *     Betölti a kiválasztott tábla állapotát a megadott SaveContainer alapján.
 * 
 * help():
 *     Megnyitja a felhasználói dokumentációt.
 * 
 * startDelete():
 *     Elindítja a törlés folyamatát, megnyitja a DeleteMenu-t.
 * 
 * delete(SaveContainer container):
 *     Törli a megadott táblát a Boards mappából.
 * 
 */
package panels;

import java.awt.Desktop;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import additions.FileMenu;
import additions.FileMenuItem;
import additions.FileMenu.MenuOperation;
import additions.FileMenuItem.ItemOperation;
import containers.SaveContainer;
import fileios.FileHandler;
import listeners.MenuBarListener;
import uiholders.Menu;

public class MenuBar extends JMenuBar {
    
    //menubar items
    private ArrayList<FileMenu> menuPoints;
    private ArrayList<FileMenuItem> menuItems;
    private ArrayList<JTextField> menuTexts;
    private JTextField fileName;
    private JTextField fileDescription;
    private MenuBarListener menuBarListener;
    private JFrame jf;
    private Canvas canvas;

    public MenuBar(JFrame jf, Canvas canvas)
    {
        this.jf = jf;
        this.canvas = canvas;
        initMenuPoints();
        initMenuTexts();
        jf.setJMenuBar(this);
    }

    private void initMenuPoints()
    {
        menuPoints = new ArrayList<FileMenu>();
        menuItems = new ArrayList<FileMenuItem>();
        menuPoints.add(new FileMenu("New", this, MenuOperation.New));
        FileMenu save = new FileMenu("Save", this, null);
        FileMenuItem fileMenuItem = new FileMenuItem("Save", this, ItemOperation.Save);
        menuItems.add(fileMenuItem);
        save.add(fileMenuItem);
        fileMenuItem = new FileMenuItem("Save as", this, ItemOperation.SaveAs);
        menuItems.add(fileMenuItem);
        save.add(fileMenuItem);
        menuPoints.add(save);
        menuPoints.add(new FileMenu("Load", this, MenuOperation.Load));
        menuPoints.add(new FileMenu("Delete", this, MenuOperation.Delete));
        menuPoints.add(new FileMenu("Help", this, MenuOperation.Help));
        add(menuPoints.get(0));
        add(menuPoints.get(1));
        add(menuPoints.get(2));
        add(menuPoints.get(3));
        add(menuPoints.get(4));
        menuBarListener = new MenuBarListener(menuPoints, menuItems);
        for (FileMenu menu : menuPoints) {
            menu.addMouseListener(menuBarListener);
        }
        for (FileMenuItem item : menuItems) {
            item.addActionListener(menuBarListener);
        }
    }

    private void initMenuTexts()
    {
        menuTexts = new ArrayList<JTextField>();
        fileName = new JTextField();
        fileName.setEditable(false);
        menuTexts.add(fileName);
        fileDescription = new JTextField(20);
        fileDescription.setEditable(false);
        menuTexts.add(fileDescription);
        add(new JLabel("FileName:"));
        add(menuTexts.get(0));
        add(new JLabel("Description:"));
        add(menuTexts.get(1));
    }

    private void copyImages(String source, String destination)
    {
        File[] images = FileHandler.getFolderFiles(source);
        String[] extensions = {"png", "jpg", "jpeg"};
        for (File file : images) {
            if(FileHandler.validExtension((source + "\\" + file.getName()).toString(), extensions))
                FileHandler.copyFile((source + "\\" + file.getName()).toString(), destination, null);
        }
    }

    public JFrame getJF()
    {
        return jf;
    }

    public void changeTexts(String name, String description)
    {
        fileName.setText(name);
        fileDescription.setText(description);
    }

    public void Save(boolean copy)
    {
        if(fileName.getText().equals("") || copy)
        {
            SaveMenu saveMenu = new SaveMenu(this);
        }
        else
        saveObject(fileName.getText(), fileDescription.getText(), false);
    }

    public void saveObject(String name, String description, boolean copy)
    {
        if(copy)
        FileHandler.createFolder(Paths.get("").toAbsolutePath().resolve(Menu.getBoardsFolder() + "\\" + name).toString());
        SaveContainer canvasData = canvas.saveCanvas();
        SaveContainer pack = new SaveContainer(canvasData.getImages(), canvasData.getTexts(), canvasData.getDrawings(), name, description);
        pack.changeImagesPath(Menu.getBoardsFolder() + "\\" + name);
        try {
        FileHandler.saveObject(Paths.get("").toAbsolutePath().resolve(Menu.getBoardsFolder() + "\\" + name + "\\" + name + ".ser").toString(), pack);
        copyImages((Paths.get("").toAbsolutePath().resolve(Menu.getTempFolder()).toString()) ,Menu.getBoardsFolder() + "\\" + name);
        changeTexts(name, description);
        JOptionPane.showMessageDialog(null, "Board saved successfully!", "Successful Save", JOptionPane.INFORMATION_MESSAGE);   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occured during save!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void createNew()
    {
        changeTexts("", "");
        Menu.deleteTempFolder();
        canvas.newCanvas();
    }

    public void startLoad()
    {
        LoadMenu loadMenu = new LoadMenu(this, loadObjects());
    }

    public ArrayList<SaveContainer> loadObjects()
    {
        ArrayList<SaveContainer> containers = new ArrayList<SaveContainer>();
        boolean showed = false;
        File[] boardFolders = FileHandler.getFolderFiles(Paths.get("").toAbsolutePath().resolve(Menu.getBoardsFolder()).toString());
        for(int i = 0; i < boardFolders.length; i++)
        {
            try {
               SaveContainer loadedContainer = (SaveContainer)FileHandler.loadObject(boardFolders[i].getAbsolutePath() + "\\" + boardFolders[i].getName() + ".ser");
               containers.add(loadedContainer);   
            } catch (Exception e) {
                if(!showed)
                {
                JOptionPane.showMessageDialog(null, "Failed to load all boards!", "Warning", JOptionPane.WARNING_MESSAGE);
                showed = true;
                }
            }
        }
        return containers;
    }

    public void load(SaveContainer container)
    {
        if(container != null)
        {
            Menu.deleteTempFolder();
            copyImages(Paths.get("").toAbsolutePath().resolve(Menu.getBoardsFolder() + "\\" + container.getName()).toString(), Paths.get("").toAbsolutePath().resolve(Menu.getTempFolder()).toString());
            changeTexts(container.getName(), container.getDescription());
            canvas.loadCanvas(container);
        }
    }

    public void help()
    {
        try {
        File file = new File(Menu.getResourceFiles().get("UserDocumentation"));
        Desktop.getDesktop().open(file);   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cannot open the file!", "Warning",  JOptionPane.WARNING_MESSAGE);
        }
    }

    public void startDelete()
    {
        DeleteMenu deleteMenu = new DeleteMenu(this, loadObjects());
    }

    public void delete(SaveContainer container)
    {
        String name = container.getName();
        if(fileName.getText().equals(name))
        changeTexts("", "");
        File folder = Paths.get("").toAbsolutePath().resolve(Menu.getBoardsFolder() + "\\" + name).toFile();
        File[] files = folder.listFiles();
        for (File file : files) {
            FileHandler.deleteFile(file.getAbsolutePath());
        }
        FileHandler.deleteFile(folder.getAbsolutePath());
    }
}

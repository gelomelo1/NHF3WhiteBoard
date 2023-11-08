package panels;

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
        add(menuPoints.get(0));
        add(menuPoints.get(1));
        add(menuPoints.get(2));
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

    public void turnOffMainFrame()
    {
        jf.setEnabled(false);
    }

    public void turnOnMainFrame()
    {
        jf.setEnabled(true);
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
        try {
        FileHandler.saveObject(Paths.get("").toAbsolutePath().resolve(Menu.getBoardsFolder() + "\\" + name + "\\" + name + ".ser").toString(), pack);
        changeTexts(name, description);   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occured during save!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createNew()
    {
        canvas.newCanvas();
    }
}
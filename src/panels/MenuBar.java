package panels;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

public class MenuBar extends JMenuBar {
    
    //base properties
    private int descriptionPrefferedWidth = 400;

    //menubar items
    private ArrayList<JMenu> menuPoints;
    private ArrayList<JTextField> menuTexts;
    public MenuBar(JFrame jf)
    {
        initMenuPoints();
        initMenuTexts();
        jf.setJMenuBar(this);
    }

    private void initMenuPoints()
    {
        menuPoints = new ArrayList<JMenu>();
        menuPoints.add(new JMenu("Save"));
        menuPoints.add(new JMenu("Load"));
        add(menuPoints.get(0));
        add(menuPoints.get(1));
    }

    private void initMenuTexts()
    {
        menuTexts = new ArrayList<JTextField>();
        menuTexts.add(new JTextField());
        menuTexts.add(new JTextField());
        add(new JLabel("FileName:"));
        add(menuTexts.get(0));
        add(new JLabel("Description:"));
        add(menuTexts.get(1));
        menuTexts.get(1).setPreferredSize(new Dimension(descriptionPrefferedWidth, 0));
    }
}

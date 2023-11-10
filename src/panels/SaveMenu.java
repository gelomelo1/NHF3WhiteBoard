/**
 * Függvények:
 * 
 * SaveMenu(MenuBar menuBar):
 *     A SaveMenu osztály konstruktora. Meghívja az ősosztály konstruktorát, majd inicializálja a szövegmezőket és gombokat.
 * 
 * initComponents(JPanel textPanel, JPanel buttonPanel):
 *     Inicializálja a szövegmezőket és a gombokat a megfelelő panelekhez. Beállítja a gomb eseménykezelőjét, hogy a megadott szabályoknak megfelelően működjön a mentés.
 * 
 * checkSaveRules():
 *     Ellenőrzi a mentési szabályokat: 
 *         - Ellenőrzi, hogy a megadott névvel már létezik-e tábla.
 *         - Ellenőrzi, hogy a név mező ki van-e töltve.
 *     Ha mindkét feltétel teljesül, visszatér igazzal, különben hamissal.
 * 
 */
package panels;

import java.awt.BorderLayout;
import java.io.File;
import java.nio.file.Paths;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import uiholders.Menu;

public class SaveMenu extends FrameMenus {
    
    private JTextField name;
    private JTextField desciption;
    private MenuBar menuBar;

    public SaveMenu(MenuBar menuBar)
    {
        super("Save", menuBar.getJF(), 400, 200);
        this.menuBar = menuBar;
        JPanel textPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        initComponents(textPanel, buttonPanel);
    }

    private void initComponents(JPanel textPanel, JPanel buttonPanel)
    {
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        textPanel.add(new JLabel("Name"));
        name = new JTextField();
        textPanel.add(name);
        textPanel.add(new JLabel("Description"));
        desciption = new JTextField();
        textPanel.add(desciption);
        JButton button = new JButton("Save");
        button.addActionListener(e -> {if(checkSaveRules()) { menuBar.saveObject(name.getText(), desciption.getText(), true); dispose();} });
        buttonPanel.add(button);
    }

    private boolean checkSaveRules()
    {
        File dir = Paths.get("").toAbsolutePath().resolve(Menu.getBoardsFolder()).toFile();
        File[] files = dir.listFiles();
        for (File file : files) {

            if(file.getName().equals(name.getText()))
            {
                JOptionPane.showMessageDialog(null, "Board with this name already exist!", "Information", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }
        if(name.getText().equals(""))
        {
                JOptionPane.showMessageDialog(null, "Board does not has a name!", "Information", JOptionPane.INFORMATION_MESSAGE);
                return false;
        }
        return true;
    }
}

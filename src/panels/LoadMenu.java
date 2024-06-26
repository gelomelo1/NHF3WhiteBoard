/**
 * Függvények:
 * 
 * LoadMenu(MenuBar menuBar, ArrayList<SaveContainer> containers):
 *     A LoadMenu osztály konstruktora. Az ősosztály konstruktorát hívja meg, inicializálja a komponenseket.
 * 
 * initComponents(JPanel listPanel, JPanel buttonPanel, ArrayList<SaveContainer> containers):
 *     Inicializálja a komponenseket, beleértve a listapanelt, a gombpanelt és a hozzájuk tartozó komponenseket.
 *     Beállítja a gomb eseménykezelőjét a mentett állapot betöltésére.
 * 
 * uploadList(ArrayList<SaveContainer> containers):
 *     Betölti az adott mentett állapotokat tartalmazó listát a JList-be és kijelöli az első elemet.
 * 
 */
package panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import containers.SaveContainer;

public class LoadMenu extends FrameMenus {
    
    private int width;
    private int height;
    private MenuBar menuBar;
    private JList<SaveContainer> boardList;
    private DefaultListModel<SaveContainer> defaultListModel;
    private SaveContainer selectedContainer = null;
    private JTextField description;
    
    public LoadMenu(MenuBar menuBar, ArrayList<SaveContainer> containers)
    {
        super("Load", menuBar.getJF(), 400, 400);
        this.menuBar = menuBar;
        width = 400;
        height = 400;
        JPanel listPanel = new JPanel();
        add(listPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        initComponents(listPanel, buttonPanel, containers);
    }

    private void initComponents(JPanel listPanel, JPanel buttonPanel, ArrayList<SaveContainer> containers)
    {
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
        listPanel.add(new JLabel("Boards"));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(width, (int)(height * 0.6)));
        listPanel.add(scrollPane);
        boardList = new JList<SaveContainer>();
        uploadList(containers);
        boardList.addListSelectionListener(e -> {selectedContainer = boardList.getSelectedValue(); description.setText(selectedContainer.getDescription());});
        scrollPane.setViewportView(boardList);
        listPanel.add(new JLabel("Description"));
        description = new JTextField(20);
        if(boardList.getSelectedValue() != null)
        description.setText(boardList.getSelectedValue().getDescription());
        description.setEditable(false);
        listPanel.add(description);
        JButton button = new JButton("Load");
        button.addActionListener(e -> {menuBar.load(selectedContainer); dispose();});
        buttonPanel.add(button);
    }

    private void uploadList(ArrayList<SaveContainer> containers)
    {
        defaultListModel = new DefaultListModel<SaveContainer>();
        for (SaveContainer saveContainer : containers) {
            defaultListModel.addElement(saveContainer);
        }
        boardList.setModel(defaultListModel);
        boardList.setSelectedIndex(0);
        selectedContainer = boardList.getSelectedValue();
    }
}

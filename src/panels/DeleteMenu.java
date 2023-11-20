/**
 * Függvények:
 * 
 * DeleteMenu(MenuBar menuBar, ArrayList<SaveContainer> containers):
 *     A DeleteMenu osztály konstruktora. Inicializálja a komponenseket és a megjelenítendő ablakot.
 * 
 * initComponents(JPanel listPanel, JPanel buttonPanel, ArrayList<SaveContainer> containers):
 *     Inicializálja a DeleteMenu komponenseit, mint a listát, gombokat és leíró mezőt.
 * 
 * uploadList(ArrayList<SaveContainer> containers):
 *     Feltölti a JList-et a megadott SaveContainer objektumokkal.
 * 
 * confirmDelete():
 *     Megjelenít egy megerősítő párbeszédpanelt a "Delete" címmel, ami megkérdezi a felhasználót,
 *     hogy biztosan törölni szeretné-e a kijelölt táblát. Visszaadja a felhasználó válaszát.
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import containers.SaveContainer;

public class DeleteMenu extends FrameMenus {
    private int width;
    private int height;
    private MenuBar menuBar;
    private JList<SaveContainer> boardList;
    private DefaultListModel<SaveContainer> defaultListModel;
    private SaveContainer selectedContainer = null;
    private JTextField description;
    
    public DeleteMenu(MenuBar menuBar, ArrayList<SaveContainer> containers)
    {
        super("Delete", menuBar.getJF(), 400, 400);
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
        boardList.addListSelectionListener(e -> {selectedContainer = boardList.getSelectedValue(); if(selectedContainer != null){description.setText(selectedContainer.getDescription());} else {description.setText("");}});
        scrollPane.setViewportView(boardList);
        listPanel.add(new JLabel("Description"));
        description = new JTextField(20);
        if(boardList.getSelectedValue() != null)
        description.setText(boardList.getSelectedValue().getDescription());
        description.setEditable(false);
        listPanel.add(description);
        JButton button = new JButton("Delete");
        button.addActionListener(e -> {if(selectedContainer != null){if(confirmDelete() == 0){menuBar.delete(selectedContainer); defaultListModel.remove(boardList.getSelectedIndex());}}});
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

    private int confirmDelete()
    {
        return JOptionPane.showConfirmDialog(null, "Are you sure, you want to delete this board?", "Delete", JOptionPane.YES_NO_OPTION);
    }
}

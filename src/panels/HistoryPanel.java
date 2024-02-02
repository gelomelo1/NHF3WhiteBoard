package panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import additions.DefaultTransaction;
import additions.HistoryButton;
import additions.HistoryQueue;
import additions.Transactions;
import additions.HistoryButton.direction;

public class HistoryPanel extends JPanel {

    private HistoryButton undo;
    private HistoryButton redo;
    private HistoryQueue<Transactions> queue;

    public HistoryPanel()
    {
        super();
        initComps();
    }

    private void initComps()
    {
        undo = createButton(direction.undo, "<");
        queue = new HistoryQueue<Transactions>();
        queue.addItem(new DefaultTransaction());
        redo = createButton(direction.redo, ">");
        add(undo);
        add(queue);
        add(redo);
        setMaximumSize(new Dimension(300, 40));
        setAlignmentX(Component.RIGHT_ALIGNMENT);
        setAlignmentY(Component.BOTTOM_ALIGNMENT);
    }

    private HistoryButton createButton(direction dir, String text)
    {
        HistoryButton historyButton = new HistoryButton(dir, this, text);
        historyButton.addActionListener((ActionEvent e) -> {HistoryButton button = (HistoryButton) e.getSource(); button.startTransaction();});
        return historyButton;
    }

    public HistoryQueue<Transactions> getQueue()
    {
        return queue;
    }

    public void reset()
    {
        
    }
}

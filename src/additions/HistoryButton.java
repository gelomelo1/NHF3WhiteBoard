package additions;

import javax.swing.JButton;
import panels.HistoryPanel;

public class HistoryButton extends JButton {

    private direction dir;
    private HistoryPanel historyPanel;

    public enum direction
    {
        redo,
        undo
    }

    public HistoryButton(direction dir, HistoryPanel historyPanel , String text)
    {
        super(text);
        this.dir = dir;
        this.historyPanel = historyPanel;
    }

    public void startTransaction()
    {
        Transactions originalSelectedTransaction = (Transactions) historyPanel.getQueue().getSelectedItem();
        Transactions selectedTransaction;
        if(dir == direction.undo)
        {
            selectedTransaction = historyPanel.getQueue().switchSelected(-1);
            if(selectedTransaction != originalSelectedTransaction)
            originalSelectedTransaction.executeBacward();
        }
        else
        {
            selectedTransaction = historyPanel.getQueue().switchSelected(1);
            if(selectedTransaction != originalSelectedTransaction)
            selectedTransaction.executeForward();
        }
    }
}

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
        if(dir == direction.undo)
            historyPanel.getQueue().switchSelected(-1);
        else
            historyPanel.getQueue().switchSelected(1);
    }
}

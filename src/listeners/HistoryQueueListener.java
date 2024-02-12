package listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import panels.HistoryPanel;

public class HistoryQueueListener implements ItemListener {

    private HistoryPanel historyPanel;
    private int deselectedIndex = 0;
    private int lastSelectedIndex = 0;

    public HistoryQueueListener(HistoryPanel historyPanel)
    {
        this.historyPanel = historyPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.DESELECTED)
            deselectedIndex = lastSelectedIndex;
            else if(e.getStateChange() == ItemEvent.SELECTED)
            {
                if(!historyPanel.getQueue().getChanging())
                {
                    int selectedIndex = historyPanel.getQueue().getSelectedIndex();
                    if(selectedIndex < deselectedIndex)
                    {        
                        for(int i = deselectedIndex; i > selectedIndex; i--)
                        historyPanel.getQueue().getItemAt(i).executeBacward();
                    }
                    else if(selectedIndex > deselectedIndex)
                    {
                        for(int i = deselectedIndex + 1; i <= selectedIndex; i++)
                        historyPanel.getQueue().getItemAt(i).executeForward();
                    }
                }
                lastSelectedIndex = historyPanel.getQueue().getSelectedIndex();
            }
    }
    
}

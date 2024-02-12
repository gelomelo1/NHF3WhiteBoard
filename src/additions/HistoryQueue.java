package additions;

import javax.swing.JComboBox;

public class HistoryQueue<T> extends JComboBox<T> {

    private final int maxQueue = 30;
    private boolean changing = false;

    public void addItem(T item)
    {
        changing = true;

        if(getSelectedIndex() != getItemCount() - 1)
        {
            int count = getItemCount() - getSelectedIndex() - 1;
            for(int i = 0; i < count; i++)
                removeItemAt(getSelectedIndex() + 1);
        }

        if(getItemCount() == maxQueue)
        removeItemAt(0);

        super.addItem(item);
        setSelectedItem(item);
        
        changing = false;
    }

    public T switchSelected(int offset)
    {
            if(!(getSelectedIndex() + offset > maxQueue - 1 || getSelectedIndex() + offset < 0) && getSelectedIndex() + offset < getItemCount())
            setSelectedIndex(getSelectedIndex() + offset);   
        return (T) getSelectedItem();
    }

    public boolean getChanging()
    {
        return changing;
    }
}

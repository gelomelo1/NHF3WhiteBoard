/**
 * Függvények:
 * 
 * actionPerformed(ActionEvent e):
 *     A gomb lenyomásakor meghívódó metódus. Az egyszerű műveletek végrehajtását indítja el
 *     az execute(Object source) metóduson keresztül az esemény forrásával.
 * 
 * stateChanged(ChangeEvent e):
 *     Az állapot megváltozásakor meghívódó metódus. Az egyszerű műveletek végrehajtását indítja el
 *     az execute(Object source) metóduson keresztül az esemény forrásával.
 * 
 */
package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import additions.SimpleActions;

public class SimpleActionListener implements ActionListener, ChangeListener{

    private SimpleActions simpleActions;

    public SimpleActionListener(SimpleActions simpleActions)
    {
        this.simpleActions = simpleActions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        simpleActions.execute(e.getSource());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        simpleActions.execute(e.getSource());
    }
    
}

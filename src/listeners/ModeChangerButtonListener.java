package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import additions.ModeChangerButton;

public class ModeChangerButtonListener implements ActionListener {

    private ArrayList<ModeChangerButton> modeChangerButtons = new ArrayList<ModeChangerButton>();

    @Override
    public void actionPerformed(ActionEvent e) {
        for (ModeChangerButton modeChangerButton : modeChangerButtons) {
            if(e.getSource() == modeChangerButton)
            {
                modeChangerButton.change();
                break;
            }
        }
    }

    public void addButton(ModeChangerButton button)
    {
        modeChangerButtons.add(button);
    }
    
}

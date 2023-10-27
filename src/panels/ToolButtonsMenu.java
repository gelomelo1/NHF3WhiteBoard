package panels;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import additions.ModeChangerButton;
import additions.ModeChangerButton.ModesChanger;
import controllers.ModesController;
import listeners.ModeChangerButtonListener;

public class ToolButtonsMenu extends JPanel {

    private ModesController modesController;

    public ToolButtonsMenu(ModesController modesController)
    {
        initMenu(modesController);
        initButtons();
    }

    private void initMenu(ModesController modesController)
    {
        this.modesController = modesController;
        setLayout(new GridLayout(0, 1));
    }

    private void initButtons()
    {
        ModeChangerButtonListener modeChangerButtonListener = new ModeChangerButtonListener();
        ModeChangerButton modeChangerButton = new ModeChangerButton("Move", this, ModesChanger.Move);
        assignButton(modeChangerButton, modeChangerButtonListener);
        modeChangerButton = new ModeChangerButton("Draw", this, ModesChanger.Draw);
        assignButton(modeChangerButton, modeChangerButtonListener);
        modeChangerButton = new ModeChangerButton("Text", this, ModesChanger.Text);
        assignButton(modeChangerButton, modeChangerButtonListener);
        modeChangerButton = new ModeChangerButton("Image", this, ModesChanger.Image);
        assignButton(modeChangerButton, modeChangerButtonListener);
    }

    private void assignButton(ModeChangerButton modeChangerButton, ModeChangerButtonListener modeChangerButtonListener)
    {
        modeChangerButtonListener.addButton(modeChangerButton);
        modeChangerButton.addActionListener(modeChangerButtonListener);
        add(modeChangerButton);
    }

    public ModesController getModesController()
    {
        return modesController;
    }
}

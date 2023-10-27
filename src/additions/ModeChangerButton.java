package additions;

import javax.swing.JButton;

import canvasmodes.DefaultCanvasMode;
import canvasmodes.DrawCanvasMode;
import panels.ToolButtonsMenu;

public class ModeChangerButton extends JButton {
    
public enum ModesChanger
{
    Move,
    Draw,
    Text,
    Image
}

private ToolButtonsMenu toolButtonsMenu;
private ModesChanger modesChanger;

public ModeChangerButton(String text,ToolButtonsMenu toolButtonsMenu, ModesChanger modesChanger)
{
    super(text);
    this.toolButtonsMenu = toolButtonsMenu;
    this.modesChanger = modesChanger;
}

public void change()
{
    DefaultCanvasMode canvasMode = null;
    switch(modesChanger)
    {
        case Move:
        {
            canvasMode = new DefaultCanvasMode(toolButtonsMenu.getModesController().getCanvas());
            break;
        }
        case Draw:
        {
            canvasMode = new DrawCanvasMode(toolButtonsMenu.getModesController().getCanvas());
            break;
        }
    }
    toolButtonsMenu.getModesController().changeMode(canvasMode);
}

}

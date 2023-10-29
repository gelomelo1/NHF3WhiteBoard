package additions;

import javax.swing.JButton;
import canvasmodes.DefaultCanvasMode;
import canvasmodes.DrawCanvasMode;
import canvasmodes.EraseCanvasMode;
import canvasmodes.TextCanvasMode;
import panels.DrawToolPropertiesMenu;
import panels.EraseToolPropertiesMenu;
import panels.MoveToolPropertiesMenu;
import panels.TextToolPropertiesMenu;
import panels.ToolButtonsMenu;
import panels.ToolPropertiesMenu;

public class ModeChangerButton extends JButton {
    
public enum ModesChanger
{
    Move,
    Draw,
    Erase,
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
    ToolPropertiesMenu toolPropertiesMenu = null;
    switch(modesChanger)
    {
        case Move:
        {
            toolPropertiesMenu = new MoveToolPropertiesMenu(toolButtonsMenu.getModesController().getCanvas());
            canvasMode = new DefaultCanvasMode(toolButtonsMenu.getModesController().getCanvas(), toolPropertiesMenu);
            break;
        }
        case Draw:
        {
            toolPropertiesMenu = new DrawToolPropertiesMenu(toolButtonsMenu.getModesController().getCanvas());
            canvasMode = new DrawCanvasMode(toolButtonsMenu.getModesController().getCanvas(), toolPropertiesMenu);
            break;
        }
        case Erase:
        {
            toolPropertiesMenu = new EraseToolPropertiesMenu(toolButtonsMenu.getModesController().getCanvas());
            canvasMode = new EraseCanvasMode(toolButtonsMenu.getModesController().getCanvas(), toolPropertiesMenu);
            break;
        }
        case Text:
        {
            toolPropertiesMenu = new TextToolPropertiesMenu(toolButtonsMenu.getModesController().getCanvas());
            canvasMode = new TextCanvasMode(toolButtonsMenu.getModesController().getCanvas(), toolPropertiesMenu);
        }
    }
    toolButtonsMenu.getModesController().changeMode(canvasMode, toolPropertiesMenu);
}

}

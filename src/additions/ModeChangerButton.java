/**
 * Függvények:
 * 
 * ModeChangerButton(String path, ToolButtonsMenu toolButtonsMenu, ModesChanger modesChanger):
 *     A ModeChangerButton osztály konstruktora. Beállítja a gombhoz tartozó ikont, a tulajdonságmenüt, és a módot.
 * 
 * change():
 *     Váltást kezel az osztályhoz tartozó módok között. Az aktuális módot és az eszköz tulajdonságmenüt beállítja a kiválasztott módnak megfelelően.
 * 
 * setButtonImage(ImageIcon ic):
 *     Beállítja a gomb képét az ikon méretére szabva.
 * 
 */
package additions;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import canvasmodes.DefaultCanvasMode;
import canvasmodes.DrawCanvasMode;
import canvasmodes.EraseCanvasMode;
import canvasmodes.ImageCanvasMode;
import canvasmodes.TextCanvasMode;
import panels.DrawToolPropertiesMenu;
import panels.EraseToolPropertiesMenu;
import panels.ImageToolPropertiesMenu;
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

public ModeChangerButton(String path, ToolButtonsMenu toolButtonsMenu, ModesChanger modesChanger)
{
    this.toolButtonsMenu = toolButtonsMenu;
    this.modesChanger = modesChanger;
    setContentAreaFilled(false);
    setButtonImage(new ImageIcon(path));
}

protected void setButtonImage(ImageIcon ic)
{
    Image img = ic.getImage();
    Image newimg = img.getScaledInstance(50, 50, Image.SCALE_DEFAULT) ;  
    ic = new ImageIcon( newimg );
    setIcon(ic);
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
            break;
        }
        case Image:
        {
            toolPropertiesMenu = new ImageToolPropertiesMenu(toolButtonsMenu.getModesController().getCanvas());
            canvasMode = new ImageCanvasMode(toolButtonsMenu.getModesController().getCanvas(), toolPropertiesMenu);
            break;
        }
    }
    toolButtonsMenu.getModesController().changeMode(canvasMode, toolPropertiesMenu);
}

}

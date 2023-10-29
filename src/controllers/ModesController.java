package controllers;

import javax.swing.JFrame;
import javax.swing.JPanel;
import canvasmodes.DefaultCanvasMode;
import panels.Canvas;
import panels.MoveToolPropertiesMenu;
import panels.ToolMenu;
import panels.ToolPropertiesMenu;
import uiholders.Menu;

public class ModesController {

    //controller items
    private Menu menu;
    private ToolMenu toolsMenu;
    private Canvas canvas;
    private DefaultCanvasMode canvasMode;

    public ModesController(Menu menu, JFrame jf)
    {
        initController(menu, jf);
        ToolPropertiesMenu toolPropertiesMenu = new MoveToolPropertiesMenu(canvas);
        changeMode(new DefaultCanvasMode(canvas, toolPropertiesMenu), toolPropertiesMenu);
    }

    private void initController(Menu menu, JFrame jf)
    {
        this.menu = menu;
        canvas = new Canvas(jf);
        toolsMenu = new ToolMenu(jf, this);
    }

    public void changeMode(DefaultCanvasMode newMode, JPanel toolpropertiesMenu)
    {
        canvasMode = newMode;
        toolsMenu.setToolPropertiesMenu(toolpropertiesMenu);
    }

    public Canvas getCanvas()
    {
        return canvas;
    }
}

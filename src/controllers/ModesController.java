package controllers;

import javax.swing.JFrame;
import javax.swing.JPanel;
import canvasmodes.DefaultCanvasMode;
import panels.Canvas;
import panels.ToolMenu;
import uiholders.Menu;

public class ModesController {

    //controller items
    private Menu menu;
    private JPanel toolsMenu;
    private Canvas canvas;
    private DefaultCanvasMode canvasMode;

    public ModesController(Menu menu, JFrame jf)
    {
        initController(menu, jf);
        changeMode(new DefaultCanvasMode(canvas));
    }

    private void initController(Menu menu, JFrame jf)
    {
        this.menu = menu;
        canvas = new Canvas(jf);
        toolsMenu = new ToolMenu(jf, this);
    }

    public void changeMode(DefaultCanvasMode newMode)
    {
        canvasMode = newMode;
    }

    public Canvas getCanvas()
    {
        return canvas;
    }
}

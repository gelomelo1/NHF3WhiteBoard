package additions;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import canvasmodes.DefaultCanvasMode;
import panels.Canvas;
import panels.ToolPropertiesMenu;

public abstract class Transactions {

    private String name;
    private static int counter = 0;
    private ArrayList<CanvasActivity> transactionObjects;
    private Canvas canvas;

    public Transactions(Canvas canvas ,ArrayList<CanvasActivity> transactionObjects, String operation)
    {
        this.transactionObjects = transactionObjects;
        this.canvas = canvas;
        name = operation + " ";
        if(canvas != null)
        {
            if(transactionObjects.size() == 1)
            name += transactionObjects.get(0).toString();
            else
            name += "Objects";
            name += " " + counter;
            counter++;
        }
    }

    public String toString()
    {
        return name;
    }

    protected abstract void executeModeBackward();
    protected abstract void executeModeForward();

    public void executeBacward()
    {
        MouseAdapter originalMouseAdapter = canvas.getSelectedListener();
        ToolPropertiesMenu originaToolPropertiesMenu = canvas.getToolPropertiesMenu();
        executeModeBackward();
        canvas.setMouseListener(originalMouseAdapter);
        canvas.setToolPropertiesMenu(originaToolPropertiesMenu);
    }

    public void executeForward()
    {
        MouseAdapter originalMouseAdapter = canvas.getSelectedListener();
        ToolPropertiesMenu originaToolPropertiesMenu = canvas.getToolPropertiesMenu();
        executeModeForward();
        canvas.setMouseListener(originalMouseAdapter);
        canvas.setToolPropertiesMenu(originaToolPropertiesMenu);
    }

    protected Canvas getCanvas()
    {
        return canvas;
    }

    protected ArrayList<CanvasActivity> getTransactionObjects()
    {
        return transactionObjects;
    }

    protected void resetListenersSelections(DefaultCanvasMode defaultCanvasMode)
    {
        getCanvas().setMouseListener(null);
        defaultCanvasMode.resetSelection();
    }
}

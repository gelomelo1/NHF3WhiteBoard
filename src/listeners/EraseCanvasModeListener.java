package listeners;

import java.awt.event.MouseEvent;
import canvasmodes.EraseCanvasMode;

public class EraseCanvasModeListener extends DefaultCanvasModeListener {

    private EraseCanvasMode eraseCanvasMode;

        public EraseCanvasModeListener(EraseCanvasMode eraseCanvasMode)
    {
        super(eraseCanvasMode);
        this.eraseCanvasMode = eraseCanvasMode;
    }

    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        if(getPressedMouse() == MouseEvent.BUTTON1)
          eraseCanvasMode.erase(e.getPoint());
    }

    public void mouseDragged(MouseEvent e)
    {
        if(getPressedMouse() == MouseEvent.BUTTON3)
           super.mouseDragged(e);
        else if(getPressedMouse() == MouseEvent.BUTTON1)
            eraseCanvasMode.erase(e.getPoint());
    }
}

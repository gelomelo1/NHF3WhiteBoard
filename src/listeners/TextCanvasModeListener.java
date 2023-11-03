package listeners;

import java.awt.event.MouseEvent;
import canvasmodes.TextCanvasMode;

public class TextCanvasModeListener extends DefaultCanvasModeListener {
    private TextCanvasMode textCanvasMode;

    public TextCanvasModeListener(TextCanvasMode textCanvasMode)
    {
        super(textCanvasMode);
        this.textCanvasMode = textCanvasMode;
    }

    public void mouseClicked(MouseEvent e)
    {
        if(getPressedMouse() == MouseEvent.BUTTON1)
        {
            textCanvasMode.placeText(e.getPoint());
            textCanvasMode.update();
        }
    }

    public void mouseDragged(MouseEvent e)
    {
        if(getPressedMouse() == MouseEvent.BUTTON3)
            super.mouseDragged(e);
    }

    public void mouseMoved(MouseEvent e)
    {

    }
}
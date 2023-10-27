package listeners;

import java.awt.event.MouseEvent;

import canvasmodes.DrawCanvasMode;

public class DrawCanvasModeListener extends DefaultCanvasModeListener {
    
    private DrawCanvasMode drawCanvasMode;
    
    public DrawCanvasModeListener(DrawCanvasMode drawCanvasMode)
    {
        super(drawCanvasMode);
        this.drawCanvasMode = drawCanvasMode;
    }

    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        if(getPressedMouse() == MouseEvent.BUTTON1)
           drawCanvasMode.StartNewCurve(e.getPoint());
    }

    public void mouseDragged(MouseEvent e)
    {
        if(getPressedMouse() == MouseEvent.BUTTON3)
        {
           super.mouseDragged(e);
        }
        else if(getPressedMouse() == MouseEvent.BUTTON1)
            drawCanvasMode.AddPoint(e.getPoint());
    }
}

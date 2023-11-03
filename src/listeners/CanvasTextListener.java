package listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import canvasmodes.TextCanvasMode;
import containers.CanvasText;

public class CanvasTextListener implements FocusListener {

    private TextCanvasMode textCanvasMode;

    public CanvasTextListener(TextCanvasMode textCanvasMode)
    {
        this.textCanvasMode = textCanvasMode;
    }

    @Override
    public void focusGained(FocusEvent e) {
        textCanvasMode.setTextFocus((CanvasText)e.getSource());
        textCanvasMode.update();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
    
}

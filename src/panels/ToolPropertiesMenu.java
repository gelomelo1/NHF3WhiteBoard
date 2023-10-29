package panels;

import java.awt.Component;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthStyle;

public abstract class ToolPropertiesMenu extends JPanel {
    
    private Canvas canvas;

    public ToolPropertiesMenu(Canvas canvas)
    {
        this.canvas = canvas;
        initMenu();
        setBorderTitle();
    }

    private void setBorderTitle()
    {
        setBorder(BorderFactory.createTitledBorder(getPropertyName() + " Properties:"));
    }

    public Canvas getCanvas()
    {
        return canvas;
    }
    
    protected abstract void initMenu();
    protected abstract String getPropertyName();
    protected abstract void update();
}

package panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MoveToolPropertiesMenu extends ToolPropertiesMenu {

    private JLabel posX;
    private JLabel posY;

    public MoveToolPropertiesMenu(Canvas canvas)
    {
        super(canvas);
    }

    @Override
    protected void initMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JLabel("Mouse Position:"));
        posX = new JLabel();
        panel.add(posX);
        setPosX(0);
        posY = new JLabel();
        panel.add(posY);
        setPosY(0);
        add(panel);
    }

    @Override
    protected String getPropertyName() {
        return "Move";
    }

        @Override
    protected void update() {
        setPosX(getCanvas().getMousePos().x);
        setPosY(getCanvas().getMousePos().y);
    }

    public void setPosX(int x)
    {
        posX.setText("X: " + x);
    }

    public void setPosY(int y)
    {
        posY.setText("Y: " + y);
    }
    
}

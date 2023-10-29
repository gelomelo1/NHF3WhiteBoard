package panels;

import javax.swing.JLabel;

public class MoveToolPropertiesMenu extends ToolPropertiesMenu {

    private JLabel posX;
    private JLabel posY;

    public MoveToolPropertiesMenu(Canvas canvas)
    {
        super(canvas);
    }

    @Override
    protected void initMenu() {
        add(new JLabel("Mouse Position:"));
        posX = new JLabel();
        setPosX(0);
        posY = new JLabel();
        setPosY(0);
        add(posX);
        add(posY);
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

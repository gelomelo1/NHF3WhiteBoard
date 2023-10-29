package panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextToolPropertiesMenu extends ToolPropertiesMenu {
    private JTextField posX;
    private JTextField posY;
    private JTextField width;
    private JTextField height;

    public TextToolPropertiesMenu(Canvas canvas)
    {
        super(canvas);
    }

    @Override
    protected void initMenu() {
        setLayout(new GridLayout(4, 4));
        initFields("Position X:", posX);
        initFields("Position Y:", posY);
        initFields("Width:", width);
        initFields("Height:", height);
    }

    private void initFields(String labelName, JTextField value)
    {
        add(new JLabel(labelName));
        value = new JTextField();
        add(value);
    }

    @Override
    protected String getPropertyName() {
        return "Text";
    }

    @Override
    protected void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}

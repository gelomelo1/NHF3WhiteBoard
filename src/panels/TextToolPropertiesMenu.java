package panels;

import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;
import additions.PropertiesInput;
import containers.CanvasText;
import listeners.PropertiesInputListener;

public class TextToolPropertiesMenu extends ToolPropertiesMenu implements PropertiesInput {
    private JTextField posX;
    private JTextField posY;
    private JTextField width;
    private JTextField height;
    private JTextField fontSize;
    private PropertiesInputListener textToolPropertiesListener;

    public TextToolPropertiesMenu(Canvas canvas)
    {
        super(canvas);
    }

    @Override
    protected void initMenu() {
        textToolPropertiesListener = new PropertiesInputListener(this);
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(5, 5));
        add(textPanel);
        posX = initFields("Position X:", textPanel);
        posY = initFields("Position Y:", textPanel);
        width = initFields("Width:", textPanel);
        height = initFields("Height:", textPanel);
        fontSize = initFields("Font size:", textPanel);
        getCanvas().setSelectedText(null);
    }

    private JTextField initFields(String labelName, JPanel panel)
    {
        panel.add(new JLabel(labelName));
        JTextField value = new JTextField();
        value.getDocument().addDocumentListener(textToolPropertiesListener);
        panel.add(value);
        return value;
    }

    @Override
    protected String getPropertyName() {
        return "Text";
    }

    @Override
    protected void update() {
        CanvasText text = getCanvas().getSelectedText();
        if(text == null)
        {
            posX.setText("");
            posY.setText("");
            width.setText("");
            height.setText("");
            fontSize.setText("");
        }
        else
        {
            Rectangle rectangle = text.getBounds();
            posX.setText(Integer.valueOf(rectangle.x).toString());
            posY.setText(Integer.valueOf(rectangle.y).toString());
            width.setText(Integer.valueOf(rectangle.width).toString());
            height.setText(Integer.valueOf(rectangle.height).toString());
            fontSize.setText(Integer.valueOf(text.getFont().getSize()).toString());
        }
    }

    private boolean valueIsValid(int value, boolean isHorizontal)
    {
        if(isHorizontal)
        {
            if(value >= 0 && value <= getCanvas().getMaxWidth())
            return true;
        }
        else
        {
            if(value >= 0 && value <= getCanvas().getMaxHeight())
            return true;
        }
        return false;
    }

    public void changeValue(Document document)
    {
        try
        {
        int value;
        if(document == fontSize.getDocument())
        {
            value = Integer.parseInt(fontSize.getText());
            getCanvas().getSelectedText().changeFontSize(value);
        }
        else
        {
        Rectangle rectangle = getCanvas().getSelectedText().getBounds();
        if(document == posX.getDocument())
        {
            value = Integer.parseInt(posX.getText());
            if(valueIsValid(value, true))
            rectangle.x = value;
        }
        else if(document == width.getDocument())
        {
            value = Integer.parseInt(width.getText());
            if(valueIsValid(value, true))
            rectangle.width = value;
        }
        else if(document == posY.getDocument())
        {
            value = Integer.parseInt(posY.getText());
            if(valueIsValid(value, false))
            rectangle.y = value;
        }
        else if(document == height.getDocument())
        {
            value = Integer.parseInt(height.getText());
            if(valueIsValid(value, false))
            rectangle.height = value;
        }
        getCanvas().getSelectedText().setBounds(rectangle);
        }
    }
    catch(NumberFormatException e)
    {

    }
        }  
}

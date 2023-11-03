package panels;

import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.Document;
import additions.PropertiesInput;
import containers.CanvasImage;
import listeners.PropertiesInputListener;

public class ImageToolPropertiesMenu extends ToolPropertiesMenu implements PropertiesInput {
    private JTextField posX;
    private JTextField posY;
    private JTextField width;
    private JTextField height;
    private PropertiesInputListener imageToolPropertiesListener;

    public ImageToolPropertiesMenu(Canvas canvas)
    {
        super(canvas);
    }

    @Override
    protected void initMenu() {
        imageToolPropertiesListener = new PropertiesInputListener(this);
        setLayout(new GridLayout(4, 4));
        posX = initFields("Position X:");
        posY = initFields("Position Y:");
        width = initFields("Width:");
        height = initFields("Height:");
        getCanvas().setSelectedImage(null);
    }

    private JTextField initFields(String labelName)
    {
        add(new JLabel(labelName));
        JTextField value = new JTextField();
        value.getDocument().addDocumentListener(imageToolPropertiesListener);
        add(value);
        return value;
    }

    @Override
    protected String getPropertyName() {
        return "Image";
    }

    @Override
    protected void update() {
        CanvasImage image = getCanvas().getSelectedImage();
        if(image == null)
        {
            posX.setText("");
            posY.setText("");
            width.setText("");
            height.setText("");
        }
        else
        {
            Rectangle rectangle = image.getBounds();
            posX.setText(Integer.valueOf(rectangle.x).toString());
            posY.setText(Integer.valueOf(rectangle.y).toString());
            width.setText(Integer.valueOf(rectangle.width).toString());
            height.setText(Integer.valueOf(rectangle.height).toString());
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

    @Override
    public void changeValue(Document document) {
        try
        {
        int value;
        Rectangle rectangle = getCanvas().getSelectedImage().getBounds();
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
        getCanvas().getSelectedImage().setBounds(rectangle);
        }
    catch(NumberFormatException e)
    {

    }
}
}

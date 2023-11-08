package panels;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private JButton copy;
    private PropertiesInputListener imageToolPropertiesListener;

    public ImageToolPropertiesMenu(Canvas canvas)
    {
        super(canvas);
    }

    @Override
    protected void initMenu() {
        imageToolPropertiesListener = new PropertiesInputListener(this);
        JPanel textPanel = new JPanel();
        add(textPanel);
        JPanel buttonPanel = new JPanel();
        add(buttonPanel);
        textPanel.setLayout(new GridLayout(4, 4));
        posX = initFields("Position X:", textPanel);
        posY = initFields("Position Y:", textPanel);
        width = initFields("Width:", textPanel);
        height = initFields("Height:", textPanel);
        copy = initButton("Copy", buttonPanel);
        getCanvas().setSelectedImage(null);
    }

    private JButton initButton(String name, JPanel panel)
    {
        JButton button = new JButton(name);
        button.addActionListener(e -> {copyImage();});
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button);
        return button;
    }

    private JTextField initFields(String labelName, JPanel panel)
    {
        panel.add(new JLabel(labelName));
        JTextField value = new JTextField();
        value.getDocument().addDocumentListener(imageToolPropertiesListener);
        panel.add(value);
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

    private void copyImage()
    {
        if(getCanvas().getSelectedImage() != null)
        {
        Rectangle originalPos = getCanvas().getSelectedImage().getBounds();
        String path = getCanvas().getSelectedImage().getPath();
        Point point = new Point();
        int copyOffset = Double.valueOf(Math.sqrt(Math.pow((originalPos.width / 2), 2) + Math.pow(originalPos.height / 2, 2))).intValue() + 20;
        int[][] directions = {{copyOffset, copyOffset}, {-copyOffset, copyOffset}, {copyOffset, -copyOffset}, {-copyOffset, -copyOffset}};
        for(int i = 0; i < 4; i++)
        {
            point.x = originalPos.x + originalPos.width / 2 +  directions[i][0];
            point.y = originalPos.y + originalPos.height / 2 + directions[i][1];
            if(valueIsValid(point.x, true) && valueIsValid(point.y, false))
                break;
        }
        CanvasImage image = getCanvas().addImage(point, path);
        getCanvas().setSelectedImage(image);
        getCanvas().repaint();
        }
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

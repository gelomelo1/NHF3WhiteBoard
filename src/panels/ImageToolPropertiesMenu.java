/**
 * Függvények:
 * 
 * ImageToolPropertiesMenu(Canvas canvas):
 *     Az ImageToolPropertiesMenu osztály konstruktora. Az ősosztály konstruktorát hívja meg, inicializálja a komponenseket.
 * 
 * initMenu():
 *     Inicializálja az eszköztulajdonságok menüjét, mint a pozíciók és a másolás gomb.
 * 
 * initButton(String name, JPanel panel):
 *     Inicializálja a gombot a megadott névvel és hozzáadja a panelhez.
 * 
 * initFields(String labelName, JPanel panel):
 *     Inicializálja a szövegmezőket a megadott névvel és hozzáadja a panelhez.
 * 
 * getPropertyName():
 *     Visszaadja a tulajdonságok menüjének nevét ("Image").
 * 
 * update():
 *     Az ősosztály metódusának felülírása, frissíti az eszköztulajdonságokat a kiválasztott kép adataival.
 * 
 * valueIsValid(int value, boolean isHorizontal, boolean isPosition):
 *     Ellenőrzi, hogy az adott érték érvényes-e a megadott tengelyen (vízszintesen vagy függőlegesen),
 *     és ha az érték a pozícióra vonatkozik, figyelembe veszi a kiválasztott kép méretét.
 * 
 * copyImage():
 *     Másol egy új képet a kiválasztott képből, a kép középpontjából kiindulva és biztonságos pozícióban.
 * 
 * changeValue(Document document):
 *     Az egyszerű műveletek interfész metódusa, amely a szövegmezők értékeinek változásakor hívódik meg.
 *     Beállítja a kiválasztott kép méreteit és pozícióját a szövegmezők értékeinek megfelelően.
 *     Ellenőrzi az érvényességet a kép mérete és a vászon határai szerint.
 */
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
        getCanvas().resetActivitiesSelection();
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
        CanvasImage image = null;
        if(getCanvas().getSelectedImage().size() == 1)
        image = getCanvas().getSelectedImage().get(0);
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

    private boolean valueIsValid(int value, boolean isHorizontal, boolean isPosition)
    {
        int max;
        if(isHorizontal)
        {
            for (CanvasImage canvasImage : getCanvas().getSelectedImage()) {
            max = getCanvas().getMaxWidth();
            if(isPosition)
            max -= canvasImage.getWidth();
            if(!(value >= 0 && value <= max))
            return false;   
            }
        }
        else
        {
            for (CanvasImage canvasImage : getCanvas().getSelectedImage()) {
            max = getCanvas().getMaxHeight();
            if(isPosition)
            max -= canvasImage.getHeight();
            if(!(value >= 0 && value <= max))
            return false;   
            }
        }
        return true;
    }

    private void copyImage()
    {
        if(getCanvas().getSelectedImage().size() == 1)
        {
        Rectangle originalPos = getCanvas().getSelectedImage().get(0).getBounds();
        String path = getCanvas().getSelectedImage().get(0).getPath();
        Point point = new Point();
        int copyOffset = Double.valueOf(Math.sqrt(Math.pow((originalPos.width / 2), 2) + Math.pow(originalPos.height / 2, 2))).intValue() + 20;
        int[][] directions = {{copyOffset, copyOffset}, {-copyOffset, copyOffset}, {copyOffset, -copyOffset}, {-copyOffset, -copyOffset}};
        for(int i = 0; i < 4; i++)
        {
            point.x = originalPos.x + originalPos.width / 2 +  directions[i][0];
            point.y = originalPos.y + originalPos.height / 2 + directions[i][1];
            if(valueIsValid(point.x, true, true) && valueIsValid(point.y, false, true))
                break;
        }
        CanvasImage image = getCanvas().addImage(point, originalPos.width, originalPos.height, path);
        getCanvas().addSelectedCanvasActivity(image);
        getCanvas().repaint();
        }
    }

    @Override
    public void changeValue(Document document) {
        try
        {
        if(getCanvas().getSelectedImage().size() == 1)
        {
        int value;
        Rectangle rectangle = getCanvas().getSelectedImage().get(0).getBounds();
        if(document == posX.getDocument())
        {
            value = Integer.parseInt(posX.getText());
            if(valueIsValid(value, true, true))
            rectangle.x = value;
        }
        else if(document == width.getDocument())
        {
            value = Integer.parseInt(width.getText());
            int offset = 0;
            if(value >= 0)
            offset = rectangle.x;
            if(valueIsValid(value + offset, true, false))
            rectangle.width = value;
        }
        else if(document == posY.getDocument())
        {
            value = Integer.parseInt(posY.getText());
            if(valueIsValid(value, false, true))
            rectangle.y = value;
        }
        else if(document == height.getDocument())
        {
            value = Integer.parseInt(height.getText());
            int offset = 0;
            if(value >= 0)
            offset = rectangle.y;
            if(valueIsValid(value + offset, false, false))
            rectangle.height = value;
        }
        getCanvas().getSelectedImage().get(0).setBounds(rectangle);
        }
        }
    catch(NumberFormatException e)
    {

    }
}
}

/**
 * Függvények:
 * 
 * TextToolPropertiesMenu(Canvas canvas):
 *     A TextToolPropertiesMenu osztály konstruktora. Meghívja az ősosztály konstruktorát, majd inicializálja a szövegmezőket és a hallgatót.
 * 
 * initMenu():
 *     Inicializálja a szövegmezőket és a hallgatót a megfelelő panelekhez.
 * 
 * initFields(String labelName, JPanel panel):
 *     Inicializál egy szövegmezőt a megadott névvel és panellel. Hozzáad egy dokumentumhallgatót a mezőhöz, amelyet a textToolPropertiesListener kezel.
 *     Visszaadja a létrehozott szövegmezőt.
 * 
 * getPropertyName():
 *     Visszaadja az eszköz tulajdonságának nevét ("Text").
 * 
 * update():
 *     Frissíti a szövegmezőket a kiválasztott szöveg adataival.
 * 
 * valueIsValid(int value, boolean isHorizontal, boolean isPosition):
 *     Ellenőrzi, hogy a megadott érték érvényes-e a paraméterek alapján.
 *     - isHorizontal: true esetén vízszintes, false esetén függőleges ellenőrzést végez.
 *     - isPosition: true esetén a pozícióra vonatkozó ellenőrzést végez, false esetén a méretre vonatkozó ellenőrzést.
 *     Visszaadja, hogy az érték érvényes-e vagy sem.
 * 
 * changeValue(Document document):
 *     Változtatja a kiválasztott szöveg tulajdonságait a megadott dokumentum alapján. Ellenőrzi a validitást, és ha a dokumentum a betűméretet reprezentálja, 
 *     akkor azt módosítja, különben a pozíciót és méretet változtatja meg.
 * 
 * setPosX(String text):
 *     Beállítja a PosX JTextField objektumot
 * 
 * setPosY(String text):
 *     Beállítja a PosY JTextField objektumot
 * 
 * setFieldWidth(String text):
 *     Beállítja a width JTextField objektumot
 * 
 * setFieldHeight(String text):
 *     Beállítja a height JTextField objektumot
 * 
 * setFontSize(String text):
 *     Beállítja a fontSize JTextField objektumot
 * 
 * getPosX():
 *     Visszaadja a PosX JTextField objektumot
 * 
 * getPosY():
 *     Visszaadja a PosY JTextField objektumot
 * 
 * getFieldWidth():
 *     Visszaadja a width JTextField objektumot
 * 
 * getFieldHeight():
 *     Visszadja a height JTextField objektumot
 * 
 * getFontSize():
 *     Visszaadja a fontSize JTextField objektumot
 * 
 */
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
        getCanvas().resetActivitiesSelection();
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
        CanvasText text = null;
        if(getCanvas().getSelectedText().size() == 1)
         text = getCanvas().getSelectedText().get(0);
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

    private boolean valueIsValid(int value, boolean isHorizontal, boolean isPosition)
    {
        int max;
        if(isHorizontal)
        {
            for (CanvasText canvasText : getCanvas().getSelectedText()) {
            max = getCanvas().getMaxWidth();
            if(isPosition)
            max -= canvasText.getWidth();
            if(!(value >= 0 && value <= max))
            return false;
            }
        }
        else
        {
            for (CanvasText canvasText : getCanvas().getSelectedText()) {
            max = getCanvas().getMaxHeight();
            if(isPosition)
            max -= canvasText.getHeight();
            if(!(value >= 0 && value <= max))
            return false; 
            }
        }
        return true;
    }

    public void changeValue(Document document)
    {
        try
        {
        int value;
        if(getCanvas().getSelectedText().size() == 1)
        {
        if(document == fontSize.getDocument())
        {
            value = Integer.parseInt(fontSize.getText());
            getCanvas().getSelectedText().get(0).changeFontSize(value);
        }
        else
        {
            Rectangle rectangle = getCanvas().getSelectedText().get(0).getBounds();
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
            getCanvas().getSelectedText().get(0).setBounds(rectangle);
        }
    }
    }
    catch(NumberFormatException e)
    {

    }
        }
        
        public void updateText()
        {
            update();
        }
        
        public void setPosX(String text) {
            posX.setText(text);
        }
    
        public void setPosY(String text) {
            posY.setText(text);
        }
    
        public void setFieldWidth(String text) {
            width.setText(text);
        }
    
        public void setFieldHeight(String text) {
           height.setText(text);
        }
    
        public void setFontSize(String text) {
            fontSize.setText(text);
        }

        public JTextField getPosX() {
            return posX;
        }
    
        public JTextField getPosY() {
            return posY;
        }
    
        public JTextField getFieldWidth() {
            return width;
        }
    
        public JTextField getFieldHeight() {
            return height;
        }
    
        public JTextField getFontSize() {
            return fontSize;
        }
}

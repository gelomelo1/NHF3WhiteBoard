/**
 * Függvények:
 * 
 * ToolPropertiesMenu(Canvas canvas):
 *     Az absztrakt ToolPropertiesMenu osztály konstruktora. Beállítja a hozzátartozó vásznat, majd inicializálja a menüt a leszármazottak számára a initMenu() metódussal.
 *     Beállítja a panel keretének címét a getPropertyName() metódus visszatérési értéke alapján.
 * 
 * setBorderTitle():
 *     Beállítja a panel keretének címét a getPropertyName() metódus visszatérési értéke alapján.
 * 
 * getCanvas():
 *     Visszaadja a hozzátartozó vásznat.
 * 
 * initMenu():
 *     Absztrakt metódus, amelyet a leszármazottaknak implementálniuk kell. Itt kell inicializálniuk a menüt.
 * 
 * getPropertyName():
 *     Absztrakt metódus, amelyet a leszármazottaknak implementálniuk kell. Vissza kell adniuk a menü nevét.
 * 
 * update():
 *     Absztrakt metódus, amelyet a leszármazottaknak implementálniuk kell. Itt kell frissíteniük a menüt.
 * 
 */
package panels;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

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

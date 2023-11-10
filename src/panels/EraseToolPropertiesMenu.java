/**
 * Függvények:
 * 
 * EraseToolPropertiesMenu(Canvas canvas):
 *     Az EraseToolPropertiesMenu osztály konstruktora. Az ősosztály konstruktorát hívja meg, inicializálja a komponenseket.
 * 
 * initMenu():
 *     Az ősosztály metódusának felülírása, nincs implementáció.
 * 
 * getPropertyName():
 *     Visszaadja a tulajdonságok menüjének nevét ("Erase").
 * 
 * update():
 *     Az ősosztály metódusának felülírása, nincs implementáció.
 */
package panels;

public class EraseToolPropertiesMenu extends ToolPropertiesMenu {

    public EraseToolPropertiesMenu(Canvas canvas)
    {
        super(canvas);
    }

    @Override
    protected void initMenu() {

    }

    @Override
    protected String getPropertyName() {
        return "Erase";
    }

    @Override
    protected void update() {

    }
    
}

/**
 * Függvények:
 * 
 * execute(Object triggeredObject):
 *     Az interfész egyetlen függvénye, amelyet az implementáló osztályoknak meg kell valósítaniuk. Végrehajtja a kiválasztott műveletet a paraméterként kapott objektum alapján.
 * 
 * getComponentName(Object triggeredObject):
 *     Az interfész egyetlen függvénye, amelyet az implementáló osztályoknak meg kell valósítaniuk. Visszaadja a komponens nevét a paraméterként kapott objektum alapján.
 * 
 */
package additions;

public interface SimpleActions {
    public void execute(Object triggeredObject);
    public String getComponentName(Object triggeredObject);
}

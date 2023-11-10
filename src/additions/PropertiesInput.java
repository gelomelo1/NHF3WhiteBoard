/**
 * Függvények:
 * 
 * changeValue(Document document):
 *     Az interfész egyetlen függvénye, amelyet az implementáló osztályoknak meg kell valósítaniuk. A dokumentum alapján módosítja a tulajdonságokat.
 * 
 */
package additions;

import javax.swing.text.Document;

public interface PropertiesInput {
    
    public void changeValue(Document document);

}

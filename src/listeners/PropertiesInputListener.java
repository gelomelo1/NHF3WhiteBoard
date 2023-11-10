/**
 * Függvények:
 * 
 * insertUpdate(DocumentEvent e):
 *     A dokumentum változásakor meghívódó metódus. A tulajdonságok bevitelének változását továbbítja
 *     a changeValue(Document document) metódusnak az input dokumentumán keresztül.
 * 
 * removeUpdate(DocumentEvent e):
 *     A dokumentum törlésekor meghívódó metódus. A tulajdonságok bevitelének változását továbbítja
 *     a changeValue(Document document) metódusnak az input dokumentumán keresztül.
 * 
 * changedUpdate(DocumentEvent e):
 *     A dokumentum megváltozásakor meghívódó metódus, de jelenleg nincs implementálva.
 * 
 */
package listeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import additions.PropertiesInput;

public class PropertiesInputListener implements DocumentListener {

    private PropertiesInput propertiesInput;

    public PropertiesInputListener(PropertiesInput propertiesInput)
    {
        this.propertiesInput = propertiesInput;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        propertiesInput.changeValue(e.getDocument());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        propertiesInput.changeValue(e.getDocument());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
    
}

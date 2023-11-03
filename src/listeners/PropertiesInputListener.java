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

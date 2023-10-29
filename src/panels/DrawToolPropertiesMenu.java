package panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import additions.SimpleActions;
import listeners.SimpleActionListener;

public class DrawToolPropertiesMenu extends ToolPropertiesMenu implements SimpleActions {

    private ButtonGroup buttonGroup;
    private ArrayList<JRadioButton> radioButtons;
    private JSlider strokeSlider;
    private HashMap<String, Color> colorSelection;
    private SimpleActionListener simpleActionListener;

    public DrawToolPropertiesMenu(Canvas canvas) {
        super(canvas);
    }

    @Override
    protected void initMenu() {
        add(new JLabel("Color:"));
        colorSelection = new HashMap<String, Color>();
        addColorsToColorSelection();
        buttonGroup = new ButtonGroup();
        simpleActionListener = new SimpleActionListener(this);
        radioButtons = new ArrayList<JRadioButton>();
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new GridLayout(0, 3));
        addRadioButtons("Blue", Color.blue, buttonsContainer);
        addRadioButtons("Red", Color.red, buttonsContainer);
        addRadioButtons("Green", Color.green, buttonsContainer);
        addRadioButtons("Yellow", Color.yellow, buttonsContainer);
        addRadioButtons("Orange", Color.orange, buttonsContainer);
        addRadioButtons("Pink", Color.pink, buttonsContainer);
        addRadioButtons("Black", Color.black, buttonsContainer);
        addRadioButtons("White", Color.white, buttonsContainer);
        add(buttonsContainer);
        add(new JLabel("Stroke:"));
        initStrokeSlider();
    }

    private void addColorsToColorSelection()
    {
        colorSelection.put("Blue", Color.blue);
        colorSelection.put("Red", Color.red);
        colorSelection.put("Green", Color.green);
        colorSelection.put("Yellow", Color.yellow);
        colorSelection.put("Orange", Color.orange);
        colorSelection.put("Pink", Color.pink);
        colorSelection.put("Black", Color.black);
        colorSelection.put("White", Color.white);
    }

    private void addRadioButtons(String buttonText, Color backgroundColor, JPanel buttonsContainer)
    {
        JRadioButton radioButton = new JRadioButton(buttonText);
        if(backgroundColor == getCanvas().getBrush().getColor())
        radioButton.setSelected(true);
        radioButton.setBackground(backgroundColor);
        buttonGroup.add(radioButton);
        buttonsContainer.add(radioButton);
        radioButton.addActionListener(simpleActionListener);
        radioButtons.add(radioButton);
    }

    private void initStrokeSlider()
    {
        strokeSlider = new JSlider(JSlider.HORIZONTAL, 1, getCanvas().getBrush().getMaxStroke(), Float.valueOf(getCanvas().getBrush().getStroke().getLineWidth()).intValue());
        strokeSlider.addChangeListener(simpleActionListener);
        add(strokeSlider);
    }

    @Override
    protected String getPropertyName() {
        return "Draw";
    }

    @Override
    protected void update() {

    }

    @Override
    public void execute(Object triggeredObject) {
        String compName = getComponentName(triggeredObject);
        Color selectedColor = colorSelection.get(compName);
        if(selectedColor != null)
        {
            getCanvas().setBrushColor(selectedColor);   
        }
        else if(compName == strokeSlider.getName());
           getCanvas().getBrush().setStroke(new BasicStroke(strokeSlider.getValue()));
    }

    @Override
    public String getComponentName(Object triggeredObject) {
        for (JRadioButton radioButton : radioButtons) {
            if(radioButton == triggeredObject)
               return radioButton.getText();
        }
        if(strokeSlider == triggeredObject)
        return strokeSlider.getName();
        return null;
    }
    
}

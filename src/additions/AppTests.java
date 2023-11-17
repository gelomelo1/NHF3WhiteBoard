/**
 * Függvények:
 * 
 * moveTest:
 *     Leteszteli az összes irányba, hogy a vásznat nem lehet túlhúzni a megadott határokon.
 * 
 * placeTextTest:
 *     Leteszteli, hogy a megadott pontban le lett e helyezve a JTextArea
 * 
 * eraseTest:
 *     Leteszteli, hogy a radír ha beleér a komponens területébe, akkor kitörli a vászonról
 * 
 * updateTest:
 *     Leteszteli, hogy ha valamit frissíteni kell, a tulajdonságok menüben, akkor az valóban frissül
 * 
 * colorTest:
 *     Leteszteli, hogy valóban a tulajdonságoknál kiválasztott színű a vászon ecset
 * 
 * loadTest:
 *     Leteszteli, hogy betöltésnél valóban belekerül e a vászon konténereibe a komponensek
 * 
 * saveTest:
 *     Leteszteli, hogy a vászon kiadja e a konténereiben lévő összes komponensét mentésre
 * 
 * textPositionTest:
 *     Leteszteli, hogy a beírt értékek megváltoztatják a JTextField méretét, és pozícióját
 * 
 * firstSaveTest:
 *     Leteszteli, hogy első mentésnél Save, és Save As nél is meglegyen kérdezve a név, leírás
 * 
 * brushCopyTest
 *     Leteszteli, hogy a Brush osztálynak működik e a másoló konstruktora
 * 
 */

package additions;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import canvasmodes.DefaultCanvasMode;
import canvasmodes.DrawCanvasMode;
import canvasmodes.EraseCanvasMode;
import canvasmodes.TextCanvasMode;
import containers.CanvasImage;
import containers.CanvasText;
import containers.Drawing;
import containers.SaveContainer;
import panels.DrawToolPropertiesMenu;
import panels.EraseToolPropertiesMenu;
import panels.MoveToolPropertiesMenu;
import panels.TextToolPropertiesMenu;
import uiholders.Menu;

public class AppTests {
    
    Menu menu;

    @Before
    public void setUp()
    {
        menu = new Menu();
    }

    @Test
    public void moveTest()
    {
        menu.getModesController().getDefaultCanvasMode().Move(new Point(0, 1));
        assertEquals(new Point(0, 0), menu.getModesController().getCanvas().getLocation());
        menu.getModesController().getDefaultCanvasMode().Move(new Point(1, 0));
        assertEquals(new Point(0, 0), menu.getModesController().getCanvas().getLocation());
        menu.getModesController().getCanvas().setLocation(new Point(0, -20000));
        menu.getModesController().getDefaultCanvasMode().Move(new Point(0, -1));
        assertEquals(new Point(0, -20000), menu.getModesController().getCanvas().getLocation());
        menu.getModesController().getDefaultCanvasMode().Move(new Point(1, 0));
        assertEquals(new Point(0, -20000), menu.getModesController().getCanvas().getLocation());
        menu.getModesController().getCanvas().setLocation(new Point(-20000, 0));
        menu.getModesController().getDefaultCanvasMode().Move(new Point(-1, 0));
        assertEquals(new Point(-20000, 0), menu.getModesController().getCanvas().getLocation());
        menu.getModesController().getDefaultCanvasMode().Move(new Point(0, 1));
        assertEquals(new Point(-20000, 0), menu.getModesController().getCanvas().getLocation());
        menu.getModesController().getCanvas().setLocation(new Point(-20000, -20000));
        menu.getModesController().getDefaultCanvasMode().Move(new Point(-1, 0));
        assertEquals(new Point(-20000, -20000), menu.getModesController().getCanvas().getLocation());
        menu.getModesController().getDefaultCanvasMode().Move(new Point(0, -1));
        assertEquals(new Point(-20000, -20000), menu.getModesController().getCanvas().getLocation());
    }

    @Test
    public void placeTextTest()
    {
        TextToolPropertiesMenu textToolPropertiesMenu = new TextToolPropertiesMenu(menu.getModesController().getCanvas());
        menu.getModesController().changeMode(new TextCanvasMode(menu.getModesController().getCanvas(), textToolPropertiesMenu), textToolPropertiesMenu);
        TextCanvasMode textCanvasMode = (TextCanvasMode) menu.getModesController().getDefaultCanvasMode();
        textCanvasMode.placeText(new Point(100, 100));
        assertEquals(new Point(50, 50), menu.getModesController().getCanvas().getTexts().get(0).getLocation());
    }

    @Test
    public void eraseTest()
    {
        EraseToolPropertiesMenu eraseToolPropertiesMenu = new EraseToolPropertiesMenu(menu.getModesController().getCanvas());
        menu.getModesController().changeMode(new EraseCanvasMode(menu.getModesController().getCanvas(), eraseToolPropertiesMenu), eraseToolPropertiesMenu);
        EraseCanvasMode eraseCanvasMode = (EraseCanvasMode) menu.getModesController().getDefaultCanvasMode();
        eraseCanvasMode.erase(new Point(130, 130));
        assertEquals(0, menu.getModesController().getCanvas().getComponentCount());
    }

    @Test
    public void updateTest()
    {
        MoveToolPropertiesMenu moveToolPropertiesMenu = new MoveToolPropertiesMenu(menu.getModesController().getCanvas());
        menu.getModesController().changeMode(new DefaultCanvasMode(menu.getModesController().getCanvas(), moveToolPropertiesMenu), moveToolPropertiesMenu);
        menu.getModesController().getDefaultCanvasMode().setMousePos(new Point(500, 500));
        menu.getModesController().getDefaultCanvasMode().update();
        assertEquals(500, moveToolPropertiesMenu.getPosX());
        assertEquals(500, moveToolPropertiesMenu.getPosY());
    }

    @Test
    public void colorTest() throws InterruptedException
    {
        DrawToolPropertiesMenu drawToolPropertiesMenu = new DrawToolPropertiesMenu(menu.getModesController().getCanvas());
        menu.getModesController().changeMode(new DrawCanvasMode(menu.getModesController().getCanvas(), drawToolPropertiesMenu), drawToolPropertiesMenu);
        drawToolPropertiesMenu.getButtonGroup().clearSelection();
        drawToolPropertiesMenu.getRadioButtons().get(4).setSelected(true);
        drawToolPropertiesMenu.getRadioButtons().get(4).getActionListeners()[0].actionPerformed(new ActionEvent(drawToolPropertiesMenu.getRadioButtons().get(4), ActionEvent.ACTION_PERFORMED, null));
        assertEquals(Color.orange, menu.getModesController().getCanvas().getBrush().getColor());
    }

    @Test
    public void loadTest()
    {
        ArrayList<Drawing> curves = new ArrayList<Drawing>();
        curves.add(new Drawing());
        for(int i = 10; i < 200; i++)
            curves.get(0).addPoint(new Point(10, i));
        curves.add(new Drawing());
        for(int i = 10; i < 200; i++)
        curves.get(1).addPoint(new Point(80, i));
        ArrayList<CanvasText> canvasTexts = new ArrayList<CanvasText>();
        CanvasText canvasText = new CanvasText();
        canvasText.setBounds(new Rectangle(160, 160, 100, 100));
        canvasTexts.add(canvasText);
        ArrayList<CanvasImage> canvasImages = new ArrayList<CanvasImage>();
        SaveContainer saveContainer = new SaveContainer(canvasImages, canvasTexts, curves, "", "");
        menu.getModesController().getCanvas().loadCanvas(saveContainer);
        assertEquals(2, menu.getModesController().getCanvas().getCurves().size());
        assertEquals(1, menu.getModesController().getCanvas().getTexts().size());
    }

    @Test
    public void saveTest()
    {
        ArrayList<Drawing> curves = new ArrayList<Drawing>();
        curves.add(new Drawing());
        for(int i = 10; i < 200; i++)
            curves.get(0).addPoint(new Point(10, i));
        curves.add(new Drawing());
        for(int i = 10; i < 200; i++)
        curves.get(1).addPoint(new Point(80, i));
        ArrayList<CanvasText> canvasTexts = new ArrayList<CanvasText>();
        CanvasText canvasText = new CanvasText();
        canvasText.setBounds(new Rectangle(160, 160, 100, 100));
        canvasTexts.add(canvasText);
        ArrayList<CanvasImage> canvasImages = new ArrayList<CanvasImage>();
        SaveContainer saveContainer = new SaveContainer(canvasImages, canvasTexts, curves, "", "");
        menu.getModesController().getCanvas().loadCanvas(saveContainer);
        saveContainer = menu.getModesController().getCanvas().saveCanvas();
        assertEquals(2, saveContainer.getDrawings().size());
        assertEquals(1, saveContainer.getTexts().size());
    }

    @Test
    public void textPositionTest()
    {
        menu.getModesController().getCanvas().newCanvas();
        TextToolPropertiesMenu textToolPropertiesMenu = new TextToolPropertiesMenu(menu.getModesController().getCanvas());
        menu.getModesController().changeMode(new TextCanvasMode(menu.getModesController().getCanvas(), textToolPropertiesMenu), textToolPropertiesMenu);
        TextCanvasMode textCanvasMode = (TextCanvasMode) menu.getModesController().getDefaultCanvasMode();
        textCanvasMode.placeText(new Point(100, 100));
        assertEquals(new Rectangle(50, 50, 100, 100), menu.getModesController().getCanvas().getComponent(0).getBounds());
        textToolPropertiesMenu.getPosX().setEditable(false);
        textToolPropertiesMenu.getPosY().setEditable(false);
        textToolPropertiesMenu.getFieldWidth().setEditable(false);
        textToolPropertiesMenu.getFieldHeight().setEditable(false);
        textToolPropertiesMenu.getPosX().setText("200");
        textToolPropertiesMenu.getPosY().setText("200");
        textToolPropertiesMenu.getFieldWidth().setText("50");
        textToolPropertiesMenu.getFieldHeight().setText("50");
        assertEquals(new Rectangle(200, 200, 50, 50), menu.getModesController().getCanvas().getComponent(0).getBounds());
    }

    @Test
    public void firstSaveTest()
    {
        menu.getModesController().getCanvas().newCanvas();
        assertNotNull(menu.getMenuBar().Save(false));
    }

    @Test
    public void burshCopyTest()
    {
        Brush brush = new Brush();
        brush.setColor(Color.yellow);
        brush.setStroke(new BasicStroke(20));
        Brush otheBrush = new Brush(brush);
        assertEquals(otheBrush.getColor(), brush.getColor());
        assertEquals(otheBrush.getStroke(), brush.getStroke());
    }
}

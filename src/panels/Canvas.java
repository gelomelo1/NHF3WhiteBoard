/**
* Függvények:
 * 
 * Canvas(JFrame jf):
 *     A Canvas osztály konstruktora, inicializálja a komponenseket és a rajzterületet.
 * 
 * initCanvas(JFrame jf):
 *     Inicializálja a rajzterület beállításait, mint a méret és a görgetősávok.
 * 
 * initComp(JFrame jf):
 *     Inicializálja a komponenseket, mint a görgetősávot, a háttért és a rajzeszközöket.
 * 
 * initializeLoadedData():
 *     Betölti a képeket, szövegeket, és görbéket a tárolóból, inicializálva a megjelenítésüket.
 * 
 * drawCurves(Graphics2D g2):
 *     Kirajzolja a görbék vonalait a megadott Graphics2D objektum segítségével.
 * 
 * paintComponent(Graphics g):
 *     Az osztály JPanel ősosztályának paintComponent() metódusát felülírva kirajzolja
 *     a háttért és a görbéket a rajzterületre.
 * 
 * setMouseListener(MouseAdapter mouseListener):
 *     Beállítja az egér eseménykezelőt a Canvas osztályhoz.
 * 
 * updateMenu():
 *     Frissíti a toolPropertiesMenu-t a kijelölt objektumok tulajdonságaival.
 * 
 * addCurve(Drawing curve):
 *     Hozzáad egy görbét a curves listához.
 * 
 * setMousePos(Point point):
 *     Beállítja az egér pozícióját.
 * 
 * setToolPropertiesMenu(ToolPropertiesMenu toolPropertiesMenu):
 *     Beállítja a toolPropertiesMenu-t.
 * 
 * getMousePos():
 *     Visszaadja az egér pozícióját.
 * 
 * getBrush():
 *     Visszaadja a rajzecsetet.
 * 
 * setBrushColor(Color c):
 *     Beállítja a rajzecset színét.
 * 
 * setStroke(int stroke):
 *     Beállítja a rajzecset vonalvastagságát.
 * 
 * addText(Point point):
 *     Hozzáad egy szövegobjektumot a rajzterülethez a megadott pozícióval.
 * 
 * addImage(Point point, String path):
 *     Hozzáad egy képobjektumot a rajzterülethez a megadott pozícióval és fájlútvonallal.
 * 
 * getCurves():
 *     Visszaadja a görbék listáját.
 * 
 * getTexts():
 *     Visszaadja a szövegek listáját.
 * 
 * getImages():
 *     Visszaadja a képek listáját.
 * 
 * getCanvasObjects():
 *     Visszaadja az összes rajzobjektumot tartalmazó listát.
 * 
 * getSelectedText():
 *     Visszaadja a kijelölt szövegobjektumot.
 * 
 * setSelectedText(CanvasText selectedText):
 *     Beállítja a kijelölt szövegobjektumot.
 * 
 * getSelectedImage():
 *     Visszaadja a kijelölt képobjektumot.
 * 
 * setSelectedImage(CanvasImage selectedImage):
 *     Beállítja a kijelölt képobjektumot.
 * 
 * getMaxWidth():
 *     Visszaadja a maximális szélességet.
 * 
 * getMaxHeight():
 *     Visszaadja a maximális magasságot.
 * 
 * getCanvasLayout():
 *     Visszaadja a görgetősávot.
 * 
 * newCanvas():
 *     Inicializálja újra a rajzterületet, törlve az eddigi objektumokat.
 * 
 * saveCanvas():
 *     Létrehoz egy SaveContainer objektumot a rajzterület állapotának mentéséhez.
 * 
 * loadCanvas(SaveContainer container):
 *     Betölti a rajzterület állapotát egy SaveContainer objektumból.
 */
package panels;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.OverlayLayout;
import additions.Background;
import additions.Brush;
import additions.CanvasActivity;
import additions.ImageTransferHandler;
import additions.SelectionRectangle;
import additions.Transactions;
import containers.CanvasImage;
import containers.CanvasText;
import containers.Drawing;
import containers.SaveContainer;

public class Canvas extends JPanel {

    //base properties
    private int maxWidth = 20000;
    private int maxHeight = 20000;
    private static int selectionWidth = 5;

    private JScrollPane canvasLayout;
    private HistoryPanel historyPanel;
    public JPanel middlePanel;
    private Background background;
    private MouseAdapter selectedListener;
    private ToolPropertiesMenu toolPropertiesMenu;
    private Point mousePos;
    private SelectionRectangle selectionRectangle;
    private Brush brush;
    private ArrayList<CanvasActivity> selectedCanvasActivities;

    //containers
    private ArrayList<Drawing> curves;
    private ArrayList<CanvasText> texts;
    private ArrayList<CanvasImage> images;

    public Canvas(JFrame jf)
    {
        initComp(jf);
        initCanvas(jf);
    }

    private void initCanvas(JFrame jf)
    {
        setPreferredSize(new Dimension(maxWidth, maxHeight));
        canvasLayout.setViewportView(this);
        canvasLayout.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        canvasLayout.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void initComp(JFrame jf)
    {
        setTransferHandler(new ImageTransferHandler(this));
        canvasLayout = new JScrollPane();
        canvasLayout.setAlignmentX(Component.RIGHT_ALIGNMENT);
        canvasLayout.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        historyPanel = new HistoryPanel();
        middlePanel = new JPanel();
        OverlayLayout overlayLayout = new OverlayLayout(middlePanel);
        middlePanel.setLayout(overlayLayout);
        middlePanel.add(historyPanel);
        middlePanel.add(canvasLayout);
        setLayout(null);
        jf.add(middlePanel, BorderLayout.CENTER);
        background = new Background();
        newCanvas();
        brush = new Brush();
        mousePos = new Point(0, 0);
        toolPropertiesMenu = null;
        middlePanel.revalidate();
    }

    private void initializeLoadedData()
    {
        historyPanel.reset();
        removeAll();
        selectedCanvasActivities = new ArrayList<CanvasActivity>();
        for (CanvasImage canvasImage : images) {
            canvasImage.loadImage();
            add(canvasImage);
        }
        for (CanvasText canvasText : texts) {
            add(canvasText);
        }
        for (Drawing drawing : curves) {
            drawing.initializeBrush();
        }
        repaint();
    }

    private void drawCurves(Graphics2D g2)
    {
        for (Drawing curve : curves) {
            curve.draw(g2);
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        background.drawBackground(g2, maxWidth, maxHeight);
        if(selectionRectangle != null)
        selectionRectangle.drawSelectionRectangle(g2);
        drawCurves(g2);
        middlePanel.repaint();
    }

    public void setMouseListener(MouseAdapter mouseListener)
    {
        if(selectedListener != null)
        {
            removeMouseListener(selectedListener);
            removeMouseMotionListener(selectedListener);
            removeMouseWheelListener(selectedListener);
        }
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addMouseWheelListener(mouseListener);
        selectedListener = mouseListener;
    }

    public void updateMenu()
    {
        toolPropertiesMenu.update();
    }

    public void addCurve(Drawing curve)
    {
        curves.add(curve);
    }

    public void setMousePos(Point point)
    {
        mousePos.x = point.x;
        mousePos.y = point.y;
    }

    public void setToolPropertiesMenu(ToolPropertiesMenu toolPropertiesMenu)
    {
        this.toolPropertiesMenu = toolPropertiesMenu;
    }

    public Point getMousePos()
    {
        return mousePos;
    }

    public Brush getBrush()
    {
        return brush;
    }

    public void setBrushColor(Color c)
    {
        brush.setColor(c);
    }

    public void setStroke(int stroke)
    {
        brush.setStroke(new BasicStroke(stroke));
    }

    public CanvasText addText(Point point)
    {
        CanvasText text = new CanvasText();
        text.setBounds(point.x, point.y, 100, 100);
        add(text);
        texts.add(text);
        return text;
    }

    public void addText(CanvasText canvasText)
    {
        add(canvasText);
        texts.add(canvasText);
    }

    public CanvasImage addImage(Point point, int width, int height, String path)
    {
        CanvasImage image = new CanvasImage(path);
        image.setBounds(point.x, point.y, width, height);
        add(image);
        images.add(image);
        return image;
    }

    public void addImage(CanvasImage canvasImage)
    {
        add(canvasImage);
        images.add(canvasImage);
    }

    public void addTransactionToQueue(Transactions transaction)
    {
        historyPanel.getQueue().addItem(transaction);
    }

    public MouseAdapter getSelectedListener()
    {
        return selectedListener;
    }

    public ArrayList<Drawing> getCurves()
    {
        return curves;
    }

    public ArrayList<CanvasText> getTexts()
    {
        return texts;
    }

    public ArrayList<CanvasImage> getImages()
    {
        return images;
    }

    public ArrayList<CanvasActivity> getCanvasObjects()
    {
        ArrayList<CanvasActivity> canvObjects = new ArrayList<CanvasActivity>();
        for (Drawing drawings : curves) {
            canvObjects.add(drawings);
        }
        for (CanvasText canvasText : texts) {
            canvObjects.add(canvasText);
        }
        for (CanvasImage image : images) {
            canvObjects.add(image);
        }
        return canvObjects;
    }

    public ArrayList<CanvasText> getSelectedText()
    {
        ArrayList<CanvasText> selectedText = new ArrayList<CanvasText>();
        for (CanvasActivity canvasActivity : selectedCanvasActivities) {
            if(canvasActivity instanceof CanvasText)
            selectedText.add((CanvasText) canvasActivity);
        }
        return selectedText;
    }

    public ArrayList<Drawing> getSelectedCurve()
    {
        ArrayList<Drawing> selectedCurve = new ArrayList<Drawing>();
        for (CanvasActivity canvasActivity : selectedCanvasActivities) {
            if(canvasActivity instanceof Drawing)
            selectedCurve.add((Drawing) canvasActivity);
        }
        return selectedCurve;
    }

    public ArrayList<CanvasImage> getSelectedImage()
    {
        ArrayList<CanvasImage> selectedImage = new ArrayList<CanvasImage>();
        for (CanvasActivity canvasActivity : selectedCanvasActivities) {
            if(canvasActivity instanceof CanvasImage)
            selectedImage.add((CanvasImage) canvasActivity);
        }
        return selectedImage;
    }

    public ArrayList<CanvasActivity> getSelectedCanvasActivities()
    {
        return selectedCanvasActivities;
    }

    public void addSelectedCanvasActivity(CanvasActivity canvasActivity)
    {
        canvasActivity.setSelected();
        selectedCanvasActivities.add(canvasActivity);
    }

    public void removeSelectedCanvasActivity(CanvasActivity canvasActivity)
    {
        canvasActivity.resetSelected();
        selectedCanvasActivities.remove(canvasActivity);
    }

    public void resetActivitiesSelection()
    {
        for (CanvasActivity canvasActivity : selectedCanvasActivities) {
            canvasActivity.resetSelected();
        }
        selectedCanvasActivities = new ArrayList<CanvasActivity>();
    }

    public int getMaxWidth()
    {
        return maxWidth;
    }

    public int getMaxHeight()
    {
        return maxHeight;
    }

    public JScrollPane getCanvasLayout()
    {
        return canvasLayout;
    }

    public void setSelectionRectangle(SelectionRectangle rectangle)
    {
        selectionRectangle = rectangle;
    }

    public SelectionRectangle getSelectionRectangle()
    {
        return selectionRectangle;
    }

    public ToolPropertiesMenu getToolPropertiesMenu()
    {
        return toolPropertiesMenu;
    }

    public static int getSelectionWidth()
    {
        return selectionWidth;
    }

    public void newCanvas()
    {
        curves = new ArrayList<Drawing>();
        texts = new ArrayList<CanvasText>();
        images = new ArrayList<CanvasImage>();
        initializeLoadedData();
    }

    public SaveContainer saveCanvas()
    {
        return new SaveContainer(images, texts, curves, "", "");
    }

    public void loadCanvas(SaveContainer container)
    {
        images = container.getImages();
        texts = container.getTexts();
        curves = container.getDrawings();
        initializeLoadedData();
    }
}

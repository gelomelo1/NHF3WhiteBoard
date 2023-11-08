package panels;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import additions.Background;
import additions.Brush;
import additions.CanvasActivity;
import containers.CanvasImage;
import containers.CanvasText;
import containers.Drawing;
import containers.SaveContainer;

public class Canvas extends JPanel {

    //base properties
    private int maxWidth = 20000;
    private int maxHeight = 20000;

    private JScrollPane canvasLayout;
    private Background background;
    private MouseAdapter selectedListener;
    private ToolPropertiesMenu toolPropertiesMenu;
    private Point mousePos;
    private Brush brush;
    private CanvasText selectedText;
    private CanvasImage selectedImage;

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
        canvasLayout = new JScrollPane();
        setLayout(null);
        jf.add(canvasLayout, BorderLayout.CENTER);
        background = new Background();
        newCanvas();
        brush = new Brush();
        mousePos = new Point(0, 0);
        toolPropertiesMenu = null;
    }

    private void initializeLoadedData()
    {
        removeAll();
        for (CanvasImage canvasImage : images) {
            canvasImage.loadImage();
            add(canvasImage);
        }

        for (CanvasText canvasText : texts) {
            add(canvasText);
        }
        repaint();
    }

    private void drawCurves(Graphics2D g2)
    {
        for (Drawing curve : curves) {
            g2.setColor(curve.getColor());
            g2.setStroke(curve.getStroke());
            for(int i = 1; i < curve.getCurve().size(); i++)
            {
                int x1 = curve.getCurve().get(i - 1).x;
                int y1 = curve.getCurve().get(i - 1).y;
                int x2 = curve.getCurve().get(i).x;
                int y2 = curve.getCurve().get(i).y;
                g2.drawLine(x1, y1, x2, y2);
            }
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        background.drawBackground(g2, maxWidth, maxHeight);
        drawCurves(g2);
        g2.setStroke(new BasicStroke(0));
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
        curve.setBrush(new Brush(brush));
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

    public CanvasImage addImage(Point point, String path)
    {
        CanvasImage image = new CanvasImage(path);
        image.setBounds(point.x, point.y, 100, 100);
        add(image);
        images.add(image);
        return image;
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

    public CanvasText getSelectedText()
    {
        return selectedText;
    }

    public void setSelectedText(CanvasText selectedText)
    {
        this.selectedText = selectedText;
    }

    public CanvasImage getSelectedImage()
    {
        return selectedImage;
    }

    public void setSelectedImage(CanvasImage selectedImage)
    {
        this.selectedImage = selectedImage;
    }

    public int getMaxWidth()
    {
        return maxWidth;
    }

    public int getMaxHeight()
    {
        return maxHeight;
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

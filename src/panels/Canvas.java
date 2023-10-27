package panels;

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

public class Canvas extends JPanel {

    //base properties
    private int maxWidth = 20000;
    private int maxHeight = 20000;

    private JScrollPane canvasLayout;
    private Background background;
    private MouseAdapter selectedListener;
    private Brush brush;

    private ArrayList<ArrayList<Point>> curves;

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
        jf.add(canvasLayout, BorderLayout.CENTER);
        background = new Background();
        curves = new ArrayList<ArrayList<Point>>();
        brush = new Brush();
    }

    private void drawCurves(Graphics2D g2)
    {
        Color primaryColor = g2.getColor();
        g2.setColor(brush.getColor());
        g2.setStroke(brush.getStroke());
        for (ArrayList<Point> curve : curves) {
            for(int i = 1; i < curve.size(); i++)
            {
                int x1 = curve.get(i - 1).x;
                int y1 = curve.get(i - 1).y;
                int x2 = curve.get(i).x;
                int y2 = curve.get(i).y;
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

    public void addCurve(ArrayList<Point> curve)
    {
        curves.add(curve);
    }
}

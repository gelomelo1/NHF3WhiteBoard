package panels;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public abstract class FrameMenus extends JFrame {
   
    private JFrame mainFrame;

    public FrameMenus(String name, JFrame mainFrame, int width, int height)
    {
        super(name);
        this.mainFrame = mainFrame;
        this.mainFrame.setEnabled(false);
        setSize(width, height);
        setVisible(true);
        addWindowListener(new FrameMenusListener());
    }

    private class FrameMenusListener extends WindowAdapter
    {
        @Override
        public void windowClosed(WindowEvent e) {
            mainFrame.setEnabled(true);
        }

        @Override
        public void windowClosing(WindowEvent e) {
            mainFrame.setEnabled(true);
        }
    }
}

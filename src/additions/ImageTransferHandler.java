package additions;

import javax.swing.JOptionPane;
import javax.swing.TransferHandler;
import canvasmodes.ImageCanvasMode;
import panels.Canvas;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.List;

public class ImageTransferHandler extends TransferHandler {

    private Canvas canvas;
    private int offset = 50;

    public ImageTransferHandler(Canvas canvas)
    {
        super(null);
        this.canvas = canvas;
    }
    
    @Override
    public boolean canImport(TransferHandler.TransferSupport support)
    {
        return  support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport support)
    {
        if(!canImport(support))
        return false;

        MouseAdapter originalMouseAdapter = canvas.getSelectedListener();
        Transferable transferable = support.getTransferable();
        ImageCanvasMode imageCanvasMode = new ImageCanvasMode(canvas, null);
        Point mousePoint = canvas.getMousePosition();
        int width = imageCanvasMode.getDefaultImageWidth();
        int posX = 0;
        try {
            List<File> images = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
            for (File file : images) {
                imageCanvasMode.placeImage(new Point(mousePoint.x + posX, mousePoint.y), file.getAbsolutePath());
                posX += width + offset;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Cannot copy the image!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        canvas.setMouseListener(originalMouseAdapter);
        return true;
    }
}

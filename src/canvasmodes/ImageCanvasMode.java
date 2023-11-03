package canvasmodes;

import java.awt.Point;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import containers.CanvasImage;
import fileios.FileHandler;
import listeners.ImageCanvasModeListener;
import panels.Canvas;
import panels.ToolPropertiesMenu;

public class ImageCanvasMode extends DefaultCanvasMode {

public ImageCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu)
{
    super(canvas, toolPropertiesMenu, false);
    setMouseListener(new ImageCanvasModeListener(this));
}  

public void placeImage(Point point)
{
    String path = checkImageData();
    if(path != null)
    {
    String destination = Paths.get("").toAbsolutePath().resolve("Temp").toString();
    if(!FileHandler.copyFile(path, destination))
    JOptionPane.showMessageDialog(null, "Cannot save the image", "Warning", JOptionPane.WARNING_MESSAGE);
    destination += path.substring(path.lastIndexOf("\\"));
    CanvasImage image = getCanvas().addImage(new Point(point.x - 50, point.y - 50), destination);
    getCanvas().repaint();
    getCanvas().setSelectedImage(image);
    }
}

private String checkImageData()
{
    String[] extensions = {"png", "jpg", "jpeg"};
    String path = null;
    do
    {
     path = JOptionPane.showInputDialog("Please give the image path:");
    if(FileHandler.isFileExist(path))
    {
       if(FileHandler.validExtension(path, extensions))
       return path;
       else
    JOptionPane.showMessageDialog(null, "File extension not supported", "Error", JOptionPane.ERROR_MESSAGE);   
    }
    else if(path != null)
    JOptionPane.showMessageDialog(null, "No image was found", "Error", JOptionPane.ERROR_MESSAGE);   
    }while(path != null);
    return null;
}

public void setImageFocus(CanvasImage image)
{
    getCanvas().setSelectedImage(image);
}

public CanvasImage imageClicked(Point point)
{
    for (CanvasImage image : getCanvas().getImages()) {
        if(image.isCollided(point))
         return image;
    }
    return null;
}

}

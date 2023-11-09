package canvasmodes;

import java.awt.Point;
import java.io.File;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import containers.CanvasImage;
import fileios.FileHandler;
import listeners.ImageCanvasModeListener;
import panels.Canvas;
import panels.ToolPropertiesMenu;
import uiholders.Menu;

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
    String destination = Paths.get("").toAbsolutePath().resolve(Menu.getTempFolder()).toString();
    String rename = checkRename(path);
    if(!FileHandler.copyFile(path, destination, rename))
    JOptionPane.showMessageDialog(null, "Cannot save the image!", "Warning", JOptionPane.WARNING_MESSAGE);
    if(rename != null)
    destination += "\\" + rename;
    else
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
    JOptionPane.showMessageDialog(null, "File extension not supported!", "Warning", JOptionPane.WARNING_MESSAGE);   
    }
    else if(path != null)
    JOptionPane.showMessageDialog(null, "No image was found!", "Warning", JOptionPane.WARNING_MESSAGE);   
    }while(path != null);
    return null;
}

private String checkRename(String path)
{
        String name = path.substring(path.lastIndexOf("\\") + 1);
        File folder = Paths.get("").toAbsolutePath().resolve(Menu.getTempFolder()).toFile();
        File[] images = folder.listFiles();
        boolean isExist;
        String rename = name;
        int index = 0;
        do
        {
        isExist = false;
        for (File file : images) {
            if(file.getName().equals(rename))
            {
                isExist = true;
                break;
            }
        }
        if(isExist)
        {
            rename = name.substring(0, name.lastIndexOf(".")) + Integer.toString(index) + "." + name.substring(name.lastIndexOf(".")+ 1);
            index++;
        }
        }while(isExist);
        if(rename.equals(name))
        return null;
        return rename;
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

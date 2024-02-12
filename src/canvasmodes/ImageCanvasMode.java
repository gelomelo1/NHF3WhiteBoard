/**
 * Függvények:
 * 
 * ImageCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu):
 *     Az ImageCanvasMode osztály konstruktora. Beállítja a képobjektumokat kezelő mód alapértelmezett tulajdonságait.
 * 
 * placeImage(Point point):
 *     Elhelyezi a képet a megadott pontra. Ellenőrzi az adatokat, másolja a fájlt a temporális mappába, majd hozzáadja a képet a vászonhoz.
 * 
 * checkImageData():
 *     Ellenőrzi a kép elérési útját és a fájlnév kiterjesztését, majd a felhasználótól kéri be az adatokat.
 * 
 * checkRename(String path):
 *     Ellenőrzi, hogy a fájlnév ütközik-e már a temporális mappában lévő fájlokkal, és ha igen, akkor módosítja a nevet.
 * 
 * setImageFocus(CanvasImage image):
 *     Beállítja a megadott képet a kijelölt képnek a vásznon.
 * 
 * imageClicked(Point point):
 *     Ellenőrzi, hogy a megadott ponton lévő képobjektumra kattintottak-e, és visszaadja a talált objektumot.
 * 
 */
package canvasmodes;

import java.awt.Point;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import additions.CanvasActivity;
import additions.PlaceEraseTransaction;
import additions.PlaceEraseTransaction.PlaceEraseForwardMode;
import containers.CanvasImage;
import fileios.FileHandler;
import listeners.ImageCanvasModeListener;
import panels.Canvas;
import panels.ToolPropertiesMenu;
import uiholders.Menu;

public class ImageCanvasMode extends DefaultCanvasMode {

    private int defaultImageWidth = 100;
    private int defaultImageHeight = 100;

public ImageCanvasMode(Canvas canvas, ToolPropertiesMenu toolPropertiesMenu)
{
    super(canvas, toolPropertiesMenu, false);
    setMouseListener(new ImageCanvasModeListener(this));
}  

public void placeImage(Point point, String path)
{
    if(path == null)
    path = checkImageData();
    else if(!checkExtension(path))
    path = null;
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
    CanvasImage image = getCanvas().addImage(new Point(point.x - 50, point.y - 50), defaultImageWidth, defaultImageHeight, destination);
    getCanvas().repaint();
    getCanvas().resetActivitiesSelection();
    ArrayList<CanvasActivity> images = new ArrayList<CanvasActivity>();
    images.add(image);
    getCanvas().addTransactionToQueue(new PlaceEraseTransaction(getCanvas(), images, PlaceEraseForwardMode.Place));
    }
}

private String checkImageData()
{
    String path = null;
    do
    {
     path = JOptionPane.showInputDialog("Please give the image path:");
    if(FileHandler.isFileExist(path))
    {
        if(checkExtension(path))
        return path;
    }
    else if(path != null)
    JOptionPane.showMessageDialog(null, "No image was found!", "Warning", JOptionPane.WARNING_MESSAGE);   
    }while(path != null);
    return null;
}

private boolean checkExtension(String path)
{
    String[] extensions = {"png", "jpg", "jpeg"};
    if(FileHandler.validExtension(path, extensions))
    return true;
else
JOptionPane.showMessageDialog(null, "File extension not supported!", "Warning", JOptionPane.WARNING_MESSAGE);
return false;   
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
    getCanvas().resetActivitiesSelection();
    getCanvas().addSelectedCanvasActivity(image);
}

public CanvasImage imageClicked(Point point)
{
    for (CanvasImage image : getCanvas().getImages()) {
        if(image.isCollided(point))
         return image;
    }
    return null;
}

public int getDefaultImageWidth()
{
    return defaultImageWidth;
}

public int getDefaultImageHeight()
{
    return defaultImageHeight;
}

}

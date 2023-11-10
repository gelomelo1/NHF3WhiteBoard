/**
 * Függvények:
 * 
 * SaveContainer(ArrayList<CanvasImage> images, ArrayList<CanvasText> texts, ArrayList<Drawing> drawings, String name, String description):
 *     A SaveContainer osztály konstruktora. Beállítja az objektumok listáit és a metaadatokat.
 * 
 * toString():
 *     Visszaadja a konténer nevét.
 * 
 * getImages():
 *     Visszaadja a menteni kívánt CanvasImage objektumok listáját.
 * 
 * getTexts():
 *     Visszaadja a menteni kívánt CanvasText objektumok listáját.
 * 
 * getDrawings():
 *     Visszaadja a menteni kívánt Drawing objektumok listáját.
 * 
 * getName():
 *     Visszaadja a konténer nevét.
 * 
 * getDescription():
 *     Visszaadja a konténer leírását.
 * 
 * changeImagesPath(String folder):
 *     Változtatja az összes CanvasImage objektum elérési útját a megadott könyvtárra.
 * 
 */

package containers;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveContainer implements Serializable {
    private ArrayList<CanvasImage> images;
    private ArrayList<CanvasText> texts;
    private ArrayList<Drawing> drawings;
    private String name;
    private String description;
    
    public SaveContainer(ArrayList<CanvasImage> images, ArrayList<CanvasText> texts, ArrayList<Drawing> drawings, String name, String description)
    {
        this.images = images;
        this.texts = texts;
        this.drawings = drawings;
        this.name = name;
        this.description = description;
    }

    public String toString()
    {
        return getName();
    }

    public ArrayList<CanvasImage> getImages()
    {
        return images;
    }

    public ArrayList<CanvasText> getTexts()
    {
        return texts;
    }

    public ArrayList<Drawing> getDrawings()
    {
        return drawings;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public void changeImagesPath(String folder)
    {
        for (CanvasImage canvasImage : images) {
            String name = canvasImage.getPath().substring(canvasImage.getPath().lastIndexOf("\\") + 1);
            canvasImage.setPath(folder + "\\" + name);
        }
    }
}

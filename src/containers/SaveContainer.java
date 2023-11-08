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

/**
 * Függvények:
 * 
 * ImageCanvasModeListener(ImageCanvasMode imageCanvasMode):
 *     Az osztály konstruktora, inicializálja az egérfigyelőt a kép vászon móddal.
 * 
 * mouseClicked(MouseEvent e):
 *     Az egér kattintás eseményére meghívódó metódus. Az egér bal gombjának lenyomásakor a kép vászon módban meghatározza,
 *     hogy az egér pozíciójában található képre történt-e kattintás, és ennek megfelelően elhelyezi vagy fókuszba helyezi a képet.
 * 
 * mouseDragged(MouseEvent e):
 *     Az egér húzás eseményére meghívódó metódus. Az egér jobb gombjának lenyomása esetén hívja az ősosztály mouseDragged metódusát.
 * 
 * mouseMoved(MouseEvent e):
 *     Az egér mozgás eseményére meghívódó metódus. Jelenleg üres, nincs funkciója.
 * 
 */
package listeners;

import java.awt.event.MouseEvent;
import canvasmodes.ImageCanvasMode;
import containers.CanvasImage;

public class ImageCanvasModeListener extends DefaultCanvasModeListener {
    private ImageCanvasMode imageCanvasMode;

    public ImageCanvasModeListener(ImageCanvasMode imageCanvasMode)
    {
        super(imageCanvasMode);
        this.imageCanvasMode = imageCanvasMode;
    }

        public void mouseClicked(MouseEvent e)
    {
        if(getPressedMouse() == MouseEvent.BUTTON1)
        {
            CanvasImage selectedImage = imageCanvasMode.imageClicked(e.getPoint());
            if(selectedImage != null)
            imageCanvasMode.setImageFocus(selectedImage);
            else
            imageCanvasMode.placeImage(e.getPoint());
            imageCanvasMode.update();
        }
    }

    public void mouseDragged(MouseEvent e)
    {
        if(getPressedMouse() == MouseEvent.BUTTON3)
            super.mouseDragged(e);
    }

    public void mouseMoved(MouseEvent e)
    {

    }
}

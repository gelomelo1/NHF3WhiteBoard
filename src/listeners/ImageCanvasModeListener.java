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

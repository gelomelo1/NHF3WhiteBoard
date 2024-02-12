package additions;

import java.util.ArrayList;

import canvasmodes.EraseCanvasMode;
import panels.Canvas;

public class PlaceEraseTransaction extends Transactions {

    private PlaceEraseForwardMode placeEraseForwardMode;

    public enum PlaceEraseForwardMode
    {
        Place,
        Erase
    }

    public PlaceEraseTransaction(Canvas canvas, ArrayList<CanvasActivity> transactionObjects, PlaceEraseForwardMode placeEraseForwardMode) {
        super(canvas, transactionObjects, placeEraseForwardMode.toString());
        this.placeEraseForwardMode = placeEraseForwardMode;
    }

    @Override
    protected void executeModeBackward() {
        if(placeEraseForwardMode == PlaceEraseForwardMode.Place)
        erase();
        else
        place();
    }

    @Override
    protected void executeModeForward() {
        if(placeEraseForwardMode == PlaceEraseForwardMode.Place)
        place();
        else
        erase();
    }

    private void erase()
    {
        EraseCanvasMode eraseCanvasMode = new EraseCanvasMode(getCanvas(), null);
        resetListenersSelections(eraseCanvasMode);
        eraseCanvasMode.erase(getTransactionObjects(), true);
    }

    private void place()
    {
        getCanvas().resetActivitiesSelection();
        for (CanvasActivity canvasActivity : getTransactionObjects()) {
            canvasActivity.placeItself(getCanvas());
        }
        getCanvas().repaint();
    }
    
}

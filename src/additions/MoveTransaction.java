package additions;

import java.awt.Point;
import java.util.ArrayList;
import canvasmodes.DefaultCanvasMode;
import panels.Canvas;

public class MoveTransaction extends Transactions {

    private Point backwardVector;

    public MoveTransaction(Canvas canvas, ArrayList<CanvasActivity> transactionObjects, Point backwardVector) {
        super(canvas ,transactionObjects, "Move");
        this.backwardVector = backwardVector;
    }

    @Override
    public void executeModeBackward() {
        move(backwardVector);
    }

    @Override
    protected void executeModeForward() {
        move(new Point(backwardVector.x * -1, backwardVector.y * -1));
    }

    private void move(Point selectedVector)
    {
        DefaultCanvasMode defaultCanvasMode = new DefaultCanvasMode(getCanvas(), null);
        resetListenersSelections(defaultCanvasMode);
        for (CanvasActivity canvasActivity : getTransactionObjects()) {
            getCanvas().addSelectedCanvasActivity(canvasActivity);
        }
        defaultCanvasMode.confirmSelection();
        defaultCanvasMode.moveObjects(selectedVector);
        defaultCanvasMode.resetSelection();
    }
    
}

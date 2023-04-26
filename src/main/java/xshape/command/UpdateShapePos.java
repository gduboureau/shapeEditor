package xshape.command;

// import javafx.geometry.Point2D;
import java.awt.geom.Point2D;
import xshape.shape.Shape;

public class UpdateShapePos implements ICommand {
    private final Shape shape;
    private final Point2D oldPos, newPos;

    public UpdateShapePos(Shape shape, Point2D newPos) {
        this.shape = shape;
        this.oldPos = shape.position();
        this.newPos = newPos;
    }

    @Override
    public void apply() {
        shape.position(newPos);
    }

    @Override
    public void undo() {
        shape.position(oldPos);
    }
    
}

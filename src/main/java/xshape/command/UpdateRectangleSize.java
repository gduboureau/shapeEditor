package xshape.command;

import java.awt.geom.Point2D;

import xshape.shape.Shape;

public class UpdateRectangleSize implements ICommand {
    private final Shape shape;
    private final Point2D oldSize, newSize;

    public UpdateRectangleSize(Shape shape, Point2D oldSize, Point2D newSize) {
        this.shape = shape;
        this.oldSize = oldSize;
        this.newSize = newSize;
    }

    @Override
    public void apply() {
        shape.size(newSize);
        shape.draw();
    }

    @Override
    public void undo() {
        shape.size(oldSize);
        shape.draw();
    }
}

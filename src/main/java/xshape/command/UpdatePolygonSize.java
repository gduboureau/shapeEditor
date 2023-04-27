package xshape.command;

import xshape.shape.Polygon;
import xshape.shape.Shape;

public class UpdatePolygonSize implements ICommand {
    private final Shape shape;
    private final int oldNumSides, newNumSides;
    private final double oldSideLenght, newSideLenght;

    public UpdatePolygonSize(Shape polygon, int oldNumSides, int newNumSides, double oldSideLenght, double newSideLenght) {
        this.shape = polygon;
        this.oldNumSides = oldNumSides;
        this.newNumSides = newNumSides;
        this.oldSideLenght = oldSideLenght;
        this.newSideLenght = newSideLenght;
    }

    @Override
    public void apply() {
        ((Polygon) shape).numSides(newNumSides);
        ((Polygon) shape).sideLength(newSideLenght);
        shape.draw();
    }

    @Override
    public void undo() {
        ((Polygon) shape).numSides(oldNumSides);
        ((Polygon) shape).sideLength(oldSideLenght);
        shape.draw();
    }
}

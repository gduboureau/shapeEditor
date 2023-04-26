package xshape.shapeFactory;

import java.util.List;

import xshape.shape.Group;
import xshape.shape.Polygon;

import xshape.shape.Rectangle;
import xshape.shape.Shape;

public interface ShapeFactory {

    Rectangle createRectangle(double posX, double posY, double height, double width);

    Polygon createPolygon(int numSides, double sideLength, double posX, double posY);

    Group createGroup();
    
}


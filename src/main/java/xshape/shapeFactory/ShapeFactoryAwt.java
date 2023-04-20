package xshape.shapeFactory;

import java.util.List;

import xshape.shape.Group;
import xshape.shape.GroupAwt;
import xshape.shape.Polygon;
import xshape.shape.PolygonAwt;
import xshape.shape.Rectangle;
import xshape.shape.RectangleAwt;
import xshape.shape.Shape;

public class ShapeFactoryAwt implements ShapeFactory {
    public ShapeFactoryAwt() {
    }
    @Override
    public Rectangle createRectangle(double posX, double posY, 
    double height, double width) {
        return new RectangleAwt(posX, posY, height, width);
    }

    @Override
    public Polygon createPolygon(int numSides, double sideLength, double posX, double posY) {
        return new PolygonAwt(numSides, sideLength, posX, posY);
    }
    @Override
    public Group createGroup(List<Shape> shapes) {
        return new GroupAwt(shapes);
    }
}

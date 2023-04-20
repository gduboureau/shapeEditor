package xshape.shapeFactory;

import xshape.shape.GroupFx;
import xshape.shape.Polygon;
import xshape.shape.PolygonFx;

import java.util.List;

import javafx.scene.Group;
import xshape.shape.Rectangle;
import xshape.shape.RectangleFx;
import xshape.shape.Shape;

public class ShapeFactoryFx implements ShapeFactory {
    Group grp;
    public ShapeFactoryFx(Group root) {
        grp = root;
    }
    @Override
    public Rectangle createRectangle(double posX, double posY, 
    double height, double width) {
        return new RectangleFx(posX, posY, height, width, grp);
    }

    @Override
    public Polygon createPolygon(int numSides, double sideLength, double posX, double posY) {
       return new PolygonFx(numSides, sideLength, posX, posY, grp);
    }

    @Override
    public xshape.shape.Group createGroup(List<Shape> shapes) {
        return new GroupFx(shapes);
    } 
}

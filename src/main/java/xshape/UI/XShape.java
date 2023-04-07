package xshape.UI;

import java.awt.geom.Point2D;

import xshape.shape.Shape;
import xshape.shapeFactory.ShapeFactory;

public abstract class XShape {
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;

    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ShapeFactory createFactory();
    //Handler to start the GUI
    public abstract void run();

    private void createScene() {
        Shape shape = _factory.createRectangle(100, 100, 50, 50);
        Shape shape2 = _factory.createRectangle(250, 250, 75, 20);
        Shape shape3 = _factory.createPolygon(6, 100, 100, 100);
        shape.translate(new Point2D.Double(100, 50));
        Shape[] tmp = { shape, shape2, shape3};
        _shapes = tmp;
    }

    public void draw() {
        if (_shapes == null) {
            _factory = createFactory();
            createScene();
        }

        for (Shape s : _shapes)
            s.draw();
    }

}

package xshape.UI;

import java.awt.geom.Point2D;

import xshape.shape.Group;
import xshape.shape.Shape;
import xshape.shapeFactory.ShapeFactory;

public abstract class XShape {
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;
    Group shapeGroup = new Group();
    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ShapeFactory createFactory();
    //Handler to start the GUI
    public abstract void run();

    private void createScene() {
        Shape shape = _factory.createRectangle(100, 100, 50, 50);
        Shape shape2 = _factory.createRectangle(250, 250, 75, 20);
        Shape shape3 = _factory.createPolygon(6, 100, 300, 300);
        shape.translate(new Point2D.Double(100, 50));

        Shape[] tmp = { shape, shape2, shape3};
        _shapes = tmp;
        Shape shape4 = _factory.createRectangle(400, 300, 200, 84);
        Shape shape5 = _factory.createRectangle(785, 320, 75, 20);
        Shape shape6 = _factory.createPolygon(6, 100, 500, 200);
        shapeGroup.add(shape4);
        shapeGroup.add(shape5);
        shapeGroup.add(shape6);
    }

    public void draw() {
        if (_shapes == null) {
            _factory = createFactory();
            createScene();
        }

        for (Shape s : _shapes){
            s.addMouseEvents();
            s.draw();
        }
        
        shapeGroup.draw();
        shapeGroup.addMouseEvents();
    }

}

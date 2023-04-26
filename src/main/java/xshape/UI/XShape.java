package xshape.UI;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import xshape.UI.tollbar.IToolbar;
import xshape.command.Invoker;
import xshape.shape.Group;
import xshape.shape.Shape;
import xshape.shapeFactory.ShapeFactory;

public abstract class XShape {
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;
    Group group = null;
    protected Invoker invoker = new Invoker();
    protected IToolbar toolbar;
    protected abstract void createToolBar(); 
    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ShapeFactory createFactory();
    //Handler to start the GUI
    public abstract void run();

    private void createScene() {

        Shape shape = _factory.createRectangle(100, 100, 50, 50);
        Shape shape2 = _factory.createRectangle(250, 250, 75, 20);
        Shape shape3 = _factory.createPolygon(6, 100, 300, 300);
        shape.translate(new Point2D.Double(100, 50));

        Shape[] tmp = {shape,shape2,shape3};
        _shapes = tmp;
        Shape shape4 = _factory.createRectangle(400, 300, 200, 84);
        Shape shape5 = _factory.createRectangle(785, 320, 75, 20);
        Shape shape6 = _factory.createPolygon(6, 100, 500, 200);
        List<Shape> shapeGroup = new ArrayList<>();
        shapeGroup.add(shape4);
        shapeGroup.add(shape5);
        shapeGroup.add(shape6);
        group = _factory.createGroup(shapeGroup);
    }

    public void draw() {
        if (_shapes == null) {
            _factory = createFactory();
            createScene();
        }

        for (Shape s : _shapes){
            s.addMouseEvents(invoker);
            s.draw();
        }
        
        group.draw();
        group.addMouseEvents(invoker);
    }

}

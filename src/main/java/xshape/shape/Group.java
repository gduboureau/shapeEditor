package xshape.shape;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


import java.util.Iterator;

public abstract class Group implements Shape{

    List<Shape> shapeChildren;


    public Group(){
        shapeChildren = new ArrayList<Shape>();
    }

    public void add(Shape shape){
        shapeChildren.add(shape);
    }

    public void remove(Shape shape){
        shapeChildren.remove(shape);
    }

    public Iterator<Shape> getChild(){
        return shapeChildren.iterator();
    }

    @Override
    public void draw() {
        Iterator<Shape> iterator = getChild();
        while (iterator.hasNext()){
            iterator.next().draw();
        }
    }


    @Override
    public Point2D size() {
        Iterator<Shape> iterator = getChild();
        double x = Integer.MIN_VALUE;
        double y = Integer.MIN_VALUE;
        while (iterator.hasNext()){
            Shape child = iterator.next();
            double xChild = child.position().getX();
            double yChild = child.position().getY();
            x = Math.max(x, xChild);
            y = Math.max(y,yChild);
        }
        return new Point2D.Double(x, y);
    }

    @Override
    public Shape size(Point2D vec) {
        Iterator<Shape> iterator = getChild();
        while (iterator.hasNext()){
            Shape child = iterator.next();
            child.size(vec);
        }
        return this;
    }

    @Override
    public Point2D position() {
        Iterator<Shape> iterator = getChild();
        double x = Integer.MAX_VALUE;
        double y = Integer.MAX_VALUE;
        while (iterator.hasNext()){
            Shape child = iterator.next();
            double xChild = child.position().getX();
            double yChild = child.position().getY();
            x = Math.min(x, xChild);
            y = Math.min(y,yChild);
        }
        return new Point2D.Double(x, y);
    }

    @Override
    public Shape position(Point2D position) {
        Iterator<Shape> iterator = getChild();
        while (iterator.hasNext()){
            Shape child = iterator.next();
            double newPosX = child.position().getX() + position.getX();
            double newPosY = child.position().getY() + position.getY();
            child.position(new Point2D.Double(newPosX, newPosY));
        }
        return this;
    }

    @Override
    public Shape translate(Point2D vec) {
        Iterator<Shape> iterator = getChild();
        while (iterator.hasNext()){
            Shape child = iterator.next();
            child.translate(vec);
        }
        return this;
    }

    
}

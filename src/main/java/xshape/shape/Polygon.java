package xshape.shape;

import java.awt.geom.Point2D;

public abstract class Polygon implements Shape {
    
    private int numSides;
    private double sideLength;
    private Point2D pos  = new Point2D.Double(0, 0);

    @Override
    public Point2D size() {
        double apothem = sideLength / (2 * Math.tan(Math.PI / numSides));
        return new Point2D.Double(apothem, sideLength);
    }

    @Override
    public Shape size(Point2D vec) {
        return this;
    }

    @Override
    public Point2D position() {
        return (Point2D) pos.clone();
    }

    @Override
    public Shape position(Point2D position) {
        pos = (Point2D) position.clone();
        return this;
    }

    @Override
    public Shape translate(Point2D vec) {
        pos.setLocation(pos.getX() + vec.getX(),
                pos.getY() + vec.getY());
        return this;
    }
    
    public int getNumSides() {
        return numSides;
    }
    
    public Polygon numSides(int numSides) {
        this.numSides = numSides;
        return this;
    }
    
    public double sideLength() {
        return sideLength;
    }
    
    public Polygon sideLength(double sideLength) {
        this.sideLength = sideLength;
        return this;
    }
}

package xshape.shape;

import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.awt.geom.Point2D;

public class PolygonFx extends Polygon{

    javafx.scene.shape.Polygon _adapted = new javafx.scene.shape.Polygon();
    Group _grp = null;

    public PolygonFx(int numSides, double sideLength, double posX, double posY, Group grp) {
        super.numSides(numSides).sideLength(sideLength).position(new Point2D.Double(posX, posY));
        _grp = grp;
		_grp.getChildren().add(_adapted);
    }
    
    @Override
    public void draw() {
        Point2D pos = position();
        Point2D size = size();
        Double[] points = new Double[getNumSides() * 2];
        double angle = Math.PI / 2;
        double angleIncrement = 2 * Math.PI / getNumSides();
        for (int i = 0; i < getNumSides(); i++) {
            double x = pos.getX() + size.getX() / 2 * Math.cos(angle);
            double y = pos.getY() - size.getY() / 2 * Math.sin(angle);
            points[i * 2] = x;
            points[i * 2 + 1] = y;
            angle += angleIncrement;
        }
        _adapted.getPoints().clear();
        _adapted.getPoints().addAll(points);
        _adapted.setFill(Color.GREEN);
    }
    
}

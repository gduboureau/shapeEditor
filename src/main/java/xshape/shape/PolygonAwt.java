package xshape.shape;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.Point2D;

import xshape.UI.AwtContext;

public class PolygonAwt extends Polygon {
    
    public PolygonAwt(int numSides, double sideLength, double posX, double posY) {
        super.numSides(numSides).sideLength(sideLength).position(new Point2D.Double(posX, posY));
    }

    @Override
    public void draw() {
        Graphics g = AwtContext.instance().graphics();
        Color c = g.getColor();
        Point2D pos = position();
        Point2D size = size();
        g.setColor(Color.GREEN);
        int[] xPoints = new int[getNumSides()];
        int[] yPoints = new int[getNumSides()];
        for (int i = 0; i < getNumSides(); i++) {
            double angle = i * 2 * Math.PI / getNumSides();
            double x = pos.getX() + size.getX() / 2 * Math.cos(angle);
            double y = pos.getY() - size.getY() / 2 * Math.sin(angle);
            xPoints[i] = (int) x;
            yPoints[i] = (int) y;
        }
        g.fillPolygon(xPoints, yPoints, getNumSides());
        g.setColor(c);
    }
}

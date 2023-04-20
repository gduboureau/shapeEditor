package xshape.shape;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import java.awt.geom.Point2D;

import xshape.UI.AwtApp;
import xshape.UI.AwtContext;

public class PolygonAwt extends Polygon {

    private double mousePosX;
    private double mousePosY;
    
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

	@Override
	public void addMouseEvents() {
		MouseAdapter mouseAdapter = new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				mousePosX = e.getX();
				mousePosY = e.getY();	
			}

		};
		AwtApp.addListener(mouseAdapter);
	
		MouseMotionAdapter motionAdapter = new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				double xmax = position().getX() + size().getX();
				double ymax = position().getY() + size().getY();
				double mousePosXcheck = mousePosX + size().getX()/2;
				double mousePosYcheck = mousePosY + size().getY()/2;
				if (mousePosXcheck >= position().getX() && mousePosXcheck <= xmax && mousePosYcheck >= position().getY() && mousePosYcheck <= ymax){
					double deltaX = e.getX() - mousePosX;
					double deltaY = e.getY() - mousePosY;
					position(new Point2D.Double(position().getX() + deltaX, position().getY() + deltaY));
					mousePosX = e.getX();
					mousePosY = e.getY();
					draw();
					AwtApp.getCanvas().repaint();
				}
			}
		};
		AwtApp.addListener(motionAdapter);
	}
}

package xshape.shape;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import java.awt.geom.Point2D;

import xshape.UI.XShape;
import xshape.UI.awt.AwtApp;
import xshape.UI.awt.AwtContext;
import xshape.command.GroupCommand;
import xshape.command.ICommand;
import xshape.command.Invoker;
import xshape.command.UpdateShapePos;

public class PolygonAwt extends Polygon {

    private double mousePosX;
    private double mousePosY;
	private Point2D oldPos = new Point2D.Double();
	private boolean mouseListenersAdded = false;
	
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
	public void addMouseEvents(Invoker invoker) {
		if (!mouseListenersAdded) {

			MouseAdapter mouseAdapter = new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					mousePosX = e.getX();
					mousePosY = e.getY();
					double xmax = position().getX() + size().getX();
					double ymax = position().getY() + size().getY();
					double mousePosXcheck = mousePosX + size().getX()/2;
					double mousePosYcheck = mousePosY + size().getY()/2;
					if (mousePosXcheck >= position().getX() && mousePosXcheck <= xmax && mousePosYcheck >= position().getY() && mousePosYcheck <= ymax){
						oldPos.setLocation(new Point2D.Double(mousePosX, mousePosY));	
					}
					if (e.isControlDown() && e.getButton() == 1){
						ICommand groupCommand = new GroupCommand(PolygonAwt.this);
						invoker.apply(groupCommand);
					}
					if (e.isControlDown() && e.getButton() == 3){
						XShape.group.remove(PolygonAwt.this);
					}
				}

				public void mouseReleased(MouseEvent e){
					double xmax = position().getX() + size().getX();
					double ymax = position().getY() + size().getY();
					double mousePosXcheck = mousePosX + size().getX()/2;
					double mousePosYcheck = mousePosY + size().getY()/2;
					if (mousePosXcheck >= position().getX() && mousePosXcheck <= xmax && mousePosYcheck >= position().getY() && mousePosYcheck <= ymax){
						Point2D newPos = new Point2D.Double(position().getX(), position().getY());
						ICommand updateShapePos = new UpdateShapePos(PolygonAwt.this, newPos, oldPos);
						invoker.apply(updateShapePos);
						oldPos = new Point2D.Double();	
					}
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
			mouseListenersAdded = true;
		}
	}
}

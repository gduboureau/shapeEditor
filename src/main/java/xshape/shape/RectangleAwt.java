package xshape.shape;

import java.awt.geom.Point2D;

import xshape.UI.AwtApp;
import xshape.UI.AwtContext;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class RectangleAwt extends Rectangle {
	
    private double mousePosX;
    private double mousePosY;
	
	public RectangleAwt(double posX, double posY, double height, double width) {
		super.position(new Point2D.Double(posX, posY));
		super.size(new Point2D.Double(width, height));
	}

	@Override
	public void draw() {
        Graphics g = AwtContext.instance().graphics();
        Color c = g.getColor();
		Point2D pos = position();
		Point2D size = size();
        g.setColor(Color.BLUE);
        g.fillRect((int)(pos.getX() - size.getX()/2),
        (int)(pos.getY() - size.getY()/2),        
        (int)(size.getX()),
        (int)(size.getY()));
        g.setColor(c);
	}


	@Override
	public void addMouseEvents() {
		MouseAdapter mouseAdapter = new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				mousePosX = e.getX() + size().getX()/2;
				mousePosY = e.getY() + size().getY()/2;	
			}

		};
		AwtApp.jc.addMouseListener(mouseAdapter);
	
		MouseMotionAdapter motionAdapter = new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				double xmax = position().getX() + size().getX();
				double ymax = position().getY() + size().getY();
				if (mousePosX >= position().getX() && mousePosX <= xmax && mousePosY >= position().getY() && mousePosY <= ymax){
					double deltaX = e.getX() - mousePosX;
					double deltaY = e.getY() - mousePosY;
					position(new Point2D.Double(position().getX() + deltaX, position().getY() + deltaY));
					mousePosX = e.getX();
					mousePosY = e.getY();
					draw();
					AwtApp.jc.repaint();
				}
			}
		};
		AwtApp.jc.addMouseMotionListener(motionAdapter);
	}
	

}

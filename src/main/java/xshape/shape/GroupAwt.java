package xshape.shape;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.util.List;

import xshape.UI.AwtApp;
import xshape.command.Invoker;

public class GroupAwt extends Group{

    private double mousePosX;
    private double mousePosY;
    private List<Shape> shapes;

    public GroupAwt(List<Shape> shapes){
        this.shapes = shapes;
        for (Shape s : shapes){
            add(s);
        }
    }

    @Override
	public void addMouseEvents(Invoker invoker) {
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
                Boolean isInGroup = false;
                for (Shape s : shapes){
                    double xMax = s.position().getX() + s.size().getX();
				    double yMax = s.position().getY() + s.size().getY();
                    double mousePosXcheckIsIn = mousePosX + s.size().getX()/2;
                    double mousePosYcheckIsIn = mousePosY + s.size().getY()/2;
                    if (mousePosXcheckIsIn >= s.position().getX() && mousePosXcheckIsIn <= xMax && mousePosYcheckIsIn >= s.position().getY() && mousePosYcheckIsIn <= yMax){
                        isInGroup = true;
                    }
                }
				if (mousePosXcheck >= position().getX() && mousePosXcheck <= xmax && mousePosYcheck >= position().getY() && mousePosYcheck <= ymax && isInGroup){
					double deltaX = e.getX() - mousePosX;
					double deltaY = e.getY() - mousePosY;
					position(new Point2D.Double(deltaX, deltaY));
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

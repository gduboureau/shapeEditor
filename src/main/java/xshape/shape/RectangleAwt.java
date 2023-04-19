package xshape.shape;

import java.awt.geom.Point2D;
import java.io.Console;

import xshape.UI.AwtApp;
import xshape.UI.AwtContext;
import xshape.shapeFactory.ShapeFactory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		System.out.println(pos);
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
	}

}

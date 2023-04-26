package xshape.shape;

import java.awt.geom.Point2D;

import xshape.command.Invoker;

public interface Shape{
	void draw();
	void addMouseEvents(Invoker invoker);
	Point2D size();
	Shape size(Point2D vec);
	Point2D position();
	Shape position(Point2D position);
	Shape translate(Point2D vec);
}

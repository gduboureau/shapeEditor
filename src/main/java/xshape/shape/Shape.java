package xshape.shape;

import java.awt.geom.Point2D;

public interface Shape{
	void draw();
	void addMouseEvents();
	Point2D size();
	Shape size(Point2D vec);
	Point2D position();
	Shape position(Point2D position);
	Shape translate(Point2D vec);
}

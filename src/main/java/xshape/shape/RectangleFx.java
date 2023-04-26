package xshape.shape;

import java.awt.geom.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import xshape.command.ICommand;
import xshape.command.Invoker;
import xshape.command.UpdateShapePos;

public class RectangleFx extends Rectangle {

	javafx.scene.shape.Rectangle _adapted = new javafx.scene.shape.Rectangle();
    Group _grp = null;
    private double mousePosX;
    private double mousePosY;

	public RectangleFx(double posX, double posY, double height, double width, Group grp) {
		position(new Point2D.Double(posX, posY));
		size(new Point2D.Double(width, height));
		_grp = grp;
		_grp.getChildren().add(_adapted);
	}

	@Override
	public void draw() {
		Point2D pos = position();
		Point2D size = size();
		_adapted.setX(pos.getX()- size.getX()/2);
		_adapted.setY(pos.getY()- size.getY()/2);
		_adapted.setWidth(size.getX());
		_adapted.setHeight(size.getY());
		_adapted.setFill(Color.BLUE);
	}

	@Override
	public void addMouseEvents(Invoker invoker) {
		_adapted.setOnMousePressed((MouseEvent event) -> {
			mousePosX = event.getSceneX();
			mousePosY = event.getSceneY();
		});
	
		_adapted.setOnMouseDragged((MouseEvent event) -> {
			double deltaX = event.getSceneX() - mousePosX;
			double deltaY = event.getSceneY() - mousePosY;
			Point2D newPos = new Point2D.Double(position().getX() + deltaX, position().getY() + deltaY);
			ICommand updateShapePos = new UpdateShapePos(this, newPos);
			invoker.apply(updateShapePos);
			// position(new Point2D.Double(position().getX() + deltaX, position().getY() + deltaY));
			mousePosX = event.getSceneX();
			mousePosY = event.getSceneY();
			draw();
		});
	}
}

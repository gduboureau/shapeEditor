package xshape.shape;

import java.awt.geom.Point2D;
import java.util.Iterator;

import javafx.scene.input.MouseEvent;
import xshape.command.ICommand;
import xshape.command.Invoker;
import xshape.command.UpdateShapePos;

public class GroupFx extends Group{

    private double mousePosX;
    private double mousePosY;
	private Point2D oldPos = new Point2D.Double(); 

    public GroupFx(){
    }

    @Override
    public void addMouseEvents(Invoker invoker, xshape.shape.Group g) {
        Iterator<Shape> iterator = getChild();
        while (iterator.hasNext()){
            Shape shape = iterator.next();
            if (shape instanceof PolygonFx){
                ((PolygonFx) shape)._adapted.setOnMousePressed((MouseEvent event) -> {
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                    oldPos.setLocation(shape.position());
                });
            
                ((PolygonFx) shape)._adapted.setOnMouseDragged((MouseEvent event) -> {
                    double deltaX = event.getSceneX() - mousePosX;
                    double deltaY = event.getSceneY() - mousePosY;
                    position(new Point2D.Double(deltaX,deltaY));
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                    draw();
                });
                
                ((PolygonFx) shape)._adapted.setOnMouseReleased((MouseEvent event) -> {
                    Point2D newPos = new Point2D.Double(shape.position().getX(), shape.position().getY());
                    ICommand updateShapePos = new UpdateShapePos(shape, newPos, oldPos);
                    invoker.apply(updateShapePos);
                    oldPos = new Point2D.Double();
                });
            }else if (shape instanceof RectangleFx){
                ((RectangleFx) shape)._adapted.setOnMousePressed((MouseEvent event) -> {
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                    oldPos.setLocation(shape.position());
                });
            
                ((RectangleFx) shape)._adapted.setOnMouseDragged((MouseEvent event) -> {
                    double deltaX = event.getSceneX() - mousePosX;
                    double deltaY = event.getSceneY() - mousePosY;
                    position(new Point2D.Double(deltaX,deltaY));
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                    draw();
                });

                ((RectangleFx) shape)._adapted.setOnMouseReleased((MouseEvent event) -> {
                    Point2D newPos = new Point2D.Double(shape.position().getX(), shape.position().getY());
                    ICommand updateShapePos = new UpdateShapePos(shape, newPos, oldPos);
                    invoker.apply(updateShapePos);
                    oldPos = new Point2D.Double();
                });
            }
        }
    }
    
}

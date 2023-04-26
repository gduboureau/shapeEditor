package xshape.shape;

import java.awt.geom.Point2D;
import java.util.Iterator;

import javafx.scene.input.MouseEvent;
import xshape.command.Invoker;

public class GroupFx extends Group{

    private double mousePosX;
    private double mousePosY;

    public GroupFx(){
    }

    @Override
    public void addMouseEvents(Invoker invoker) {
        Iterator<Shape> iterator = getChild();
        while (iterator.hasNext()){
            Shape shape = iterator.next();
            if (shape instanceof PolygonFx){
                ((PolygonFx) shape)._adapted.setOnMousePressed((MouseEvent event) -> {
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                });
            
                ((PolygonFx) shape)._adapted.setOnMouseDragged((MouseEvent event) -> {
                    double deltaX = event.getSceneX() - mousePosX;
                    double deltaY = event.getSceneY() - mousePosY;
                    position(new Point2D.Double(deltaX,deltaY));
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                    draw();
                });
            }else if (shape instanceof RectangleFx){
                ((RectangleFx) shape)._adapted.setOnMousePressed((MouseEvent event) -> {
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                });
            
                ((RectangleFx) shape)._adapted.setOnMouseDragged((MouseEvent event) -> {
                    double deltaX = event.getSceneX() - mousePosX;
                    double deltaY = event.getSceneY() - mousePosY;
                    position(new Point2D.Double(deltaX,deltaY));
                    mousePosX = event.getSceneX();
                    mousePosY = event.getSceneY();
                    draw();
                });
            }
        }
    }
    
}

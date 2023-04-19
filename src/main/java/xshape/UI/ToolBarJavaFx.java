package xshape.UI;

import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;

public class ToolBarJavaFx {

    private ToolBar verticalToolBar;
    private ToolBar horizontalToolBar;

    public ToolBarJavaFx(){
        verticalToolBar = new ToolBar();
        horizontalToolBar = new ToolBar();
    }

    public ToolBar initializeVerticalToolBar(){
        verticalToolBar.setLayoutX(80);
        verticalToolBar.setLayoutY(40);
        verticalToolBar.setPrefHeight(1500);
        verticalToolBar.setOrientation(Orientation.VERTICAL);
        verticalToolBar.setBorder(new Border(new BorderStroke(Color.BLACK, Color.TRANSPARENT, Color.TRANSPARENT, null, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, null, BorderStroke.THIN, null)));
        verticalToolBar.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        return verticalToolBar;
    }

    public ToolBar initializeHorizontalToolBar(){
        horizontalToolBar.setLayoutY(40);
        horizontalToolBar.setPrefWidth(1500 - verticalToolBar.getWidth());
        horizontalToolBar.setOrientation(Orientation.HORIZONTAL);
        horizontalToolBar.setBorder(new Border(new BorderStroke(Color.BLACK, Color.TRANSPARENT, Color.TRANSPARENT, null, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, null, BorderStroke.THIN, null)));
        horizontalToolBar.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        return horizontalToolBar;
    }
}

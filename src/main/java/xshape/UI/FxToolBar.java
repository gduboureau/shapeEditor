package xshape.UI;

import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;

public class FxToolBar {

    private ToolBar verticalToolBar;
    private ToolBar horizontalToolBar;

    public FxToolBar(){
        verticalToolBar = new ToolBar();
        horizontalToolBar = new ToolBar();
    }

    public ToolBar initializeHorizontalToolBar(){
        Button btnLoad = new Button("Load");
        Button btnSave = new Button("Save");
        Separator separator = new Separator(Orientation.VERTICAL);
        Button btnUndo= new Button("Undo");
        Button btnRedo = new Button("Redo");
        horizontalToolBar.getItems().addAll(btnLoad, btnSave, separator, btnUndo, btnRedo);

        horizontalToolBar.prefHeightProperty().bind(btnLoad.heightProperty().add(20));
        horizontalToolBar.setPrefWidth(5000);
        return horizontalToolBar;
    }

    public ToolBar initializeVerticalToolBar(){
        Button btnTrash= new Button("Trash");
        verticalToolBar.getItems().add(btnTrash);

        verticalToolBar.setLayoutY(40);
        verticalToolBar.setPrefWidth(80);
        verticalToolBar.setPrefHeight(5000);
        verticalToolBar.setStyle("-fx-background-color: #ECECEC;");
        verticalToolBar.setOrientation(Orientation.VERTICAL);
        return verticalToolBar;
    }


}

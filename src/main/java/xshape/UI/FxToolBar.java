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
        Separator separator2 = new Separator(Orientation.VERTICAL);
        Button btnTrash= new Button("Trash");
        horizontalToolBar.getItems().addAll(btnLoad, btnSave, separator, btnUndo, btnRedo, separator2, btnTrash);

        horizontalToolBar.prefHeightProperty().bind(btnLoad.heightProperty().add(20));
        horizontalToolBar.setPrefWidth(5000);
        return horizontalToolBar;
    }

    public ToolBar initializeVerticalToolBar(){
        verticalToolBar.setLayoutY(40);
        verticalToolBar.setPrefWidth(80);
        verticalToolBar.setPrefHeight(5000);
        verticalToolBar.setStyle("-fx-background-color: #ECECEC;");
        verticalToolBar.setOrientation(Orientation.VERTICAL);
        return verticalToolBar;
    }


}

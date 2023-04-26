package xshape.UI.tollbar;

import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;
import xshape.command.Invoker;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;

public class FxToolBar implements IToolbar{

    private ToolBar verticalToolBar;
    private ToolBar horizontalToolBar;
    private Invoker invoker;

    public FxToolBar(Invoker invoker){
        verticalToolBar = new ToolBar();
        horizontalToolBar = new ToolBar();
        this.invoker = invoker;
    }

    public void createToolBarH(){
        Button btnLoad = new Button("Load");
        Button btnSave = new Button("Save");
        Separator separator = new Separator(Orientation.VERTICAL);
        Button btnUndo= new Button("Undo");
        btnUndo.setOnAction(event -> {
            invoker.undo();
        });

        Button btnRedo = new Button("Redo");
        btnRedo.setOnAction(event -> {
            invoker.redo();
        });

        Separator separator2 = new Separator(Orientation.VERTICAL);
        Button btnTrash= new Button("Trash");
        horizontalToolBar.getItems().addAll(btnLoad, btnSave, separator, btnUndo, btnRedo, separator2, btnTrash);

        horizontalToolBar.prefHeightProperty().bind(btnLoad.heightProperty().add(20));
        horizontalToolBar.setPrefWidth(5000);
    }

    public void createToolBarV(){
        verticalToolBar.setLayoutY(40);
        verticalToolBar.setPrefWidth(100);
        verticalToolBar.setPrefHeight(5000);
        verticalToolBar.setStyle("-fx-background-color: #ECECEC;");
        verticalToolBar.setOrientation(Orientation.VERTICAL);
    }


    public ToolBar getHorizontalToolBar() {
        return horizontalToolBar;
    }

    public ToolBar getVerticalToolBar() {
        return verticalToolBar;
    }


}

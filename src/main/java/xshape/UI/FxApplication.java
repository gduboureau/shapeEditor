package xshape.UI;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxApplication extends Application {
    public static Group _root = new Group ();

    @Override
    public void start(Stage primaryStage) throws Exception {
        FxToolBar toolBar = new FxToolBar();
        _root.getChildren().add(toolBar.initializeVerticalToolBar());
        _root.getChildren().add(toolBar.initializeHorizontalToolBar());
        
        primaryStage.setTitle("XShape JavaFx Rendering");
        Scene _scene = new Scene(_root, 500, 500);
        primaryStage.setScene(_scene);
        primaryStage.show();
    }
}
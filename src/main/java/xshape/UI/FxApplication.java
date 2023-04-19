package xshape.UI;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FxApplication extends Application {
    public static Group _root = new Group ();

    @Override
    public void start(Stage primaryStage) throws Exception {
        ToolBarJavaFx toolBar = new ToolBarJavaFx();
        _root.getChildren().add(toolBar.initializeVerticalToolBar());
        _root.getChildren().add(toolBar.initializeHorizontalToolBar());
        Rectangle backgroundVertical = new Rectangle(0, 40, 80, 1500);
        backgroundVertical.setFill(Color.rgb(192, 192, 192));
        _root.getChildren().add(backgroundVertical);
        Rectangle backgroundHorizontal = new Rectangle(0, 0, 1500, 40);
        backgroundHorizontal.setFill(Color.BLACK);
        _root.getChildren().add(backgroundHorizontal);
        primaryStage.setTitle("XShape JavaFx Rendering");
        Scene _scene = new Scene(_root, 500, 500);
        primaryStage.setScene(_scene);
        primaryStage.show();
    }
}
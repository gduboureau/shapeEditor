package xshape.shape;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import xshape.command.GroupCommand;
import xshape.command.ICommand;
import xshape.command.Invoker;
import xshape.command.UpdateShapePos;

import java.awt.geom.Point2D;
import java.util.Optional;

public class PolygonFx extends Polygon{

    javafx.scene.shape.Polygon _adapted = new javafx.scene.shape.Polygon();
    Group _grp = null;
    private double mousePosX;
    private double mousePosY;
	private Point2D oldPos = new Point2D.Double(); 

    public PolygonFx(int numSides, double sideLength, double posX, double posY, Group grp) {
        super.numSides(numSides).sideLength(sideLength).position(new Point2D.Double(posX, posY));
        _grp = grp;
		_grp.getChildren().add(_adapted);
    }
    
    @Override
    public void draw() {
        Point2D pos = position();
        Point2D size = size();
        Double[] points = new Double[getNumSides() * 2];
        double angle = Math.PI / 2;
        double angleIncrement = 2 * Math.PI / getNumSides();
        for (int i = 0; i < getNumSides(); i++) {
            double x = pos.getX() + size.getX() / 2 * Math.cos(angle);
            double y = pos.getY() - size.getY() / 2 * Math.sin(angle);
            points[i * 2] = x;
            points[i * 2 + 1] = y;
            angle += angleIncrement;
        }
        _adapted.getPoints().clear();
        _adapted.getPoints().addAll(points);
        _adapted.setFill(Color.GREEN);
    }

    @Override
	public void addMouseEvents(Invoker invoker, xshape.shape.Group g) {
		_adapted.setOnMousePressed((MouseEvent event) -> {
			mousePosX = event.getSceneX();
			mousePosY = event.getSceneY();
			oldPos.setLocation(position());
			if (event.isControlDown() && event.isPrimaryButtonDown()){
				ICommand groupCommand = new GroupCommand(PolygonFx.this,g);
				invoker.apply(groupCommand);
			}
			if (event.isControlDown() && event.isSecondaryButtonDown()){
				g.remove(this);
			}
			if (event.isAltDown() && event.isPrimaryButtonDown()){
				Dialog<Pair<Double, Double>> dialog = new Dialog<>();
				dialog.setTitle("Edit Shape Position");
				dialog.setHeaderText("Enter new position for the shape:");
				ButtonType applyButtonType = new ButtonType("Apply", ButtonData.OK_DONE);
				dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, ButtonType.CANCEL);
				GridPane gridPane = new GridPane();
				gridPane.setHgap(10);
				gridPane.setVgap(10);
				gridPane.setPadding(new Insets(20, 150, 10, 10));
				TextField xField = new TextField(String.valueOf(position().getX()));
				TextField yField = new TextField(String.valueOf(position().getY()));
				gridPane.add(new Label("X:"), 0, 0);
				gridPane.add(xField, 1, 0);
				gridPane.add(new Label("Y:"), 0, 1);
				gridPane.add(yField, 1, 1);
				Node applyButton = dialog.getDialogPane().lookupButton(applyButtonType);
				applyButton.setDisable(true);
				xField.textProperty().addListener((observable, oldValue, newValue) -> {
					applyButton.setDisable(newValue.trim().isEmpty() || yField.getText().trim().isEmpty());
				});
				yField.textProperty().addListener((observable, oldValue, newValue) -> {
					applyButton.setDisable(newValue.trim().isEmpty() || xField.getText().trim().isEmpty());
				});
				dialog.getDialogPane().setContent(gridPane);
				Platform.runLater(() -> xField.requestFocus());
				dialog.setResultConverter(dialogButton -> {
					if (dialogButton == applyButtonType) {
						double x = Double.parseDouble(xField.getText());
						double y = Double.parseDouble(yField.getText());
						return new Pair<>(x, y);
					}
					return null;
				});
				Optional<Pair<Double, Double>> result = dialog.showAndWait();
				if (result.isPresent()) {
					Pair<Double, Double> pair = result.get();
					position(new Point2D.Double(pair.getKey(), pair.getValue()));
					draw();
				}
			}
		});
	
		_adapted.setOnMouseDragged((MouseEvent event) -> {
			double deltaX = event.getSceneX() - mousePosX;
			double deltaY = event.getSceneY() - mousePosY;
			position(new Point2D.Double(position().getX() + deltaX, position().getY() + deltaY));
			mousePosX = event.getSceneX();
			mousePosY = event.getSceneY();
			draw();
		});

		_adapted.setOnMouseReleased((MouseEvent event) -> {
			Point2D newPos = new Point2D.Double(position().getX(), position().getY());
			ICommand updateShapePos = new UpdateShapePos(PolygonFx.this, newPos, oldPos);
			invoker.apply(updateShapePos);
			oldPos = new Point2D.Double();
		});
	}
    
}

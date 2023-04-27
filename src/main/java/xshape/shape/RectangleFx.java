package xshape.shape;

import java.awt.geom.Point2D;
import java.util.Optional;

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
import xshape.command.ICommand;
import javafx.util.Pair;
import xshape.command.GroupCommand;
import xshape.command.Invoker;
import xshape.command.UpdateRectangleSize;
import xshape.command.UpdateShapePos;

public class RectangleFx extends Rectangle{

	javafx.scene.shape.Rectangle _adapted = new javafx.scene.shape.Rectangle();
    Group _grp = null;
    private double mousePosX;
    private double mousePosY;
	private Point2D oldPos = new Point2D.Double(); 

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
	public void addMouseEvents(Invoker invoker, xshape.shape.Group g) {
		_adapted.setOnMousePressed((MouseEvent event) -> {
			mousePosX = event.getSceneX();
			mousePosY = event.getSceneY();
			oldPos.setLocation(position());
			if (event.isControlDown() && event.isPrimaryButtonDown()){
				ICommand groupCommand = new GroupCommand(RectangleFx.this,g);
				invoker.apply(groupCommand);
			}
			if (event.isControlDown() && event.isSecondaryButtonDown()){
				g.remove(this);
			}
			if (event.isAltDown() && event.isPrimaryButtonDown()){
				Dialog<Pair<Double, Double>> dialog = new Dialog<>();
				dialog.setTitle("Edit Rectangle size");
				dialog.setHeaderText("Enter new size for the rectangle:");
				ButtonType applyButtonType = new ButtonType("Apply", ButtonData.OK_DONE);
				dialog.getDialogPane().getButtonTypes().addAll(applyButtonType, ButtonType.CANCEL);
				GridPane gridPane = new GridPane();
				gridPane.setHgap(10);
				gridPane.setVgap(10);
				gridPane.setPadding(new Insets(20, 150, 10, 10));
				TextField widthField = new TextField(String.valueOf(size().getX()));
				TextField heightField = new TextField(String.valueOf(size().getY()));
				gridPane.add(new Label("Width:"), 0, 0);
				gridPane.add(widthField, 1, 0);
				gridPane.add(new Label("Height:"), 0, 1);
				gridPane.add(heightField, 1, 1);
				Node applyButton = dialog.getDialogPane().lookupButton(applyButtonType);
				applyButton.setDisable(true);
				widthField.textProperty().addListener((observable, oldValue, newValue) -> {
					applyButton.setDisable(newValue.trim().isEmpty() || heightField.getText().trim().isEmpty());
				});
				heightField.textProperty().addListener((observable, oldValue, newValue) -> {
					applyButton.setDisable(newValue.trim().isEmpty() || widthField.getText().trim().isEmpty());
				});
				dialog.getDialogPane().setContent(gridPane);
				Platform.runLater(() -> widthField.requestFocus());
				dialog.setResultConverter(dialogButton -> {
					if (dialogButton == applyButtonType) {
						double width = Double.parseDouble(widthField.getText());
						double height = Double.parseDouble(heightField.getText());
						return new Pair<>(width, height);
					}
					return null;
				});
				Optional<Pair<Double, Double>> result = dialog.showAndWait();
				if (result.isPresent()) {
					Pair<Double, Double> pair = result.get();
					ICommand command = new UpdateRectangleSize(RectangleFx.this, size(), new Point2D.Double(pair.getKey(), pair.getValue()));
					invoker.apply(command);
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
			ICommand updateShapePos = new UpdateShapePos(RectangleFx.this, newPos, oldPos);
			invoker.apply(updateShapePos);
			oldPos = new Point2D.Double();
		});
	}
}

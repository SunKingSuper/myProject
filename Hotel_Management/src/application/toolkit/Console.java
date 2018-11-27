package application.toolkit;

import application.Constant;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class Console extends VBox {
	Label status = new Label();
	TextArea infomation = new TextArea();

	public Console() {
		super();
		init();
	}

	private void init() {
		infomation.setEditable(false);
		getChildren().addAll(status, infomation);
		
		Rectangle2D primaryScreenBound = Screen.getPrimary().getVisualBounds();
		setPrefHeight(primaryScreenBound.getHeight() / 4);
		setPrefWidth(primaryScreenBound.getWidth() * 4/5);
	}

	public void refresh(int free, int booked, int registered, int cleanning) {
		status.setText(String.format(Constant.StatusText, free, booked, registered, cleanning));
	}
	public void info(String msg) {
		infomation.setText(infomation.getText() + '\n' + msg);
	}
}

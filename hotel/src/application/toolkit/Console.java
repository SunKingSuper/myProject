package application.toolkit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import application.Constant;
import control.Core;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import model.domain.Room;

public class Console extends VBox {
	Label status = new Label();
	static Label time = new Label();
	TextArea infomation = new TextArea();
	SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public Console(List<Room> list) {
		super();
		init();
		refresh(list);
	}

	private void init() {
		infomation.setEditable(false);
		HBox hBox = new HBox();
		hBox.getChildren().addAll(time, status);
		hBox.setAlignment(Pos.CENTER_LEFT);
		hBox.setPadding(new Insets(5));
		hBox.setMargin(time, new Insets(0, 100, 0, 50));
		getChildren().addAll(hBox, infomation);

		Rectangle2D primaryScreenBound = Screen.getPrimary().getVisualBounds();
		setPrefHeight(primaryScreenBound.getHeight() / 4);
		setPrefWidth(primaryScreenBound.getWidth() * 4 / 5);
		setAlignment(Pos.BOTTOM_CENTER);
	}

	public void refresh(List<Room> list) {
		HashMap<String, Integer> statics = Core.getRoomStatic();
		String nowtime = dFormat.format(new Date()).toString();
		String statusinfo = String.format(Constant.StatusText, statics.get("free"), statics.get("booked"),
				statics.get("registered"), statics.get("cleanning"));
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				time.setText(nowtime);  										
				status.setText(statusinfo);
			}
		});
	}

	public void info(String msg) {
		infomation.setText(msg + '\n' + infomation.getText());
	}
}

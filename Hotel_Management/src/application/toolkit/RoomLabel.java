package application.toolkit;

import application.Constant;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.domain.Room;
import application.toolkit.TitleInputField;

public class RoomLabel extends Label {
	public RoomLabel(Room room, TitleInputField field) {
		super();
		setTextAlignment(TextAlignment.CENTER);
		setText(String.valueOf(room.getidRoom()));
		setFont(new Font(16));
		RoomLabel that = this;
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				field.inputfield.setText(that.getText());
			}
		});
		setPrefWidth(100);
		setStyle("-fx-background-color:" + Constant.BackgroundBlue);
		
		textFillProperty().set(Paint.valueOf("white"));
	}
}


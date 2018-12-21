package application.toolkit;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TitleInputField extends HBox {
	public Label title;
	public TextField inputfield;

	public TitleInputField(String title) {
		getChildren().addAll(new Label(title), new TextField());
		ui();
	}

	public TitleInputField(Label title, TextField inputField) {
		getChildren().addAll(title, inputField);
		ui();
	}

	public TitleInputField(String title, TextField inputField) {
		getChildren().addAll(new Label(title), inputField);
		ui();
	}

	private void ui() {
		title = (Label) getChildren().get(0);
		inputfield = (TextField) getChildren().get(1);
		setAlignment(Pos.CENTER);
		setSpacing(10);
	}

	public String getText() {
		return inputfield.getText();
	}

}

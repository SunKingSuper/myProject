package application.toolkit;

import application.Constant;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RegisterItem extends VBox{
	Label name = new Label("入住人");
	TitleInputField registerName = new TitleInputField("姓名");
	TitleInputField registerIdCard = new TitleInputField("身份证号");
	public RegisterItem() {
		init();
	}
	public RegisterItem(String bookerName) {
		registerName.inputfield.setText(bookerName);
	}
	private void init() {
		name.setFont(new Font(8));
		registerName.setBackground(Background.EMPTY);
		registerIdCard.setBackground(Background.EMPTY);
		getChildren().addAll(name, registerName, registerIdCard);
		setStyle(Constant.Background + Constant.Gray);
		setAlignment(Pos.CENTER_LEFT);
	}
}

package application.toolkit;

import application.App;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ExitButton extends Button {
	public ExitButton(App platform) {
		super();
		setText("退出系统");
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				platform.exit();
			}
		});
	}
}

package application.toolkit;

import application.MyStage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CancelButton extends Button {
	private MyStage parent;
	public CancelButton(MyStage myStage) {
		super();
		parent = myStage;
		setText("取消");
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				parent.close();
			}
		});
	}
}

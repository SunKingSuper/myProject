package application.toolkit;

import application.App;
import application.MyStage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class CancelOkGroup extends HBox {
	public Button cancel = new Button("取消");
	public Button ok = new Button("确定");
	private MyStage parent;
	public CancelOkGroup() {
		init(100, true);
	}
	public CancelOkGroup(double space, boolean isDefault) {
		init(space, isDefault);
	}
	public CancelOkGroup(String cancelTitle, String okTitle, double space, Boolean isDefault) {
		cancel.setText(cancelTitle);
		ok.setText(okTitle);
		init(space, isDefault);
	}
	private void init(double space, Boolean isDefault) {
		getChildren().addAll(cancel, ok);
		setSpacing(space);
		if (isDefault) {
			cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					parent.close();
				}
			});
		}
	}
}

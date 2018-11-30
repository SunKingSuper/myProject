package application;

import javafx.event.EventHandler;
import Log.TheLog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class WarnDialog extends MyStage {
	Label warnMessage = new Label();
	public WarnDialog(App platform, String msg) {
		warnMessage.setText(msg);
		TheLog.info(msg);
		ui(platform);
	}
	@Override
	protected void init() {
		Button okButton = new Button("确定");
		okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				close();
			}
		});
		VBox mainroot = new VBox();
		mainroot.getChildren().addAll(warnMessage, okButton);
		mainroot.setSpacing(10);
		mainroot.setPadding(new Insets(10));
		mainroot.setAlignment(Pos.CENTER);
		root = mainroot;
	}
}

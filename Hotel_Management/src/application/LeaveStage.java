package application;

import application.toolkit.NumberField;
import application.toolkit.TitleInputField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class LeaveStage extends MyStage {
	private TitleInputField idRoom  = new TitleInputField("房号", new NumberField());;
	private TextArea showArea = new TextArea();
	public LeaveStage(App platform) {
		ui(platform);
		setTitle(Constant.MenuLeave);
	}

	@Override
	protected void init() {
		
		VBox mainroot = new VBox();
		mainroot.getChildren().addAll(idRoom, showArea);
	}

}

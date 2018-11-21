package application;

import javafx.scene.control.Label;

public class MainStage extends myStage {
	public MainStage(App platform) {
		ui(platform);
	}

	@Override
	protected void init() {
		root = new Label("登陆成功");
	}
}

package application;

import java.util.Date;
import java.util.TimerTask;

import application.toolkit.Console;
import application.toolkit.FuncMenu;
import application.toolkit.Monitor;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class MainStage extends MyStage {
	private BorderPane mainroot = new BorderPane();
	Monitor monitor;
	VBox funcmenu;
	Console console = new Console();

	public MainStage(App platform) {
		ui(platform);
	}

	@Override
	protected void init() {
		VBox centerLayout = new VBox();
		
		monitor = new Monitor(platform);
		funcmenu = new FuncMenu(platform);
		console = new Console();

		centerLayout.getChildren().addAll(monitor, console);
		mainroot.setCenter(centerLayout);
		mainroot.setRight(funcmenu);

		stage.initStyle(StageStyle.UNDECORATED);
		stage.setFullScreen(true);
		root = mainroot;

		platform.timer.schedule(new TimerTask() {

			@Override
			public void run() {
				refresh();
			}
		}, 0L, Constant.FreshPeriod);

	}

	public void refresh() {
		infoMessage(new Date().toString());
	}

	public void infoMessage(String msg) {
		console.info(msg);
	}
}

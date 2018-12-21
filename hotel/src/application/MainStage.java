package application;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import Log.TheLog;
import application.toolkit.Console;
import application.toolkit.FuncMenu;
import application.toolkit.Monitor;
import control.Core;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import model.domain.Room;

public class MainStage extends MyStage {
	private BorderPane mainroot = new BorderPane();
	Monitor monitor;
	VBox funcmenu;
	Console console;

	public MainStage(App platform) {
		ui(platform);
	}

	@Override
	protected void init() {
		VBox centerLayout = new VBox();
		
		List<Room> list = Core.checkRoom();
		monitor = new Monitor(platform, list);
		funcmenu = new FuncMenu(platform);
		console = new Console(list);

		centerLayout.getChildren().addAll(monitor, console);
		mainroot.setCenter(centerLayout);
		mainroot.setRight(funcmenu);

		initStyle(StageStyle.UNDECORATED);
		setFullScreen(true);
		root = mainroot;

		platform.timer.schedule(new TimerTask() {

			@Override
			public void run() {
				refresh();
			}
		}, 1000L, Constant.FreshPeriod);
		
		TheLog.setMainStage(this);
		TheLog.info("进入系统成功");
	}

	public void refresh() {
		List<Room> list = Core.checkRoom();
		monitor.refresh(list);
		console.refresh(list);
	}

	public void infoMessage(String msg) {
		console.info(msg);
	}
}

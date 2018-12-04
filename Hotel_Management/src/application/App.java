package application;

import java.util.Timer;

import Log.TheLog;
import control.Core;
import application.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application {
	public Timer timer = new Timer();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		super.init();
		setUserAgentStylesheet(STYLESHEET_MODENA);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TheLog.info("App启动");
//		new LoginStage(this);
//		new MainStage(this);
//		new BookStage(this);
//		new ShowStage(this);
//		new LeaveStage(this);
		new RegisterStage(this);
	}

	public void exit() {
		timer.cancel();
		Core.logout();
		TheLog.info("程序退出成功");
		Platform.exit();
	}
}

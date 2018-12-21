package application;
/**	酒店住房管理系统
 * @author SKS
 * 已经做了快一个月了，真的很难过，自己依然做得乱七八糟的
 */
import java.util.Timer;

import Log.TheLog;
import control.Core;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.domain.User;

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
		
		// debug
		TheLog.info("App启动");
		new LoginStage(this);		//记得把调bug用的自动登陆，以及页面改回来

	}

	public void exit() {
		timer.cancel();
		Core.logout();
		TheLog.info("程序退出成功");
		Platform.exit();
	}
}

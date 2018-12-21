package app;

import app.ui.LoginPage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author 王明 2016015338
 *	主程序启动
 */
public class MaterialManagement extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		new LoginPage();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

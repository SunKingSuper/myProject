package application;

import control.Core;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application{
	public Core core;
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void init() throws Exception {
		super.init();
		core = new Core();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		new LoginStage(this);
	}
	
	public void exit() {
		core.close();
		Platform.exit();
	}
}

package model.test;

import java.util.Date;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TestApp extends Application {

	public Timer timer = new Timer();
	private Stage mainStage;
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = new MainStage(this);
	}
	public void exit() {
		Platform.exit();
	}
	
}

class MainStage extends Stage {
	private Button exit = new Button("exit");
	private TestApp platform;
	public MainStage(TestApp platform) {
		super();
		this.platform = platform;
		platform.timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println(new Date());
			}
		}, 0L, 1000L);
		setScene(new Scene(new Menu(platform)));
		show();
	}
}

class Menu extends VBox {
	TestApp app;
	public Menu(TestApp app) {
		this.app = app;
		init();
	}
	private void init() {
		Button iButton = new Button("exit");
		iButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				app.timer.cancel();
				app.exit();
			}});
		getChildren().add(iButton);
	}
}
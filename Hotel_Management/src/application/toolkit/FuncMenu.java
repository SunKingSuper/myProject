package application.toolkit;

import Log.TheLog;
import application.App;
import application.Constant;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class FuncMenu extends VBox{
	App platform;
	public FuncMenu(App platform) {
		super();
		this.platform = platform;
		init();
	}
	private void init() {
		Button book = new Button(Constant.MenuBook);
		Button register = new Button(Constant.MenuRegister);
		Button leave = new Button(Constant.MenuLeave);
		Button show = new Button(Constant.MenuShow);
		Button exit = new Button(Constant.MenuExit);
		
		exit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				TheLog.info(platform.toString());
				platform.exit();
			}
		});
		
		getChildren ().addAll(book, register, leave, show, exit);
		setAlignment(Pos.CENTER);
		setSpacing(20);
		
		Rectangle2D primaryScreenBound = Screen.getPrimary().getVisualBounds();
		setPrefHeight(primaryScreenBound.getHeight());
		setPrefWidth(primaryScreenBound.getWidth()/5);
	}
	
}

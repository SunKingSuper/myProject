package application.toolkit;

import Log.TheLog;
import application.App;
import application.Authentication;
import application.BookStage;
import application.Constant;
import application.LeaveStage;
import application.RegisterStage;
import application.ShowStage;
import control.Core;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class FuncMenu extends VBox {
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
		Button exit = new ExitButton();

		book.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				new BookStage(platform);
			}
		});

		register.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				new RegisterStage(platform);
			}
		});

		leave.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				new LeaveStage(platform);
			}
		});

		show.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				new Authentication(platform);
				if (Core.getPermission()) {
					new ShowStage(platform);
				}
			}
		});

		getChildren().addAll(book, register, leave, show, exit);
		setAlignment(Pos.CENTER);
		setSpacing(20);

		Rectangle2D primaryScreenBound = Screen.getPrimary().getVisualBounds();
		setPrefHeight(primaryScreenBound.getHeight());
		setPrefWidth(primaryScreenBound.getWidth() / 5);
	}

}

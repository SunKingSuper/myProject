package application.toolkit;

import application.App;
import application.Authentication;
import application.BookStage;
import application.Constant;
import application.LeaveStage;
import application.RegisterStage;
import application.ShowStage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
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
		Button exit = new ExitButton(platform);

		book.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				new Authentication(platform, new PermissBook(platform));
			}
		});

		register.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				new Authentication(platform, new PermissRegister(platform));
				}
		});

		leave.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				new Authentication(platform, new PermissLeave(platform));
			}
		});

		show.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				new Authentication(platform, new PermissShow(platform));
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

class PermissLeave extends Permiss {
	public PermissLeave(App platform) {
		this.platform = platform;
	}
	@Override
	public void open() {
		new LeaveStage(platform);
	}
	
}

class PermissBook extends Permiss {
	public PermissBook(App platform) {
		this.platform = platform;
	}
	@Override
	public void open() {
		new BookStage(platform);
	}
	
}

class PermissRegister extends Permiss {
	public PermissRegister(App platform) {
		this.platform = platform;
	}
	@Override
	public void open() {
		new RegisterStage(platform);
	}
	
}

class PermissShow extends Permiss {
	public PermissShow(App platform) {
		this.platform = platform;
	}
	@Override
	public void open() {
		new ShowStage(platform);
	}
	
}

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class FuncMenu extends VBox {
	App platform;

	public FuncMenu(App platform) {
		super();
		this.platform = platform;
		init();
	}

	private void init() {
		ImageView logo = new ImageView(new Image(Constant.LOGO));
		Text name = new Text(Constant.NAME);
		name.setFont(new Font(20));
		Button book = new Button(Constant.MenuBook);
		Button register = new Button(Constant.MenuRegister);
		Button leave = new Button(Constant.MenuLeave);
		Button show = new Button(Constant.MenuShow);
		Button exit = new ExitButton(platform);
		
		book.setPrefWidth(100);
		register.setPrefWidth(100);
		leave.setPrefWidth(100);
		show.setPrefWidth(100);
		exit.setPrefWidth(100);

		book.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
//				new Authentication(platform, new PermissBook(platform));
				new BookStage(platform, Constant.BOOKED);
			}
		});

		register.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
//				new Authentication(platform, new PermissRegister(platform));
				new RegisterStage(platform);
				}
		});

		leave.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
//				new Authentication(platform, new PermissLeave(platform));
				new LeaveStage(platform);
			}
		});

		show.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
//				new Authentication(platform, new PermissShow(platform));
				new ShowStage(platform);
			}
		});

		getChildren().addAll(logo, name, book, register, leave, show, exit);
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
		new BookStage(platform, Constant.BOOKED);
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

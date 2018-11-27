package application;

import java.util.function.UnaryOperator;

import application.toolkit.NumberFiled;
import control.Core;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.domain.User;

public class LoginStage extends MyStage {
	NumberFiled userID = new NumberFiled();
	PasswordField password = new PasswordField();
	Label loginError = new Label(Constant.LoginError);
	VBox mainroot;

	public LoginStage(App platform) {
		ui(platform);
	}

	@Override
	protected void init() {
		userID.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (loginError.isVisible()) {
					loginError.setVisible(false);
				}
			}
		});

		Button loginbtn = new Button("登陆");
		Button cancelbtn = new Button("取消");
		loginbtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				login();
			}
		});
		cancelbtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				platform.exit();
			}
		});

		ImageView logo = new ImageView(new Image(Constant.LOGO));
		Text name = new Text(Constant.NAME);
		name.setFont(new Font(20));
		// 表单布局
		Label id = new Label("员工号");
		Label psd = new Label("   密码");
		GridPane form = new GridPane();
		form.add(id, 0, 0);
		form.add(psd, 0, 1);
		form.add(userID, 1, 0);
		form.add(password, 1, 1);
		form.add(cancelbtn, 0, 2);
		form.add(loginbtn, 1, 2);
		form.setVgap(10);
		form.setHgap(10);
		form.setMargin(loginbtn, new Insets(5, 0, 0, 130));
		form.setMargin(cancelbtn, new Insets(5, 0, 0, 0));
		form.setAlignment(Pos.CENTER_LEFT);
		form.setPadding(new Insets(30, 30, 0, 30));
		loginError.setStyle("-fx-background-color:#F5B7B1");
		loginError.setTextFill(Paint.valueOf("red"));
		loginError.setPadding(new Insets(10));
		loginError.setVisible(false);

		mainroot = new VBox();
		mainroot.getChildren().addAll(logo, name, form, loginError);
		mainroot.alignmentProperty().set(Pos.CENTER);
		VBox.setMargin(logo, new Insets(0, 0, 5, 0));
		stage.setWidth(300);
		stage.setHeight(400);
		stage.initStyle(StageStyle.UNDECORATED);
		root = mainroot;
	}

	private void login() {
		User user = new User();
		user.setidUser(Integer.parseInt(userID.getText()));
		user.setpassword(password.getText());
		if (Core.login(user)) {
			new MainStage(platform);
			stage.close();
		} else {
			loginError.setVisible(true);
			FadeTransition fadeTransition = new FadeTransition();
			fadeTransition.setDuration(Duration.seconds(0.3));
			fadeTransition.setNode(loginError);
			fadeTransition.setFromValue(0);
			fadeTransition.setToValue(1);
			fadeTransition.play();
		}
	}
}

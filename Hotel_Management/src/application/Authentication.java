package application;

import Log.TheLog;
import application.toolkit.Permiss;
import control.Core;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class Authentication extends MyStage {
	public static boolean kind = true; // 在Core里面用到
	private Label loginError = new Label("密码错误");
	private VBox mainroot = new VBox();
	private Authentication self;
	private Permiss permissStage;

	public Authentication(App platform, Permiss permissStage) {
		this.permissStage = permissStage;
		self = this;
		ui(platform);
		setTitle("身份确认");
	}

	@Override
	protected void init() {
		Label password = new Label("密码");
		PasswordField psd = new PasswordField();
		Button ok = new Button("确认");
		Button cancel = new Button("取消");
		ok.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (Core.authentication(psd.getText())) {
					Core.givePermission(self);
					TheLog.info("授权成功");
					close();
					permissStage.getPermiss(); // 这里曲折实现了权限管理
				} else {
					loginError.setVisible(true);
					mainroot.getChildren().add(loginError);
					TheLog.info("密码错误，授权失败");
				}
			}
		});
		cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				close();
			}
		});
		psd.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (loginError.isVisible()) {
					loginError.setVisible(false);
					mainroot.getChildren().remove(loginError);
				}
			}
		});

		loginError.setVisible(false);
		loginError.setStyle("-fx-background-color:" + Constant.BackgroundRed);
		loginError.textFillProperty().set(Paint.valueOf("red"));
		loginError.setAlignment(Pos.CENTER);
		loginError.setPadding(new Insets(2, 15, 2, 15));
		HBox box1 = new HBox(password, psd);
		HBox box2 = new HBox(cancel, ok);
		box1.setSpacing(10);
		box1.setAlignment(Pos.CENTER);
		box2.setSpacing(100);
		box2.setAlignment(Pos.CENTER);
		mainroot.getChildren().addAll(box1, box2);
		mainroot.setMargin(ok, new Insets(0, 0, 0, 130));
		mainroot.setSpacing(10);
		mainroot.setPadding(new Insets(15));
		mainroot.setPrefSize(300, 200);
		mainroot.setAlignment(Pos.CENTER);
		root = mainroot;
	}
}

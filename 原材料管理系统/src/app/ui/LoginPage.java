package app.ui;


import app.control.UserControl;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage extends Stage{
	public LoginPage() {
		super();
		GridPane root = new GridPane();
		
		Label idL = new Label("帐号");
		TextField id = new TextField();
		Label passwordL = new Label("密码");
		PasswordField password = new PasswordField();
		Button exit = new Button("退出系统");
		Button ok = new Button("登陆");
		
		ok.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(UserControl.userValidation(id.getText(), password.getText())) {
					new MenuPage();
					close();
				};
			}
		});
		
		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Platform.exit();
			}
		});
		
		root.add(idL, 0, 0);
		root.add(id, 1, 0);
		root.add(passwordL, 0, 1);
		root.add(password, 1, 1);
		root.add(exit, 0, 2);
		root.add(ok, 1, 2);
		root.setPadding(new Insets(10));
		root.setHgap(5);
		root.setMargin(ok, new Insets(0, 0, 0, 120));
		Scene scene = new Scene(root);
		setScene(scene);
		setTitle("原材料管理系统");
		show();
	}
}

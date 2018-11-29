package application;


import application.toolkit.NumberFiled;
import control.Core;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Authentication extends MyStage {
	public static boolean kind = true;
	private Authentication self;
	public Authentication(App platform) {
		self = this;
		ui(platform);
		stage.setTitle("身份确认");
	}
	@Override
	protected void init() {
		Label password = new Label("密码");
		TextField psd = new NumberFiled();
		Button ok = new Button("确认");
		Button cancel = new Button("取消");
		ok.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(Core.authentication(psd.getText())) {
					Core.givePermission(self);
					stage.close();
				}
			}
		});
		cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				stage.close();
			}
		});
		
		GridPane mainroot = new GridPane();
		mainroot.add(password, 0, 0);
		mainroot.add(psd, 1, 0);
		mainroot.add(cancel, 0, 1);
		mainroot.add(ok, 1, 1);
		mainroot.setHgap(10);
		mainroot.setVgap(10);
		mainroot.setMargin(ok, new Insets(0, 0, 0, 130));
		mainroot.setPrefSize(300, 150);
		mainroot.setAlignment(Pos.CENTER);
		root = mainroot;
	}

}

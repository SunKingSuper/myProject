package model.test;

import java.util.HashMap;

import application.Constant;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Test extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new A();
	}
}

class A {
	public static HashMap<Long, Image> statusImage = new HashMap<>();
	Label idRoom = new Label();
	{
		statusImage.put(Constant.FREE, new Image(Constant.RoomFreeImgUrl));
		statusImage.put(Constant.BOOKED, new Image(Constant.RoomBookedImgUrl));
		statusImage.put(Constant.REGISTED, new Image(Constant.RoomRegistedImgUrl));
		statusImage.put(Constant.CLEANNING, new Image(Constant.RoomCleanningImgUrl));
	}
	public A() {
		HBox root = new HBox();
		ImageView a = new ImageView();
		ImageView b = new ImageView();
		ImageView c = new ImageView();
		ImageView d = new ImageView();
		a.setImage(statusImage.get(Constant.FREE));
		b.setImage(statusImage.get(Constant.BOOKED));
		c.setImage(statusImage.get(Constant.REGISTED));
		d.setImage(statusImage.get(Constant.CLEANNING));
		root.getChildren().addAll(a,b,c,d);
		Scene scene = new Scene(root); 
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
}


package application.toolkit;

import application.Constant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RoomView extends VBox{
	ImageView imageView;
	Label idRoom = new Label("101");
	public RoomView() {
		super();
		init();
	}
	private void init() {
		imageView = new ImageView(new Image(Constant.RoomInitImgUrl));
		imageView.setFitHeight(64);
		imageView.setFitWidth(64);
		getChildren().addAll(imageView, idRoom);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(15));
	}
	public void refresh() {
		
	}
}

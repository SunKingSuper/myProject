package application.toolkit;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RoomView extends VBox{
	ImageView room;
	Label idRoom = new Label("101");
	public RoomView() {
		super();
		init();
	}
	private void init() {
		room = new ImageView(new Image("Resource/home.png"));
		
		getChildren().addAll(room, idRoom);
		setAlignment(Pos.CENTER);
	}
	public void refresh() {
		
	}
}

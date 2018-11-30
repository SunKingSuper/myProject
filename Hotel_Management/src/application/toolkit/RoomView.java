package application.toolkit;

import java.util.HashMap;

import Log.TheLog;
import application.Constant;
import model.domain.Room;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RoomView extends VBox{
	ImageView imageView = new ImageView();;
	public static HashMap<Long, Image> statusImage = new HashMap<>();
	Label idRoom = new Label();
	{
		statusImage.put(Constant.FREE, new Image(Constant.RoomFreeImgUrl));
		statusImage.put(Constant.BOOKED, new Image(Constant.RoomBookedImgUrl));
		statusImage.put(Constant.REGISTED, new Image(Constant.RoomRegistedImgUrl));
		statusImage.put(Constant.CLEANNING, new Image(Constant.RoomCleanningImgUrl));
	}
	
	public RoomView(Room room) {
		super();
		imageView.setFitHeight(128);
		imageView.setFitWidth(128);
		refresh(room);
		
		getChildren().addAll(imageView, idRoom);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(15));
	}
	
	public void refresh(Room room) {
		imageView.setImage(statusImage.get(room.getstatus()));
		idRoom.setText(String.valueOf(room.getidRoom()));
	}
}

package application.toolkit;

import java.util.HashMap;

import Log.TheLog;
import application.Constant;
import model.domain.Room;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RoomView extends VBox{
	ImageView imageView = new ImageView();;
	public static HashMap<Long, Image> statusImage = new HashMap<>();
	public static HashMap<Long, String> statusString = new HashMap<>();
	Text idRoom = new Text();
	Label statusS = new Label();
	{
		statusImage.put(Constant.FREE, new Image(Constant.RoomFreeImgUrl));
		statusImage.put(Constant.BOOKED, new Image(Constant.RoomBookedImgUrl));
		statusImage.put(Constant.REGISTERED, new Image(Constant.RoomRegisteredImgUrl));
		statusImage.put(Constant.CLEANNING, new Image(Constant.RoomCleanningImgUrl));
		statusString.put(Constant.FREE, "空余");
		statusString.put(Constant.BOOKED, "已预定");
		statusString.put(Constant.REGISTERED, "入住中");
		statusString.put(Constant.CLEANNING, "清洁中");
	}
	
	
	public RoomView(Room room) {
		super();
		statusS.setFont(new Font(12));
		imageView.setFitHeight(128);
		imageView.setFitWidth(128);
		refresh(room);
		
		getChildren().addAll(imageView, idRoom, statusS);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(15));
	}
	
	public void refresh(Room room) {
		Image image = statusImage.get(room.getstatus());
		String mString = statusString.get(room.getstatus());
		String idroom = String.valueOf(room.getidRoom());
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				imageView.setImage(image);
				idRoom.setText(idroom);
				statusS.setText(mString);
			}
		});
	}
}

package application.toolkit;

import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import application.App;
import application.Constant;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Screen;

public class Monitor extends ScrollPane {
	private App platform;
	public HashMap<Long, Image> statusImage = new HashMap<>();
	public FlowPane flowPane;

	public Monitor(App platform) {
		super();
		this.platform = platform;
		statusImage.put(Constant.FREE, new Image(Constant.RoomFreeImgUrl));
		statusImage.put(Constant.BOOKED, new Image(Constant.RoomBookedImgUrl));
		statusImage.put(Constant.REGISTED, new Image(Constant.RoomRegistedImgUrl));
		statusImage.put(Constant.CLEANNING, new Image(Constant.RoomCleanningImgUrl));
		init();
	}

	private void init() {
		flowPane = new FlowPane();
		for(int i = 0; i<100;i++) {
			RoomView roomView = new RoomView();
			flowPane.getChildren().add(roomView);
		}
		setContent(flowPane);
		setFitToWidth(true);
		Rectangle2D primaryScreenBound = Screen.getPrimary().getVisualBounds();
		setPrefSize(primaryScreenBound.getWidth() *4/5, primaryScreenBound.getHeight() *3/4);
		setMaxSize(primaryScreenBound.getWidth() *4/5, primaryScreenBound.getHeight() *3/4);
	}
	private void refresh() {
		
	}
}

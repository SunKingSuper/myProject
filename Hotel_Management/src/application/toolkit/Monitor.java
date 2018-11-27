package application.toolkit;

import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import application.App;
import application.Constant;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Screen;

public class Monitor extends FlowPane {
	private App platform;
	public HashMap<Integer, Image> statusImage = new HashMap<>();

	public Monitor(App platform) {
		super();
		this.platform = platform;
		statusImage.put(Constant.FREE, new Image("Resource/homeGreen.png"));
		statusImage.put(Constant.BOOKED, new Image("Resource/homePurple.png"));
		statusImage.put(Constant.REGISTERED, new Image("Resource/homeBlue.png"));
		statusImage.put(Constant.CLEANNING, new Image("Resource/homeYellow.png"));
		init();
	}

	private void init() {
		for(int i = 0; i<100;i++) {
			RoomView roomView = new RoomView();
			getChildren().add(roomView);
		}
		
		setHgap(10);
		setVgap(10);
		Rectangle2D primaryScreenBound = Screen.getPrimary().getVisualBounds();
		setPrefSize(primaryScreenBound.getWidth() *4/5, primaryScreenBound.getHeight() *3/4);
	}
	private void refresh() {
		
	}
}

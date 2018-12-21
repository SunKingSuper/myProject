package application.toolkit;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import application.App;
import application.Constant;
import control.Core;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Screen;
import model.domain.Room;

public class Monitor extends ScrollPane {
	private App platform;

	public FlowPane flowPane;

	public Monitor(App platform, List<Room> list) {
		super();
		this.platform = platform;
		init(list);
	}

	private void init(List<Room> list) {
		flowPane = new FlowPane();
		Iterator<Room> iterator = list.iterator();
		while (iterator.hasNext()) {
			RoomView roomView = new RoomView(iterator.next());
			flowPane.getChildren().add(roomView);
		}
		setContent(flowPane);
		setFitToWidth(true);
		Rectangle2D primaryScreenBound = Screen.getPrimary().getVisualBounds();
		setPrefSize(primaryScreenBound.getWidth() * 4 / 5, primaryScreenBound.getHeight() * 3 / 4);
		setMaxSize(primaryScreenBound.getWidth() * 4 / 5, primaryScreenBound.getHeight() * 3 / 4);
	}
/**
 * 这个refresh有问题，有可能别人把房间删掉而不能及时更新
 */
	public void refresh(List<Room> list) {								
		Iterator<Room> iterator = list.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			RoomView roomView = (RoomView) flowPane.getChildren().get(i);
			roomView.refresh(iterator.next());
			i++;
		}
	}
}

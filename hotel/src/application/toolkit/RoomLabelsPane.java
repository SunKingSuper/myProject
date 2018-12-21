package application.toolkit;

import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import application.App;
import control.Core;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.domain.Room;

public class RoomLabelsPane extends ScrollPane {
	public VBox content = new VBox();

	long status;
	TitleInputField idRoom;
	
	/**
	 *  这个类是从CleannerStage里提取出来，为了灵活性，我还是把idRoom做成参数传进来
	 *  后面会有个refresh 也是不想主动调用timer
	 * @param platform
	 * @param status
	 * @param idRoom
	 */
	public RoomLabelsPane(App platform, long status, TitleInputField idRoom) {
		this.status = status;
		this.idRoom = idRoom;
		
		content.setPadding(new Insets(10));
		content.setAlignment(Pos.CENTER);
		content.setSpacing(5);
		setContent(content);
		setPrefHeight(250);
	}
	
	public void refresh() {
		List<Room> list = Core.getRoombyStatus(status);
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				content.getChildren().removeAll();
				Iterator<Room> iterator = list.iterator();
				while (iterator.hasNext()) {
					content.getChildren().add(new RoomLabel(iterator.next(), idRoom));
				}
			}
		});
	}
}

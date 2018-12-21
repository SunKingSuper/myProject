package application;

import model.domain.Room;

import java.util.TimerTask;

import application.toolkit.ExitButton;
import application.toolkit.RoomLabel;
import application.toolkit.RoomLabelsPane;
import application.toolkit.TitleInputField;
import control.Core;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

public class LeaveStage extends MyStage {
	private long status = Constant.REGISTERED;
	private TitleInputField idRoom = new TitleInputField("房间号");
	private RoomLabelsPane registeredRoom = new RoomLabelsPane(platform, status, idRoom);

	public LeaveStage(App platform) {
		initModality(Modality.WINDOW_MODAL);
		ui(platform);
		setTitle(Constant.MenuLeave);
		setTitle("退房处理");
	}

	@Override
	protected void init() {
		Button cancel = new Button("取消");
		Label label = new Label("已入住房间");
		cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				close();
			}
		});
		
		Button ok = new Button("确认");
		ok.setMinWidth(getWidth());
		ok.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				String room = idRoom.getText();
				for(Node label: registeredRoom.content.getChildren()) {
					Label roomLabel = (RoomLabel) label;
					if (room.equals(roomLabel.getText())) {
						Core.leaveHandle((Room) label.getUserData());
						new WarnDialog(platform, roomLabel.getText() + "退房完成");
						}
					}
			}
		});
		
		platform.timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				registeredRoom.refresh();
			}
		}, 0, 6000); //每分钟刷新一次
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(label, registeredRoom, idRoom, ok);
		vbox.setPrefSize(300, 400);
		vbox.setAlignment(Pos.CENTER);
		
		VBox mainroot = new VBox();
		mainroot.getChildren().addAll(cancel, 	vbox);
		root = mainroot;
	}
}

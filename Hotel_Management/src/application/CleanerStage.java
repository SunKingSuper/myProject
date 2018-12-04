package application;

import application.toolkit.RoomLabel;
import control.Core;
import model.domain.Room;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.StageStyle;
import model.Dao.RoomDao;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import application.toolkit.TitleInputField;

import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import application.toolkit.ExitButton;

public class CleanerStage extends MyStage {
	private VBox cleanningRoom = new VBox();
	private TitleInputField idRoom = new TitleInputField("房间号");
	public CleanerStage(App platform) {
		initStyle(StageStyle.UNDECORATED);
		ui(platform);
	}
	@Override
	protected void init() {
		Button exit = new ExitButton(platform);
		Label label = new Label("待清理房间");
		cleanningRoom.setPadding(new Insets(10));
		cleanningRoom.setAlignment(Pos.CENTER);
		cleanningRoom.setSpacing(5);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(cleanningRoom);
		scrollPane.setPrefHeight(250);
		Button ok = new Button("清理完成");
		ok.setMinWidth(getWidth());
		ok.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				String room = idRoom.getText();
				for(Node label: cleanningRoom.getChildren()) {
					Label roomLabel = (RoomLabel) label;
					if (room.equals(roomLabel.getText())) {
						Core.roomIsReady((Room) label.getUserData());
						new WarnDialog(platform, roomLabel.getText() + "清理完成");
						}
					}
			}
		});
		
		platform.timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				List<Room> list = Core.watiToClean();
				cleanningRoom.getChildren().removeAll();
				Iterator<Room> iterator = list.iterator();
				while(iterator.hasNext()) {
					cleanningRoom.getChildren().add(new RoomLabel(iterator.next(), idRoom));
				}
			}
		}, 0, 6000); //每分钟刷新一次
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(label, scrollPane, idRoom, ok);
		vbox.setPrefSize(300, 400);
		vbox.setAlignment(Pos.CENTER);
		
		VBox mainroot = new VBox();
		mainroot.getChildren().addAll(exit,vbox);
		root = mainroot;
	}
}

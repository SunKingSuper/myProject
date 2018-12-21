package application.toolkit;

/**
 * @author SKS
 * BookRoom 与 BookGuest 好像， 可是我不会抽象他们（真懒），直接复制粘贴了
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Log.TheLog;
import application.Constant;
import control.Core;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import model.domain.Room;
import model.domain.RoomType;

public class BookRoom extends VBox {
	private long STATUS; //因为在登记入住那里也会利用这个页面
	private Label title = new Label("房间选择");
	public ComboBox<RoomType> cmb = new ComboBox<>();
	public ComboBox<Room> roomChoice = new ComboBox<Room>();
	public HBox roomItems = new HBox();										//之所以选择从这里public是为了方便实时计算金额
	private List<Room> bookRooms = new ArrayList<>();

	public BookRoom(long status) {
		super();
		this.STATUS = status;
		setSpacing(10);
		cmb.setPromptText("选择房型");
		cmb.getItems().addAll(Core.getFreeRoomType());

		cmb.setCellFactory(new Callback<ListView<RoomType>, ListCell<RoomType>>() {

			@Override
			public ListCell<RoomType> call(ListView<RoomType> param) {
				return new ListCell<RoomType>() {
					{
						setContentDisplay(ContentDisplay.TEXT_ONLY);
					}

					@Override
					protected void updateItem(RoomType item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setText(null);
						} else {
							setText(item.toString());
						}
					}
				};
			}
		});

		BookRoom self = this;
		cmb.valueProperty().addListener(new ChangeListener<RoomType>() {

			@Override
			public void changed(ObservableValue<? extends RoomType> observable, RoomType oldValue, RoomType newValue) {
				roomChoiceRefresh(newValue);
			}
		});

		roomChoice.setCellFactory(new Callback<ListView<Room>, ListCell<Room>>() {

			@Override
			public ListCell<Room> call(ListView<Room> param) {
				return new ListCell<Room>() {
					{
						setContentDisplay(ContentDisplay.TEXT_ONLY);
					}

					@Override
					protected void updateItem(Room item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setText(null);
						} else {
							setText(String.valueOf(item.getidRoom()));
						}
					}
				};
			}
		});
		roomChoice.valueProperty().addListener(new ChangeListener<Room>() {

			@Override
			public void changed(ObservableValue<? extends Room> observable, Room oldValue, Room newValue) {
				if (newValue != null) {
					roomItems.getChildren().add(0, new BookRoomItem(self, roomItems, newValue));
					Core.registerRoomTemp(newValue, self);
					roomChoiceRefresh(cmb.getValue());
				}
			}
		});

		cmb.setPromptText("选择房间类型");
		roomChoice.setPromptText("选择房间");
		cmb.setValue(cmb.getItems().get(0));
		roomItems.setSpacing(5);
		getChildren().addAll(title, cmb, roomChoice, roomItems);
		setPrefHeight(100);
	}

	public void roomChoiceRefresh(RoomType newValue) {
		List<Room> list = Core.getFreeRoomByRoomType(newValue);
		roomChoice.getItems().clear();
		roomChoice.getItems().addAll(list);
	}
	
	public List<Room> getRooms() {
		List<Node> list = roomItems.getChildren();
		Iterator<Node> iterator = list.iterator();
		List<Room> rooms = new ArrayList<Room>();
		while (iterator.hasNext()) {
			Room room = (Room) iterator.next().getUserData();
			room.setstatus(STATUS);
			rooms.add(room);
		}
		return rooms;
	}
}

class BookRoomItem extends Button {
	private Room data;

	public BookRoomItem(BookRoom grandparent, HBox parent, Room room) {
		setUserData(room);
		Label title = new Label(String.valueOf(room.getidRoom()));
		title.setFont(new Font(24));
		setGraphic(title);
		BookRoomItem self = this;
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Core.cancelRoomTemp(data, grandparent);
				grandparent.roomChoiceRefresh(grandparent.cmb.getValue());
				parent.getChildren().remove(self);
			}
		});
	}
}
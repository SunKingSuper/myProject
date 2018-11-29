package application;

import java.util.Iterator;
import java.util.List;

import application.toolkit.NumberFiled;
import control.Core;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import model.Dao.RoomTypeDao;
import model.Dao.Impl.RoomTypeDaoImpl;
import model.domain.Guest;
import model.domain.RoomType;

public class BookStage extends MyStage {
	ObservableList<Label> roomTypeList = FXCollections.observableArrayList();
	ObservableList<Label> bookRoomTypeList = FXCollections.observableArrayList();
	ListView<Label> bookRoomTypeListView = new ListView<>();

	public BookStage(App platform) {
		check();
		ui(platform);
	}

	private void check() {
		List<RoomType> list = Core.roomTypes();
		Iterator<RoomType> iterator = list.iterator();
		while (iterator.hasNext()) {
			RoomType roomType = iterator.next();
			Label label = new Label(roomType.toString());
			label.setUserData(roomType);
			roomTypeList.add(label); // 这里用Label进行封装
		}
	}

	@Override
	protected void init() {
		Label bookerName = new Label("预定人姓名");
		ComboBox<Guest> bookerNameT = new ComboBox<Guest>();
		bookerNameT.setEditable(true);
		TextField tField = bookerNameT.editorProperty().get();
		tField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				List<Guest> guests = Core.guestChoice(newValue);
				if (guests != null && guests.size() <= 1) {
					bookerNameT.getItems().addAll(guests);
					bookerNameT.show();
				} else {
					bookerNameT.getItems().removeAll();
				}
			}
		});

		Label bookerPhone = new Label("电话号码");
		NumberFiled bookerPhoneT = new NumberFiled();

		HBox registerBox = new HBox();
		registerBox.setAlignment(Pos.CENTER);
		Button addRegister = new Button();
		addRegister.setGraphic(new ImageView(new Image("Resource/add.png")));
		registerBox.getChildren().add(addRegister);

		HBox roomBox = new HBox();
		roomBox.setAlignment(Pos.CENTER);
		Button addRoom = new Button();
		addRoom.setGraphic(new ImageView(new Image("Resource/add.png")));

		ListView<Label> roomTypeListView = new ListView<>();
		roomTypeListView.setItems(roomTypeList);
		roomTypeListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					Label currentItemSelected = roomTypeListView.getSelectionModel().getSelectedItem();
					bookRoomTypeList.add(currentItemSelected);
					bookRoomTypeListView.setItems(bookRoomTypeList);
					bookRoomTypeListView.refresh();
				}
			}
		});
		bookRoomTypeListView.setItems(bookRoomTypeList);
		bookRoomTypeListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					int currentIndexSelected = roomTypeListView.getSelectionModel().getSelectedIndex();
					bookRoomTypeList.remove(currentIndexSelected);
					bookRoomTypeListView.setItems(bookRoomTypeList);
					bookRoomTypeListView.refresh();
				}
			}
		});
		Button ok = new Button("确认");
		Button cancel = new Button("取消");

		cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				stage.close();
				platform.exit();
			}
		});

		GridPane mainroot = new GridPane();
		mainroot.setAlignment(Pos.CENTER);
		mainroot.add(bookerName, 0, 0);
		mainroot.add(bookerNameT, 1, 0);
		mainroot.add(bookerPhone, 0, 1);
		mainroot.add(bookerPhoneT, 1, 1);
		mainroot.add(roomBox, 0, 2);
		mainroot.add(roomTypeListView, 0, 3);
		mainroot.add(bookRoomTypeListView, 1, 3);
		mainroot.add(cancel, 0, 4);
		mainroot.add(ok, 1, 4);

		mainroot.setHgap(10);
		mainroot.setVgap(10);

		root = mainroot;
		stage.setWidth(800);
		stage.setHeight(600);
	}

}

package model.test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import application.App;
import application.Constant;
import application.MyStage;
import application.WarnDialog;
import application.toolkit.NumberField;
import application.toolkit.TitleInputField;
import control.Core;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import model.domain.Order;
import model.domain.Room;
import model.domain.RoomType;
/**
 * BookStage 注册界面
 * @author SKS
 * 问题： BookStage逻辑较为复杂，需要精心设计，而且能够跟RegisterStage
 * 构建Order的流程
 */
class BookStage extends MyStage {
	ObservableList<Label> roomTypeList = FXCollections.observableArrayList();
	ObservableList<Label> bookRoomTypeList = FXCollections.observableArrayList();
	ListView<Label> bookRoomTypeListView = new ListView<>();
	HashMap<String, Integer> roomTypeLeft = new HashMap();	// 记录剩余FREE房间
	HBox registerBox = new HBox();
	Button addRegister = new Button();
	NumberField bookerPhoneT = new NumberField();
	DatePicker registerDate = new DatePicker();
	DatePicker leftDate = new DatePicker();
	
	Timer timer = new Timer();
	
	private Guest booker = null;
	
	public BookStage(App platform) {
		ui(platform);
		setTitle(Constant.MenuBook);
	}

	private void check(List<RoomType> list) {
		roomTypeList.clear();
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
		bookerName.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue == false) {
					booker = bookerNameT.getSelectionModel().getSelectedItem();
					if (booker == null) {
						booker = new Guest();
						booker.setname(tField.getText());
						Core.saveNewGuest(booker);
					}
				}
			}
		});

		Label bookerPhone = new Label("电话号码");
		HBox dateRecord = new HBox();
		dateRecord.getChildren().addAll(new Label("入住日期"), registerDate, new Label("离开日期"), leftDate);
		
		registerBox.setAlignment(Pos.CENTER);
		addRegister.setGraphic(new ImageView(new Image(Constant.AddImgUrl)));
		registerBox.getChildren().add(addRegister);
		addRegister.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				add();
			}
		});

		HBox roomBox = new HBox();
		roomBox.setAlignment(Pos.CENTER);
		Button addRoom = new Button();
		addRoom.setGraphic(new ImageView(new Image(Constant.AddImgUrl)));

		ListView<Label> roomTypeListView = new ListView<>();
		roomTypeListView.setItems(roomTypeList);
		roomTypeListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					Label currentItemSelected = roomTypeListView.getSelectionModel().getSelectedItem();
					RoomType roomType = (RoomType) currentItemSelected.getUserData();
					if (roomTypeLeft.get(roomType.getroomType()) > 0) {
						bookRoomTypeList.add(currentItemSelected);
						bookRoomTypeListView.setItems(bookRoomTypeList);
						bookRoomTypeListView.refresh();
						roomTypeLeft.put(roomType.getroomType(), roomTypeLeft.get(roomType.getroomType()) - 1);
					} else {
						new WarnDialog(platform, "目前没有该房型");
					}
					
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
		
		TitleInputField comment = new TitleInputField("备注");
		
		Button ok = new Button("确认");
		Button cancel = new Button("取消");
		
		ok.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Order order = new Order();
				order.setstatus(Constant.oREGISTER);
				order.setregisterDate(new Timestamp(Date.valueOf(registerDate.getValue()).getTime()));
				order.setleftDate(new Timestamp(Date.valueOf(leftDate.getValue()).getTime()));
				order.setorderTime(new Timestamp(new  java.util.Date().getTime()));
				order.setcomment(comment.getText());
				order.setname(booker.getname());
			}
		});

		cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				exit();
			}
		});

		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				refresh();
			}
		}, 0L, Constant.rFreshPeriod);
		
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
		setWidth(800);
		setHeight(600);
	}
	
	public void refresh() {
		List<Room> rooms = Core.checkRoom();
		List<RoomType> list = Core.roomTypes();
		check(list);
		Iterator<Room> iterator = rooms.iterator();
		roomTypeLeft.clear();
		while(iterator.hasNext()) {
			Room room = iterator.next();
			if(room.getstatus() == Constant.FREE) {
				roomTypeLeft.put(room.getroomType(), roomTypeLeft.getOrDefault(room.getroomType(), 0) + 1);
			}
		}
	}
	
	public void add() {
		registerBox.getChildren().remove(addRegister);
//		registerBox.getChildren().add(new RegisterItem());
		registerBox.getChildren().add(addRegister);
	}
	
	private void exit() {
		timer.cancel();
		close();
	}
}

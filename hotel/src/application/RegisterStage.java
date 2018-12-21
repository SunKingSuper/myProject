package application;

/**
 * @author SKS
 * 这里设定的逻辑是这样子的
 * 如果我已经预定的话，我只要确保把房卡发放到位就好了
 * 		
 * 如果我没有预定的话，我只要使用预定页面就好了
 */

import application.toolkit.TitleInputField;
import control.Core;

import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import model.domain.Guest;
import model.domain.Order;
import application.toolkit.CancelOkGroup;
import application.toolkit.NumberField;
import application.toolkit.RoomLabel;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import model.domain.Room;

public class RegisterStage extends MyStage {
	TitleInputField phoneNumer = new TitleInputField("预定人电话号码");

	public RegisterStage(App platform) {
		initModality(Modality.WINDOW_MODAL);
		setTitle(Constant.MenuRegister);
		ui(platform);
	}

	@Override
	protected void init() {
		Button hasBooked = new Button("已有预定");
		Button bookNow = new Button("现在预定并入住");
		hasBooked.setPrefWidth(250);
		bookNow.setPrefWidth(250);
		Button cancel = new Button("取消");

		cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				close();
			}
		});
		hasBooked.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				new ValidationStage(platform, phoneNumer.getText());
				close();
			}
		});
		bookNow.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				new BookStage(platform, Constant.REGISTERED);
				close();
			}
		});

		VBox mainroot = new VBox();
		mainroot.getChildren().addAll(phoneNumer, hasBooked, bookNow, cancel);
		mainroot.setAlignment(Pos.CENTER);
		mainroot.setSpacing(10);
		setWidth(300);
		setHeight(400);
		root = mainroot;
	}
}

class ValidationStage extends MyStage {
	private Order order;
	CancelOkGroup cancelok = new CancelOkGroup(this);
	TitleInputField main_guest_Name = new TitleInputField("预定人名字");
	TitleInputField phoneNumber = new TitleInputField("预定电话号码", new NumberField());
	Label registeredRoom = new Label("预定房间列表");
	FlowPane roomlist = new FlowPane();
	Label guests = new Label("客人信息确认");
	FlowPane guestlist = new FlowPane();
	ScrollPane comment = new ScrollPane();

	public ValidationStage(App platform, String phoneNumber) {
		order = Core.registerhandle(phoneNumber);
		if (order == null) {
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("查询失败");
			error.setContentText("此电话下无订单");
			error.showAndWait();
			new RegisterStage(platform);
			close();
		}
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				ui(platform);
				setTitle("入住办理");
			}
		});
	}

	@Override
	protected void init() {
		main_guest_Name.inputfield.setText(order.getname());
		main_guest_Name.inputfield.setEditable(false);
		comment.setContent(new Label(order.getcomment()));
		prepare();
		cancelok.ok.setText("登记完成");
		cancelok.ok.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Core.orderFinish(order);
				close();
			}
		});
		VBox mainroot = new VBox();
		mainroot.getChildren().addAll(main_guest_Name, phoneNumber, registeredRoom, roomlist, guests, guestlist,
				comment, cancelok);

		root = mainroot;
	}

	private void prepare() {
		List<Room> rooms = Core.getBookRoom(order.getidOrder());
		Iterator<Room> roomIterator = rooms.iterator();
		while (roomIterator.hasNext()) {
			Room room = (Room) roomIterator.next();
			roomlist.getChildren().add(new PreValidationItem(roomlist, String.valueOf(room.getidRoom())));
		}
		List<Guest> guests = Core.getBookGuests(order.getidOrder());
		Iterator<Guest> guestIterator = guests.iterator();
		while (guestIterator.hasNext()) {
			Guest guest = (Guest) guestIterator.next();
			guestlist.getChildren().add(new PreValidationItem(guestlist, guest.getname() + "\n" + guest.getidCard()));
		}
	}
}

class PreValidationItem extends Button {
	public PreValidationItem(FlowPane parent, String msg) {
		setText(msg);
		PreValidationItem self = this;
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				parent.getChildren().remove(self);
			}
		});
	}
}

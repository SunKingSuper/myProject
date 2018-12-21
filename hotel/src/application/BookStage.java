package application;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import application.Constant;
import application.toolkit.BookGuest;
import application.toolkit.BookRoom;
import application.toolkit.CancelOkGroup;
import application.toolkit.NumberField;
import application.toolkit.TitleInputField;
import control.Core;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import model.domain.Order;
import model.domain.Room;
import model.domain.RoomType;

public class BookStage extends MyStage {
	TitleInputField bookerName = new TitleInputField("预定人姓名");
	TitleInputField phonenumber = new TitleInputField("电话号码", new NumberField());
	DatePicker regiterDate = new DatePicker();
	DatePicker leftDate = new DatePicker();
	BookRoom roomType;
	BookGuest guests = new BookGuest();
	Label amount = new Label("金额:");
	TextArea comment = new TextArea();
	CancelOkGroup cancelok = new CancelOkGroup(1000, true, this);
	private long STATUS;

	public BookStage(App platform, long status) { 
		STATUS = status;
		roomType = new BookRoom(status); //因为在登记入住那里也会利用这个页面 是直接入住呢还是只下订单
		initModality(Modality.WINDOW_MODAL);
		ui(platform);
		if (status == Constant.BOOKED) {
			setTitle("预定房间");
		} else if (status == Constant.REGISTERED) {
			setTitle("入住办理");
		}
	}

	@Override
	protected void init() {
		VBox subroot = new VBox();
		bookerName.setAlignment(Pos.CENTER_LEFT);
		phonenumber.setAlignment(Pos.CENTER_LEFT);
		// 通过电话号码判断是不是会员，并且判断自动填入第一个入住人里
		bookerName.inputfield.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(oldValue && ! newValue) {
					guests.setMainName(bookerName.getText());
				}
			}
		});
		
		
		regiterDate.setValue(LocalDate.now());
		leftDate.setValue(regiterDate.getValue().plusDays(1));                                     //默认今晚住一天
		HBox registerDateGroup = new HBox();
		registerDateGroup.getChildren().addAll(new Label("入住日期"), regiterDate);
		registerDateGroup.setSpacing(10);
		HBox leftDateGroup = new HBox();
		leftDateGroup.getChildren().addAll(new Label("离店日期"), leftDate);
		leftDateGroup.setSpacing(10);

		amount.setUserData(0);
		// 智能计算总金额,通过roomChoice来判断有没有插入
		roomType.roomItems.getChildren().addListener(new ListChangeListener<Node>() {

			@Override
			public void onChanged(Change<? extends Node> c) {
				double price = Core.calculate(roomType.getRooms(), Core.guestRoleValidation(phonenumber.getText()));
				amount.setText(String.format("金额: %5.2f", price));
				amount.setUserData(price); // 保存在amount
			}
		});
		
		Label commentL = new Label("备注");
		
		cancelok.cancel.setText("退出");
		cancelok.setSpacing(350);
		cancelok.ok.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				long orderStatus =  Constant.oBOOK;
				if (STATUS == Constant.REGISTERED) {
					orderStatus = Constant.oREGISTER;
				}
				Core.bookhandle(orderStatus, bookerName.getText(), phonenumber.getText(), regiterDate.getValue(), leftDate.getValue(),
						roomType.getRooms(), guests.getGuests(), amount.getUserData(), comment.getText());
			
				new BookStage(platform, STATUS);
				close();
			}
		});
		
		amount.setFont(new Font(24));
		if (STATUS == Constant.REGISTERED) {
			cancelok.ok.setText("确认办理入住");
		}
		
		subroot.setAlignment(Pos.CENTER_LEFT);
		subroot.getChildren().addAll(bookerName, phonenumber, registerDateGroup, leftDateGroup, roomType, guests,
				amount, commentL, comment, cancelok);
		subroot.setPadding(new Insets(20));
		subroot.setSpacing(10);
		ScrollPane mainroot = new ScrollPane();
		mainroot.setContent(subroot);
		root = mainroot;
		setWidth(600);
		setHeight(500);
	}
}
package application;

import model.domain.Order;
import application.toolkit.CancelOkGroup;
import application.toolkit.NumberField;
import application.toolkit.TitleInputField;
import control.Core;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class LeaveStage extends MyStage {
	private TitleInputField idRoom = new TitleInputField("房号", new NumberField());;
	private TextArea showOrder = new TextArea();

	public LeaveStage(App platform) {
		ui(platform);
		setTitle(Constant.MenuLeave);
		setTitle("退房处理");
	}

	@Override
	protected void init() {
		CancelOkGroup buttons = new CancelOkGroup();
		buttons.ok.setText("确认退房");
		buttons.ok.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				leaveHandle();
			}
		});

		idRoom.inputfield.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				showOrder.clear();
				searchHandle();
			}
		});

		showOrder.setEditable(false);
		VBox mainroot = new VBox();
		mainroot.getChildren().addAll(idRoom, showOrder);
	}

	private void leaveHandle() {
		Order order = (Order) showOrder.getUserData();
		if (order != null) {
			Core.leaveHandle(Long.parseLong(idRoom.getText()), order);
			new WarnDialog(platform, "退房成功");
		}
	}

	private void searchHandle() {
		Order order = Core.searchidRoom(Long.parseLong(idRoom.getText()));
		if (order == null) {
			showOrder.setText(Constant.OrderNotFound);
		} else {
			showOrder.setUserData(order);
			showOrder.setText(order.toString());
		}
	}
}

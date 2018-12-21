package application;

import application.toolkit.TitleInputField;
import control.Core;

import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import application.toolkit.CancelOkGroup;
import application.toolkit.NumberField;
import application.toolkit.RoomLabel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.domain.Room;

public class RegisterStage extends MyStage {
	Timer timer = new Timer();
	
	HBox hBox = new HBox();
	TitleInputField room = new TitleInputField("房间号", new NumberField());
	TitleInputField guest = new TitleInputField("入住人姓名");
	TitleInputField idCard = new TitleInputField("身份证");
	FlowPane roomshow = new FlowPane();
	
	public RegisterStage(App platform) {
		setTitle(Constant.MenuRegister);
		ui(platform);
	}

	@Override
	protected void init() {
		CancelOkGroup cancelok = new CancelOkGroup(150, false, this);
		Label title = new Label("空余房间");
		ScrollPane freeroom = new ScrollPane();
		RadioButton isTogether = new RadioButton();
		hBox.getChildren().add(isTogether);
		TitleInputField main_Guest = new TitleInputField("主要订单人身份证号");
		freeroom.setContent(roomshow);
		
		cancelok.ok.setText("入住登记");
		
		cancelok.ok.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				registerHandle();
			}
		});
		
		cancelok.cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				exit();
			}
		});
		
		isTogether.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) {
					hBox.getChildren().add(main_Guest);
				} else {
					hBox.getChildren().remove(main_Guest);
				}
			}
		});
		
		main_Guest.inputfield.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				main_Guest.inputfield.setText(newValue);
			}
		});
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				refresh();
			}
		}, 0L, Constant.rFreshPeriod);

		VBox mainroot = new VBox();
		mainroot.setAlignment(Pos.CENTER_RIGHT);
		mainroot.setSpacing(10);
		mainroot.setPadding(new Insets(20));
		mainroot.getChildren().addAll(title, freeroom, hBox, room, guest, idCard, cancelok);
		
		root = mainroot;
	}
	
	private void registerHandle() {
		String msg =Core.registerhandle();
		if (msg != null) {
			new WarnDialog(platform, msg);
		}
	}
	
	private void refresh() {
		roomshow.getChildren().clear();
		List<Room> list = Core.checkRoom();
		Iterator<Room> iterator = list.iterator();
		while(iterator.hasNext()) {
			RoomLabel roomLabel = new RoomLabel(iterator.next(), room);
			roomshow.getChildren().add(roomLabel);
		}
	}
	
	private void exit() {
		timer.cancel();
		close();
	}
}

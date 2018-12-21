package application.toolkit;

/**
 * @author SKS
 * BookRoom 与 BookGuest 好像， 可是我不会抽象他们（真懒），直接复制粘贴了
 * 但这里出现一个很棘手的问题
 * 我不能确定订单人与登记入住的是否一致，如果看入住的名字相同，就很担心把数据给弄错了
 * 我的解决方法：
 * - 修改原来流程
 * --如果没查到订单人的会员身份，默认普通会员，把数据库main_idGuest修改成String name
 * ---不需要记录订单人信息（真正记录的应该是入住人的身份信息）
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
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import model.Dao.GuestDao;
import model.domain.Guest;
import model.domain.Room;
import model.domain.RoomType;

public class BookGuest extends VBox {
	private Label title = new Label("入住人");
	private Button add = new Button("添加");
	public TitleInputField name = new TitleInputField("姓名");
	private TitleInputField id_card = new TitleInputField("身份证号");
	public HBox guessInfos= new HBox();										//之所以选择从这里public是为了方便实时计算金额
	public void setMainName(String name) {
		this.name.inputfield.setText(name);
	}
	
	public BookGuest() {
		super();

		HBox group = new HBox();
		group.setAlignment(Pos.CENTER_LEFT);
		group.setSpacing(5);
		VBox input = new VBox();
		input.getChildren().addAll(name, id_card);
		input.setAlignment(Pos.CENTER_LEFT);
		input.setSpacing(5);
		group.getChildren().addAll(input, add);
		setSpacing(10);
		guessInfos.setSpacing(5);
		getChildren().addAll(title, group, guessInfos);
		
		guessInfos.getChildren().addListener(new ListChangeListener<Node>() {

			@Override
			public void onChanged(Change<? extends Node> c) {
				title.setText(String.format("入住人 %d人", guessInfos.getChildren().size()));
			}
			
		});
		
		add.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Guest guest = new Guest();
				guest.setname(name.getText());
				guest.setidCard(id_card.getText());
				guessInfos.getChildren().add(new BookGuestItem(guessInfos, guest));
				name.inputfield.setText("");
				id_card.inputfield.setText("");
			}
		});
	}
	
	public List<Guest> getGuests() {
		List<Node> list = guessInfos.getChildren();
		List<Guest> guests = new ArrayList<Guest>();
		Iterator<Node> iterator = list.iterator();
		while (iterator.hasNext()) {
			guests.add((Guest) iterator.next().getUserData());
		}
		return guests;
	}
}

class BookGuestItem extends VBox {
	public Label name = new Label();
	private Label id_card = new Label();
	Button cancel = new Button("删除");
	public BookGuestItem(HBox parent, Guest guest) {
		setUserData(guest);
		name.setText(guest.getname());
		name.setFont(new Font(36));
		id_card.setFont(new Font(16));
		id_card.setText(guest.getidCard());
		BookGuestItem self = this;
		cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				parent.getChildren().remove(self);
			}
		});
		
		getChildren().addAll(name, id_card, cancel);
		setSpacing(5);
	}
}
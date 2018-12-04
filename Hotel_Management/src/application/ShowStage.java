package application;

import application.toolkit.TitleInputField;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import application.toolkit.CancelButton;
import application.toolkit.NumberField;
import control.Core;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.domain.Order;

public class ShowStage extends MyStage {
	TableView<MyOrder> tableView = new TableView<MyOrder>();
	private final ObservableList<MyOrder> myOrders = FXCollections.observableArrayList();
	

	public ShowStage(App platform) {
		ui(platform);
		setTitle("订单查询");
		setWidth(500);
		setHeight(600);
	}

	@Override
	protected void init() {
		TitleInputField queryShow = new TitleInputField(new Label("房间号"), new NumberField());
		Button querybtn = new Button("查询");
		ScrollPane orderShow = new ScrollPane();
		orderShow.setContent(tableView);
		RadioButton showAll = new RadioButton(Constant.OrderShow);
		Button cancel = new CancelButton(this);
		
		querybtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Core.queryOrderbyRoomId(queryShow.getText());
			}
		});
		
		TableColumn<MyOrder,String> Col0 = new TableColumn<MyOrder,String>("订单号");
		TableColumn<MyOrder,String> Col1 = new TableColumn<MyOrder,String>("订单客人号");
		TableColumn<MyOrder,String> Col2 = new TableColumn<MyOrder,String>("入住时间");
		TableColumn<MyOrder,String> Col3 = new TableColumn<MyOrder,String>("预定退房时间");
		TableColumn<MyOrder,String> Col4 = new TableColumn<MyOrder,String>("订单时间");
		TableColumn<MyOrder,String> Col5 = new TableColumn<MyOrder,String>("金额");
		TableColumn<MyOrder,String> Col6 = new TableColumn<MyOrder,String>("状态");
		TableColumn<MyOrder,String> Col7 = new TableColumn<MyOrder,String>("备注");
		tableView.getColumns().addAll(Col0, Col1, Col2, Col3, Col4, Col5, Col6, Col7);

		ObservableList<TableColumn<MyOrder, ?>> observableList = tableView.getColumns();
		observableList.get(0).setCellValueFactory(new PropertyValueFactory<>("idOrder"));
		observableList.get(1).setCellValueFactory(new PropertyValueFactory<>("main_idGuset"));
		observableList.get(2).setCellValueFactory(new PropertyValueFactory<>("registerDate"));
		observableList.get(3).setCellValueFactory(new PropertyValueFactory<>("leftDate"));
		observableList.get(4).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		observableList.get(5).setCellValueFactory(new PropertyValueFactory<>("amount"));
		observableList.get(6).setCellValueFactory(new PropertyValueFactory<>("status"));
		observableList.get(7).setCellValueFactory(new PropertyValueFactory<>("comment"));
		tableView.setItems(myOrders);

		showAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (queryShow.getText().length() == 0) {
					if (showAll.isSelected()) {
						orderShow(Core.showAllOrders());
					} else {
						orderShow(Core.showNotDoneOrders());
					}
				}
			}
		});

		GridPane mainroot = new GridPane();
		mainroot.add(queryShow, 0, 0);
		mainroot.add(querybtn, 1, 0);
		mainroot.add(orderShow, 1, 1);
		mainroot.add(showAll, 0, 2);
		mainroot.add(cancel, 1, 2);
		mainroot.setPadding(new Insets(10));
		mainroot.setHgap(10);
		mainroot.setVgap(10);
		root = mainroot;
	}

	private void orderShow(List<Order> orders) {
		Iterator<Order> iterator = orders.iterator();
		myOrders.clear();
		while (iterator.hasNext()) {
			Order order = iterator.next();
			MyOrder myOrder = new MyOrder();
			myOrder.setidOrder(order.getidOrder());
			myOrder.setmain_idGuest(order.getmain_idGuest());
			myOrder.setregisterDate(order.getregisterDate());
			myOrder.setleftDate(order.getleftDate());
			myOrder.setorderTime(order.getorderTime());
			myOrder.setstatus(order.getstatus());
			myOrder.setamount(order.getamount());
			myOrder.setcomment(order.getcomment());
			myOrders.add(myOrder);
		}

	}

}

class MyOrder {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private StringProperty idOrder;

	public void setidOrder(long value) {
		idOrderProperty().set(String.valueOf(value));
	}

	public String getidOrder() {
		return idOrderProperty().get();
	}

	public StringProperty idOrderProperty() {
		if (idOrder == null)
			idOrder = new SimpleStringProperty(this, "idOrder");
		return idOrder;
	}

	private StringProperty main_idGuest;

	public void setmain_idGuest(long value) {
		main_idGuestProperty().set(String.valueOf(value));
	}

	public String getmain_idGuest() {
		return main_idGuestProperty().get();
	}

	public StringProperty main_idGuestProperty() {
		if (main_idGuest == null)
			main_idGuest = new SimpleStringProperty(this, "main_idGuest");
		return main_idGuest;
	}

	private StringProperty registerDate;

	public void setregisterDate(Timestamp value) {
		registerDateProperty().set(df.format(value));
	}

	public String getregisterDate() {
		return registerDateProperty().get();
	}

	public StringProperty registerDateProperty() {
		if (registerDate == null)
			registerDate = new SimpleStringProperty(this, "registerDate");
		return registerDate;
	}

	private StringProperty leftDate;

	public void setleftDate(Timestamp value) {
		leftDateProperty().set(df.format(value));
	}

	public String getleftDate() {
		return leftDateProperty().get();
	}

	public StringProperty leftDateProperty() {
		if (leftDate == null)
			leftDate = new SimpleStringProperty(this, "leftDate");
		return leftDate;
	}

	private StringProperty orderTime;

	public void setorderTime(Timestamp value) {
		orderTimeProperty().set(df.format(value));
	}

	public String getorderTime() {
		return orderTimeProperty().get();
	}

	public StringProperty orderTimeProperty() {
		if (orderTime == null)
			orderTime = new SimpleStringProperty(this, "orderTime");
		return orderTime;
	}

	private StringProperty amount;

	public void setamount(float value) {
		amountProperty().set(String.valueOf(value));
	}

	public String getamount() {
		return amountProperty().get();
	}

	public StringProperty amountProperty() {
		if (amount == null)
			amount = new SimpleStringProperty(this, "amount");
		return amount;
	}

	private StringProperty status;

	public void setstatus(long value) {
		statusProperty().set(String.valueOf(value));
	}

	public String getstatus() {
		return statusProperty().get();
	}

	public StringProperty statusProperty() {
		if (status == null)
			status = new SimpleStringProperty(this, "status");
		return status;
	}

	private StringProperty comment;

	public void setcomment(String value) {
		commentProperty().set(value);
	}

	public String getcomment() {
		return commentProperty().get();
	}

	public StringProperty commentProperty() {
		if (comment == null)
			comment = new SimpleStringProperty(this, "comment");
		return comment;
	}
}

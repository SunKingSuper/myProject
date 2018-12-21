package application;

import application.toolkit.TitleInputField;
import java.util.Date;
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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import model.domain.Order;

public class ShowStage extends MyStage {
	TableView<MyOrder> tableView = new TableView<MyOrder>();
	private final ObservableList<MyOrder> myOrders = FXCollections.observableArrayList();
	

	public ShowStage(App platform) {
		initModality(Modality.WINDOW_MODAL);
		setTitle("订单查询");
		setWidth(800);
		setHeight(600);
		ui(platform);
	}

	@Override
	protected void init() {
		ScrollPane orderShow = new ScrollPane();
		orderShow.setContent(tableView);
		RadioButton showAll = new RadioButton(Constant.OrderShow);
		Button cancel = new CancelButton(this);
		
		TableColumn<MyOrder,String> Col0 = new TableColumn<MyOrder,String>("订单号");
		TableColumn<MyOrder,String> Col1 = new TableColumn<MyOrder,String>("订单客人名字");
		TableColumn<MyOrder,String> Col2 = new TableColumn<MyOrder,String>("电话号码");
		TableColumn<MyOrder,String> Col3 = new TableColumn<MyOrder,String>("入住时间");
		TableColumn<MyOrder,String> Col4 = new TableColumn<MyOrder,String>("预定退房时间");
		TableColumn<MyOrder,String> Col5 = new TableColumn<MyOrder,String>("订单时间");
		TableColumn<MyOrder,String> Col6 = new TableColumn<MyOrder,String>("金额");
		TableColumn<MyOrder,String> Col7 = new TableColumn<MyOrder,String>("状态");
		TableColumn<MyOrder,String> Col8 = new TableColumn<MyOrder,String>("备注");
		tableView.getColumns().addAll(Col0, Col1, Col2, Col3, Col4, Col5, Col6, Col7, Col8);

		ObservableList<TableColumn<MyOrder, ?>> observableList = tableView.getColumns();
		observableList.get(0).setCellValueFactory(new PropertyValueFactory<>("idOrder"));
		observableList.get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
		observableList.get(2).setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		observableList.get(3).setCellValueFactory(new PropertyValueFactory<>("registerDate"));
		observableList.get(4).setCellValueFactory(new PropertyValueFactory<>("leftDate"));
		observableList.get(5).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		observableList.get(6).setCellValueFactory(new PropertyValueFactory<>("amount"));
		observableList.get(7).setCellValueFactory(new PropertyValueFactory<>("status"));
		observableList.get(8).setCellValueFactory(new PropertyValueFactory<>("comment"));
		tableView.setItems(myOrders);
		orderShow(Core.showNotDoneOrders());
		
		showAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (showAll.isSelected()) {
						orderShow(Core.showAllOrders());
					} else {
						orderShow(Core.showNotDoneOrders());
					}
				}
		});

		VBox mainroot = new VBox();
		mainroot.getChildren().add(orderShow);
		mainroot.getChildren().add(showAll);
		mainroot.getChildren().add(cancel);
		mainroot.setPadding(new Insets(10));
		root = mainroot;
	}

	private void orderShow(List<Order> orders) {
		Iterator<Order> iterator = orders.iterator();
		myOrders.removeAll();
		while (iterator.hasNext()) {
			Order order = iterator.next();
			MyOrder myOrder = new MyOrder();
			myOrder.setAmount(order.getamount());
			myOrder.setComment(order.getcomment());
			myOrder.setIdOrder(order.getidOrder());
			myOrder.setLeftDate(new Date(order.getleftDate().getTime()).toString());
			myOrder.setName(order.getname());
			myOrder.setOrderTime(new Date(order.getorderTime().getTime()).toString());
			myOrder.setPhoneNumber(order.getPhoneNumber());
			myOrder.setRegisterDate(new Date(order.getregisterDate().getTime()).toString());
			myOrder.setStatus(order.getstatus());
			myOrders.add(myOrder);
		}

	}

}

// 我估计是因为那个TimeStamp的原因，改一下order
class MyOrder {
	public long idOrder;
	public String name;
	public String phoneNumber;
	public String registerDate;
	public String leftDate;
	public String orderTime;
	public double amount;
	public long status;
	private String comment;
	public long getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(long idOrder) {
		this.idOrder = idOrder;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getLeftDate() {
		return leftDate;
	}
	public void setLeftDate(String leftDate) {
		this.leftDate = leftDate;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}

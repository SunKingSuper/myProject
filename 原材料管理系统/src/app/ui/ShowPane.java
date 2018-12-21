package app.ui;

import app.control.MaterialControl;
import app.control.OrderControl;
import app.model.Material;
import app.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowPane extends VBox {
	public ObservableList<Material> materials = FXCollections.observableArrayList();
	public ObservableList<Order> orders = FXCollections.observableArrayList();
	public ShowPane(MenuPage parent) {
		super();
		MaterialControl.showMaterials(materials);
		OrderControl.showOrders(orders);
		
		Label titleMaterial = new Label("现有原材料情况");
		ScrollPane material = new ScrollPane();
		TableView<Material> materialView = new TableView<Material>();
		TableColumn<Material,String> Col0m = new TableColumn<Material,String>("原材料编号");
		TableColumn<Material,String> Col1m = new TableColumn<Material,String>("原材料名称");
		TableColumn<Material,String> Col2m = new TableColumn<Material,String>("规格");
		TableColumn<Material,String> Col3m = new TableColumn<Material,String>("现有库存");
		TableColumn<Material,String> Col4m = new TableColumn<Material,String>("单位");
		materialView.getColumns().addAll(Col0m, Col1m, Col2m, Col3m, Col4m);
		
		ObservableList<TableColumn<Material, ?>> observableListMaterial = materialView.getColumns();
		observableListMaterial.get(0).setCellValueFactory(new PropertyValueFactory<>("idmaterial"));
		observableListMaterial.get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
		observableListMaterial.get(2).setCellValueFactory(new PropertyValueFactory<>("standard"));
		observableListMaterial.get(3).setCellValueFactory(new PropertyValueFactory<>("storeAmount"));
		observableListMaterial.get(4).setCellValueFactory(new PropertyValueFactory<>("unit"));
		materialView.setItems(materials);
		
		Label titleOrder = new Label("目前订单状况情况");
		ScrollPane order = new ScrollPane();
		TableView<Order> orderView = new TableView<Order>();
		TableColumn<Order,String> Col0 = new TableColumn<Order,String>("订单编号");
		TableColumn<Order,String> Col1 = new TableColumn<Order,String>("原材料编号");
		TableColumn<Order,String> Col2 = new TableColumn<Order,String>("订单到期日期");
		TableColumn<Order,String> Col3 = new TableColumn<Order,String>("仓库编号");
		TableColumn<Order,String> Col4 = new TableColumn<Order,String>("供应商");
		TableColumn<Order,String> Col5 = new TableColumn<Order,String>("运输商");
		TableColumn<Order,String> Col6 = new TableColumn<Order,String>("订单数量");
		TableColumn<Order,String> Col7 = new TableColumn<Order,String>("入库数量");
		TableColumn<Order,String> Col8 = new TableColumn<Order,String>("负责人");
		orderView.getColumns().addAll(Col0, Col1, Col2, Col3, Col4, Col5, Col6, Col7, Col8);
		ObservableList<TableColumn<Order, ?>> observableListOrder = orderView.getColumns();
		observableListOrder.get(0).setCellValueFactory(new PropertyValueFactory<>("idorder"));
		observableListOrder.get(1).setCellValueFactory(new PropertyValueFactory<>("idmaterial"));
		observableListOrder.get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
		observableListOrder.get(3).setCellValueFactory(new PropertyValueFactory<>("idStore"));
		observableListOrder.get(4).setCellValueFactory(new PropertyValueFactory<>("supplier"));
		observableListOrder.get(5).setCellValueFactory(new PropertyValueFactory<>("tansporter"));
		observableListOrder.get(6).setCellValueFactory(new PropertyValueFactory<>("planAmount"));
		observableListOrder.get(7).setCellValueFactory(new PropertyValueFactory<>("storeAmount"));
		observableListOrder.get(8).setCellValueFactory(new PropertyValueFactory<>("responsiblePerson"));
		orderView.setItems(orders);
		
		
		material.setContent(materialView);
		order.setContent(orderView);
		getChildren().addAll(titleMaterial, material, titleOrder, order);
		setAlignment(Pos.CENTER_LEFT);
	}
}

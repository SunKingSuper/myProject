package app.ui;

import app.control.MaterialControl;
import app.control.OrderControl;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OrderAddPane extends VBox {
	Label idorderL = new Label("订单编号");
	NumberField idorder = new NumberField();
	Label idmaterialL = new Label("原材料编号");
	NumberField idmaterial = new NumberField();
	Label dateL = new Label("订单到期日期");
	DatePicker date = new DatePicker();
	Label idStoreL = new Label("仓库编号");
	NumberField idStore = new NumberField();
	Label supplierL = new Label("供应商");
	TextField supplier = new TextField();
	Label tansporterL = new Label("运输商");
	TextField tansporter = new TextField();
	Label planAmountL = new Label("订单数量");
	NumberField planAmount = new NumberField();
	Label storeAmountL = new Label("入库数量");
	NumberField storeAmount = new NumberField();
	Label responsiblePersonL = new Label("负责人");
	TextField responsiblePerson = new TextField();
	public OrderAddPane(MenuPage parent) {
		super();
		GridPane form = new GridPane();
		
		form.add(idorderL, 0, 0);
		form.add(idorder, 1, 0);
		form.add(idmaterialL, 0, 1);
		form.add(idmaterial, 1, 1);
		form.add(dateL, 0, 2);
		form.add(date, 1, 2);
		form.add(idStoreL, 0, 3);
		form.add(idStore, 1, 3);
		form.add(supplierL, 0, 5);
		form.add(supplier, 1, 5);
		form.add(tansporterL, 0, 6);
		form.add(tansporter, 1, 6);
		form.add(planAmountL, 0, 7);
		form.add(planAmount, 1, 7);
		form.add(storeAmountL, 0, 8);
		form.add(storeAmount, 1, 8);
		form.add(responsiblePersonL, 0, 9);
		form.add(responsiblePerson, 1, 9);

		Button add = new Button("添加订单");
		add.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				boolean status = OrderControl.addOrder(idorder.getLong(), idmaterial.getLong(), date.getValue(),
						idStore.getLong(), supplierL.getText(), tansporter.getText(), planAmount.getValue(),
						storeAmount.getValue(), responsiblePerson.getText());

				if (status) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("订单登记成功");
					alert.setHeaderText("成功");
					alert.showAndWait();

					AddPane newAddPane = new AddPane(parent);
					parent.change(newAddPane);
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("订单登记失败");
					alert.setContentText("请查看是否格式错误，如否，请联系开发者");
					alert.showAndWait();
				}
			}
		});

		getChildren().addAll(form, add);
		setAlignment(Pos.CENTER_LEFT);
	}
}

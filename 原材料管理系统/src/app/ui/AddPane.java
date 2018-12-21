package app.ui;

import app.control.MaterialControl;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class AddPane extends VBox {
	Label idmaterialL = new Label("原材料编号");
	NumberField idmaterial = new NumberField();
	Label nameL = new Label("原材料名称");
	TextField name = new TextField();
	Label standardL = new Label("规格");
	TextField standard = new TextField();
	Label storeAmountL = new Label("目前存量(入库后)");
	NumberField storeAmount = new NumberField();
	Label unitL = new Label("单位");
	TextField unit = new TextField();
	public AddPane(MenuPage parent) {
		super();
		GridPane form = new GridPane();
		
		form.add(idmaterialL, 0, 0);
		form.add(idmaterial, 1, 0);
		form.add(nameL, 0, 1);
		form.add(name, 1, 1);
		form.add(standardL, 0, 2);
		form.add(standard, 1, 2);
		form.add(storeAmountL, 0, 3);
		form.add(storeAmount, 1, 3);
		form.add(unitL, 0, 4);
		form.add(unit, 1, 4);
		
		
		Button add = new Button("原材料入库登记");
		add.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				boolean status = MaterialControl.addMaterial(idmaterial.getLong(), name.getText()
						, standard.getText(), storeAmount.getValue(), unit.getText());
				
				if (status) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("登记成功");
					alert.setHeaderText("原材料登记更新成功");
					alert.showAndWait();
					
					AddPane newAddPane = new AddPane(parent);
					parent.change(newAddPane);
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("添加原材料失败");
					alert.setContentText("请查看是否格式错误，如否，请联系开发者");
					alert.showAndWait();
				}
			}
		});
		
		getChildren().addAll(form, add);
		setAlignment(Pos.CENTER_LEFT);
	}

}

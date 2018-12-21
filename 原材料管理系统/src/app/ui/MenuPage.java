package app.ui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuPage extends Stage {
	private BorderPane root;
	public MenuPage() {
		super();
		root = new BorderPane();
		VBox subroot = new VBox();
		
		Label title = new Label("菜单");
		title.setFont(new Font(36));
		Button add = new Button("原材料入库登记");
		Button orderAdd = new Button("订单登记");
		Button show = new Button("查看总体情况");
		Button exit = new Button("退出系统");
		add.setPrefWidth(200);
		orderAdd.setPrefWidth(200);
		show.setPrefWidth(200);
		exit.setPrefWidth(200);
		
		MenuPage that = this;
		add.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				change(new AddPane(that));
			}
		});
		orderAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				change(new OrderAddPane(that));
			}
		});
		show.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				change(new ShowPane(that));
			}
		});
		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Platform.exit();
			}
		});
		
		subroot.getChildren().addAll(add, orderAdd, show, exit);
		subroot.setAlignment(Pos.CENTER);
		subroot.setSpacing(5);
		subroot.setPadding(new Insets(10));
		subroot.setPrefWidth(200);
		subroot.setStyle(" -fx-background-image: url(/app/resource/background.jpeg)");
		
		
		root.setLeft(subroot);
		root.setStyle("-fx-background-image: url(/app/resource/timg.jpeg)");
		root.setPadding(new Insets(10));
		Scene scene = new Scene(root);
		setScene(scene);
		setTitle("原材料管理系统");
		setWidth(700);
		setHeight(400);
		show();
	}
	
	public void change(VBox node) {
		node.setPadding(new Insets(10));
		node.setPrefWidth(500);
		node.setStyle("-fx-background-image: url(/app/resource/timg.jpeg)");
		root.setCenter(node);
	}
}

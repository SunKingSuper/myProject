package application;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class Main extends Application {
	public Stage mainStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			mainStage = primaryStage;

			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			FlowPane top = new Choice(this);
			ScrollPane center = new EditArea(this);
			root.setTop(top);
			root.setCenter(center);
			scene.setRoot(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("JsonEditor");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void display(File selectedFile) {

	}
}

package model.test;

import java.util.ArrayList;
import java.util.List;

import Log.TheLog;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ComboBoxTest extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ComboBox<String> aBox  = new ComboBox<String>();
		aBox.getItems().addAll("1","2","3");
		aBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<String>() {
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setText(null);
						} else {
							setText(item+"!!!");
						}
					}
				};
			}
		});
		
		
		
		HBox root = new HBox();
		root.getChildren().add(aBox);
		
		List<String> aList = new ArrayList<String>();
		aList.add("a");
		aList.add("b");
		aList.add("c");
		aBox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println(newValue);
				if(newValue == null) {
					System.out.println("null");
				}
				root.getChildren().add(new BookStringItem(root, aList));
				aBox.setValue(null);
			}
		});
		
		aBox.setValue(null);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

class BookStringItem extends VBox {
	Label label = new Label();
	ComboBox<String> StringChoice = new ComboBox<String>();
	Button delete = new Button("delete");

	public BookStringItem(HBox parent, List<String> StringType) {
		label.setFont(new Font(36));
		System.out.println(StringType);

		StringChoice.getItems().addAll(StringType);
		StringChoice.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<String>() {
					{
						setContentDisplay(ContentDisplay.TEXT_ONLY);
					}

					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setText(null);
						} else {
							setText(item);
						}
					}
				};
			}
		});
		StringChoice.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue == null) {
					TheLog.info("null");
				}
				label.setText(newValue);
			}
		});

		StringChoice.setValue(StringChoice.getItems().get(0));

		BookStringItem self = this;
		delete.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				parent.getChildren().remove(self);
			}
		});

		StringChoice.setValue(StringChoice.getItems().get(0));

		getChildren().addAll(label, StringChoice, delete);
		setSpacing(5);
	}

	public String getData() {
		return StringChoice.getValue();
	}
}
package application;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Choice extends FlowPane {
	Button open = new Button("打开");
	Button save = new Button("保存");
	Main parent;
	public Choice(Main parent) {
		super();
		this.parent = parent;
		open.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				openFile();
			}
		});
		save.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				saveFile();
			}
		});
		getChildren().addAll(open, save);
	}
	public void openFile() {
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open Resource File");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Json Files", "*.json"));
		 File selectedFile = fileChooser.showOpenDialog(parent.mainStage);
		 if (selectedFile != null) {
		    parent.display(selectedFile);
		 }
	}
	public void saveFile() {
		
	}
}

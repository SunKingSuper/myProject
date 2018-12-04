package application.toolkit;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class RegisterFactory {
	private static int index = 0;
	public RegisterGui create(FlowPane parent) {
		return new RegisterGui(index++, parent);
	}
	
}

class RegisterGui extends GridPane {
	private int index;
	private Node parent;
	public RegisterGui(int id, FlowPane parent) {
		this.parent = parent;
		this.index = index;
		Label name = new Label("入住人" + id);
		Label registerName = new Label("入住人姓名");
		TextField registerNameT = new TextField();
		Label idCard = new Label("身份证号码");
		TextField idCardT = new TextField();
		
		Button minus = new Button();
		ImageView imageView = new ImageView(new Image("Resource/minus.png"));
		imageView.resize(50, 50);
		minus.setGraphic(imageView);
		
		minus.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				parent.getChildren().remove(index);
			}
		});
		
		add(name, 0, 0);
		add(registerName, 1, 0);
		add(registerNameT, 1, 1);
		add(idCard, 2, 0);
		add(idCardT, 2, 1);
		add(minus, 3, 2);
	}
}
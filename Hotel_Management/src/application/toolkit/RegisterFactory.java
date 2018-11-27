package application.toolkit;


import com.sun.xml.internal.bind.v2.model.core.ID;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class RegisterFactory {
	private static int id = 0;
	public RegisterGui create(FlowPane parent) {
		id++;
		return new RegisterGui(id, parent);
	}
	
}

class RegisterGui extends GridPane {
	private int id;
	private Node parent;
	public RegisterGui(int id, FlowPane parent) {
		this.parent = parent;
		this.id = id;
		Label name = new Label("入住人" + id);
		Label registerName = new Label("入住人姓名");
		TextField registerNameT = new TextField();
		Label idCard = new Label("身份证号码");
		TextField idCardT = new TextField();
		
		Button minus = new Button();
		1/0; //为minus按钮加上图标
		
		minus.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				parent.getChildren().remove(id);
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
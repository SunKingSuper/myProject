package application;

import java.util.Iterator;
import java.util.List;
import application.toolkit.CancelButton;
import application.toolkit.NumberField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.layout.GridPane;
import model.domain.Order;

public class ShowStage extends MyStage {
	TableView tableView = new TableView();

	public ShowStage(App platform) {
		ui(platform);
	}

	@Override
	protected void init() {
		Label query = new Label(Constant.QueryShow);
		TextField queryShow = new NumberField();
		ScrollPane orderShow = new ScrollPane();
		orderShow.setContent(tableView);
		RadioButton showAll = new RadioButton(Constant.OrderShow);
		Button cancel = new CancelButton(this);
		
		queryShow.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				queryHandle(newValue);
			}
		});

		showAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (queryShow.getText().length() > 0) {
					if (showAll.isSelected()) {
						showAllHandle();
					} else {
						showNowHandle();
					}
				}
			}
		});

		GridPane mainroot = new GridPane();
		mainroot.add(query, 0, 0);
		mainroot.add(queryShow, 1, 0);
		mainroot.add(orderShow, 1, 1);
		mainroot.add(showAll, 0, 2);
		mainroot.add(cancel, 1, 2);
		mainroot.setPadding(new Insets(10));
		mainroot.setHgap(10);
		mainroot.setVgap(10);
		root = mainroot;
	}

	private void showAllHandle() {

	}

	private void showNowHandle() {

	}
	private void queryHandle(String keyword) {
		
	}
	private void refresh(List<Order>orders) {
		Iterator<Order> iterator = orders.iterator();
		while(iterator.hasNext()) {
			TableColumn<S, T>
		}
	}
}

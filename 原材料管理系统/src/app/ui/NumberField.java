package app.ui;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public class NumberField extends TextField {

	public NumberField() {
		super();

		setTextFormatter(new TextFormatter<String>(new UnaryOperator<Change>() {

			@Override
			public Change apply(Change t) {
				// System.out.println(t.getText().getBytes());
				if (t.getText().matches("[0-9]*") || t.getText().equals(".")) {
					return t;
				}
				return null;
			}
		}));
	}

	public double getValue() {
		return Double.parseDouble(getText());
	}

	public long getLong() {
		return Long.parseLong(getText());
	}
}

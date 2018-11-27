package application.toolkit;

import java.util.function.UnaryOperator;

import application.Constant;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.text.Font;

public class NumberFiled extends TextField {
	public NumberFiled(){
		super();
		Tooltip tooltip = new Tooltip(Constant.LoginTip);
		tooltip.setFont(new Font(16));
		setTooltip(tooltip);
		setText(Constant.LoginTip);
		setTextFormatter(new TextFormatter<String>(new UnaryOperator<Change>() {

			@Override
			public Change apply(Change t) {
				// System.out.println(t.getText().getBytes());
				if (t.getText().matches("[0-9]*")) {
					return t;
				}
				return null;
			}
		}));
	}
}

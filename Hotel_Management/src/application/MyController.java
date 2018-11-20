package application;

import java.util.Date;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class MyController implements Initializable{
	private  Button myButton;
	private TextField myTextField;
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void sayHello(ActionEvent event) {
		System.out.println("Clicked");
		Date now =new Date();
		DateFormat dFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		String dateTimeString = dFormat.format(now);
		myTextField.setText(dateTimeString);
	}
}

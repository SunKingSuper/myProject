package model.test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import application.Constant;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Test {
	static HashMap<String, Object>a = new HashMap<>();
	
	public static void main(String[] args) {
		List<Number> b = new ArrayList<Number>();
		b.add(new Number(1));
		b.add(new Number(1));
		b.add(b.size() - 1, new Number(3));
		System.out.println(b);
		a.put("1", b); 
		func();
		System.out.println((List<Number>) a.get("1"));
	}
	
	public static void func() {
		List<Number> b = (List<Number>) a.get("1");
		Iterator<Number> iterator = b.iterator();
		while (iterator.hasNext()) {
			Number d = (Number) iterator.next();
			d.a = 5;
		}
		
	}
}

class Number {
	public Number(int b) {
		a = b;
	}
	public int a = 1;
	@Override
	public String toString() {
		return String.valueOf(a);
	}
}
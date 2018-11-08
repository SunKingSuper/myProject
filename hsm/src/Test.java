import java.util.ArrayList;
import java.util.Date;

import com.sun.swing.internal.plaf.basic.resources.basic;

import java.io.Serializable;

public class Test {
	public static void UI() {
		String msg = "Hello";
		ArrayList<String> msg2 = new ArrayList<String>(2);
		msg2.add("Hello");
		msg2.add("World");
		UI ui = new UI();
		ui.titleShow(msg);
		ui.unorderedListShow(msg2);
		ui.orderedListShow(msg2);
		ui.errorMsg(msg);
		ui.print();
	}
	public  static void IOtest() {
		System.out.println("文件序列化读写测试");
		A aclass = new A();
		IO.writeData(aclass, "test.db");
		A bclass = (A) IO.readData("test.db");
		bclass.p();
	}
	public static void a() {
		Database database = (Database) IO.readData("database.db");
		b(database);
	}
	public static void b(Database database) {
		while(true) {
			if(IO.getInt() == -1) {
				break;
			}
		}
	}
	public static void main(String[] args) {
		a();
	}
}

class A implements Serializable{
	private int a = 1;
	public void p() {
		System.out.println(a);
	}
}
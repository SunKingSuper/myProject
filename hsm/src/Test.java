import java.util.ArrayList;
import java.util.Date;

import com.sun.jndi.toolkit.ctx.StringHeadTail;
import com.sun.org.apache.bcel.internal.generic.NEW;
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
	public static void main(String[] args) {
		String a = "包子---1";
		String[] bStrings = a.split("---");
		System.out.println(bStrings[0]);
		
	}
}

class A implements Serializable{
	private int a = 1;
	public void p() {
		System.out.println(a);
	}
}
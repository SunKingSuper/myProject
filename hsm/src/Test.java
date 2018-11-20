import java.util.ArrayList;
import java.util.Date;

import com.sun.jndi.toolkit.ctx.StringHeadTail;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.swing.internal.plaf.basic.resources.basic;

import jdk.internal.util.xml.impl.Input;

import java.io.Serializable;

public class Test {
	public static void main(String[] args) {	
		A a = new A();
		System.out.println(a.a);
	}
}

interface P {
	void seta(); 
}

class A implements Serializable{
	private int a = 1;
	public void p() {
		System.out.println(a);
	}
}
package model.test;


public class Test {
	public static void main(String[] args) {
		B b = new B();
		b.b();
		System.out.println(b.a);
	}
}

class A {
	final public int a = 1;
	final public void b() {
		System.out.println("ok");
	}
}

class B extends A {
	final public void b(int a) {
		System.out.println("123");
	}
}
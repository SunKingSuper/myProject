import java.io.*;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @author 一个有趣的人
 * @author 3410322436@qq.com
 */
public class IO {
	private static Scanner scan = new Scanner(System.in);
	public static String getInput() {
		return scan.next();
	}
	public static void log(String msg) {
		System.out.println(msg);
	}
	public static float getNum() {
		float a;
		try {
			a = scan.nextFloat();
		} catch (Exception e) {
			UI.errorMsg("只支持数字,请重新输入");
			scan = new Scanner(System.in);
			a = getNum();							//这里可能会出现无限循环的bug
		}
		return a;
	}
	public static int getInt() {
		int a = 0;
		try {
			a = scan.nextInt();
		} catch (Exception e) {
			UI.errorMsg("只支持整数,请重新输入");
			scan = new Scanner(System.in);
			a = getInt();							//这里可能会出现无限循环的bug
		}
		return a;
	}
	public static void writeData(Object data, String fileDir) {
		try {
			FileOutputStream fileOutput = new FileOutputStream(fileDir);
			ObjectOutputStream out = new ObjectOutputStream(fileOutput);
			out.writeObject(data);
			out.close();
			fileOutput.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static Object readData(String fileDir) {
		Object data = null;
		try {
			FileInputStream fileInput = new FileInputStream(fileDir);
			ObjectInputStream in = new ObjectInputStream(fileInput);
			data = (Object) in.readObject();
			in.close();
			fileInput.close();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}
			return data;
	}
	public static boolean isFileExists(String fileDir) {
		File file = new File(fileDir);
		if (!file.exists()) {
			return false;
		}
		return true;
	}
}

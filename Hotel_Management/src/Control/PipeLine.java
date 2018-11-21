package Control;
/**
 * @author SKS
 * 
 * PipeLine 是之前数据包的延伸，用一个公共静态域达到数据交换的目的
 * 但是很不安全，数据无法保证是谁的
 */

import java.sql.Date;

import Log.TheLog;

public class PipeLine {
	private static String aString;
	private static String[] arrayString;
	private static Date date;
	private static int aInt;

	private static int isExist = 0;				//PipeLine每次有数据输入的时候会将isExist为true
	
	public static void put(String name, Object data) {
		if (isExist <= 0) {
			switch (name) {
			case "aString":
				aString = (String) data;
				break;
			case "arrayString":
				arrayString = (String[]) data;
				break;
			case "date":
				date = (Date) data;
				break;
			case "aInt":
				aInt = (int) data;
				break;
			default:
				TheLog.warn("PipeLine error:\n String name is not existed(\"" + name + "\")");
				break;
			}
			isExist++;
		}
	}
	public static Object get(String name) {
		Object data = null;
		if (isExist > 0) {
			try {
				switch (name) {
				case "aString":
					data = aString;
					break;
				case "arrayString":
					data = arrayString;
					break;
				case "date":
					data = date;
					break;
				case "aInt":
					data = aInt;
					break;
				default:
					TheLog.warn("PipeLine error:\n String name is not existed(\"" + register + "\")");
					break;
				}
				isExist--;
			} catch (NullPointerException ne) {
				TheLog.warn(ne.getLocalizedMessage());
				//没有此数据
			} catch (Exception e) {
				TheLog.warn(e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
		if(isExist == 0) {
			clear();											//这里我等到数据传输完了，则马上初始化所有静态成员，保证数据完整
		}
		return data;
	}
	private static void clear() {
		aString = null;
		arrayString = null;
		date = null;
		aInt = null;
	}
}
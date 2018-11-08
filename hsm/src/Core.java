import java.util.*;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author 一个有趣的人
 * @author 3410322436@qq.com
 * 我就在Core这个类谈谈我对整个系统的看法，这里Core是整个系统的开始
 * 我是以MVC模式设计这个系统的，最基本的由database提供数据，core组织逻辑，而ui负责页面的展示
 */

/**
 * 
 * @author 一个有趣的人
 * 这个Data类是专门为了UI和Core进行信息传递使用的
 * 之所以想用这个是因为写打扫增加权限角色的时候，一个是String，一个是ArrayList<Integer>
 * 就不好像之前打包成String[]那样传输，只好先弄一个Data类封装
 * 以后优化再全部用上吧，目前先专注把这个系统原型完成
 */
class Data {
	int intNum;
	float floatNum;
	String string;
	ArrayList<Integer> intArray;
	ArrayList<Float> floatArray;
	ArrayList<String> stringArray;
	Set<Integer> intSet;
	Set<String> stringSet;
}

public class Core {
	public static void main(String[] args) {
		Process app = new Process();
		UI ui = new UI();
		IO io = new IO();//初始化以免发生某些错误
		app.start();
	}
}

class Process {
	static String role = "None";
	static ArrayList<Integer> rolePermission = new ArrayList<Integer>(0);
	static int counter = 0;
	static String fileDir = "database.db"; // 数据库文件
	static int tableAmount = 30;
	static ArrayList<Integer> busyTable = new ArrayList<Integer>();
	Database database;
	public void exit() {
		IO.writeData(database, fileDir);
		System.exit(0);
	}
	public void start() {
		UI.welcomePage();
		if (IO.isFileExists(fileDir)) {
			database = (Database) IO.readData(fileDir);
			login();
		} else {
			DBAregister();
		}
		on();
	}
	public void login() {
		// userName = userInfo[0], password = userInfo[1]
		String[] userInfo = UI.loginPage();
		role = database.userVerify(userInfo);
		if (role.equals("LoginError")) {
			counter++;
			if(counter < 3) {
				UI.errorMsg("员工号或密码错误，请重新登陆");
				login();
			} else {
				UI.errorMsg("登陆失败已达3次，退出程序");
				exit();
			}
		}
		UI.welcomeUserPage(userInfo[0]);
		on();
	}
	public void DBAregister() {
		database = new Database();
		UI.dbaRegisterPage();
		register();
		role = "DBA";
	}
	public void register() {
		Set<String> roleInfo = database.getRole();
		String[] userInfo = UI.registerPage(roleInfo);
		// 写到这里我在犹豫要不要用字典控制权限 <角色名，功能合集>
		int userID = Integer.parseInt(userInfo[0]);
		String name = userInfo[1], password = userInfo[2], userRole = userInfo[3];
		database.addUsers(userID, name, password, userRole);
		UI.doneMessage(String.format("已添加用户：%d-->name:%s passwd:%s role:%s", userID, name, password, userRole));
	}
	public void occupyTable(int tableID) {
		busyTable.add(tableID);
	}
	public void freeTable(int tableID) {
		Integer ID = new Integer(tableID);
		busyTable.remove(ID);
	}
	/**
	 * 我这里感觉自己写的严重碎片化了
	 * 其实感觉可以将其分层管理，只是懒得麻烦，希望什么时候把这个地方拆出去
	 * 比如变成UserMangement对吧？就不用写那么多，我这么写是想着权限控制可以变得十分细粒
	 * 我想了想，应该用中间件的形式进行多层传递，希望以后有机会再做吧
	 */
	public void on() {
		while(true) {
			ArrayList<Integer> rolePermission = database.getPermission(role); //在角色表没有添加权限时没必要刷新
			int choice =  UI.mainMenuPage(rolePermission);
			switch (choice) {
			case 0:
				exit();break;
			//DBA管理员
			case 1:
				addUser();break;
			case 2:
				addRole();break;
			case 3:
				changeRole();break;
			case 4:
				makeDayReport();break;
			case 5:
				getDayReport();break;
			//前台
			case 6:
				searchTable();break;
			case 7:
				makeOrder();break;
			case 8:
				makePayment();break;
			//后厨
			case 9:
				checkTodoList();break;
			case 10:
				finishDish();break; //这里设想的情景是后厨做完后叫前台，但是实在懒得写了。
			case 11:
				addDish();break;
			}
			UI.doneMessage("...操作完成");
		}
	}
	public void addUser() {
		register();
	}
	public void addRole() {
		Set<String> roleInfo = database.getRole();
		Data data = UI.addRolePage(roleInfo);
		changingRole(data);
	}
	public void changingRole(Data data) {
		String roleName = data.string;
		ArrayList<Integer> permission = new ArrayList<Integer>(); 
		permission.addAll(data.intSet);
		database.addRole(roleName, permission);
		UI.doneMessage(String.format("已修改权限 %s-->permision:%s", roleName, permission.toString()));
	}
	public void changeRole() {
		Data data = new Data();
		data.stringArray = database.getRoleList();
		data.stringSet = database.getRole();
		data = UI.changeRolePage(data);
		changingRole(data);
	}
	public void makeDayReport() {
		Data data = new Data();
		data.string = database.makeDayReport();
		UI.dayReportPage(data);
	}
	public void getDayReport() {
		Data data = new Data();
		data.stringArray = database.getDaysReport();
		UI.daysReportPage(data);
	}
	//我决定把空桌交给Core维护
	public void searchTable() {
		UI.tableShowPage(tableAmount, busyTable);
	}
	//还有很多没写呢，比如说我怎么利用set来拒绝不正确的输入呢？5
	public void makeOrder() {
		Data data = new Data();
		data.stringArray = new ArrayList<String>();
		data.stringArray = database.getDishMenu();
		data.intNum = tableAmount;
		data.intArray = busyTable;
		data = UI.makeOrderPage(data);
		int tableID = data.intNum;
		ArrayList<Integer> todoDish = data.intArray;
		occupyTable(tableID);
		String msg  = database.createOrder(tableID, todoDish);
		UI.doneMessage("订单已生成...\n" + msg);
	}
	/**
	 * 从这里我开始意识到，从数据库返回一个确认的信息是很重要的。
	 * 因为一保证了数据的一致性，二是调试的时候更容易明确错误
	 * 当然不一定指函数上的返回值，也可以弄一个log日志
	 */
	public void makePayment() {
		int orderID = UI.paymentPage1();
		Order order = database.getOrder(orderID); //这里开始代码就变得很混乱
		Data data = new Data();
		data.string = order.toString();
		data.floatNum = order.amount;
		UI.paymentPage2(data);
		database.orderDone(orderID);
		freeTable(order.tableID);
		UI.doneMessage("结帐完成");
	}
	public void checkTodoList() {
		Data data = new Data();
		ArrayList<String> msg = database.checkTodoList();
		data.stringArray = msg;
		UI.checktTodoPage(data);
	}
	public void finishDish() {
		Data data = new Data();
		data.stringArray = new ArrayList<String>();
		data.stringArray = database.getDishMenu();
		data = UI.finishPage(data);
		database.doneDish(data.intNum, data.stringArray);
	}
	public void addDish() {
		Data data = new Data();
		data.stringArray = new ArrayList<String>();
		data.stringArray = database.getDishMenu();
		data = UI.addDishPage(data);
		String name = data.string;
		float price = data.floatNum ;
		database.addDish(name, price);
	}
}
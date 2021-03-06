import java.util.*;

/**
 * @author 一个有趣的人
 * @author 3410322436@qq.com
 */
public class UI {
	private static String buffer = "";
	private static int WIDTH = 10;
	private static String splitToken = "-".repeat(WIDTH) + "\n";
	private static HashMap<Integer, String>choice = new HashMap<Integer, String>();
	/**
	 * 这里初始化系统目前所有可用功能
	 * 前面为功能代号，后面为选项名称
	 */
	public UI() {
		choice.put(0, "退出系统");
		choice.put(1, "添加用户");
		choice.put(2, "添加角色");
		choice.put(3, "修改角色权限");
		choice.put(4, "生成每日结帐报告并储存");
		choice.put(5, "查看每日结帐报告");
		choice.put(6, "查询空桌");
		choice.put(7, "开台点单");
		choice.put(8, "结帐");
		choice.put(9, "检查要做的菜");
		choice.put(10, "完成某个菜");
		choice.put(11, "增加菜式");
	}
	/**
	 * UI的功能区
	 */
	public static void print() {
		System.out.println(buffer);
		buffer = "";
	}
	public static String getBuffer() {
		String string = buffer;
		buffer = "";
		return string;
	}
	public static void errorMsg(String error) {
		String msg = "! " + error;
		System.out.println(msg);
	}
	public static void orderedListShow(int start, ArrayList<String> msg) {
		int i = start;
		addSplitToken();
		for(int j = 0, size = msg.size(); j < size ; j++) {
			if (msg.get(j).equals("退出系统")) {
				addOrderedItem(0, msg.get(j));
			} else {
				addOrderedItem(i++, msg.get(j));
			}
		}
		addSplitToken();
		print();
	}
	public static void unorderedListShow(ArrayList<String> msg) {
		addSplitToken();
		for(int j = 0, size = msg.size(); j < size; j++) {
			addUnorderedItem(msg.get(j));
		}
		addSplitToken();
		print();
	}
	public static void titleShow(String msg) {
		addSpace();
		addSplitToken();
		buffer += "+++++ " + msg + " +++++";
		print();
	}
	public static void addSpace() {
		buffer += "\n";
	}
	public static void addSplitToken() {
		buffer += splitToken;
	}
	public static void addUnorderedItem(String item) {
		buffer += String.format("| * %s%n", item);
	}
	public static void addOrderedItem(int order, String item) {
		buffer += String.format("| %2d. %s%n", order, item);
	}
	public static void text(String msg) {
		buffer += String.format("%s", msg);
		print();
	}
	public static void addQuestion(String msg) {
		buffer += String.format("~ %s?", msg);
		print();
	}
	public static void inputTip(String msg) {
		buffer += String.format("%s: ", msg);
		print();
	}
	/**
	 * 下面是pageShow
	 */
	public static void doneMessage(String msg) {
		text(msg);
	}
	public static void welcomePage() {
		titleShow("酒店管理系统");
		text("欢迎使用本系统。本系统由 * 一个有趣的人 *完成！");
		addSplitToken();
		print();
	}
	public static void welcomeUserPage(String name) {
		text("员工" + name + "，您已登陆成功");
	}
	public static String[] loginPage() {
		String[] userInfo = new String[2];
		inputTip("员工号");
		userInfo[0] = IO.getInput();
		inputTip("密码");
		userInfo[1] = IO.getInput();
		addSpace();
		return userInfo;
	}
	public static void dbaRegisterPage() {
		errorMsg("系统检测不到数据库文件");
		errorMsg("开始重新安装");
		titleShow("注册管理员");
	}
	private static void showRole(Set<String> roleInfo) {
		ArrayList<String> menu = new ArrayList<String>();
		menu.addAll(roleInfo);
		orderedListShow(0, menu);
	}
	public static String[] registerPage(Set<String> roleInfo) {
		text("添加用户");
		inputTip("员工号");
		int id = IO.getInt();
		inputTip("员工名");
		String name = IO.getInput();
		inputTip("密码");
		String passwd = IO.getInput();
		showRole(roleInfo);
		inputTip("设置的权限角色为");
		ArrayList<String> array = new ArrayList<String>();
		array.addAll(roleInfo);
		String role  = array.get( IO.getInt() % array.size());
		String[] userInfo = {String.valueOf(id), name, passwd, role};
		return userInfo;
	}
	public static int mainMenuPage(ArrayList<Integer> rolePermission) {
		ArrayList<String> menu = new ArrayList<String>();
		for(int i : rolePermission) {
			if(i != 0) {menu.add(choice.get(i));}
		}
		menu.add(choice.get(0));
		titleShow("主菜单");
		orderedListShow(1, menu);
		inputTip("请选择其中一项");
		return rolePermission.get( IO.getInt() % rolePermission.size());
	}
	private static void showChoice() {
		ArrayList<String> msg = new ArrayList<String>();
		msg.addAll(choice.values());
		orderedListShow(1, msg);
	}
	public static Data addRolePage(Set<String> roleInfo) {
		titleShow("新建用户权限");
		showRole(roleInfo);
		return changingRolePage();
	}
	public static Data changingRolePage() {
		inputTip("角色名");
		String roleName = IO.getInput();
		text("添加权限(默认添加退出系统的权限)");
		showChoice();
		Set<Integer> permission = new HashSet<Integer>();
		int p = 0;
		while(p != -1) {
			permission.add(p);
			inputTip("添加权限(输入-1结束)");
			p =  IO.getInt();
		}
		Data data = new Data();
		data.string = roleName;
		data.intSet = permission;
		return data;
	}
	//很烦到处都要写一个转换的狮子，把set转成arraylist，希望能统一数据格式
	public static Data changeRolePage(Data roleList) {
		titleShow("更改角色权限");
		text("目前已有权限角色");
		unorderedListShow(roleList.stringArray);
		text("修改权限角色");
		return changingRolePage();
	}
	public static void dayReportPage(Data data) {
		titleShow("今天财报");
		text(data.string);
	}
	public static void daysReportPage(Data data) {
		titleShow("每日总帐");
		unorderedListShow(data.stringArray);
	}
	public static void tableShowPage(int tableAmount, ArrayList<Integer> busyTable) {
		titleShow("桌位情况");
		text("正用餐的桌号:");
		text(busyTable.toString());
		addSpace();
		text("可用桌号:");
		String string = "";
		for(int i = 1; i <= tableAmount; i++) {
			if(!busyTable.contains(i)) {
				string += " " + i + " ";
			}
		}
		text(string);
	}
	public static Data makeOrderPage(Data data) {
		int tableAmount = data.intNum;
		ArrayList<Integer>busyTable = data.intArray;
		titleShow("下单");
		inputTip("桌号");
		int tableID = IO.getInt() % tableAmount;
		while(busyTable.contains(tableID)) {
			errorMsg("该桌已被占用了,请重新输入");
			tableID = IO.getInt() % tableAmount;
		}
		ArrayList<String> dishMenu = data.stringArray;
		ArrayList<Integer> todoDishes = new ArrayList<Integer>();
		orderedListShow(0, dishMenu);
		int p;
		while(true) {
			inputTip("点菜(输入-1结束)");
			p =  IO.getInt();
			if (p == -1) break;
			if(dishMenu.get(p).contains("暂无现货供应")) {
				errorMsg("该菜品暂无现货供应");
				continue;
			}
			todoDishes.add(p);
		}
		data.intArray = todoDishes;
		data.intNum = tableID;
		return data;
	}
	public static int paymentPage1() {
		titleShow("智能收付款");
		inputTip("其订单号为：");
		return IO.getInt();
	}
	public static void paymentPage2(Data data) {
		text(data.string);
		text("客人需要支付金额: " + data.floatNum);
		inputTip("客人实际支付金额:");
		float money = IO.getNum();
		float repaid = money - data.floatNum;
		text("应找回金额: "+repaid);
	}
	public static void checktTodoPage(Data data) {
		titleShow("后厨上菜清单");
		unorderedListShow(data.stringArray);
	}
	/**
	 * 因为这里以及之前都出现好几次复制代码的过程
	 * 所以我这里的代码实际上是可以进一步组件化
	 * 然后毕竟这个finishPage页面和chekTodoPage一样的
	 * 我能不能让它们在同一个视图下工作呢？
	 */
	public static Data finishPage(Data data) {
		titleShow("完成菜品");
		inputTip("订单号");
		int orderID = IO.getInt();
		ArrayList<String> dishMenu = data.stringArray;
		orderedListShow(0, dishMenu);
		ArrayList<String> doneDish = new ArrayList<String>();
		int p;
		while(true) {
			inputTip("完成的菜品(输入-1结束)");
			p =  IO.getInt();
			if (p == -1) break;
			doneDish.add(dishMenu.get(p));
		}
		data.intNum = orderID;
		data.stringArray = doneDish;
		return data;
	}
	public static Data addDishPage(Data data) {
		titleShow("增加菜式");
		text("已有菜式");
		ArrayList<String> dishMenu = data.stringArray;
		unorderedListShow(dishMenu);
		inputTip("菜名");
		String name = IO.getInput();
		inputTip("价格");
		float price = IO.getNum();
		data.string = name;
		data.floatNum = price;
		return data;
	}
}


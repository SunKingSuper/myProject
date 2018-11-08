import java.io.Serializable;
import java.util.*;

/**
 * @author 一个有趣的人
 * @author 3410322436@qq.com
 */
public class Database implements Serializable {
	private HashMap<String,Dish> dishTable = new HashMap<String,Dish>();
	private HashMap<Integer,Order> orderTable = new HashMap<Integer,Order>();
	private HashMap<Date,Bill> dayBillTable = new HashMap<Date,Bill>();
	private HashMap<Integer,User> userTable = new HashMap<Integer,User>();
	// 这里我傻了，Permission应该是Set而不是List，下次优化把它改掉
	private HashMap<String, ArrayList<Integer>> roleList = new HashMap<String, ArrayList<Integer>>();
	private HashMap<Date, Report>daysReport = new HashMap<Date, Report>();
	private static Integer orderID = 1;
	static Date today = new Date();
	public Database() {
		roleList.put("None", new ArrayList<Integer>(0));
		ArrayList<Integer> permission = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,11));
		roleList.put("DBA", permission);
	}
	
	/**
	 * 这里都是Database自带方法，用户无法选择的f f
	 */
	public String userVerify(String[] userInfo) {
		Integer userID = Integer.parseInt(userInfo[0]);
		String passwd = userInfo[1];
		User user = userTable.get(userID);
		if (user != null && passwd.equals(user.password)) {
			return user.role;
		}
		return "LoginError"; //这里是唯一用到LoginError的地方，所以我把Flag那个类删了
	}
	public Set<String> getRole() {
		return roleList.keySet();
	}
	public ArrayList<Integer> getPermission(String role) {
		return roleList.get(role);
	}
	public String toString() {
		return String.format("数据库%ntableLength:%d%t%d%t%d%t%d", dishTable.size(), orderTable.size(), dayBillTable.size(), roleList.size());
	}
	/**
	 * 以下都是用户可选择的方法调用
	 */
	public void addUsers(int userID, String name, String password, String role) {
		userTable.put(userID, new User(name, password, role));
	}
	public void addRole(String roleName, ArrayList<Integer> permission) {
		roleList.put(roleName, permission);
	}
	public ArrayList<String> getRoleList() {
		ArrayList<String> strings = new ArrayList<String>();
		for(String key: roleList.keySet()) {
			strings.add(String.format("%s-->%s", key, roleList.get(key).toString()));
		}
		return strings;
	}
	public String createOrder(int tableID, ArrayList<Integer>todoDish) {
		Date orderTime = new Date();
		String string = "";
		float amount = 0;
		ArrayList<String> name = new  ArrayList<String>();
		ArrayList<Dish> dishList = new  ArrayList<Dish>();
		name.addAll(dishTable.keySet());
		string += "订单号" + orderID + " 桌号" + tableID;
		for(int i: todoDish) {
			String dishName = name.get(i);
			Dish dish = dishTable.get(dishName);
			amount += dish.price;
			dishList.add(dish);
			string += "\n" + dishName.toString();
		}
		string += "\n总金额：" + amount;
		orderTable.put(orderID++, new Order(tableID, orderTime, amount, dishList, todoDish));
		return string;
	}
	public Order getOrder(int orderID) {
		return orderTable.get(orderID);
	}
	//我很讨厌这里居然得在database做格式化输出，为三么不直接把整个表返回出来
	//是害怕被修改吗？
	public ArrayList<String> checkTodoList() {
		ArrayList<String> msg = new ArrayList<String>();
		for(int orderID : orderTable.keySet()) {
			Order order = orderTable.get(orderID);
			String string = "";
			string += "订单号: "+orderID+"  桌号: "+order.tableID+"\n";
			string += "待处理的菜品:\n";
			for(int i: order.leftDishLish) {
				string += order.dishList.get(i).toString() +"\n";
			}
			msg.add(string);
		}
		return msg;
	}
	public void orderDone(int orderID) {
		Order order = orderTable.remove(orderID);
		dayBillTable.put(today, new Bill(order.amount));
		daysReport.put(today, daysReport.getOrDefault(today,new Report()).add(order.amount));
	}
	public String makeDayReport() {
		Date date = new Date();
		String todayReport = ""; 
		for(Date day: dayBillTable.keySet()) {
			if (date.compareTo(day) == 0) {
				todayReport += dayBillTable.get(day).toString();
			}
		}
		if (todayReport.equals("")) { todayReport = "今日暂无订单";}
		return todayReport;
	}
	public ArrayList<String> getDaysReport() { 
		ArrayList<String> string = new ArrayList<String>();
		for(Date day: daysReport.keySet()) {
			string.add(String.format("%tT-->%s", day, daysReport.get(day)));
		}
		return string;
	}
	public void saveDayReport(float amount) {
		
		dayBillTable.put(today, new Bill(amount));
	}
	public void addDish(String name, float price) {
		dishTable.put(name, new Dish(price));
	}
	public ArrayList<String> getDishMenu() {
		ArrayList<String> menu = new ArrayList<String>();
		for (String name:dishTable.keySet()) {
			menu.add(String.format("%20s---%s", name, dishTable.get(name).toString()));
		}
		return menu;
	}
	public void doneDish(int orderID, ArrayList<String> donedish) {
		for(String done: donedish) {
			int index = orderTable.get(orderID).dishList.indexOf(done);
			index = orderTable.get(orderID).leftDishLish.indexOf(index);
			orderTable.get(orderID).leftDishLish.remove(index);
		}
	}
	public void noDish(String name) {
		dishTable.get(name).isAvailble = false;
	}
	public void yesDish(String name) {
		dishTable.get(name).isAvailble = true;
	}
	// 未完待续
}
/**
 * 下面是基本的数据项
 */
class Dish implements Serializable{
	float price;
	boolean isAvailble = false;
	public Dish(float price) {
		this.price = price;
	}
	public String toString() {
		String msg = String.format("%.2f元", price);
		if(!isAvailble) {
			msg += "暂无现货供应";
		}
		return msg;
	}
}

class Order implements Serializable {
	int tableID;
	Date orderTime;
	float amount;
	ArrayList<Dish> dishList = new ArrayList<Dish>();
	ArrayList<Integer> leftDishLish = new ArrayList<Integer>();
	public Order(int tableID, Date orderTime, Float amount, ArrayList<Dish> dishList, ArrayList<Integer> leftDishLish) {
		this.tableID = tableID;
		this.orderTime = orderTime;
		this.amount = amount;
		this.dishList = dishList;
		this.leftDishLish = leftDishLish;
	}
	public String toString() {
		return String.format("桌号：%d  总额：%d 下单时间：%tT%n——已点菜单%s%n——未完成菜单%s", tableID, orderTime, amount, dishList.toString(), leftDishLish.toString());
	}
}

class Bill implements Serializable {
	float amount;
	public Bill(float amount) {
		this.amount = amount;
	}
	public String toString() {
		return String.format("总额：%f", amount);
	}
}

class User implements Serializable {
	String name;
	String password;
	String role;
	public User(String name, String password, String role) {
		this.name = name;
		this.password = password;
		this.role = role;
	}
	public String toString() {
		return String.format("姓名：%s  权限角色：%s pd:%s", name, role, password);
	}
}

class Report implements Serializable {
	float amount = 0;
	public Report add(float num) {
		amount += num;
		return this;
	}
	public String toString() {
		return "总金额: " + amount;
	}
}
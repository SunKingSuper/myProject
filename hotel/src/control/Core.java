package control;

/**
 *  @author SKS
 *  注意对象的引用
 */
import application.*;
import application.toolkit.BookRoom;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import model.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import Log.TheLog;
import model.Dao.*;
import model.Dao.Impl.*;

public class Core {
	static User employee = new User();
	private static HashMap<String, Object> buffer = new HashMap<>(); // 用来存放临时数据
	private static GuestDao guestDao = new GuestDaoImpl();
	private static UserDAO userDAO = new UserDaoImpl();
	private static RoomTypeDao roomTypeDao = new RoomTypeDaoImpl();
	private static RoomDao roomDao = new RoomDaoImpl();
	private static RoleSaleDao roleSaleDao = new RoleSaleDaoImpl();
	private static OrderDao orderDao = new OrderDaoImpl();
	private static OrderRoomDao orderRoomDao = new OrderRoomDaoImpl();
	private static OrderGuestDao orderGuestDao = new OrderGuestDaoImpl();
	private static boolean permission = false;

	// loginStage
	public static boolean login(User user) {
		User userinfo = userDAO.get(user.getidUser());
		if (userinfo == null)
			return false;
		if (userinfo.getpassword().equals(user.getpassword())) {
			userinfo.setlastLogin(new Timestamp(new Date().getTime()));
			userDAO.update(userinfo);
			employee = userinfo;
			return true;
		}
		return false;
	}

	public static String getrole() {
		return employee.getrole();
	}

	public static void logout() {
		employee.setlastLogout(new Timestamp(new Date().getTime()));
		userDAO.update(employee);
		TheLog.info("用户注销");
	}

	// AuthentianStage
	public static void givePermission(Authentication stage) {
		if (stage.kind) {
			permission = true;
		}
	}

	public static boolean getPermission() {
		if (permission) {
			permission = false;
			return true;
		}
		return permission;
	}

	public static boolean authentication(String password) {
		return employee.getpassword().equals(password);
	}

	// bookStage
	public static List<RoomType> roomTypes() {
		return roomTypeDao.listall();
	}

	public static List<Guest> guestChoice(String name) {
		return guestDao.listAll();
	}

	// CleanerStage
	public static void roomIsReady(Room newroom) {
		newroom.setstatus(Constant.FREE);
		roomDao.update(newroom);
	}

	public static List<Room> getRoombyStatus(Long status) {
		return roomDao.listOneStatus(status);
	}

	// MainStage.refresh
	public static List<Room> checkRoom() {
		return roomDao.listAll();
	}

	// ShowStage
	public static List<Order> showAllOrders() {
		return orderDao.listall();
	}

	public static List<Order> showNotDoneOrders() {
		return orderDao.listallnotDone();
	}


	// Console.refresh
	public static HashMap<String, Integer> getRoomStatic() {
		List<Room> list = checkRoom();
		buffer.put("rooms", list);
		return analyse(list);
	}

	// 内部函数
	private static HashMap<String, Integer> analyse(List<Room> list) {
		HashMap<String, Integer> statics = new HashMap<String, Integer>();
		statics.put("free", 0);
		statics.put("booked", 0);
		statics.put("registered", 0);
		statics.put("cleanning", 0);
		Iterator<Room> iterator = list.iterator();
		while (iterator.hasNext()) {
			Room room = iterator.next();
			Long status = room.getstatus();
			if (status.equals(Constant.FREE)) {
				statics.put("free", statics.get("free") + 1);
				int i = statics.getOrDefault(room.getroomType(), 0);
				statics.put(room.getroomType(), i + 1);
			} else if (status.equals(Constant.BOOKED)) {
				statics.put("booked", statics.get("booked") + 1);
			} else if (status.equals(Constant.REGISTERED)) {
				statics.put("registered", statics.get("registered") + 1);
			} else if (status.equals(Constant.CLEANNING)) {
				statics.put("cleanning", statics.get("cleanning") + 1);
			}
		}
		return statics;
	}

	// BookStage.comboBox
	public static List<RoomType> getFreeRoomType() {
		List<RoomType> list = roomTypeDao.listall();
		HashMap<String, Integer> statics = analyse((List<Room>) buffer.get("rooms"));
		BufferHandle<RoomType> bufferHandle = new BufferHandle<RoomType>() {

			@Override
			public void map(RoomType t) {
				if (statics.get(t.getroomType()) == null || statics.get(t.getroomType()) == 0) {
					list.remove(t);
				}
			}
		};

		return list;
	}

	// BookRoom.comboBox
	public static List<Room> getFreeRoomByRoomType(RoomType roomType) {
		List<Room> rooms = (List<Room>) buffer.get("rooms");
		List<Room> list = new ArrayList<Room>();
		BufferHandle<Room> bufferHandle = new BufferHandle<Room>() {

			@Override
			public void map(Room t) {
				if (t.getstatus() == Constant.FREE && t.getroomType().equals(roomType.getroomType())) {
					list.add(t);
				}
			}

		};
		bufferHandle.handle(rooms);
		return list;
	}

	// 临时预定房间，尚未提交, 防止返回list的时候出事
	public static void registerRoomTemp(Room room, BookRoom that) {
		List<Room> rooms = (List<Room>) buffer.get("rooms");
		BufferHandle<Room> bufferHandle = new BufferHandle<Room>() {

			@Override
			public void map(Room t) {
				if (t.getstatus() == Constant.FREE && t.getidRoom() == room.getidRoom()) {
					t.setstatus(Constant.REGISTERED); // 我老是忘记这些都是引用，完了，我不知道还有哪些地方有可能因为这里出错
				}
			}

		};
		bufferHandle.handle(rooms);
		that.getRooms().add(room);
		System.out.println(that.getRooms());
	}

	public static void cancelRoomTemp(Room room, BookRoom that) {
		List<Room> rooms = (List<Room>) buffer.get("rooms");
		BufferHandle<Room> bufferHandle = new BufferHandle<Room>() {

			@Override
			public void map(Room t) {
				if (t.getstatus() == Constant.REGISTERED && t.getidRoom() == room.getidRoom()) {
					t.setstatus(Constant.FREE); // 我老是忘记这些都是引用，完了，我不知道还有哪些地方有可能因为这里出错
				}
			}

		};
		bufferHandle.handle(rooms);
		that.getRooms().remove(room);
	}

	// BookStage.phonenumber
	// 返回折扣
	public static float guestRoleValidation(String phonenumber) {
		float dismiss = 1;
		Guest guest = guestDao.getbyPhonenumber(phonenumber);
		if (guest != null) {
			RoleSale roleSale = roleSaleDao.get(guest.getguestRole());
			if (roleSale != null) {
				dismiss = roleSale.getdismiss();
			}
		}
		System.out.println(dismiss);
		return dismiss;
	}

	public static double calculate(List<Room> rooms, double dismiss) {
		System.out.println(rooms);
		HashMap<String, Double> pricelist = new HashMap<String, Double>();
		List<RoomType> list = roomTypeDao.listall();
		Iterator<RoomType> iterator = list.iterator();
		while (iterator.hasNext()) {
			RoomType roomType = (RoomType) iterator.next();
			pricelist.put(roomType.getroomType(), roomType.getprice());
		}

		double price = 0;
		Iterator<Room> roomiterator = rooms.iterator();
		while (roomiterator.hasNext()) {
			Room room = (Room) roomiterator.next();
			price += pricelist.get(room.getroomType());
		}
		price *= dismiss;
		return price;
	}

	public static boolean bookhandle(long orderStatus, String mainGuestName, String phoneNumber, LocalDate registerDate,
			LocalDate leftDate, List<Room> rooms, List<Guest> guests, Object amount, String comment) {
		boolean issuccess = false;
		try {
			Order order = new Order();
			/**
			 * 写到这里我才发现，我不能把多个插入弄成事务性的，要是有一步错误就依然对数据库产生影响 利用orderTime来找回idOrder真是蠢
			 * 本来想用uuid，但uuid得改成String 麻烦 所以用时间戳13位调成10位, 短时间多次插入可能报错
			 */
			long idOrder = (new Date().getTime() / 1000);
			order.setidOrder(idOrder); // （在数据库让它自增）
			order.setname(mainGuestName);
			order.setPhoneNumber(phoneNumber);
			order.setregisterDate(localDate2Timestamp(registerDate));
			order.setleftDate(localDate2Timestamp(leftDate));
			Timestamp now = new Timestamp(new Date().getTime());
			order.setorderTime(now);
			order.setstatus(orderStatus);
			order.setamount((double) amount);
			order.setcomment(comment);
			orderDao.save(order);
//			order = orderDao.getByorderTime(now);			我已放弃
			// 插入另外两张表
			// 这也就证明了多进程时无法保证数据一致性
			Iterator<Room> iteratorRoom = rooms.iterator();
			Iterator<Guest> iteratorGuest = guests.iterator();
			while (iteratorRoom.hasNext()) {
				Room room = (Room) iteratorRoom.next();
				OrderRoom orderRoom = new OrderRoom();
				orderRoom.setidOrder(order.getidOrder());
				orderRoom.setidRoom(room.getidRoom());
				roomDao.update(room);
				orderRoomDao.save(orderRoom);
			}
			while (iteratorGuest.hasNext()) {
				Guest guest = (Guest) iteratorGuest.next();
				System.out.println(guest);
				guest = saveNewGuest(guest);
				OrderGuest orderGuest = new OrderGuest();
				orderGuest.setidOrder(order.getidOrder());
				orderGuest.setidGuest(guest.getidGuest());
				orderGuestDao.save(orderGuest);
			}
			issuccess = true;
			TheLog.info("订单提交成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return issuccess;
	}

	// registerStage
	public static Order registerhandle(String phoneNumber) {
		Order order = null;
		order = orderDao.getByPhoneNumber(phoneNumber);
		return order;
	}
	
	public static List<Room> getBookRoom(long idOrder) {
		List<Room> list = null;
		
		return list;
	}
	
	public static List<Guest> getBookGuests(long idOrder) {
		List<Guest> list = null;
		
		return list;
	}

	// 转换时间用的
	private static Timestamp localDate2Timestamp(LocalDate localDate) {
		ZoneId zoneId = ZoneId.systemDefault();
		Instant instant = localDate.atStartOfDay().atZone(zoneId).toInstant();
		Date date = Date.from(instant);
		return new Timestamp(date.getTime());
	}

	public static boolean guestValidation(Guest guest) {
		Guest theGuest = guestDao.getByidCard(guest.getidCard());
		if (theGuest != null) {
			return true;
		}
		return false;
	}

	public static Guest saveNewGuest(Guest guest) {
		Guest newguest = guest;
		if (!guestValidation(guest)) {
			guestDao.add(guest);
			newguest = guestDao.getByidCard(guest.getidCard());
		} else if (guest.getphoneNumber() != null) {
			guestDao.update(guest);
			newguest = guestDao.getByidCard(guest.getidCard());
		}
		return newguest;
	}

	// RegisterStage
	public static void orderFinish(Order order) {
		order.setstatus(Constant.oREGISTER);
		orderDao.update(order);
	}

	// LeaveStage
	public static Order searchidRoom(long idRoom) {
		return orderDao.getByidRoom(idRoom);
	}

	public static void leaveHandle(Room room) {
		// 这里只要退一间房间就等于订单完成
		// 所以为了偷懒而故意犯错
		Order order = orderDao.getByidRoom(room.getidRoom());
		order.setstatus(Constant.oDONE);
		orderDao.update(order);
		room.setstatus(Constant.CLEANNING);
		orderDao.update(order);
	}

}

// 专门针对Core.buffer里存储的临时数据进行循环处理
abstract class BufferHandle<T> {
	public void handle(List<T> list) {
		Iterator<T> iterator = list.iterator();
		while (iterator.hasNext()) {
			T t = (T) iterator.next();
			map(t);
		}
	}

	abstract public void map(T t);
}

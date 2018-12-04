package control;

import application.*;
import java.sql.Timestamp;
import model.domain.*;
import java.util.Date;
import java.util.List;
import Log.TheLog;
import model.Dao.*;
import model.Dao.Impl.*;

public class Core {
	static User employee = new User();
	private static GuestDao guestDao = new GuestDaoImpl();
	private static UserDAO userDAO = new UserDaoImpl();
	private static RoomTypeDao roomTypeDao = new RoomTypeDaoImpl();
	private static RoomDao roomDao = new RoomDaoImpl();
	private static OrderDao orderDao = new OrderDaoImpl();
	private static OrderGuestRoomDao orderGuestRoomDao = new OrderGuestRoomDaoImpl();
	private static boolean permission = false;

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

	public static List<RoomType> roomTypes() {
		return roomTypeDao.listall();
	}

	public static List<Guest> guestChoice(String name) {
		return guestDao.listAll();
	}

	public static boolean authentication(String password) {
		return employee.getpassword().equals(password);
	}

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

	public static Order searchidRoom(long idRoom) {
		return orderDao.getByidRoom(idRoom);
	}

	public static void leaveHandle(Long idRoom, Order order) {
		Room room = roomDao.get(idRoom);
		room.setstatus(Constant.CLEANNING);
		order.setleftDate(new Timestamp(new Date().getTime()));
		order.setstatus(Constant.oDONE);
		orderDao.update(order);
	}

	public static void roomIsReady(Room newroom) {
		newroom.setstatus(Constant.FREE);
		roomDao.update(newroom);
	}

	public static List<Room> watiToClean() {
		return roomDao.listCleanning();
	}

	public static List<Room> checkRoom() {
		return roomDao.listAll();
	}

	public static List<Order> showAllOrders() {
		return orderDao.listall();
	}

	public static List<Order> showNotDoneOrders() {
		return orderDao.listallnotDone();
	}

	public static List<Order> queryOrderbyRoomId(String newValue) {
		return orderDao.listallnotDone();
	}
	
	public static String registerhandle() {
		
		return null;
	}
	
}

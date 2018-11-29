package control;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import Log.TheLog;
import application.Authentication;
import model.Dao.GuestDao;
import model.Dao.RoomDao;
import model.Dao.RoomTypeDao;
import model.Dao.UserDAO;
import model.Dao.Impl.GuestDaoImpl;
import model.Dao.Impl.RoomDaoImpl;
import model.Dao.Impl.RoomTypeDaoImpl;
import model.Dao.Impl.UserDaoImpl;
import model.domain.Guest;
import model.domain.RoomType;
import model.domain.User;

public class Core {
	static User employee = new User();
	private static GuestDao guestDao = new GuestDaoImpl();
	private static UserDAO userDAO = new UserDaoImpl();
	private static RoomTypeDao roomTypeDao = new RoomTypeDaoImpl();
	private static RoomDao roomDao = new RoomDaoImpl();
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
		if(stage.kind) {
			permission = true;
			System.out.println("Permission");
		}
	}
	
	public static boolean getPermission() {
		if(permission) {
			permission = false;
			return true;
		}
		return permission;
	}
}

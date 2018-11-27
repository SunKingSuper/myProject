package control;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import model.Dao.GuestDao;
import model.Dao.RoomDao;
import model.Dao.RoomTypeDao;
import model.Dao.UserDAO;
import model.Dao.Impl.GuestDaoImpl;
import model.Dao.Impl.RoomDaoImpl;
import model.Dao.Impl.RoomTypeDaoImpl;
import model.Dao.Impl.UserDaoImpl;
import model.domain.RoomType;
import model.domain.User;

public class Core {
	static User employee = new User();
	private static GuestDao guestDao = new GuestDaoImpl();
	private static UserDAO userDAO = new UserDaoImpl();
	private static RoomTypeDao roomTypeDao = new RoomTypeDaoImpl();
	private static RoomDao roomDao = new RoomDaoImpl();

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
	
	public static List<RoomType> roomTypes() {
		return roomTypeDao.listall();
	}

	public static boolean authentication(String password) {
		return employee.getpassword().equals(password);
	}
}

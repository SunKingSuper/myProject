package model.test;

import java.sql.Timestamp;
import java.util.Date;

import model.Dao.Impl.UserDaoImpl;
import model.domain.User;

public class UserDaoTest {
	private static UserDaoImpl dao = new UserDaoImpl();

	public static void save() {
		User user = new User();
		user.setidUser(1);
		user.setpassword("1");
		user.setlastLogin(new Timestamp(new Date().getTime()));
		user.setlastLogout(new Timestamp(new Date().getTime()));
		dao.save(user);
	}

	public static void getUser() {
		System.out.println(dao.get(2L));
	}

	public static void updateUser() {
		User user = new User();
		user.setidUser(2);
		user.setpassword("2");
		user.setlastLogin(new Timestamp(new Date().getTime()));
		user.setlastLogout(new Timestamp(new Date().getTime()));
		dao.update(user.getidUser(), user);
	}

	public static void deleteUser() {
		dao.delete(2L);
	}

	public static void main(String[] args) {
//	 save();
		 getUser();
		// updateUser();
		// deleteUser();
	}
}

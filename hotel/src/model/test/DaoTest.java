package model.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import model.Dao.*;
import model.Dao.Impl.*;
import model.domain.*;

public class DaoTest {
	private static HashMap<String, Object> buffer = new HashMap<>(); // 用来存放临时数据
	private static GuestDao guestDao = new GuestDaoImpl();
	private static UserDAO userDAO = new UserDaoImpl();
	private static RoomTypeDao roomTypeDao = new RoomTypeDaoImpl();
	private static RoomDao roomDao = new RoomDaoImpl();
	private static RoleSaleDao roleSaleDao = new RoleSaleDaoImpl();
	private static OrderDao orderDao = new OrderDaoImpl();
	private static OrderRoomDao orderRoomDao = new OrderRoomDaoImpl();
	private static OrderGuestDao orderGuestDao = new OrderGuestDaoImpl();
	
	private static void guestDaoTest() {
		Guest guest = new Guest();
		guest.setidCard("123123124");
		guest.setname("a");
		guestDao.add(guest);
		guest = guestDao.getByidCard("123123124");
		System.out.println(guest);
	}
	
	private static void orderDaoTest() {
		Order order =  new Order();
		order.setidOrder(1);
		order.setname("1");
		order.setregisterDate(new Timestamp(new Date().getTime()));
		order.setorderTime(new Timestamp(new Date().getTime()));
		order.setleftDate(new Timestamp(new Date().getTime()));
		order.setcomment("sada");
		order.setstatus(2001);
		order.setamount(100.0);
		orderDao.add(order);
	}
	
	public static void orderXXXTest() {
		System.out.println(orderGuestDao.listAll().get(0));
		System.out.println(orderRoomDao.listAll().get(0));
	}
	
	public static void main(String[] args) {
		orderXXXTest();
	}
	
}

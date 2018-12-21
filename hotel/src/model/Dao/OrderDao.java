package model.Dao;

import java.sql.Timestamp;
import java.util.List;

import model.domain.Order;

public interface OrderDao {
	public void save(Order order);
	public void add(Order order);
	public void update(Order order);
	public Order get(long idOrder);
	public List<Order> listallnotDone();
	public List<Order> listall();
	public Order getByidRoom(long idRoom);
	public Order getByPhoneNumber(String phoneNumber);
}

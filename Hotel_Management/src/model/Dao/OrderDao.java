package model.Dao;

import java.util.List;

import model.domain.Order;

public interface OrderDao {
	public void save(Order order);
	public void update(Order order);
	public Order get(long idOrder);
	public Order getByidRoom(long idRoom);
	public List<Order> listallnotDone();
}

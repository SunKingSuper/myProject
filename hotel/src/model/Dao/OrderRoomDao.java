package model.Dao;

import java.util.List;

import model.domain.OrderRoom;

public interface OrderRoomDao {
	public void save(OrderRoom orderRoom);
	public List<OrderRoom> get(long idOrder);
	public List<OrderRoom> listAll();
}
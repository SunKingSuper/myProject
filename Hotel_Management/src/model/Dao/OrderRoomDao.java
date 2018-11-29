package model.Dao;

import java.util.List;

import model.domain.OrderRoom;

public interface OrderRoomDao {
	public void save(List<OrderRoom> details); 
	public List<OrderRoom> get(long idOrder);
}

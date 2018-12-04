package model.domain;

import java.util.List;

public interface OrderGuestRoomDao {
	public void save(OrderGuestRoom orderGuestRoom);
	public void update(OrderGuestRoom newOrderGuestRoom);
	public List<OrderGuestRoom> getbyidOrder(long idOrder);
	public List<OrderGuestRoom> getbyidRoom(long idRoom);
	public OrderGuestRoom getbyidGuest(long idGuest);
}

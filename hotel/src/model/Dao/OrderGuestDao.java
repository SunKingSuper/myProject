package model.Dao;

import java.util.List;

import model.domain.OrderGuest;

public interface OrderGuestDao {
	public void save(OrderGuest orderGuest);
	public List<OrderGuest> get(long idOrder);
	public List<OrderGuest> listAll();
}

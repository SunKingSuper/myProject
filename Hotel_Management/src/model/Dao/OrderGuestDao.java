package model.Dao;

import java.util.List;

import model.domain.OrderGuest;

public interface OrderGuestDao {
	public void save(List<OrderGuest> details); 
	public List<OrderGuest> get(long idOrder);
}

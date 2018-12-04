package model.Dao.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.JdbcTemplate;
import model.Dao.MyResultSetHandle;
import model.domain.OrderGuestRoom;
import model.domain.OrderGuestRoomDao;

public class OrderGuestRoomDaoImpl implements OrderGuestRoomDao {

	@Override
	public void update(OrderGuestRoom newOrderGuestRoom) {
		JdbcTemplate.update("UPDATE orderGuestRoom SET (idOrder = ?, idRoom = ?) WHERE (idGuest = ?)", 
				newOrderGuestRoom.getidOrder(), newOrderGuestRoom.getidRoom(), newOrderGuestRoom.getidGuest());	
	}

	@Override
	public List<OrderGuestRoom> getbyidOrder(long idOrder) {
		List<OrderGuestRoom> list = JdbcTemplate.query("SELECT * FROM orderGuestRoom WHERE idOrder = ?", new OrderGuestRoomResultSetHandle(), idOrder);
		return list;
	}

	@Override
	public List<OrderGuestRoom> getbyidRoom(long idRoom) {
		List<OrderGuestRoom> list = JdbcTemplate.query("SELECT * FROM orderGuestRoom WHERE idRoom = ?", new OrderGuestRoomResultSetHandle(), idRoom);
		return list;
	}

	@Override
	public OrderGuestRoom getbyidGuest(long idGuest) {
		List<OrderGuestRoom> list = JdbcTemplate.query("SELECT * FROM orderGuestRoom WHERE idGuest = ?", new OrderGuestRoomResultSetHandle(), idGuest);
		return list.size()==1?list.get(0):null;
	}

	@Override
	public void save(OrderGuestRoom orderGuestRoom) {
		JdbcTemplate.update("INSERT INTO orderGuestRoom (idOrder, idGuest, idRoom) VALUES (?, ?, ?)", 
				orderGuestRoom.getidOrder(), orderGuestRoom.getidGuest(), orderGuestRoom.getidRoom());		
	}

}

class OrderGuestRoomResultSetHandle implements MyResultSetHandle {

	@Override
	public List handle(ResultSet resultSet) throws Exception {
		List<OrderGuestRoom> list = new ArrayList<OrderGuestRoom>();
		while(resultSet.next()) {
			OrderGuestRoom orderGuestRoom = new OrderGuestRoom();
			orderGuestRoom.setidGuest(resultSet.getLong("idGuest"));
			orderGuestRoom.setidRoom(resultSet.getLong("idRoom"));
			orderGuestRoom.setidOrder(resultSet.getLong("idOrder"));
			list.add(orderGuestRoom);
		}
		return list;
	}
	
}

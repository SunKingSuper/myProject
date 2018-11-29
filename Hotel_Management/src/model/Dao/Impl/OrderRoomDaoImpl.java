package model.Dao.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.JdbcTemplate;
import model.Dao.MyResultSetHandle;
import model.Dao.OrderRoomDao;
import model.domain.OrderRoom;

public class OrderRoomDaoImpl implements OrderRoomDao {

	@Override
	public void save(List<OrderRoom> details) {
		Iterator<OrderRoom> iterator = details.iterator();
		while (iterator.hasNext()) {
			OrderRoom orderRoom = (OrderRoom) iterator.next();
			JdbcTemplate.update("INSERT INTO orderRoom VALUES (?,?)",
					orderRoom.getidOrder(), orderRoom.getidRoom());
		}
	}

	@Override
	public List<OrderRoom> get(long idOrder) {
		List<OrderRoom> list = JdbcTemplate.query("SELECT * FROM orderRoom WHERE (idOrder = ?)", new OrderRoomResultSetHandle(), idOrder);
		return list;
	}

}

class OrderRoomResultSetHandle implements MyResultSetHandle {

	@Override
	public List handle(ResultSet resultSet) throws Exception {
		ArrayList<OrderRoom> list = new ArrayList<>();
		while (resultSet.next()) {
			OrderRoom orderRoom = new OrderRoom();
			orderRoom.setidOrder(resultSet.getLong("idOrder"));
			orderRoom.setidRoom(resultSet.getLong("idRoom"));
			list.add(orderRoom);
		}
		return list;
	}
	
}

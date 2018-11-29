package model.Dao.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.JdbcTemplate;
import model.Dao.MyResultSetHandle;
import model.Dao.OrderGuestDao;
import model.domain.OrderGuest;
import model.domain.OrderGuest;

public class OrderGuestDaoImpl implements OrderGuestDao {

	@Override
	public void save(List<OrderGuest> details) {
		Iterator<OrderGuest> iterator = details.iterator();
		while (iterator.hasNext()) {
			OrderGuest orderGuest = (OrderGuest) iterator.next();
			JdbcTemplate.update("INSERT INTO orderGuest VALUES (?,?)",
					orderGuest.getidOrder(), orderGuest.getidGuest());
		}
	}

	@Override
	public List<OrderGuest> get(long idOrder) {
		List<OrderGuest> list = JdbcTemplate.query("SELECT * FROM orderGuest WHERE (idOrder = ?)", new OrderGuestResultSetHandle(), idOrder);
		return list;
	}

}

class OrderGuestResultSetHandle implements MyResultSetHandle {

	@Override
	public List handle(ResultSet resultSet) throws Exception {
		ArrayList<OrderGuest> list = new ArrayList<>();
		while (resultSet.next()) {
			OrderGuest orderGuest = new OrderGuest();
			orderGuest.setidOrder(resultSet.getLong("idOrder"));
			orderGuest.setidGuest(resultSet.getLong("idGuest"));
			list.add(orderGuest);
		}
		return list;
	}
	
}

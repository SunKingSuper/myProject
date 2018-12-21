package model.Dao.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.JdbcTemplate;
import model.Dao.MyResultSetHandle;
import model.Dao.OrderGuestDao;
import model.domain.OrderGuest;

public class OrderGuestDaoImpl implements OrderGuestDao{

	@Override
	public void save(OrderGuest orderGuest) {
		JdbcTemplate.update("INSERT INTO orderGuest VALUES (?, ?)", orderGuest.getidOrder(), orderGuest.getidGuest());
	}

	@Override
	public List<OrderGuest> get(long idOrder) {
		List<OrderGuest> list = JdbcTemplate.query("SELECT * FROM orderGuest WHERE (idOrder = ?)", new OrderGuestResultSetHandle(), idOrder);
		return list;
	}

	@Override
	public List<OrderGuest> listAll() {
		List<OrderGuest> list = JdbcTemplate.query("SELECT * FROM orderGuest", new OrderGuestResultSetHandle());
		return list;
	}
	
}

class OrderGuestResultSetHandle implements MyResultSetHandle {

	@Override
	public List handle(ResultSet resultSet) throws Exception {
		List<OrderGuest> list = new ArrayList<OrderGuest>();
		while(resultSet.next()) {
			OrderGuest orderGuest = new OrderGuest();
			orderGuest.setidOrder(resultSet.getLong("idOrder"));
			orderGuest.setidGuest(resultSet.getLong("idGuest"));
			list.add(orderGuest);
		}
		return list;
	}
	
}
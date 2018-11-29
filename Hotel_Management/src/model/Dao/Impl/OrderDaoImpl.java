package model.Dao.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.JdbcTemplate;
import model.Dao.MyResultSetHandle;
import model.Dao.OrderDao;
import model.domain.Order;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void save(Order order) {
		JdbcTemplate.update("INSERT INTO order VALUES (?,?,?,?,?,?,?,?)",
				order.getidOrder(), order.getmain_idGuest(), order.getregisterDate(), order.getleftDate(), order.getorderTime(), order.getamount(), order.getstatus(), order.getcomment());
	}

	@Override
	public void update(Order order) {
		JdbcTemplate.update("UPDATE order SET  idOrder=?, main_idGuest=?, registerDate=?, lefDate=?, orderTime=?, amount=?, status=? WHERE (idOrder=?)",
				order.getidOrder(), order.getmain_idGuest(), order.getregisterDate(), order.getleftDate(), order.getorderTime(), order.getamount(), order.getstatus(), order.getcomment());	
	}

	@Override
	public Order get(long idOrder) {
		List<Order> list = JdbcTemplate.query("SELECT * FROM order WHERE (idOrder = ?)", new OrderResultSetHandler(), idOrder);
		return list.size() == 1?null:list.get(0);
	}

	@Override
	public List<Order> listallnotDone() {
		return JdbcTemplate.query("SELECT * FROM order)", new OrderResultSetHandler());
	}

}

class OrderResultSetHandler implements MyResultSetHandle {
	public List handle(ResultSet resultSet) throws Exception {
		ArrayList<Order> result = new ArrayList<>();
		while (resultSet.next()) {
			Order order = new Order();
			order.setidOrder(resultSet.getLong("idOrder"));
			order.setmain_idGuest(resultSet.getLong("main_idGuest"));
			order.setregisterDate(resultSet.getDate("registerDate"));
			order.setleftDate(resultSet.getDate("leftDate"));
			order.setorderTime(resultSet.getDate("orderTime"));
			order.setamount(resultSet.getFloat("amount"));
			order.setstatus(resultSet.getLong("status"));
			order.setcomment(resultSet.getString("comment"));
			result.add(order);
		}
		return result;
	}
}
package model.Dao.Impl;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import application.Constant;
import model.JdbcTemplate;
import model.Dao.MyResultSetHandle;
import model.Dao.OrderDao;
import model.domain.Order;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void save(Order order) {
		JdbcTemplate.update("INSERT INTO KingSun_Hotel.order VALUES (?,?,?,?,?,?,?,?,?)", order.getidOrder(), order.getname(), order.getPhoneNumber(),
				order.getregisterDate(), order.getleftDate(), order.getorderTime(), order.getamount(),
				order.getstatus(), order.getcomment());
	}

	@Override
	public void update(Order order) {
		JdbcTemplate.update(
				"UPDATE KingSun_Hotel.order SET  idOrder=?, name=?, phoneNumber=?, registerDate=?, lefDate=?, orderTime=?, amount=?, status=? WHERE (idOrder=?)",
				order.getidOrder(), order.getname(), order.getPhoneNumber(), order.getregisterDate(), order.getleftDate(),
				order.getorderTime(), order.getamount(), order.getstatus(), order.getcomment());
	}

	@Override
	public Order get(long idOrder) {
		List<Order> list = JdbcTemplate.query("SELECT * FROM KingSun_Hotel.order WHERE (idOrder = ?)", new OrderResultSetHandler(),
				idOrder);
		return list.size() == 1 ? null : list.get(0);
	}

	@Override
	public List<Order> listallnotDone() {
		return JdbcTemplate.query("SELECT * FROM KingSun_Hotel.order WHERE status != ?", new OrderResultSetHandler(), Constant.oDONE);
	}

	@Override
	public Order getByidRoom(long idRoom) {
		List<Order> list = JdbcTemplate.query(
				"SELECT * FROM order WHERE (idOrder = (SELECT idOrder FROM KingSun_Hotel.orderRoom WHERE (idRoom = ?))",
				new OrderResultSetHandler(), idRoom);
		return list.size() == 1 ? null : list.get(0);
	}

	@Override
	public List<Order> listall() {
		return JdbcTemplate.query("SELECT * FROM KingSun_Hotel.order", new OrderResultSetHandler());
	}

	@Override
	public void add(Order order) {
		order.setidOrder(1);
		System.out.println(order);
		JdbcTemplate.update(
				"INSERT INTO KingSun_Hotel.order (name, registerDate, leftDate, orderTime, amount, status, comment) VALUES (?, ?, ?, ?, ?, ?, ?)",
				order.getname(), order.getregisterDate(), order.getleftDate(), order.getorderTime(),
				order.getamount(), order.getstatus(), order.getcomment());
	}

	@Override
	public Order getByPhoneNumber(String phoneNumber) {
		List<Order> list = JdbcTemplate.query(
				"SELECT * FROM order WHERE (phoneNumber = ? and status = ?)",
				new OrderResultSetHandler(), phoneNumber, Constant.oBOOK);
		return list.size()==1?list.get(0):null;
	}


}

class OrderResultSetHandler implements MyResultSetHandle {
	public List handle(ResultSet resultSet) throws Exception {
		ArrayList<Order> result = new ArrayList<>();
		while (resultSet.next()) {
			Order order = new Order();
			order.setidOrder(resultSet.getLong("idOrder"));
			order.setname(resultSet.getString("name"));
			order.setPhoneNumber(resultSet.getString("phoneNumber"));
			order.setregisterDate(resultSet.getTimestamp("registerDate"));
			order.setleftDate(resultSet.getTimestamp("leftDate"));
			order.setorderTime(resultSet.getTimestamp("orderTime"));
			order.setamount(resultSet.getDouble("amount"));
			order.setstatus(resultSet.getLong("status"));
			order.setcomment(resultSet.getString("comment"));
			result.add(order);
		}
		return result;
	}
}
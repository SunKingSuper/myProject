package app.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.model.Database;
import app.model.Order;
import app.model.Order;
import app.model.RSHandle;
import javafx.collections.ObservableList;

public class OrderControl {
	public static void add() {

	}

	public static boolean addOrder(long idorder, long idmaterial, LocalDate date, long idStore, String supplier,
			String tansporter, double planAmount, double storeAmount, String responsiblePerson) {
		try {
			List<Long> idorders = (List<Long>) Database.use("SELECT idorder FROM MaterialManagement.order",
					new RSHandle() {

						@Override
						public List handle(ResultSet resultSet) throws SQLException {
							List<Long> list = new ArrayList<Long>();
							while (resultSet.next()) {
								list.add(resultSet.getLong("idorder"));
							}
							return list;
						}
					});

			if (idorders.contains(idorder)) {
				Database.use(
						"UPDATE MaterialManagement.order SET (" + "idmaterial = " + String.valueOf(idmaterial)
								+ ",date = '" + date.toString() + "',idStore=" + String.valueOf(idStore) + ",supplier='"
								+ supplier + "',supplier='" + tansporter + "',planAmount=" + String.valueOf(planAmount)
								+ ",storeAmount=" + String.valueOf(storeAmount) + ",responsiblePerson='"
								+ responsiblePerson + "')" + ") WHERE (idorder = " + String.valueOf(idorder) + ")",
						null);
			} else {
				Database.use("INSERT INTO MaterialManagement.order VALUES (" + String.valueOf(idorder) + ","
						+ String.valueOf(idmaterial) + ",'" + date.toString() + "'," + String.valueOf(idStore) + ",'"
						+ supplier + "','" + tansporter + "'," + String.valueOf(planAmount) + ","
						+ String.valueOf(storeAmount) + ",'" + responsiblePerson + "')", null);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void showOrders(ObservableList<Order> orders) {
		orders.removeAll();

		List<Order> list = (List<Order>) Database.use("SELECT * FROM MaterialManagement.order", new RSHandle() {

			@Override
			public List handle(ResultSet resultSet) throws SQLException {
				List<Order> list = new ArrayList<Order>();
				while (resultSet.next()) {
					Order order = new Order();
					order.setIdorder(resultSet.getLong("idorder"));
					order.setIdmaterial(resultSet.getLong("idmaterial"));
					order.setDate(new Timestamp(resultSet.getDate("date").getTime()));					// 头痛
					order.setIdStore(resultSet.getLong("idStore"));
					order.setSupplier(resultSet.getString("supplier"));
					order.setTransporter(resultSet.getString("transporter"));
					order.setPlanAmount(resultSet.getLong("planAmount"));
					order.setStoreAmount(resultSet.getLong("storeAmount"));
					order.setResponsiblePerson(resultSet.getString("responsiblePerson"));
					list.add(order);
				}
				return list;
			}
		});

		Iterator<Order> iterator = list.iterator();
		while (iterator.hasNext()) {
			Order order = (Order) iterator.next();
			orders.add(order);
		}
	}
}

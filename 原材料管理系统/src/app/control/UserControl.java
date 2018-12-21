package app.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.Database;
import app.model.RSHandle;

public class UserControl {
	public static boolean userValidation(String id, String password) {
		boolean isEqual = false;
		isEqual = (boolean) Database.use("SELECT * FROM MaterialManagement.user WHERE id = '" + id + "'", new RSHandle() {

			@Override
			public List handle(ResultSet resultSet) throws SQLException {
				List list = new ArrayList<Boolean>();
				resultSet.next();
				if (resultSet.getString("password").equals(password)) {
					list.add(true);
				} else {
					list.add(false);
				}
				return list;
			}
		}).get(0);
		return isEqual;
	}
}

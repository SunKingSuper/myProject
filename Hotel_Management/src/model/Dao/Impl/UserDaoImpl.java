package model.Dao.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Log.TheLog;
import model.JdbcTemplate;
import model.Dao.MyResultSetHandle;
import model.Dao.UserDAO;
import model.domain.User;

public class UserDaoImpl implements UserDAO {

	@Override
	public void save(User user) {
		JdbcTemplate.update("INSERT INTO User VALUES (?, ?, ?, ?)", user.getidUser(), user.getpassword(),
				user.getlastLogin(), user.getlastLogout());
		TheLog.info("save user");
	}

	@Override
	public void delete(Long idUser) {
		JdbcTemplate.update("Delete FROM User WHERE (idUser = ?)", idUser);
		TheLog.info("delete user");
	}

	@Override
	public void update(Long idUser, User newUser) {
		JdbcTemplate.update("UPDATE User SET password = ?, lastLogin = ?, lastLogout = ? WHERE (idUser = ?)",
				newUser.getpassword(), newUser.getlastLogin(), newUser.getlastLogout(), idUser);
		TheLog.info("update user");
	}

	@Override
	public User get(Long idUser) {
		List<User> list = JdbcTemplate.query("SELECT * FROM User WHERE (idUser = ?)", new UserResultSetHandler(),
				idUser);
		TheLog.info("get user");
		return list.size() == 1 ? list.get(0) : null;
	}

	@Override
	public List<User> listAll() {
		TheLog.info("list user");
		return JdbcTemplate.query("SELECT * FROM User", new UserResultSetHandler());
	}

}

class UserResultSetHandler implements MyResultSetHandle {
	public List handle(ResultSet resultSet) throws Exception {
		List resultlist = new ArrayList();
		while (resultSet.next()) {
			User user = new User();
			user.setidUser(resultSet.getLong("idUser"));
			user.setpassword(resultSet.getString("password"));
			user.setlastLogin(resultSet.getTimestamp("lastLogin"));
			user.setlastLogout(resultSet.getTimestamp("lastLogout"));
			resultlist.add(user);
		}
		return resultlist;
	}
}

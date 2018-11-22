package model.Dao.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.JdbcTemplate;
import model.Dao.MyResultSetHandle;
import model.Dao.UserDAO;
import model.domain.User;

public class UserImpl implements UserDAO{

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long idUser) {
		JdbcTemplate.update("Delete * FROM User WHERE idUser = ?",
				idUser);
	}

	@Override
	public void update(Long idUser, User newUser) {
		JdbcTemplate.update("UPDATE User SET password = ?, lastLogin = ?, lastLogout = ? WHERE idUser = ?", 
				newUser.getpassword(), newUser.getlastLogin(), newUser.getlastLogout(), idUser);
	}

	@Override
	public User get(Long idUser) {
		List<User> list = JdbcTemplate.query("SELECT * FROM User WHERE idUser = ?",
				new UserResultSetHandler(), idUser);
		return list.size() == 1?list.get(0):null;
	}

	@Override
	public List<User> listAll() {
		return JdbcTemplate.query("SELECT * FROM User", new UserResultSetHandler());
	}

}

class UserResultSetHandler implements MyResultSetHandle{
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

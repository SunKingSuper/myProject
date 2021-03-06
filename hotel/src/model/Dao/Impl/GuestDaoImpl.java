package model.Dao.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Log.TheLog;
import model.JdbcTemplate;
import model.Dao.GuestDao;
import model.Dao.MyResultSetHandle;
import model.domain.Guest;
import model.domain.Room;

public class GuestDaoImpl implements GuestDao {

	@Override
	public void save(Guest guest) {
		JdbcTemplate.update("INSERT INTO Guest VALUES (?, ?, ?, ?, ?)", guest.getidGuest(), guest.getname(),
				guest.getidCard(), guest.getphoneNumber(), guest.getguestRole());
		TheLog.info("save guest");
	}

	@Override
	public void delete(Long idGuest) {
		JdbcTemplate.update("DELETE FROM Guest WHERE (idGuest = ?)", idGuest);
		TheLog.info("delete guest");
	}

	@Override
	public void update(Guest newGuest) {
		JdbcTemplate.update("UPDATE Guest SET name=?, idCard=?, getphoneNumber=?, guestRole=? WHERE (idGuest = ?)", 
				newGuest.getname(), newGuest.getidCard(), newGuest.getphoneNumber(), newGuest.getguestRole(), newGuest.getidGuest());
		TheLog.info("update guest");
	}

	@Override
	public Guest get(Long idCard) {
		List<Guest> list = JdbcTemplate.query("SELECT * FROM Guest WHERE (idGuest = ?)", new GuestResultSetHandler(),
				idCard);
		TheLog.info("get user");
		return list.size() == 1 ? list.get(0) : null;
	}

	@Override
	public List<Guest> listAll() {
		List<Guest> list = JdbcTemplate.query("SELECT * FROM Guest", new GuestResultSetHandler());
		return list;
	}

	@Override
	public Guest getbyPhonenumber(String phonenumber) {
		List<Guest> list = JdbcTemplate.query("SELECT * FROM Guest WHERE (phoneNumber = ?)", new GuestResultSetHandler(), phonenumber);
		return list.size() == 1 ? list.get(0) : null;
	}

	@Override
	public Guest getByidCard(String idCard) {
		List<Guest> list = JdbcTemplate.query("SELECT * FROM Guest WHERE (idCard = ?)", new GuestResultSetHandler(), idCard);
		return  list.size() == 1 ? list.get(0) : null;
	}

	@Override
	public void add(Guest newGuest) {
		JdbcTemplate.update("INSERT INTO Guest (name, idCard) VALUES (?, ?)", 
				newGuest.getname(), newGuest.getidCard());
		TheLog.info("add guest");
		
	}

	@Override
	public void addbyphone(Guest newGuest) {
		JdbcTemplate.update("INSERT INTO Guest (name, phoneNumber) VALUES (?, ?)", 
				newGuest.getname(), newGuest.getphoneNumber());
		TheLog.info("add guest");
	}

	@Override
	public List<Guest> listBookGuests(long idOrder) {
		List<Guest> list = JdbcTemplate.query("SELECT * FROM Guest WHERE idGuest in (SELECT idGuest FROM orderGuest WHERE idOrder = ?)", new GuestResultSetHandler(), idOrder);
		return list;
	}

}


class GuestResultSetHandler implements MyResultSetHandle {
	public List handle(ResultSet resultSet) throws Exception {
		List resultlist = new ArrayList();
		while (resultSet.next()) {
			Guest guest = new Guest();
			guest.setidGuest(resultSet.getLong("idGuest"));
			guest.setname(resultSet.getString("name"));
			guest.setidCard(resultSet.getString("idCard"));
			guest.setphoneNumber(resultSet.getString("phoneNumber"));
			guest.setguestRole(resultSet.getString("guestRole"));
			resultlist.add(guest);
		}
		return resultlist;
	}
}
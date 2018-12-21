package model.Dao.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.JdbcTemplate;
import model.Dao.MyResultSetHandle;
import model.Dao.RoleSaleDao;
import model.domain.RoleSale;

public class RoleSaleDaoImpl implements RoleSaleDao {

	@Override
	public void update(RoleSale newRoleSale) {
		JdbcTemplate.update("UPDATE roleSale SET (guestRole = ?, dismiss = ?) WHERE (guestRole = ?)",
				newRoleSale.getguestRole(), newRoleSale.getdismiss(), newRoleSale.getguestRole());
	}

	@Override
	public RoleSale get(String guestRole) {
		List<RoleSale> list = JdbcTemplate.query("SELECT * FROM roleSale WHERE guestRole = ?",
				new RoleSaleResultSetHandle(), guestRole);
		return list.size() == 1 ? list.get(0) : null;
	}

	@Override
	public List<RoleSale> listAll() {
		List<RoleSale> list = JdbcTemplate.query("SELECT * FROM roleSale", new RoleSaleResultSetHandle());
		return list;
	}

}

class RoleSaleResultSetHandle implements MyResultSetHandle {

	@Override
	public List handle(ResultSet resultSet) throws Exception {
		List<RoleSale> list = new ArrayList<RoleSale>();
		while (resultSet.next()) {
			RoleSale roleSale = new RoleSale();
			roleSale.setguestRole(resultSet.getString("guestRole"));
			roleSale.setdismiss(resultSet.getFloat("dismiss"));
			list.add(roleSale);
		}
		return list;
	}

}

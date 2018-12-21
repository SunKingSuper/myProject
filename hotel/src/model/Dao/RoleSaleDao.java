package model.Dao;
import java.util.List;

import model.domain.RoleSale;

public interface RoleSaleDao {
	public void update(RoleSale newRoleSale);
	public RoleSale get(String guestRole);
	public List<RoleSale> listAll();
}

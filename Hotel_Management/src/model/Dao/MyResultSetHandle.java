package model.Dao;

import java.sql.ResultSet;
import java.util.List;

public interface MyResultSetHandle {
	List handle(ResultSet resultSet) throws Exception;
}

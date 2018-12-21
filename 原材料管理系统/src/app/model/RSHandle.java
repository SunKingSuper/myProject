package app.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RSHandle {
	public List handle(ResultSet resultSet) throws SQLException;
}

package model.Dao.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Log.TheLog;
import model.JdbcTemplate;
import model.Dao.MyResultSetHandle;
import model.Dao.RoomTypeDao;
import model.domain.RoomType;

public class RoomTypeDaoImpl implements RoomTypeDao{

	@Override
	public RoomType get(String roomType) {
		List<RoomType> list = JdbcTemplate.query("SELECT * FROM roomType WHERE (roomType = ?",
				new RoomTypeResultSetHandler(),roomType);
		TheLog.info("get roomType");
		return list.size() == 1? list.get(0) : null;
	}

	@Override
	public List<RoomType> listall() {
		List<RoomType> list = JdbcTemplate.query("SELECT * FROM roomType",
				new RoomTypeResultSetHandler());
		return list;
	}

}

class RoomTypeResultSetHandler implements MyResultSetHandle {
	public List handle(ResultSet resultSet) throws Exception {
		ArrayList<RoomType> result = new ArrayList<>();
		while (resultSet.next()) {
			RoomType RoomType = new RoomType();
			RoomType.setroomType(resultSet.getString("roomType"));
			RoomType.setprice(resultSet.getDouble("price"));
			result.add(RoomType);
		}
		return result;
	}
}
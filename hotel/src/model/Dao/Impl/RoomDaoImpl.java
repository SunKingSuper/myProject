package model.Dao.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Log.TheLog;
import application.Constant;
import model.JdbcTemplate;
import model.Dao.MyResultSetHandle;
import model.Dao.RoomDao;
import model.domain.Guest;
import model.domain.Room;

public class RoomDaoImpl implements RoomDao {

	@Override
	public void update(Room newroom) {
		JdbcTemplate.update("UPDATE room SET status=? WHERE (idRoom = ?)", newroom.getstatus(), newroom.getidRoom());
		TheLog.info("update room");
	}

	@Override
	public Room get(Long idRoom) {
		List<Room> list = JdbcTemplate.query("SELECT * FROM room WHERE (idRoom = ?)", new RoomResultSetHandler(),
				idRoom);
		TheLog.info("get room");
		return list.size() == 1 ? list.get(0) : null;
	}

	@Override
	public List<Room> listAll() {
		List<Room> list = JdbcTemplate.query("SELECT * FROM room", new RoomResultSetHandler());
		TheLog.info("listall room");
		return list;
	}

	@Override
	public List<Room> listByRoomTypeAndStatus(String roomType, long status) {
		List<Room> list = JdbcTemplate.query("SELECT * FROM room WHERE roomTypes = ? AND status = ?", new RoomResultSetHandler(), roomType, status);
		return list;
	}

	@Override
	public List<Room> listOneStatus(long status) {
		List<Room> list = JdbcTemplate.query("SELECT * FROM room WHERE status = ?", new RoomResultSetHandler(), status);
		return list;
	}

	@Override
	public List<Room> listBookRooms(long idOrder) {
		List<Room> list = JdbcTemplate.query("SELECT * FROM room WHERE idRoom in (SELECT idRoom FROM orderRoom WHERE idOrder = ?)", new GuestResultSetHandler(), idOrder);
		return list;
	}
}

class RoomResultSetHandler implements MyResultSetHandle {
	public List handle(ResultSet resultSet) throws Exception {
		ArrayList<Room> result = new ArrayList<>();
		while (resultSet.next()) {
			Room room = new Room();
			room.setidRoom(resultSet.getLong("idRoom"));
			room.setroomType(resultSet.getString("roomType"));
			room.setstatus(resultSet.getLong("status"));
			result.add(room);
		}
		return result;
	}
}
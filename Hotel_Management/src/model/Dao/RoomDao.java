package model.Dao;

import java.util.List;

import model.domain.Room;

public interface RoomDao {
	void update(Room newroom);
	Room get(Long idRoom);
	List<Room> listAll();
}

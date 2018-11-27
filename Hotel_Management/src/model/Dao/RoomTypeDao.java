package model.Dao;

import java.util.List;

import model.domain.RoomType;

public interface RoomTypeDao {
	public RoomType get(String roomType);
	public List<RoomType> listall();
}

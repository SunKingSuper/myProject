package model.domain;

public class Room {
	private long idRoom;
	private String roomType;
	private long status;

	public void setidRoom(long idRoom) {
		this.idRoom = idRoom;
	}

	public void setroomType(String roomType) {
		this.roomType = roomType;
	}

	public void setstatus(long status) {
		this.status = status;
	}

	public long getidRoom() {
		return idRoom;
	}

	public String getroomType() {
		return roomType;
	}

	public long getstatus() {
		return status;
	}

	@Override
	public String toString() {
		return String.format("房间号: %d | 房型: %s | 状态: %d", idRoom, roomType, status);
	}
}

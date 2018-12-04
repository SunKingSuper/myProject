package model.domain;

public class OrderGuestRoom {
	private long idOrder;
	private long idGuest;
	private long idRoom;
	
	public void setidOrder(long idOrder) {
		this.idOrder = idOrder;
	}
	public void setidGuest(long idGuest) {
		this.idGuest = idGuest;
	}
	public void setidRoom(long idRoom) {
		this.idRoom = idRoom;
	}
	public long getidOrder() {
		return idOrder;
	}
	public long getidGuest() {
		return idGuest;
	}
	public long getidRoom() {
		return idRoom;
	}
	@Override
	public String toString() {
		return String.format("idOrder %d | idGuest %d | idRoom %d", idOrder, idGuest, idRoom);
	}
}

package model.domain;

public class OrderRoom {
	private long idOrder;
	private long idRoom;

	public void setidOrder(long idOrder) {
		this.idOrder = idOrder;
	}

	public void setidRoom(long idRoom) {
		this.idRoom = idRoom;
	}

	public long getidOrder() {
		return idOrder;
	}

	public long getidRoom() {
		return idRoom;
	}
	
	@Override
	public String toString() {
		return String.format("订单号 %d | 房间号: %d", idOrder, idRoom);
	}
}

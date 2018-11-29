package model.domain;

public class OrderGuest {
	private long idOrder;
	private long idGuest;
	
	public void setidOrder(long idOrder) {
		this.idOrder = idOrder;
	}
	public void setidGuest(long idGuest) {
		this.idGuest = idGuest;
	}
	public long getidOrder() {
		return idOrder;
	}
	public long getidGuest() {
		return idGuest;
	}
	@Override
	public String toString() {
		return String.format("订单id: %d | 客人id: %d |", idOrder, idGuest);
	}
}

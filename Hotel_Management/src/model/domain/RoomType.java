package model.domain;

public class RoomType {
	private String roomType;
	private double price;

	public void setroomType(String roomType) {
		this.roomType = roomType;
	}

	public void setprice(double price) {
		this.price = price;
	}

	public String getroomType() {
		return roomType;
	}

	public double getprice() {
		return price;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("房型: %s | 价格: %.2f", roomType, price);
	}
}

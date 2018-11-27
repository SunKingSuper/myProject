package model.domain;

public class Guest {
	private long idGuest;
	private String name;
	private String idCard;
	private String phoneNumber;
	private String guestRole;

	public void setidGuest(long idGuest) {
		this.idGuest = idGuest;
	}

	public void setname(String name) {
		this.name = name;
	}

	public void setidCard(String idCard) {
		this.idCard = idCard;
	}

	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setguestRole(String guestRole) {
		this.guestRole = guestRole;
	}

	public long getidGuest() {
		return this.idGuest;
	}

	public String getname() {
		return this.name;
	}

	public String getidCard() {
		return this.idCard;
	}

	public String getphoneNumber() {
		return this.phoneNumber;
	}

	public String getguestRole() {
		return this.guestRole;
	}
	
	@Override
	public String toString() {
		return String.format("客人号: %d | 客人姓名: %s | 身份证号: %s | 电话号码: %s | 身份: %s", idGuest, name, idCard, phoneNumber, guestRole);
	}
}

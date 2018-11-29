package model.domain;

import java.sql.Date;

public class Order {
	private long idOrder;
	private long main_idGuest;
	private Date registerDate;
	private Date leftDate;
	private Date orderTime;
	private float amount;
	private long status;
	private String comment;
	

	public void setidOrder(long idOrder) {
		this.idOrder = idOrder;
	}

	public void setmain_idGuest(long main_idGuest) {
		this.main_idGuest = main_idGuest;
	}

	public void setregisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public void setleftDate(Date leftDate) {
		this.leftDate = leftDate;
	}

	public void setorderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public void setamount(float amount) {
		this.amount = amount;
	}

	public void setstatus(long status) {
		this.status = status;
	}

	public void setcomment(String comment) {
		this.comment = comment;
	}

	public long getidOrder() {
		return idOrder;
	}

	public long getmain_idGuest() {
		return main_idGuest;
	}

	public Date getregisterDate() {
		return registerDate;
	}

	public Date getleftDate() {
		return leftDate;
	}

	public Date getorderTime() {
		return orderTime;
	}

	public float getamount() {
		return amount;
	}

	public long getstatus() {
		return status;
	}

	public String getcomment() {
		return comment;
	}

	@Override
	public String toString() {
		return String.format("订单号: %d | 下单人: %d | 入住日期: %tc | 离开日期: %tc | 订单时间: %tc | 金额: %.2f | 备注: %s |", idOrder,
				main_idGuest, new Date(registerDate.getTime()), new Date(leftDate.getTime()),
				new Date(orderTime.getTime()), amount, status, comment);
	}
}

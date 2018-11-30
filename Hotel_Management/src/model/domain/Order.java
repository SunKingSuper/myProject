package model.domain;

import java.util.Date;
import java.sql.Timestamp;

public class Order {
	private long idOrder;
	private long main_idGuest;
	private Timestamp registerDate;
	private Timestamp leftDate;
	private Timestamp orderTime;
	private float amount;
	private long status;
	private String comment;
	

	public void setidOrder(long idOrder) {
		this.idOrder = idOrder;
	}

	public void setmain_idGuest(long main_idGuest) {
		this.main_idGuest = main_idGuest;
	}

	public void setregisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public void setleftDate(Timestamp leftDate) {
		this.leftDate = leftDate;
	}

	public void setorderTime(Timestamp orderTime) {
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

	public Timestamp getregisterDate() {
		return registerDate;
	}

	public Timestamp getleftDate() {
		return leftDate;
	}

	public Timestamp getorderTime() {
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
		return String.format("订单号: %d \n 下单人: %d \n 入住日期: %tc \n 离开日期: %tc \n 订单时间: %tc \n 金额: %.2f \n 备注: %s \n", idOrder,
				main_idGuest, new Date(registerDate.getTime()), new Date(leftDate.getTime()),
				new Date(orderTime.getTime()), amount, status, comment);
	}
}

package model.domain;

import java.util.Date;
import java.sql.Timestamp;

public class Order {
	private long idOrder;
	private String name;
	private String phoneNumber;
	private Timestamp registerDate;
	private Timestamp leftDate;
	private Timestamp orderTime;
	private double amount;
	private long status;
	private String comment;
	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setidOrder(long idOrder) {
		this.idOrder = idOrder;
	}

	public void setname(String name) {
		this.name = name;
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

	public void setamount(double amount) {
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

	public String getname() {
		return name;
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

	public double getamount() {
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
		return String.format("订单号: %d \n 下单人: %s \n 电话: %s \n 入住日期: %tc \n 离开日期: %tc \n 订单时间: %tc \n 金额: %.2f \n 状态: %d \n 备注: %s \n", idOrder,
				name, phoneNumber, new Date(registerDate.getTime()), new Date(leftDate.getTime()),
				new Date(orderTime.getTime()), amount, status, comment);
	}
}

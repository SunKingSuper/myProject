package app.model;

import java.sql.Timestamp;

public class Order {
	long idorder;
	long idmaterial;
	Timestamp date;
	long idStore;
	String supplier;
	String transporter;
	double planAmount;
	double storeAmount;
	String responsiblePerson;

	public long getIdorder() {
		return idorder;
	}

	public void setIdorder(long idorder) {
		this.idorder = idorder;
	}

	public long getIdmaterial() {
		return idmaterial;
	}

	public void setIdmaterial(long idmaterial) {
		this.idmaterial = idmaterial;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public long getIdStore() {
		return idStore;
	}

	public void setIdStore(long idStore) {
		this.idStore = idStore;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getTransporter() {
		return transporter;
	}

	public void setTransporter(String transporter) {
		this.transporter = transporter;
	}

	public double getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(double planAmount) {
		this.planAmount = planAmount;
	}

	public double getStoreAmount() {
		return storeAmount;
	}

	public void setStoreAmount(double storeAmount) {
		this.storeAmount = storeAmount;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}
}

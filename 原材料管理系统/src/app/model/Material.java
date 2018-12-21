package app.model;

public class Material {
	long idmaterial;
	String name;
	String standard;
	double storeAmount;
	String unit;

	public long getIdmaterial() {
		return idmaterial;
	}

	public void setIdmaterial(long idmaterial) {
		this.idmaterial = idmaterial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public double getStoreAmount() {
		return storeAmount;
	}

	public void setStoreAmount(double storeAmount) {
		this.storeAmount = storeAmount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}

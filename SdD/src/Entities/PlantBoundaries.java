package Entities;

public class PlantBoundaries {
	
	private String limit;
	private double fe;
	private double sio2;
	private double al2o3;
	private double mn;
	private double p;
	private double ppc;
	private double mais50mm;
	private double mais6_3mm;
	private double mais0_15mm;
	
	public PlantBoundaries(String limit, double fe, double sio2, double al2o3, double mn, double p, double ppc,
			double mais50mm, double mais6_3mm, double mais0_15mm) {
		super();
		this.limit = limit;
		this.fe = fe;
		this.sio2 = sio2;
		this.al2o3 = al2o3;
		this.mn = mn;
		this.p = p;
		this.ppc = ppc;
		this.mais50mm = mais50mm;
		this.mais6_3mm = mais6_3mm;
		this.mais0_15mm = mais0_15mm;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public double getFe() {
		return fe;
	}

	public void setFe(double fe) {
		this.fe = fe;
	}

	public double getSio2() {
		return sio2;
	}

	public void setSio2(double sio2) {
		this.sio2 = sio2;
	}

	public double getAl2o3() {
		return al2o3;
	}

	public void setAl2o3(double al2o3) {
		this.al2o3 = al2o3;
	}

	public double getMn() {
		return mn;
	}

	public void setMn(double mn) {
		this.mn = mn;
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public double getPpc() {
		return ppc;
	}

	public void setPpc(double ppc) {
		this.ppc = ppc;
	}

	public double getMais50mm() {
		return mais50mm;
	}

	public void setMais50mm(double mais50mm) {
		this.mais50mm = mais50mm;
	}

	public double getMais6_3mm() {
		return mais6_3mm;
	}

	public void setMais6_3mm(double mais6_3mm) {
		this.mais6_3mm = mais6_3mm;
	}

	public double getMais0_15mm() {
		return mais0_15mm;
	}

	public void setMais0_15mm(double mais0_15mm) {
		this.mais0_15mm = mais0_15mm;
	}
	
}

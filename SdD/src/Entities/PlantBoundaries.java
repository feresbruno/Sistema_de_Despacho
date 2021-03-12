package Entities;

public class PlantBoundaries {
	
	private String limit;
	private double fe;
	private double sio2;
	private double al2o3;
	private double mn;
	private double p;
	private double loi;
	private double more50mm;
	private double more6_3mm;
	private double more0_15mm;
	
	public PlantBoundaries(String limit, double fe, double sio2, double al2o3, double mn, double p, double loi,
			double more50mm, double more6_3mm, double more0_15mm) {
		this.limit = limit;
		this.fe = fe;
		this.sio2 = sio2;
		this.al2o3 = al2o3;
		this.mn = mn;
		this.p = p;
		this.loi = loi;
		this.more50mm = more50mm;
		this.more6_3mm = more6_3mm;
		this.more0_15mm = more0_15mm;
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

	public double getLoi() {
		return loi;
	}

	public void setLoi(double loi) {
		this.loi = loi;
	}

	public double getMore50mm() {
		return more50mm;
	}

	public void setMore50mm(double more50mm) {
		this.more50mm = more50mm;
	}

	public double getMore6_3mm() {
		return more6_3mm;
	}

	public void setMore6_3mm(double more6_3mm) {
		this.more6_3mm = more6_3mm;
	}

	public double getMore0_15mm() {
		return more0_15mm;
	}

	public void setMore0_15mm(double more0_15mm) {
		this.more0_15mm = more0_15mm;
	}
}

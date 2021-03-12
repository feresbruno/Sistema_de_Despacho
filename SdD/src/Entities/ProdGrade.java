package Entities;

public class ProdGrade {
	
	private String fe;
	private String sio2;
	private String al2o3;
	private String mn;
	private String p;
	private String loi;
	private String more50mm;
	private String more6_3mm;
	private String more0_15mm;
	
	public ProdGrade(String fe, String sio2, String al2o3, String mn, String p, String loi, String more50mm,
			String more6_3mm, String more0_15mm) {
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

	public String getFe() {
		return fe;
	}

	public void setFe(String fe) {
		this.fe = fe;
	}

	public String getSio2() {
		return sio2;
	}

	public void setSio2(String sio2) {
		this.sio2 = sio2;
	}

	public String getAl2o3() {
		return al2o3;
	}

	public void setAl2o3(String al2o3) {
		this.al2o3 = al2o3;
	}

	public String getMn() {
		return mn;
	}

	public void setMn(String mn) {
		this.mn = mn;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getLoi() {
		return loi;
	}

	public void setLoi(String loi) {
		this.loi = loi;
	}

	public String getMore50mm() {
		return more50mm;
	}

	public void setMore50mm(String more50mm) {
		this.more50mm = more50mm;
	}

	public String getMore6_3mm() {
		return more6_3mm;
	}

	public void setMore6_3mm(String more6_3mm) {
		this.more6_3mm = more6_3mm;
	}

	public String getMore0_15mm() {
		return more0_15mm;
	}

	public void setMore0_15mm(String more0_15mm) {
		this.more0_15mm = more0_15mm;
	}
}

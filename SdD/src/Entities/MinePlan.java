package Entities;

public class MinePlan  {
	
	private String face;
	private int T1;
	private int T2;
	private int T3;
	private int T4;
	private int T5;
	private int T6;
	private int T7;
	private int T8;
	private int T9;
	private int T10;
	private int T11;
	private int T12;
	
	

	public MinePlan(String face, int t1, int t2, int t3, int t4, int t5, int t6, int t7, int t8, int t9, int t10,
			int t11, int t12) {
		this.face = face;
		T1 = t1;
		T2 = t2;
		T3 = t3;
		T4 = t4;
		T5 = t5;
		T6 = t6;
		T7 = t7;
		T8 = t8;
		T9 = t9;
		T10 = t10;
		T11 = t11;
		T12 = t12;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public int getT1() {
		return T1;
	}

	public void setT1(int t1) {
		T1 = t1;
	}

	public int getT2() {
		return T2;
	}

	public void setT2(int t2) {
		T2 = t2;
	}

	public int getT3() {
		return T3;
	}

	public void setT3(int t3) {
		T3 = t3;
	}

	public int getT4() {
		return T4;
	}

	public void setT4(int t4) {
		T4 = t4;
	}

	public int getT5() {
		return T5;
	}

	public void setT5(int t5) {
		T5 = t5;
	}

	public int getT6() {
		return T6;
	}

	public void setT6(int t6) {
		T6 = t6;
	}

	public int getT7() {
		return T7;
	}

	public void setT7(int t7) {
		T7 = t7;
	}

	public int getT8() {
		return T8;
	}

	public void setT8(int t8) {
		T8 = t8;
	}

	public int getT9() {
		return T9;
	}

	public void setT9(int t9) {
		T9 = t9;
	}

	public int getT10() {
		return T10;
	}

	public void setT10(int t10) {
		T10 = t10;
	}

	public int getT11() {
		return T11;
	}

	public void setT11(int t11) {
		T11 = t11;
	}

	public int getT12() {
		return T12;
	}

	public void setT12(int t12) {
		T12 = t12;
	}

	@Override
	public String toString() {
		return "MinePlan [face=" + face + ", T1=" + T1 + ", T2=" + T2 + ", T3=" + T3 + ", T4=" + T4 + ", T5=" + T5
				+ ", T6=" + T6 + ", T7=" + T7 + ", T8=" + T8 + ", T9=" + T9 + ", T10=" + T10 + ", T11=" + T11 + ", T12="
				+ T12 + "]";
	}

	
}

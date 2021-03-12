package Entities;

public class Travels {
	
	private int truck;
	private String date;
	private String face;
	
	public Travels(int truck, String date, String face) {
		this.truck = truck;
		this.date = date;
		this.face = face;
	}

	public int getTruck() {
		return truck;
	}

	public void setTruck(int truck) {
		this.truck = truck;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}
	
	
	
	
}

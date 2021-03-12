package Entities;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Report {
	
	private List<MinePlan> listRepPlan;
	
	private List<MinePlan> listRepActual;
	
	private String RepDate;
	
	private List<Travels> listRepTravels;
	
	private Double[] VectRepProdPerFace;
	
	private Double RepProdOre;
	
	private Double RepProdWaste;
	
	private Double RepProdTotal;
	
	private Double RepREM;
	
	private List<ProdGrade> listRepProdGrade;
	
	private Integer[] VectRepTravelsPerTruck;
	
	DecimalFormat formato = new DecimalFormat("#,##");      
	

	public Report(List<MinePlan> listRepPlan, List<MinePlan> listRepActual, String repDate,
			List<Travels> listRepTravels, Double[] vectRepProdPerFace, Double repProdOre, Double repProdWaste,
			Double repProdTotal, Double repREM, List<ProdGrade> listRepProdGrade, Integer[] vectRepTravelsPerTruck) {
		this.listRepPlan = listRepPlan;
		this.listRepActual = listRepActual;
		RepDate = repDate;
		this.listRepTravels = listRepTravels;
		VectRepProdPerFace = vectRepProdPerFace;
		RepProdOre = repProdOre;
		RepProdWaste = repProdWaste;
		RepProdTotal = repProdTotal;
		RepREM = repREM;
		this.listRepProdGrade = listRepProdGrade;
		VectRepTravelsPerTruck = vectRepTravelsPerTruck;
	}

	public List<MinePlan> getListRepPlan() {
		return listRepPlan;
	}

	public void setListRepPlan(List<MinePlan> listRepPlan) {
		this.listRepPlan = listRepPlan;
	}

	public List<MinePlan> getListRepActual() {
		return listRepActual;
	}

	public void setListRepActual(List<MinePlan> listRepActual) {
		this.listRepActual = listRepActual;
	}

	public String getRepDate() {
		return RepDate;
	}

	public void setRepDate(String repDate) {
		RepDate = repDate;
	}

	public List<Travels> getListRepTravels() {
		return listRepTravels;
	}

	public void setListRepTravels(List<Travels> listRepTravels) {
		this.listRepTravels = listRepTravels;
	}

	public Double[] getVectRepProdPerFace() {
		return VectRepProdPerFace;
	}

	public void setVectRepProdPerFace(Double[] vectRepProdPerFace) {
		VectRepProdPerFace = vectRepProdPerFace;
	}

	public Double getRepProdOre() {
		return RepProdOre;
	}

	public void setRepProdOre(Double repProdOre) {
		RepProdOre = repProdOre;
	}

	public Double getRepProdWaste() {
		return RepProdWaste;
	}

	public void setRepProdWaste(Double repProdWaste) {
		RepProdWaste = repProdWaste;
	}

	public Double getRepProdTotal() {
		return RepProdTotal;
	}

	public void setRepProdTotal(Double repProdTotal) {
		RepProdTotal = repProdTotal;
	}

	public Double getRepREM() {
		return RepREM;
	}

	public void setRepREM(Double repREM) {
		RepREM = repREM;
	}

	public List<ProdGrade> getListRepProdGrade() {
		return listRepProdGrade;
	}

	public void setListRepProdGrade(List<ProdGrade> listRepProdGrade) {
		this.listRepProdGrade = listRepProdGrade;
	}

	public Integer[] getVectRepTravelsPerTruck() {
		return VectRepTravelsPerTruck;
	}

	public void setVectRepTravelsPerTruck(Integer[] vectRepTravelsPerTruck) {
		VectRepTravelsPerTruck = vectRepTravelsPerTruck;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Shift report - " + RepDate + "\n");
		sb.append("-------------------------\n");
		sb.append("\n");
		sb.append("Mine Plan\n");
		for (MinePlan c : listRepPlan) {
			sb.append(c + "\n");
		}
		sb.append("\n");
		sb.append("Realized Mine Plan\n");
		for (MinePlan c : listRepActual) {
			sb.append(c + "\n");
		}
		sb.append("\n");
		sb.append("Truck Trips\n[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]\n");
		sb.append(Arrays.toString(VectRepTravelsPerTruck));
		sb.append("\n");
		sb.append("\n");
		sb.append("Travel List\n");
		for (Travels c : listRepTravels) {
			sb.append("T" + c.getTruck() + " -> " + c.getFace() + " at " + c.getDate() + "\n");
		}
		sb.append("\n");
		sb.append("Faces Productions \n[O1, O2, O3, O4, O5, W1, W2]\n");
		sb.append(Arrays.toString(VectRepProdPerFace));
		sb.append("\n");
		sb.append("Ore Production: " + RepProdOre + "\n");
		sb.append("Waste Production: " + RepProdWaste + "\n");
		sb.append("Total Production: " + RepProdTotal + "\n");
		sb.append("REM: " + Double.valueOf(formato.format(RepREM)) + "\n");
		sb.append("\n");
		sb.append("Mixture Grade\n");
		for (ProdGrade c : listRepProdGrade) {
			sb.append("%Fe: " + c.getFe() + "; %SiO2: " + c.getSio2() + "; %Al2O3: " + c.getAl2o3() + "; %Mn: " + c.getMn() + "; %P: " + c.getP() + "; %LOI: " + c.getLoi() + "; %+50,00mm: " + c.getMore50mm() + "; %+6,30mm: " + c.getMore6_3mm() + "; %+0,15mm: " + c.getMore0_15mm());
		}
		return sb.toString(); 
	}
}

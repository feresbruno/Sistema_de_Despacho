package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Entities.FaceGrades;
import Entities.MinePlan;
import Entities.PlantBoundaries;
import Entities.ProdGrade;
import Entities.Report;
import Entities.Travels;
import Entities.TruckCapacity;
import gui.util.Alerts;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainViewController implements Initializable {

	@FXML
	private GridPane GRIDP;

	@FXML
	private Button btImporMinePlan;

	@FXML
	private Button btImporTrucksCapacity;

	@FXML
	private Button btImporFaceGrades;

	@FXML
	private Button btImporPlantBoundaries;

	@FXML
	private ComboBox<String> InitialFaceT1;

	@FXML
	private ComboBox<String> InitialFaceT2;

	@FXML
	private ComboBox<String> InitialFaceT3;

	@FXML
	private ComboBox<String> InitialFaceT4;

	@FXML
	private ComboBox<String> InitialFaceT5;

	@FXML
	private ComboBox<String> InitialFaceT6;

	@FXML
	private ComboBox<String> InitialFaceT7;

	@FXML
	private ComboBox<String> InitialFaceT8;

	@FXML
	private ComboBox<String> InitialFaceT9;

	@FXML
	private ComboBox<String> InitialFaceT10;

	@FXML
	private ComboBox<String> InitialFaceT11;

	@FXML
	private ComboBox<String> InitialFaceT12;

	@FXML
	private MenuItem menuItemReinit;

	@FXML
	private MenuItem menuItemExit;

	@FXML
	private MenuItem menuItemImportFaceGrades;

	@FXML
	private MenuItem menuImportPlantBoundaries;

	@FXML
	private MenuItem menuItemImportTrucksCapacity;

	@FXML
	private MenuItem menuItemImportMinePlan;

	@FXML
	private MenuItem menuItemExportShiftReport;

	@FXML
	private MenuItem menuItemAbout;

	@FXML
	private TableView<MinePlan> tableViewMinePlans;

	@FXML
	private TableColumn<MinePlan, String> tableColumnFace;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnT1;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnT2;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnT3;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnT4;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnT5;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnT6;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnT7;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnT8;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnT9;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnT10;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnT11;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnT12;

	private ObservableList<MinePlan> obsListMinePlan;

	private ObservableList<MinePlan> obsListActual;

	private ObservableList<ProdGrade> obsProdGrade;

	List<MinePlan> listMinePlan = new ArrayList<>();

	List<String> listOperativeTruck = new ArrayList<>();

	List<ProdGrade> listProdGrade = new ArrayList<>();

	List<TruckCapacity> listTC = new ArrayList<>();

	List<PlantBoundaries> listPB = new ArrayList<>();

	List<FaceGrades> listFG = new ArrayList<>();
	
	List<FaceGrades> listFG1 = new ArrayList<>();

	Double[] UB = new Double[9];
	
	Double[] LB = new Double[9];
	
	String[] Faces = new String[7];
	
	Double[] ProdGrade = new Double[9];
	
	Double[][] MatG = new Double[5][9];
	
	Integer[][] MatP = new Integer[7][12];
	
	Integer[][] MatA = new Integer[7][12];
	
	Double[] VectPlan = new Double[7];
	
	Double[] VectProd = new Double[7];
	
	Double[] VectDev = new Double[7];
	
	Double[] VectDevX = new Double[7];
	
	Double[] VectCapT = new Double[12];
	
	Integer[] VectFT = new Integer[7];
	
	//Report
	
	List<MinePlan> listRepPlan = new ArrayList<>();
	
	List<MinePlan> listRepActual = new ArrayList<>();
	
	Long RepDate = null;
	
	List<Travels> listRepTravels = new ArrayList<>();
	
	Double[] VectRepProdPerFace = new Double[7];
	
	Double RepProdOre = null;
	
	Double RepProdWaste = null;
	
	Double RepProdTotal = null;
	
	Double RepREM = null;
	
	List<ProdGrade> listRepProdGrade = new ArrayList<>();
	
	Integer[] VectRepTravelsPerTruck = new Integer[12];
	
	@FXML
	public void onMenuItemImportMinePlanAction() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File Dialog");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt", "txt"));

		File file = fileChooser.showOpenDialog(null);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();

			List<MinePlan> list = new ArrayList<>();
			list.clear();
			listMinePlan.clear();

			int k = 0;
			while (line != null) {
				String[] vect = line.split(",");

				String face = vect[0];
				int t1 = Integer.parseInt(vect[1]);
				int t2 = Integer.parseInt(vect[2]);
				int t3 = Integer.parseInt(vect[3]);
				int t4 = Integer.parseInt(vect[4]);
				int t5 = Integer.parseInt(vect[5]);
				int t6 = Integer.parseInt(vect[6]);
				int t7 = Integer.parseInt(vect[7]);
				int t8 = Integer.parseInt(vect[8]);
				int t9 = Integer.parseInt(vect[9]);
				int t10 = Integer.parseInt(vect[10]);
				int t11 = Integer.parseInt(vect[11]);
				int t12 = Integer.parseInt(vect[12]);
				Faces[k] = vect[0];
				k++;

				list.add(new MinePlan(face, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12));
				listMinePlan.add(new MinePlan(face, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12));
				line = br.readLine();
			}
			obsListMinePlan = FXCollections.observableArrayList(list);
			tableViewMinePlans.setItems(obsListMinePlan);

			boolean var = true;
			int i = 0;

			boolean[] faces = new boolean[7];

			for (MinePlan m : listMinePlan) {
				if (m.getT1() + m.getT2() + m.getT3() + m.getT4() + m.getT5() + m.getT6() + m.getT7() + m.getT8()
						+ m.getT9() + m.getT10() + m.getT11() + m.getT12() != 0) {
					var = true;
				} else {
					var = false;
				}
				faces[i] = var;
				i++;
			}

			if (faces[0] == false) {
				PlanProdO1.setDisable(true);
				ActualProdO1.setDisable(true);
				DevProdO1.setDisable(true);
			}

			if (faces[1] == false) {
				PlanProdO2.setDisable(true);
				ActualProdO2.setDisable(true);
				DevProdO2.setDisable(true);
			}

			if (faces[2] == false) {
				PlanProdO3.setDisable(true);
				ActualProdO3.setDisable(true);
				DevProdO3.setDisable(true);
			}

			if (faces[3] == false) {
				PlanProdO4.setDisable(true);
				ActualProdO4.setDisable(true);
				DevProdO4.setDisable(true);
			}

			if (faces[4] == false) {
				PlanProdO5.setDisable(true);
				ActualProdO5.setDisable(true);
				DevProdO5.setDisable(true);
			}

			if (faces[5] == false) {
				PlanProdW1.setDisable(true);
				ActualProdW1.setDisable(true);
				DevProdW1.setDisable(true);
			}

			if (faces[6] == false) {
				PlanProdW2.setDisable(true);
				ActualProdW2.setDisable(true);
				DevProdW2.setDisable(true);
			}

			int somaT1 = 0;
			for (MinePlan m : listMinePlan) {
				somaT1 += m.getT1();
			}
			if (somaT1 == 0) {
				CBOPT1.setSelected(true);
				CBOPT1.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
				CBOPT1.setDisable(true);
				CBOPT1.setOpacity(10);
				InitialFaceT1.setDisable(true);
				CBLRT1.setDisable(true);
				CBLT1.setDisable(true);
				CBFHT1.setDisable(true);
				CBCRT1.setDisable(true);
				CBCDT1.setDisable(true);
				CBWRT1.setDisable(true);
				CBWDT1.setDisable(true);
				CBEHT1.setDisable(true);
				CBSRT1.setDisable(true);
				CBST1.setDisable(true);

			} else {
				CBOPT1.setStyle("-fx-body-color: GREEN");
				CBOPT1.setDisable(true);
				CBOPT1.setOpacity(10);
				listOperativeTruck.add("1");

			}

			int somaT2 = 0;
			for (MinePlan m : listMinePlan) {
				somaT2 += m.getT2();
			}
			if (somaT2 == 0) {
				CBOPT2.setSelected(true);
				CBOPT2.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
				CBOPT2.setDisable(true);
				CBOPT2.setOpacity(10);
				InitialFaceT2.setDisable(true);
				CBLRT2.setDisable(true);
				CBLT2.setDisable(true);
				CBFHT2.setDisable(true);
				CBCRT2.setDisable(true);
				CBCDT2.setDisable(true);
				CBWRT2.setDisable(true);
				CBWDT2.setDisable(true);
				CBEHT2.setDisable(true);
				CBSRT2.setDisable(true);
				CBST2.setDisable(true);

			} else {
				CBOPT2.setStyle("-fx-body-color: GREEN");
				CBOPT2.setDisable(true);
				CBOPT2.setOpacity(10);
				listOperativeTruck.add("2");

			}

			int somaT3 = 0;
			for (MinePlan m : listMinePlan) {
				somaT3 += m.getT3();
			}
			if (somaT3 == 0) {
				CBOPT3.setSelected(true);
				CBOPT3.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
				CBOPT3.setDisable(true);
				CBOPT3.setOpacity(10);
				InitialFaceT3.setDisable(true);
				CBLRT3.setDisable(true);
				CBLT3.setDisable(true);
				CBFHT3.setDisable(true);
				CBCRT3.setDisable(true);
				CBCDT3.setDisable(true);
				CBWRT3.setDisable(true);
				CBWDT3.setDisable(true);
				CBEHT3.setDisable(true);
				CBSRT3.setDisable(true);
				CBST3.setDisable(true);

			} else {
				CBOPT3.setStyle("-fx-body-color: GREEN");
				CBOPT3.setDisable(true);
				CBOPT3.setOpacity(10);
				listOperativeTruck.add("3");

			}

			int somaT4 = 0;
			for (MinePlan m : listMinePlan) {
				somaT4 += m.getT4();
			}
			if (somaT4 == 0) {
				CBOPT4.setSelected(true);
				CBOPT4.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
				CBOPT4.setDisable(true);
				CBOPT4.setOpacity(10);
				InitialFaceT4.setDisable(true);
				CBLRT4.setDisable(true);
				CBLT4.setDisable(true);
				CBFHT4.setDisable(true);
				CBCRT4.setDisable(true);
				CBCDT4.setDisable(true);
				CBWRT4.setDisable(true);
				CBWDT4.setDisable(true);
				CBEHT4.setDisable(true);
				CBSRT4.setDisable(true);
				CBST4.setDisable(true);

			} else {
				CBOPT4.setStyle("-fx-body-color: GREEN");
				CBOPT4.setDisable(true);
				CBOPT4.setOpacity(10);
				listOperativeTruck.add("4");

			}

			int somaT5 = 0;
			for (MinePlan m : listMinePlan) {
				somaT5 += m.getT5();
			}
			if (somaT5 == 0) {
				CBOPT5.setSelected(true);
				CBOPT5.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
				CBOPT5.setDisable(true);
				CBOPT5.setOpacity(10);
				InitialFaceT5.setDisable(true);
				CBLRT5.setDisable(true);
				CBLT5.setDisable(true);
				CBFHT5.setDisable(true);
				CBCRT5.setDisable(true);
				CBCDT5.setDisable(true);
				CBWRT5.setDisable(true);
				CBWDT5.setDisable(true);
				CBEHT5.setDisable(true);
				CBSRT5.setDisable(true);
				CBST5.setDisable(true);

			} else {
				CBOPT5.setStyle("-fx-body-color: GREEN");
				CBOPT5.setDisable(true);
				CBOPT5.setOpacity(10);
				listOperativeTruck.add("5");

			}

			int somaT6 = 0;
			for (MinePlan m : listMinePlan) {
				somaT6 += m.getT6();
			}
			if (somaT6 == 0) {
				CBOPT6.setSelected(true);
				CBOPT6.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
				CBOPT6.setDisable(true);
				CBOPT6.setOpacity(10);
				InitialFaceT6.setDisable(true);
				CBLRT6.setDisable(true);
				CBLT6.setDisable(true);
				CBFHT6.setDisable(true);
				CBCRT6.setDisable(true);
				CBCDT6.setDisable(true);
				CBWRT6.setDisable(true);
				CBWDT6.setDisable(true);
				CBEHT6.setDisable(true);
				CBSRT6.setDisable(true);
				CBST6.setDisable(true);

			} else {
				CBOPT6.setStyle("-fx-body-color: GREEN");
				CBOPT6.setDisable(true);
				CBOPT6.setOpacity(10);
				listOperativeTruck.add("6");

			}

			int somaT7 = 0;
			for (MinePlan m : listMinePlan) {
				somaT7 += m.getT7();
			}
			if (somaT7 == 0) {
				CBOPT7.setSelected(true);
				CBOPT7.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
				CBOPT7.setDisable(true);
				CBOPT7.setOpacity(10);
				InitialFaceT7.setDisable(true);
				CBLRT7.setDisable(true);
				CBLT7.setDisable(true);
				CBFHT7.setDisable(true);
				CBCRT7.setDisable(true);
				CBCDT7.setDisable(true);
				CBWRT7.setDisable(true);
				CBWDT7.setDisable(true);
				CBEHT7.setDisable(true);
				CBSRT7.setDisable(true);
				CBST7.setDisable(true);

			} else {
				CBOPT7.setStyle("-fx-body-color: GREEN");
				CBOPT7.setDisable(true);
				CBOPT7.setOpacity(10);
				listOperativeTruck.add("7");

			}

			int somaT8 = 0;
			for (MinePlan m : listMinePlan) {
				somaT8 += m.getT8();
			}
			if (somaT8 == 0) {
				CBOPT8.setSelected(true);
				CBOPT8.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
				CBOPT8.setDisable(true);
				CBOPT8.setOpacity(10);
				InitialFaceT8.setDisable(true);
				CBLRT8.setDisable(true);
				CBLT8.setDisable(true);
				CBFHT8.setDisable(true);
				CBCRT8.setDisable(true);
				CBCDT8.setDisable(true);
				CBWRT8.setDisable(true);
				CBWDT8.setDisable(true);
				CBEHT8.setDisable(true);
				CBSRT8.setDisable(true);
				CBST8.setDisable(true);

			} else {
				CBOPT8.setStyle("-fx-body-color: GREEN");
				CBOPT8.setDisable(true);
				CBOPT8.setOpacity(10);
				listOperativeTruck.add("8");

			}

			int somaT9 = 0;
			for (MinePlan m : listMinePlan) {
				somaT9 += m.getT9();
			}
			if (somaT9 == 0) {
				CBOPT9.setSelected(true);
				CBOPT9.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
				CBOPT9.setDisable(true);
				CBOPT9.setOpacity(10);
				InitialFaceT9.setDisable(true);
				CBLRT9.setDisable(true);
				CBLT9.setDisable(true);
				CBFHT9.setDisable(true);
				CBCRT9.setDisable(true);
				CBCDT9.setDisable(true);
				CBWRT9.setDisable(true);
				CBWDT9.setDisable(true);
				CBEHT9.setDisable(true);
				CBSRT9.setDisable(true);
				CBST9.setDisable(true);

			} else {
				CBOPT9.setStyle("-fx-body-color: GREEN");
				CBOPT9.setDisable(true);
				CBOPT9.setOpacity(10);
				listOperativeTruck.add("9");

			}

			int somaT10 = 0;
			for (MinePlan m : listMinePlan) {
				somaT10 += m.getT10();
			}
			if (somaT10 == 0) {
				CBOPT10.setSelected(true);
				CBOPT10.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
				CBOPT10.setDisable(true);
				CBOPT10.setOpacity(10);
				InitialFaceT10.setDisable(true);
				CBLRT10.setDisable(true);
				CBLT10.setDisable(true);
				CBFHT10.setDisable(true);
				CBCRT10.setDisable(true);
				CBCDT10.setDisable(true);
				CBWRT10.setDisable(true);
				CBWDT10.setDisable(true);
				CBEHT10.setDisable(true);
				CBSRT10.setDisable(true);
				CBST10.setDisable(true);

			} else {
				CBOPT10.setStyle("-fx-body-color: GREEN");
				CBOPT10.setDisable(true);
				CBOPT10.setOpacity(10);
				listOperativeTruck.add("10");

			}

			int somaT11 = 0;
			for (MinePlan m : listMinePlan) {
				somaT11 += m.getT11();
			}
			if (somaT11 == 0) {
				CBOPT11.setSelected(true);
				CBOPT11.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
				CBOPT11.setDisable(true);
				CBOPT11.setOpacity(10);
				InitialFaceT11.setDisable(true);
				CBLRT11.setDisable(true);
				CBLT11.setDisable(true);
				CBFHT11.setDisable(true);
				CBCRT11.setDisable(true);
				CBCDT11.setDisable(true);
				CBWRT11.setDisable(true);
				CBWDT11.setDisable(true);
				CBEHT11.setDisable(true);
				CBSRT11.setDisable(true);
				CBST11.setDisable(true);

			} else {
				CBOPT11.setStyle("-fx-body-color: GREEN");
				CBOPT11.setDisable(true);
				CBOPT11.setOpacity(10);
				listOperativeTruck.add("11");

			}

			int somaT12 = 0;
			for (MinePlan m : listMinePlan) {
				somaT12 += m.getT12();
			}
			if (somaT12 == 0) {
				CBOPT12.setSelected(true);
				CBOPT12.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
				CBOPT12.setDisable(true);
				CBOPT12.setOpacity(10);
				InitialFaceT12.setDisable(true);
				CBLRT12.setDisable(true);
				CBLT12.setDisable(true);
				CBFHT12.setDisable(true);
				CBCRT12.setDisable(true);
				CBCDT12.setDisable(true);
				CBWRT12.setDisable(true);
				CBWDT12.setDisable(true);
				CBEHT12.setDisable(true);
				CBSRT12.setDisable(true);
				CBST12.setDisable(true);

			} else {
				CBOPT12.setStyle("-fx-body-color: GREEN");
				CBOPT12.setDisable(true);
				CBOPT12.setOpacity(10);
				listOperativeTruck.add("12");

			}

			ObservableList<String> obsCombo;
			List<String> initialFaces = new ArrayList<>();
			for (MinePlan m : listMinePlan) {
				if (m.getT1() + m.getT2() + m.getT3() + m.getT4() + m.getT5() + m.getT6() + m.getT7() + m.getT8()
						+ m.getT9() + m.getT10() + m.getT11() + m.getT12() != 0) {
					initialFaces.add(m.getFace());
				}
			}

			obsCombo = FXCollections.observableArrayList(initialFaces);
			InitialFaceT1.setItems(obsCombo);
			InitialFaceT2.setItems(obsCombo);
			InitialFaceT3.setItems(obsCombo);
			InitialFaceT4.setItems(obsCombo);
			InitialFaceT5.setItems(obsCombo);
			InitialFaceT6.setItems(obsCombo);
			InitialFaceT7.setItems(obsCombo);
			InitialFaceT8.setItems(obsCombo);
			InitialFaceT9.setItems(obsCombo);
			InitialFaceT10.setItems(obsCombo);
			InitialFaceT11.setItems(obsCombo);
			InitialFaceT12.setItems(obsCombo);

			GRIDP.setDisable(false);
			btAllocate.setDisable(false);

			menuItemImportMinePlan.setDisable(true);

			int l = 0;
			for (MinePlan m : listMinePlan) {
				for (int j = 0; j < 11; j++) {
					MatP[l][0] = m.getT1();
					MatP[l][1] = m.getT2();
					MatP[l][2] = m.getT3();
					MatP[l][3] = m.getT4();
					MatP[l][4] = m.getT5();
					MatP[l][5] = m.getT6();
					MatP[l][6] = m.getT7();
					MatP[l][7] = m.getT8();
					MatP[l][8] = m.getT9();
					MatP[l][9] = m.getT10();
					MatP[l][10] = m.getT11();
					MatP[l][11] = m.getT12();
				}
				l += 1;
			}

			for (int i1 = 0; i1 <= 6; i1++) {
				for (int j = 0; j <= 11; j++) {
					MatA[i1][j] = 0;
				}
			}

			List<MinePlan> listActual = new ArrayList<>();

			for (int i1 = 0; i1 < 7; i1++) {
				int k1 = MatA[i1][0];
				int k2 = MatA[i1][1];
				int k3 = MatA[i1][2];
				int k4 = MatA[i1][3];
				int k5 = MatA[i1][4];
				int k6 = MatA[i1][5];
				int k7 = MatA[i1][6];
				int k8 = MatA[i1][7];
				int k9 = MatA[i1][8];
				int k10 = MatA[i1][9];
				int k11 = MatA[i1][10];
				int k12 = MatA[i1][11];
				listActual.add(new MinePlan(Faces[i1], k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11, k12));
			}

			obsListActual = FXCollections.observableArrayList(listActual);
			tableViewActual.setItems(obsListActual);

		} catch (RuntimeException e) {
			Alerts.showAlert("File Reading Error", "Check the file!", AlertType.ERROR);
		}

	}

	private void initializeNodes() {
		tableColumnFace.setCellValueFactory(new PropertyValueFactory<MinePlan, String>("Face"));
		tableColumnT1.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("T1"));
		tableColumnT2.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("T2"));
		tableColumnT3.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("T3"));
		tableColumnT4.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("T4"));
		tableColumnT5.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("T5"));
		tableColumnT6.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("T6"));
		tableColumnT7.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("T7"));
		tableColumnT8.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("T8"));
		tableColumnT9.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("T9"));
		tableColumnT10.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("T10"));
		tableColumnT11.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("T11"));
		tableColumnT12.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("T12"));
		tableColumnt1.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT1"));
		tableColumnt2.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT2"));
		tableColumnt3.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT3"));
		tableColumnt4.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT4"));
		tableColumnt5.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT5"));
		tableColumnt6.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT6"));
		tableColumnt7.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT7"));
		tableColumnt8.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT8"));
		tableColumnt9.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT9"));
		tableColumnt10.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT10"));
		tableColumnt11.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT11"));
		tableColumnt12.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT12"));
		tableColumnFaceAc.setCellValueFactory(new PropertyValueFactory<MinePlan, String>("face"));
		tableColumnAcT1.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("t1"));
		tableColumnAcT2.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("t2"));
		tableColumnAcT3.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("t3"));
		tableColumnAcT4.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("t4"));
		tableColumnAcT5.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("t5"));
		tableColumnAcT6.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("t6"));
		tableColumnAcT7.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("t7"));
		tableColumnAcT8.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("t8"));
		tableColumnAcT9.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("t9"));
		tableColumnAcT10.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("t10"));
		tableColumnAcT11.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("t11"));
		tableColumnAcT12.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("t12"));
		tableColumnFaceG.setCellValueFactory(new PropertyValueFactory<FaceGrades, String>("face"));
		tableColumnGFe.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("fe"));
		tableColumnGSiO2.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("sio2"));
		tableColumnGAl2O3.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("al2o3"));
		tableColumnGMn.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("mn"));
		tableColumnGP.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("p"));
		tableColumnGLOI.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("loi"));
		tableColumnG50mm.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("more50mm"));
		tableColumnG6_3mm.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("more6_3mm"));
		tableColumnG0_15mm.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("more0_15mm"));
		tableColumnLimits.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, String>("limit"));
		tableColumnBFe.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("fe"));
		tableColumnBSiO2.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("sio2"));
		tableColumnBAl2O3.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("al2o3"));
		tableColumnBMn.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("mn"));
		tableColumnBP.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("p"));
		tableColumnBLOI.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("loi"));
		tableColumnB50mm.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("more50mm"));
		tableColumnB6_3mm.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("more6_3mm"));
		tableColumnB0_15mm.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("more0_15mm"));
		tableColumnPG1.setCellValueFactory(new PropertyValueFactory<ProdGrade, String>("fe"));
		tableColumnPG2.setCellValueFactory(new PropertyValueFactory<ProdGrade, String>("sio2"));
		tableColumnPG3.setCellValueFactory(new PropertyValueFactory<ProdGrade, String>("al2o3"));
		tableColumnPG4.setCellValueFactory(new PropertyValueFactory<ProdGrade, String>("mn"));
		tableColumnPG5.setCellValueFactory(new PropertyValueFactory<ProdGrade, String>("p"));
		tableColumnPG6.setCellValueFactory(new PropertyValueFactory<ProdGrade, String>("loi"));
		tableColumnPG7.setCellValueFactory(new PropertyValueFactory<ProdGrade, String>("more50mm"));
		tableColumnPG8.setCellValueFactory(new PropertyValueFactory<ProdGrade, String>("more6_3mm"));
		tableColumnPG9.setCellValueFactory(new PropertyValueFactory<ProdGrade, String>("more0_15mm"));
	}

	@FXML
	private TableView<TruckCapacity> tableViewTrucksCapacity;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnt1;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnt2;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnt3;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnt4;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnt5;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnt6;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnt7;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnt8;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnt9;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnt10;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnt11;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnt12;

	private ObservableList<TruckCapacity> obsListTruckCapacity;

	List<TruckCapacity> listTruckCapacity = new ArrayList<>();

	TruckCapacity TC = null;

	@FXML
	public void onMenuItemImportTrucksCapacityAction() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File Dialog");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt", "txt"));

		File file = fileChooser.showOpenDialog(null);
		listTC.clear();
		listTruckCapacity.clear();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {
				String[] vect = line.split(",");

				int t1 = Integer.parseInt(vect[0]);
				VectCapT[0] = Double.parseDouble(vect[0]);
				int t2 = Integer.parseInt(vect[1]);
				VectCapT[1] = Double.parseDouble(vect[1]);
				int t3 = Integer.parseInt(vect[2]);
				VectCapT[2] = Double.parseDouble(vect[2]);
				int t4 = Integer.parseInt(vect[3]);
				VectCapT[3] = Double.parseDouble(vect[3]);
				int t5 = Integer.parseInt(vect[4]);
				VectCapT[4] = Double.parseDouble(vect[4]);
				int t6 = Integer.parseInt(vect[5]);
				VectCapT[5] = Double.parseDouble(vect[5]);
				int t7 = Integer.parseInt(vect[6]);
				VectCapT[6] = Double.parseDouble(vect[6]);
				int t8 = Integer.parseInt(vect[7]);
				VectCapT[7] = Double.parseDouble(vect[7]);
				int t9 = Integer.parseInt(vect[8]);
				VectCapT[8] = Double.parseDouble(vect[8]);
				int t10 = Integer.parseInt(vect[9]);
				VectCapT[9] = Double.parseDouble(vect[9]);
				int t11 = Integer.parseInt(vect[10]);
				VectCapT[10] = Double.parseDouble(vect[10]);
				int t12 = Integer.parseInt(vect[11]);
				VectCapT[11] = Double.parseDouble(vect[11]);

				listTruckCapacity.add(new TruckCapacity(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12));
				listTC.add(new TruckCapacity(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12));
				TC = new TruckCapacity(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12);
				line = br.readLine();
			}
			obsListTruckCapacity = FXCollections.observableArrayList(listTC);
			tableViewTrucksCapacity.setItems(obsListTruckCapacity);

			menuItemImportTrucksCapacity.setDisable(true);

		} catch (RuntimeException e) {
			Alerts.showAlert("File Reading Error", "Check the file!", AlertType.ERROR);
		}

	}

	@FXML
	private TextField PlanProdO1;

	@FXML
	private TextField PlanProdO2;

	@FXML
	private TextField PlanProdO3;

	@FXML
	private TextField PlanProdO4;

	@FXML
	private TextField PlanProdO5;

	@FXML
	private TextField PlanProdW1;

	@FXML
	private TextField PlanProdW2;

	@FXML
	private TextField DevProdO1;

	@FXML
	private TextField DevProdO2;

	@FXML
	private TextField DevProdO3;

	@FXML
	private TextField DevProdO4;

	@FXML
	private TextField DevProdO5;

	@FXML
	private TextField DevProdW1;

	@FXML
	private TextField DevProdW2;

	@FXML
	private TextField ActualProdO1;

	@FXML
	private TextField ActualProdO2;

	@FXML
	private TextField ActualProdO3;

	@FXML
	private TextField ActualProdO4;

	@FXML
	private TextField ActualProdO5;

	@FXML
	private TextField ActualProdW1;

	@FXML
	private TextField ActualProdW2;

	@FXML
	private Button btDispatch;

	@FXML
	private TextField Time;

	@FXML
	private TextField Dat;

	@FXML
	void onBtDispatchAction(ActionEvent event) {

		DecimalFormat df = new DecimalFormat("###.0");
		DecimalFormat df1 = new DecimalFormat("#0.00");
		DecimalFormat df2 = new DecimalFormat("#0.000");
		DecimalFormat df3 = new DecimalFormat("#0.0");
		
		listRepPlan = listMinePlan;

		txtAreaDispatch.clear();
		try {
			int numberT = Integer.parseInt(CBDispatch.getValue());
			double menor = 1000000;
			int index = -1;
			int soma = 0;

			for (int i = 0; i < 7; i++) {
				VectFT[i] = MatP[i][numberT - 1];
				soma += VectFT[i];
			}
			if (soma > 0) {

				for (int i = 0; i < 7; i++) {
					if (VectFT[i] != 0) {
						VectDevX[i] = (VectProd[i] + VectCapT[numberT - 1]) / VectPlan[i];
						if (VectDevX[i] < menor) {
							menor = VectDevX[i];
							index = i;
						}
					}
				}
			} else {
				Alerts.showAlert("Attention!",
						"The scheduled trips of this truck have already been made, it was dispatched to the face with the greatest deviation of all.",
						AlertType.INFORMATION);
				for (int i = 0; i < 7; i++) {
					VectDevX[i] = VectProd[i] / VectPlan[i];
					if (VectDevX[i] < menor) {
						menor = VectProd[i] / VectPlan[i];
						index = i;
					}
				}
				menor = (VectProd[index] + VectCapT[numberT - 1]) / VectPlan[index];
			}
			
			VectProd[index] = VectProd[index] + VectCapT[numberT - 1];
			VectDev[index] = (menor - 1) * -100;

			MatP[index][numberT - 1] -= 1;
			MatA[index][numberT - 1] += 1;

			List<MinePlan> listActual = new ArrayList<>();

			for (int i = 0; i < 7; i++) {
				int k1 = MatA[i][0];
				int k2 = MatA[i][1];
				int k3 = MatA[i][2];
				int k4 = MatA[i][3];
				int k5 = MatA[i][4];
				int k6 = MatA[i][5];
				int k7 = MatA[i][6];
				int k8 = MatA[i][7];
				int k9 = MatA[i][8];
				int k10 = MatA[i][9];
				int k11 = MatA[i][10];
				int k12 = MatA[i][11];
				listActual.add(new MinePlan(Faces[i], k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11, k12));
			}

			obsListActual = FXCollections.observableArrayList(listActual);
			tableViewActual.setItems(obsListActual);
			
			VectRepTravelsPerTruck[0] = 0;
			VectRepTravelsPerTruck[1] = 0;
			VectRepTravelsPerTruck[2] = 0;
			VectRepTravelsPerTruck[3] = 0;
			VectRepTravelsPerTruck[4] = 0;
			VectRepTravelsPerTruck[5] = 0;
			VectRepTravelsPerTruck[6] = 0;
			VectRepTravelsPerTruck[7] = 0;
			VectRepTravelsPerTruck[8] = 0;
			VectRepTravelsPerTruck[9] = 0;
			VectRepTravelsPerTruck[10] = 0;
			VectRepTravelsPerTruck[11] = 0;
			
			for (int i = 0; i < 7; i++) {
				VectRepTravelsPerTruck[0] += MatA[i][0];
				VectRepTravelsPerTruck[1] += MatA[i][1];
				VectRepTravelsPerTruck[2] += MatA[i][2];
				VectRepTravelsPerTruck[3] += MatA[i][3];
				VectRepTravelsPerTruck[4] += MatA[i][4];
				VectRepTravelsPerTruck[5] += MatA[i][5];
				VectRepTravelsPerTruck[6] += MatA[i][6];
				VectRepTravelsPerTruck[7] += MatA[i][7];
				VectRepTravelsPerTruck[8] += MatA[i][8];
				VectRepTravelsPerTruck[9] += MatA[i][9];
				VectRepTravelsPerTruck[10] += MatA[i][10];
				VectRepTravelsPerTruck[11] += MatA[i][11];
			}
			
			listRepActual = listActual;
			
			double somaFe = 0;
			double somaSiO2 = 0;
			double somaAl2O3 = 0;
			double somaMn = 0;
			double somaP = 0;
			double somaLOI = 0;
			double soma50 = 0;
			double soma63 = 0;
			double soma015 = 0;
			double soma1 = 0;

			for (int o = 0; o < 5; o++) {
				somaFe += VectProd[o] * MatG[o][0];
				somaSiO2 += VectProd[o] * MatG[o][1];
				somaAl2O3 += VectProd[o] * MatG[o][2];
				somaMn += VectProd[o] * MatG[o][3];
				somaP += VectProd[o] * MatG[o][4];
				somaLOI += VectProd[o] * MatG[o][5];
				soma50 += VectProd[o] * MatG[o][6];
				soma63 += VectProd[o] * MatG[o][7];
				soma015 += VectProd[o] * MatG[o][8];
				soma1 += VectProd[o];
			}

			ProdGrade[0] = somaFe / soma1;
			ProdGrade[1] = somaSiO2 / soma1;
			ProdGrade[2] = somaAl2O3 / soma1;
			ProdGrade[3] = somaMn / soma1;
			ProdGrade[4] = somaP / soma1;
			ProdGrade[5] = somaLOI / soma1;
			ProdGrade[6] = soma50 / soma1;
			ProdGrade[7] = soma63 / soma1;
			ProdGrade[8] = soma015 / soma1;

			listProdGrade.clear();
			listProdGrade
					.add(new ProdGrade(df1.format(ProdGrade[0]), df1.format(ProdGrade[1]), df1.format(ProdGrade[2]),
							df1.format(ProdGrade[3]), df2.format(ProdGrade[4]), df1.format(ProdGrade[5]),
							df1.format(ProdGrade[6]), df1.format(ProdGrade[7]), df1.format(ProdGrade[8])));
			obsProdGrade = FXCollections.observableArrayList(listProdGrade);
			tableViewProdGrade.setItems(obsProdGrade);
			
			listRepProdGrade = listProdGrade;

			if (ProdGrade[0] > UB[0] | ProdGrade[0] < LB[0]) {
				CBFe.setSelected(false);
				CBFe.setStyle("-fx-body-color: red");
				CBFe.setDisable(true);
				CBFe.setOpacity(10);
			} else {
				CBFe.setSelected(true);
				CBFe.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CBFe.setDisable(true);
				CBFe.setOpacity(10);
			}

			if (ProdGrade[1] > UB[1] | ProdGrade[1] < LB[1]) {
				CBSiO2.setSelected(false);
				CBSiO2.setStyle("-fx-body-color: red");
				CBSiO2.setDisable(true);
				CBSiO2.setOpacity(10);
			} else {
				CBSiO2.setSelected(true);
				CBSiO2.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CBSiO2.setDisable(true);
				CBSiO2.setOpacity(10);
			}

			if (ProdGrade[2] > UB[2] | ProdGrade[2] < LB[2]) {
				CBAl2O3.setSelected(false);
				CBAl2O3.setStyle("-fx-body-color: red");
				CBAl2O3.setDisable(true);
				CBAl2O3.setOpacity(10);
			} else {
				CBAl2O3.setSelected(true);
				CBAl2O3.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CBAl2O3.setDisable(true);
				CBAl2O3.setOpacity(10);
			}

			if (ProdGrade[3] > UB[3] | ProdGrade[3] < LB[3]) {
				CBMn.setSelected(false);
				CBMn.setStyle("-fx-body-color: red");
				CBMn.setDisable(true);
				CBMn.setOpacity(10);
			} else {
				CBMn.setSelected(true);
				CBMn.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CBMn.setDisable(true);
				CBMn.setOpacity(10);
			}

			if (ProdGrade[4] > UB[4] | ProdGrade[4] < LB[4]) {
				CBP.setSelected(false);
				CBP.setStyle("-fx-body-color: red");
				CBP.setDisable(true);
				CBP.setOpacity(10);
			} else {
				CBP.setSelected(true);
				CBP.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CBP.setDisable(true);
				CBP.setOpacity(10);
			}

			if (ProdGrade[5] > UB[5] | ProdGrade[5] < LB[5]) {
				CBLOI.setSelected(false);
				CBLOI.setStyle("-fx-body-color: red");
				CBLOI.setDisable(true);
				CBLOI.setOpacity(10);
			} else {
				CBLOI.setSelected(true);
				CBLOI.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CBLOI.setDisable(true);
				CBLOI.setOpacity(10);
			}

			if (ProdGrade[6] > UB[6] | ProdGrade[6] < LB[6]) {
				CB50.setSelected(false);
				CB50.setStyle("-fx-body-color: red");
				CB50.setDisable(true);
				CB50.setOpacity(10);
			} else {
				CB50.setSelected(true);
				CB50.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CB50.setDisable(true);
				CB50.setOpacity(10);
			}

			if (ProdGrade[7] > UB[7] | ProdGrade[7] < LB[7]) {
				CB63.setSelected(false);
				CB63.setStyle("-fx-body-color: red");
				CB63.setDisable(true);
				CB63.setOpacity(10);
			} else {
				CB63.setSelected(true);
				CB63.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CB63.setDisable(true);
				CB63.setOpacity(10);
			}

			if (ProdGrade[8] > UB[8] | ProdGrade[8] < LB[8]) {
				CB015.setSelected(false);
				CB015.setStyle("-fx-body-color: red");
				CB015.setDisable(true);
				CB015.setOpacity(10);
			} else {
				CB015.setSelected(true);
				CB015.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CB015.setDisable(true);
				CB015.setOpacity(10);
			}

			if (PlanProdO1.isDisable() == false) {
				if (VectProd[0] >= VectPlan[0]) {
					CBO1.setSelected(true);
					CBO1.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBO1.setDisable(true);
					CBO1.setOpacity(10);
				} else {
					CBO1.setSelected(false);
					CBO1.setStyle("-fx-body-color: red");
					CBO1.setDisable(true);
					CBO1.setOpacity(10);
				}
			} else {
				CBO1.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}
			
			if (PlanProdO2.isDisable() == false) {
				if (VectProd[1] >= VectPlan[1]) {
					CBO2.setSelected(true);
					CBO2.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBO2.setDisable(true);
					CBO2.setOpacity(10);
				} else {
					CBO2.setSelected(false);
					CBO2.setStyle("-fx-body-color: red");
					CBO2.setDisable(true);
					CBO2.setOpacity(10);
				}
			} else {
				CBO2.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}

			if (PlanProdO3.isDisable() == false) {
				if (VectProd[2] >= VectPlan[2]) {
					CBO3.setSelected(true);
					CBO3.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBO3.setDisable(true);
					CBO3.setOpacity(10);
				} else {
					CBO3.setSelected(false);
					CBO3.setStyle("-fx-body-color: red");
					CBO3.setDisable(true);
					CBO3.setOpacity(10);
				}
			} else {
				CBO3.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}

			if (PlanProdO4.isDisable() == false) {
				if (VectProd[3] >= VectPlan[3]) {
					CBO4.setSelected(true);
					CBO4.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBO4.setDisable(true);
					CBO4.setOpacity(10);
				} else {
					CBO4.setSelected(false);
					CBO4.setStyle("-fx-body-color: red");
					CBO4.setDisable(true);
					CBO4.setOpacity(10);
				}
			} else {
				CBO4.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}

			if (PlanProdO5.isDisable() == false) {
				if (VectProd[4] >= VectPlan[4]) {
					CBO5.setSelected(true);
					CBO5.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBO5.setDisable(true);
					CBO5.setOpacity(10);
				} else {
					CBO5.setSelected(false);
					CBO5.setStyle("-fx-body-color: red");
					CBO5.setDisable(true);
					CBO5.setOpacity(10);
				}
			} else {
				CBO5.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}

			if (PlanProdW1.isDisable() == false) {
				if (VectProd[5] >= VectPlan[5]) {
					CBW1.setSelected(true);
					CBW1.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBW1.setDisable(true);
					CBW1.setOpacity(10);
				} else {
					CBW1.setSelected(false);
					CBW1.setStyle("-fx-body-color: red");
					CBW1.setDisable(true);
					CBW1.setOpacity(10);
				}
			} else {
				CBW1.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}

			if (PlanProdW2.isDisable() == false) {
				if (VectProd[6] >= VectPlan[6]) {
					CBW2.setSelected(true);
					CBW2.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBW2.setDisable(true);
					CBW2.setOpacity(10);
				} else {
					CBW2.setSelected(false);
					CBW2.setStyle("-fx-body-color: red");
					CBW2.setDisable(true);
					CBW2.setOpacity(10);
				}
			} else {
				CBW2.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}
			
			if (ActualProdO1.isDisable() == false) {
				if (VectProd[0] == 0.0) {
					ActualProdO1.setText("0,0");
				} else {
					ActualProdO1.setText(String.valueOf(df.format(VectProd[0])));
				}
			}
			if (ActualProdO2.isDisable() == false) {
				if (VectProd[1] == 0.0) {
					ActualProdO2.setText("0,0");
				} else {
					ActualProdO2.setText(String.valueOf(df.format(VectProd[1])));
				}
			}
			if (ActualProdO3.isDisable() == false) {
				if (VectProd[2] == 0.0) {
					ActualProdO3.setText("0,0");
				} else {
					ActualProdO3.setText(String.valueOf(df.format(VectProd[2])));
				}
			}
			if (ActualProdO4.isDisable() == false) {
				if (VectProd[3] == 0.0) {
					ActualProdO4.setText("0,0");
				} else {
					ActualProdO4.setText(String.valueOf(df.format(VectProd[3])));
				}
			}
			if (ActualProdO5.isDisable() == false) {
				if (VectProd[4] == 0.0) {
					ActualProdO5.setText("0,0");
				} else {
					ActualProdO5.setText(String.valueOf(df.format(VectProd[4])));
				}
			}
			if (ActualProdW1.isDisable() == false) {
				if (VectProd[5] == 0.0) {
					ActualProdW1.setText("0,0");
				} else {
					ActualProdW1.setText(String.valueOf(df.format(VectProd[5])));
				}
			}
			if (ActualProdW2.isDisable() == false) {
				if (VectProd[6] == 0.0) {
					ActualProdW2.setText("0,0");
				} else {
					ActualProdW2.setText(String.valueOf(df.format(VectProd[6])));
				}
			}

			if (DevProdO1.isDisable() == false) {
				if (VectDev[0] == 0.0) {
					DevProdO1.setText("0.0");
				} else {
					DevProdO1.setText(String.valueOf(df.format(VectDev[0])));
				}
			}
			if (DevProdO2.isDisable() == false) {
				if (VectDev[1] == 0.0) {
					DevProdO2.setText("0.0");
				} else {
					DevProdO2.setText(String.valueOf(df.format(VectDev[1])));
				}
			}
			if (DevProdO3.isDisable() == false) {
				if (VectDev[2] == 0.0) {
					DevProdO3.setText("0.0");
				} else {
					DevProdO3.setText(String.valueOf(df.format(VectDev[2])));
				}
			}
			if (DevProdO4.isDisable() == false) {
				if (VectDev[3] == 0.0) {
					DevProdO4.setText("0.0");
				} else {
					DevProdO4.setText(String.valueOf(df.format(VectDev[3])));
				}
			}
			if (DevProdO5.isDisable() == false) {
				if (VectDev[4] == 0.0) {
					DevProdO5.setText("0.0");
				} else {
					DevProdO5.setText(String.valueOf(df.format(VectDev[4])));
				}
			}
			if (DevProdW1.isDisable() == false) {
				if (VectDev[5] == 0.0) {
					DevProdW1.setText("0.0");
				} else {
					DevProdW1.setText(String.valueOf(df.format(VectDev[5])));
				}
			}
			if (DevProdW2.isDisable() == false) {
				if (VectDev[6] == 0.0) {
					DevProdW2.setText("0.0");
				} else {
					DevProdW2.setText(String.valueOf(df.format(VectDev[6])));
				}
			}

			String destiny = null;
			switch (index) {
			case 0:
				destiny = "O1";
				break;
			case 1:
				destiny = "O2";
				break;
			case 2:
				destiny = "O3";
				break;
			case 3:
				destiny = "O4";
				break;
			case 4:
				destiny = "O5";
				break;
			case 5:
				destiny = "W1";
				break;
			case 6:
				destiny = "W2";
				break;
			}

			txtAreaDispatch.setText(destiny);

			switch (numberT) {
			case 1:
				InitialFaceT1.setValue(destiny);
				CBLRT1.setSelected(false);
				CBLT1.setSelected(false);
				CBFHT1.setSelected(false);
				CBCRT1.setSelected(false);
				CBCDT1.setSelected(false);
				CBWRT1.setSelected(false);
				CBWDT1.setSelected(false);
				CBEHT1.setSelected(true);
				CBSRT1.setSelected(false);
				CBST1.setSelected(false);
				break;
			case 2:
				InitialFaceT2.setValue(destiny);
				CBLRT2.setSelected(false);
				CBLT2.setSelected(false);
				CBFHT2.setSelected(false);
				CBCRT2.setSelected(false);
				CBCDT2.setSelected(false);
				CBWRT2.setSelected(false);
				CBWDT2.setSelected(false);
				CBEHT2.setSelected(true);
				CBSRT2.setSelected(false);
				CBST2.setSelected(false);
				break;
			case 3:
				InitialFaceT3.setValue(destiny);
				CBLRT3.setSelected(false);
				CBLT3.setSelected(false);
				CBFHT3.setSelected(false);
				CBCRT3.setSelected(false);
				CBCDT3.setSelected(false);
				CBWRT3.setSelected(false);
				CBWDT3.setSelected(false);
				CBEHT3.setSelected(true);
				CBSRT3.setSelected(false);
				CBST3.setSelected(false);
				break;
			case 4:
				InitialFaceT4.setValue(destiny);
				CBLRT4.setSelected(false);
				CBLT4.setSelected(false);
				CBFHT4.setSelected(false);
				CBCRT4.setSelected(false);
				CBCDT4.setSelected(false);
				CBWRT4.setSelected(false);
				CBWDT4.setSelected(false);
				CBEHT4.setSelected(true);
				CBSRT4.setSelected(false);
				CBST4.setSelected(false);
				break;
			case 5:
				InitialFaceT5.setValue(destiny);
				CBLRT5.setSelected(false);
				CBLT5.setSelected(false);
				CBFHT5.setSelected(false);
				CBCRT5.setSelected(false);
				CBCDT5.setSelected(false);
				CBWRT5.setSelected(false);
				CBWDT5.setSelected(false);
				CBEHT5.setSelected(true);
				CBSRT5.setSelected(false);
				CBST5.setSelected(false);
				break;
			case 6:
				InitialFaceT6.setValue(destiny);
				CBLRT6.setSelected(false);
				CBLT6.setSelected(false);
				CBFHT6.setSelected(false);
				CBCRT6.setSelected(false);
				CBCDT6.setSelected(false);
				CBWRT6.setSelected(false);
				CBWDT6.setSelected(false);
				CBEHT6.setSelected(true);
				CBSRT6.setSelected(false);
				CBST6.setSelected(false);
				break;
			case 7:
				InitialFaceT7.setValue(destiny);
				CBLRT7.setSelected(false);
				CBLT7.setSelected(false);
				CBFHT7.setSelected(false);
				CBCRT7.setSelected(false);
				CBCDT7.setSelected(false);
				CBWRT7.setSelected(false);
				CBWDT7.setSelected(false);
				CBEHT7.setSelected(true);
				CBSRT7.setSelected(false);
				CBST7.setSelected(false);
				break;
			case 8:
				InitialFaceT8.setValue(destiny);
				CBLRT8.setSelected(false);
				CBLT8.setSelected(false);
				CBFHT8.setSelected(false);
				CBCRT8.setSelected(false);
				CBCDT8.setSelected(false);
				CBWRT8.setSelected(false);
				CBWDT8.setSelected(false);
				CBEHT8.setSelected(true);
				CBSRT8.setSelected(false);
				CBST8.setSelected(false);
				break;
			case 9:
				InitialFaceT9.setValue(destiny);
				CBLRT9.setSelected(false);
				CBLT9.setSelected(false);
				CBFHT9.setSelected(false);
				CBCRT9.setSelected(false);
				CBCDT9.setSelected(false);
				CBWRT9.setSelected(false);
				CBWDT9.setSelected(false);
				CBEHT9.setSelected(true);
				CBSRT9.setSelected(false);
				CBST9.setSelected(false);
				break;
			case 10:
				InitialFaceT10.setValue(destiny);
				CBLRT10.setSelected(false);
				CBLT10.setSelected(false);
				CBFHT10.setSelected(false);
				CBCRT10.setSelected(false);
				CBCDT10.setSelected(false);
				CBWRT10.setSelected(false);
				CBWDT10.setSelected(false);
				CBEHT10.setSelected(true);
				CBSRT10.setSelected(false);
				CBST10.setSelected(false);
				break;
			case 11:
				InitialFaceT11.setValue(destiny);
				CBLRT11.setSelected(false);
				CBLT11.setSelected(false);
				CBFHT11.setSelected(false);
				CBCRT11.setSelected(false);
				CBCDT11.setSelected(false);
				CBWRT11.setSelected(false);
				CBWDT11.setSelected(false);
				CBEHT11.setSelected(true);
				CBSRT11.setSelected(false);
				CBST11.setSelected(false);
				break;
			case 12:
				InitialFaceT12.setValue(destiny);
				CBLRT12.setSelected(false);
				CBLT12.setSelected(false);
				CBFHT12.setSelected(false);
				CBCRT12.setSelected(false);
				CBCDT12.setSelected(false);
				CBWRT12.setSelected(false);
				CBWDT12.setSelected(false);
				CBEHT12.setSelected(true);
				CBSRT12.setSelected(false);
				CBST12.setSelected(false);
				break;
			}

			double prodTot = 0;
			double prodOre = 0;
			double prodWaste = 0;
			double rem = 0;

			prodOre = VectProd[0] + VectProd[1] + VectProd[2] + VectProd[3] + VectProd[4];
			prodWaste = VectProd[5] + VectProd[6];
			prodTot = prodOre + prodWaste;
			rem = prodWaste / prodOre;

			if (prodTot == 0.0) {
				ProdTot.setText("0,0");
			} else {
				ProdTot.setText(String.valueOf(df.format(prodTot)));
			}

			if (prodOre == 0.0) {
				ProdOre.setText("0,0");
			} else {
				ProdOre.setText(String.valueOf(df.format(prodOre)));
			}

			if (prodWaste == 0.0) {
				ProdWaste.setText("0,0");
			} else {
				ProdWaste.setText(String.valueOf(df.format(prodWaste)));
			}

			if (rem == 0.0) {
				REM.setText("0,0");
			} else {
				REM.setText(String.valueOf(df3.format(rem)));
			}
			
			VectRepProdPerFace[0] = VectProd[0];
			VectRepProdPerFace[1] = VectProd[1];
			VectRepProdPerFace[2] = VectProd[2];
			VectRepProdPerFace[3] = VectProd[3];
			VectRepProdPerFace[4] = VectProd[4];
			VectRepProdPerFace[5] = VectProd[5];
			VectRepProdPerFace[6] = VectProd[6];
						
			RepProdOre = prodOre;
			
			RepProdWaste = prodWaste;
			
			RepProdTotal = prodTot;
			
			RepREM = rem;
			
			CBDispatch.getSelectionModel().clearSelection();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			RepDate = System.currentTimeMillis();
			String dataFormatada = dateFormat.format(RepDate);
			
			listRepTravels.add(new Travels(numberT, dataFormatada, destiny));
			
			//Report report = new Report(listRepPlan, listRepActual, dataFormatada, listRepTravels,	VectRepProdPerFace, RepProdOre, RepProdWaste, RepProdTotal, RepREM, listRepProdGrade, VectRepTravelsPerTruck); 
			//System.out.println(report);

		} catch (RuntimeException e) {
			Alerts.showAlert("Error", "Select a truck to dispatch", AlertType.ERROR);
			e.printStackTrace();
		}
	}

	@FXML
	private ComboBox<String> CBDispatch;

	@FXML
	private TextField txtAreaDispatch;

	@FXML
	private CheckBox CBFe;

	@FXML
	private CheckBox CBSiO2;

	@FXML
	private CheckBox CBAl2O3;

	@FXML
	private CheckBox CBMn;

	@FXML
	private CheckBox CBP;

	@FXML
	private CheckBox CBLOI;

	@FXML
	private CheckBox CB50;

	@FXML
	private CheckBox CB63;

	@FXML
	private CheckBox CB015;

	@FXML
	private CheckBox CBO1;

	@FXML
	private CheckBox CBO2;

	@FXML
	private CheckBox CBO3;

	@FXML
	private CheckBox CBO4;

	@FXML
	private CheckBox CBO5;

	@FXML
	private CheckBox CBW1;

	@FXML
	private CheckBox CBW2;

	@FXML
	private TableView<ProdGrade> tableViewProdGrade;

	@FXML
	private TableColumn<ProdGrade, String> tableColumnPG1;

	@FXML
	private TableColumn<ProdGrade, String> tableColumnPG2;

	@FXML
	private TableColumn<ProdGrade, String> tableColumnPG3;

	@FXML
	private TableColumn<ProdGrade, String> tableColumnPG4;

	@FXML
	private TableColumn<ProdGrade, String> tableColumnPG5;

	@FXML
	private TableColumn<ProdGrade, String> tableColumnPG6;

	@FXML
	private TableColumn<ProdGrade, String> tableColumnPG7;

	@FXML
	private TableColumn<ProdGrade, String> tableColumnPG8;

	@FXML
	private TableColumn<ProdGrade, String> tableColumnPG9;

	@FXML
	private CheckBox CBDone;

	@FXML
	private TextField ProdTot;

	@FXML
	private TextField ProdOre;

	@FXML
	private TextField ProdWaste;

	@FXML
	private TextField REM;

	@FXML
	private Button btAllocate;

	@FXML
	void onBtAllocateAction(ActionEvent event) {

		List<String> verification = new ArrayList<>();
		for (MinePlan m : listMinePlan) {
			if (m.getT1() + m.getT2() + m.getT3() + m.getT4() + m.getT5() + m.getT6() + m.getT7() + m.getT8()
					+ m.getT9() + m.getT10() + m.getT11() + m.getT12() != 0) {
				verification.add(m.getFace());
			}
		}
		if (listTC.isEmpty() == false & listFG.isEmpty() == false & listMinePlan.isEmpty() == false
				& listPB.isEmpty() == false
				& (((CBOPT1.isSelected() == true | (CBLT1.isSelected() == true | CBLRT1.isSelected() == true
						| CBFHT1.isSelected() == true | CBCDT1.isSelected() == true | CBCRT1.isSelected() == true
						| CBWDT1.isSelected() == true | CBWRT1.isSelected() == true | CBEHT1.isSelected() == true
						| CBST1.isSelected() == true | CBSRT1.isSelected() == true))
						& (CBOPT2.isSelected() == true | (CBLT2.isSelected() == true | CBLRT2.isSelected() == true
								| CBFHT2.isSelected() == true | CBCDT2.isSelected() == true
								| CBCRT2.isSelected() == true | CBWDT2.isSelected() == true
								| CBWRT2.isSelected() == true | CBEHT2.isSelected() == true | CBST2.isSelected() == true
								| CBSRT2.isSelected() == true))
						& (CBOPT3.isSelected() == true | (CBLT3.isSelected() == true | CBLRT3.isSelected() == true
								| CBFHT3.isSelected() == true | CBCDT3.isSelected() == true
								| CBCRT3.isSelected() == true | CBWDT3.isSelected() == true
								| CBWRT3.isSelected() == true | CBEHT3.isSelected() == true | CBST3.isSelected() == true
								| CBSRT3.isSelected() == true))
						& (CBOPT4.isSelected() == true | (CBLT4.isSelected() == true | CBLRT4.isSelected() == true
								| CBFHT4.isSelected() == true | CBCDT4.isSelected() == true
								| CBCRT4.isSelected() == true | CBWDT4.isSelected() == true
								| CBWRT4.isSelected() == true | CBEHT4.isSelected() == true | CBST4.isSelected() == true
								| CBSRT4.isSelected() == true))
						& (CBOPT5.isSelected() == true | (CBLT5.isSelected() == true | CBLRT5.isSelected() == true
								| CBFHT5.isSelected() == true | CBCDT5.isSelected() == true
								| CBCRT5.isSelected() == true | CBWDT5.isSelected() == true
								| CBWRT5.isSelected() == true | CBEHT5.isSelected() == true | CBST5.isSelected() == true
								| CBSRT5.isSelected() == true))
						& (CBOPT6.isSelected() == true | (CBLT6.isSelected() == true | CBLRT6.isSelected() == true
								| CBFHT6.isSelected() == true | CBCDT6.isSelected() == true
								| CBCRT6.isSelected() == true | CBWDT6.isSelected() == true
								| CBWRT6.isSelected() == true | CBEHT6.isSelected() == true | CBST6.isSelected() == true
								| CBSRT6.isSelected() == true))
						& (CBOPT7.isSelected() == true | (CBLT7.isSelected() == true | CBLRT7.isSelected() == true
								| CBFHT7.isSelected() == true | CBCDT7.isSelected() == true
								| CBCRT7.isSelected() == true | CBWDT7.isSelected() == true
								| CBWRT7.isSelected() == true | CBEHT7.isSelected() == true | CBST7.isSelected() == true
								| CBSRT7.isSelected() == true))
						& (CBOPT8.isSelected() == true | (CBLT8.isSelected() == true | CBLRT8.isSelected() == true
								| CBFHT8.isSelected() == true | CBCDT8.isSelected() == true
								| CBCRT8.isSelected() == true | CBWDT8.isSelected() == true
								| CBWRT8.isSelected() == true | CBEHT8.isSelected() == true | CBST8.isSelected() == true
								| CBSRT8.isSelected() == true))
						& (CBOPT9.isSelected() == true | (CBLT9.isSelected() == true | CBLRT9.isSelected() == true
								| CBFHT9.isSelected() == true | CBCDT9.isSelected() == true
								| CBCRT9.isSelected() == true | CBWDT9.isSelected() == true
								| CBWRT9.isSelected() == true | CBEHT9.isSelected() == true | CBST9.isSelected() == true
								| CBSRT9.isSelected() == true))
						& (CBOPT10.isSelected() == true | (CBLT10.isSelected() == true | CBLRT10.isSelected() == true
								| CBFHT10.isSelected() == true | CBCDT10.isSelected() == true
								| CBCRT10.isSelected() == true | CBWDT10.isSelected() == true
								| CBWRT10.isSelected() == true | CBEHT10.isSelected() == true
								| CBST10.isSelected() == true | CBSRT10.isSelected() == true))
						& (CBOPT11.isSelected() == true | (CBLT11.isSelected() == true | CBLRT11.isSelected() == true
								| CBFHT11.isSelected() == true | CBCDT11.isSelected() == true
								| CBCRT11.isSelected() == true | CBWDT11.isSelected() == true
								| CBWRT11.isSelected() == true | CBEHT11.isSelected() == true
								| CBST11.isSelected() == true | CBSRT11.isSelected() == true))
						& (CBOPT12.isSelected() == true | (CBLT12.isSelected() == true | CBLRT12.isSelected() == true
								| CBFHT12.isSelected() == true | CBCDT12.isSelected() == true
								| CBCRT12.isSelected() == true | CBWDT12.isSelected() == true
								| CBWRT12.isSelected() == true | CBEHT12.isSelected() == true
								| CBST12.isSelected() == true | CBSRT12.isSelected() == true))) == true)
				& (((CBOPT1.isSelected() == true | verification.contains(InitialFaceT1.getValue()) == true)
						& (CBOPT2.isSelected() == true | verification.contains(InitialFaceT2.getValue()) == true)
						& (CBOPT3.isSelected() == true | verification.contains(InitialFaceT3.getValue()) == true)
						& (CBOPT4.isSelected() == true | verification.contains(InitialFaceT4.getValue()) == true)
						& (CBOPT5.isSelected() == true | verification.contains(InitialFaceT5.getValue()) == true)
						& (CBOPT6.isSelected() == true | verification.contains(InitialFaceT6.getValue()) == true)
						& (CBOPT7.isSelected() == true | verification.contains(InitialFaceT7.getValue()) == true)
						& (CBOPT8.isSelected() == true | verification.contains(InitialFaceT8.getValue()) == true)
						& (CBOPT9.isSelected() == true | verification.contains(InitialFaceT9.getValue()) == true)
						& (CBOPT10.isSelected() == true | verification.contains(InitialFaceT10.getValue()) == true)
						& (CBOPT11.isSelected() == true | verification.contains(InitialFaceT11.getValue()) == true)
						& (CBOPT12.isSelected() == true
								| verification.contains(InitialFaceT12.getValue()) == true)) == true)) {

			CBDone.setSelected(true);
			CBDone.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
			CBDone.setDisable(true);
			CBDone.setOpacity(10);
			Alerts.showAlert("Initial Location Done", "Initial location successfully done", AlertType.INFORMATION);

			double prod = 0;
			List<Double> list = new ArrayList<>();
			int p = 0;
			for (MinePlan m : listMinePlan) {

				prod = m.getT1() * TC.getCapT1() + m.getT2() * TC.getCapT2() + m.getT3() * TC.getCapT3()
						+ m.getT4() * TC.getCapT4() + m.getT5() * TC.getCapT5() + m.getT6() * TC.getCapT6()
						+ m.getT7() * TC.getCapT7() + m.getT8() * TC.getCapT8() + m.getT9() * TC.getCapT9()
						+ m.getT10() * TC.getCapT10() + m.getT11() * TC.getCapT11() + m.getT12() * TC.getCapT12();
				list.add(prod);
				VectPlan[p] = prod;
				p += 1;
			}

			DecimalFormat df = new DecimalFormat("#,###.0");
			DecimalFormat df1 = new DecimalFormat("#,00.00");
			DecimalFormat df2 = new DecimalFormat("#,0.000");
			DecimalFormat df3 = new DecimalFormat("#,0.0");

			if (PlanProdO1.isDisable() == false) {
				PlanProdO1.setText(String.valueOf(df.format(list.get(0))));
			}
			if (PlanProdO2.isDisable() == false) {
				PlanProdO2.setText(String.valueOf(df.format(list.get(1))));
			}
			if (PlanProdO3.isDisable() == false) {
				PlanProdO3.setText(String.valueOf(df.format(list.get(2))));
			}
			if (PlanProdO4.isDisable() == false) {
				PlanProdO4.setText(String.valueOf(df.format(list.get(3))));
			}
			if (PlanProdO5.isDisable() == false) {
				PlanProdO5.setText(String.valueOf(df.format(list.get(4))));
			}
			if (PlanProdW1.isDisable() == false) {
				PlanProdW1.setText(String.valueOf(df.format(list.get(5))));
			}
			if (PlanProdW2.isDisable() == false) {
				PlanProdW2.setText(String.valueOf(df.format(list.get(6))));
			}

			// Initial Production

			double ip1 = 0.0;
			double ip2 = 0.0;
			double ip3 = 0.0;
			double ip4 = 0.0;
			double ip5 = 0.0;
			double ip6 = 0.0;
			double ip7 = 0.0;

			String[] faces = new String[7];
			int i = 0;
			for (MinePlan m : listMinePlan) {
				faces[i] = m.getFace();
				i += 1;
			}

			if ((CBOPT1.isSelected() == false)
					& (CBLT1.isSelected() == true | CBLRT1.isSelected() == true | CBEHT1.isSelected() == true)) {
				if (InitialFaceT1.getValue() == faces[0]) {
					ip1 += TC.getCapT1();

				} else if ((InitialFaceT1.getValue() == faces[1])) {
					ip2 += TC.getCapT1();

				} else if ((InitialFaceT1.getValue() == faces[2])) {
					ip3 += TC.getCapT1();

				} else if ((InitialFaceT1.getValue() == faces[3])) {
					ip4 += TC.getCapT1();

				} else if ((InitialFaceT1.getValue() == faces[4])) {
					ip5 += TC.getCapT1();

				} else if ((InitialFaceT1.getValue() == faces[5])) {
					ip6 += TC.getCapT1();

				} else if ((InitialFaceT1.getValue() == faces[6])) {
					ip7 += TC.getCapT1();
				}

			}

			if ((CBOPT2.isSelected() == false)
					& (CBLT2.isSelected() == true | CBLRT2.isSelected() == true | CBEHT2.isSelected() == true)) {
				if (InitialFaceT2.getValue() == faces[0]) {
					ip1 += TC.getCapT2();

				} else if ((InitialFaceT2.getValue() == faces[1])) {
					ip2 += TC.getCapT2();

				} else if ((InitialFaceT2.getValue() == faces[2])) {
					ip3 += TC.getCapT2();

				} else if ((InitialFaceT2.getValue() == faces[3])) {
					ip4 += TC.getCapT2();

				} else if ((InitialFaceT2.getValue() == faces[4])) {
					ip5 += TC.getCapT2();

				} else if ((InitialFaceT2.getValue() == faces[5])) {
					ip6 += TC.getCapT2();

				} else if ((InitialFaceT2.getValue() == faces[6])) {
					ip7 += TC.getCapT2();
				}

			}

			if ((CBOPT3.isSelected() == false)
					& (CBLT3.isSelected() == true | CBLRT3.isSelected() == true | CBEHT3.isSelected() == true)) {
				if (InitialFaceT3.getValue() == faces[0]) {
					ip1 += TC.getCapT3();

				} else if ((InitialFaceT3.getValue() == faces[1])) {
					ip2 += TC.getCapT3();

				} else if ((InitialFaceT3.getValue() == faces[2])) {
					ip3 += TC.getCapT3();

				} else if ((InitialFaceT3.getValue() == faces[3])) {
					ip4 += TC.getCapT3();

				} else if ((InitialFaceT3.getValue() == faces[4])) {
					ip5 += TC.getCapT3();

				} else if ((InitialFaceT3.getValue() == faces[5])) {
					ip6 += TC.getCapT3();

				} else if ((InitialFaceT3.getValue() == faces[6])) {
					ip7 += TC.getCapT3();
				}

			}

			if ((CBOPT4.isSelected() == false)
					& (CBLT4.isSelected() == true | CBLRT4.isSelected() == true | CBEHT4.isSelected() == true)) {
				if (InitialFaceT4.getValue() == faces[0]) {
					ip1 += TC.getCapT4();

				} else if ((InitialFaceT4.getValue() == faces[1])) {
					ip2 += TC.getCapT4();

				} else if ((InitialFaceT4.getValue() == faces[2])) {
					ip3 += TC.getCapT4();

				} else if ((InitialFaceT4.getValue() == faces[3])) {
					ip4 += TC.getCapT4();

				} else if ((InitialFaceT4.getValue() == faces[4])) {
					ip5 += TC.getCapT4();

				} else if ((InitialFaceT4.getValue() == faces[5])) {
					ip6 += TC.getCapT4();

				} else if ((InitialFaceT4.getValue() == faces[6])) {
					ip7 += TC.getCapT4();
				}

			}

			if ((CBOPT5.isSelected() == false)
					& (CBLT5.isSelected() == true | CBLRT5.isSelected() == true | CBEHT5.isSelected() == true)) {
				if (InitialFaceT5.getValue() == faces[0]) {
					ip1 += TC.getCapT5();

				} else if ((InitialFaceT5.getValue() == faces[1])) {
					ip2 += TC.getCapT5();

				} else if ((InitialFaceT5.getValue() == faces[2])) {
					ip3 += TC.getCapT5();

				} else if ((InitialFaceT5.getValue() == faces[3])) {
					ip4 += TC.getCapT5();

				} else if ((InitialFaceT5.getValue() == faces[4])) {
					ip5 += TC.getCapT5();

				} else if ((InitialFaceT5.getValue() == faces[5])) {
					ip6 += TC.getCapT5();

				} else if ((InitialFaceT5.getValue() == faces[6])) {
					ip7 += TC.getCapT5();
				}

			}

			if ((CBOPT6.isSelected() == false)
					& (CBLT6.isSelected() == true | CBLRT6.isSelected() == true | CBEHT6.isSelected() == true)) {
				if (InitialFaceT6.getValue() == faces[0]) {
					ip1 += TC.getCapT6();

				} else if ((InitialFaceT6.getValue() == faces[1])) {
					ip2 += TC.getCapT6();

				} else if ((InitialFaceT6.getValue() == faces[2])) {
					ip3 += TC.getCapT6();

				} else if ((InitialFaceT6.getValue() == faces[3])) {
					ip4 += TC.getCapT6();

				} else if ((InitialFaceT6.getValue() == faces[4])) {
					ip5 += TC.getCapT6();

				} else if ((InitialFaceT6.getValue() == faces[5])) {
					ip6 += TC.getCapT6();

				} else if ((InitialFaceT6.getValue() == faces[6])) {
					ip7 += TC.getCapT6();
				}

			}

			if ((CBOPT7.isSelected() == false)
					& (CBLT7.isSelected() == true | CBLRT7.isSelected() == true | CBEHT7.isSelected() == true)) {
				if (InitialFaceT7.getValue() == faces[0]) {
					ip1 += TC.getCapT7();

				} else if ((InitialFaceT7.getValue() == faces[1])) {
					ip2 += TC.getCapT7();

				} else if ((InitialFaceT7.getValue() == faces[2])) {
					ip3 += TC.getCapT7();

				} else if ((InitialFaceT7.getValue() == faces[3])) {
					ip4 += TC.getCapT7();

				} else if ((InitialFaceT7.getValue() == faces[4])) {
					ip5 += TC.getCapT7();

				} else if ((InitialFaceT7.getValue() == faces[5])) {
					ip6 += TC.getCapT7();

				} else if ((InitialFaceT7.getValue() == faces[6])) {
					ip7 += TC.getCapT7();
				}

			}

			if ((CBOPT8.isSelected() == false)
					& (CBLT8.isSelected() == true | CBLRT8.isSelected() == true | CBEHT8.isSelected() == true)) {
				if (InitialFaceT8.getValue() == faces[0]) {
					ip1 += TC.getCapT8();

				} else if ((InitialFaceT8.getValue() == faces[1])) {
					ip2 += TC.getCapT8();

				} else if ((InitialFaceT8.getValue() == faces[2])) {
					ip3 += TC.getCapT8();

				} else if ((InitialFaceT8.getValue() == faces[3])) {
					ip4 += TC.getCapT8();

				} else if ((InitialFaceT8.getValue() == faces[4])) {
					ip5 += TC.getCapT8();

				} else if ((InitialFaceT8.getValue() == faces[5])) {
					ip6 += TC.getCapT8();

				} else if ((InitialFaceT8.getValue() == faces[6])) {
					ip7 += TC.getCapT8();
				}

			}

			if ((CBOPT9.isSelected() == false)
					& (CBLT9.isSelected() == true | CBLRT9.isSelected() == true | CBEHT9.isSelected() == true)) {
				if (InitialFaceT9.getValue() == faces[0]) {
					ip1 += TC.getCapT9();

				} else if ((InitialFaceT9.getValue() == faces[1])) {
					ip2 += TC.getCapT9();

				} else if ((InitialFaceT9.getValue() == faces[2])) {
					ip3 += TC.getCapT9();

				} else if ((InitialFaceT9.getValue() == faces[3])) {
					ip4 += TC.getCapT9();

				} else if ((InitialFaceT9.getValue() == faces[4])) {
					ip5 += TC.getCapT9();

				} else if ((InitialFaceT9.getValue() == faces[5])) {
					ip6 += TC.getCapT9();

				} else if ((InitialFaceT9.getValue() == faces[6])) {
					ip7 += TC.getCapT9();
				}

			}

			if ((CBOPT10.isSelected() == false)
					& (CBLT10.isSelected() == true | CBLRT10.isSelected() == true | CBEHT10.isSelected() == true)) {
				if (InitialFaceT10.getValue() == faces[0]) {
					ip1 += TC.getCapT10();

				} else if ((InitialFaceT10.getValue() == faces[1])) {
					ip2 += TC.getCapT10();

				} else if ((InitialFaceT10.getValue() == faces[2])) {
					ip3 += TC.getCapT10();

				} else if ((InitialFaceT10.getValue() == faces[3])) {
					ip4 += TC.getCapT10();

				} else if ((InitialFaceT10.getValue() == faces[4])) {
					ip5 += TC.getCapT10();

				} else if ((InitialFaceT10.getValue() == faces[5])) {
					ip6 += TC.getCapT10();

				} else if ((InitialFaceT10.getValue() == faces[6])) {
					ip7 += TC.getCapT10();
				}

			}

			if ((CBOPT11.isSelected() == false)
					& (CBLT11.isSelected() == true | CBLRT11.isSelected() == true | CBEHT11.isSelected() == true)) {
				if (InitialFaceT11.getValue() == faces[0]) {
					ip1 += TC.getCapT11();

				} else if ((InitialFaceT11.getValue() == faces[1])) {
					ip2 += TC.getCapT11();

				} else if ((InitialFaceT11.getValue() == faces[2])) {
					ip3 += TC.getCapT11();

				} else if ((InitialFaceT11.getValue() == faces[3])) {
					ip4 += TC.getCapT11();

				} else if ((InitialFaceT11.getValue() == faces[4])) {
					ip5 += TC.getCapT11();

				} else if ((InitialFaceT11.getValue() == faces[5])) {
					ip6 += TC.getCapT11();

				} else if ((InitialFaceT11.getValue() == faces[6])) {
					ip7 += TC.getCapT11();
				}

			}

			if ((CBOPT12.isSelected() == false)
					& (CBLT12.isSelected() == true | CBLRT12.isSelected() == true | CBEHT12.isSelected() == true)) {
				if (InitialFaceT12.getValue() == faces[0]) {
					ip1 += TC.getCapT12();

				} else if ((InitialFaceT12.getValue() == faces[1])) {
					ip2 += TC.getCapT12();

				} else if ((InitialFaceT12.getValue() == faces[2])) {
					ip3 += TC.getCapT12();

				} else if ((InitialFaceT12.getValue() == faces[3])) {
					ip4 += TC.getCapT12();

				} else if ((InitialFaceT12.getValue() == faces[4])) {
					ip5 += TC.getCapT12();

				} else if ((InitialFaceT12.getValue() == faces[5])) {
					ip6 += TC.getCapT12();

				} else if ((InitialFaceT12.getValue() == faces[6])) {
					ip7 += TC.getCapT12();
				}

			}

			if (ActualProdO1.isDisable() == false) {
				if (ip1 == 0.0) {
					ActualProdO1.setText("0,0");
				} else {
					ActualProdO1.setText(String.valueOf(df.format(ip1)));
				}
			}
			if (ActualProdO2.isDisable() == false) {
				if (ip2 == 0.0) {
					ActualProdO2.setText("0,0");
				} else {
					ActualProdO2.setText(String.valueOf(df.format(ip2)));
				}
			}
			if (ActualProdO3.isDisable() == false) {
				if (ip3 == 0.0) {
					ActualProdO3.setText("0,0");
				} else {
					ActualProdO3.setText(String.valueOf(df.format(ip3)));
				}
			}
			if (ActualProdO4.isDisable() == false) {
				if (ip4 == 0.0) {
					ActualProdO4.setText("0,0");
				} else {
					ActualProdO4.setText(String.valueOf(df.format(ip4)));
				}
			}
			if (ActualProdO5.isDisable() == false) {
				if (ip5 == 0.0) {
					ActualProdO5.setText("0,0");
				} else {
					ActualProdO5.setText(String.valueOf(df.format(ip5)));
				}
			}
			if (ActualProdW1.isDisable() == false) {
				if (ip6 == 0.0) {
					ActualProdW1.setText("0,0");
				} else {
					ActualProdW1.setText(String.valueOf(df.format(ip6)));
				}
			}
			if (ActualProdW2.isDisable() == false) {
				if (ip7 == 0.0) {
					ActualProdW2.setText("0,0");
				} else {
					ActualProdW2.setText(String.valueOf(df.format(ip7)));
				}
			}

			VectProd[0] = ip1;
			VectProd[1] = ip2;
			VectProd[2] = ip3;
			VectProd[3] = ip4;
			VectProd[4] = ip5;
			VectProd[5] = ip6;
			VectProd[6] = ip7;

			double prodTot = 0;
			double prodOre = 0;
			double prodWaste = 0;
			double rem = 0;

			prodOre = VectProd[0] + VectProd[1] + VectProd[2] + VectProd[3] + VectProd[4];
			prodWaste = VectProd[5] + VectProd[6];
			prodTot = prodOre + prodWaste;
			rem = prodWaste / prodOre;

			if (prodTot == 0.0) {
				ProdTot.setText("0,0");
			} else {
				ProdTot.setText(String.valueOf(df.format(prodTot)));
			}

			if (prodOre == 0.0) {
				ProdOre.setText("0,0");
			} else {
				ProdOre.setText(String.valueOf(df.format(prodOre)));
			}

			if (prodWaste == 0.0) {
				ProdWaste.setText("0,0");
			} else {
				ProdWaste.setText(String.valueOf(df.format(prodWaste)));
			}

			if (rem == 0.0) {
				REM.setText("0,0");
			} else {
				REM.setText(String.valueOf(df3.format(rem)));
			}

			double somaFe = 0;
			double somaSiO2 = 0;
			double somaAl2O3 = 0;
			double somaMn = 0;
			double somaP = 0;
			double somaLOI = 0;
			double soma50 = 0;
			double soma63 = 0;
			double soma015 = 0;
			double soma = 0;

			for (int o = 0; o < 4; o++) {
				somaFe += VectProd[o] * MatG[o][0];
				somaSiO2 += VectProd[o] * MatG[o][1];
				somaAl2O3 += VectProd[o] * MatG[o][2];
				somaMn += VectProd[o] * MatG[o][3];
				somaP += VectProd[o] * MatG[o][4];
				somaLOI += VectProd[o] * MatG[o][5];
				soma50 += VectProd[o] * MatG[o][6];
				soma63 += VectProd[o] * MatG[o][7];
				soma015 += VectProd[o] * MatG[o][8];
				soma += VectProd[o];
			}

			ProdGrade[0] = somaFe / soma;
			ProdGrade[1] = somaSiO2 / soma;
			ProdGrade[2] = somaAl2O3 / soma;
			ProdGrade[3] = somaMn / soma;
			ProdGrade[4] = somaP / soma;
			ProdGrade[5] = somaLOI / soma;
			ProdGrade[6] = soma50 / soma;
			ProdGrade[7] = soma63 / soma;
			ProdGrade[8] = soma015 / soma;

			listProdGrade
					.add(new ProdGrade(df1.format(ProdGrade[0]), df1.format(ProdGrade[1]), df1.format(ProdGrade[2]),
							df1.format(ProdGrade[3]), df2.format(ProdGrade[4]), df1.format(ProdGrade[5]),
							df1.format(ProdGrade[6]), df1.format(ProdGrade[7]), df1.format(ProdGrade[8])));
			obsProdGrade = FXCollections.observableArrayList(listProdGrade);
			tableViewProdGrade.setItems(obsProdGrade);

			if (ProdGrade[0] > UB[0] | ProdGrade[0] < LB[0]) {
				CBFe.setSelected(false);
				CBFe.setStyle("-fx-body-color: red");
				CBFe.setDisable(true);
				CBFe.setOpacity(10);
			} else {
				CBFe.setSelected(true);
				CBFe.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CBFe.setDisable(true);
				CBFe.setOpacity(10);
			}

			if (ProdGrade[1] > UB[1] | ProdGrade[1] < LB[1]) {
				CBSiO2.setSelected(false);
				CBSiO2.setStyle("-fx-body-color: red");
				CBSiO2.setDisable(true);
				CBSiO2.setOpacity(10);
			} else {
				CBSiO2.setSelected(true);
				CBSiO2.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CBSiO2.setDisable(true);
				CBSiO2.setOpacity(10);
			}

			if (ProdGrade[2] > UB[2] | ProdGrade[2] < LB[2]) {
				CBAl2O3.setSelected(false);
				CBAl2O3.setStyle("-fx-body-color: red");
				CBAl2O3.setDisable(true);
				CBAl2O3.setOpacity(10);
			} else {
				CBAl2O3.setSelected(true);
				CBAl2O3.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CBAl2O3.setDisable(true);
				CBAl2O3.setOpacity(10);
			}

			if (ProdGrade[3] > UB[3] | ProdGrade[3] < LB[3]) {
				CBMn.setSelected(false);
				CBMn.setStyle("-fx-body-color: red");
				CBMn.setDisable(true);
				CBMn.setOpacity(10);
			} else {
				CBMn.setSelected(true);
				CBMn.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CBMn.setDisable(true);
				CBMn.setOpacity(10);
			}

			if (ProdGrade[4] > UB[4] | ProdGrade[4] < LB[4]) {
				CBP.setSelected(false);
				CBP.setStyle("-fx-body-color: red");
				CBP.setDisable(true);
				CBP.setOpacity(10);
			} else {
				CBP.setSelected(true);
				CBP.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CBP.setDisable(true);
				CBP.setOpacity(10);
			}

			if (ProdGrade[5] > UB[5] | ProdGrade[5] < LB[5]) {
				CBLOI.setSelected(false);
				CBLOI.setStyle("-fx-body-color: red");
				CBLOI.setDisable(true);
				CBLOI.setOpacity(10);
			} else {
				CBLOI.setSelected(true);
				CBLOI.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CBLOI.setDisable(true);
				CBLOI.setOpacity(10);
			}

			if (ProdGrade[6] > UB[6] | ProdGrade[6] < LB[6]) {
				CB50.setSelected(false);
				CB50.setStyle("-fx-body-color: red");
				CB50.setDisable(true);
				CB50.setOpacity(10);
			} else {
				CB50.setSelected(true);
				CB50.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CB50.setDisable(true);
				CB50.setOpacity(10);
			}

			if (ProdGrade[7] > UB[7] | ProdGrade[7] < LB[7]) {
				CB63.setSelected(false);
				CB63.setStyle("-fx-body-color: red");
				CB63.setDisable(true);
				CB63.setOpacity(10);
			} else {
				CB63.setSelected(true);
				CB63.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CB63.setDisable(true);
				CB63.setOpacity(10);
			}

			if (ProdGrade[8] > UB[8] | ProdGrade[8] < LB[8]) {
				CB015.setSelected(false);
				CB015.setStyle("-fx-body-color: red");
				CB015.setDisable(true);
				CB015.setOpacity(10);
			} else {
				CB015.setSelected(true);
				CB015.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
				CB015.setDisable(true);
				CB015.setOpacity(10);
			}
			if (PlanProdO1.isDisable() == false) {
				if (VectProd[0] >= VectPlan[0]) {
					CBO1.setSelected(true);
					CBO1.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBO1.setDisable(true);
					CBO1.setOpacity(10);
				} else {
					CBO1.setSelected(false);
					CBO1.setStyle("-fx-body-color: red");
					CBO1.setDisable(true);
					CBO1.setOpacity(10);
				}
			} else {
				CBO1.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}

			if (PlanProdO2.isDisable() == false) {
				if (VectProd[1] >= VectPlan[1]) {
					CBO2.setSelected(true);
					CBO2.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBO2.setDisable(true);
					CBO2.setOpacity(10);
				} else {
					CBO2.setSelected(false);
					CBO2.setStyle("-fx-body-color: red");
					CBO2.setDisable(true);
					CBO2.setOpacity(10);
				}
			} else {
				CBO2.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}

			if (PlanProdO3.isDisable() == false) {
				if (VectProd[2] >= VectPlan[2]) {
					CBO3.setSelected(true);
					CBO3.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBO3.setDisable(true);
					CBO3.setOpacity(10);
				} else {
					CBO3.setSelected(false);
					CBO3.setStyle("-fx-body-color: red");
					CBO3.setDisable(true);
					CBO3.setOpacity(10);
				}
			} else {
				CBO3.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}

			if (PlanProdO4.isDisable() == false) {
				if (VectProd[3] >= VectPlan[3]) {
					CBO4.setSelected(true);
					CBO4.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBO4.setDisable(true);
					CBO4.setOpacity(10);
				} else {
					CBO4.setSelected(false);
					CBO4.setStyle("-fx-body-color: red");
					CBO4.setDisable(true);
					CBO4.setOpacity(10);
				}
			} else {
				CBO4.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}

			if (PlanProdO5.isDisable() == false) {
				if (VectProd[4] >= VectPlan[4]) {
					CBO5.setSelected(true);
					CBO5.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBO5.setDisable(true);
					CBO5.setOpacity(10);
				} else {
					CBO5.setSelected(false);
					CBO5.setStyle("-fx-body-color: red");
					CBO5.setDisable(true);
					CBO5.setOpacity(10);
				}
			} else {
				CBO5.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}

			if (PlanProdW1.isDisable() == false) {
				if (VectProd[5] >= VectPlan[5]) {
					CBW1.setSelected(true);
					CBW1.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBW1.setDisable(true);
					CBW1.setOpacity(10);
				} else {
					CBW1.setSelected(false);
					CBW1.setStyle("-fx-body-color: red");
					CBW1.setDisable(true);
					CBW1.setOpacity(10);
				}
			} else {
				CBW1.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}

			if (PlanProdW2.isDisable() == false) {
				if (VectProd[6] >= VectPlan[6]) {
					CBW2.setSelected(true);
					CBW2.setStyle("-fx-body-color: GREEN;  -fx-mark-color: WHITE");
					CBW2.setDisable(true);
					CBW2.setOpacity(10);
				} else {
					CBW2.setSelected(false);
					CBW2.setStyle("-fx-body-color: red");
					CBW2.setDisable(true);
					CBW2.setOpacity(10);
				}
			} else {
				CBW2.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
			}

			// Deviation
			if (DevProdO1.isDisable() == false) {
				if (ip1 / list.get(0) == 1) {
					DevProdO1.setText("0,0");
				} else {
					DevProdO1.setText(String.valueOf(df.format(((ip1 / list.get(0)) - 1) * -100)));
				}
			}
			if (DevProdO2.isDisable() == false) {
				if (ip2 / list.get(1) == 1) {
					DevProdO2.setText("0,0");
				} else {
					DevProdO2.setText(String.valueOf(df.format(((ip2 / list.get(1)) - 1) * -100)));
				}
			}
			if (DevProdO3.isDisable() == false) {
				if (ip3 / list.get(2) == 1) {
					DevProdO3.setText("0,0");
				} else {
					DevProdO3.setText(String.valueOf(df.format(((ip3 / list.get(2)) - 1) * -100)));
				}
			}
			if (DevProdO4.isDisable() == false) {
				if (ip4 / list.get(3) == 1) {
					DevProdO4.setText("0,0");
				} else {
					DevProdO4.setText(String.valueOf(df.format(((ip4 / list.get(3)) - 1) * -100)));
				}
			}
			if (DevProdO5.isDisable() == false) {
				if (ip5 / list.get(4) == 1) {
					DevProdO5.setText("0,0");
				} else {
					DevProdO5.setText(String.valueOf(df.format(((ip5 / list.get(4)) - 1) * -100)));
				}
			}
			if (DevProdW1.isDisable() == false) {
				if (ip6 / list.get(5) == 1) {
					DevProdW1.setText("0,0");
				} else {
					DevProdW1.setText(String.valueOf(df.format(((ip6 / list.get(5)) - 1) * -100)));
				}
			}
			if (DevProdW2.isDisable() == false) {
				if (ip7 / list.get(6) == 1) {
					DevProdW2.setText("0,0");
				} else {
					DevProdW2.setText(String.valueOf(df.format(((ip7 / list.get(6)) - 1) * -100)));
				}
			}

			VectDev[0] = ((ip1 / list.get(0)) - 1) * -100;
			VectDev[1] = ((ip2 / list.get(1)) - 1) * -100;
			VectDev[2] = ((ip3 / list.get(2)) - 1) * -100;
			VectDev[3] = ((ip4 / list.get(3)) - 1) * -100;
			VectDev[4] = ((ip5 / list.get(4)) - 1) * -100;
			VectDev[5] = ((ip6 / list.get(5)) - 1) * -100;
			VectDev[6] = ((ip7 / list.get(6)) - 1) * -100;

			for (int i1 = 0; i1 < 7; i1++) {
				VectDevX[i1] = 0.0;
			}

			btDispatch.setDisable(false);
			btFinish.setDisable(false);
			
			txtAreaDispatch.setDisable(false);
			CBDispatch.setDisable(false);

			ObservableList<String> obsCombo;
			obsCombo = FXCollections.observableArrayList(listOperativeTruck);

			CBDispatch.setItems(obsCombo);
			btAllocate.setDisable(true);

		} else {
			Alerts.showAlert("Error", "Check that the data has been imported and if you have allocated each truck!!",
					AlertType.ERROR);
			CBDone.setSelected(false);
			CBDone.setStyle("-fx-body-color: red");
			CBDone.setDisable(true);
			CBDone.setOpacity(10);
		}
	}

	@FXML
	private TableView<MinePlan> tableViewActual;

	@FXML
	private TableColumn<MinePlan, String> tableColumnFaceAc;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnAcT1;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnAcT2;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnAcT3;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnAcT4;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnAcT5;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnAcT6;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnAcT7;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnAcT8;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnAcT9;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnAcT10;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnAcT11;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnAcT12;

	@FXML
	private Button btFinish;

	@FXML
	public void onBtFinish() {

		CBDispatch.setDisable(true);
		btDispatch.setDisable(true);
		menuItemExportShiftReport.setDisable(false);
		menuItemReinit.setDisable(false);
		btFinish.setDisable(true);
	}

	@FXML
	public void onMenuItemReinitAction() {
		tableViewFaceGrades.getItems().clear();
		tableViewPlantBoundaries.getItems().clear();
		tableViewTrucksCapacity.getItems().clear();
		tableViewMinePlans.getItems().clear();
		tableViewActual.getItems().clear();
		tableViewProdGrade.getItems().clear();

		btAllocate.setDisable(true);

		menuItemReinit.setDisable(true);
		menuItemImportFaceGrades.setDisable(false);
		menuImportPlantBoundaries.setDisable(false);
		menuItemImportTrucksCapacity.setDisable(false);
		menuItemImportMinePlan.setDisable(false);
		menuItemExportShiftReport.setDisable(true);

		CBDone.setSelected(false);
		GRIDP.setDisable(true);

		CBOPT1.setSelected(false);
		InitialFaceT1.setValue("");
		CBLRT1.setSelected(false);
		CBLT1.setSelected(false);
		CBFHT1.setSelected(false);
		CBCRT1.setSelected(false);
		CBCDT1.setSelected(false);
		CBWRT1.setSelected(false);
		CBWDT1.setSelected(false);
		CBEHT1.setSelected(false);
		CBSRT1.setSelected(false);
		CBST1.setSelected(false);

		CBOPT2.setSelected(false);
		InitialFaceT2.setValue("");
		CBLRT2.setSelected(false);
		CBLT2.setSelected(false);
		CBFHT2.setSelected(false);
		CBCRT2.setSelected(false);
		CBCDT2.setSelected(false);
		CBWRT2.setSelected(false);
		CBWDT2.setSelected(false);
		CBEHT2.setSelected(false);
		CBSRT2.setSelected(false);
		CBST2.setSelected(false);

		CBOPT3.setSelected(false);
		InitialFaceT3.setValue("");
		CBLRT3.setSelected(false);
		CBLT3.setSelected(false);
		CBFHT3.setSelected(false);
		CBCRT3.setSelected(false);
		CBCDT3.setSelected(false);
		CBWRT3.setSelected(false);
		CBWDT3.setSelected(false);
		CBEHT3.setSelected(false);
		CBSRT3.setSelected(false);
		CBST3.setSelected(false);

		CBOPT4.setSelected(false);
		InitialFaceT4.setValue("");
		CBLRT4.setSelected(false);
		CBLT4.setSelected(false);
		CBFHT4.setSelected(false);
		CBCRT4.setSelected(false);
		CBCDT4.setSelected(false);
		CBWRT4.setSelected(false);
		CBWDT4.setSelected(false);
		CBEHT4.setSelected(false);
		CBSRT4.setSelected(false);
		CBST4.setSelected(false);

		CBOPT5.setSelected(false);
		InitialFaceT5.setValue("");
		CBLRT5.setSelected(false);
		CBLT5.setSelected(false);
		CBFHT5.setSelected(false);
		CBCRT5.setSelected(false);
		CBCDT5.setSelected(false);
		CBWRT5.setSelected(false);
		CBWDT5.setSelected(false);
		CBEHT5.setSelected(false);
		CBSRT5.setSelected(false);
		CBST5.setSelected(false);

		CBOPT6.setSelected(false);
		InitialFaceT6.setValue("");
		CBLRT6.setSelected(false);
		CBLT6.setSelected(false);
		CBFHT6.setSelected(false);
		CBCRT6.setSelected(false);
		CBCDT6.setSelected(false);
		CBWRT6.setSelected(false);
		CBWDT6.setSelected(false);
		CBEHT6.setSelected(false);
		CBSRT6.setSelected(false);
		CBST6.setSelected(false);

		CBOPT7.setSelected(false);
		InitialFaceT7.setValue("");
		CBLRT7.setSelected(false);
		CBLT7.setSelected(false);
		CBFHT7.setSelected(false);
		CBCRT7.setSelected(false);
		CBCDT7.setSelected(false);
		CBWRT7.setSelected(false);
		CBWDT7.setSelected(false);
		CBEHT7.setSelected(false);
		CBSRT7.setSelected(false);
		CBST7.setSelected(false);

		CBOPT8.setSelected(false);
		InitialFaceT8.setValue("");
		CBLRT8.setSelected(false);
		CBLT8.setSelected(false);
		CBFHT8.setSelected(false);
		CBCRT8.setSelected(false);
		CBCDT8.setSelected(false);
		CBWRT8.setSelected(false);
		CBWDT8.setSelected(false);
		CBEHT8.setSelected(false);
		CBSRT8.setSelected(false);
		CBST8.setSelected(false);

		CBOPT9.setSelected(false);
		InitialFaceT9.setValue("");
		CBLRT9.setSelected(false);
		CBLT9.setSelected(false);
		CBFHT9.setSelected(false);
		CBCRT9.setSelected(false);
		CBCDT9.setSelected(false);
		CBWRT9.setSelected(false);
		CBWDT9.setSelected(false);
		CBEHT9.setSelected(false);
		CBSRT9.setSelected(false);
		CBST9.setSelected(false);

		CBOPT10.setSelected(false);
		InitialFaceT10.setValue("");
		CBLRT10.setSelected(false);
		CBLT10.setSelected(false);
		CBFHT10.setSelected(false);
		CBCRT10.setSelected(false);
		CBCDT10.setSelected(false);
		CBWRT10.setSelected(false);
		CBWDT10.setSelected(false);
		CBEHT10.setSelected(false);
		CBSRT10.setSelected(false);
		CBST10.setSelected(false);

		CBOPT11.setSelected(false);
		InitialFaceT11.setValue("");
		CBLRT11.setSelected(false);
		CBLT11.setSelected(false);
		CBFHT11.setSelected(false);
		CBCRT11.setSelected(false);
		CBCDT11.setSelected(false);
		CBWRT11.setSelected(false);
		CBWDT11.setSelected(false);
		CBEHT11.setSelected(false);
		CBSRT11.setSelected(false);
		CBST11.setSelected(false);

		CBOPT12.setSelected(false);
		InitialFaceT12.setValue("");
		CBLRT12.setSelected(false);
		CBLT12.setSelected(false);
		CBFHT12.setSelected(false);
		CBCRT12.setSelected(false);
		CBCDT12.setSelected(false);
		CBWRT12.setSelected(false);
		CBWDT12.setSelected(false);
		CBEHT12.setSelected(false);
		CBSRT12.setSelected(false);
		CBST12.setSelected(false);

		PlanProdO1.clear();
		PlanProdO2.clear();
		PlanProdO3.clear();
		PlanProdO4.clear();
		PlanProdO5.clear();
		PlanProdW1.clear();
		PlanProdW2.clear();
		ActualProdO1.clear();
		ActualProdO2.clear();
		ActualProdO3.clear();
		ActualProdO4.clear();
		ActualProdO5.clear();
		ActualProdW1.clear();
		ActualProdW2.clear();
		DevProdO1.clear();
		DevProdO2.clear();
		DevProdO3.clear();
		DevProdO4.clear();
		DevProdO5.clear();
		DevProdW1.clear();
		DevProdW2.clear();

		CBDispatch.setValue("");
		CBDispatch.setDisable(true);
		txtAreaDispatch.clear();
		btDispatch.setDisable(true);
		btFinish.setDisable(true);

		CBDone.setStyle("-fx-body-color: GAINSBORO");
		CBOPT1.setStyle("-fx-body-color: GAINSBORO");
		CBOPT2.setStyle("-fx-body-color: GAINSBORO");
		CBOPT3.setStyle("-fx-body-color: GAINSBORO");
		CBOPT4.setStyle("-fx-body-color: GAINSBORO");
		CBOPT5.setStyle("-fx-body-color: GAINSBORO");
		CBOPT6.setStyle("-fx-body-color: GAINSBORO");
		CBOPT7.setStyle("-fx-body-color: GAINSBORO");
		CBOPT8.setStyle("-fx-body-color: GAINSBORO");
		CBOPT9.setStyle("-fx-body-color: GAINSBORO");
		CBOPT10.setStyle("-fx-body-color: GAINSBORO");
		CBOPT11.setStyle("-fx-body-color: GAINSBORO");
		CBOPT12.setStyle("-fx-body-color: GAINSBORO");

		CBO1.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CBO2.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CBO3.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CBO4.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CBO5.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CBW1.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CBW2.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");

		CBFe.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CBSiO2.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CBAl2O3.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CBMn.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CBP.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CBLOI.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CB50.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CB63.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");
		CB015.setStyle("-fx-body-color: GAINSBORO; -fx-mark-color: GAINSBORO");

		ProdTot.setText("");
		ProdOre.setText("");
		ProdWaste.setText("");
		REM.setText("");

	}

	@FXML
	public void onMenuItemExportShiftReportAction() throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		RepDate = System.currentTimeMillis();
		String dataFormatada = dateFormat.format(RepDate);
		

		SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String dataFormatada1 = dateFormat1.format(date);
		
		DecimalFormat formato = new DecimalFormat("#,##");
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File Dialog");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt", "txt"));
		
		File file = fileChooser.showSaveDialog(null);

	    FileWriter arq = new FileWriter(file);
	    PrintWriter gravarArq = new PrintWriter(arq);

	    gravarArq.printf("Shift report - " + dataFormatada + " - " + dataFormatada1 + "\n");
	    gravarArq.printf("------------------------------------\n");
	    gravarArq.printf("\n");
	    gravarArq.printf("Mine Plan\n");
	    for (MinePlan c : listRepPlan) {
	    	gravarArq.printf("[face=" + c.getFace() + ", T1=" + c.getT1() + ", T2=" + c.getT2() + ", T3=" + c.getT3() + ", T4=" + c.getT4() + ", T5=" + c.getT5() + ", T6=" + c.getT6() + ", T7=" + c.getT7() + ", T8=" + c.getT8() + ", T9=" + c.getT9() + ", T10=" + c.getT10() + ", T11=" + c.getT11() + ", T12=" + c.getT12() +"]\n" );
		}
	    gravarArq.printf("\n");
	    gravarArq.printf("Realized Mine Plan\n");
	    for (MinePlan c : listRepActual) {
	    	gravarArq.printf("[face=" + c.getFace() + ", T1=" + c.getT1() + ", T2=" + c.getT2() + ", T3=" + c.getT3() + ", T4=" + c.getT4() + ", T5=" + c.getT5() + ", T6=" + c.getT6() + ", T7=" + c.getT7() + ", T8=" + c.getT8() + ", T9=" + c.getT9() + ", T10=" + c.getT10() + ", T11=" + c.getT11() + ", T12=" + c.getT12() +"]\n" );
		}
	    gravarArq.printf("\n");
	    gravarArq.printf("Truck Trips\n[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]\n");
	    gravarArq.printf(Arrays.toString(VectRepTravelsPerTruck));
	    gravarArq.printf("\n");
	    gravarArq.printf("\n");	    
	    gravarArq.printf("Travel List\n");
	    for (Travels c : listRepTravels) {
			gravarArq.printf("T" + c.getTruck() + " -> " + c.getFace() + " at " + c.getDate() + "\n");
		}
	    gravarArq.printf("\n");
	    gravarArq.printf("Faces Productions \n[O1, O2, O3, O4, O5, W1, W2]\n");
	    gravarArq.printf(Arrays.toString(VectRepProdPerFace));
	    gravarArq.printf("\n");
	    gravarArq.printf("\n");	 
	    gravarArq.printf("Ore Production=" + RepProdOre + "\n");
	    gravarArq.printf("Waste Production=" + RepProdWaste + "\n");
	    gravarArq.printf("Total Production=" + RepProdTotal + "\n");
	    gravarArq.printf("REM=" + Double.valueOf(formato.format(RepREM)) + "\n");
	    gravarArq.printf("\n");
	    gravarArq.printf("Mixture Grade (in percentage)\n");
	    for (ProdGrade c : listRepProdGrade) {
	    	gravarArq.printf("[Fe=" + c.getFe() + "; SiO2=" + c.getSio2() + "; Al2O3=" + c.getAl2o3() + "; Mn=" + c.getMn() + "; P=" + c.getP() + "; LOI=" + c.getLoi() + "; +50,00mm=" + c.getMore50mm() + "; +6,30mm=" + c.getMore6_3mm() + "; +0,15mm=" + c.getMore0_15mm() + "]");
		}
	    
	    arq.close();
	  
}
	

	@FXML
	public void onMenuItemExitAction() {
		int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the application?", "Warning",
				JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
		if (answer == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	@FXML
	private TableView<FaceGrades> tableViewFaceGrades;

	@FXML
	private TableColumn<FaceGrades, String> tableColumnFaceG;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnGFe;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnGSiO2;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnGAl2O3;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnGMn;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnGP;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnGLOI;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnG50mm;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnG6_3mm;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnG0_15mm;

	private ObservableList<FaceGrades> obsList4;

	@FXML
	public void onMenuItemImportFaceGradesAction() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File Dialog");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt", "txt"));

		File file = fileChooser.showOpenDialog(null);

		@SuppressWarnings("resource")
		BufferedReader br1 = new BufferedReader(new FileReader(file));
		String line2 = br1.readLine();
		int x = 0;
		int k = 0;
		while (line2 != null) {
			String[] vect = line2.split(",");
			k = vect.length;
			x += 1;
			line2 = br1.readLine();
		}
		listFG.clear();
		if (x == 7 & k == 10) {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while (line != null) {

				String[] vect = line.split(",");

				String face = vect[0];
				double fe = Double.parseDouble(vect[1]);
				double sio2 = Double.parseDouble(vect[2]);
				double al2o3 = Double.parseDouble(vect[3]);
				double mn = Double.parseDouble(vect[4]);
				double p = Double.parseDouble(vect[5]);
				double loi = Double.parseDouble(vect[6]);
				double g50mm = Double.parseDouble(vect[7]);
				double g6_3mm = Double.parseDouble(vect[8]);
				double g0_15mm = Double.parseDouble(vect[9]);

				listFG.add(new FaceGrades(face, fe, sio2, al2o3, mn, p, loi, g50mm, g6_3mm, g0_15mm));
				line = br.readLine();
			}
			obsList4 = FXCollections.observableArrayList(listFG);
			tableViewFaceGrades.setItems(obsList4);
		} else {
			Alerts.showAlert("File Reading Error", "Check the file!", AlertType.ERROR);
		}

		if (x == 7 & k == 10) {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(file));

			listFG1.clear();
			String line1 = br.readLine();
			for (int i = 0; i <= 4; i++) {

				String[] vect = line1.split(",");

				String face = vect[0];
				double fe = Double.parseDouble(vect[1]);
				double sio2 = Double.parseDouble(vect[2]);
				double al2o3 = Double.parseDouble(vect[3]);
				double mn = Double.parseDouble(vect[4]);
				double p = Double.parseDouble(vect[5]);
				double loi = Double.parseDouble(vect[6]);
				double g50mm = Double.parseDouble(vect[7]);
				double g6_3mm = Double.parseDouble(vect[8]);
				double g0_15mm = Double.parseDouble(vect[9]);

				listFG1.add(new FaceGrades(face, fe, sio2, al2o3, mn, p, loi, g50mm, g6_3mm, g0_15mm));
				line1 = br.readLine();
			}

			int l = 0;
			for (FaceGrades m : listFG1) {
				for (int j = 0; j < 4; j++) {
					MatG[l][0] = m.getFe();
					MatG[l][1] = m.getSio2();
					MatG[l][2] = m.getAl2o3();
					MatG[l][3] = m.getMn();
					MatG[l][4] = m.getP();
					MatG[l][5] = m.getLoi();
					MatG[l][6] = m.getMore50mm();
					MatG[l][7] = m.getMore6_3mm();
					MatG[l][8] = m.getMore0_15mm();
				}
				l += 1;
			}

			menuItemImportFaceGrades.setDisable(true);
		}
	}

	@FXML
	private TableView<PlantBoundaries> tableViewPlantBoundaries;

	@FXML
	private TableColumn<PlantBoundaries, String> tableColumnLimits;

	@FXML
	private TableColumn<PlantBoundaries, Integer> tableColumnBFe;

	@FXML
	private TableColumn<PlantBoundaries, Integer> tableColumnBSiO2;

	@FXML
	private TableColumn<PlantBoundaries, Integer> tableColumnBAl2O3;

	@FXML
	private TableColumn<PlantBoundaries, Integer> tableColumnBMn;

	@FXML
	private TableColumn<PlantBoundaries, Integer> tableColumnBP;

	@FXML
	private TableColumn<PlantBoundaries, Integer> tableColumnBLOI;

	@FXML
	private TableColumn<PlantBoundaries, Integer> tableColumnB50mm;

	@FXML
	private TableColumn<PlantBoundaries, Integer> tableColumnB6_3mm;

	@FXML
	private TableColumn<PlantBoundaries, Integer> tableColumnB0_15mm;

	private ObservableList<PlantBoundaries> obsList5;

	@FXML
	public void onMenuImportPlantBoundariesAction() throws IOException {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File Dialog");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt", "txt"));

		File file = fileChooser.showOpenDialog(null);

		listPB.clear();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line1 = br.readLine();
		int x = 0;
		while (line1 != null) {
			x += 1;
			line1 = br.readLine();
		}
		@SuppressWarnings("resource")
		BufferedReader br1 = new BufferedReader(new FileReader(file));
		if (x == 2) {
			String line = br1.readLine();
			while (line != null) {
				String[] vect = line.split(",");

				String face = vect[0];
				double fe = Double.parseDouble(vect[1]);
				double sio2 = Double.parseDouble(vect[2]);
				double al2o3 = Double.parseDouble(vect[3]);
				double mn = Double.parseDouble(vect[4]);
				double p = Double.parseDouble(vect[5]);
				double loi = Double.parseDouble(vect[6]);
				double g50mm = Double.parseDouble(vect[7]);
				double g6_3mm = Double.parseDouble(vect[8]);
				double g0_15mm = Double.parseDouble(vect[9]);

				listPB.add(new PlantBoundaries(face, fe, sio2, al2o3, mn, p, loi, g50mm, g6_3mm, g0_15mm));
				line = br1.readLine();
			}
			obsList5 = FXCollections.observableArrayList(listPB);
			tableViewPlantBoundaries.setItems(obsList5);

			UB[0] = listPB.get(0).getFe();
			UB[1] = listPB.get(0).getSio2();
			UB[2] = listPB.get(0).getAl2o3();
			UB[3] = listPB.get(0).getMn();
			UB[4] = listPB.get(0).getP();
			UB[5] = listPB.get(0).getLoi();
			UB[6] = listPB.get(0).getMore50mm();
			UB[7] = listPB.get(0).getMore6_3mm();
			UB[8] = listPB.get(0).getMore0_15mm();

			LB[0] = listPB.get(1).getFe();
			LB[1] = listPB.get(1).getSio2();
			LB[2] = listPB.get(1).getAl2o3();
			LB[3] = listPB.get(1).getMn();
			LB[4] = listPB.get(1).getP();
			LB[5] = listPB.get(1).getLoi();
			LB[6] = listPB.get(1).getMore50mm();
			LB[7] = listPB.get(1).getMore6_3mm();
			LB[8] = listPB.get(1).getMore0_15mm();

			menuImportPlantBoundaries.setDisable(true);
		} else {
			Alerts.showAlert("File Reading Error", "Check the file!", AlertType.ERROR);
		}

	}

	@FXML
	public void onMenuItemAboutAction() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutView.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("1.0.0 goTruck® - About");
			Image ico = new Image("/gui/util/Images/truck.png");
			stage.getIcons().add(ico);
			stage.setScene(new Scene(root1));
			stage.show();
			stage.setResizable(false);
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", AlertType.ERROR);
			e.printStackTrace();
		}
	}

	@FXML
	private CheckBox CBST8;

	@FXML
	private CheckBox CBST4;

	@FXML
	private CheckBox CBEHT1;

	@FXML
	private CheckBox CBST2;

	@FXML
	private CheckBox CBEHT3;

	@FXML
	private CheckBox CBST1;

	@FXML
	private CheckBox CBEHT2;

	@FXML
	private CheckBox CBEHT4;

	@FXML
	private CheckBox CBEHT5;

	@FXML
	private CheckBox CBEHT6;

	@FXML
	private CheckBox CBEHT7;

	@FXML
	private CheckBox CBEHT8;

	@FXML
	private CheckBox CBEHT9;

	@FXML
	private CheckBox CBEHT10;

	@FXML
	private CheckBox CBEHT11;

	@FXML
	private CheckBox CBEHT12;

	@FXML
	private CheckBox CBST3;

	@FXML
	private CheckBox CBSRT3;

	@FXML
	private CheckBox CBSRT1;

	@FXML
	private CheckBox CBSRT2;

	@FXML
	private CheckBox CBSRT4;

	@FXML
	private CheckBox CBSRT5;

	@FXML
	private CheckBox CBSRT6;

	@FXML
	private CheckBox CBSRT7;

	@FXML
	private CheckBox CBSRT8;

	@FXML
	private CheckBox CBSRT9;

	@FXML
	private CheckBox CBSRT10;

	@FXML
	private CheckBox CBSRT11;

	@FXML
	private CheckBox CBSRT12;

	@FXML
	private CheckBox CBWRT1;

	@FXML
	private CheckBox CBWRT2;

	@FXML
	private CheckBox CBWRT3;

	@FXML
	private CheckBox CBWRT4;

	@FXML
	private CheckBox CBWRT5;

	@FXML
	private CheckBox CBWRT6;

	@FXML
	private CheckBox CBWRT7;

	@FXML
	private CheckBox CBWRT8;

	@FXML
	private CheckBox CBWRT9;

	@FXML
	private CheckBox CBWRT11;

	@FXML
	private CheckBox CBWRT10;

	@FXML
	private CheckBox CBWRT12;

	@FXML
	private CheckBox CBWDT1;

	@FXML
	private CheckBox CBWDT2;

	@FXML
	private CheckBox CBWDT3;

	@FXML
	private CheckBox CBWDT4;

	@FXML
	private CheckBox CBWDT5;

	@FXML
	private CheckBox CBWDT6;

	@FXML
	private CheckBox CBWDT7;

	@FXML
	private CheckBox CBWDT8;

	@FXML
	private CheckBox CBWDT9;

	@FXML
	private CheckBox CBWDT10;

	@FXML
	private CheckBox CBWDT11;

	@FXML
	private CheckBox CBWDT12;

	@FXML
	private CheckBox CBST5;

	@FXML
	private CheckBox CBCDT1;

	@FXML
	private CheckBox CBCDT2;

	@FXML
	private CheckBox CBCDT3;

	@FXML
	private CheckBox CBCDT4;

	@FXML
	private CheckBox CBCDT5;

	@FXML
	private CheckBox CBCDT6;

	@FXML
	private CheckBox CBCDT7;

	@FXML
	private CheckBox CBCDT8;

	@FXML
	private CheckBox CBCDT9;

	@FXML
	private CheckBox CBCDT10;

	@FXML
	private CheckBox CBCDT11;

	@FXML
	private CheckBox CBCDT12;

	@FXML
	private CheckBox CBST6;

	@FXML
	private CheckBox CBST7;

	@FXML
	private CheckBox CBST9;

	@FXML
	private CheckBox CBST10;

	@FXML
	private CheckBox CBST11;

	@FXML
	private CheckBox CBST12;

	@FXML
	private CheckBox CBCRT1;

	@FXML
	private CheckBox CBFHT1;

	@FXML
	private CheckBox CBCRT2;

	@FXML
	private CheckBox CBCRT3;

	@FXML
	private CheckBox CBCRT4;

	@FXML
	private CheckBox CBCRT5;

	@FXML
	private CheckBox CBCRT6;

	@FXML
	private CheckBox CBFHT2;

	@FXML
	private CheckBox CBFHT3;

	@FXML
	private CheckBox CBFHT4;

	@FXML
	private CheckBox CBCRT7;

	@FXML
	private CheckBox CBFHT5;

	@FXML
	private CheckBox CBFHT6;

	@FXML
	private CheckBox CBFHT7;

	@FXML
	private CheckBox CBCRT8;

	@FXML
	private CheckBox CBCRT9;

	@FXML
	private CheckBox CBCRT10;

	@FXML
	private CheckBox CBCRT11;

	@FXML
	private CheckBox CBCRT12;

	@FXML
	private CheckBox CBFHT8;

	@FXML
	private CheckBox CBFHT9;

	@FXML
	private CheckBox CBFHT10;

	@FXML
	private CheckBox CBFHT11;

	@FXML
	private CheckBox CBFHT12;

	@FXML
	private CheckBox CBLT1;

	@FXML
	private CheckBox CBLT2;

	@FXML
	private CheckBox CBLT3;

	@FXML
	private CheckBox CBLT4;

	@FXML
	private CheckBox CBLT5;

	@FXML
	private CheckBox CBLT6;

	@FXML
	private CheckBox CBLT7;

	@FXML
	private CheckBox CBLT8;

	@FXML
	private CheckBox CBLT9;

	@FXML
	private CheckBox CBLT10;

	@FXML
	private CheckBox CBLT11;

	@FXML
	private CheckBox CBLT12;

	@FXML
	private CheckBox CBLRT1;

	@FXML
	private CheckBox CBLRT2;

	@FXML
	private CheckBox CBLRT3;

	@FXML
	private CheckBox CBLRT4;

	@FXML
	private CheckBox CBLRT5;

	@FXML
	private CheckBox CBLRT6;

	@FXML
	private CheckBox CBLRT7;

	@FXML
	private CheckBox CBLRT8;

	@FXML
	private CheckBox CBLRT9;

	@FXML
	private CheckBox CBLRT10;

	@FXML
	private CheckBox CBLRT11;

	@FXML
	private CheckBox CBLRT12;

	@FXML
	private CheckBox CBOPT1;

	@FXML
	private CheckBox CBOPT2;

	@FXML
	private CheckBox CBOPT3;

	@FXML
	private CheckBox CBOPT4;

	@FXML
	private CheckBox CBOPT5;

	@FXML
	private CheckBox CBOPT6;

	@FXML
	private CheckBox CBOPT7;

	@FXML
	private CheckBox CBOPT8;

	@FXML
	private CheckBox CBOPT9;

	@FXML
	private CheckBox CBOPT10;

	@FXML
	private CheckBox CBOPT11;

	@FXML
	private CheckBox CBOPT12;

	@FXML
	void onCBCDT10Action(ActionEvent event) {
		CBLRT10.setSelected(false);
		CBLT10.setSelected(false);
		CBFHT10.setSelected(false);
		CBCRT10.setSelected(false);
		CBWRT10.setSelected(false);
		CBWDT10.setSelected(false);
		CBEHT10.setSelected(false);
		CBSRT10.setSelected(false);
		CBST10.setSelected(false);
	}

	@FXML
	void onCBCDT11Action(ActionEvent event) {
		CBLRT11.setSelected(false);
		CBLT11.setSelected(false);
		CBFHT11.setSelected(false);
		CBCRT11.setSelected(false);
		CBWRT11.setSelected(false);
		CBWDT11.setSelected(false);
		CBEHT11.setSelected(false);
		CBSRT11.setSelected(false);
		CBST11.setSelected(false);
	}

	@FXML
	void onCBCDT12Action(ActionEvent event) {
		CBLRT12.setSelected(false);
		CBLT12.setSelected(false);
		CBFHT12.setSelected(false);
		CBCRT12.setSelected(false);
		CBWRT12.setSelected(false);
		CBWDT12.setSelected(false);
		CBEHT12.setSelected(false);
		CBSRT12.setSelected(false);
		CBST12.setSelected(false);
	}

	@FXML
	void onCBCDT1Action(ActionEvent event) {
		CBLRT1.setSelected(false);
		CBLT1.setSelected(false);
		CBFHT1.setSelected(false);
		CBCRT1.setSelected(false);
		CBWRT1.setSelected(false);
		CBWDT1.setSelected(false);
		CBEHT1.setSelected(false);
		CBSRT1.setSelected(false);
		CBST1.setSelected(false);
	}

	@FXML
	void onCBCDT2Action(ActionEvent event) {
		CBLRT2.setSelected(false);
		CBLT2.setSelected(false);
		CBFHT2.setSelected(false);
		CBCRT2.setSelected(false);
		CBWRT2.setSelected(false);
		CBWDT2.setSelected(false);
		CBEHT2.setSelected(false);
		CBSRT2.setSelected(false);
		CBST2.setSelected(false);
	}

	@FXML
	void onCBCDT3Action(ActionEvent event) {
		CBLRT3.setSelected(false);
		CBLT3.setSelected(false);
		CBFHT3.setSelected(false);
		CBCRT3.setSelected(false);
		CBWRT3.setSelected(false);
		CBWDT3.setSelected(false);
		CBEHT3.setSelected(false);
		CBSRT3.setSelected(false);
		CBST3.setSelected(false);
	}

	@FXML
	void onCBCDT4Action(ActionEvent event) {
		CBLRT4.setSelected(false);
		CBLT4.setSelected(false);
		CBFHT4.setSelected(false);
		CBCRT4.setSelected(false);
		CBWRT4.setSelected(false);
		CBWDT4.setSelected(false);
		CBEHT4.setSelected(false);
		CBSRT4.setSelected(false);
		CBST4.setSelected(false);
	}

	@FXML
	void onCBCDT5Action(ActionEvent event) {
		CBLRT5.setSelected(false);
		CBLT5.setSelected(false);
		CBFHT5.setSelected(false);
		CBCRT5.setSelected(false);
		CBWRT5.setSelected(false);
		CBWDT5.setSelected(false);
		CBEHT5.setSelected(false);
		CBSRT5.setSelected(false);
		CBST5.setSelected(false);
	}

	@FXML
	void onCBCDT6Action(ActionEvent event) {
		CBLRT6.setSelected(false);
		CBLT6.setSelected(false);
		CBFHT6.setSelected(false);
		CBCRT6.setSelected(false);
		CBWRT6.setSelected(false);
		CBWDT6.setSelected(false);
		CBEHT6.setSelected(false);
		CBSRT6.setSelected(false);
		CBST6.setSelected(false);
	}

	@FXML
	void onCBCDT7Action(ActionEvent event) {
		CBLRT7.setSelected(false);
		CBLT7.setSelected(false);
		CBFHT7.setSelected(false);
		CBCRT7.setSelected(false);
		CBWRT7.setSelected(false);
		CBWDT7.setSelected(false);
		CBEHT7.setSelected(false);
		CBSRT7.setSelected(false);
		CBST7.setSelected(false);
	}

	@FXML
	void onCBCDT8Action(ActionEvent event) {
		CBLRT8.setSelected(false);
		CBLT8.setSelected(false);
		CBFHT8.setSelected(false);
		CBCRT8.setSelected(false);
		CBWRT8.setSelected(false);
		CBWDT8.setSelected(false);
		CBEHT8.setSelected(false);
		CBSRT8.setSelected(false);
		CBST8.setSelected(false);
	}

	@FXML
	void onCBCDT9Action(ActionEvent event) {
		CBLRT9.setSelected(false);
		CBLT9.setSelected(false);
		CBFHT9.setSelected(false);
		CBCRT9.setSelected(false);
		CBWRT9.setSelected(false);
		CBWDT9.setSelected(false);
		CBEHT9.setSelected(false);
		CBSRT9.setSelected(false);
		CBST9.setSelected(false);
	}

	@FXML
	void onCBCRT10Action(ActionEvent event) {
		CBLRT10.setSelected(false);
		CBLT10.setSelected(false);
		CBFHT10.setSelected(false);
		CBCDT10.setSelected(false);
		CBWRT10.setSelected(false);
		CBWDT10.setSelected(false);
		CBEHT10.setSelected(false);
		CBSRT10.setSelected(false);
		CBST10.setSelected(false);
	}

	@FXML
	void onCBCRT11Action(ActionEvent event) {
		CBLRT11.setSelected(false);
		CBLT11.setSelected(false);
		CBFHT11.setSelected(false);
		CBCDT11.setSelected(false);
		CBWRT11.setSelected(false);
		CBWDT11.setSelected(false);
		CBEHT11.setSelected(false);
		CBSRT11.setSelected(false);
		CBST11.setSelected(false);
	}

	@FXML
	void onCBCRT12Action(ActionEvent event) {
		CBLRT12.setSelected(false);
		CBLT12.setSelected(false);
		CBFHT12.setSelected(false);
		CBCDT12.setSelected(false);
		CBWRT12.setSelected(false);
		CBWDT12.setSelected(false);
		CBEHT12.setSelected(false);
		CBSRT12.setSelected(false);
		CBST12.setSelected(false);
	}

	@FXML
	void onCBCRT1Action(ActionEvent event) {
		CBLRT1.setSelected(false);
		CBLT1.setSelected(false);
		CBFHT1.setSelected(false);
		CBCDT1.setSelected(false);
		CBWRT1.setSelected(false);
		CBWDT1.setSelected(false);
		CBEHT1.setSelected(false);
		CBSRT1.setSelected(false);
		CBST1.setSelected(false);
	}

	@FXML
	void onCBCRT2Action(ActionEvent event) {
		CBLRT2.setSelected(false);
		CBLT2.setSelected(false);
		CBFHT2.setSelected(false);
		CBCDT2.setSelected(false);
		CBWRT2.setSelected(false);
		CBWDT2.setSelected(false);
		CBEHT2.setSelected(false);
		CBSRT2.setSelected(false);
		CBST2.setSelected(false);
	}

	@FXML
	void onCBCRT3Action(ActionEvent event) {
		CBLRT3.setSelected(false);
		CBLT3.setSelected(false);
		CBFHT3.setSelected(false);
		CBCDT3.setSelected(false);
		CBWRT3.setSelected(false);
		CBWDT3.setSelected(false);
		CBEHT3.setSelected(false);
		CBSRT3.setSelected(false);
		CBST3.setSelected(false);
	}

	@FXML
	void onCBCRT4Action(ActionEvent event) {
		CBLRT4.setSelected(false);
		CBLT4.setSelected(false);
		CBFHT4.setSelected(false);
		CBCDT4.setSelected(false);
		CBWRT4.setSelected(false);
		CBWDT4.setSelected(false);
		CBEHT4.setSelected(false);
		CBSRT4.setSelected(false);
		CBST4.setSelected(false);
	}

	@FXML
	void onCBCRT5Action(ActionEvent event) {
		CBLRT5.setSelected(false);
		CBLT5.setSelected(false);
		CBFHT5.setSelected(false);
		CBCDT5.setSelected(false);
		CBWRT5.setSelected(false);
		CBWDT5.setSelected(false);
		CBEHT5.setSelected(false);
		CBSRT5.setSelected(false);
		CBST5.setSelected(false);
	}

	@FXML
	void onCBCRT6Action(ActionEvent event) {
		CBLRT6.setSelected(false);
		CBLT6.setSelected(false);
		CBFHT6.setSelected(false);
		CBCDT6.setSelected(false);
		CBWRT6.setSelected(false);
		CBWDT6.setSelected(false);
		CBEHT6.setSelected(false);
		CBSRT6.setSelected(false);
		CBST6.setSelected(false);
	}

	@FXML
	void onCBCRT7Action(ActionEvent event) {
		CBLRT7.setSelected(false);
		CBLT7.setSelected(false);
		CBFHT7.setSelected(false);
		CBCDT7.setSelected(false);
		CBWRT7.setSelected(false);
		CBWDT7.setSelected(false);
		CBEHT7.setSelected(false);
		CBSRT7.setSelected(false);
		CBST7.setSelected(false);
	}

	@FXML
	void onCBCRT8Action(ActionEvent event) {
		CBLRT8.setSelected(false);
		CBLT8.setSelected(false);
		CBFHT8.setSelected(false);
		CBCDT8.setSelected(false);
		CBWRT8.setSelected(false);
		CBWDT8.setSelected(false);
		CBEHT8.setSelected(false);
		CBSRT8.setSelected(false);
		CBST8.setSelected(false);
	}

	@FXML
	void onCBCRT9Action(ActionEvent event) {
		CBLRT9.setSelected(false);
		CBLT9.setSelected(false);
		CBFHT9.setSelected(false);
		CBCDT9.setSelected(false);
		CBWRT9.setSelected(false);
		CBWDT9.setSelected(false);
		CBEHT9.setSelected(false);
		CBSRT9.setSelected(false);
		CBST9.setSelected(false);
	}

	@FXML
	void onCBEHT10Action(ActionEvent event) {
		CBLRT10.setSelected(false);
		CBLT10.setSelected(false);
		CBFHT10.setSelected(false);
		CBCRT10.setSelected(false);
		CBCDT10.setSelected(false);
		CBWRT10.setSelected(false);
		CBWDT10.setSelected(false);
		CBSRT10.setSelected(false);
		CBST10.setSelected(false);
	}

	@FXML
	void onCBEHT11Action(ActionEvent event) {
		CBLRT11.setSelected(false);
		CBLT11.setSelected(false);
		CBFHT11.setSelected(false);
		CBCRT11.setSelected(false);
		CBCDT11.setSelected(false);
		CBWRT11.setSelected(false);
		CBWDT11.setSelected(false);
		CBSRT11.setSelected(false);
		CBST11.setSelected(false);
	}

	@FXML
	void onCBEHT12Action(ActionEvent event) {
		CBLRT12.setSelected(false);
		CBLT12.setSelected(false);
		CBFHT12.setSelected(false);
		CBCRT12.setSelected(false);
		CBCDT12.setSelected(false);
		CBWRT12.setSelected(false);
		CBWDT12.setSelected(false);
		CBSRT12.setSelected(false);
		CBST12.setSelected(false);
	}

	@FXML
	void onCBEHT1Action(ActionEvent event) {
		CBLRT1.setSelected(false);
		CBLT1.setSelected(false);
		CBFHT1.setSelected(false);
		CBCRT1.setSelected(false);
		CBCDT1.setSelected(false);
		CBWRT1.setSelected(false);
		CBWDT1.setSelected(false);
		CBSRT1.setSelected(false);
		CBST1.setSelected(false);
	}

	@FXML
	void onCBEHT2Action(ActionEvent event) {
		CBLRT2.setSelected(false);
		CBLT2.setSelected(false);
		CBFHT2.setSelected(false);
		CBCRT2.setSelected(false);
		CBCDT2.setSelected(false);
		CBWRT2.setSelected(false);
		CBWDT2.setSelected(false);
		CBSRT2.setSelected(false);
		CBST2.setSelected(false);
	}

	@FXML
	void onCBEHT3Action(ActionEvent event) {
		CBLRT3.setSelected(false);
		CBLT3.setSelected(false);
		CBFHT3.setSelected(false);
		CBCRT3.setSelected(false);
		CBCDT3.setSelected(false);
		CBWRT3.setSelected(false);
		CBWDT3.setSelected(false);
		CBSRT3.setSelected(false);
		CBST3.setSelected(false);
	}

	@FXML
	void onCBEHT4Action(ActionEvent event) {
		CBLRT4.setSelected(false);
		CBLT4.setSelected(false);
		CBFHT4.setSelected(false);
		CBCRT4.setSelected(false);
		CBCDT4.setSelected(false);
		CBWRT4.setSelected(false);
		CBWDT4.setSelected(false);
		CBSRT4.setSelected(false);
		CBST4.setSelected(false);
	}

	@FXML
	void onCBEHT5Action(ActionEvent event) {
		CBLRT5.setSelected(false);
		CBLT5.setSelected(false);
		CBFHT5.setSelected(false);
		CBCRT5.setSelected(false);
		CBCDT5.setSelected(false);
		CBWRT5.setSelected(false);
		CBWDT5.setSelected(false);
		CBSRT5.setSelected(false);
		CBST5.setSelected(false);
	}

	@FXML
	void onCBEHT6Action(ActionEvent event) {
		CBLRT6.setSelected(false);
		CBLT6.setSelected(false);
		CBFHT6.setSelected(false);
		CBCRT6.setSelected(false);
		CBCDT6.setSelected(false);
		CBWRT6.setSelected(false);
		CBWDT6.setSelected(false);
		CBSRT6.setSelected(false);
		CBST6.setSelected(false);
	}

	@FXML
	void onCBEHT7Action(ActionEvent event) {
		CBLRT7.setSelected(false);
		CBLT7.setSelected(false);
		CBFHT7.setSelected(false);
		CBCRT7.setSelected(false);
		CBCDT7.setSelected(false);
		CBWRT7.setSelected(false);
		CBWDT7.setSelected(false);
		CBSRT7.setSelected(false);
		CBST7.setSelected(false);
	}

	@FXML
	void onCBEHT8Action(ActionEvent event) {
		CBLRT8.setSelected(false);
		CBLT8.setSelected(false);
		CBFHT8.setSelected(false);
		CBCRT8.setSelected(false);
		CBCDT8.setSelected(false);
		CBWRT8.setSelected(false);
		CBWDT8.setSelected(false);
		CBSRT8.setSelected(false);
		CBST8.setSelected(false);
	}

	@FXML
	void onCBEHT9Action(ActionEvent event) {
		CBLRT9.setSelected(false);
		CBLT9.setSelected(false);
		CBFHT9.setSelected(false);
		CBCRT9.setSelected(false);
		CBCDT9.setSelected(false);
		CBWRT9.setSelected(false);
		CBWDT9.setSelected(false);
		CBSRT9.setSelected(false);
		CBST9.setSelected(false);
	}

	@FXML
	void onCBFHT10Action(ActionEvent event) {
		CBLRT10.setSelected(false);
		CBLT10.setSelected(false);
		CBCRT10.setSelected(false);
		CBCDT10.setSelected(false);
		CBWRT10.setSelected(false);
		CBWDT10.setSelected(false);
		CBEHT10.setSelected(false);
		CBSRT10.setSelected(false);
		CBST10.setSelected(false);
	}

	@FXML
	void onCBFHT11Action(ActionEvent event) {
		CBLRT11.setSelected(false);
		CBLT11.setSelected(false);
		CBCRT11.setSelected(false);
		CBCDT11.setSelected(false);
		CBWRT11.setSelected(false);
		CBWDT11.setSelected(false);
		CBEHT11.setSelected(false);
		CBSRT11.setSelected(false);
		CBST11.setSelected(false);
	}

	@FXML
	void onCBFHT12Action(ActionEvent event) {
		CBLRT12.setSelected(false);
		CBLT12.setSelected(false);
		CBCRT12.setSelected(false);
		CBCDT12.setSelected(false);
		CBWRT12.setSelected(false);
		CBWDT12.setSelected(false);
		CBEHT12.setSelected(false);
		CBSRT12.setSelected(false);
		CBST12.setSelected(false);
	}

	@FXML
	void onCBFHT1Action(ActionEvent event) {
		CBLRT1.setSelected(false);
		CBLT1.setSelected(false);
		CBCRT1.setSelected(false);
		CBCDT1.setSelected(false);
		CBWRT1.setSelected(false);
		CBWDT1.setSelected(false);
		CBEHT1.setSelected(false);
		CBSRT1.setSelected(false);
		CBST1.setSelected(false);
	}

	@FXML
	void onCBFHT2Action(ActionEvent event) {
		CBLRT2.setSelected(false);
		CBLT2.setSelected(false);
		CBCRT2.setSelected(false);
		CBCDT2.setSelected(false);
		CBWRT2.setSelected(false);
		CBWDT2.setSelected(false);
		CBEHT2.setSelected(false);
		CBSRT2.setSelected(false);
		CBST2.setSelected(false);
	}

	@FXML
	void onCBFHT3Action(ActionEvent event) {
		CBLRT3.setSelected(false);
		CBLT3.setSelected(false);
		CBCRT3.setSelected(false);
		CBCDT3.setSelected(false);
		CBWRT3.setSelected(false);
		CBWDT3.setSelected(false);
		CBEHT3.setSelected(false);
		CBSRT3.setSelected(false);
		CBST3.setSelected(false);
	}

	@FXML
	void onCBFHT4Action(ActionEvent event) {
		CBLRT4.setSelected(false);
		CBLT4.setSelected(false);
		CBCRT4.setSelected(false);
		CBCDT4.setSelected(false);
		CBWRT4.setSelected(false);
		CBWDT4.setSelected(false);
		CBEHT4.setSelected(false);
		CBSRT4.setSelected(false);
		CBST4.setSelected(false);
	}

	@FXML
	void onCBFHT5Action(ActionEvent event) {
		CBLRT5.setSelected(false);
		CBLT5.setSelected(false);
		CBCRT5.setSelected(false);
		CBCDT5.setSelected(false);
		CBWRT5.setSelected(false);
		CBWDT5.setSelected(false);
		CBEHT5.setSelected(false);
		CBSRT5.setSelected(false);
		CBST5.setSelected(false);
	}

	@FXML
	void onCBFHT6Action(ActionEvent event) {
		CBLRT6.setSelected(false);
		CBLT6.setSelected(false);
		CBCRT6.setSelected(false);
		CBCDT6.setSelected(false);
		CBWRT6.setSelected(false);
		CBWDT6.setSelected(false);
		CBEHT6.setSelected(false);
		CBSRT6.setSelected(false);
		CBST6.setSelected(false);
	}

	@FXML
	void onCBFHT7Action(ActionEvent event) {
		CBLRT7.setSelected(false);
		CBLT7.setSelected(false);
		CBCRT7.setSelected(false);
		CBCDT7.setSelected(false);
		CBWRT7.setSelected(false);
		CBWDT7.setSelected(false);
		CBEHT7.setSelected(false);
		CBSRT7.setSelected(false);
		CBST7.setSelected(false);
	}

	@FXML
	void onCBFHT8Action(ActionEvent event) {
		CBLRT8.setSelected(false);
		CBLT8.setSelected(false);
		CBCRT8.setSelected(false);
		CBCDT8.setSelected(false);
		CBWRT8.setSelected(false);
		CBWDT8.setSelected(false);
		CBEHT8.setSelected(false);
		CBSRT8.setSelected(false);
		CBST8.setSelected(false);
	}

	@FXML
	void onCBFHT9Action(ActionEvent event) {
		CBLRT9.setSelected(false);
		CBLT9.setSelected(false);
		CBCRT9.setSelected(false);
		CBCDT9.setSelected(false);
		CBWRT9.setSelected(false);
		CBWDT9.setSelected(false);
		CBEHT9.setSelected(false);
		CBSRT9.setSelected(false);
		CBST9.setSelected(false);
	}

	@FXML
	void onCBLRT10Action(ActionEvent event) {
		CBLT10.setSelected(false);
		CBFHT10.setSelected(false);
		CBCRT10.setSelected(false);
		CBCDT10.setSelected(false);
		CBWRT10.setSelected(false);
		CBWDT10.setSelected(false);
		CBEHT10.setSelected(false);
		CBSRT10.setSelected(false);
		CBST10.setSelected(false);
	}

	@FXML
	void onCBLRT11Action(ActionEvent event) {
		CBLT11.setSelected(false);
		CBFHT11.setSelected(false);
		CBCRT11.setSelected(false);
		CBCDT11.setSelected(false);
		CBWRT11.setSelected(false);
		CBWDT11.setSelected(false);
		CBEHT11.setSelected(false);
		CBSRT11.setSelected(false);
		CBST11.setSelected(false);
	}

	@FXML
	void onCBLRT12Action(ActionEvent event) {
		CBLT12.setSelected(false);
		CBFHT12.setSelected(false);
		CBCRT12.setSelected(false);
		CBCDT12.setSelected(false);
		CBWRT12.setSelected(false);
		CBWDT12.setSelected(false);
		CBEHT12.setSelected(false);
		CBSRT12.setSelected(false);
		CBST12.setSelected(false);
	}

	@FXML
	void onCBLRT1Action(ActionEvent event) {
		CBLT1.setSelected(false);
		CBFHT1.setSelected(false);
		CBCRT1.setSelected(false);
		CBCDT1.setSelected(false);
		CBWRT1.setSelected(false);
		CBWDT1.setSelected(false);
		CBEHT1.setSelected(false);
		CBSRT1.setSelected(false);
		CBST1.setSelected(false);
	}

	@FXML
	void onCBLRT2Action(ActionEvent event) {
		CBLT2.setSelected(false);
		CBFHT2.setSelected(false);
		CBCRT2.setSelected(false);
		CBCDT2.setSelected(false);
		CBWRT2.setSelected(false);
		CBWDT2.setSelected(false);
		CBEHT2.setSelected(false);
		CBSRT2.setSelected(false);
		CBST2.setSelected(false);
	}

	@FXML
	void onCBLRT3Action(ActionEvent event) {
		CBLT3.setSelected(false);
		CBFHT3.setSelected(false);
		CBCRT3.setSelected(false);
		CBCDT3.setSelected(false);
		CBWRT3.setSelected(false);
		CBWDT3.setSelected(false);
		CBEHT3.setSelected(false);
		CBSRT3.setSelected(false);
		CBST3.setSelected(false);
	}

	@FXML
	void onCBLRT4Action(ActionEvent event) {
		CBLT4.setSelected(false);
		CBFHT4.setSelected(false);
		CBCRT4.setSelected(false);
		CBCDT4.setSelected(false);
		CBWRT4.setSelected(false);
		CBWDT4.setSelected(false);
		CBEHT4.setSelected(false);
		CBSRT4.setSelected(false);
		CBST4.setSelected(false);
	}

	@FXML
	void onCBLRT5Action(ActionEvent event) {
		CBLT5.setSelected(false);
		CBFHT5.setSelected(false);
		CBCRT5.setSelected(false);
		CBCDT5.setSelected(false);
		CBWRT5.setSelected(false);
		CBWDT5.setSelected(false);
		CBEHT5.setSelected(false);
		CBSRT5.setSelected(false);
		CBST5.setSelected(false);
	}

	@FXML
	void onCBLRT6Action(ActionEvent event) {
		CBLT6.setSelected(false);
		CBFHT6.setSelected(false);
		CBCRT6.setSelected(false);
		CBCDT6.setSelected(false);
		CBWRT6.setSelected(false);
		CBWDT6.setSelected(false);
		CBEHT6.setSelected(false);
		CBSRT6.setSelected(false);
		CBST6.setSelected(false);
	}

	@FXML
	void onCBLRT7Action(ActionEvent event) {
		CBLT7.setSelected(false);
		CBFHT7.setSelected(false);
		CBCRT7.setSelected(false);
		CBCDT7.setSelected(false);
		CBWRT7.setSelected(false);
		CBWDT7.setSelected(false);
		CBEHT7.setSelected(false);
		CBSRT7.setSelected(false);
		CBST7.setSelected(false);
	}

	@FXML
	void onCBLRT8Action(ActionEvent event) {
		CBLT8.setSelected(false);
		CBFHT8.setSelected(false);
		CBCRT8.setSelected(false);
		CBCDT8.setSelected(false);
		CBWRT8.setSelected(false);
		CBWDT8.setSelected(false);
		CBEHT8.setSelected(false);
		CBSRT8.setSelected(false);
		CBST8.setSelected(false);
	}

	@FXML
	void onCBLRT9Action(ActionEvent event) {
		CBLT9.setSelected(false);
		CBFHT9.setSelected(false);
		CBCRT9.setSelected(false);
		CBCDT9.setSelected(false);
		CBWRT9.setSelected(false);
		CBWDT9.setSelected(false);
		CBEHT9.setSelected(false);
		CBSRT9.setSelected(false);
		CBST9.setSelected(false);
	}

	@FXML
	void onCBLT10Action(ActionEvent event) {
		CBLRT10.setSelected(false);
		CBFHT10.setSelected(false);
		CBCRT10.setSelected(false);
		CBCDT10.setSelected(false);
		CBWRT10.setSelected(false);
		CBWDT10.setSelected(false);
		CBEHT10.setSelected(false);
		CBSRT10.setSelected(false);
		CBST10.setSelected(false);
	}

	@FXML
	void onCBLT11Action(ActionEvent event) {
		CBLRT11.setSelected(false);
		CBFHT11.setSelected(false);
		CBCRT11.setSelected(false);
		CBCDT11.setSelected(false);
		CBWRT11.setSelected(false);
		CBWDT11.setSelected(false);
		CBEHT11.setSelected(false);
		CBSRT11.setSelected(false);
		CBST11.setSelected(false);
	}

	@FXML
	void onCBLT12Action(ActionEvent event) {
		CBLRT12.setSelected(false);
		CBFHT12.setSelected(false);
		CBCRT12.setSelected(false);
		CBCDT12.setSelected(false);
		CBWRT12.setSelected(false);
		CBWDT12.setSelected(false);
		CBEHT12.setSelected(false);
		CBSRT12.setSelected(false);
		CBST12.setSelected(false);
	}

	@FXML
	void onCBLT1Action(ActionEvent event) {
		CBLRT1.setSelected(false);
		CBFHT1.setSelected(false);
		CBCRT1.setSelected(false);
		CBCDT1.setSelected(false);
		CBWRT1.setSelected(false);
		CBWDT1.setSelected(false);
		CBEHT1.setSelected(false);
		CBSRT1.setSelected(false);
		CBST1.setSelected(false);
	}

	@FXML
	void onCBLT2Action(ActionEvent event) {
		CBLRT2.setSelected(false);
		CBFHT2.setSelected(false);
		CBCRT2.setSelected(false);
		CBCDT2.setSelected(false);
		CBWRT2.setSelected(false);
		CBWDT2.setSelected(false);
		CBEHT2.setSelected(false);
		CBSRT2.setSelected(false);
		CBST2.setSelected(false);
	}

	@FXML
	void onCBLT3Action(ActionEvent event) {
		CBLRT3.setSelected(false);
		CBFHT3.setSelected(false);
		CBCRT3.setSelected(false);
		CBCDT3.setSelected(false);
		CBWRT3.setSelected(false);
		CBWDT3.setSelected(false);
		CBEHT3.setSelected(false);
		CBSRT3.setSelected(false);
		CBST3.setSelected(false);
	}

	@FXML
	void onCBLT4Action(ActionEvent event) {
		CBLRT4.setSelected(false);
		CBFHT4.setSelected(false);
		CBCRT4.setSelected(false);
		CBCDT4.setSelected(false);
		CBWRT4.setSelected(false);
		CBWDT4.setSelected(false);
		CBEHT4.setSelected(false);
		CBSRT4.setSelected(false);
		CBST4.setSelected(false);
	}

	@FXML
	void onCBLT5Action(ActionEvent event) {
		CBLRT5.setSelected(false);
		CBFHT5.setSelected(false);
		CBCRT5.setSelected(false);
		CBCDT5.setSelected(false);
		CBWRT5.setSelected(false);
		CBWDT5.setSelected(false);
		CBEHT5.setSelected(false);
		CBSRT5.setSelected(false);
		CBST5.setSelected(false);
	}

	@FXML
	void onCBLT6Action(ActionEvent event) {
		CBLRT6.setSelected(false);
		CBFHT6.setSelected(false);
		CBCRT6.setSelected(false);
		CBCDT6.setSelected(false);
		CBWRT6.setSelected(false);
		CBWDT6.setSelected(false);
		CBEHT6.setSelected(false);
		CBSRT6.setSelected(false);
		CBST6.setSelected(false);
	}

	@FXML
	void onCBLT7Action(ActionEvent event) {
		CBLRT7.setSelected(false);
		CBFHT7.setSelected(false);
		CBCRT7.setSelected(false);
		CBCDT7.setSelected(false);
		CBWRT7.setSelected(false);
		CBWDT7.setSelected(false);
		CBEHT7.setSelected(false);
		CBSRT7.setSelected(false);
		CBST7.setSelected(false);
	}

	@FXML
	void onCBLT8Action(ActionEvent event) {
		CBLRT8.setSelected(false);
		CBFHT8.setSelected(false);
		CBCRT8.setSelected(false);
		CBCDT8.setSelected(false);
		CBWRT8.setSelected(false);
		CBWDT8.setSelected(false);
		CBEHT8.setSelected(false);
		CBSRT8.setSelected(false);
		CBST8.setSelected(false);
	}

	@FXML
	void onCBLT9Action(ActionEvent event) {
		CBLRT9.setSelected(false);
		CBFHT9.setSelected(false);
		CBCRT9.setSelected(false);
		CBCDT9.setSelected(false);
		CBWRT9.setSelected(false);
		CBWDT9.setSelected(false);
		CBEHT9.setSelected(false);
		CBSRT9.setSelected(false);
		CBST9.setSelected(false);
	}

	@FXML
	void onCBOPT1Action(ActionEvent event) {
		if (CBOPT1.isSelected()) {
			CBOPT1.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
			InitialFaceT1.setDisable(true);
			CBLRT1.setDisable(true);
			CBLT1.setDisable(true);
			CBFHT1.setDisable(true);
			CBCRT1.setDisable(true);
			CBCDT1.setDisable(true);
			CBWRT1.setDisable(true);
			CBWDT1.setDisable(true);
			CBEHT1.setDisable(true);
			CBSRT1.setDisable(true);
			CBST1.setDisable(true);
		} else {
			CBOPT1.setStyle("-fx-body-color: GREEN; -fx-mark-color: WHITE");
			InitialFaceT1.setDisable(false);
			CBLRT1.setDisable(false);
			CBLT1.setDisable(false);
			CBFHT1.setDisable(false);
			CBCRT1.setDisable(false);
			CBCDT1.setDisable(false);
			CBWRT1.setDisable(false);
			CBWDT1.setDisable(false);
			CBEHT1.setDisable(false);
			CBSRT1.setDisable(false);
			CBST1.setDisable(false);
		}
	}

	@FXML
	void onCBOPT2Action(ActionEvent event) {
		if (CBOPT2.isSelected()) {
			CBOPT2.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
			InitialFaceT2.setDisable(true);
			CBLRT2.setDisable(true);
			CBLT2.setDisable(true);
			CBFHT2.setDisable(true);
			CBCRT2.setDisable(true);
			CBCDT2.setDisable(true);
			CBWRT2.setDisable(true);
			CBWDT2.setDisable(true);
			CBEHT2.setDisable(true);
			CBSRT2.setDisable(true);
			CBST2.setDisable(true);
		} else {
			CBOPT2.setStyle("-fx-body-color: GREEN; -fx-mark-color: WHITE");
			InitialFaceT2.setDisable(false);
			CBLRT2.setDisable(false);
			CBLT2.setDisable(false);
			CBFHT2.setDisable(false);
			CBCRT2.setDisable(false);
			CBCDT2.setDisable(false);
			CBWRT2.setDisable(false);
			CBWDT2.setDisable(false);
			CBEHT2.setDisable(false);
			CBSRT2.setDisable(false);
			CBST2.setDisable(false);
		}
	}

	@FXML
	void onCBOPT3Action(ActionEvent event) {
		if (CBOPT3.isSelected()) {
			CBOPT3.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
			InitialFaceT3.setDisable(true);
			CBLRT3.setDisable(true);
			CBLT3.setDisable(true);
			CBFHT3.setDisable(true);
			CBCRT3.setDisable(true);
			CBCDT3.setDisable(true);
			CBWRT3.setDisable(true);
			CBWDT3.setDisable(true);
			CBEHT3.setDisable(true);
			CBSRT3.setDisable(true);
			CBST3.setDisable(true);
		} else {
			CBOPT3.setStyle("-fx-body-color: GREEN; -fx-mark-color: WHITE");
			InitialFaceT3.setDisable(false);
			CBLRT3.setDisable(false);
			CBLT3.setDisable(false);
			CBFHT3.setDisable(false);
			CBCRT3.setDisable(false);
			CBCDT3.setDisable(false);
			CBWRT3.setDisable(false);
			CBWDT3.setDisable(false);
			CBEHT3.setDisable(false);
			CBSRT3.setDisable(false);
			CBST3.setDisable(false);
		}
	}

	@FXML
	void onCBOPT4Action(ActionEvent event) {
		if (CBOPT4.isSelected()) {
			CBOPT4.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
			InitialFaceT4.setDisable(true);
			CBLRT4.setDisable(true);
			CBLT4.setDisable(true);
			CBFHT4.setDisable(true);
			CBCRT4.setDisable(true);
			CBCDT4.setDisable(true);
			CBWRT4.setDisable(true);
			CBWDT4.setDisable(true);
			CBEHT4.setDisable(true);
			CBSRT4.setDisable(true);
			CBST4.setDisable(true);
		} else {
			CBOPT4.setStyle("-fx-body-color: GREEN; -fx-mark-color: WHITE");
			InitialFaceT4.setDisable(false);
			CBLRT4.setDisable(false);
			CBLT4.setDisable(false);
			CBFHT4.setDisable(false);
			CBCRT4.setDisable(false);
			CBCDT4.setDisable(false);
			CBWRT4.setDisable(false);
			CBWDT4.setDisable(false);
			CBEHT4.setDisable(false);
			CBSRT4.setDisable(false);
			CBST4.setDisable(false);
		}
	}

	@FXML
	void onCBOPT5Action(ActionEvent event) {
		if (CBOPT5.isSelected()) {
			CBOPT5.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
			InitialFaceT5.setDisable(true);
			CBLRT5.setDisable(true);
			CBLT5.setDisable(true);
			CBFHT5.setDisable(true);
			CBCRT5.setDisable(true);
			CBCDT5.setDisable(true);
			CBWRT5.setDisable(true);
			CBWDT5.setDisable(true);
			CBEHT5.setDisable(true);
			CBSRT5.setDisable(true);
			CBST5.setDisable(true);
		} else {
			CBOPT5.setStyle("-fx-body-color: GREEN; -fx-mark-color: WHITE");
			InitialFaceT5.setDisable(false);
			CBLRT5.setDisable(false);
			CBLT5.setDisable(false);
			CBFHT5.setDisable(false);
			CBCRT5.setDisable(false);
			CBCDT5.setDisable(false);
			CBWRT5.setDisable(false);
			CBWDT5.setDisable(false);
			CBEHT5.setDisable(false);
			CBSRT5.setDisable(false);
			CBST5.setDisable(false);
		}
	}

	@FXML
	void onCBOPT6Action(ActionEvent event) {
		if (CBOPT6.isSelected()) {
			CBOPT6.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
			InitialFaceT6.setDisable(true);
			CBLRT6.setDisable(true);
			CBLT6.setDisable(true);
			CBFHT6.setDisable(true);
			CBCRT6.setDisable(true);
			CBCDT6.setDisable(true);
			CBWRT6.setDisable(true);
			CBWDT6.setDisable(true);
			CBEHT6.setDisable(true);
			CBSRT6.setDisable(true);
			CBST6.setDisable(true);
		} else {
			CBOPT6.setStyle("-fx-body-color: GREEN; -fx-mark-color: WHITE");
			InitialFaceT6.setDisable(false);
			CBLRT6.setDisable(false);
			CBLT6.setDisable(false);
			CBFHT6.setDisable(false);
			CBCRT6.setDisable(false);
			CBCDT6.setDisable(false);
			CBWRT6.setDisable(false);
			CBWDT6.setDisable(false);
			CBEHT6.setDisable(false);
			CBSRT6.setDisable(false);
			CBST6.setDisable(false);
		}
	}

	@FXML
	void onCBOPT7Action(ActionEvent event) {
		if (CBOPT7.isSelected()) {
			CBOPT7.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
			InitialFaceT7.setDisable(true);
			CBLRT7.setDisable(true);
			CBLT7.setDisable(true);
			CBFHT7.setDisable(true);
			CBCRT7.setDisable(true);
			CBCDT7.setDisable(true);
			CBWRT7.setDisable(true);
			CBWDT7.setDisable(true);
			CBEHT7.setDisable(true);
			CBSRT7.setDisable(true);
			CBST7.setDisable(true);
		} else {
			CBOPT7.setStyle("-fx-body-color: GREEN; -fx-mark-color: WHITE");
			InitialFaceT7.setDisable(false);
			CBLRT7.setDisable(false);
			CBLT7.setDisable(false);
			CBFHT7.setDisable(false);
			CBCRT7.setDisable(false);
			CBCDT7.setDisable(false);
			CBWRT7.setDisable(false);
			CBWDT7.setDisable(false);
			CBEHT7.setDisable(false);
			CBSRT7.setDisable(false);
			CBST7.setDisable(false);
		}
	}

	@FXML
	void onCBOPT8Action(ActionEvent event) {
		if (CBOPT8.isSelected()) {
			CBOPT8.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
			InitialFaceT8.setDisable(true);
			CBLRT8.setDisable(true);
			CBLT8.setDisable(true);
			CBFHT8.setDisable(true);
			CBCRT8.setDisable(true);
			CBCDT8.setDisable(true);
			CBWRT8.setDisable(true);
			CBWDT8.setDisable(true);
			CBEHT8.setDisable(true);
			CBSRT8.setDisable(true);
			CBST8.setDisable(true);
		} else {
			CBOPT8.setStyle("-fx-body-color: GREEN; -fx-mark-color: WHITE");
			InitialFaceT8.setDisable(false);
			CBLRT8.setDisable(false);
			CBLT8.setDisable(false);
			CBFHT8.setDisable(false);
			CBCRT8.setDisable(false);
			CBCDT8.setDisable(false);
			CBWRT8.setDisable(false);
			CBWDT8.setDisable(false);
			CBEHT8.setDisable(false);
			CBSRT8.setDisable(false);
			CBST8.setDisable(false);
		}
	}

	@FXML
	void onCBOPT9Action(ActionEvent event) {
		if (CBOPT9.isSelected()) {
			CBOPT9.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
			InitialFaceT9.setDisable(true);
			CBLRT9.setDisable(true);
			CBLT9.setDisable(true);
			CBFHT9.setDisable(true);
			CBCRT9.setDisable(true);
			CBCDT9.setDisable(true);
			CBWRT9.setDisable(true);
			CBWDT9.setDisable(true);
			CBEHT9.setDisable(true);
			CBSRT9.setDisable(true);
			CBST9.setDisable(true);
		} else {
			CBOPT9.setStyle("-fx-body-color: GREEN; -fx-mark-color: WHITE");
			InitialFaceT9.setDisable(false);
			CBLRT9.setDisable(false);
			CBLT9.setDisable(false);
			CBFHT9.setDisable(false);
			CBCRT9.setDisable(false);
			CBCDT9.setDisable(false);
			CBWRT9.setDisable(false);
			CBWDT9.setDisable(false);
			CBEHT9.setDisable(false);
			CBSRT9.setDisable(false);
			CBST9.setDisable(false);
		}
	}

	@FXML
	void onCBOPT10Action(ActionEvent event) {

		if (CBOPT10.isSelected()) {
			CBOPT10.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
			InitialFaceT10.setDisable(true);
			CBLRT10.setDisable(true);
			CBLT10.setDisable(true);
			CBFHT10.setDisable(true);
			CBCRT10.setDisable(true);
			CBCDT10.setDisable(true);
			CBWRT10.setDisable(true);
			CBWDT10.setDisable(true);
			CBEHT10.setDisable(true);
			CBSRT10.setDisable(true);
			CBST10.setDisable(true);
		} else {
			CBOPT10.setStyle("-fx-body-color: GREEN; -fx-mark-color: WHITE");
			InitialFaceT10.setDisable(false);
			CBLRT10.setDisable(false);
			CBLT10.setDisable(false);
			CBFHT10.setDisable(false);
			CBCRT10.setDisable(false);
			CBCDT10.setDisable(false);
			CBWRT10.setDisable(false);
			CBWDT10.setDisable(false);
			CBEHT10.setDisable(false);
			CBSRT10.setDisable(false);
			CBST10.setDisable(false);
		}
	}

	@FXML
	void onCBOPT11Action(ActionEvent event) {
		if (CBOPT11.isSelected()) {
			CBOPT11.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
			InitialFaceT11.setDisable(true);
			CBLRT11.setDisable(true);
			CBLT11.setDisable(true);
			CBFHT11.setDisable(true);
			CBCRT11.setDisable(true);
			CBCDT11.setDisable(true);
			CBWRT11.setDisable(true);
			CBWDT11.setDisable(true);
			CBEHT11.setDisable(true);
			CBSRT11.setDisable(true);
			CBST11.setDisable(true);
		} else {
			CBOPT11.setStyle("-fx-body-color: GREEN; -fx-mark-color: WHITE");
			InitialFaceT11.setDisable(false);
			CBLRT11.setDisable(false);
			CBLT11.setDisable(false);
			CBFHT11.setDisable(false);
			CBCRT11.setDisable(false);
			CBCDT11.setDisable(false);
			CBWRT11.setDisable(false);
			CBWDT11.setDisable(false);
			CBEHT11.setDisable(false);
			CBSRT11.setDisable(false);
			CBST11.setDisable(false);
		}
	}

	@FXML
	void onCBOPT12Action(ActionEvent event) {
		if (CBOPT12.isSelected()) {
			CBOPT12.setStyle("-fx-body-color: RED; -fx-mark-color: WHITE");
			InitialFaceT12.setDisable(true);
			CBLRT12.setDisable(true);
			CBLT12.setDisable(true);
			CBFHT12.setDisable(true);
			CBCRT12.setDisable(true);
			CBCDT12.setDisable(true);
			CBWRT12.setDisable(true);
			CBWDT12.setDisable(true);
			CBEHT12.setDisable(true);
			CBSRT12.setDisable(true);
			CBST12.setDisable(true);
		} else {
			CBOPT12.setStyle("-fx-body-color: GREEN; -fx-mark-color: WHITE");
			InitialFaceT12.setDisable(false);
			CBLRT12.setDisable(false);
			CBLT12.setDisable(false);
			CBFHT12.setDisable(false);
			CBCRT12.setDisable(false);
			CBCDT12.setDisable(false);
			CBWRT12.setDisable(false);
			CBWDT12.setDisable(false);
			CBEHT12.setDisable(false);
			CBSRT12.setDisable(false);
			CBST12.setDisable(false);
		}
	}

	@FXML
	void onCBSRT10Action(ActionEvent event) {
		CBLRT10.setSelected(false);
		CBLT10.setSelected(false);
		CBFHT10.setSelected(false);
		CBCRT10.setSelected(false);
		CBCDT10.setSelected(false);
		CBWRT10.setSelected(false);
		CBWDT10.setSelected(false);
		CBEHT10.setSelected(false);
		CBST10.setSelected(false);
	}

	@FXML
	void onCBSRT11Action(ActionEvent event) {
		CBLRT11.setSelected(false);
		CBLT11.setSelected(false);
		CBFHT11.setSelected(false);
		CBCRT11.setSelected(false);
		CBCDT11.setSelected(false);
		CBWRT11.setSelected(false);
		CBWDT11.setSelected(false);
		CBEHT11.setSelected(false);
		CBST11.setSelected(false);
	}

	@FXML
	void onCBSRT12Action(ActionEvent event) {
		CBLRT12.setSelected(false);
		CBLT12.setSelected(false);
		CBFHT12.setSelected(false);
		CBCRT12.setSelected(false);
		CBCDT12.setSelected(false);
		CBWRT12.setSelected(false);
		CBWDT12.setSelected(false);
		CBEHT12.setSelected(false);
		CBST12.setSelected(false);
	}

	@FXML
	void onCBSRT1Action(ActionEvent event) {
		CBLRT1.setSelected(false);
		CBLT1.setSelected(false);
		CBFHT1.setSelected(false);
		CBCRT1.setSelected(false);
		CBCDT1.setSelected(false);
		CBWRT1.setSelected(false);
		CBWDT1.setSelected(false);
		CBEHT1.setSelected(false);
		CBST1.setSelected(false);
	}

	@FXML
	void onCBSRT2Action(ActionEvent event) {
		CBLRT2.setSelected(false);
		CBLT2.setSelected(false);
		CBFHT2.setSelected(false);
		CBCRT2.setSelected(false);
		CBCDT2.setSelected(false);
		CBWRT2.setSelected(false);
		CBWDT2.setSelected(false);
		CBEHT2.setSelected(false);
		CBST2.setSelected(false);
	}

	@FXML
	void onCBSRT3Action(ActionEvent event) {
		CBLRT3.setSelected(false);
		CBLT3.setSelected(false);
		CBFHT3.setSelected(false);
		CBCRT3.setSelected(false);
		CBCDT3.setSelected(false);
		CBWRT3.setSelected(false);
		CBWDT3.setSelected(false);
		CBEHT3.setSelected(false);
		CBST3.setSelected(false);
	}

	@FXML
	void onCBSRT4Action(ActionEvent event) {
		CBLRT4.setSelected(false);
		CBLT4.setSelected(false);
		CBFHT4.setSelected(false);
		CBCRT4.setSelected(false);
		CBCDT4.setSelected(false);
		CBWRT4.setSelected(false);
		CBWDT4.setSelected(false);
		CBEHT4.setSelected(false);
		CBST4.setSelected(false);
	}

	@FXML
	void onCBSRT5Action(ActionEvent event) {
		CBLRT5.setSelected(false);
		CBLT5.setSelected(false);
		CBFHT5.setSelected(false);
		CBCRT5.setSelected(false);
		CBCDT5.setSelected(false);
		CBWRT5.setSelected(false);
		CBWDT5.setSelected(false);
		CBEHT5.setSelected(false);
		CBST5.setSelected(false);
	}

	@FXML
	void onCBSRT6Action(ActionEvent event) {
		CBLRT6.setSelected(false);
		CBLT6.setSelected(false);
		CBFHT6.setSelected(false);
		CBCRT6.setSelected(false);
		CBCDT6.setSelected(false);
		CBWRT6.setSelected(false);
		CBWDT6.setSelected(false);
		CBEHT6.setSelected(false);
		CBST6.setSelected(false);
	}

	@FXML
	void onCBSRT7Action(ActionEvent event) {
		CBLRT7.setSelected(false);
		CBLT7.setSelected(false);
		CBFHT7.setSelected(false);
		CBCRT7.setSelected(false);
		CBCDT7.setSelected(false);
		CBWRT7.setSelected(false);
		CBWDT7.setSelected(false);
		CBEHT7.setSelected(false);
		CBST7.setSelected(false);
	}

	@FXML
	void onCBSRT8Action(ActionEvent event) {
		CBLRT8.setSelected(false);
		CBLT8.setSelected(false);
		CBFHT8.setSelected(false);
		CBCRT8.setSelected(false);
		CBCDT8.setSelected(false);
		CBWRT8.setSelected(false);
		CBWDT8.setSelected(false);
		CBEHT8.setSelected(false);
		CBST8.setSelected(false);
	}

	@FXML
	void onCBSRT9Action(ActionEvent event) {
		CBLRT9.setSelected(false);
		CBLT9.setSelected(false);
		CBFHT9.setSelected(false);
		CBCRT9.setSelected(false);
		CBCDT9.setSelected(false);
		CBWRT9.setSelected(false);
		CBWDT9.setSelected(false);
		CBEHT9.setSelected(false);
		CBST9.setSelected(false);
	}

	@FXML
	void onCBST10Action(ActionEvent event) {
		CBLRT10.setSelected(false);
		CBLT10.setSelected(false);
		CBFHT10.setSelected(false);
		CBCRT10.setSelected(false);
		CBCDT10.setSelected(false);
		CBWRT10.setSelected(false);
		CBWDT10.setSelected(false);
		CBEHT10.setSelected(false);
		CBSRT10.setSelected(false);
	}

	@FXML
	void onCBST11Action(ActionEvent event) {
		CBLRT11.setSelected(false);
		CBLT11.setSelected(false);
		CBFHT11.setSelected(false);
		CBCRT11.setSelected(false);
		CBCDT11.setSelected(false);
		CBWRT11.setSelected(false);
		CBWDT11.setSelected(false);
		CBEHT11.setSelected(false);
		CBSRT11.setSelected(false);
	}

	@FXML
	void onCBST12Action(ActionEvent event) {
		CBLRT12.setSelected(false);
		CBLT12.setSelected(false);
		CBFHT12.setSelected(false);
		CBCRT12.setSelected(false);
		CBCDT12.setSelected(false);
		CBWRT12.setSelected(false);
		CBWDT12.setSelected(false);
		CBEHT12.setSelected(false);
		CBSRT12.setSelected(false);
	}

	@FXML
	void onCBST1Action(ActionEvent event) {
		CBLRT1.setSelected(false);
		CBLT1.setSelected(false);
		CBFHT1.setSelected(false);
		CBCRT1.setSelected(false);
		CBCDT1.setSelected(false);
		CBWRT1.setSelected(false);
		CBWDT1.setSelected(false);
		CBEHT1.setSelected(false);
		CBSRT1.setSelected(false);
	}

	@FXML
	void onCBST2Action(ActionEvent event) {
		CBLRT2.setSelected(false);
		CBLT2.setSelected(false);
		CBFHT2.setSelected(false);
		CBCRT2.setSelected(false);
		CBCDT2.setSelected(false);
		CBWRT2.setSelected(false);
		CBWDT2.setSelected(false);
		CBEHT2.setSelected(false);
		CBSRT2.setSelected(false);
	}

	@FXML
	void onCBST3Action(ActionEvent event) {
		CBLRT3.setSelected(false);
		CBLT3.setSelected(false);
		CBFHT3.setSelected(false);
		CBCRT3.setSelected(false);
		CBCDT3.setSelected(false);
		CBWRT3.setSelected(false);
		CBWDT3.setSelected(false);
		CBEHT3.setSelected(false);
		CBSRT3.setSelected(false);
	}

	@FXML
	void onCBST4Action(ActionEvent event) {
		CBLRT4.setSelected(false);
		CBLT4.setSelected(false);
		CBFHT4.setSelected(false);
		CBCRT4.setSelected(false);
		CBCDT4.setSelected(false);
		CBWRT4.setSelected(false);
		CBWDT4.setSelected(false);
		CBEHT4.setSelected(false);
		CBSRT4.setSelected(false);
	}

	@FXML
	void onCBST5Action(ActionEvent event) {
		CBLRT5.setSelected(false);
		CBLT5.setSelected(false);
		CBFHT5.setSelected(false);
		CBCRT5.setSelected(false);
		CBCDT5.setSelected(false);
		CBWRT5.setSelected(false);
		CBWDT5.setSelected(false);
		CBEHT5.setSelected(false);
		CBSRT5.setSelected(false);
	}

	@FXML
	void onCBST6Action(ActionEvent event) {
		CBLRT6.setSelected(false);
		CBLT6.setSelected(false);
		CBFHT6.setSelected(false);
		CBCRT6.setSelected(false);
		CBCDT6.setSelected(false);
		CBWRT6.setSelected(false);
		CBWDT6.setSelected(false);
		CBEHT6.setSelected(false);
		CBSRT6.setSelected(false);
	}

	@FXML
	void onCBST7Action(ActionEvent event) {
		CBLRT7.setSelected(false);
		CBLT7.setSelected(false);
		CBFHT7.setSelected(false);
		CBCRT7.setSelected(false);
		CBCDT7.setSelected(false);
		CBWRT7.setSelected(false);
		CBWDT7.setSelected(false);
		CBEHT7.setSelected(false);
		CBSRT7.setSelected(false);
	}

	@FXML
	void onCBST8Action(ActionEvent event) {
		CBLRT8.setSelected(false);
		CBLT8.setSelected(false);
		CBFHT8.setSelected(false);
		CBCRT8.setSelected(false);
		CBCDT8.setSelected(false);
		CBWRT8.setSelected(false);
		CBWDT8.setSelected(false);
		CBEHT8.setSelected(false);
		CBSRT8.setSelected(false);
	}

	@FXML
	void onCBST9Action(ActionEvent event) {
		CBLRT9.setSelected(false);
		CBLT9.setSelected(false);
		CBFHT9.setSelected(false);
		CBCRT9.setSelected(false);
		CBCDT9.setSelected(false);
		CBWRT9.setSelected(false);
		CBWDT9.setSelected(false);
		CBEHT9.setSelected(false);
		CBSRT9.setSelected(false);
	}

	@FXML
	void onCBWDT10Action(ActionEvent event) {
		CBLRT10.setSelected(false);
		CBLT10.setSelected(false);
		CBFHT10.setSelected(false);
		CBCRT10.setSelected(false);
		CBCDT10.setSelected(false);
		CBWRT10.setSelected(false);
		CBEHT10.setSelected(false);
		CBSRT10.setSelected(false);
		CBST10.setSelected(false);
	}

	@FXML
	void onCBWDT11Action(ActionEvent event) {
		CBLRT11.setSelected(false);
		CBLT11.setSelected(false);
		CBFHT11.setSelected(false);
		CBCRT11.setSelected(false);
		CBCDT11.setSelected(false);
		CBWRT11.setSelected(false);
		CBEHT11.setSelected(false);
		CBSRT11.setSelected(false);
		CBST11.setSelected(false);
	}

	@FXML
	void onCBWDT12Action(ActionEvent event) {
		CBLRT12.setSelected(false);
		CBLT12.setSelected(false);
		CBFHT12.setSelected(false);
		CBCRT12.setSelected(false);
		CBCDT12.setSelected(false);
		CBWRT12.setSelected(false);
		CBEHT12.setSelected(false);
		CBSRT12.setSelected(false);
		CBST12.setSelected(false);
	}

	@FXML
	void onCBWDT1Action(ActionEvent event) {
		CBLRT1.setSelected(false);
		CBLT1.setSelected(false);
		CBFHT1.setSelected(false);
		CBCRT1.setSelected(false);
		CBCDT1.setSelected(false);
		CBWRT1.setSelected(false);
		CBEHT1.setSelected(false);
		CBSRT1.setSelected(false);
		CBST1.setSelected(false);
	}

	@FXML
	void onCBWDT2Action(ActionEvent event) {
		CBLRT2.setSelected(false);
		CBLT2.setSelected(false);
		CBFHT2.setSelected(false);
		CBCRT2.setSelected(false);
		CBCDT2.setSelected(false);
		CBWRT2.setSelected(false);
		CBEHT2.setSelected(false);
		CBSRT2.setSelected(false);
		CBST2.setSelected(false);
	}

	@FXML
	void onCBWDT3Action(ActionEvent event) {
		CBLRT3.setSelected(false);
		CBLT3.setSelected(false);
		CBFHT3.setSelected(false);
		CBCRT3.setSelected(false);
		CBCDT3.setSelected(false);
		CBWRT3.setSelected(false);
		CBEHT3.setSelected(false);
		CBSRT3.setSelected(false);
		CBST3.setSelected(false);
	}

	@FXML
	void onCBWDT4Action(ActionEvent event) {
		CBLRT4.setSelected(false);
		CBLT4.setSelected(false);
		CBFHT4.setSelected(false);
		CBCRT4.setSelected(false);
		CBCDT4.setSelected(false);
		CBWRT4.setSelected(false);
		CBEHT4.setSelected(false);
		CBSRT4.setSelected(false);
		CBST4.setSelected(false);
	}

	@FXML
	void onCBWDT5Action(ActionEvent event) {
		CBLRT5.setSelected(false);
		CBLT5.setSelected(false);
		CBFHT5.setSelected(false);
		CBCRT5.setSelected(false);
		CBCDT5.setSelected(false);
		CBWRT5.setSelected(false);
		CBEHT5.setSelected(false);
		CBSRT5.setSelected(false);
		CBST5.setSelected(false);
	}

	@FXML
	void onCBWDT6Action(ActionEvent event) {
		CBLRT6.setSelected(false);
		CBLT6.setSelected(false);
		CBFHT6.setSelected(false);
		CBCRT6.setSelected(false);
		CBCDT6.setSelected(false);
		CBWRT6.setSelected(false);
		CBEHT6.setSelected(false);
		CBSRT6.setSelected(false);
		CBST6.setSelected(false);
	}

	@FXML
	void onCBWDT7Action(ActionEvent event) {
		CBLRT7.setSelected(false);
		CBLT7.setSelected(false);
		CBFHT7.setSelected(false);
		CBCRT7.setSelected(false);
		CBCDT7.setSelected(false);
		CBWRT7.setSelected(false);
		CBEHT7.setSelected(false);
		CBSRT7.setSelected(false);
		CBST7.setSelected(false);
	}

	@FXML
	void onCBWDT8Action(ActionEvent event) {
		CBLRT8.setSelected(false);
		CBLT8.setSelected(false);
		CBFHT8.setSelected(false);
		CBCRT8.setSelected(false);
		CBCDT8.setSelected(false);
		CBWRT8.setSelected(false);
		CBEHT8.setSelected(false);
		CBSRT8.setSelected(false);
		CBST8.setSelected(false);
	}

	@FXML
	void onCBWDT9Action(ActionEvent event) {
		CBLRT9.setSelected(false);
		CBLT9.setSelected(false);
		CBFHT9.setSelected(false);
		CBCRT9.setSelected(false);
		CBCDT9.setSelected(false);
		CBWRT9.setSelected(false);
		CBEHT9.setSelected(false);
		CBSRT9.setSelected(false);
		CBST9.setSelected(false);
	}

	@FXML
	void onCBWRT10Action(ActionEvent event) {
		CBLRT10.setSelected(false);
		CBLT10.setSelected(false);
		CBFHT10.setSelected(false);
		CBCRT10.setSelected(false);
		CBCDT10.setSelected(false);
		CBWDT10.setSelected(false);
		CBEHT10.setSelected(false);
		CBSRT10.setSelected(false);
		CBST10.setSelected(false);
	}

	@FXML
	void onCBWRT11Action(ActionEvent event) {
		CBLRT11.setSelected(false);
		CBLT11.setSelected(false);
		CBFHT11.setSelected(false);
		CBCRT11.setSelected(false);
		CBCDT11.setSelected(false);
		CBWDT11.setSelected(false);
		CBEHT11.setSelected(false);
		CBSRT11.setSelected(false);
		CBST11.setSelected(false);
	}

	@FXML
	void onCBWRT12Action(ActionEvent event) {
		CBLRT12.setSelected(false);
		CBLT12.setSelected(false);
		CBFHT12.setSelected(false);
		CBCRT12.setSelected(false);
		CBCDT12.setSelected(false);
		CBWDT12.setSelected(false);
		CBEHT12.setSelected(false);
		CBSRT12.setSelected(false);
		CBST12.setSelected(false);
	}

	@FXML
	void onCBWRT1Action(ActionEvent event) {
		CBLRT1.setSelected(false);
		CBLT1.setSelected(false);
		CBFHT1.setSelected(false);
		CBCRT1.setSelected(false);
		CBCDT1.setSelected(false);
		CBWDT1.setSelected(false);
		CBEHT1.setSelected(false);
		CBSRT1.setSelected(false);
		CBST1.setSelected(false);
	}

	@FXML
	void onCBWRT2Action(ActionEvent event) {
		CBLRT2.setSelected(false);
		CBLT2.setSelected(false);
		CBFHT2.setSelected(false);
		CBCRT2.setSelected(false);
		CBCDT2.setSelected(false);
		CBWDT2.setSelected(false);
		CBEHT2.setSelected(false);
		CBSRT2.setSelected(false);
		CBST2.setSelected(false);
	}

	@FXML
	void onCBWRT3Action(ActionEvent event) {
		CBLRT3.setSelected(false);
		CBLT3.setSelected(false);
		CBFHT3.setSelected(false);
		CBCRT3.setSelected(false);
		CBCDT3.setSelected(false);
		CBWDT3.setSelected(false);
		CBEHT3.setSelected(false);
		CBSRT3.setSelected(false);
		CBST3.setSelected(false);
	}

	@FXML
	void onCBWRT4Action(ActionEvent event) {
		CBLRT4.setSelected(false);
		CBLT4.setSelected(false);
		CBFHT4.setSelected(false);
		CBCRT4.setSelected(false);
		CBCDT4.setSelected(false);
		CBWDT4.setSelected(false);
		CBEHT4.setSelected(false);
		CBSRT4.setSelected(false);
		CBST4.setSelected(false);
	}

	@FXML
	void onCBWRT5Action(ActionEvent event) {
		CBLRT5.setSelected(false);
		CBLT5.setSelected(false);
		CBFHT5.setSelected(false);
		CBCRT5.setSelected(false);
		CBCDT5.setSelected(false);
		CBWDT5.setSelected(false);
		CBEHT5.setSelected(false);
		CBSRT5.setSelected(false);
		CBST5.setSelected(false);
	}

	@FXML
	void onCBWRT6Action(ActionEvent event) {
		CBLRT6.setSelected(false);
		CBLT6.setSelected(false);
		CBFHT6.setSelected(false);
		CBCRT6.setSelected(false);
		CBCDT6.setSelected(false);
		CBWDT6.setSelected(false);
		CBEHT6.setSelected(false);
		CBSRT6.setSelected(false);
		CBST6.setSelected(false);
	}

	@FXML
	void onCBWRT7Action(ActionEvent event) {
		CBLRT7.setSelected(false);
		CBLT7.setSelected(false);
		CBFHT7.setSelected(false);
		CBCRT7.setSelected(false);
		CBCDT7.setSelected(false);
		CBWDT7.setSelected(false);
		CBEHT7.setSelected(false);
		CBSRT7.setSelected(false);
		CBST7.setSelected(false);
	}

	@FXML
	void onCBWRT8Action(ActionEvent event) {
		CBLRT8.setSelected(false);
		CBLT8.setSelected(false);
		CBFHT8.setSelected(false);
		CBCRT8.setSelected(false);
		CBCDT8.setSelected(false);
		CBWDT8.setSelected(false);
		CBEHT8.setSelected(false);
		CBSRT8.setSelected(false);
		CBST8.setSelected(false);
	}

	@FXML
	void onCBWRT9Action(ActionEvent event) {
		CBLRT9.setSelected(false);
		CBLT9.setSelected(false);
		CBFHT9.setSelected(false);
		CBCRT9.setSelected(false);
		CBCDT9.setSelected(false);
		CBWDT9.setSelected(false);
		CBEHT9.setSelected(false);
		CBSRT9.setSelected(false);
		CBST9.setSelected(false);
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		initializeNodes();

		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			LocalTime currentTime = LocalTime.now();
			Time.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
		}), new KeyFrame(Duration.seconds(1)));
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();

		Date dataAtual = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = dateFormat.format(dataAtual);

		Dat.setText(dataFormatada);
	}

}

package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Entities.CycleTime;
import Entities.FaceGrades;
import Entities.MinePlan;
import Entities.PlantBoundaries;
import Entities.TruckCapacity;
import application.Main;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

	@FXML
	private GridPane GRIDP;

	@FXML
	private Button btImporMinePlan;

	@FXML
	private Button btImporTrucksCapacity;

	@FXML
	private Button btImporCycleTime;

	@FXML
	private Button btImporProgrammedProduction;

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
	private MenuItem menuItemImportMinePlan;

	@FXML
	private MenuItem menuItemImportFaceGrades;

	@FXML
	private MenuItem menuItemImportFaceProduction;

	@FXML
	private MenuItem menuItemImportTrucksCapacity;

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

	List<MinePlan> listMinePlan = new ArrayList<>();

	List<String> listOperativeTruck = new ArrayList<>();

	Integer[] VectT1 = new Integer[7];

	Integer[] VectT2 = new Integer[7];

	@FXML
	public void onBtImportMinePlan() {
		String path = "E:\\UFOP\\TCC\\inputs\\minePlan.txt ";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();

			List<MinePlan> list = new ArrayList<>();
			list.clear();
			listMinePlan.clear();

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
			int k1 = 0;
			for (MinePlan m : listMinePlan) {
				somaT1 += m.getT1();
				VectT1[k1] = m.getT1();
				k1 += 1;
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
				listOperativeTruck.add("T1");

			}

			int somaT2 = 0;
			int k2 = 0;
			for (MinePlan m : listMinePlan) {
				somaT2 += m.getT2();
				VectT2[k2] = m.getT2();
				k2 += 1;
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
				listOperativeTruck.add("T2");

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
				listOperativeTruck.add("T3");

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
				listOperativeTruck.add("T4");

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
				listOperativeTruck.add("T5");

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
				listOperativeTruck.add("T6");

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
				listOperativeTruck.add("T7");

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
				listOperativeTruck.add("T8");

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
				listOperativeTruck.add("T9");

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
				listOperativeTruck.add("T10");

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
				listOperativeTruck.add("T11");

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
				listOperativeTruck.add("T12");

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

		} catch (

		IOException e) {
			System.out.println(" Erro: " + e.getMessage());
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
		tableColumnFaceCT.setCellValueFactory(new PropertyValueFactory<CycleTime, String>("face"));
		tableColumnCT1.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("t1"));
		tableColumnCT2.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("t2"));
		tableColumnCT3.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("t3"));
		tableColumnCT4.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("t4"));
		tableColumnCT5.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("t5"));
		tableColumnCT6.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("t6"));
		tableColumnCT7.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("t7"));
		tableColumnCT8.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("t8"));
		tableColumnCT9.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("t9"));
		tableColumnCT10.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("t10"));
		tableColumnCT11.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("t11"));
		tableColumnCT12.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("t12"));
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
	public void onbtImporTrucksCapacity() {
		String path = "E:\\UFOP\\TCC\\inputs\\truck'sCapacity.txt ";
		List<TruckCapacity> list = new ArrayList<>();
		list.clear();
		listTruckCapacity.clear();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String[] vect = line.split(",");

				int t1 = Integer.parseInt(vect[0]);
				int t2 = Integer.parseInt(vect[1]);
				int t3 = Integer.parseInt(vect[2]);
				int t4 = Integer.parseInt(vect[3]);
				int t5 = Integer.parseInt(vect[4]);
				int t6 = Integer.parseInt(vect[5]);
				int t7 = Integer.parseInt(vect[6]);
				int t8 = Integer.parseInt(vect[7]);
				int t9 = Integer.parseInt(vect[8]);
				int t10 = Integer.parseInt(vect[9]);
				int t11 = Integer.parseInt(vect[10]);
				int t12 = Integer.parseInt(vect[11]);

				listTruckCapacity.add(new TruckCapacity(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12));
				list.add(new TruckCapacity(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12));
				TC = new TruckCapacity(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12);
				line = br.readLine();
			}
			obsListTruckCapacity = FXCollections.observableArrayList(list);
			tableViewTrucksCapacity.setItems(obsListTruckCapacity);

		} catch (IOException e) {
			System.out.println(" Erro: " + e.getMessage());
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
	void onBtDispatchAction(ActionEvent event) {

		List<String> MainFaces = new ArrayList<>();

		for (MinePlan m : listMinePlan) {
			MainFaces.add(m.getFace());
		}

		String Dispatched = CBDispatch.getValue();

		List<Integer> facesT1 = new ArrayList<>();
		List<Double> prodT1 = new ArrayList<>();
		List<Double> devT1 = new ArrayList<>();
		List<Integer> nTravelsT1 = new ArrayList<>();

		if (Dispatched == "T1") {
			if (VectT1[0] != 0) {
				nTravelsT1.add(VectT1[0]);
				facesT1.add(0);
				prodT1.add(Double.parseDouble(ActualProdO1.getText()) + TC.getCapT1());
				devT1.add((Double.parseDouble(ActualProdO1.getText()) + TC.getCapT1())
						/ Double.parseDouble(PlanProdO1.getText()));

			}
			if (VectT1[1] != 0) {
				nTravelsT1.add(VectT1[1]);
				facesT1.add(1);
				prodT1.add(Double.parseDouble(ActualProdO2.getText()) + TC.getCapT1());
				devT1.add((Double.parseDouble(ActualProdO2.getText()) + TC.getCapT1())
						/ Double.parseDouble(PlanProdO2.getText()));

			}
			if (VectT1[2] != 0) {
				nTravelsT1.add(VectT1[2]);
				facesT1.add(2);
				prodT1.add(Double.parseDouble(ActualProdO3.getText()) + TC.getCapT1());
				devT1.add((Double.parseDouble(ActualProdO3.getText()) + TC.getCapT1())
						/ Double.parseDouble(PlanProdO3.getText()));

			}
			if (VectT1[3] != 0) {
				nTravelsT1.add(VectT1[3]);
				facesT1.add(3);
				prodT1.add(Double.parseDouble(ActualProdO4.getText()) + TC.getCapT1());
				devT1.add((Double.parseDouble(ActualProdO4.getText()) + TC.getCapT1())
						/ Double.parseDouble(PlanProdO4.getText()));

			}
			if (VectT1[4] != 0) {
				nTravelsT1.add(VectT1[4]);
				facesT1.add(4);
				prodT1.add(Double.parseDouble(ActualProdO5.getText()) + TC.getCapT1());
				devT1.add((Double.parseDouble(ActualProdO5.getText()) + TC.getCapT1())
						/ Double.parseDouble(PlanProdO5.getText()));

			}
			if (VectT1[5] != 0) {
				nTravelsT1.add(VectT1[5]);
				facesT1.add(5);
				prodT1.add(Double.parseDouble(ActualProdW1.getText()) + TC.getCapT1());
				devT1.add((Double.parseDouble(ActualProdW1.getText()) + TC.getCapT1())
						/ Double.parseDouble(PlanProdW1.getText()));

			}
			if (VectT1[6] != 0) {
				nTravelsT1.add(VectT1[6]);
				facesT1.add(6);
				prodT1.add(Double.parseDouble(ActualProdW2.getText()) + TC.getCapT1());
				devT1.add((Double.parseDouble(ActualProdW2.getText()) + TC.getCapT1())
						/ Double.parseDouble(PlanProdW2.getText()));
			}

			double smallerT1 = 10 ^ 6;
			int indexT1 = 0;
			int contT1 = 0;
			for (Double m : devT1) {
				if (m < smallerT1) {
					smallerT1 = m;
					indexT1 = contT1;
					contT1 += 1;
				}
			}

			switch (facesT1.get(contT1)) {
			case 0:
				if (Double.parseDouble(ActualProdO1.getText()) < Double.parseDouble(PlanProdO1.getText())) {
					txtAreaDispatch.setText(MainFaces.get(facesT1.get(contT1)));
					ActualProdO1.setText(String.valueOf(prodT1.get(contT1)));
					DevProdO1.setText(String.valueOf((devT1.get(contT1)-1)*-100));
					
					
				}
				break;
			case 1:
				if (Double.parseDouble(ActualProdO2.getText()) < Double.parseDouble(PlanProdO2.getText())) {
					txtAreaDispatch.setText(MainFaces.get(facesT1.get(contT1)));
					ActualProdO2.setText(String.valueOf(prodT1.get(contT1)));
					DevProdO2.setText(String.valueOf((devT1.get(contT1)-1)*-100));
				}
				break;
			case 2:
				if (Double.parseDouble(ActualProdO3.getText()) < Double.parseDouble(PlanProdO3.getText())) {
					txtAreaDispatch.setText(MainFaces.get(facesT1.get(contT1)));
					ActualProdO3.setText(String.valueOf(prodT1.get(contT1)));
					DevProdO3.setText(String.valueOf((devT1.get(contT1)-1)*-100));
				}
				break;
			case 3:
				if (Double.parseDouble(ActualProdO4.getText()) < Double.parseDouble(PlanProdO4.getText())) {
					txtAreaDispatch.setText(MainFaces.get(facesT1.get(contT1)));
					ActualProdO4.setText(String.valueOf(prodT1.get(contT1)));
					DevProdO4.setText(String.valueOf((devT1.get(contT1)-1)*-100));
				}
				break;
			case 4:
				if (Double.parseDouble(ActualProdO5.getText()) < Double.parseDouble(PlanProdO5.getText())) {
					txtAreaDispatch.setText(MainFaces.get(facesT1.get(contT1)));
					ActualProdO5.setText(String.valueOf(prodT1.get(contT1)));
					DevProdO5.setText(String.valueOf((devT1.get(contT1)-1)*-100));
				}
				break;
			case 5:
				if (Double.parseDouble(ActualProdW1.getText()) < Double.parseDouble(PlanProdW1.getText())) {
					txtAreaDispatch.setText(MainFaces.get(facesT1.get(contT1)));
					ActualProdW1.setText(String.valueOf(prodT1.get(contT1)));
					DevProdW1.setText(String.valueOf((devT1.get(contT1)-1)*-100));
				}
				break;
			case 6:
				if (Double.parseDouble(ActualProdW2.getText()) < Double.parseDouble(PlanProdW2.getText())) {
					txtAreaDispatch.setText(MainFaces.get(facesT1.get(contT1)));
					ActualProdW2.setText(String.valueOf(prodT1.get(contT1)));
					DevProdW2.setText(String.valueOf((devT1.get(contT1)-1)*-100));
				}
				break;
			}

			for (Integer i : nTravelsT1) {
				System.out.println(i);
			}
			for (Integer i : facesT1) {
				System.out.println(i);
			}

			for (Double j : prodT1) {
				System.out.println(j);
			}

			for (Double j : devT1) {
				System.out.println(j);
			}

			System.out.println(smallerT1);
			System.out.println(facesT1.get(contT1));
			System.out.println(contT1);
			System.out.println(MainFaces.get(facesT1.get(contT1)));
			/**
			 * Double T1Dev[] = {0.0,0.0,0.0,0.0,0.0,0.0,0.0}; if (PlanProdO1.getText() !=
			 * null) { T1Dev[0] = T1Face[0] / Double.parseDouble(PlanProdO1.getText()); } if
			 * (PlanProdO1.getText() != null) { T1Dev[1] = T1Face[0] /
			 * Double.parseDouble(PlanProdO2.getText()); } if (PlanProdO1.getText() != null)
			 * { T1Dev[2] = T1Face[0] / Double.parseDouble(PlanProdO3.getText()); } if
			 * (PlanProdO1.getText() != null) { T1Dev[3] = T1Face[0] /
			 * Double.parseDouble(PlanProdO4.getText()); } if (PlanProdO1.getText() != null)
			 * { T1Dev[4] = T1Face[0] / Double.parseDouble(PlanProdO5.getText()); } if
			 * (PlanProdO1.getText() != null) { T1Dev[5] = T1Face[0] /
			 * Double.parseDouble(PlanProdW1.getText()); } if (PlanProdO1.getText() != null)
			 * { T1Dev[6] = T1Face[0] / Double.parseDouble(PlanProdW2.getText()); }
			 * 
			 * double smallerT1 = 10 ^ 6; int indexT1 = 0;
			 * 
			 * for (int cont = 0; cont < 7; cont++) { if (T1Dev[cont] < smallerT1) {
			 * smallerT1 = T1Dev[cont]; indexT1 = cont; } }
			 * 
			 * switch (indexT1) { case 0: txtAreaDispatch.setText("O1"); break; case 1:
			 * txtAreaDispatch.setText("O2"); break; case 2: txtAreaDispatch.setText("O3");
			 * break; case 3: txtAreaDispatch.setText("O4"); break; case 4:
			 * txtAreaDispatch.setText("O5"); break; case 5: txtAreaDispatch.setText("W1");
			 * break; case 6: txtAreaDispatch.setText("W2"); break; }
			 * 
			 * } Double T2Face[] = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }; if (Dispatched ==
			 * "T2") { if (VectT2[0] != 0) { T2Face[0] =
			 * Double.parseDouble(ActualProdO1.getText() + TC.getCapT2());
			 * 
			 * } if (VectT2[1] != 0) { T2Face[1] = Double.parseDouble(ActualProdO2.getText()
			 * + TC.getCapT2());
			 * 
			 * } if (VectT2[2] != 0) { T2Face[2] = Double.parseDouble(ActualProdO3.getText()
			 * + TC.getCapT2());
			 * 
			 * } if (VectT2[3] != 0) { T2Face[3] = Double.parseDouble(ActualProdO4.getText()
			 * + TC.getCapT2());
			 * 
			 * } if (VectT2[4] != 0) { T2Face[4] = Double.parseDouble(ActualProdO5.getText()
			 * + TC.getCapT2());
			 * 
			 * } if (VectT2[5] != 0) { T2Face[5] = Double.parseDouble(ActualProdW1.getText()
			 * + TC.getCapT2());
			 * 
			 * } if (VectT2[6] != 0) { T2Face[6] = Double.parseDouble(ActualProdW2.getText()
			 * + TC.getCapT2()); } Double T2Dev[] = { T2Face[0] /
			 * Double.parseDouble(PlanProdO1.getText()), T2Face[1] /
			 * Double.parseDouble(PlanProdO2.getText()), T2Face[2] /
			 * Double.parseDouble(PlanProdO3.getText()), T2Face[3] /
			 * Double.parseDouble(PlanProdO4.getText()), T2Face[4] /
			 * Double.parseDouble(PlanProdO5.getText()), T2Face[5] /
			 * Double.parseDouble(PlanProdW1.getText()), T2Face[6] /
			 * Double.parseDouble(PlanProdW2.getText()) };
			 * 
			 * double smallerT2 = 10 ^ 6; int indexT2 = 0;
			 * 
			 * for (int cont = 0; cont < 7; cont++) { if (T2Dev[cont] < smallerT2) {
			 * smallerT2 = T2Dev[cont]; indexT2 = cont; } }
			 * 
			 * switch (indexT2) { case 0: txtAreaDispatch.setText("O1"); break; case 1:
			 * txtAreaDispatch.setText("O2"); break; case 2: txtAreaDispatch.setText("O3");
			 * break; case 3: txtAreaDispatch.setText("O4"); break; case 4:
			 * txtAreaDispatch.setText("O5"); break; case 5: txtAreaDispatch.setText("W1");
			 * break; case 6: txtAreaDispatch.setText("W2"); break; }
			 **/
		}

	}

	@FXML
	private ComboBox<String> CBDispatch;

	@FXML
	private TextArea txtAreaDispatch;

	@FXML
	private Button btStart;

	@FXML
	void onBtStart(ActionEvent event) {

		try {
			double prod = 0;
			List<Double> list = new ArrayList<>();

			for (MinePlan m : listMinePlan) {

				prod = m.getT1() * TC.getCapT1() + m.getT2() * TC.getCapT2() + m.getT3() * TC.getCapT3()
						+ m.getT4() * TC.getCapT4() + m.getT5() * TC.getCapT5() + m.getT6() * TC.getCapT6()
						+ m.getT7() * TC.getCapT7() + m.getT8() * TC.getCapT8() + m.getT9() * TC.getCapT9()
						+ m.getT10() * TC.getCapT10() + m.getT11() * TC.getCapT11() + m.getT12() * TC.getCapT12();
				list.add(prod);
			}

			if (PlanProdO1.isDisable() == false) {
				PlanProdO1.setText(String.valueOf(list.get(0)));
			}
			if (PlanProdO2.isDisable() == false) {
				PlanProdO2.setText(String.valueOf(list.get(1)));
			}
			if (PlanProdO3.isDisable() == false) {
				PlanProdO3.setText(String.valueOf(list.get(2)));
			}
			if (PlanProdO4.isDisable() == false) {
				PlanProdO4.setText(String.valueOf(list.get(3)));
			}
			if (PlanProdO5.isDisable() == false) {
				PlanProdO5.setText(String.valueOf(list.get(4)));
			}
			if (PlanProdW1.isDisable() == false) {
				PlanProdW1.setText(String.valueOf(list.get(5)));
			}
			if (PlanProdW2.isDisable() == false) {
				PlanProdW2.setText(String.valueOf(list.get(6)));
			}

			// Initial Production

			double ip1 = 0;
			double ip2 = 0;
			double ip3 = 0;
			double ip4 = 0;
			double ip5 = 0;
			double ip6 = 0;
			double ip7 = 0;

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
				ActualProdO1.setText(String.valueOf(ip1));
			}
			if (ActualProdO2.isDisable() == false) {
				ActualProdO2.setText(String.valueOf(ip2));
			}
			if (ActualProdO3.isDisable() == false) {
				ActualProdO3.setText(String.valueOf(ip3));
			}
			if (ActualProdO4.isDisable() == false) {
				ActualProdO4.setText(String.valueOf(ip4));
			}
			if (ActualProdO5.isDisable() == false) {
				ActualProdO5.setText(String.valueOf(ip5));
			}
			if (ActualProdW1.isDisable() == false) {
				ActualProdW1.setText(String.valueOf(ip6));
			}
			if (ActualProdW2.isDisable() == false) {
				ActualProdW2.setText(String.valueOf(ip7));
			}

			// Deviation
			if (DevProdO1.isDisable() == false) {
				DevProdO1.setText(String.valueOf(((ip1 / list.get(0)) - 1) * -100));
			}
			if (DevProdO2.isDisable() == false) {
				DevProdO2.setText(String.valueOf(((ip2 / list.get(1)) - 1) * -100));
			}
			if (DevProdO3.isDisable() == false) {
				DevProdO3.setText(String.valueOf(((ip3 / list.get(2)) - 1) * -100));
			}
			if (DevProdO4.isDisable() == false) {
				DevProdO4.setText(String.valueOf(((ip4 / list.get(3)) - 1) * -100));
			}
			if (DevProdO5.isDisable() == false) {
				DevProdO5.setText(String.valueOf(((ip5 / list.get(4)) - 1) * -100));
			}
			if (DevProdW1.isDisable() == false) {
				DevProdW1.setText(String.valueOf(((ip6 / list.get(5)) - 1) * -100));
			}
			if (DevProdW2.isDisable() == false) {
				DevProdW2.setText(String.valueOf(((ip7 / list.get(6)) - 1) * -100));
			}

			btDispatch.setDisable(false);
			// txtFieldDispatch.setDisable(false);
			txtAreaDispatch.setDisable(false);

			ObservableList<String> obsCombo;
			obsCombo = FXCollections.observableArrayList(listOperativeTruck);

			CBDispatch.setItems(obsCombo);

		} catch (RuntimeException e) {
			Alerts.showAlert("Error",
					"Check that the data has been imported and that the initial location has already been made!",
					AlertType.ERROR);
		}

	}

	@FXML
	private CheckBox CBDone;

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
		if ((((CBOPT1.isSelected() == true | (CBLT1.isSelected() == true | CBLRT1.isSelected() == true
				| CBFHT1.isSelected() == true | CBCDT1.isSelected() == true | CBCRT1.isSelected() == true
				| CBWDT1.isSelected() == true | CBWRT1.isSelected() == true | CBEHT1.isSelected() == true
				| CBST1.isSelected() == true | CBSRT1.isSelected() == true))
				& (CBOPT2.isSelected() == true | (CBLT2.isSelected() == true | CBLRT2.isSelected() == true
						| CBFHT2.isSelected() == true | CBCDT2.isSelected() == true | CBCRT2.isSelected() == true
						| CBWDT2.isSelected() == true | CBWRT2.isSelected() == true | CBEHT2.isSelected() == true
						| CBST2.isSelected() == true | CBSRT2.isSelected() == true))
				& (CBOPT3.isSelected() == true | (CBLT3.isSelected() == true | CBLRT3.isSelected() == true
						| CBFHT3.isSelected() == true | CBCDT3.isSelected() == true | CBCRT3.isSelected() == true
						| CBWDT3.isSelected() == true | CBWRT3.isSelected() == true | CBEHT3.isSelected() == true
						| CBST3.isSelected() == true | CBSRT3.isSelected() == true))
				& (CBOPT4.isSelected() == true | (CBLT4.isSelected() == true | CBLRT4.isSelected() == true
						| CBFHT4.isSelected() == true | CBCDT4.isSelected() == true | CBCRT4.isSelected() == true
						| CBWDT4.isSelected() == true | CBWRT4.isSelected() == true | CBEHT4.isSelected() == true
						| CBST4.isSelected() == true | CBSRT4.isSelected() == true))
				& (CBOPT5.isSelected() == true | (CBLT5.isSelected() == true | CBLRT5.isSelected() == true
						| CBFHT5.isSelected() == true | CBCDT5.isSelected() == true | CBCRT5.isSelected() == true
						| CBWDT5.isSelected() == true | CBWRT5.isSelected() == true | CBEHT5.isSelected() == true
						| CBST5.isSelected() == true | CBSRT5.isSelected() == true))
				& (CBOPT6.isSelected() == true | (CBLT6.isSelected() == true | CBLRT6.isSelected() == true
						| CBFHT6.isSelected() == true | CBCDT6.isSelected() == true | CBCRT6.isSelected() == true
						| CBWDT6.isSelected() == true | CBWRT6.isSelected() == true | CBEHT6.isSelected() == true
						| CBST6.isSelected() == true | CBSRT6.isSelected() == true))
				& (CBOPT7.isSelected() == true | (CBLT7.isSelected() == true | CBLRT7.isSelected() == true
						| CBFHT7.isSelected() == true | CBCDT7.isSelected() == true | CBCRT7.isSelected() == true
						| CBWDT7.isSelected() == true | CBWRT7.isSelected() == true | CBEHT7.isSelected() == true
						| CBST7.isSelected() == true | CBSRT7.isSelected() == true))
				& (CBOPT8.isSelected() == true | (CBLT8.isSelected() == true | CBLRT8.isSelected() == true
						| CBFHT8.isSelected() == true | CBCDT8.isSelected() == true | CBCRT8.isSelected() == true
						| CBWDT8.isSelected() == true | CBWRT8.isSelected() == true | CBEHT8.isSelected() == true
						| CBST8.isSelected() == true | CBSRT8.isSelected() == true))
				& (CBOPT9.isSelected() == true | (CBLT9.isSelected() == true | CBLRT9.isSelected() == true
						| CBFHT9.isSelected() == true | CBCDT9.isSelected() == true | CBCRT9.isSelected() == true
						| CBWDT9.isSelected() == true | CBWRT9.isSelected() == true | CBEHT9.isSelected() == true
						| CBST9.isSelected() == true | CBSRT9.isSelected() == true))
				& (CBOPT10.isSelected() == true | (CBLT10.isSelected() == true | CBLRT10.isSelected() == true
						| CBFHT10.isSelected() == true | CBCDT10.isSelected() == true | CBCRT10.isSelected() == true
						| CBWDT10.isSelected() == true | CBWRT10.isSelected() == true | CBEHT10.isSelected() == true
						| CBST10.isSelected() == true | CBSRT10.isSelected() == true))
				& (CBOPT11.isSelected() == true | (CBLT11.isSelected() == true | CBLRT11.isSelected() == true
						| CBFHT11.isSelected() == true | CBCDT11.isSelected() == true | CBCRT11.isSelected() == true
						| CBWDT11.isSelected() == true | CBWRT11.isSelected() == true | CBEHT11.isSelected() == true
						| CBST11.isSelected() == true | CBSRT11.isSelected() == true))
				& (CBOPT12.isSelected() == true | (CBLT12.isSelected() == true | CBLRT12.isSelected() == true
						| CBFHT12.isSelected() == true | CBCDT12.isSelected() == true | CBCRT12.isSelected() == true
						| CBWDT12.isSelected() == true | CBWRT12.isSelected() == true | CBEHT12.isSelected() == true
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
			GRIDP.setDisable(true);

		} else {
			CBDone.setSelected(false);
			CBDone.setStyle("-fx-body-color: red");
			CBDone.setDisable(true);
			CBDone.setOpacity(10);
			Alerts.showAlert("Initial Location Error", "Make sure you have allocated each truck!", AlertType.ERROR);

		}
		/**
		 * if (GRIDP.isDisable() == true) { btEdit.setDisable(false);
		 * btAllocate.setDisable(true); btStart.setDisable(false); }
		 **/
	}

	@FXML
	private Button btEdit;

	@FXML
	void onBtEditAction(ActionEvent event) {
		/**
		 * GRIDP.setDisable(false); btAllocate.setDisable(false);
		 * btStart.setDisable(true);
		 **/
	}

	@FXML
	private TableView<CycleTime> tableViewCycleTime;

	@FXML
	private TableColumn<CycleTime, String> tableColumnFaceCT;

	@FXML
	private TableColumn<CycleTime, Integer> tableColumnCT1;

	@FXML
	private TableColumn<CycleTime, Integer> tableColumnCT2;

	@FXML
	private TableColumn<CycleTime, Integer> tableColumnCT3;

	@FXML
	private TableColumn<CycleTime, Integer> tableColumnCT4;

	@FXML
	private TableColumn<CycleTime, Integer> tableColumnCT5;

	@FXML
	private TableColumn<CycleTime, Integer> tableColumnCT6;

	@FXML
	private TableColumn<CycleTime, Integer> tableColumnCT7;

	@FXML
	private TableColumn<CycleTime, Integer> tableColumnCT8;

	@FXML
	private TableColumn<CycleTime, Integer> tableColumnCT9;

	@FXML
	private TableColumn<CycleTime, Integer> tableColumnCT10;

	@FXML
	private TableColumn<CycleTime, Integer> tableColumnCT11;

	@FXML
	private TableColumn<CycleTime, Integer> tableColumnCT12;

	private ObservableList<CycleTime> obsList3;

	@FXML
	public void onBtImportCycleTime() {
		String path = "E:\\UFOP\\TCC\\inputs\\cycleTime.txt ";

		List<CycleTime> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String[] vect = line.split(",");

				String face = vect[0];
				double t1 = Double.parseDouble(vect[1]);
				double t2 = Double.parseDouble(vect[2]);
				double t3 = Double.parseDouble(vect[3]);
				double t4 = Double.parseDouble(vect[4]);
				double t5 = Double.parseDouble(vect[5]);
				double t6 = Double.parseDouble(vect[6]);
				double t7 = Double.parseDouble(vect[7]);
				double t8 = Double.parseDouble(vect[8]);
				double t9 = Double.parseDouble(vect[9]);
				double t10 = Double.parseDouble(vect[10]);
				double t11 = Double.parseDouble(vect[11]);
				double t12 = Double.parseDouble(vect[12]);

				list.add(new CycleTime(face, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12));
				line = br.readLine();
			}
			obsList3 = FXCollections.observableArrayList(list);
			tableViewCycleTime.setItems(obsList3);

		} catch (IOException e) {
			System.out.println(" Erro: " + e.getMessage());
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
	public void onBtImportFaceGrades() {
		String path = "E:\\UFOP\\TCC\\inputs\\faceGrades.txt ";

		List<FaceGrades> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
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

				list.add(new FaceGrades(face, fe, sio2, al2o3, mn, p, loi, g50mm, g6_3mm, g0_15mm));
				line = br.readLine();
			}
			obsList4 = FXCollections.observableArrayList(list);
			tableViewFaceGrades.setItems(obsList4);

		} catch (IOException e) {
			System.out.println(" Erro: " + e.getMessage());
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
	public void onBtImportPlantBoundaries() {
		String path = "E:\\UFOP\\TCC\\inputs\\plantBoundaries.txt ";

		List<PlantBoundaries> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
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

				list.add(new PlantBoundaries(face, fe, sio2, al2o3, mn, p, loi, g50mm, g6_3mm, g0_15mm));
				line = br.readLine();
			}
			obsList5 = FXCollections.observableArrayList(list);
			tableViewPlantBoundaries.setItems(obsList5);

		} catch (IOException e) {
			System.out.println(" Erro: " + e.getMessage());
		}
	}

	@FXML
	public void onMenuItemImportMinePlanAction() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ImportMinePlanView.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Import Mine Plan");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", AlertType.ERROR);
		}
	}

	@FXML
	public void onMenuItemImportFaceGradesAction(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ImportFaceGradesView.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Import Face Grades");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", AlertType.ERROR);
		}
	}

	@FXML
	public void onMenuItemImportFaceProductionAction() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ImportFaceProductionView.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Import Face Production");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", AlertType.ERROR);
		}
	}

	@FXML
	public void onMenuItemImportTrucksCapacityAction() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ImportTrucksCapacityView.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Import Truck's Capacity");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", AlertType.ERROR);
		}
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/AboutView.fxml");
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
	}

	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", AlertType.ERROR);
		}
	}

	/**
	 * private void createDialogForm(String absoluteName, Stage parentStage) { try {
	 * FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
	 * Pane pane = loader.load();
	 * 
	 * Stage dialogStage = new Stage(); dialogStage.setTitle("Enter Department
	 * data"); dialogStage.setScene(new Scene(pane));
	 * dialogStage.setResizable(false); dialogStage.initOwner(parentStage);
	 * dialogStage.initModality(Modality.WINDOW_MODAL); dialogStage.showAndWait(); }
	 * catch (IOException e) { e.printStackTrace(); Alerts.showAlert("IO Exception",
	 * "Error loading view", e.getMessage(), AlertType.ERROR); } }
	 **/
}

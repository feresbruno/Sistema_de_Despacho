package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import Entities.CycleTime;
import Entities.FaceGrades;
import Entities.MinePlan;
import Entities.PlantBoundaries;
import Entities.ProgrammedProduction;
import Entities.TruckCapacity;
import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

	@FXML
	private Button btInicio;

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
	private TextArea txtArea1;

	@FXML
	private TextArea txtArea2;

	@FXML
	private TextArea txtArea3;

	@FXML
	private TextArea txtArea4;

	@FXML
	private TextArea txtArea5;

	@FXML
	private TextArea txtArea6;

	@FXML
	private TextArea txtArea7;

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
	private TableColumn<MinePlan, Integer> tableColumnC1;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnC2;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnC3;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnC4;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnC5;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnC6;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnC7;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnC8;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnC9;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnC10;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnC11;

	@FXML
	private TableColumn<MinePlan, Integer> tableColumnC12;

	private ObservableList<MinePlan> obsList;

	@FXML
	public void onBtImportMinePlan() {
		String path = "E:\\UFOP\\TCC\\inputs\\minePlan.txt ";

		List<MinePlan> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String[] vect = line.split(",");

				String face = vect[0];
				int c1 = Integer.parseInt(vect[1]);
				int c2 = Integer.parseInt(vect[2]);
				int c3 = Integer.parseInt(vect[3]);
				int c4 = Integer.parseInt(vect[4]);
				int c5 = Integer.parseInt(vect[5]);
				int c6 = Integer.parseInt(vect[6]);
				int c7 = Integer.parseInt(vect[7]);
				int c8 = Integer.parseInt(vect[8]);
				int c9 = Integer.parseInt(vect[9]);
				int c10 = Integer.parseInt(vect[10]);
				int c11 = Integer.parseInt(vect[11]);
				int c12 = Integer.parseInt(vect[12]);

				list.add(new MinePlan(face, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12));
				line = br.readLine();
			}
			obsList = FXCollections.observableArrayList(list);
			tableViewMinePlans.setItems(obsList);

		} catch (IOException e) {
			System.out.println(" Erro: " + e.getMessage());
		}
	}

	private void initializeNodes() {
		tableColumnFace.setCellValueFactory(new PropertyValueFactory<MinePlan, String>("Face"));
		tableColumnC1.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("C1"));
		tableColumnC2.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("C2"));
		tableColumnC3.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("C3"));
		tableColumnC4.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("C4"));
		tableColumnC5.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("C5"));
		tableColumnC6.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("C6"));
		tableColumnC7.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("C7"));
		tableColumnC8.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("C8"));
		tableColumnC9.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("C9"));
		tableColumnC10.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("C10"));
		tableColumnC11.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("C11"));
		tableColumnC12.setCellValueFactory(new PropertyValueFactory<MinePlan, Integer>("C12"));
		tableColumnT1.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT1"));
		tableColumnT2.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT2"));
		tableColumnT3.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT3"));
		tableColumnT4.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT4"));
		tableColumnT5.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT5"));
		tableColumnT6.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT6"));
		tableColumnT7.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT7"));
		tableColumnT8.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT8"));
		tableColumnT9.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT9"));
		tableColumnT10.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT10"));
		tableColumnT11.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT11"));
		tableColumnT12.setCellValueFactory(new PropertyValueFactory<TruckCapacity, Integer>("capT12"));
		tableColumnFaceCT.setCellValueFactory(new PropertyValueFactory<CycleTime, String>("face"));
		tableColumnCT1.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("c1"));
		tableColumnCT2.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("c2"));
		tableColumnCT3.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("c3"));
		tableColumnCT4.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("c4"));
		tableColumnCT5.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("c5"));
		tableColumnCT6.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("c6"));
		tableColumnCT7.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("c7"));
		tableColumnCT8.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("c8"));
		tableColumnCT9.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("c9"));
		tableColumnCT10.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("c10"));
		tableColumnCT11.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("c11"));
		tableColumnCT12.setCellValueFactory(new PropertyValueFactory<CycleTime, Integer>("c12"));
		tableColumnProd.setCellValueFactory(new PropertyValueFactory<ProgrammedProduction, Double>("Prod"));
		tableColumnFaceG.setCellValueFactory(new PropertyValueFactory<FaceGrades, String>("face"));
		tableColumnGFe.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("fe"));
		tableColumnGSiO2.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("sio2"));
		tableColumnGAl2O3.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("al2o3"));
		tableColumnGMn.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("mn"));
		tableColumnGP.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("p"));
		tableColumnGPPC.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("ppc"));
		tableColumnG50mm.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("mais50mm"));
		tableColumnG6_3mm.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("mais6_3mm"));
		tableColumnG0_15mm.setCellValueFactory(new PropertyValueFactory<FaceGrades, Integer>("mais0_15mm"));
		tableColumnLimits.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, String>("limit"));
		tableColumnBFe.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("fe"));
		tableColumnBSiO2.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("sio2"));
		tableColumnBAl2O3.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("al2o3"));
		tableColumnBMn.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("mn"));
		tableColumnBP.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("p"));
		tableColumnBPPC.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("ppc"));
		tableColumnB50mm.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("mais50mm"));
		tableColumnB6_3mm.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("mais6_3mm"));
		tableColumnB0_15mm.setCellValueFactory(new PropertyValueFactory<PlantBoundaries, Integer>("mais0_15mm"));
			
	}
	
	@FXML
	private TableView<TruckCapacity> tableViewTrucksCapacity;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnT1;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnT2;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnT3;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnT4;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnT5;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnT6;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnT7;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnT8;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnT9;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnT10;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnT11;

	@FXML
	private TableColumn<TruckCapacity, Integer> tableColumnT12;

	private ObservableList<TruckCapacity> obsList1;

	@FXML
	public void onbtImporTrucksCapacity() {
		String path = "E:\\UFOP\\TCC\\inputs\\truck'sCapacity.txt ";

		List<TruckCapacity> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String[] vect = line.split(",");

				int c1 = Integer.parseInt(vect[0]);
				int c2 = Integer.parseInt(vect[1]);
				int c3 = Integer.parseInt(vect[2]);
				int c4 = Integer.parseInt(vect[3]);
				int c5 = Integer.parseInt(vect[4]);
				int c6 = Integer.parseInt(vect[5]);
				int c7 = Integer.parseInt(vect[6]);
				int c8 = Integer.parseInt(vect[7]);
				int c9 = Integer.parseInt(vect[8]);
				int c10 = Integer.parseInt(vect[9]);
				int c11 = Integer.parseInt(vect[10]);
				int c12 = Integer.parseInt(vect[11]);

				list.add(new TruckCapacity(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12));
				line = br.readLine();
			}
			obsList1 = FXCollections.observableArrayList(list);
			tableViewTrucksCapacity.setItems(obsList1);

		} catch (IOException e) {
			System.out.println(" Erro: " + e.getMessage());
		}
	}
	
	@FXML
	private TableView<ProgrammedProduction> tableViewProgrammedProduction;

	@FXML
	private TableColumn<ProgrammedProduction, Double> tableColumnProd;

	private ObservableList<ProgrammedProduction> obsList2;

	@FXML
	public void onBtImportProgrammedProduction() {
		String path = "E:\\UFOP\\TCC\\inputs\\programmedProduction.txt ";

		List<ProgrammedProduction> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {

				list.add(new ProgrammedProduction(Double.parseDouble(line)));
				line = br.readLine();
			}
			obsList2 = FXCollections.observableArrayList(list);
			tableViewProgrammedProduction.setItems(obsList2);

		} catch (IOException e) {
			System.out.println(" Erro: " + e.getMessage());
		}
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
				double c1 = Double.parseDouble(vect[1]);
				double c2 = Double.parseDouble(vect[2]);
				double c3 = Double.parseDouble(vect[3]);
				double c4 = Double.parseDouble(vect[4]);
				double c5 = Double.parseDouble(vect[5]);
				double c6 = Double.parseDouble(vect[6]);
				double c7 = Double.parseDouble(vect[7]);
				double c8 = Double.parseDouble(vect[8]);
				double c9 = Double.parseDouble(vect[9]);
				double c10 = Double.parseDouble(vect[10]);
				double c11 = Double.parseDouble(vect[11]);
				double c12 = Double.parseDouble(vect[12]);

				list.add(new CycleTime(face, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12));
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
	private TableColumn<FaceGrades, Integer> tableColumnGPPC;

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
				double ppc = Double.parseDouble(vect[6]);
				double g50mm = Double.parseDouble(vect[7]);
				double g6_3mm = Double.parseDouble(vect[8]);
				double g0_15mm = Double.parseDouble(vect[9]);

				list.add(new FaceGrades(face, fe, sio2, al2o3, mn, p, ppc, g50mm, g6_3mm, g0_15mm));
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
	private TableColumn<PlantBoundaries, Integer> tableColumnBPPC;

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
				double ppc = Double.parseDouble(vect[6]);
				double g50mm = Double.parseDouble(vect[7]);
				double g6_3mm = Double.parseDouble(vect[8]);
				double g0_15mm = Double.parseDouble(vect[9]);

				list.add(new PlantBoundaries(face, fe, sio2, al2o3, mn, p, ppc, g50mm, g6_3mm, g0_15mm));
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
		loadView("/gui/ImportMinePlanView.fxml");
	}

	@FXML
	public void onMenuItemImportFaceGradesAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/ImportFaceGradesView.fxml", parentStage);
	}

	@FXML
	public void onMenuItemImportFaceProductionAction() {
		loadView("/gui/ImportFaceProductionView.fxml");
	}

	@FXML
	public void onMenuItemImportTrucksCapacityAction() {
		loadView("/gui/ImportTruckscapacityView.fxml");
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/AboutView.fxml");
	}

	@FXML
	public void onBtInicioAction() {
		txtArea1.clear();
		txtArea2.clear();
		txtArea3.clear();
		txtArea4.clear();
		txtArea5.clear();
		txtArea6.clear();
		txtArea7.clear();

		Random gerador = new Random();

		for (int i = 1; i <= 12; i++) {
			int num = gerador.nextInt(7) + 1;
			switch (num) {
			case 1:
				txtArea1.appendText(String.valueOf(i) + "\n");
				break;
			case 2:
				txtArea2.appendText(String.valueOf(i) + "\n");
				break;
			case 3:
				txtArea3.appendText(String.valueOf(i) + "\n");
				break;
			case 4:
				txtArea4.appendText(String.valueOf(i) + "\n");
				break;
			case 5:
				txtArea5.appendText(String.valueOf(i) + "\n");
				break;
			case 6:
				txtArea6.appendText(String.valueOf(i) + "\n");
				break;
			case 7:
				txtArea7.appendText(String.valueOf(i) + "\n");
				break;
			}

		}

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
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	private void createDialogForm(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Department data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}

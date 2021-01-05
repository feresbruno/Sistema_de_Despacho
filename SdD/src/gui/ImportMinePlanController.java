package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Entities.MinePlan;
import application.Main;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ImportMinePlanController implements Initializable {
	
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

	@FXML
	private Button btOpen;

	
	@FXML
	private Button btNew;
	
	private ObservableList<MinePlan> obsList;

	@FXML
	public void onBtOpenAction() {
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
	
	@FXML
	public void onBtNewAction() {
		loadView("/gui/MainView.fxml");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
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
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}

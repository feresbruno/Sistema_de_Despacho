package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;

public class ImportMinePlanController implements Initializable {

	@FXML
	VBox vb;

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

	@FXML
	private Button btOpen;

	@FXML
	private Button btNew;

	@FXML
	private Button btSave;
	
	

	private ObservableList<MinePlan> obsList;

	List<MinePlan> list = new ArrayList<>();

	@FXML
	public void onBtOpenAction() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File Dialog");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt", "txt"));

		File file = fileChooser.showOpenDialog(null);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			list.clear();
			String line = br.readLine();

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
				line = br.readLine();
			}
			obsList = FXCollections.observableArrayList(list);
			tableViewMinePlans.setItems(obsList);

			/**tableViewMinePlans.setEditable(true);
			tableColumnFace.setCellFactory(TextFieldTableCell.forTableColumn());
			tableColumnT1.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableColumnT2.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableColumnT3.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableColumnT4.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableColumnT5.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableColumnT6.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableColumnT7.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableColumnT8.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableColumnT9.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableColumnT10.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableColumnT11.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			tableColumnT12.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
**/
		} catch (IOException e) {
			Alerts.showAlert("File Reading Error", "Check the file!", AlertType.ERROR);
		}

	}

	@FXML
	public void onBtNewAction() {
		list.clear();
		for (int i = 0; i < 7; i++) {
			list.add(new MinePlan("-", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		}

		obsList = FXCollections.observableArrayList(list);
		tableViewMinePlans.setItems(obsList);
/**
		tableViewMinePlans.setEditable(true);
		tableColumnFace.setCellFactory(TextFieldTableCell.forTableColumn());
		tableColumnT1.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT2.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT3.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT4.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT5.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT6.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT7.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT8.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT9.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT10.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT11.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT12.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
**/
	}

	 //obsList
	
	@FXML
	public void onBtSaveAction() {
		/**
		String a=tableViewMinePlans.getColumns().get(0).getCellObservableValue(0).getValue().toString(); 
		
		System.out.println("value"+a);
		tableViewMinePlans.editingCellProperty();

	    MinePlan item = tableViewMinePlans.getItems().get(0);

	    TableColumn col = tableViewMinePlans.getColumns().get(0);
	    
	    String data = (String) col.getCellObservableValue(item).getValue();
	    
	    JOptionPane.showMessageDialog(null, data);
		
		
		
		/**
		
		
	
		
		
		
		
		
		
		// Item here is the table view type:
	    

	    // this gives the value in the selected cell:
	    
		/**
		obsList = FXCollections.observableArrayList(list);
		tableViewMinePlans.setItems(obsList);

		list = tableViewMinePlans.getItems();
		
		for (MinePlan x : list) {
			System.out.println(x);
		}
		if (list.isEmpty()) {
			Alerts.showAlert("File Saving Error", "There are not data", AlertType.ERROR);
		} else {
			for (MinePlan x : list) {
				System.out.println(x);
			}

			FileChooser fileChooser = new FileChooser();
			Window stage = vb.getScene().getWindow();
			fileChooser.setTitle("Save File Dialog");
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt", "txt"));

			File file = fileChooser.showSaveDialog(stage);

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

				for (MinePlan item : list) {
					bw.write(item.getFace() + "," + item.getT1() + "," + item.getT12() + "," + item.getT3() + ","
							+ item.getT4() + "," + item.getT5() + "," + item.getT6() + "," + item.getT7() + ","
							+ item.getT8() + "," + item.getT9() + "," + item.getT10() + "," + item.getT11() + ","
							+ item.getT12());
					bw.newLine();
				}

				Alerts.showAlert("Saved Data", "Data saved successfully", AlertType.CONFIRMATION);

			} catch (IOException e) {
				Alerts.showAlert("File Writing Error", "Check the data", AlertType.ERROR);
			}
		}**/
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
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
		tableViewMinePlans.setEditable(true);
		tableColumnFace.setCellFactory(TextFieldTableCell.forTableColumn());
		tableColumnT1.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT2.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT3.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT4.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT5.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT6.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT7.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT8.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT9.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT10.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT11.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		tableColumnT12.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
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
}

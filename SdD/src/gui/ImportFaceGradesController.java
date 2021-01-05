package gui;

import java.net.URL;
import java.util.ResourceBundle;

import Entities.FaceGrades;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ImportFaceGradesController implements Initializable {

	@FXML
	private TableView<FaceGrades> tableViewFaceGrades;

	@FXML
	private TableColumn<FaceGrades, String> tableColumnFace;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnFe;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnSiO2;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnAl2O3;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnMn;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnP;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnPPC;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnMaior50mm;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumMaior6_3mm;

	@FXML
	private TableColumn<FaceGrades, Integer> tableColumnMaior0_15mm;

	@FXML
	private Button btOpen;
	
	@FXML
	private Button btNew;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}

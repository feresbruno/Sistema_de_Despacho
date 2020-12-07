package gui;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ViewController {

	@FXML
	private Button btInicio;
	
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

}

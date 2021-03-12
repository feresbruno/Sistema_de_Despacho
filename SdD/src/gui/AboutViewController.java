package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AboutViewController implements Initializable {

	@FXML
	private ImageView img1;

	@FXML
	private ImageView img2;
	
	@Override
    public void initialize(URL uri, ResourceBundle rb) {
		
		File file = new File("E:\\UFOP\\TCC\\School of mines.png");
		File file1 = new File("E:\\UFOP\\TCC\\UFOP.png");
        Image image = new Image(file.toURI().toString());
        Image image1 = new Image(file1.toURI().toString());
        img1.setImage(image1);
        img2.setImage(image);
    }
}

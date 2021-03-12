package application;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	Stage window;
	
	private static Scene mainScene;

	@Override
	public void start(Stage primaryStage) throws InterruptedException {
		try {
			//new Splash();
						
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			
			primaryStage.setOnCloseRequest(e -> {e.consume(); closeProgram();});
			
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);

			mainScene = new Scene(scrollPane);
			
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("1.0.0 goTruck® - Dispatch System");
			Image ico = new Image("/gui/util/Images/truck.png");
			primaryStage.getIcons().add(ico);
			primaryStage.show();
			
			window = primaryStage;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void closeProgram() {
		int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the application?","Warning", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
		if(answer == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
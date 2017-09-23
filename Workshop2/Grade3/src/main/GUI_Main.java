package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI_Main extends Application {

	// If Error shows, Go to Workshop2_Grade2 -> Build path -> Add libraries -> Add
	// JavaFX SDK. Its in built library but you still need to import.

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/GUI.fxml"));
			Scene scene = new Scene(root, 800, 500);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

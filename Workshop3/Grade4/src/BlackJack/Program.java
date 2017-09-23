package BlackJack;

import BlackJack.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {

	public static void main(String[] a_args) {
		launch(a_args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainController ctr = new MainController();
		ctr.Start(primaryStage);
	}
}
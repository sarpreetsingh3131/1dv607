package BlackJack.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import BlackJack.view.GameView;
import BlackJack.view.MainView;
import BlackJack.view.SettingsView;
import BlackJack.view.SettingsView.Language;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainController implements Initializable {

	@FXML private Label play;
	@FXML private Label settings;
	@FXML private Label quit;
	@FXML private ImageView imageView;
	protected static Language currentLanguage = Language.English;
	protected Stage stage;
	protected MainView mainView;
	protected SettingsView settingsView;
	protected GameView gameView;
	
	
	public MainController(){
		mainView = new MainView();
		settingsView = new SettingsView();
		gameView = new GameView();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainView.SetPageLanguage(currentLanguage, play, settings, quit);
		mainView.SetBackgroundImage(imageView);
		play.setOnMouseClicked(e -> DisplayGamePage(play));
		settings.setOnMouseClicked(e -> DisplaySettingsPage(settings));
		quit.setOnMouseClicked(e -> QuitGame());
		AddEffects(new Label[]{play, settings, quit});
	}

	public void Start(Stage stage) {
		this.stage = stage;
		this.stage.setResizable(false);
		DisplayPage(mainView.GetMainPageName());
	}
	
	protected void QuitGame(){
		System.exit(0);
	}
	
	protected void AddEffects(Label[] list){
		for(int i = 0; i < list.length; i++){
			Label temp = list[i];
			temp.setOnMouseEntered(e ->  SetHover(temp, true));
			temp.setOnMouseExited(e ->  SetHover(temp, false));
		}
	}
	
	protected void DisaplayMainPage(Label label) {
		stage = (Stage) label.getScene().getWindow();
		DisplayPage(mainView.GetMainPageName());
	}
	
	private void DisplaySettingsPage(Label label) {
		stage = (Stage) label.getScene().getWindow();
		DisplayPage(mainView.GetSettingsPageName());
	}
	
	private void DisplayGamePage(Label label) {
		stage = (Stage) label.getScene().getWindow();
		DisplayPage(mainView.GetGamePageName());
	}
	
	private void DisplayPage(String path) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/BlackJack/view/" + path + ".fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private void SetHover(Label label, boolean isOnHover){
		if (isOnHover) {
			label.setTextFill(Color.rgb(201, 123, 211));
			label.setFont(Font.font(label.getFont().getSize() + 3));
		} else {
			label.setTextFill(Color.WHITE);
			label.setFont(Font.font(label.getFont().getSize() - 3));
		}
	}
}
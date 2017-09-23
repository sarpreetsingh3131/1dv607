package BlackJack.controller;

import java.net.URL;
import java.util.ResourceBundle;
import BlackJack.view.SettingsView.GameMode;
import BlackJack.view.SettingsView.Language;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SettingsController extends MainController {

	@FXML private Label gameMode;
	@FXML private Label language;
	@FXML private Label english;
	@FXML private Label swedish;
	@FXML private Label back;
	@FXML private ChoiceBox<GameMode> gameModeChoices;
	@FXML private ImageView imageView;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		settingsView.SetPageLanguage(currentLanguage, language, back, gameMode, english, swedish);
		settingsView.SetBackgroundImage(imageView);
		settingsView.SetAllGameModes(gameModeChoices);
		back.setOnMouseClicked(e -> super.DisaplayMainPage(back));
		english.setOnMouseClicked( e -> ChangeToEnglish());
		swedish.setOnMouseClicked(e -> ChangeToSwedish());
		gameModeChoices.setOnAction(e -> GetNewGameMode());
		AddEffects(new Label[]{back, english, swedish});
	}
	
	public void ChangeToEnglish(){
		currentLanguage = Language.English;
		settingsView.ChangeToEnglish(language, back, gameMode, english, swedish);
	}
	
	public void ChangeToSwedish(){
		currentLanguage = Language.Swedish;
		settingsView.ChangeToSwedish(language, back, gameMode, english, swedish);
	}
	
	public void GetNewGameMode(){
		settingsView.SetGameMode(gameModeChoices);
	}
}
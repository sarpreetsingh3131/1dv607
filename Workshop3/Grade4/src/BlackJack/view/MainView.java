package BlackJack.view;

import BlackJack.view.SettingsView.Language;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainView {

	private Image backgroundImage = new Image("https://lh3.ggpht.com/zKR6s9Gaqqa4XYnscTMiaH6o1gM5mlIjueiNwBbSt8hq1dGar7zZRHT9FMWCNwNjvQ=h900");
	private final String MAINPAGE_NAME = "Mainpage";
	private final String SETTINGPAGE_NAME = "Settingspage";
	private final String GAMEPAGE_NAME = "Gamepage";


	
	public void SetPageLanguage(Language currentLanguage, Label play, Label settings, Label quit) {
		switch (currentLanguage) {
		case English:
			play.setText("PLAY");
			settings.setText("SETTINGS");
			quit.setText("QUIT");
			break;
		case Swedish:
			play.setText("SPEL");
			settings.setText("INSTÄLLINGAR");
			quit.setText("AVSLUTA");
			break;
		}
	}

	public void SetBackgroundImage(ImageView imageView) {
		imageView.setImage(backgroundImage);
	}

	public String GetMainPageName() {
		return MAINPAGE_NAME;
	}

	public String GetSettingsPageName() {
		return SETTINGPAGE_NAME;
	}
	
	public String GetGamePageName() {
		return GAMEPAGE_NAME;
	}
}
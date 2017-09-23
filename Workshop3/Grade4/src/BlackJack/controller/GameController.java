package BlackJack.controller;

import java.net.URL;
import java.util.ResourceBundle;
import BlackJack.model.Game;
import BlackJack.model.IObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class GameController extends MainController implements IObserver{
	
	@FXML private Label back;
	@FXML private Label play;
	@FXML private Label stand;
	@FXML private Label hit;
	@FXML private Label quit;
	@FXML private Label player;
	@FXML private Label dealer;
	@FXML private TextArea rules;
	@FXML private HBox playerCardDisplayer;
	@FXML private HBox dealerCardDisplayer;
	private Game game;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		game = settingsView.GetSelectedGame(rules);
		game.AddSubscribers(this);
		gameView.SetPageLanguage(currentLanguage, back, play, stand, hit, quit, player, dealer);
		SetLabelsOnAction();
		AddEffects(new Label[]{back, play, stand, hit, quit});
		stand.setDisable(true);
		hit.setDisable(true);
	}

	@Override
	public void PlayerGetNewCard() {
		try {
			gameView.DisplayDealerHand(game.GetDealerHand(), dealerCardDisplayer, game.GetDealerScore(), dealer);
			gameView.DisplayPlayerHand(game.GetPlayerHand(), playerCardDisplayer, game.GetPlayerScore(), player);
			
			if(game.IsGameOver()){
				gameView.DisplayGameOver(currentLanguage, game.IsDealerWinner());
				play.setDisable(false);
				hit.setDisable(true);
				stand.setDisable(true);
			}
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void NewGame() {
		play.setDisable(true);
		hit.setDisable(false);
		stand.setDisable(false);
		game.NewGame();
	}
	
	public void SetLabelsOnAction() {
		quit.setOnMouseClicked(e -> super.QuitGame());
		back.setOnMouseClicked(e -> super.DisaplayMainPage(back));
		play.setOnMouseClicked(e -> NewGame());
		stand.setOnMouseClicked(e -> game.Stand());
		hit.setOnMouseClicked(e -> game.Hit());
	}
}
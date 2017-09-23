package BlackJack.view;

import BlackJack.model.Card;
import BlackJack.model.Card.Value;
import BlackJack.view.SettingsView.Language;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GameView {
	
	private final double imageHeight = 250.0;
	private final double imageWidth = 200.0;
	
	
	public void SetPageLanguage(Language currentLanguage, Label back, Label play, Label stand, Label hit, Label quit, Label player, Label dealer){
			switch(currentLanguage){
			case English:
				back.setText("Back");
				play.setText("Play");
				stand.setText("Stand");
				hit.setText("Hit");
				quit.setText("Quit");
				player.setText("Player (Score: 0)");
				dealer.setText("Dealer (Score: 0)");
				break;
			case Swedish:
				back.setText("Tillbacka");
				play.setText("Spela");
				stand.setText("Stanna");
				hit.setText("Hämta");
				quit.setText("Avsluta");
				player.setText("Spelaren (Poäng: 0)");
				dealer.setText("Givaren (Poäng: 0)");
				break;
			}
	}
	
	public void DisplayDealerHand(Iterable<Card> dealerHand, HBox dealerCardDisplayer, int score, Label dealer) {
		DisplayHand(dealerHand, dealerCardDisplayer, score, dealer);
	}
	
	public void DisplayPlayerHand(Iterable<Card> playerHand, HBox playerCardDisplayer, int score, Label player) {
		DisplayHand(playerHand, playerCardDisplayer, score, player);
	}
	
	public void DisplayGameOver(Language currentLanguage, boolean isDealerWinner) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Game Over !!");
		switch (currentLanguage) {
		case English:
			if (isDealerWinner)
				alert.setContentText("Dealer Won !!");
			else
				alert.setContentText("You Won !!");
			break;
		case Swedish:
			if (isDealerWinner)
				alert.setContentText("Dealern Vann !!");
			else
				alert.setContentText("Du Vann !!");
			break;
		}
		alert.show();
	}
	
	private void DisplayHand(Iterable<Card> hand, HBox cardDisplayer, int score, Label player) {
		cardDisplayer.getChildren().clear();
		for(Card c: hand){
			ImageView imageView = new ImageView(new Image(GetCardLink(c)));
			imageView.setFitHeight(imageHeight);
			imageView.setFitWidth(imageWidth);
			imageView.setPreserveRatio(false);
			cardDisplayer.getChildren().add(imageView);
		}
		player.setText(player.getText().split(":")[0]  + ":  " + score + ")");
	}
	
	private String GetCardLink(Card card) {
		if(card.GetValue() == Value.Hidden){
			return "http://chetart.com/blog/wp-content/uploads/2012/05/playing-card-back.jpg";
		}
		StringBuilder make = new StringBuilder("http://homepage.lnu.se/student/sl222xk/playingcards/");
		make.append(card.GetValue());
		make.append("_of_");
		make.append(card.GetColor());
		make.append(".png");

		return make.toString();
	}
}
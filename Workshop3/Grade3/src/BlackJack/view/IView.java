package BlackJack.view;

public interface IView {
	public enum Command {PLAY, HIT, STAND, QUIT, INVALID}
	void DisplayWelcomeMessage();

	Command GetInput();

	void DisplayCard(BlackJack.model.Card a_card);

	void DisplayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score);

	void DisplayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score);

	void DisplayGameOver(boolean a_dealerIsWinner);
	
	void CreateNewView();
}
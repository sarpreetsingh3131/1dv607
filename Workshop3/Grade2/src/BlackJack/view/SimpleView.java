package BlackJack.view;

public class SimpleView implements IView {

	private final char play = 'p';
	private final char hit = 'h';
	private final char stand = 's';
	private final char quit = 'q';

	public void DisplayWelcomeMessage() {
		System.out.println("Hello Black Jack World");
		System.out.println("Type " + "\'" + play + "\' to Play, " + "\'" + hit + "\' to Hit, " + "\'" + stand
				+ "\' to Stand, " + "\'" + quit + "\' to Quit\n");
	}

	public Command GetInput() {
		int input = GetIntInput();
		switch (input) {
		case play:
			return Command.PLAY;
		case hit:
			return Command.HIT;
		case stand:
			return Command.STAND;
		case quit:
			return Command.QUIT;
		default:
			System.out.println("<< INVALID OPTION. TRY AGAIN !! >>\n");
			return Command.INVALID;
		}
	}

	public void DisplayCard(BlackJack.model.Card a_card) {
		System.out.println("" + a_card.GetValue() + " of " + a_card.GetColor());
	}

	public void DisplayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score) {
		DisplayHand("Player", a_hand, a_score);
	}

	public void DisplayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score) {
		DisplayHand("Dealer", a_hand, a_score);
	}

	private void DisplayHand(String a_name, Iterable<BlackJack.model.Card> a_hand, int a_score) {
		System.out.println(a_name + " Has: ");
		for (BlackJack.model.Card c : a_hand) {
			DisplayCard(c);
		}
		System.out.println("Score: " + a_score + "\n");
	}

	public void DisplayGameOver(boolean a_dealerIsWinner) {
		System.out.println("GameOver: ");
		if (a_dealerIsWinner) {
			System.out.println("Dealer Won!");
		} else {
			System.out.println("You Won!");
		}
	}
	public void CreateNewView(){
		 for(int i = 0; i < 50; i++) {System.out.print("\n");}; 
	}
	private int GetIntInput() {
		try {
			int c = System.in.read();
			while (c == '\r' || c == '\n') {
				c = System.in.read();
			}
			return c;
		} catch (java.io.IOException e) {
			System.out.println("" + e);
			return 0;
		}
	}
	
}
package BlackJack.view;

public class SwedishView implements IView {

	private final char play = 'p';
	private final char hit = 'h';
	private final char stand = 's';
	private final char quit = 'q';

	public void DisplayWelcomeMessage() {
		System.out.println("Hej Black Jack Världen");
		System.out.println("Skriv " + "\'" + play + "\' för att Spela, " + "\'" + hit + "\' för nytt Kort, " + "\'"
				+ stand + "\' för att Stanna, " + "\'" + quit + "\' för att avsluta\n");
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
			System.out.println("<< OGILTIGT VAL. FÖRSÖK IGEN !! >>\n");
			return Command.INVALID;
		}
	}

	public void DisplayCard(BlackJack.model.Card a_card) {
		if (a_card.GetColor() == BlackJack.model.Card.Color.Hidden) {
			System.out.println("Dolt Kort");
		} else {
			String colors[] = { "Hjärter", "Spader", "Ruter", "Klöver" };
			String values[] = { "två", "tre", "fyra", "fem", "sex", "sju", "åtta", "nio", "tio", "knekt", "dam", "kung",
					"ess" };
			System.out.println("" + colors[a_card.GetColor().ordinal()] + " " + values[a_card.GetValue().ordinal()]);
		}
	}

	public void DisplayPlayerHand(Iterable<BlackJack.model.Card> a_hand, int a_score) {
		DisplayHand("Spelare", a_hand, a_score);
	}

	public void DisplayDealerHand(Iterable<BlackJack.model.Card> a_hand, int a_score) {
		DisplayHand("Croupier", a_hand, a_score);
	}

	public void DisplayGameOver(boolean a_dealerIsWinner) {
		System.out.println("Slut: ");
		if (a_dealerIsWinner) {
			System.out.println("Croupiern Vann!");
		} else {
			System.out.println("Du vann!");
		}
	}
	
	public void CreateNewView(){
		 for(int i = 0; i < 50; i++) {System.out.print("\n");}; 
	}

	private void DisplayHand(String a_name, Iterable<BlackJack.model.Card> a_hand, int a_score) {
		System.out.println(a_name + " Har: " + a_score);
		for (BlackJack.model.Card c : a_hand) {
			DisplayCard(c);
		}
		System.out.println("Poäng: " + a_score + "\n");
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
package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

class AmericanNewGameStrategy implements INewGameStrategy {

	public boolean NewGame(Dealer a_dealer, Player a_player) {
		
		a_dealer.GetCardFromDeckAndDealTo(a_player, true);

		a_dealer.GetCardFromDeckAndDealTo(a_dealer, true);
		
		a_dealer.GetCardFromDeckAndDealTo(a_player, true);

		a_dealer.GetCardFromDeckAndDealTo(a_dealer, false);
	
		return true;
	}
}
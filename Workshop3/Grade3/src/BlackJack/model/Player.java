package BlackJack.model;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Player {

	private List<Card> m_hand;
	protected final int g_maxScore = 21;
	private ArrayList<IObserver> m_subscribers;

	
	public Player() {
		m_hand = new LinkedList<Card>();
		m_subscribers = new ArrayList<IObserver>();
	}

	public void DealCard(Card a_addToHand) {
		m_hand.add(a_addToHand);
		NotifySubscribers();
	}

	public Iterable<Card> GetHand() {
		return m_hand;
	}

	public void ClearHand() {
		m_hand.clear();
	}

	public void ShowHand() {
		for (Card c : m_hand) {
			if(c.GetValue()==Card.Value.Hidden){
				c.Show(true);
				NotifySubscribers();
			}		
		}
	}
	
	public int CalcScore() {
		int score = CalcScoreWithoutAce();
		if (score > g_maxScore) {
			for (Card c : GetHand()) {
				if (c.GetValue() == Card.Value.Ace && score > g_maxScore) {
					score -= 10;
				}
			}
		}
		return score;
	}
	public int CalcScoreWithoutAce() {
		int cardScores[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
		assert (cardScores.length == Card.Value.Count.ordinal()) : "Card Scores array size does not match number of card values";
		
		int score = 0;
		for (Card c : GetHand()) {
			if (c.GetValue() != Card.Value.Hidden) {
				score += cardScores[c.GetValue().ordinal()];
			}
		}
		
		return score;
	}
	
	public void AddSubscribers(IObserver a_subscriber){
		m_subscribers.add(a_subscriber);
	}
	
	private void NotifySubscribers(){
		for(IObserver a_subscriber: m_subscribers){
			a_subscriber.PlayerGetNewCard();
		}
	}
}
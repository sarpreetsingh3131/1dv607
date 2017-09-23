package BlackJack.view;

import BlackJack.model.rules.IGameVisitor;
import BlackJack.model.rules.IHitStrategy;
import BlackJack.model.rules.INewGameStrategy;
import BlackJack.model.rules.IWinStrategy;

public class RulePrinterVisitor implements IGameVisitor {
	
	@Override
	public void VisitAmericanNewGameStrategy(INewGameStrategy am_newGameStrategy) {
			System.out.println("Game Strategy: American new game strategy");	
	}

	@Override
	public void VisitInternationalNewGameStrategy(INewGameStrategy int_newGameStrategy) {
		System.out.println("Game Strategy: International new game strategy");	
	}

	@Override
	public void VisitBasicHitStrategy(IHitStrategy bas_hitStrategy) {
		System.out.println("Hit Strategy: Basic hit strategy");	
	}
	
	@Override
	public void VisitSoft17HitStrategy(IHitStrategy soft17_hitStrategy) {
		System.out.println("Hit Strategy: Soft-17 hit strategy");	
	}


	@Override
	public void VisitDealerWinStrategy(IWinStrategy deal_winStrategy) {
		System.out.println("Win Strategy: Dealer win strategy");
	}

	@Override
	public void VisitPlayerWinStrategy(IWinStrategy deal_winStrategy) {
		System.out.println("Win Strategy: Player win strategy");	
	}
}
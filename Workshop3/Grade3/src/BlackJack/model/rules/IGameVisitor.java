package BlackJack.model.rules;

public interface IGameVisitor {

	public void VisitAmericanNewGameStrategy(INewGameStrategy am_newGameStrategy);

	public void VisitInternationalNewGameStrategy(INewGameStrategy int_newGameStrategy);

	public void VisitBasicHitStrategy(IHitStrategy bas_hitStrategy);

	public void VisitSoft17HitStrategy(IHitStrategy soft17_hitStrategy);

	public void VisitDealerWinStrategy(IWinStrategy deal_winStrategy);

	public void VisitPlayerWinStrategy(IWinStrategy deal_winStrategy);
}

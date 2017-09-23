package BlackJack.model.rules;

class BasicInternationalStyleWithDealerAdvantange implements IGame {

	@Override
	public INewGameStrategy GetNewGameStrategy() {
		return new InternationalNewGameStrategy();
	}

	@Override
	public IHitStrategy GetHitStrategy() {
		return new BasicHitStrategy();
	}

	@Override
	public IWinStrategy GetWinStrategy() {
		return new DealerWinStrategy();
	}
	
	@Override
	public void Accept(IGameVisitor a_visitor) {
		a_visitor.VisitInternationalNewGameStrategy(this.GetNewGameStrategy());
		a_visitor.VisitBasicHitStrategy(this.GetHitStrategy());
		a_visitor.VisitDealerWinStrategy(this.GetWinStrategy());
	}
}

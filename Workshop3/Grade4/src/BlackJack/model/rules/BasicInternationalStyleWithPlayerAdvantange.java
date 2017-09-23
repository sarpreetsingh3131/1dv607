package BlackJack.model.rules;

class BasicInternationalStyleWithPlayerAdvantange implements IGame {

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
		return new PlayerWinStrategy();
	}
	
	@Override
	public void Accept(IGameVisitor a_visitor) {
		a_visitor.VisitInternationalNewGameStrategy(this.GetNewGameStrategy());
		a_visitor.VisitBasicHitStrategy(this.GetHitStrategy());
		a_visitor.VisitPlayerWinStrategy(this.GetWinStrategy());
	}
}

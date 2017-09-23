package BlackJack.model.rules;

class BasicAmericanStyleWithPlayerAdvantange implements IGame {

	@Override
	public INewGameStrategy GetNewGameStrategy() {
		return new AmericanNewGameStrategy();
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
		a_visitor.VisitAmericanNewGameStrategy(this.GetNewGameStrategy());
		a_visitor.VisitBasicHitStrategy(this.GetHitStrategy());
		a_visitor.VisitPlayerWinStrategy(this.GetWinStrategy());
	}
}

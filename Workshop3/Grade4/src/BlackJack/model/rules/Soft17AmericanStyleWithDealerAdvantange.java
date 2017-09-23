package BlackJack.model.rules;

class Soft17AmericanStyleWithDealerAdvantange implements IGame {

	@Override
	public INewGameStrategy GetNewGameStrategy() {
		return new AmericanNewGameStrategy();
	}

	@Override
	public IHitStrategy GetHitStrategy() {
		return new Soft17HitStrategy();
	}

	@Override
	public IWinStrategy GetWinStrategy() {
		return new DealerWinStrategy();
	}
	
	@Override
	public void Accept(IGameVisitor a_visitor) {
		a_visitor.VisitAmericanNewGameStrategy(this.GetNewGameStrategy());
		a_visitor.VisitSoft17HitStrategy(this.GetHitStrategy());
		a_visitor.VisitDealerWinStrategy(this.GetWinStrategy());
	}
}

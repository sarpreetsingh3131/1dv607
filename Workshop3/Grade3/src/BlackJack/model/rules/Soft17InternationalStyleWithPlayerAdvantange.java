package BlackJack.model.rules;

class Soft17InternationalStyleWithPlayerAdvantange implements IGame {

	@Override
	public INewGameStrategy GetNewGameStrategy() {
		return new InternationalNewGameStrategy();
	}

	@Override
	public IHitStrategy GetHitStrategy() {
		return new Soft17HitStrategy();
	}
	
	@Override
	public IWinStrategy GetWinStrategy() {
		return new PlayerWinStrategy();
	}
	
	@Override
	public void Accept(IGameVisitor a_visitor) {
		a_visitor.VisitInternationalNewGameStrategy(this.GetNewGameStrategy());
		a_visitor.VisitSoft17HitStrategy(this.GetHitStrategy());
		a_visitor.VisitPlayerWinStrategy(this.GetWinStrategy());
	}
}

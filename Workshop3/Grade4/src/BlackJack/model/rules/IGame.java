package BlackJack.model.rules;

public interface IGame {

	public INewGameStrategy GetNewGameStrategy();

	public IHitStrategy GetHitStrategy();

	public IWinStrategy GetWinStrategy();
	
	public void Accept(IGameVisitor a_visitor);
}
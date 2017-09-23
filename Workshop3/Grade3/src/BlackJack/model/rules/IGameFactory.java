package BlackJack.model.rules;

public interface IGameFactory {

	public IGame CreateBasicAmericanStyleWithDealerAdvantange();

	public IGame CreateBasicAmericanStyleWithPlayerAdvantange();

	public IGame CreateSoft17AmericanStyleWithPlayerAdvantange();

	public IGame CreateSoft17AmericanStyleWithDealerAdvantange();

	public IGame CreateBasicInternationalStyleWithDealerAdvantange();

	public IGame CreateBasicInternationalStyleWithPlayerAdvantange();

	public IGame CreateSoft17InternationalStyleWithPlayerAdvantange();

	public IGame CreateSoft17InternationalStyleWithDealerAdvantange();
}

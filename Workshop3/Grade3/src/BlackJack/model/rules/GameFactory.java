package BlackJack.model.rules;

public class GameFactory implements IGameFactory {

	@Override
	public IGame CreateBasicAmericanStyleWithDealerAdvantange() {
		return new BasicAmericanStyleGameWithDealerAdvantage();
	}

	@Override
	public IGame CreateBasicAmericanStyleWithPlayerAdvantange() {
		return new BasicAmericanStyleWithPlayerAdvantange();
	}

	@Override
	public IGame CreateSoft17AmericanStyleWithDealerAdvantange() {
		return new Soft17AmericanStyleWithDealerAdvantange();
	}

	@Override
	public IGame CreateSoft17AmericanStyleWithPlayerAdvantange() {
		return new Soft17AmericanStyleWithPlayerAdvantange();
	}

	@Override
	public IGame CreateBasicInternationalStyleWithDealerAdvantange() {
		return new BasicInternationalStyleWithDealerAdvantange();
	}

	@Override
	public IGame CreateBasicInternationalStyleWithPlayerAdvantange() {
		return new BasicInternationalStyleWithPlayerAdvantange();
	}

	@Override
	public IGame CreateSoft17InternationalStyleWithDealerAdvantange() {
		return new Soft17InternationalStyleWithDealerAdvantange();
	}
	
	@Override
	public IGame CreateSoft17InternationalStyleWithPlayerAdvantange() {
		return new Soft17InternationalStyleWithPlayerAdvantange();
	}
}
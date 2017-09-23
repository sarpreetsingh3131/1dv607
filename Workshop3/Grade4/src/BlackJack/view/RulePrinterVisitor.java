package BlackJack.view;

import BlackJack.model.rules.IGameVisitor;
import BlackJack.model.rules.IHitStrategy;
import BlackJack.model.rules.INewGameStrategy;
import BlackJack.model.rules.IWinStrategy;
import javafx.scene.control.TextArea;

public class RulePrinterVisitor implements IGameVisitor {

	private TextArea textArea;

	public RulePrinterVisitor(TextArea view) {
		this.textArea = view;
	}
	
	@Override
	public void VisitAmericanNewGameStrategy(INewGameStrategy am_newGameStrategy) {	
		textArea.setText("Game Strategy: American Style\n");
	}

	@Override
	public void VisitInternationalNewGameStrategy(INewGameStrategy int_newGameStrategy) {
		textArea.setText("Game Strategy: International Style\n");	
	}

	@Override
	public void VisitBasicHitStrategy(IHitStrategy bas_hitStrategy) {	
		textArea.setText(textArea.getText() + "Hit Strategy: Basic\n");
	}
	
	@Override
	public void VisitSoft17HitStrategy(IHitStrategy soft17_hitStrategy) {
		textArea.setText(textArea.getText() + "Hit Strategy: Soft-17\n");	
	}

	@Override
	public void VisitDealerWinStrategy(IWinStrategy deal_winStrategy) {
		textArea.setText(textArea.getText() + "Win Strategy: Dealer Win");
	}

	@Override
	public void VisitPlayerWinStrategy(IWinStrategy deal_winStrategy) {
		textArea.setText(textArea.getText() + "Win Strategy: Player Win");	
	}
}
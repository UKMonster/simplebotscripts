package nPlankMakerNovea;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class BankTask extends Task {
	public BankTask(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {

		// TODO Auto-generated method stub
		return ctx.inventory.populate().filter("mahogany plank").population() > 0;
	}

	@Override
	public void run() {
		if(!ctx.bank.bankOpen()){
			ctx.pathing.step(new WorldPoint(3101,3504,0));
			ctx.pathing.step(new WorldPoint(3097,3495,0));
			SimpleObject bank = ctx.objects.populate().filter("Bank Booth").nearest().next();
			if(bank != null && bank.validateInteractable() ){
				bank.click("bank");
				ctx.onCondition(() -> ctx.bank.bankOpen(),600,10);
			}
		} else {
			ctx.bank.depositAllExcept("coins");
			ctx.onCondition(() -> ctx.inventory.populate().filter("mahogany plank").isEmpty(),500,10);
			ctx.bank.withdraw("mahogany logs", 27);
			ctx.onCondition(() -> !ctx.inventory.populate().filter("mahogany logs").isEmpty(),500,10);
			ctx.bank.closeBank();
			ctx.onCondition(()-> !ctx.bank.bankOpen());
		}

	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Banking";
	}

}

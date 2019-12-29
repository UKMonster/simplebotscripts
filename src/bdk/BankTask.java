package bdk;

import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.api.ClientContext;

public class BankTask extends Task{

	public BankTask(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {


		return ctx.inventory.populate().filter("manta ray").isEmpty();
	}



	@Override
	public void run() {
		ctx.sleep(600);
		SimpleNpc bank = ctx.npcs.populate().filter("Banker").nearest().next();
		if(bank != null){
			if(bank.validateInteractable()){
				bank.click("bank");
				ctx.onCondition(() -> ctx.bank.bankOpen(),6000);
				if(ctx.bank.bankOpen()){
					ctx.bank.depositInventory();
					ctx.onCondition(() -> ctx.inventory.populate().population() < 0,6000);
					SimpleItem mantaray = ctx.bank.populate().filter("manta ray").next();
					if(mantaray != null){
						mantaray.click("withdraw-10");
						ctx.sleep(600);
					}
					SimpleItem teletab = ctx.bank.populate().filter("Zenyte home teleport").next();
					if(teletab != null){
						teletab.click("withdraw-1");
						ctx.sleep(600);
					}
					ctx.bank.closeBank();
				}
			}
		}
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return null;
	}

}

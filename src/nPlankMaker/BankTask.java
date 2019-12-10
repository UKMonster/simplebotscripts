package nPlankMaker;

import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class BankTask extends Task {

	private String plankName;
	public BankTask(ClientContext ctx, String plankName) {
		super(ctx);
	this.plankName = plankName;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {
		// TODO Auto-generated method stub
		return ctx.inventory.populate().filter("Mahogany Logs").population() <= 1;
	}

	@Override
	public void run() {
		if(!ctx.bank.bankOpen()){
			SimpleObject depositbox = ctx.objects.populate().filter("Grand Exchange Booth","bank booth").nextNearest();
			if(depositbox != null && depositbox.validateInteractable()){
				ctx.sleep(300);
				depositbox.click("Bank");
				ctx.sleepCondition(() -> ctx.bank.bankOpen(),6000);
				if(ctx.bank.bankOpen()){
					SimpleItem planks = ctx.inventory.populate().filter("Mahogany Plank").next();
					planks.click("deposit-all");
					ctx.sleepCondition(() -> ctx.inventory.population() < 2,5000);
					SimpleItem planksB = ctx.bank.populate().filter("Mahogany Logs").next();
					planksB.click("withdraw-all");
					ctx.onCondition(() -> ctx.inventory.populate().population() > 25,300,10);
					ctx.bank.closeBank();
				}
			}
		}
	}
	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Banking Planks";
	}

}

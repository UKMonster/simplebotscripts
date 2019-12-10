package nEssenceMiner;

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
		return ctx.inventory.inventoryFull();
	}

	@Override
	public void run() {
		if(!ctx.bank.depositBoxOpen()){
			SimpleObject depositbox = ctx.objects.populate().filter("Bank deposit box").nextNearest();
			if(depositbox != null){
				if(depositbox.validateInteractable()){
					ctx.sleep(300);
					depositbox.click("Deposit");
					ctx.sleepCondition(() -> ctx.bank.depositBoxOpen(),6000);
					if(ctx.bank.depositBoxOpen() == true){
						ctx.bank.depositInventory();
						ctx.sleepCondition(() -> ctx.inventory.populate().isEmpty(),5000);
						ctx.bank.closeBank();
					}
				}
			}
		}
	}
	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Banking Essence";
	}

}

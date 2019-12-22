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
			SimpleObject depositbox = ctx.objects.populate().filter("Bank deposit box").nearest().next();
			if(depositbox != null){
				if(depositbox.validateInteractable()){
					ctx.sleep(300);
					depositbox.click("Deposit");
					ctx.onCondition(() -> ctx.bank.depositBoxOpen(),600,10);
					if(ctx.bank.depositBoxOpen() == true){
						ctx.bank.depositInventory();
						ctx.onCondition(() -> ctx.inventory.populate().isEmpty(),500,10);
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

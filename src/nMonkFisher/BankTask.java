package nMonkFisher;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;
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
			ctx.pathing.step(new WorldPoint(2329,3689,0));
			//ctx.sleep(5500);
			SimpleNpc depositbox = ctx.npcs.populate().filter("Arnold Lydspor").nearest().next();
			if(depositbox != null){
				if(depositbox.validateInteractable()){
					ctx.sleep(300);
					depositbox.click("bank");
					ctx.onCondition(() -> ctx.bank.bankOpen(),600,10);
					if(ctx.bank.bankOpen() == true){
						SimpleItem monk = ctx.inventory.populate().filter("raw monkfish").next();
						if(monk != null){
							monk.click("Deposit-All");
							ctx.onCondition(() -> ctx.inventory.populate().filter("raw monkfish").isEmpty(),500,10);
						}
						ctx.bank.closeBank();
					}
				}
			}
		}
	}
	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Banking Fish";
	}

}

package nBarSmelter;

import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class BankTask extends Task {

	private Bars barType;
	public BankTask(ClientContext ctx, Bars barType) {
		super(ctx);
		this.barType = barType;

		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {
		// TODO Auto-generated method stub
		return ctx.inventory.populate().filter(barType.productName).population() >= barType.barsPerInv;
	}

	@Override
	public void run() {
		if(!ctx.bank.bankOpen()){
			SimpleObject depositbox = ctx.objects.populate().filter("Grand Exchange Booth","bank booth").nextNearest();
			if(depositbox != null && depositbox.validateInteractable()){
				if (depositbox.click("Bank") && ctx.onCondition(() -> ctx.bank.bankOpen(),300,10)) {
					bankItems();
				}
			}
		} else {
			bankItems();
		}
	}

	int itemtowithdraw1 = barType.itemsRequired[0][0];
	int itemtowithdraw2 = barType.itemsRequired[1][0];
	int withdrawamount1 = barType.itemsRequired[0][1];
	int withdrawamount2 = barType.itemsRequired[1][1];
	
	private void bankItems() {
		if(ctx.inventory.populate().filter(barType.productName).isEmpty()) {
		ctx.bank.depositInventory();
		}
		SimpleItem itemA = ctx.bank.populate().filter(itemtowithdraw1).next();
		if(!ctx.inventory.populate().filter(itemtowithdraw1).isEmpty() && itemA != null && ctx.bank.withdraw(itemtowithdraw1, withdrawamount1)){
			SimpleItem itemB = ctx.bank.populate().filter(itemtowithdraw2).next();
			if(!ctx.inventory.populate().filter(itemtowithdraw2).isEmpty() && itemB != null && ctx.bank.withdraw(itemtowithdraw2, withdrawamount2)){
				ctx.bank.closeBank();
			} else if (itemB == null){
				ctx.bank.closeBank();
			}
		} else if(itemA == null){
			ctx.stopScript();
		}
	}
	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Banking...";
	}


}

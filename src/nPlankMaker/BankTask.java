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
		return ctx.inventory.populate().filter(plankName).isEmpty();
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

	    private void bankItems() {
	        ctx.bank.depositAllExcept("Nature rune", "Astral rune", "Earth rune", "Coins","Rune pouch");
	        SimpleItem planksB = ctx.bank.populate().filter(plankName).next();
	        if(planksB != null && planksB.click("withdraw-all")&& ctx.onCondition(() -> !ctx.inventory.populate().filter(plankName).isEmpty(),300,10)){
	            ctx.bank.closeBank();
	        } else if(planksB == null){
	            ctx.stopScript();
	        }
	    }
	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Banking Planks";
	}


}

package nBarSmelter;


import nBarSmelter.Bars;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class BarSmeltTask extends Task {

	private Bars barType;
	public BarSmeltTask(ClientContext ctx, Bars barType) {
		super(ctx);
		this.barType = barType;
	}
	@Override
	public boolean condition() {
		return ctx.players.getLocal().getAnimation() == -1 && (haveItemsToSmelt(barType)) ;
	}


	@Override
	public void run() {
		SimpleItem ores = ctx.inventory.populate().filter(barType.itemsRequired[0][0]).next();
		if(ores != null){
			ctx.updateStatus("Found Ores..");
			SimpleObject furnace = ctx.objects.populate().filter("furnace").nearest().next();
			if(furnace != null){
				ctx.updateStatus("Found Furnace..");
				ores.click("use");
				ctx.sleep(500);
				furnace.click("use");
				ctx.onCondition(() -> ctx.dialogue.dialogueOpen(),250,20);
				ctx.updateStatus("Waiting for dialogue box...");
				if(ctx.dialogue.dialogueOpen()){
					ctx.updateStatus("Found Dialogue box...");
					ctx.sleep(500);
					ctx.keyboard.clickKey(barType.dialogueOption);
					ctx.onCondition(() -> ctx.inventory.populate().filter(barType.productName).population() == barType.barsPerInv,500,60);
				}
			} else {
				ctx.updateStatus("Running to furnace..");
				ctx.pathing.step(3216,3112);
				ctx.sleep(500);
			}
		}
	}

	private boolean haveItemsToSmelt(Bars bar) {
		for (int[] items : bar.itemsRequired) {
			if (ctx.inventory.populate().filter(items[0]).population() < items[1]) {
				return false;
			}
		}
		return true;
	}
	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Making Bars..";
	}

}

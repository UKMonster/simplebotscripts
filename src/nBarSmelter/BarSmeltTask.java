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
			SimpleObject furnace = ctx.objects.populate().filter("furnace").nextNearest();
			if(furnace != null){
				ores.click("use");
				ctx.sleep(500);
				furnace.click("use");
				ctx.onCondition(() -> ctx.dialogue.dialogueOpen(),250,20);
				if(ctx.dialogue.dialogueOpen()){
					ctx.dialogue.clickDialogueOption(barType.dialogueOption);
					ctx.onCondition(() -> ctx.inventory.populate().filter(barType.productName).population() == barType.barsPerInv,500,60);
				}
			} else {
				ctx.pathing.step(3216,3112);
				ctx.sleep(500);
			}
		}
	}

	private boolean haveItemsToSmelt(Bars bar) {
		for (int[] items : bar.itemsRequired) {
			if (ctx.inventory.populate().filter(items[0]).population() < items[1] * bar.barsPerInv) {
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

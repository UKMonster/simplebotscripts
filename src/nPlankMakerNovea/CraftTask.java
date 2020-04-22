package nPlankMakerNovea;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleWidget;
import simple.robot.api.ClientContext;


public class CraftTask extends Task {

	public CraftTask(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {

		return ctx.inventory.populate().filter("mahogany logs").population() > 0;
	}
	@Override
	public void run() {
		if(!ctx.pathing.running() && ctx.pathing.energyLevel() > 50 ){
			ctx.pathing.running(true);
		}
		SimpleNpc sawmillop = ctx.npcs.populate().filter("sawmill operator").nearest().next();
		if(!ctx.inventory.populate().filter("mahogany logs").isEmpty() && sawmillop != null) {
			ctx.onCondition(()-> (sawmillop.visibleOnScreen()),500,10);
			sawmillop.validateInteractable();
			sawmillop.click("buy-plank");
			ctx.sleep(2000);
			SimpleWidget mahogony = ctx.widgets.getWidget(403, 96);
			ctx.onCondition(()-> (mahogony.visibleOnScreen()),500,10);
			if (mahogony != null) {
				mahogony.click("Buy all");
				ctx.onCondition(()-> (ctx.inventory.populate().filter("mahogany logs").isEmpty()),500,10);
				ctx.sleep(1000);
			}
		} else {
			ctx.pathing.step(new WorldPoint(3101,3504,0));
			ctx.sleep(750);
			ctx.pathing.step(new WorldPoint(3108,3511,0));
			ctx.sleep(2000);
		}

	}



	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Making Planks";
	}

}

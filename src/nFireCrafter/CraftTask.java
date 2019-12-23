package nFireCrafter;

import net.runelite.api.coords.WorldPoint;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class CraftTask extends Task {

	public CraftTask(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {

		return !ctx.inventory.populate().filter("Pure essence").isEmpty();
	}

	public void walkfromBank() {
		ctx.pathing.step(3381, 3269);
		ctx.sleep(2000);
		ctx.pathing.step(3382, 3268);
		ctx.sleep(2000);
		ctx.pathing.step(3383, 3266);
		ctx.sleep(2000);
		ctx.pathing.step(3374, 3264);
		ctx.sleep(2000);
		ctx.pathing.step(3366, 3263);
		ctx.sleep(2000);
		ctx.pathing.step(3359, 3263);
		ctx.sleep(2000);
		ctx.pathing.step(3348, 3264);
		ctx.sleep(2000);
		ctx.pathing.step(3338, 3265);
		ctx.sleep(2000);
		ctx.pathing.step(3326, 3263);
		ctx.sleep(2000);
		ctx.pathing.step(3325, 3258);
		ctx.sleep(2000);
		ctx.pathing.step(3324, 3249);
		ctx.sleep(2000);
		ctx.pathing.step(3323, 3242);
		ctx.sleep(2000);
		ctx.pathing.step(3317, 3233);
		ctx.sleep(2000);
		ctx.pathing.step(3306, 3234);
		ctx.sleep(2000);
		ctx.pathing.step(3307, 3239);
		ctx.sleep(2000);
		ctx.pathing.step(3309, 3246);
		ctx.sleep(2000);
		ctx.pathing.step(3312, 3250);
		ctx.sleep(2000);
	}

	private final WorldArea portalArea = new WorldArea(new WorldPoint(2570, 4853, 0), new WorldPoint(2596, 4823, 0));
	private final WorldArea ruinsArea = new WorldArea(new WorldPoint(3309, 3250, 0), new WorldPoint(3318, 3260, 0));
	private final WorldArea bankArea = new WorldArea(new WorldPoint(3380, 3266, 0), new WorldPoint(3385, 3273, 0));

	@Override
	public void run() {
		if (ctx.pathing.inArea(portalArea)) {
			SimpleObject altar = ctx.objects.populate().filter("Altar").nearest().next();
			if(!ctx.inventory.populate().filter("Pure Essence").isEmpty() && ctx.players.getLocal().getAnimation() == -1 && altar != null) {
				altar.validateInteractable();
				ctx.updateStatus("Using Altar");
				altar.click("Craft-rune");
				ctx.sleep(5000);
			}
		}
		if(ctx.pathing.inArea(ruinsArea)){
			SimpleObject ruins = ctx.objects.populate().filter(14993).nearest().next();
			SimpleItem talisman = ctx.inventory.populate().filter("fire talisman").next();
			ctx.updateStatus("Entering Ruins...");
			if(talisman != null && ruins != null){
				ruins.validateInteractable();
				ctx.sleep(300);
				talisman.click("use");
				ctx.sleep(300);
				ruins.click(0);
				ctx.sleep(300);
			} else if(talisman == null && ruins != null && ruins.validateInteractable()){
				ruins.click("Enter");
			}
			ctx.sleep(3500);
		}
		if(ctx.pathing.inArea(bankArea)){
			walkfromBank();
		}
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Crafting Essence";
	}

}

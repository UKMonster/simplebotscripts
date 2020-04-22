package nFireCrafter;


import net.runelite.api.coords.WorldPoint;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class BankTask extends Task {

	public BankTask(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {
		// TODO Auto-generated method stub
		return ctx.inventory.populate().filter("Pure essence").isEmpty();
	}
	String statusScript = "Banking";

	public WorldPoint[] getPath() {
		return new WorldPoint[]{
				new WorldPoint(3312, 3250, 0),
				new WorldPoint(3310, 3246, 0),
				new WorldPoint(3307, 3239, 0),
				new WorldPoint(3306, 3234, 0),
				new WorldPoint(3313, 3233, 0),
				new WorldPoint(3317, 3233, 0),
				new WorldPoint(3323, 3242, 0),
				new WorldPoint(3324, 3249, 0),
				new WorldPoint(3325, 3258, 0),
				new WorldPoint(3326, 3263, 0),
				new WorldPoint(3331, 3265, 0),
				new WorldPoint(3338, 3265, 0),
				new WorldPoint(3348, 3264, 0),
				new WorldPoint(3359, 3263, 0),
				new WorldPoint(3366, 3263, 0),
				new WorldPoint(3374, 3264, 0),
				new WorldPoint(3383, 3266, 0),
				new WorldPoint(3382, 3268, 0),
				new WorldPoint(3381, 3269, 0)};
	}


	/*	public void walkbank(){ 

		ctx.pathing.step(3312, 3250);
		ctx.pathing.step(3309, 3246);
		ctx.pathing.step(3307, 3239);
		ctx.pathing.step(3306, 3234);
		ctx.pathing.step(3317, 3233);
		ctx.pathing.step(3323, 3242);
		ctx.pathing.step(3324, 3249);
		ctx.pathing.step(3325, 3258);
		ctx.pathing.step(3326, 3263);
		ctx.pathing.step(3338, 3265);
		ctx.pathing.step(3348, 3264);
		ctx.pathing.step(3359, 3263);
		ctx.pathing.step(3366, 3263);
		ctx.pathing.step(3374, 3264);
		ctx.pathing.step(3383, 3266);
		ctx.pathing.step(3382, 3268);
		ctx.pathing.step(3381, 3269);
		ctx.sleep(2000);
	};*/

	private final WorldArea portalArea = new WorldArea(new WorldPoint(2570, 4853, 0), new WorldPoint(2596, 4823, 0));
	private final WorldArea bankArea = new WorldArea(new WorldPoint(3380, 3266, 0), new WorldPoint(3385, 3273, 0));



	@Override
	public void run() {
		if(!ctx.pathing.running() && ctx.pathing.energyLevel() > 50 ){
			ctx.pathing.running(true);
		}
		if (ctx.pathing.inArea(portalArea)) {
			SimpleObject portal = ctx.objects.populate().filter("Portal").nearest().next();
			if(!ctx.inventory.populate().filter("Fire rune").isEmpty() && ctx.players.getLocal().getAnimation() == -1 && portal != null) {
				portal.validateInteractable();
				statusScript =("Teleporting out..");
				portal.click("Use");
				ctx.sleep(5000);
			}
		}

		if(ctx.pathing.inArea(bankArea)){
			SimpleObject bank = ctx.objects.populate().filter("open chest").nearest().next();
			if(bank != null && bank.validateInteractable()){
				bank.click("Bank");
				ctx.onCondition(()-> ctx.bank.bankOpen(),300,20);
				if(ctx.bank.bankOpen()){
					ctx.bank.withdraw("Pure Essence", 28);
					ctx.onCondition(()-> !ctx.inventory.populate().filter("Pure Essence").isEmpty());
				}
			}


		} else {
			statusScript =("Running to bank");
			//walkbank();
			ctx.pathing.walkPath(getPath());
		}
	}


	@Override
	public String status() {
		// TODO Auto-generated method stub
		return statusScript;
	}

}

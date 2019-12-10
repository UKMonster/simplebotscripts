package bdk;


import net.runelite.api.coords.WorldPoint;
import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;
import simple.robot.utils.WorldArea;

public class WalkDragon extends Task {

	public WalkDragon(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}
	WorldArea homearea = new WorldArea(new WorldPoint(3079,3486, 0), new WorldPoint(3096,3501, 0));

	@Override
	public boolean condition() {
		SimpleNpc bdrag = ctx.npcs.populate().filter("Blue Dragon").nextNearest();
		return !bdrag.validateInteractable();
	}

	@Override
	public void run() {
		if (homearea.containsPoint(ctx.players.getLocal().getLocation())) {
			ctx.pathing.step(3092,3497);
			ctx.sleepCondition(() -> ctx.players.getLocal().getAnimation() == -1,6000);
			SimpleObject portal = ctx.objects.populate().filter(34657).nextNearest();
			if(portal != null){
				if(portal.validateInteractable()){
					portal.click("Teleport-previous");
				}
			} else {
				ctx.pathing.step(3096,3501);
				ctx.sleepCondition(() -> ctx.players.getLocal().getAnimation() == -1,6000);
			}
		} else {
			ctx.updateStatus("teleported - going through pipe");
			SimpleObject pipe = ctx.objects.populate().filter("Obstacle pipe").next();
			if(pipe != null){
				if(pipe.validateInteractable()){
					pipe.click("Squeeze-through");
					ctx.sleepCondition(() -> ctx.players.getLocal().getLocation().getX() == 2892,8000);
					ctx.updateStatus("Through pipe - running safe spot");
					//ctx.pathing.walkPath(new Point[]{new Point(2903,9804), new Point(2902,9809)});
				}
			}
		}
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Walking to Dragons";
	}

}

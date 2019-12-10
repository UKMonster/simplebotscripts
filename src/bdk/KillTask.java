package bdk;


import java.util.function.Predicate;

import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.api.ClientContext;

public class KillTask extends Task {

	public KillTask(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean condition() {
		SimpleNpc bdrag = ctx.npcs.populate().filter("Blue Dragon").next();
		if(bdrag != null){
		return bdrag.validateInteractable();
		}
		return false;
	}

	@Override
	public void run() {
		if(!ctx.players.getLocal().inCombat()){
			//SimpleNpc bdrag = ctx.npcs.populate().filter("Blue Dragon").filter((npc) -> !npc.inCombat() && !npc.isDead()).nextNearest();
			 SimpleNpc fightingMe = ctx.npcs.populate().filter(new Predicate<SimpleNpc>() {
	                @Override
	                public boolean test(SimpleNpc n) {
	                    if (n.isDead()) {
	                        return false;
	                    }
	                    return n.inCombat() && n.getInteracting() != null && n.getInteracting().equals(ctx.players.getLocal().getPlayer());
	                }
	            }).nextNearest();
	            SimpleNpc bdrag = fightingMe != null ? fightingMe : ctx.npcs.populate().filter("Blue Dragon").filter(new Predicate<SimpleNpc>() {
	                @Override
	                public boolean test(SimpleNpc n) {
	                    if (n.isDead()) {
	                        return false;
	                    }
	                    if (n.inCombat() && n.getInteracting() != null && !n.getInteracting().equals(ctx.players.getLocal().getPlayer())) {
	                        return false;
	                    }
	                    return true;
	                }
	            }).nextNearest();
			if(bdrag != null){
				if(bdrag.validateInteractable()){
					bdrag.click("Attack");
					ctx.sleepCondition(() -> ctx.players.getLocal().inCombat(),4000);
					ctx.pathing.step(2900,9809);
					ctx.sleepCondition(() -> ctx.players.getLocal().getLocation().getX() == 2900 && ctx.players.getLocal().getLocation().getY() == 9809,4500);
					bdrag.validateInteractable();
					bdrag.click("Attack");
					ctx.sleepCondition(() -> !ctx.players.getLocal().inCombat(),60000);
				}
			}
		}
	}


	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Killing Dragons";
	}

}

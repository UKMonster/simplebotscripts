package bdk;

import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class EatTask extends Task{

	public EatTask(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {
		// TODO Auto-generated method stub
		return ctx.combat.health() < 20;
	}

	@Override
	public void run() {
		if(ctx.combat.health() < 35){
			SimpleItem food = ctx.inventory.populate().filter("Manta ray").next();
			if(food != null){
				food.click("eat");
				ctx.sleep(600);
			} else {
				ctx.updateStatus("out of food - teleporting out");
				SimpleItem teletab = ctx.inventory.populate().filter("Zenyte home teleport").next();
				if(teletab != null){
					teletab.click("break");
					ctx.sleep(2000);
				}
			}
		}

	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Eating food";
	}

}

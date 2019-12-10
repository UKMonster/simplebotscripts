package nMonkFisher;

import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.api.ClientContext;

public class FishTask extends Task {

	public FishTask(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {
		
		return ctx.inventory.populate().population() < 28;
	}

	@Override
	public void run() {
		if(ctx.players.getLocal().getAnimation() == -1){
			SimpleNpc fishspot = ctx.npcs.populate().filter("Fishing spot").nextNearest();
			if(fishspot != null){
				if(fishspot.validateInteractable()){
					fishspot.click("Net");
					ctx.sleepCondition(() -> ctx.players.getLocal().getAnimation() != -1,16000);
				}
			}
		}
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Fishing Monks";
	}

}

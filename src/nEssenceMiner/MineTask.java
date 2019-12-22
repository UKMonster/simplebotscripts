package nEssenceMiner;

import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class MineTask extends Task {

	public MineTask(ClientContext ctx) {
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
			SimpleObject essence = ctx.objects.populate().filter("Rune Essence").nearest().next();
			if(essence != null){
				if(essence.validateInteractable()){
					essence.click("Mine");
					ctx.onCondition(() -> ctx.players.getLocal().getAnimation() != -1,100,60);
				}
			}
		}
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Mining Essence";
	}

}

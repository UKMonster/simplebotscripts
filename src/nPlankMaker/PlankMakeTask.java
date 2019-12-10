package nPlankMaker;


import simple.hooks.scripts.task.Task;
import simple.hooks.simplebot.Game.Tab;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class PlankMakeTask extends Task {

	private String plankName;
	public PlankMakeTask(ClientContext ctx, String plankName) {
		super(ctx);
		this.plankName = plankName;
	}
	@Override
	public boolean condition() {
		return ctx.players.getLocal().getAnimation() == -1 && !ctx.inventory.populate().filter(plankName).isEmpty();
	}


	@Override
	public void run() {
			SimpleItem logs = ctx.inventory.next();
			if(logs != null){
				if(ctx.game.tab(Tab.MAGIC)) {
					ctx.magic.castSpellOnItem("Plank Make", logs.getId());
					ctx.onCondition(() -> ctx.players.getLocal().getAnimation() != -1,300,12);
				}
			}
		}
	


	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Making Planks..";
	}

}

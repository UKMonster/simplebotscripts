package bdk;

import simple.hooks.scripts.task.Task;
import simple.robot.api.ClientContext;

public class LootTask extends Task {

	public LootTask(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void run() {
		 String[] toLoot = {"Blue dragonhide, Dragon bones"};
		ctx.groundItems.populate().filter(toLoot[01]);
	}

	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Looting";
	}

}

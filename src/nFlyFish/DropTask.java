package nFlyFish;

import java.awt.event.KeyEvent;

import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.api.ClientContext;

public class DropTask extends Task {

	public DropTask(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {
		// TODO Auto-generated method stub
		return ctx.inventory.inventoryFull();
	}

	@Override
	public void run() {
		if(ctx.inventory.populate().population() > 3){
			for(int i=-28; i<ctx.inventory.populate().population(); i++){
				SimpleItem todrop = ctx.inventory.populate().filter("Raw Salmon","Raw Trout","Raw Rainbowfish").next();
				ctx.keyboard.pressKey(KeyEvent.VK_SHIFT);
				todrop.click(0);
				ctx.sleep(100);
			}
			ctx.keyboard.releaseKey(KeyEvent.VK_SHIFT);
		}
	}


	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Dropping Fish";
	}
}


package bdk;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.scripts.task.Task;
import simple.hooks.scripts.task.TaskScript;
import simple.hooks.simplebot.ChatMessage;

@ScriptManifest(author = "Nate", category = Category.COMBAT, description = "Messes up Blue Drags",
discord = "Nathan#6809", name = "nBlueDrags", servers = {"Zenyte" }, version = "0.1")

public class BDK extends TaskScript {

	private List<Task> tasks = new ArrayList<Task>();
	
	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean prioritizeTasks() {
		return true;
	}

	@Override
	public List<Task> tasks() {
		return tasks;
	}

	@Override
	public void onChatMessage(ChatMessage arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExecute() {
		tasks.addAll(Arrays.asList(new BankTask(ctx), new WalkDragon(ctx), new LootTask(ctx), new EatTask(ctx), new KillTask(ctx)));// Adds our tasks to our {task} list for execution
        ctx.updateStatus("Started Blue Dragon Killer!");
		
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		
	}

}

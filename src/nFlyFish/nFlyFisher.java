package nFlyFish;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.scripts.task.Task;
import simple.hooks.scripts.task.TaskScript;
import simple.hooks.simplebot.ChatMessage;

@ScriptManifest(author = "Nate", category = Category.FISHING, description = "Fly Fishs and drops, have feathers in inv with rod",
discord = "Nathan#6809", name = "nFlyFish", servers = {"Zenyte" }, version = "0.1")

public class nFlyFisher extends TaskScript{

	 private List<Task> tasks = new ArrayList<Task>();
	 
	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		
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
    public void onProcess() {
        super.onProcess();
    }
	
	@Override
	public void onExecute() {
		tasks.addAll(Arrays.asList(new FishTask(ctx), new DropTask(ctx)));// Adds our tasks to our {task} list for execution
        ctx.updateStatus("Started NFlyFisher!");
		
	}
	@Override
    public boolean prioritizeTasks() {
        return true;
    }
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		
	}

}

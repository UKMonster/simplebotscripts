package nPlankMakerNovea;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.scripts.task.Task;
import simple.hooks.scripts.task.TaskScript;
import simple.hooks.simplebot.ChatMessage;

@ScriptManifest(author = "Nate", category = Category.OTHER, description = "plank maker",
discord = "Nathan#6809", name = "nPlankMaker", servers = {"Novea"}, version = "0.1")

public class NPlankMaker extends TaskScript{

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
		tasks.addAll(Arrays.asList(new BankTask(ctx), new CraftTask(ctx)));// Adds our tasks to our {task} list for execution
        ctx.updateStatus("Started NAstrals!");
		
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

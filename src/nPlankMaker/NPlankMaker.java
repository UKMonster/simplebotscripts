package nPlankMaker;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.scripts.task.Task;
import simple.hooks.scripts.task.TaskScript;
import simple.hooks.simplebot.ChatMessage;

@ScriptManifest(author = "Nate", category = Category.MONEYMAKING, description = "Makes Mahogany Planks!",
discord = "Nathan#6809", name = "nAIOPlankMaker", servers = {"Zenyte","Atlas" }, version = "0.1")

public class NPlankMaker extends TaskScript{

	public List<Task> tasks = new ArrayList<Task>();
	public  boolean started = false;
	private static PlankGui gui;
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
		if(started){
			super.onProcess();
		}
	}

	@Override
	public void onExecute() {
		gui = new PlankGui();
		gui.setVisible(true);
		gui.setLocale(ctx.getClient().getCanvas().getLocale());
		
		ctx.updateStatus("Started NPlankMaker!");

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

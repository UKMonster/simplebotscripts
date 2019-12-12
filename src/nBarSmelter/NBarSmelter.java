package nBarSmelter;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.scripts.task.Task;
import simple.hooks.scripts.task.TaskScript;
import simple.hooks.simplebot.ChatMessage;

@ScriptManifest(author = "Nate", category = Category.SMITHING, description = "Makes Bars of your choice!",
discord = "Nathan#6809", name = "nAIOBarSmelter", servers = {"Zenyte","Atlas" }, version = "0.1")

public class NBarSmelter extends TaskScript{

	public List<Task> tasks = new ArrayList<Task>();
	public  boolean started = false;
	private SmelterGui gui;
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
		gui = new SmelterGui(this);
		gui.setVisible(true);
		gui.setLocale(ctx.getClient().getCanvas().getLocale());
		ctx.updateStatus("Started nBarSmelter!");

	}
	@Override
	public boolean prioritizeTasks() {
		return true;
	}
	@Override
	public void onTerminate() {
		if (gui != null) {
			gui.dispose();
		}
	}
}



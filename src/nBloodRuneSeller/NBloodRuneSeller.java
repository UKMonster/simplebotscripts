package nBloodRuneSeller;

import java.awt.Graphics;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.script.Script;

@ScriptManifest(author = "Nate", category = Category.OTHER, description = "Start with blood runes in inv and near shop",
discord = "Nathan#6809", name = "NBloodSeller", servers = {"Zenyte"}, version = "0.1")

public class NBloodRuneSeller extends Script {

	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChatMessage(ChatMessage arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProcess() {
		
		if(!ctx.shop.shopOpen()){
		SimpleNpc edalf = ctx.npcs.populate().filter("edalf").next();
			if(edalf != null && edalf.validateInteractable()) {
				edalf.click("Trade");
			}
		} else {
			SimpleItem blood = ctx.inventory.populate().filter("blood rune").next();
			if(blood != null){
				blood.click(5);
				blood.click(5);
			}
		}
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub

	}

}

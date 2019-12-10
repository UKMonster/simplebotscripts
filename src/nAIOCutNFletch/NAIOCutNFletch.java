package nAIOCutNFletch;

import java.awt.Graphics;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.script.Script;

@ScriptManifest(author = "Nate", category = Category.WOODCUTTING, description = "Cuts trees and then fletches them into arrow shafts start near trees and have a knife in inv",
discord = "Nathan#6809", name = "NAIO CutnFletch", servers = { "Zenyte" }, version = "0.15")

public class NAIOCutNFletch extends Script {

	public static int startedbot = 0;
	private static FletchGUI gui;



	@Override
	public void onExecute() {
		gui = new FletchGUI();
		gui.setVisible(true);
		gui.setLocale(ctx.getClient().getCanvas().getLocale());
	}

	@Override
	public void onProcess() {
		if(startedbot == 1) {
			if(!ctx.dialogue.dialogueOpen()){
				if(!ctx.inventory.inventoryFull()) {
					SimpleObject tree = ctx.objects.populate().filter(properTreeName()).nextNearest();
					if(tree != null){
						if(tree.validateInteractable()){
							tree.click("chop down");
							ctx.sleepCondition(() -> ctx.players.getLocal().getAnimation() == -1,9000);
							ctx.sleep(2000);
						}
					} else {
						ctx.updateStatus("not near tree");
					}
				} else {
					SimpleItem knife = ctx.inventory.populate().filter("knife").next();
					SimpleItem logs = ctx.inventory.populate().filter(FletchGUI.comboTrees.getSelectedItem().toString().toLowerCase()+" logs").next();
					knife.click(1);
					ctx.sleep(750);
					logs.click(1);
					ctx.sleep(400);
					ctx.mouse.click(52,424,true);
					ctx.sleepCondition((() -> !logsLeft()), 68000);
				}
			}  else {
				ctx.sleep(500);
				ctx.keyboard.sendKeys(" ");
				ctx.sleep(300);
			}
		}
	}
	   private String properTreeName() {
	        String selected = FletchGUI.comboTrees.getSelectedItem().toString().toLowerCase();
	        if (selected.equals("maple")) {
	            return "Maple tree";
	        }
	        return selected;
	    }
	public boolean logsLeft() {
		return !ctx.inventory.populate().filter((FletchGUI.comboTrees.getSelectedItem().toString().toLowerCase()+" logs")).isEmpty();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChatMessage(ChatMessage arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub

	}

}

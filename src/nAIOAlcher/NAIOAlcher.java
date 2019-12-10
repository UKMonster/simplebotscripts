package nAIOAlcher;

import java.awt.Graphics;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.simplebot.Game.Tab;
import simple.hooks.wrappers.SimpleItem;
import simple.robot.script.Script;

@ScriptManifest(author = "Nate", category = Category.MAGIC, description = "AIO Alcher - start with fire staff equipped and nature runes in inv with the relevant item.",
discord = "Nathan#6809", name = "NAIO Alcher", servers = { "Atlas", "Zenyte" }, version = "0.12")

public class NAIOAlcher extends Script{

	private static GUI gui;
	public static int startedbot = 0;
	public static int natureruneID = 561;


	@Override
	public void onExecute() {
		gui = new GUI();
		gui.setVisible(true);
		gui.setLocale(ctx.getClient().getCanvas().getLocale());
	}

	@Override
	public void onProcess() {
		if(startedbot == 1) {
			if(gui.itemNameField.getText().equals("")) {//if blank
				return;
			}

			SimpleItem diamond = ctx.inventory.populate().filter(gui.itemNameField.getText().toLowerCase()).next();
			int diamondid = diamond.getId();
			SimpleItem nature = ctx.inventory.populate().filter("nature rune").next();
			if(ctx.game.tab(Tab.MAGIC)){
				if(nature != null) {
					if(diamond != null){
						ctx.mouse.click(741, 185, true);//ctx.interfaces.openTab(SimpleWidgets.MAGIC_TAB);
						ctx.sleep(250);
						ctx.magic.castSpellOnItem("high level alchemy", diamondid);
						ctx.sleepCondition(() -> ctx.players.getLocal().getAnimation() != -1,1750);
						ctx.sleepCondition(() -> ctx.players.getLocal().getAnimation() == -1,1750);

					} else {
						ctx.updateStatus("Out of Items to Alch");
					}
				} else {
					ctx.updateStatus("Out of Natures");
				}
			} else {
				ctx.game.tab(Tab.MAGIC);//ctx.mouse.click(741, 185, true);//ctx.interfaces.openTab(SimpleWidgets.MAGIC_TAB);
				ctx.sleep(500);
			}
		}
	}
	@Override
	public void onTerminate() {

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

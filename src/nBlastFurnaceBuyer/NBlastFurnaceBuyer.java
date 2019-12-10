package nBlastFurnaceBuyer;

import java.awt.Graphics;



import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.script.Script;

@ScriptManifest(author = "Nate", category = Category.OTHER, description = "Blast Furnace Ore Buyer start with coins in inventory, v0.2 - now uses chest",
discord = "Nathan#6809", name = "NBlastFurnace Buyer", servers = { "Zenyte" }, version = "0.3")


public class NBlastFurnaceBuyer extends Script{

	private static BlastGUI gui;
	public static int buying = 0;
	public int shopID = 1560;
	public int bankID = 6948;
	public int coinID = 995;



	@Override
	public void onExecute() {
		gui = new BlastGUI();
		gui.setVisible(true);
		gui.setLocale(ctx.getClient().getCanvas().getLocale());

	}

	@Override
	public void onProcess() {
		if(buying == 1){
			SimpleItem coins = ctx.inventory.populate().filter(coinID).next();
			SimpleNpc shop = ctx.npcs.populate().filter(shopID).nextNearest();
			if(!ctx.inventory.inventoryFull()) {
				if(shop.validateInteractable()){
					shop.click("trade");
					ctx.sleepCondition(()-> ctx.shop.shopOpen());
					ctx.sleep(400);
					SimpleItem shopore = ctx.shop.populate().filter((BlastGUI.itemName.getSelectedItem().toString().toLowerCase())).next();
					if(coins != null){
						shopore.click("buy 50");
						ctx.sleep(550);
						ctx.shop.closeShop();
						ctx.sleep(300);
					}
				}
			} else {
				SimpleObject depositBox = ctx.objects.populate().filter("bank chest").nextNearest();
				ctx.pathing.step(1948,4957);
				ctx.sleep(600);
				if(depositBox.validateInteractable()) {
					depositBox.click("use");
					ctx.sleepCondition(()-> ctx.bank.bankOpen());
					SimpleItem depositOres = ctx.inventory.populate().filter((BlastGUI.itemName.getSelectedItem().toString().toLowerCase())).next();
					depositOres.click("deposit-all");
					ctx.sleep(500);
					ctx.bank.closeBank();
				}
			}
		}
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

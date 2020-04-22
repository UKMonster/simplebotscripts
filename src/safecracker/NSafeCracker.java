package safecracker;


import java.awt.Graphics;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.simplebot.Game.Tab;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.script.Script;

@ScriptManifest(author = "Nate", category = Category.THIEVING, description = "Cracks wall safe in rogues den - make sure to have lobsters visible in bank",
discord = "Nathan#6809", name = "NSafeCracker", servers = {"Zenyte"}, version = "0.1")

public class NSafeCracker extends Script {


	public void onExecute() {
		ctx.game.tab(Tab.INVENTORY);
	}

	public void onProcess() {
		SimpleObject wallsafe = ctx.objects.populate().filter("wall safe").nearest().next();
		SimpleItem lobster = ctx.inventory.populate().filter("trout").next();
		if (wallsafe != null) {
			if (!ctx.inventory.inventoryFull()) {
				if (ctx.combat.health() > 7) {
					if (wallsafe.validateInteractable()) {
						wallsafe.click("crack");
						ctx.onCondition(() -> ctx.players.getLocal().getAnimation() != -1,250,20);
						ctx.onCondition(() -> ctx.players.getLocal().getAnimation() == -1,250,20);
						ctx.sleep(3500);
					}
					else {
						ctx.onCondition(() -> wallsafe.visibleOnScreen());
					}
				}
				else if (lobster != null) {
					lobster.click(1);
				}
				else {
					bankNow();
				}
			}
			else {
				bankNow();
			}
		}
	}

	public void onTerminate() {
	}

	public void bankNow() {
		ctx.pathing.step(3048, 4970);
		SimpleObject bank = ctx.objects.populate().filter("bank chest").nearest().next();
		ctx.onCondition(() -> bank.visibleOnScreen(),250,20);
		bank.click("use");
		ctx.onCondition(() -> ctx.bank.bankOpen(), 1500,10);
		if (ctx.bank.bankOpen()) {
			ctx.bank.depositInventory();
			ctx.sleep(1000);
			SimpleItem lobsterbank = ctx.bank.populate().filter("trout").next();
			if (lobsterbank != null) {
				lobsterbank.click("withdraw-x");
				ctx.sleep(1500);
				ctx.keyboard.sendKeys("6", true);
				ctx.sleep(1500);
				ctx.bank.closeBank();
				ctx.sleep(1000);
				ctx.pathing.step(3081, 3249);
				ctx.sleep(3500);
				ctx.game.tab(Tab.INVENTORY);
			}
			else {
				ctx.updateStatus("Out of trout");
				onTerminate();
			}
		}
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
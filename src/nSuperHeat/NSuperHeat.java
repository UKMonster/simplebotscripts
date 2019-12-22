package nSuperHeat;

import java.awt.Graphics;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.simplebot.Game.Tab;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.script.Script;

@ScriptManifest(author = "Nate", category = Category.SMITHING, description = "Start with Nature Runes in Inventory and Fire Staff Equipped in home bank - make sure gold ore is in inventory & visible in bank - make sure banker is visible",
discord = "Nathan#6809", name = "NGoldSuperHeat", servers = { "Zenyte","Atlas" }, version = "0.1")

public class NSuperHeat extends Script {

	public int botstarted = 1;
	

	@Override
	public void onExecute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProcess() {

		if(botstarted ==1){
			SimpleItem ore = ctx.inventory.populate().filter("gold ore").next();
			SimpleItem nature = ctx.inventory.populate().filter("nature rune").next();
			if(ore != null && nature != null) {
				for(SimpleItem item : ctx.inventory.populate().filter("gold ore")) {
					ctx.updateStatus("SuperHeating Items..");
					ctx.game.tab(Tab.MAGIC); //opens magic tab
					ctx.sleep(250);
					ctx.magic.castSpellOnItem("superheat item", item.getId());
					ctx.onCondition(() -> ctx.players.getLocal().getAnimation() != -1,250,10);
					ctx.onCondition(() -> ctx.players.getLocal().getAnimation() == -1,250,10);
				}
			} else {
					bank();
				}
			} else {
				botstarted = 0;
				ctx.updateStatus("out of nature runes");
				
			}
		}
	
	@Override
	public void onTerminate(){

	}

	public void bank() {
		SimpleNpc bank = ctx.npcs.populate().filter("banker").nearest().next();
		if(bank.validateInteractable()){
			bank.click("bank");
			ctx.onCondition(()-> ctx.bank.bankOpen(),250,20);
			ctx.sleep(300);
			SimpleItem barbank = ctx.inventory.populate().filter("gold bar").next();
			barbank.click("deposit-all");
			ctx.sleep(500);
			SimpleItem orebank = ctx.bank.populate().filter("gold ore").next();
			if(orebank.validateInteractable()) {
				if(orebank.click("withdraw-all")) {
					ctx.sleep(600);
					ctx.bank.closeBank();
					ctx.sleep(300);
				} else {
					botstarted = 0;
				}
			} else {
				botstarted = 0;
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

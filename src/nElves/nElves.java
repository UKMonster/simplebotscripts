package nElves;

import java.awt.Graphics;


import java.text.DecimalFormat;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.simplebot.Game.Tab;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.script.Script;
import simple.robot.util.Formats;

@ScriptManifest(author = "Nate", category = Category.THIEVING, description = "nElves will pickpocket the Elves on Lletya and banks the loot for big gp per hour The GUI will allow you to choose which food to use and if you want to High Alch the diamond which you recieve Make sure to have plenty of food visible in the bank and if alching make sure nature runes are visible too!if choosing the alch option make sure you have a fire staff equipped!",
discord = "Nathan#6809", name = "NElves", servers = { "Zenyte"}, version = "1.52")

public class nElves extends Script {


	private long timeBegan;
	public static int elvesID = 5297;
	public static int foodID = 361;
	public static int bankID = 20127;
	public static int natureID = 561;
	public static int diamondID = 1601;
	public static int botstarted = 0;
	public int hptoeat = 10;
	private static ElvesGUI gui;
	private int alchs = 0;
	private int gpmade;
	private int gpPerHour;
	private int totalGpPerHour;


	@Override
	public void onExecute() {	
		gui = new ElvesGUI();
		gui.setVisible(true);
		gui.setLocale(ctx.getClient().getCanvas().getLocale());
		ctx.mouse.click(560, 20, true);
		ctx.sleep(500);
		timeBegan = System.currentTimeMillis();
	}

	@Override
	public void onProcess() {
		if(botstarted == 1){
			hptoeat = Integer.parseInt(ElvesGUI.hpField.getText());
			String food = ElvesGUI.comboFood.getSelectedItem().toString().toLowerCase();
			//System.out.println("location: "+ location);
			/*int runenergy = ctx.game.getRunEnergy();
			if(runenergy > 50) {
				if(!ctx.getContext().runActive()) {
					ctx.getContext().toggleRun();
				}
			}*/
			if(ctx.inventory.populate().filter("coin pouch").population() > 20){
				SimpleItem pouch = ctx.inventory.populate().filter("coin pouch").next();
				ctx.game.tab(Tab.INVENTORY);
				ctx.updateStatus("Found Pouch");
				ctx.sleep(500);
				pouch.click(1);
			}
			if(food.equals("tuna")){
				foodID = 361;
			} else if(food.equals("lobster")) {
				foodID = 379;
			} else if(food.equals("monkfish")) {
				foodID = 7946;
			} else {
				foodID = 385;
			}

			if(ctx.inventory.populate().population() > 27) {
				ctx.updateStatus("Inventory full..");
				if(ElvesGUI.chckbxAlchDiamonds.isSelected()){
					SimpleItem diamonds = ctx.inventory.populate().filter("diamond").next();
					SimpleItem nature = ctx.inventory.populate().filter("nature rune").next();
					if(diamonds != null && nature != null) {
						int diamondAmount = ctx.inventory.populate().filter("diamond").population();
						if(ctx.game.tab(Tab.MAGIC)){
							for(int i = 0; i < diamondAmount; i++) {
								SimpleItem item = ctx.inventory.next();
								ctx.updateStatus("Alching Items..");
								alchs++;
								ctx.sleep(150);
								ctx.game.tab(Tab.MAGIC);
								ctx.sleep(300);
								ctx.magic.castSpellOnItem("high level alchemy", item.getId());
								ctx.onCondition(() -> ctx.players.getLocal().getAnimation() != -1,2500);
								if(ctx.onCondition(() -> ctx.players.getLocal().getAnimation() == -1,2500)) {
									ctx.game.tab(Tab.MAGIC);
								}
							}
						} else {
							ctx.game.tab(Tab.INVENTORY);
						}
					} else {
						bank();
					}

				} else {
					ctx.updateStatus("Alch not selected");
					bank();
					return;
				}
			}
			if(ctx.combat.health() < hptoeat){ //)){
				SimpleItem tuna = ctx.inventory.filter(foodID).next();
				ctx.game.tab(Tab.INVENTORY);
				ctx.sleep(600);//ctx.interfaces.openTab(SimpleWidgets.INVENTORY_TAB);
				if(tuna != null) {
					tuna.click("eat");
					ctx.sleep(500);
				}
				if(tuna == null){
					bank();
				}
			} else {
				SimpleNpc elves = ctx.npcs.populate().filter(elvesID).nearest().next();
				if(elves.validateInteractable()){
					elves.click("pickpocket");
					ctx.updateStatus("Pickpocketing..");
					ctx.onCondition(() -> ctx.players.getLocal().getAnimation() != -1,3500);
					ctx.onCondition(() -> ctx.players.getLocal().getAnimation() == -1,3500);
				}
			}
		}

	}

	private void bank(){
		ctx.updateStatus("Open Coin Pouch");
		ctx.game.tab(Tab.INVENTORY);
		ctx.sleep(600);//ctx.interfaces.openTab(SimpleWidgets.INVENTORY_TAB);
		SimpleItem pouch = ctx.inventory.populate().filter("coin pouch").next();
		if(pouch != null){
			ctx.updateStatus("Found Pouch");
			ctx.sleep(500);
			pouch.click(1);
		}
		ctx.updateStatus("Banking..");
		ctx.sleep(1000);
		ctx.pathing.step(2350,3157);
		ctx.sleep(2500);
		SimpleObject bank = ctx.objects.populate().filter(bankID).nearest().next();
		if(bank.validateInteractable()){
			ctx.updateStatus("Finding bank..");
			//ctx.objects.clickOnMinimap(bank);
			//ctx.onCondition(()-> ctx.objects.visibleOnScreen(bank));
			//ctx.sleep(1000);
			bank.click("bank");
			ctx.onCondition(()-> ctx.bank.bankOpen());
			ctx.sleep(500);
			ctx.bank.depositInventory();
			ctx.sleep(3000);
			SimpleItem banktuna = ctx.bank.populate().filter(foodID).next();
			if(banktuna.validateInteractable()){
				ctx.sleep(500);
				banktuna.click("withdraw-5");
				ctx.sleep(1250);
				ctx.updateStatus("found fish");
			} else {
				ctx.updateStatus("can't find fish");
			}
			if(ElvesGUI.chckbxAlchDiamonds.isSelected()){
				SimpleItem banknature = ctx.bank.populate().filter(natureID).next();
				ctx.sleep(300);
				banknature.click("withdraw-all-but-1");
				ctx.sleep(1000);
			} 
			ctx.bank.closeBank();
			ctx.sleep(1000);
			ctx.updateStatus("Walking back to Elf");
			ctx.pathing.step(2349,3156);
			ctx.sleep(300);
		}
	}
	@Override
	public void onTerminate() {

	}


	@Override
	public void paint(Graphics g) {
		DecimalFormat df = new DecimalFormat("#");
		gpPerHour = (int)(((alchs*1200)+gpmade) / ((System.currentTimeMillis() - this.timeBegan) / 3600000.0D));
		totalGpPerHour = gpPerHour / 1000;
		g.drawString("Gold Made "+ df.format((alchs*1200)+gpmade) + " ", 1,10);
		g.drawString("Gold Made Per Hour " + df.format(totalGpPerHour) + " k", 1, 25);

	}

	@Override
	public void onChatMessage(ChatMessage message) {
		String msg = message.message;
		if(msg.contains("you open all of the pouches")){
			int coinsadd = Formats.getDigitsFromText(msg);;
			gpmade += coinsadd;
		}
	}
}
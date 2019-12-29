package masterDrop;


import java.awt.Graphics;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.script.Script;

@ScriptManifest(author = "Nate", category = Category.THIEVING, description = "Pickpockets Master Farmers and drops seeds - no food support",
discord = "Nathan#6809", name = "NMaster Farmer", servers = { "Atlas", "Zenyte" }, version = "0.1")

public class NMasterFarmerDrop extends Script{

	public static int farmerID = 3257;
	public static int botstarted = 0;
	private static MasterGUI gui;

	@Override
	public void onExecute() {
		// TODO Auto-generated method stub
		gui = new MasterGUI();
		gui.setVisible(true);
		gui.setLocale(ctx.getClient().getCanvas().getLocale());
	}

	@Override
	public void onProcess() {
		System.out.println("botID: " +botstarted);
		if(ctx.combat.health() < 8){
			botstarted = 0;
			ctx.updateStatus("out of hp");
			System.out.println("im stuck");
		}
		if(botstarted == 1) {
			String location = MasterGUI.locationBox.getSelectedItem().toString().toLowerCase();
			//System.out.println("location: "+ location);
			if(location.equals("zeah")){
				farmerID = 3258;
			} else {
				farmerID = 3257;
			}
		//	System.out.println("size: "+ctx.inventory.inventory.length);
			//System.out.println("farmerID: " +farmerID);
			if(ctx.inventory.inventoryFull()) {
				dropSeeds();
				//return;
			} else {
				SimpleNpc farmer = ctx.npcs.populate().filter(farmerID).nearest().next();
				if(farmer.validateInteractable()){
					farmer.click("pickpocket");
					ctx.onCondition(() -> ctx.players.getLocal().getAnimation() != -1,100,30);
					ctx.onCondition(() -> ctx.players.getLocal().getAnimation() == -1,100,30);
				}
			}
		}
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub

	}

	public boolean dropSeed(String itemName) {
		final String[] seedNames = getFormattedSeedNames();
		for (String seed : seedNames) {
			seed = seed.toLowerCase();
			if (itemName.contains(seed.toLowerCase())) {
				return false;
			}
		} 
		return true;
	}

	private void dropSeeds() {	
		//System.out.println(seedNames);
		for (SimpleItem item : ctx.inventory.populate()) {
			final String name = this.ctx.definitions.getItemDefinition(item.getId()).getName().toLowerCase();
			if (dropSeed(name)) {
				System.out.println("Dropping "+name+" format name ");
				item.click("drop");
				ctx.sleep(150);
			}
		} 
	}

	public String[] getFormattedSeedNames() {
		String format = MasterGUI.seedList1.getText().toLowerCase();
		if (format.contains("\r\n")) {
			format = format.replaceAll("\r\n", "");
		}
		return format.split(";");
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

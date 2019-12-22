package nMinnows;

import java.awt.Graphics;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleNpc;
import simple.robot.script.Script;

@ScriptManifest(author = "Nate", category = Category.FISHING, description = "Fishes Minnows - start with net in inv",
discord = "Nathan#6809", name = "NMinnows", servers = {"Zenyte"}, version = "0.1")

public class nMinnows extends Script{
	
	

	
	public static int[] MINNOW_SPOTS = {7730,7731,7732,7733};

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
		if(ctx.players.getLocal().getAnimation() == -1){
			SimpleNpc spot = getClosestMinnowSpot();
			if(spot != null) {
				if(spot.validateInteractable()){
					spot.click("Small Net");
					ctx.sleep(500);
					ctx.onCondition(()-> ctx.players.getLocal().getAnimation() == -1,450,10);
				}
			}
		}
		
	}

	public SimpleNpc getClosestMinnowSpot(){
		ctx.updateStatus("Finding new spot...");
		return ctx.npcs.populate().filter(nMinnows.MINNOW_SPOTS).nearest().next();
		
	}
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		
	}

}

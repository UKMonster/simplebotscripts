package nBarSmelter;

import java.awt.Graphics;

import simple.hooks.simplebot.ChatMessage;
import simple.robot.script.Script;


public class NBarSmelter extends Script {
	
	public static int[] BarIDs = {436,438,440,444,447,449,451,453}; //copper,tin,iron,gold,mith,addy,rune

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		
	}
	  private boolean haveItemsToSmelt(Bars bar) {
	        for (int[] items : bar.itemsRequired) {
	            if (ctx.inventory.populate().filter(items[0]).population() < items[1] * bar.barsPerInv) {
	                return false;
	            }
	        }
	        return true;
	    }


}

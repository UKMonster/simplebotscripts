package nAIORogueDenCooker;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.simplebot.ChatMessage;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.script.Script;

@ScriptManifest(author = "Nate", category = Category.COOKING, description = "Start near the fire in burthorpe, Make sure the raw fish are visible in bank",
discord = "Nathan#6809", name = "NAIO Rogue Den Cooker", servers = { "Zenyte" }, version = "0.1")

public class NAIORogueDenCooker extends Script {

	public static boolean hidePaint = false;

	public int sharkscooked = 0;
	public int sharksburnt = 0;
	private long timeBegan;
	private long timeRan;
	public static boolean started = false;
	private static CookerGUI gui;
	private final Image bg = getImage("https://i.imgur.com/eu7VjYG.png");

	private Image getImage(String url)
	{
		try
		{
			return ImageIO.read(new URL(url));
		}
		catch (IOException e) {}
		return null;
	}
	@Override
	public void onExecute() {
		// TODO Auto-generated method stub
		timeBegan = System.currentTimeMillis();
		started = false;
		gui = new CookerGUI();
		gui.setVisible(true);
		gui.setLocale(ctx.getClient().getCanvas().getLocale());
	}

	@Override
	public void onProcess() {
		if(started == true){
			if (uncookedFoodLeft()) {
				SimpleObject fire = ctx.objects.populate().filter("fire").nearest().next();
				if (fire.validateInteractable()) {
					SimpleItem shark = ctx.inventory.populate().filter("raw " +CookerGUI.fishBox.getSelectedItem().toString().toLowerCase()).next();
					if (shark != null) {
						shark.click(1);
						ctx.sleep(250);
						fire.click("use");
						ctx.sleep(3000);
						ctx.mouse.click(257, 428, true);
						ctx.sleep(250);
						ctx.onCondition((() -> !uncookedFoodLeft()), 250,280);
					}
				}
			} else {
				SimpleObject bank = ctx.objects.populate().filter("bank chest").nearest().next();
				ctx.sleep(500);
				if (bank.validateInteractable()) {;
				ctx.sleep(500);
				bank.click("use");
				ctx.onCondition(() -> ctx.bank.bankOpen(),125,30);
				ctx.sleep(300);
				ctx.bank.depositInventory();
				ctx.sleep(200);
				SimpleItem sharkbank = ctx.bank.populate().filter("raw " +CookerGUI.fishBox.getSelectedItem().toString().toLowerCase()).next();
				if(sharkbank.validateInteractable()){
					ctx.sleep(500);
					sharkbank.click("withdraw-all");
					ctx.sleep(1250);
					ctx.updateStatus("found fish");
				} 
				ctx.sleep(600);
				ctx.bank.closeBank();
				ctx.sleep(600);
				}
			}
		} 
	}


	public boolean uncookedFoodLeft() {
		return !ctx.inventory.populate().filter("raw " +CookerGUI.fishBox.getSelectedItem().toString().toLowerCase()).isEmpty();
	}


	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub

	}


	@Override
	public void paint(Graphics g) {
		timeRan = System.currentTimeMillis() - this.timeBegan;
		Graphics gr = g;
		if (!hidePaint) {
			g.drawImage(bg, -2, 315, null);
			g.drawString(ft(timeRan), 115, 396);
			g.drawString(Integer.toString(sharkscooked), 115, 419);
			g.drawString(Integer.toString(sharksburnt), 115, 440);
		} 
	}

	@Override
	public void onChatMessage(ChatMessage message) {
		String msg = message.message;
		if(msg.contains("successfully cook the")){
			sharkscooked += 1;
		} else if(msg.contains("burnt")){
			sharksburnt += 1; 
		}
	}

	private String ft(long duration) {
		String res = "";
		long days = TimeUnit.MILLISECONDS.toDays(duration);
		long hours = TimeUnit.MILLISECONDS.toHours(duration)
				- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
				- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
						.toHours(duration));
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
				- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
						.toMinutes(duration));
		if (days == 0) {
			res = (hours + ":" + minutes + ":" + seconds);
		} else {
			res = (days + ":" + hours + ":" + minutes + ":" + seconds);
		}
		return res;

	}

}



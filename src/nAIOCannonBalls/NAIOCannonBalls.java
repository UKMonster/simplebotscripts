package nAIOCannonBalls;

import java.awt.Color;
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
import simple.hooks.wrappers.SimpleNpc;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.script.Script;
import net.runelite.api.Skill;

@ScriptManifest(author = "Nate", category = Category.MONEYMAKING, description = "Start with c'ball mould in inv with steel bars, have steel bars visible in bank",
discord = "Nathan#6809", name = "NCannonBalls", servers = { "Zenyte" }, version = "0.1")

public class NAIOCannonBalls extends Script {

	public static boolean started = false;
	public static boolean hidePaint = false;
	private static CannonGUI gui;
	private long timeBegan;
	private long timeRan;
	public int steelbarsgrabbed = 0;
	public String statusatm = "Setting up";
	private int beginningXP;
	private int currentXp;
	private int xpGained;

	private final Image bg = getImage("https://i.imgur.com/OahFwWh.png");

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
	public void onChatMessage(ChatMessage message) {
		String msg = message.message;
		if(msg.contains("the molten metal cools slowly")){
			steelbarsgrabbed += 1;
		}
	}



	@Override
	public void onExecute() {
		timeBegan = System.currentTimeMillis();
		beginningXP = ctx.getClient().getSkillExperience(Skill.SMITHING);
		// TODO Auto-generated method stub
		gui = new CannonGUI();
		gui.setVisible(true);
		gui.setLocale(ctx.getClient().getCanvas().getLocale());
	}

	@Override
	public void onProcess() {

		if(started == true){
			if(CannonGUI.comboBox.getSelectedItem().toString().toLowerCase().equals("home")){
				SimpleItem ammomould = ctx.inventory.populate().filter("ammo mould").next();
				if(ammomould != null){
					SimpleItem steelbars = ctx.inventory.populate().filter("steel bar").next();
					if(steelbars != null){
						statusatm = "Walking to Furnace";
						ctx.pathing.step(3099,3498);
						ctx.sleep(2400);
						ctx.pathing.step(3109,3498);
						ctx.sleep(2400);
						SimpleObject furnace = ctx.objects.populate().filter("furnace").nearest().next();
						if(furnace != null){
							if(furnace.validateInteractable()){
								statusatm = "using bars on furnace";
								steelbars.click("use");
								ctx.sleep(1200);
								furnace.click("use");
								ctx.onCondition(() -> ctx.dialogue.dialogueOpen(),300,30);
								ctx.sleep(1000);
								ctx.mouse.click(257, 428, true);
								statusatm = "Smelting Bars";
								ctx.onCondition(() -> ctx.inventory.populate().filter("steel bar").isEmpty(), 300,330);
							}
						} else {
							ctx.pathing.step(3109,3498);
							ctx.sleep(600);
						}
					} else {
						statusatm = "Walking to bank";
						ctx.pathing.step(3099,3498);
						ctx.sleep(300);
						SimpleNpc bank = ctx.npcs.populate().filter("banker").nearest().next();
						if(bank.validateInteractable()){
							ctx.sleep(800);
							bank.click("bank");
							ctx.onCondition(() -> ctx.bank.bankOpen(),300,25);
							if(ctx.bank.bankOpen()){
								SimpleItem banksteel = ctx.bank.populate().filter("steel bar").next();
								if(banksteel.validateInteractable()){
									statusatm = "Finding steel";
									ctx.onCondition(() -> banksteel.visibleOnScreen(),250,20);
									banksteel.click("withdraw-all");
									ctx.sleep(150);
									ctx.bank.closeBank();
									ctx.sleep(300);
								} else {
									started = false;
								}
							}
						}

					}
				}
			} else if(CannonGUI.comboBox.getSelectedItem().toString().toLowerCase().equals("shilo")){
				ctx.updateStatus("bot shilo");
				SimpleItem ammomould = ctx.inventory.populate().filter("ammo mould").next();
				if(ammomould != null){
					SimpleItem steelbars = ctx.inventory.populate().filter("steel bar").next();
					if(steelbars != null){
						ctx.sleep(300);
						SimpleObject furnace = ctx.objects.populate().filter(29662).nearest().next();
						statusatm = "Walking to Furnace";
						ctx.pathing.step(2856,2962);
						ctx.sleep(750);
						if(furnace != null && furnace.validateInteractable()){
							statusatm = "Using bars on Furnace";
							ctx.sleep(900);
							steelbars.click("use");
							ctx.sleep(900);
							furnace.click(0);
							ctx.onCondition(() -> ctx.dialogue.dialogueOpen(),250,20);
							ctx.mouse.click(257, 428, true);
							ctx.sleep(1200);
							statusatm = "Smelting Bars..";
							ctx.onCondition(() -> ctx.inventory.populate().filter("steel bar").isEmpty(), 300,330);
						}
					} else {
						SimpleNpc bank = ctx.npcs.populate().filter("banker").nearest().next();
						statusatm = "Finding Bank";
						ctx.sleep(600);
						ctx.pathing.step(2856,2962);
						ctx.sleep(400);
						if(bank.validateInteractable()){
							ctx.sleep(300);
							bank.click("bank");
							statusatm = "Opening Bank";
							ctx.onCondition(() -> ctx.bank.bankOpen(),300,20);
							if(ctx.bank.bankOpen()){
								SimpleItem banksteel = ctx.bank.populate().filter("steel bar").next();
								if(banksteel.validateInteractable()){
									statusatm = "Finding Steel Bars";
									ctx.onCondition(() -> banksteel.visibleOnScreen(),300,20);
									banksteel.click("withdraw-all");
									ctx.sleep(150);
									ctx.bank.closeBank();
									ctx.sleep(300);
								} else {
									started = false;
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub

	}

	public void paint(Graphics g) {
		timeRan = System.currentTimeMillis() - this.timeBegan;
		currentXp = ctx.getClient().getSkillExperience(Skill.SMITHING);
		xpGained = currentXp - beginningXP;
		if (!hidePaint) {
			Color orange = new Color(254, 127, 0);
			g.setColor(orange);
			g.drawImage(bg, -2, 315, null);
			g.drawString(statusatm, 112, 384);//status
			g.drawString(ft(timeRan), 418, 384);//time ran
			g.drawString(Integer.toString(steelbarsgrabbed*8),123,419); //cballs
			g.drawString(Integer.toString(xpGained), 418, 419);
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

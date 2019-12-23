package nFireCrafter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import simple.hooks.filters.SimpleSkills.Skills;
import simple.hooks.scripts.Category;
import simple.hooks.scripts.ScriptManifest;
import simple.hooks.scripts.task.Task;
import simple.hooks.scripts.task.TaskScript;
import simple.hooks.simplebot.ChatMessage;

@ScriptManifest(author = "Nate", category = Category.RUNECRAFTING, description = "Crafts mfking Fire runes!",
discord = "Nathan#6809", name = "nFireCrafter", servers = {"Zenyte" }, version = "0.1")

public class NFireCrafter extends TaskScript{
	
	 private long timeBegan;
	    private long timeRan;
	    private int beginningXp;
	    private int currentXp;
	    private int xpGained;
	    private int currentLevel;
	    private int beginningLevel;
	    private int levelsGained;
	    private double nextLevelXp;
	    private double xpTillNextLevel;
	    private long timeTNL;

	    private int xpPerHour;
	    final int[] XP_TABLE =
	            {
	                    0, 0, 83, 174, 276, 388, 512, 650, 801, 969, 1154,
	                    1358, 1584, 1833, 2107, 2411, 2746, 3115, 3523, 3973, 4470, 5018,
	                    5624, 6291, 7028, 7842, 8740, 9730, 10824, 12031, 13363, 14833,
	                    16456, 18247, 20224, 22406, 24815, 27473, 30408, 33648, 37224,
	                    41171, 45529, 50339, 55649, 61512, 67983, 75127, 83014, 91721,
	                    101333, 111945, 123660, 136594, 150872, 166636, 184040, 203254,
	                    224466, 247886, 273742, 302288, 333804, 368599, 407015, 449428,
	                    496254, 547953, 605032, 668051, 737627, 814445, 899257, 992895,
	                    1096278, 1210421, 1336443, 1475581, 1629200, 1798808, 1986068,
	                    2192818, 2421087, 2673114, 2951373, 3258594, 3597792, 3972294,
	                    4385776, 4842295, 5346332, 5902831, 6517253, 7195629, 7944614,
	                    8771558, 9684577, 10692629, 11805606, 13034431, 200000000
	            };

	 private List<Task> tasks = new ArrayList<Task>();
	 
	@Override
	public void paint(Graphics g) {
        Font font = new Font("Java Kick BTN",Font.BOLD,16);
        g.setFont(font);
        g.setColor(Color.RED);
        timeRan = System.currentTimeMillis() - this.timeBegan;
        g.drawString("nFireRCrafter",12,210);
        g.drawString(ft(timeRan), 12, 235);
        currentXp = ctx.skills.experience(Skills.RUNECRAFT);
        xpGained = currentXp - beginningXp;
        g.drawString("Exp Gained: " + xpGained, 12, 250);
        currentLevel = ctx.skills.level(Skills.RUNECRAFT);
        levelsGained = currentLevel - beginningLevel;
        g.drawString("Start Level: " + beginningLevel, 12,265);
        g.drawString("Current Level: " + currentLevel, 12,280);
        g.drawString("Levels Gained: " + levelsGained, 12, 295);
        g.drawString("Time Till Level: " + ft(timeTNL), 12,325);
        currentXp = ctx.skills.experience(Skills.RUNECRAFT);
        currentLevel = ctx.skills.level(Skills.RUNECRAFT);
        xpGained = currentXp - beginningXp;
        xpPerHour = (int)( xpGained / ((System.currentTimeMillis() - this.timeBegan) / 3600000.0D));
        nextLevelXp = XP_TABLE[currentLevel + 1];
        xpTillNextLevel = nextLevelXp - currentXp;

        if (xpGained >= 1)
        {
            timeTNL = (long) ((xpTillNextLevel / xpPerHour) * 3600000);
        }
		
	}
	
	@Override
	public List<Task> tasks() {
		return tasks;
	}

	@Override
	public void onChatMessage(ChatMessage arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public void onProcess() {
        super.onProcess();
    }
	
	@Override
	public void onExecute() {
		tasks.addAll(Arrays.asList(new BankTask(ctx), new CraftTask(ctx)));// Adds our tasks to our {task} list for execution
		ctx.updateStatus("Welcome to Fire RuneCrafter by: Nate");

        timeBegan = System.currentTimeMillis();
        beginningXp = ctx.skills.experience(Skills.RUNECRAFT);
        beginningLevel = ctx.skills.level(Skills.RUNECRAFT);
        timeTNL = 0;
		
	}
	@Override
    public boolean prioritizeTasks() {
        return true;
    }
	
	private String ft(long duration)
    {
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
	
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		
	}

}

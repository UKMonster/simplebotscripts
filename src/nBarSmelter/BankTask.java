package nBarSmelter;

import javax.swing.JComboBox;

import simple.hooks.scripts.task.Task;
import simple.hooks.wrappers.SimpleItem;
import simple.hooks.wrappers.SimpleObject;
import simple.robot.api.ClientContext;

public class BankTask extends Task {

	private Bars barType;
	private JComboBox<String> locationBox;
	public BankTask(ClientContext ctx, Bars barType, JComboBox<String> locationBox) {
		super(ctx);
		this.barType = barType;
		this.locationBox = locationBox;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean condition() {
		// TODO Auto-generated method stub
		return ctx.inventory.populate().filter(barType.productName).population() >= barType.barsPerInv;
	}

	@Override
	public void run() {
		if(!ctx.bank.bankOpen()){
			SimpleObject depositbox = ctx.objects.populate().filter("Grand Exchange Booth","bank booth").nextNearest();
			if(depositbox != null && depositbox.validateInteractable()){
				if (depositbox.click("Bank") && ctx.onCondition(() -> ctx.bank.bankOpen(),300,10)) {
					bankItems();
				}
			} else { //if it cant find a deposit box walk to one
				if(locationBox.getSelectedItem().toString() == "Home (Zenyte)"){
					ctx.pathing.step(3032, 3440); //walk towards home bank
					ctx.sleep(500);
				} else {
					ctx.pathing.step(3240,3136); //walk towards alkarid bank
					ctx.sleep(500);
				}
			}
		} else {
			bankItems();
		}
	}



	private void bankItems() {
		if (!ctx.inventory.populate().filter(barType.productName).isEmpty()) { // if we have bars
			ctx.bank.depositInventory(); // bank our inv
		}
		final int itemtowithdraw1 = barType.itemsRequired[0][0]; // the item id of the first ore
		if (ctx.inventory.populate().filter(itemtowithdraw1).isEmpty()) { // if we dont have any of our first item
			SimpleItem itemA = ctx.bank.populate().filter(itemtowithdraw1).next(); // grabs the first item from our bank
			if (itemA != null) { // if it isn't null withdraw the specified amount
				ctx.bank.withdraw(itemtowithdraw1, barType.itemsRequired[0][1]); // withdraws our first item x amount
			} else { // if we dont have any of our first item
				ctx.stopScript(); // stop the script
			}
		}
		if (barType.itemsRequired.length > 1) { // if our specified bar has more than 1 ore required to make it
			final int itemtowithdraw2 = barType.itemsRequired[1][0]; // the item id of the second ore
			if (ctx.inventory.populate().filter(itemtowithdraw2).isEmpty()) { // if we dont have any of our second ore in our bank
				SimpleItem itemB = ctx.bank.populate().filter(itemtowithdraw2).next(); // grabs the second item from our bank
				if (itemB != null && ctx.bank.withdraw(itemtowithdraw2, barType.itemsRequired[1][1])) { // if it isnt null and we withdrew it
					ctx.bank.closeBank(); // close the bank
				}
			}
		} else { // if we only have 1 ore type to withdraw
			ctx.bank.closeBank(); // close the bank
		}
	}
	@Override
	public String status() {
		// TODO Auto-generated method stub
		return "Banking...";
	}


}

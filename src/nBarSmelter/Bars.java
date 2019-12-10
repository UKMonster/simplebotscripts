package nBarSmelter;

public enum Bars {

	
    BRONZE_BAR("Bronze bar", new int[][] { {NBarSmelter.BarIDs[0], 1}, {NBarSmelter.BarIDs[1], 1} }, 14),
    IRON_BAR("Iron bar", new int[][] {{NBarSmelter.BarIDs[2], 1}},28),
    STEEL_BAR("Steel bar", new int[][] { {NBarSmelter.BarIDs[2], 1}, {NBarSmelter.BarIDs[7], 2} },9),
    MITHRIL_BAR("Mithril bar", new int[][] { {NBarSmelter.BarIDs[4], 1}, {NBarSmelter.BarIDs[7], 4} }, 5),
    ADMANT_BAR("Adamant bar", new int[][] { {NBarSmelter.BarIDs[5], 1}, {NBarSmelter.BarIDs[7], 6} }, 4),
    RUNITE_BAR("Runite bar", new int[][] { {NBarSmelter.BarIDs[6], 1}, {NBarSmelter.BarIDs[7], 8} }, 3),
    GOLD_BAR("Gold bar", new int[][] {{NBarSmelter.BarIDs[3], 1}},28)
    ;

    public String productName;
    public int[][] itemsRequired;
    public int barsPerInv;
    
    Bars(String productName, int[][] itemsRequired, int barsPerInv) {
        this.productName = productName;
        this.itemsRequired = itemsRequired;
        this.barsPerInv = barsPerInv;
    }
}

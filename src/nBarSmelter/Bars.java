package nBarSmelter;

public enum Bars {
	
	
    BRONZE_BAR("Bronze bar", new int[][] { {436, 1}, {438, 1} }, 14,1),
    IRON_BAR("Iron bar", new int[][] {{440, 1}},28,3),
    STEEL_BAR("Steel bar", new int[][] { {440, 1}, {453, 2} },9,5),
    MITHRIL_BAR("Mithril bar", new int[][] { {447, 1}, {453, 4} }, 5,7),
    ADMANT_BAR("Adamant bar", new int[][] { {449, 1}, {453, 6} }, 4,8),
    RUNITE_BAR("Runite bar", new int[][] { {451, 1}, {453, 8} }, 3,9),
    GOLD_BAR("Gold bar", new int[][] {{444, 1}},28,6)
    ;

    public final String productName;
    public final int[][] itemsRequired;
    public final int barsPerInv;
    public final int dialogueOption;
    
    Bars(String productName, int[][] itemsRequired, int barsPerInv,int dialogueOption) {
        this.productName = productName;
        this.itemsRequired = itemsRequired;
        this.barsPerInv = barsPerInv;
        this.dialogueOption = dialogueOption;
    }
}
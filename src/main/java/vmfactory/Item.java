package vmfactory;

/**
 * Represents an Item to be stored in ItemSlots
 * <p>
 * Includes the name, caloriesAmt, price of the Item, and prepMessage.
 */
public class Item {
    private String name;
    private int caloriesAmt;
    private int price;
    private String prepMessage;

    /**
     * Creates an instance of an Item
     * @param name is the item's name
     * @param caloriesAmt is the item's calorie count
     * @param price is the item's price
     * @param prepMessage is the item's message when being prepared for a special item
     */
    public Item(String name, int caloriesAmt, int price, String prepMessage) {
        this.name = name;
        this.caloriesAmt = caloriesAmt;
        this.price = price;
        this.prepMessage = prepMessage;
    }

    public void setCaloriesAmt(int caloriesAmt) {
        this.caloriesAmt = caloriesAmt;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPrepMessage(String message) {
        this.prepMessage = message;
    }

    public String getName() {
        return this.name;
    }
    
    public int getCaloriesAmt() {
        return this.caloriesAmt;
    }

    public int getPrice() {
        return this.price;
    }

    public String getPrepMessage() {
        return this.prepMessage;
    }
}

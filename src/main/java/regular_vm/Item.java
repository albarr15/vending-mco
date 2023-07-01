/**
 * Represents an Item to be stored in ItemSlots or carts of Transactions.
 * <p>
 * Includes the name, calories, and price of the Item.
 */
package regular_vm;

public class Item {
    private String name;
    private int caloriesAmt;
    private int price;

    /**
     * Creates an instance of an Item
     * @param name is the item's name
     * @param caloriesAmt is the item's calorie count
     * @param price is the item's price
     */
    public Item(String name, int caloriesAmt, int price) {
        this.name = name;
        this.caloriesAmt = caloriesAmt;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCaloriesAmt(int caloriesAmt) {
        this.caloriesAmt = caloriesAmt;
    }

    public void setPrice(int price) {
        this.price = price;
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
}

/**
 * Represents one slot which holds Items in a vending machine.
 * <p>
 * Includes the Item set, the list of Items, the price, and the number sold.
 * Features include checking availability, dispensing, and stocking items.
 */
package vmfactory;
import java.util.ArrayList;

/**
 * Represents one itemSlot where items of the vending machine are stored in
 */
public class ItemSlot {
    private Item item = null;
    private ArrayList<Item> listItem = new ArrayList<Item>();
    private int price;
    private boolean forSale;
    private int noSold =  0;

    public ItemSlot() {
    }

    public ItemSlot(Item item, boolean forSale) {
        this.setItem(item);
        this.forSale = forSale;
    }

    /**
     * Checks if there has been any Item set to this ItemSlot or if it is empty
     *
     * @return  a boolean value of whether or not this slot is occupied
     */
    public boolean checkSlotAvailability() {
        if(this.item == null || this.listItem.size() == 0)
            return true;
        else return false;
    }


    /**
     * Removes an Item from the list of Items stored in this ItemSlot
     *
     * @return  the Item removed from this list
     */
    public Item dispenseItem() {
        if(listItem.size() > 0) {
            Item item = this.listItem.get(this.listItem.size()-1);
            this.listItem.remove(item);
            this.noSold++;
            System.out.println("Dispensing " + this.item.getName() + " ...");
            return item;
        } else return null;
    }

    /**
     * Creates Item stock for this list of Items in this ItemSlot
     *
     * @param isReturnedItem  tells if the item was returned from a buyer's cart rather than restocked
     * @return  a boolean value of whether or not the stocking was successful
     */
    public boolean stockItem(boolean isReturnedItem) {
        if(listItem.size() < 10) {
            Item item = new Item(this.item.getName(),
                    this.item.getCaloriesAmt(),
                    this.item.getPrice(), this.item.getPrepMessage());
            listItem.add(item);
            if(isReturnedItem) {
                this.noSold--;
            }
            return true;
        } else return false;
    }

    /**
     * Fully stocks an ItemSlot's list of Items
     *
     * @return  a boolean value of whether or not there is room for restocking
     */
    public boolean fullStockSlot() {
        if (listItem.size() == 10)
        {
            return false;
        }
        else {
            while (listItem.size() != 10) {
                stockItem(false);
            }
            return true;
        }
    }

    public void setItem(Item item) {
        this.item = item;
        listItem.clear();
        listItem.add(item);
        this.price = item.getPrice();
    }

    public String setPrice(int price) {
        String feedback = "";
        if (this.item instanceof SpecialItem) {
            feedback = ("Cannot set price. A Special Item's price must be set according" +
                    " to its components");
            return feedback;
        }
        else {
            try {
                if (price < 0) {
                    feedback = ("Invalid price. " +
                            "Please enter a positive integer.");
                    return feedback;
                }
            }
                catch (NumberFormatException e) {
                    feedback = ("Invalid price. Please enter an integer.");
                    return feedback;
                }
                this.price = price;
            for (int i=0; i<this.listItem.size(); i++)
                this.listItem.get(i).setPrice(price);
            }
        return "";
    }

    public Item getItem() {
        return this.item;
    }

    public String getItemName() {
        return this.item.getName();
    }

    public int getItemStock() {
        return this.listItem.size();
    }

    public int getPrice() {
        return this.price;
    }

    public boolean getForSale() {
        return this.forSale;
    }

    public int getNoSold() {
        return this.noSold;
    }
}

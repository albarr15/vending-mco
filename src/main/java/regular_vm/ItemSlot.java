/**
 * Represents one slot which holds Items in a vending machine.
 * <p>
 * Includes the Item set, the list of Items, the price, and the number sold.
 * Features include checking availability, dispensing, and stocking items.
 */
package regular_vm;
import java.util.ArrayList;

public class ItemSlot {
    private Item item = null;
    private ArrayList<Item> listItem = new ArrayList<Item>();
    private double price;
    private int noSold =  0;

    public ItemSlot() {}

    /**
     * Sets the Item that this ItemSlot will contain.
     * 
     * @param item  the Item that will be set
     */
    public void setItem(Item item) {
        this.item = item;
        listItem.clear();
        listItem.add(item);
        this.price = item.getPrice();
    }

    /** 
     * Returns the Item in this ItemSlot
     * 
     * @return  the Item in this ItemSlot
     */
    public Item getItem() {
        return this.item;
    }

    /** 
     * Returns the name of the Item in this ItemSlot
     * 
     * @return  the name of Item in this ItemSlot
     */
    public String getItemName() {
        return this.item.getName();
    }

    /**
     * Sets the price of the Items in this ItemSlot
     * 
     * @param price  the price that the Items will be set to
     */
    public void setPrice(double price) {
        this.price = price;

        for (int i=0; i<this.listItem.size(); i++)
            this.listItem.get(i).setPrice(price);
    }

    /**
     * Returns the price of the Items in this ItemSlot
     * 
     * @return  the price of the Items in this ItemSlot
     */
    public double getPrice() {
        return this.price;
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
                                 this.item.getPrice());
            listItem.add(item);
            if(isReturnedItem)
                this.noSold--;
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

    /**
     * Returns the number of Items currently stocked
     * 
     * @return  integer number of Items currently stocked in this ItemSlot
     */
    public int getItemStock() {
        return this.listItem.size();
    }

    /**
     * Returns the number of Items that have been sold since the last restock
     * 
     * @return  integer number of sold items
     */
    public int getNoSold() {
        return this.noSold;
    }
}

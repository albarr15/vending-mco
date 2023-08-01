/**
 * Represents one slot which holds Items in a vending machine.
 * <p>
 * Includes the Item, the list of Items, the price, whether the item is for sale individually or not
 * and the number of items sold.
 * Features include checking availability, dispensing, stocking items, and fully stocking a slot.
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
     * Adds Item stock for this list of Items in this ItemSlot
     *
     * @param isReturnedItem  tells if the item was returned from a buyer's cart rather than restocked
     * @return  a boolean value of whether the stocking was successful
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
     * @return  a boolean value of whether there is room for restocking
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

    public int setPrice(int price) {
        if (this.item instanceof SpecialItem) {
            // cannot manually set price of special item
            return 1;
        }
        else {
            try {
                if (price < 0) {
                    // price must be a positive integer
                    return 2;
                }
            }
            catch (NumberFormatException e) {
                // price must be an integer
                return 3;
            }
            this.price = price;
            for (int i=0; i<this.listItem.size(); i++)
                this.listItem.get(i).setPrice(price);
        }
            // no errors
            return 0;
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

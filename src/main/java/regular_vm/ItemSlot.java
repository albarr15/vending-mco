package regular_vm;
import java.util.ArrayList;

public class ItemSlot {
    private Item item = null;
    private ArrayList<Item> listItem = new ArrayList<Item>();
    private double price;
    private int noSold =  0;

    public ItemSlot() {}

    public void setItem(Item item) {
        this.item = item;
        listItem.clear();
        listItem.add(item);
        this.price = item.getPrice();
    }
    
    public String getItemName() {
        return this.item.getName();
    }

    public void setPrice(double price) {
        this.price = price;

        for (int i=0; i<this.listItem.size(); i++)
            this.listItem.get(i).setPrice(price);
    }
    
    public double getPrice() {
        return this.price;
    }

    public boolean checkSlotAvailability() {
        if(this.item == null || this.listItem.size() == 0)
            return true;
        else return false;
    }

    public Item dispenseItem() {
        if(listItem.size() > 0) {
            Item item = this.listItem.get(this.listItem.size()-1);
            this.listItem.remove(item);
            this.noSold++;
            return item;
        } else return null;
    }

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

    public int getItemStock() {
        return this.listItem.size();
    }

    public int getNoSold() {
        return this.noSold;
    }
}

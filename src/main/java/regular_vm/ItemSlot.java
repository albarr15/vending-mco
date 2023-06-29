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
        this.price = item.getPrice();
    }
    
    public String getItem() {
        return this.item.getName();
    }

    public void setPrice(double price) {
        this.price = price;

        for (int i=0; i<this.listItem.size(); i++)
            this.listItem.get(i).setPrice(price);
    }
    
    public double getPrice() {
        return this.getPrice();
    }

    public boolean checkSlotAvailability() {
        if(this.item == null)
            return true;
        else return false;
    }

    public boolean dispenseItem() {
        if(listItem.size() > 0) {
            listItem.remove(listItem.size()-1);
            this.noSold++;
            return true;
        } else return false;
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
                stockItem();
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

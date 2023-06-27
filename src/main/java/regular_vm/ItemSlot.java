package regular_vm;
import java.util.ArrayList;

public class ItemSlot {
    Item item = null;
    ArrayList<Item> listItem = new ArrayList<Item>();
    double price;

    public void setItem(Item item) {
        this.item = item;
        listItem.clear();
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
        return this.price;
    }

    public boolean checkSlotAvailability() {
        if(this.item == null)
            return true;
        else return false;
    }

    public boolean dispenseItem() {
        if(listItem.size() > 0) {
            listItem.remove(listItem.size()-1);
            return true;
        } else return false;
    }

    public boolean stockItem() {
        if(listItem.size() < 10) {
            Item item = new Item(this.item.getName(), 
                                 this.item.getCaloriesAmt(),
                                 this.item.getPrice());
            listItem.add(item);
            return true;
        } else return false;
    }

    public int getItemStock() {
        return this.listItem.size();
    }
}

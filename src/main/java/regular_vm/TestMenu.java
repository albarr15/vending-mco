package regular_vm;
import java.util.ArrayList;

public class TestMenu {

    public void setItem(ItemSlot itemSlot, String itemName,
                        double caloriesAmt, double price) {
        Item item = new Item(itemName, caloriesAmt, price);
        itemSlot.setItem(item);
    }

    public void stockItem(ItemSlot itemSlot, int noItems) {
        int i=0;
        for(i=0; i < noItems; i++)
            if(!itemSlot.stockItem(false)) {
                System.out.println("Error: Could not stock " +
                (noItems-i) + " " + itemSlot.getItem() + ".");
                break;
            } 

        if(i!=0)
            System.out.println("Successfully stocked " + noItems +
                               " " + itemSlot.getItem() + ".");
    }

    public void stockItem(ItemSlot itemSlot) {
        itemSlot.fullStockSlot();
        System.out.println("Fully stocked " + itemSlot.getItem() + ".");
    }

    public void setPrice(ItemSlot itemSlot, double price) {
        itemSlot.setPrice(price);
    }

    public double collectMoney(Balance bal, double amount) {
        if(bal.withdrawCash(amount) != null)
            return bal.getCurrentBal();
        else return null;
    }
    public double collectMoney(Balance bal) {
        bal.withdrawCash(bal.getCurrentBal());
        return bal.getCurrentBal();
    }

    public double replenishMoney(Balance bal, String cashList) {
        bal.depositCash(cashList);
        return bal.getCurrentBal();
    }

    public void printTransacSummary() {
        double totalEarnings = 0;
        System.out.println("Number of items sold: ");
        for(int i=0; i<slots.size(); i++) {
            ItemSlot itemSlot = slots.get(i);
            System.out.println(itemSlot.getItem() + ": " +
                               itemSlot.getNoSold() + " (" +
                               (itemSlot.getNoSold()+itemSlot.getItemStock())
                               + " -> " + itemSlot.getItemStock() + ")");
            // <item name>: <num sold> (<previous stock> -> <current stock>)
            totalEarnings += itemSlot.getPrice() * itemSlot.getNoSold();
        }
        System.out.println("Total earnings since last summary: " + totalEarnings);
    }
    
}

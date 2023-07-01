package regular_vm;
import java.util.ArrayList;

public class Maintenance {
    private RegularVM currentVM;

    public Maintenance(RegularVM currentVM) {
        this.currentVM = currentVM;
    }

    public void setItem(ItemSlot itemSlot, String itemName,
                        double caloriesAmt, double price) {
        Item item = new Item(itemName, caloriesAmt, price);
        itemSlot.setItem(item);
    }

    public void stockItem(ItemSlot itemSlot, int numItems) {
        int i=0;
        for(i=0; i < numItems; i++)
            if(!itemSlot.stockItem(false)) {
                System.out.println("Error: Could not stock " +
                (numItems-i) + " " + itemSlot.getItemName() + ".");
                break;
            } 

        if(i!=0)
            System.out.println("Successfully stocked " + numItems +
                               " " + itemSlot.getItemName() + ".");
    }

    public void stockItem(ItemSlot itemSlot) {
        itemSlot.fullStockSlot();
        System.out.println("Fully stocked " + itemSlot.getItemName() + ".");
    }

    public void setPrice(ItemSlot itemSlot, double price) {
        itemSlot.setPrice(price);
    }

    public double collectMoney(Balance bal, double amount) {
        if(bal.withdrawCash(amount) != null)
            return bal.getCurrentBal();
        else return 0;
    }
    public double collectMoney(Balance bal) {
        bal.withdrawCash(bal.getCurrentBal());
        return bal.getCurrentBal();
    }

    public double replenishMoney(Balance bal, String cashList) {
        bal.depositCash(cashList);
        return bal.getCurrentBal();
    }

    public void printTransacSummary(ArrayList<ItemSlot> listItemSlots) {
        double totalEarnings = 0;
        System.out.println("Number of items sold: ");
        for(int i=0; i<listItemSlots.size(); i++) {
            ItemSlot itemSlot = listItemSlots.get(i);
            System.out.println(itemSlot.getItemName() + ": " +
                               itemSlot.getNoSold() + " (" +
                               (itemSlot.getNoSold()+itemSlot.getItemStock())
                               + " -> " + itemSlot.getItemStock() + ")");
            // <item name>: <num sold> (<previous stock> -> <current stock>)
            totalEarnings += itemSlot.getPrice() * itemSlot.getNoSold();
        }
        System.out.println("Total earnings since last summary: " + totalEarnings);
    }

}

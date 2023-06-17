package regular_vm;

public class TestMenu {

    public void setItem(ItemSlot itemSlot, String itemName,
                        double caloriesAmt, double price) {
        Item item = new Item(itemName, caloriesAmt);
        itemSlot.setItem(item);
        itemSlot.setPrice(price);
    }

    public void stockItem(ItemSlot itemSlot, int noItems) {
        int i=0;
        for(i=0; i < noItems; i++)
            if(!itemSlot.stockItem()) {
                System.out.println("Error: Could not stock " +
                (noItems-i) + " " + itemSlot.getItem() + ".");
                break;
            } 

        if(i!=0)
            System.out.println("Successfully stocked " + noItems +
                               " " + itemSlot.getItem() + ".");
    }

    public void setPrice(ItemSlot itemSlot, double price) {
        itemSlot.setPrice(price);
    }

    public double collectMoney(Balance bal, double amount) {
        bal.decreaseBal(amount);
        return amount;
    }
    
    public double collectMoney(Balance bal) {
        double totalBalance = bal.getCurrentBal();
        bal.decreaseBal(totalBalance);
        return totalBalance;
    }

    public double replenishMoney(Balance bal, double amount) {
        bal.increaseBal(amount);
        return bal.getCurrentBal();
    }

    public void printTransacSummary() {

    }
    
}

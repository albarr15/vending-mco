package regular_vm;

public class TestMenu {

    public void setItem(ItemSlot itemSlot, String itemName,
                        double caloriesAmt, double price) {
        Item item = new Item(itemName, caloriesAmt, price);
        itemSlot.setItem(item);
        itemSlot.setPrice(price);
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
        System.out.println("Fully stocked " + itemSlot.getItem());
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
        double totalBalance = bal.getCurrentBal();
        bal.withdrawCash(totalBalance);
        return bal.getCurrentBal();
    }

    public double replenishMoney(Balance bal, String cashList) {
        bal.depositCash(cashList);
        return bal.getCurrentBal();
    }

    public void printTransacSummary() {
        // to add : starting & ending inventory since last restocking
        double totalEarnings = 0;
        System.out.println("Number of items sold: ");
        for(int i=0; i<slots.size(); i++) {
            System.out.println(slots.get(i).getItem() +
                               ": " + slots.get(i).getNoSold());
            totalEarnings += slots.get(i).getPrice();
        }
        System.out.println("Total earnings: " + totalEarnings);
    }
    
}

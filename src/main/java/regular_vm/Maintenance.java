/** Contains the maintenance features of the vending machine.
 * <p>
 * Features include setting Items of ItemSlots, stocking/restocking Items, setting
 * prices, collecting money from the Balance, and printing the summary of transactions.
 */
package regular_vm;
import java.util.ArrayList;

public class Maintenance {

    /**
     * Creates a new Item to be set as the Item of an ItemSlot
     * 
     * @param itemSlot  the slot for the Item to be placed in
     * @param itemName  the name of the Item
     * @param caloriesAmt  the calorie count of the Item
     * @param price  the price of the Item
     */
    public void setItem(ItemSlot itemSlot, String itemName,
                        double caloriesAmt, double price) {
        Item item = new Item(itemName, caloriesAmt, price);
        itemSlot.setItem(item);
    }

    /**
     * Stocks an ItemSlot of a certain amoutn of whatever Item is assigned to it
     * 
     * @param itemSlot  the ItemSlot to be stocked
     * @param numItems  the number of Items to be stocked
     */
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

    /**
     * Fully stocks an ItemSlot of whatever Item is assigned to it
     * 
     * @param itemSlot  the ItemSlot to be stocked
     */
    public void stockItem(ItemSlot itemSlot) {
        itemSlot.fullStockSlot();
        System.out.println("Fully stocked " + itemSlot.getItemName() + ".");
    }

    /**
     * Sets the price of Items in an ItemSlot
     * 
     * @param itemSlot  the ItemSlot to be given a price
     * @param price  the price to be set
     */
    public void setPrice(ItemSlot itemSlot, double price) {
        itemSlot.setPrice(price);
    }

    /**
     * Collects a certain amount of money from the machine
     * 
     * @param bal  the Balance of the machine
     * @param amount  the amount to be collected
     * @return  the remaining balance or null if unsuccessful
     */
    public double collectMoney(Balance bal, double amount) {
        if(bal.withdrawCash(amount) != null)
            return bal.getCurrentBal();
        else return 0;
    }

    /**
     * Collects all the money from the machine
     * 
     * @param bal  the Balance of the machine
     * @return  the remaining balance
     */
    public double collectMoney(Balance bal) {
        double origBal = bal.getCurrentBal();
        bal.withdrawCash(bal.getCurrentBal());
        return origBal;
    }

    /**
     * Replenishes the stock of bills and coins in the machine
     * 
     * @param bal  the Balance of the machine
     * @param cashList  a String containing values of the money to be stocked
     * @return  the new balance after stocking
     */
    public double replenishMoney(Balance bal, String cashList) {
        bal.depositCash(cashList);
        return bal.getCurrentBal();
    }

    /**
     * Prints the summary of transactions since the last summary printed
     * 
     * @param listItemSlots  the list of ItemSlots in the machine
     */
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

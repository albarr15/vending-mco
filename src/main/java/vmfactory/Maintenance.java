package vmfactory;
import java.util.ArrayList;
import java.util.StringJoiner;

/** Contains the maintenance features of the vending machine.
 * <p>
 * Features include stocking/restocking items, collecting money from the machine,
 * replenishing the money, and printing the summary of transactions.
 */
public class Maintenance {
    /**
     * Stocks an ItemSlot of a certain amount of whatever Item is assigned to it
     * 
     * @param itemSlot  the ItemSlot to be stocked
     * @param numItems  the number of Items to be stocked
     * @return the number of ignored stocks due to already full itemSlot
     *          0 if successfully restocked the total amount of numItems in itemSlots
     */
    public int stockItem(ItemSlot itemSlot, int numItems) {
        int i=0;
        for(i=0; i < numItems; i++)
            if(!itemSlot.stockItem(false)) {
                // Slot cannot be stocked because already full
                return (numItems - i);
                // System.out.println("Error: Could not stock " +
                // (numItems-i) + " " + itemSlot.getItemName() + ".");
            } 

        if(i!=0)
        {
            System.out.println("Successfully stocked " + numItems +
                    " " + itemSlot.getItemName() + ".");
        }
        return 0;
    }

    /**
     * Collects a certain amount of money from the machine
     * 
     * @param bal  the Balance of the machine
     * @param amount  the amount to be collected
     * @return  the collected money or null if unsuccessful
     */
    public int collectMoney(Balance bal, int amount) {
        if(bal.withdrawCash(amount) != null)
            return amount;
        else return 0;
    }

    /**
     * Collects all the money from the machine
     * 
     * @param bal  the Balance of the machine
     * @return  the collected money
     */
    public int collectMoney(Balance bal) {
        int origBal = bal.getCurrentBal();
        bal.withdrawCash(bal.getCurrentBal());
        return origBal;
    }

    /**
     * Replenishes the stock of bills and coins in the machine given a cashList
     * 
     * @param bal  the Balance of the machine
     * @param cashList  a String containing values of the money to be stocked
     * @return  the number of invalid entries found within the cashList
     */
    public int replenishMoney(Balance bal, String cashList) {
        return bal.depositCash(cashList);
    }

    /**
     * Replenishes the stock of bills and coins in the machine with the default stock of machine
     * (Default stock is 20 pieces of each denomination)
     *
     * @param bal  the Balance of the machine
     * @return  the new balance after stocking
     */
    public int replenishMoney(Balance bal) {
        int[][] defaultStock = { {1,5,10,20,50,100,200,500,1000},
                    {20,20,20,20,20,20,20,20,20} };
        bal.setCashStock(defaultStock);
        return bal.getCurrentBal();
    }

    /**
     * Prints the summary of transactions
     * 
     * @param listItemSlots  the list of ItemSlots in the machine
     * @return transacSummary a string containing the transaction summary
     */
    public String printTransacSummary(ArrayList<ItemSlot> listItemSlots) {
        StringJoiner transacSummary = new StringJoiner("");

        int totalEarnings = 0;
        transacSummary.add("Number of items sold : ");
        System.out.println("Number of items sold: ");
        for(int i=0; i<listItemSlots.size(); i++) {
            ItemSlot itemSlot = listItemSlots.get(i);
            transacSummary.add(itemSlot.getItemName() + ": " +
                    itemSlot.getNoSold() + " (" +
                    (itemSlot.getNoSold()+itemSlot.getItemStock())
                    + " -> " + itemSlot.getItemStock() + ")" + "\n");

            System.out.println(itemSlot.getItemName() + ": " +
                               itemSlot.getNoSold() + " (" +
                               (itemSlot.getNoSold()+itemSlot.getItemStock())
                               + " -> " + itemSlot.getItemStock() + ")");
            // <item name>: <num sold> (<previous stock> -> <current stock>)
            totalEarnings += itemSlot.getPrice() * itemSlot.getNoSold();
        }

        transacSummary.add("Total earnings since last summary: " + totalEarnings + "\n");
        System.out.println("Total earnings since last summary: " + totalEarnings);

        return transacSummary.toString();
    }

    /**
     * Sets the price of Items in an ItemSlot
     *
     * @param itemSlot  the ItemSlot to be given a price
     * @param price  the price to be set
     */
    public int setPrice(ItemSlot itemSlot, int price) {
        return itemSlot.setPrice(price);
    }
}

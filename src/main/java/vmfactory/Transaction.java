package vmfactory;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Represents a transaction
 */
public class Transaction {
    protected Item itemOrdered;
    protected int orderTotal;
    protected int amtReceived;

    public Transaction() {
        this.itemOrdered = null;
        this.orderTotal = 0;
        this.amtReceived = 0;
    }

    /**
     * Finds the itemSlot in which item1 is stored in
     * @param item  is the item to be searched
     * @param listItemSlots  is the list of existing item slots in the machine
     * @return  itemSlot if item is found in a slot
     *          null if item is not found in any existing slots
     */
    public ItemSlot findItemSlot(Item item, ArrayList<ItemSlot> listItemSlots) {
        ItemSlot foundItemSlot = null;

        // find itemSlot of item in currentCart
        for (ItemSlot listItemSlot : listItemSlots) {
            if (listItemSlot.getItemName().equals(item.getName())) {
                foundItemSlot = listItemSlot;
            }
        }
        return foundItemSlot;
    }

    /**
     * Gets item from slotOrdered and sets the order total accordingly
     * @param slotOrdered  is the item slot chosen by the user
     * @return  true if selection is successful, false if not
     */
    public boolean selectItem(ItemSlot slotOrdered){
        this.itemOrdered = slotOrdered.getItem();

        if(this.itemOrdered != null) {
            System.out.println("You have selected : " + itemOrdered.getName());
            this.orderTotal = this.itemOrdered.getPrice();
            return true;
        } else {
            System.out.println("Error : " + slotOrdered.getItemName() + " is not available.");
            return false;
        }
    }

    /**
     * Returns the item ordered to its proper slot
     * @param listItemSlots  the list of existing item slots in the machine
     */
    public void returnItem(ArrayList<ItemSlot> listItemSlots) {
        findItemSlot(this.itemOrdered, listItemSlots).stockItem(true);
    }



    /**
     * Receives payment from the customer
     * @param bal  is the current balance of the machine
     */
    public void receivePayment(Balance bal){
        int initialBal = bal.getCurrentBal();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Receiving payment ...");

        // prompt for cash deposit
        System.out.println("Enter Cash Payment : (Please separate each denomination with spaces)");
        String amount = scanner.nextLine();
        bal.depositCash(amount);
        this.amtReceived = (bal.getCurrentBal() - initialBal);

        System.out.println("Received : " +  this.amtReceived);
    }

    /**
     * Produces change according to the case situated by the current balance, item ordered, and order total
     * @param bal  is the current balance of the machine
     * @return true if transaction is successful, false if not
     */
    public boolean produceChange(Balance bal, ArrayList<ItemSlot> listItemSlots) {

        int changeAmt = this.amtReceived - this.orderTotal;

        if (this.itemOrdered == null) {
            System.out.println("TRANSACTION UNSUCCESSFUL (No item selected)");
            System.out.println("Returning amount received ...");
            System.out.println("Returned : " + bal.withdrawCash(amtReceived));
            return false;
        }
        else if (this.amtReceived < this.orderTotal) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough cash entered)");
            System.out.println("Returning amount received ...");
            System.out.println("Returned : " + bal.withdrawCash(amtReceived));
            return false;
        }
        else if((bal.withdrawCash(changeAmt)) == null) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough change in machine)");
            System.out.println("Returning amount received ...");
            System.out.println("Returned : " + bal.withdrawCash(amtReceived));
            return false;
        }
        else {
            System.out.println("TRANSACTION SUCCESSFUL");
            if(changeAmt > 0) {
                System.out.println("Withdrawing change ...");
                System.out.println("Your change is: " + bal.withdrawCash(changeAmt));
            }
            findItemSlot(this.itemOrdered, listItemSlots).dispenseItem();
            System.out.println();
            return true;
        }
    }

    /**
     * Resets all information of the current transaction
     */
    public void reset(ArrayList<ItemSlot> listItemSlots) {
        this.itemOrdered = null;
        this.orderTotal = 0;
    }

    public Item getItemOrdered() {
        return itemOrdered;
    }
}

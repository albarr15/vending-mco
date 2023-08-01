package vmfactory;
import java.util.ArrayList;

/**
 * Represents a transaction
 * <p>
 *     Features include: selecting an item, receiving payment, producing change,
 *     and resetting the said transaction
 * </p>
 */
public class Transaction {
    protected Item itemOrdered;
    protected int orderTotal;
    protected int amtReceived;
    protected String returned = "0";

    public Transaction() {
        this.itemOrdered = null;
        this.orderTotal = 0;
        this.amtReceived = 0;
    }

    /**
     * Finds the itemSlot in which item is stored in
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
     * Selects item from slotOrdered and sets the order total accordingly
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
     * Receives payment from the customer
     * @param amount is a String cashList corresponding to the amount entered by the customer
     * @param bal  is the current balance of the machine
     */
    public void receivePayment(String amount, Balance bal){
        int initialBal = bal.getCurrentBal();
        
        bal.depositCash(amount);
        this.amtReceived = (bal.getCurrentBal() - initialBal);

        System.out.println("Received : " +  this.amtReceived);
    }

    /**
     * Produces change according to the case situated by the current balance,
     * item ordered, change, and order total
     *
     * @param bal is the current balance of the machine
     * @param listItemSlots is the existing list of item slots in the machine
     * @return 1 if no item is selected
     *         2 if there is not enough item stock
     *         3 if there is not enough cash entered
     *         4 if there is not enough change in machine
     *         0 if transaction is successful
     */
    public int produceChange(Balance bal, ArrayList<ItemSlot> listItemSlots) {

        int changeAmt = this.amtReceived - this.orderTotal;
        String change = null;

        if (this.itemOrdered == null) {
            System.out.println("TRANSACTION UNSUCCESSFUL (No item selected)");
            System.out.println("Returning amount received ...");
            this.returned = bal.withdrawCash(amtReceived);
            System.out.println("Returned : " + returned);
            return 1;
        }
        else if(findItemSlot(this.itemOrdered, listItemSlots).getItemStock() == 0) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough item stock)");
            System.out.println("Returning amount received ...");
            this.returned = bal.withdrawCash(amtReceived);
            System.out.println("Returned : " + returned);
            return 2;
        }
        else if (this.amtReceived < this.orderTotal) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough cash entered)");
            System.out.println("Returning amount received ...");
            this.returned = bal.withdrawCash(amtReceived);
            System.out.println("Returned : " + returned);
            return 3;
        }
        change = bal.withdrawCash(changeAmt);
        if(change == null) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough change in machine)");
            System.out.println("Returning amount received ...");
            this.returned = bal.withdrawCash(amtReceived);
            System.out.println("Returned : " + returned);
            return 4;
        }
        else {
            System.out.println("TRANSACTION SUCCESSFUL");
            if(changeAmt > 0) {
                System.out.println("Withdrawing change ...");
                this.returned = change;
                System.out.println("Your change is: " + change);
            } else this.returned = "0";
            findItemSlot(this.itemOrdered, listItemSlots).dispenseItem();
            System.out.println();
            return 0;
        }
    }

    /**
     * Resets all information of the current transaction
     * @param listItemSlots is the list of existing ItemSlots in the machine
     */
    public void reset(ArrayList<ItemSlot> listItemSlots) {
        this.itemOrdered = null;
        this.orderTotal = 0;
    }

    public Item getItemOrdered() {
        return itemOrdered;
    }

    public String getReturned() {
        return this.returned;
    }
}

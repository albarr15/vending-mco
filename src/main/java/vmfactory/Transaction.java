package vmfactory;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Represents a transaction
 */
public class Transaction {
    private Item itemOrdered;
    private int orderTotal;
    private int amtReceived;
    private boolean status = false;

    public void setStatus(boolean status) {
        if (!status) {
            this.setItemOrdered(null);
            this.amtReceived = 0;
            this.orderTotal = 0;
        }
        else {
            this.status = status;
        }
    }

    public boolean getStatus() {
        return status;
    }

    /**
     * Finds the itemSlot in which item1 is stored in
     * @param item1 is the item to be searched
     * @param listItemSlots is the list of existing item slots in the machine
     * @return itemSlot if item1 is found in a slot
     *         null if item1 is not found in any existing slots
     */
    public ItemSlot findItemSlot(Item item1, ArrayList<ItemSlot> listItemSlots) {
        ItemSlot foundItemSlot = null;

        // find itemSlot of item in currentCart
        for (ItemSlot listItemSlot : listItemSlots) {
            if (listItemSlot.getItemName().equals(item1.getName())) {
                foundItemSlot = listItemSlot;
            }
        }

        return foundItemSlot;
    }


    /**
     * Receives payment from the customer
     * @param bal is the current balance of the machine
     */
    public void receivePayment(Balance bal){
        System.out.println("Receiving payment ...");

        Scanner scanner = new Scanner(System.in);

        int initialBal = bal.getCurrentBal();

        // prompt for cash deposit
        System.out.println("Enter Cash Payment : (Please separate each denomination with spaces)");
        String amount = scanner.nextLine();
        bal.depositCash(amount);
        this.setAmtReceived((bal.getCurrentBal() - initialBal));

        System.out.println("Received : " +  amtReceived);
    }

    public void setItemOrdered(Item itemOrdered) {
        this.itemOrdered = itemOrdered;
    }

    /**
     * Sets item order from the parameter itemOrdered1 and sets the order total accordingly
     * @param itemOrdered1 is the item chosen by the user
     */
    public void selectItem(Item itemOrdered1){
        this.itemOrdered = itemOrdered1;
        this.orderTotal = itemOrdered1.getPrice();

        System.out.println("You have selected : " + itemOrdered1.getName());
    }

    public Item getItemOrdered() {
        return itemOrdered;
    }

    public void setAmtReceived(int amtReceived) {
        this.amtReceived = amtReceived;
    }

    /**
     * Produces change according to the case situated by the current balance, item ordered, and order total
     * @param bal is the current balance of the machine
     */
    public void produceChange(Balance bal) {
        int changeAmt = this.amtReceived - this.orderTotal;

        if (this.itemOrdered == null) {
            System.out.println("TRANSACTION UNSUCCESSFUL (No item selected)");
            System.out.println("Returning amount received ...");
            System.out.println("Returned : " + bal.withdrawCash(amtReceived));
            this.setStatus(false);
        }
        else if (this.amtReceived < this.orderTotal) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough cash entered)");
            System.out.println("Returning amount received ...");
            System.out.println("Returned : " + bal.withdrawCash(amtReceived));
            this.setStatus(false);
        }
        else if((bal.withdrawCash(changeAmt)) == null) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough change in machine)");
            System.out.println("Returning amount received ...");
            System.out.println("Returned : " + bal.withdrawCash(amtReceived));
            this.setStatus(false);
        }
        else {
            System.out.println("TRANSACTION SUCCESSFUL");
            System.out.println("Withdrawing change ...");
            System.out.println("Your change is: " + changeAmt);
            System.out.print("Dispensing " + this.itemOrdered.getName() + "...");
            System.out.println();
            this.setStatus(true);
        }
    }

    /**
     * Dispenses item previously selected and produces corresponding change
     * @param bal is the current balance of the machine
     */
    public void dispenseItem(Balance bal, ArrayList<ItemSlot> listItemSlots) {
        this.produceChange(bal);
        if (this.status) {
            ItemSlot itemSlot = this.findItemSlot(itemOrdered, listItemSlots);
            itemSlot.dispenseItem();
        }
    }
}


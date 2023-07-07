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
    private boolean isSpecial;
    private SpecialItem specialItem;

    public Transaction() {
        this.itemOrdered = null;
        this.orderTotal = 0;
        this.isSpecial = false;
        this.specialItem = null;
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
        this.itemOrdered = slotOrdered.dispenseItem();

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
     * Starts a special transaction to make a special item
     */
    public void doSpecialTransaction() {
        this.isSpecial = true;
        this.specialItem = new SpecialItem("Custom Ramen");
    }

    /**
     * Adds an item to this special item
     * @param itemSlot  the slot from which the item will be taken from
     */
    public void addItem(ItemSlot itemSlot) {
        this.specialItem.addComponent(itemSlot.dispenseItem());
        this.orderTotal = this.specialItem.getTotalPrice();
    }

    /**
     * Remmoves an item from this special item
     * @param itemName  the name of the item to be removed
     * @param listItemSlots  the list of existing item slots in the machine
     */
    public void removeItem(String itemName, ArrayList<ItemSlot> listItemSlots) {
        Item itemRemoved = this.specialItem.removeComponent(itemName);
        if(itemRemoved != null)
            findItemSlot(itemRemoved, listItemSlots).stockItem(true);

        this.orderTotal = this.specialItem.getTotalPrice();
    }

    /**
     * Previews this special item and its current components
     */
    public void previewItem() {
        System.out.println(this.specialItem.getName() + ": ");

        for(Item item : this.specialItem.getListComponents())
            System.out.println(item.getName());

        System.out.println("Current item price: " + this.orderTotal +
                         "\nCurrent item calories: " + this.specialItem.getTotalCalories());
    }

    /**
     * Receives payment from the customer
     * @param bal  is the current balance of the machine
     */
    public void receivePayment(Balance bal){
        int amtReceived = 0;
        int initialBal = bal.getCurrentBal();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Receiving payment ...");

        // prompt for cash deposit
        System.out.println("Enter Cash Payment : (Please separate each denomination with spaces)");
        String amount = scanner.nextLine();
        bal.depositCash(amount);
        amtReceived = (bal.getCurrentBal() - initialBal);
        scanner.close();

        System.out.println("Received : " +  amtReceived);
        produceChange(bal, amtReceived);
    }

    /**
     * Produces change according to the case situated by the current balance, item ordered, and order total
     * @param bal  is the current balance of the machine
     * @param amtReceived  is the amount given by the customer
     * @return  true if transaction is successful, false if not
     */
    public boolean produceChange(Balance bal, int amtReceived) {

        int changeAmt = amtReceived - this.orderTotal;

        if (this.itemOrdered == null) {
            System.out.println("TRANSACTION UNSUCCESSFUL (No item selected)");
            System.out.println("Returning amount received ...");
            System.out.println("Returned : " + bal.withdrawCash(amtReceived));
            return false;
        }
        else if (amtReceived < this.orderTotal) {
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
            if(!isSpecial) {
                System.out.print("Dispensing " + this.itemOrdered.getName() + "...");
            } else {
                this.specialItem.printPreparation();
                System.out.print("Dispensing " + this.specialItem.getName() + "...");
            }
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
        this.isSpecial = false;
        if(this.specialItem != null) {
            for(Item item : this.specialItem.getListComponents())
            findItemSlot(item, listItemSlots).stockItem(true);
        }
        this.specialItem = null;
    }

    public Item getItemOrdered() {
        return itemOrdered;
    }
}

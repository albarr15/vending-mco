package vmfactory;

import java.util.ArrayList;

public class SpecialTransaction extends Transaction{
    private boolean isSpecial;
    private SpecialItem specialItem;

    public SpecialTransaction() {
        this.isSpecial = false;
        this.specialItem = null;
    }

    /**
     * Adds an item to this special item
     * @param itemSlot  the slot from which the item will be taken from
     */
    public void addItem(ItemSlot itemSlot) {
        this.specialItem.addComponent(itemSlot.dispenseItem());
        this.orderTotal = this.specialItem.getPrice();
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

        this.orderTotal = this.specialItem.getPrice();
    }

    /**
     * Previews this special item and its current components
     */
    public void previewItem() {
        System.out.println(this.specialItem.getName() + ": ");

        for(Item item : this.specialItem.getListComponents())
            System.out.println(item.getName());

        System.out.println("Current item price: " + this.orderTotal +
                "\nCurrent item calories: " + this.specialItem.getCaloriesAmt());
    }
    /**
     * Starts a special transaction to make a special item
     */
    public void doSpecialTransaction() {
        this.isSpecial = true;
        this.specialItem = new SpecialItem("Custom Ramen");
    }

    /**
     * (For Special Transactions) Produces change according to the case situated by the current balance, item ordered, and order total
     *
     * @param bal  is the current balance of the machine
     * @return true if transaction is successful, false if not
     */
    @Override public boolean produceChange(Balance bal) {

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
     * (For Special Transactions) Resets all information of the current transaction (including special item components)
     */
    @Override public void reset(ArrayList<ItemSlot> listItemSlots) {
        this.itemOrdered = null;
        this.orderTotal = 0;
        this.isSpecial = false;
        if(this.specialItem != null) {
            for(Item item : this.specialItem.getListComponents())
                findItemSlot(item, listItemSlots).stockItem(true);
        }
        this.specialItem = null;
    }
}

package vmfactory;

import java.util.ArrayList;

public class SpecialTransaction extends Transaction {

    public SpecialTransaction() {
        this.itemOrdered = null;
    }

    /**
     * Adds an item to this special item
     * @param itemSlot  the slot from which the item will be taken from
     */
    public void addItem(ItemSlot itemSlot) {
        ((SpecialItem)this.itemOrdered).addComponent(itemSlot.dispenseItem());
        this.orderTotal = this.itemOrdered.getPrice();
    }

    /**
     * Remmoves an item from this special item
     * @param itemName  the name of the item to be removed
     * @param listItemSlots  the list of existing item slots in the machine
     */
    public void removeItem(String itemName, ArrayList<ItemSlot> listItemSlots) {
        Item itemRemoved = ((SpecialItem)this.itemOrdered).removeComponent(itemName);
        if(itemRemoved != null)
            findItemSlot(itemRemoved, listItemSlots).stockItem(true);

        this.orderTotal = this.itemOrdered.getPrice();
    }

    /**
     * Previews this special item and its current components
     */
    public void previewItem() {
        System.out.println(this.itemOrdered.getName() + ": ");

        for(Item item : ((SpecialItem)this.itemOrdered).getListComponents())
            System.out.println(item.getName());

        System.out.println("Current item price: " + this.orderTotal +
                "\nCurrent item calories: " + this.itemOrdered.getCaloriesAmt());
    }

    /**
     * (For Special Transactions) Produces change according to the case situated by the current balance, item ordered, and order total
     *
     * @param bal is the current balance of the machine
     * @return true if transaction is successful, false if not
     */
    @Override public int produceChange(Balance bal, ArrayList<ItemSlot> listItemSlots) {

        int changeAmt = this.amtReceived - this.orderTotal;
        boolean isSpecial = this.itemOrdered instanceof SpecialItem;
        String change = null;

        if (this.itemOrdered == null) {
            System.out.println("TRANSACTION UNSUCCESSFUL (No item selected)");
            System.out.println("Returning amount received ...");
            this.returned = bal.withdrawCash(amtReceived);
            System.out.println("Returned : " + returned);
            return 1;
        }
        // checks if the component of current special item is not allowed to be sold separately
        else if (isSpecial && ((SpecialItem)this.itemOrdered).getListComponents().size() == 1) {
            ItemSlot itemSlot = findItemSlot(((SpecialItem)this.itemOrdered).getListComponents().get(0), listItemSlots);
            if (!itemSlot.getForSale()) {
                System.out.println("TRANSACTION UNSUCCESSFUL (Component of Special Item is not allowed to be sold separately)");
                System.out.println("Returning amount received ...");
                this.returned = bal.withdrawCash(amtReceived);
                System.out.println("Returned : " + returned);
                return 5;
            }
        }
        else if(!isSpecial && findItemSlot(this.itemOrdered, listItemSlots).getItemStock() == 0) {
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
            if (this.itemOrdered instanceof SpecialItem){
                ((SpecialItem)this.itemOrdered).printPreparation();
                System.out.print("Ramen done!\n");
                findItemSlot(this.itemOrdered, listItemSlots).dispenseItem();
            }
            else {
                findItemSlot(this.itemOrdered, listItemSlots).dispenseItem();
            }
            System.out.println();
            return 0;
        }
    }

    /**
     * (For Special Transactions) Resets all information of the current transaction (including special item components)
     */
    @Override public void reset(ArrayList<ItemSlot> listItemSlots) {
        this.orderTotal = 0;
        if(this.itemOrdered instanceof SpecialItem) {
            for(Item item : ((SpecialItem)this.itemOrdered).getListComponents()) {
                findItemSlot(item, listItemSlots).stockItem(true);
                System.out.println("Returning " + item.getName());
             }
        }
        this.itemOrdered = null;
    }

    public void setSpecialItem(SpecialItem specialItem) {
        this.itemOrdered = specialItem;
    }
}

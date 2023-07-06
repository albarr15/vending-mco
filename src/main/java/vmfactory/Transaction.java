package vmfactory;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Represents a transaction
 */
public class Transaction {
    private ArrayList<Item> currentCart = new ArrayList<Item>();
    private int orderTotal;
    private int amtReceived;
    private boolean status = true;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    /**
     * Adds the given item from the itemSlot to the current cart and updates the orderTotal
     * @param itemSlot is where the given item to be added to cart is stored
     */
    public void addToCart(ItemSlot itemSlot) {
        Item item = itemSlot.dispenseItem();
        if(item != null) {
            this.currentCart.add(item);
            orderTotal = orderTotal + itemSlot.getPrice();
            System.out.println("Added " + itemSlot.getItemName() + " to cart.");
        }
    }


    /**
     * Removes the given item of the itemSlot from the current cart and updates the orderTotal
     * @param itemSlot is where the given item to be removed to cart is stored
     */
    public void removeFromCart(ItemSlot itemSlot) {
        System.out.println("Removing from cart ...");

        System.out.println("Removing " + itemSlot.getItemName() + "...");

        ListIterator<Item> cartIterator = currentCart.listIterator();

        while (cartIterator.hasNext()) {
            if (cartIterator.next().getName().equals(itemSlot.getItemName())) {
                cartIterator.remove();
                itemSlot.stockItem(true);
                orderTotal = orderTotal - itemSlot.getPrice();

                System.out.println("Removed " + itemSlot.getItem().getName() + " from cart.");
            }
        }
    }

    // displays currentCart's items
    public void previewCart() {
        System.out.println();
        System.out.println("----- Current Cart -----");
        if (currentCart != null) {
            for(int i = 0; i < this.currentCart.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + this.currentCart.get(i).getName());
            }
            System.out.println("Current total: " + orderTotal);
            System.out.println();
        }
    }

    /**
     * Checks out the current cart where it prompts for cash deposit
     * and determines whether the transaction is successful or not
     * @param bal is the current balance
     * @param listItemSlots is the list of existing item slots in the machine
     */
    public boolean checkOut(Balance bal, ArrayList<ItemSlot> listItemSlots){
        Scanner scanner = new Scanner(System.in);
        String change;
        int initialBal = bal.getCurrentBal();

        // prompt for cash deposit
        System.out.println("Enter Cash Payment : (Please separate each denomination with spaces)");
        String amount = scanner.nextLine();
        bal.depositCash(amount);

        // get newly added cash for this specific transaction
        this.amtReceived = bal.getCurrentBal() - initialBal;
        int changeAmt = this.amtReceived - this.orderTotal;

        if (this.amtReceived < this.orderTotal) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough cash entered)");
            this.cancelOrder(bal, listItemSlots);
            return false;
        }
        else if((change = bal.withdrawCash(changeAmt)) == null) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough change in machine)");
            this.cancelOrder(bal, listItemSlots);
            return false;
        } else {
            System.out.println("TRANSACTION SUCCESSFUL");
            System.out.println("Withdrawing change ...");
            System.out.println("Your change is: " + change);
            System.out.print("Dispensing " + this.currentCart.get(0).getName());
            for(int i=1; i<currentCart.size(); i++) {
                System.out.print(", " + currentCart.get(i).getName());
            }
            System.out.print(". . .");
            System.out.println();
            this.setStatus(false);
            return true;
        }
    }

    /**
     * Cancels an order
     * This sets the transaction as inactive as well as withdraw previously deposited money and empties current cart
     * @param bal is the current balance
     * @param listItemSlots is the list of item slots of the machine
     */
    public void cancelOrder(Balance bal, ArrayList<ItemSlot> listItemSlots) {
        if (this.amtReceived > 0) {
            System.out.println("Returning cash ...");
            bal.withdrawCash(this.amtReceived);
            this.amtReceived = 0;
        }

        ArrayList<Item> initialCart = new ArrayList<Item>(currentCart);

        for (Item item : initialCart) {
            this.removeFromCart(findItemSlot(item, listItemSlots));
        }

        this.setStatus(false);
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

        System.out.println("Received : " +  (bal.getCurrentBal() - initialBal));
    }
}


package regular_vm;
import java.util.ArrayList;
import java.util.Scanner;

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
        }
    }

    /**
     * Removes the given item from the itemSlot to the current cart and updates the orderTotal
     * @param itemSlot is where the given item to be added to cart is stored
     */
    public void removeFromCart(ItemSlot itemSlot) {
        if(currentCart.remove(itemSlot.getItem())) {
            itemSlot.stockItem(true);
            orderTotal = orderTotal - itemSlot.getPrice();
        }
    }

    // displays currentCart's items
    public void previewCart() {
        System.out.println("----- Current Cart -----");
        if (currentCart != null) {
            for(int i = 0; i < this.currentCart.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + this.currentCart.get(i).getName());
            }
            System.out.println("Current total: " + orderTotal);
        }
    }

    /**
     * Checks out the current cart where it prompts for cash deposit
     * and determines whether the transaction is successful or not
     * @param bal is the current balance
     */
    public boolean checkOut(Balance bal){
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
            System.out.println("Returning cash ...");
            this.cancelOrder(bal, this.amtReceived);
            return false;
        }
        else if((change = bal.withdrawCash(changeAmt)) == null) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough change in machine)");
            System.out.println("Returning cash ...");
            this.cancelOrder(bal, this.amtReceived);
            return false;
        } else {
            System.out.println("TRANSACTION SUCCESSFUL");
            System.out.println("Withdrawing change ...");
            System.out.println("Your change is: " + change);
            System.out.println("Dispensing " + this.currentCart.get(0).getName());
            for(int i=1; i<currentCart.size(); i++) {
                System.out.print(", " + currentCart.get(i).getName());
            }
            System.out.println(". . .");
            this.setStatus(false);
            return true;
        }
    }

    /**
     * Cancels an order that has not gone through check out yet
     * This sets the transaction as inactive
     * @param bal is the current balance
     */
    public void cancelOrder(Balance bal) {
        this.setStatus(false);
    }

    /**
     * Cancels an order that has proceeded with checkout
     * This sets the transaction as inactive as well as withdraw previously deposited money
     * @param bal is the current balance
     * @param amtReceived is the total amount of entered money by the user
     */
    public void cancelOrder(Balance bal, int amtReceived) {
        bal.withdrawCash(this.amtReceived);
        this.amtReceived = 0;
        this.setStatus(false);
    }
}

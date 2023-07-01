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

    public void addToCart(ItemSlot itemSlot) {
        Item item = itemSlot.dispenseItem();
        this.currentCart.add(item);
        orderTotal = orderTotal + itemSlot.getPrice();
    }

    public void removeFromCart(ItemSlot itemSlot) {
        currentCart.remove(itemSlot.getItem());
        itemSlot.stockItem(true);
        orderTotal = orderTotal - itemSlot.getPrice();
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

    public void checkOut(Balance bal){
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
        }
        else if((change = bal.withdrawCash(changeAmt)) == null) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough change in machine)");
            System.out.println("Returning cash ...");
            this.cancelOrder(bal, this.amtReceived);
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
        }
    }
    public void cancelOrder(Balance bal) {
        this.setStatus(false);
    }

    public void cancelOrder(Balance bal, int amtReceived) {
        bal.withdrawCash(this.amtReceived);
        this.amtReceived = 0;
        this.setStatus(false);
    }
}

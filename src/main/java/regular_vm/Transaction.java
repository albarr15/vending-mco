package regular_vm;
import java.util.ArrayList;
import java.util.Scanner;

public class Transaction {
    private ArrayList<Item> currentCart = new ArrayList<Item>();
    private double orderTotal;
    private double amtReceived;
    private boolean status;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void addToCart(Item item, ItemSlot itemSlot) {
        currentCart.add(item);
        orderTotal = orderTotal + itemSlot.price;
    }

    public void removeFromCart(Item item, ItemSlot itemSlot) {
        currentCart.remove(item);
        orderTotal = orderTotal + itemSlot.price;
    }

    // displays currentCart's items
    public void previewCart() {
        System.out.println("----- Current Cart -----");
        for(int i = 0; i < currentCart.size(); i++) {
            System.out.println("[i] " + currentCart.get(i));
        }
    }

    // returns 0 if successful, 1 if not
    public int checkOut(Balance bal){
        Scanner scanner = new Scanner(System.in);

        // prompt for cash deposit
        double initialBal = bal.getCurrentBal();
        bal.depositCash(scanner.next());

        // get newly added cash for this specific transaction
        this.amtReceived = initialBal - bal.getCurrentBal();

        if (this.amtReceived < this.orderTotal) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough cash entered)");
            System.out.println("Returning cash ...");
            bal.withdrawCash(this.amtReceived);

            return 1;
        }
        else {
            double changeAmt = this.amtReceived - orderTotal;
            System.out.println("TRANSACTION SUCCESSFUL");
            System.out.println("Withdrawing change ...");
            bal.withdrawCash(changeAmt);
            return 0;
        }
    }
}
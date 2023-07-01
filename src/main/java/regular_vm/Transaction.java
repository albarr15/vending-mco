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

    public void addToCart(ItemSlot itemSlot) {
        Item item = itemSlot.dispenseItem();
        this.currentCart.add(item);
        orderTotal = orderTotal + itemSlot.getPrice();
    }

    public void removeFromCart(Item item, ItemSlot itemSlot) {
        currentCart.remove(item);
        itemSlot.stockItem(true);
        orderTotal = orderTotal - itemSlot.getPrice();
    }

    // displays currentCart's items
    public void previewCart() {
        System.out.println("----- Current Cart -----");
        if (currentCart != null) {
            for(int i = 0; i < this.currentCart.size(); i++) {
                System.out.println("[" + i + "] " + this.currentCart.get(i).getName());
            }
            System.out.println("Current total: " + orderTotal);
        }
    }

    // returns 0 if successful, 1 if not
    public int checkOut(Balance bal){
        Scanner scanner = new Scanner(System.in);
        String change;

        // prompt for cash deposit
        double initialBal = bal.getCurrentBal();
        bal.depositCash(scanner.next());

        // get newly added cash for this specific transaction
        this.amtReceived = initialBal - bal.getCurrentBal();
        double changeAmt = this.amtReceived - this.orderTotal;

        if (this.amtReceived < this.orderTotal) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough cash entered)");
            System.out.println("Returning cash ...");
            bal.withdrawCash(this.amtReceived);
            this.amtReceived = 0;
            return 1;
        }
        else if((change = bal.withdrawCash(changeAmt)) == null) {
            System.out.println("TRANSACTION UNSUCCESSFUL (Not enough change in machine)");
            System.out.println("Returning cash ...");
            bal.withdrawCash(this.amtReceived);
            this.amtReceived = 0;
            return 1;
        } else {
            System.out.println("TRANSACTION SUCCESSFUL");
            System.out.println("Withdrawing change ...");
            System.out.println("Your change is: " + change);
            System.out.println("Dispensing " + this.currentCart.get(0).getName());
            for(int i=0; i<currentCart.size(); i++) {
                System.out.println(", " + currentCart.get(i).getName());
            }
            System.out.println(". . .");
            return 0;
        }
    }
}

package regular_vm;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Character;

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

    // shows summary of total cart and prompts for order placement
    // returns 0 if successful, 1 if user wants to go back to modifying currentCart items
    public int checkOut(Balance bal){
        for(int i = 0; i < currentCart.size(); i++) {
            System.out.println("[i] " + currentCart.get(i));
        }
        System.out.println("Place Order? (y/n)");
        Scanner scanner = new Scanner(System.in);
        char input = scanner.next().charAt(0);
        if (input == 'y') {
            // prompt for cash deposit
            double initialBal = bal.getCurrentBal();
            bal.depositCash(scanner.next());

            // get newly added cash for this specific transaction
            amtReceived = initialBal - bal.getCurrentBal();
            return 0;
        }
        else {
            return 1;
        }
    }


    public void receiveMoney(Balance bal, double amount) {
        if (isValidDenomination(amount)) {
            this.amtReceived = this.amtReceived + amount;
            bal.getListDenominations().add(amount);
        }
        else {
            System.out.println("Error : Invalid Denomination");
        }
    }

    public double dispenseChange(Balance bal) {
        // to be modified,, use balance of regular vm for change and apply specific denominations

        if (amtReceived < orderTotal) {
            bal.decreaseBal(amtReceived);
            return amtReceived;
        }
        else {
            bal.decreaseBal(amtReceived - orderTotal);
            return amtReceived - orderTotal;
        }
    }

    public boolean isValidDenomination(double amount) {
        if (amount == 1000 || amount == 500 || amount == 200 || amount == 100 || amount == 50 || amount == 20 ||
            amount == 10 || amount == 5 || amount == 1 || amount == 0.25 || amount == 0.05 || amount == 0.01) {
            return true;
        }
        else {
            return false;
        }
    }
}

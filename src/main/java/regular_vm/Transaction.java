package regular_vm;
import java.util.ArrayList;

public class Transaction {
    private ArrayList<Item> currentCart;
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

    public void receiveMoney(double amount) {
        if (amount == 1000) {
            this.amtReceived = this.amtReceived + amount;
        }
        else if (amount == 500) {
            this.amtReceived = this.amtReceived + amount;
        }
        else if (amount == 200) {
            this.amtReceived = this.amtReceived + amount;
        }
        else if (amount == 100) {
            this.amtReceived = this.amtReceived + amount;
        }
        else if (amount == 50) {
            this.amtReceived = this.amtReceived + amount;
        }
        else if (amount == 20) {
            this.amtReceived = this.amtReceived + amount;
        }
        else if (amount == 10) {
            this.amtReceived = this.amtReceived + amount;
        }
        else if (amount == 5) {
            this.amtReceived = this.amtReceived + amount;
        }
        else if (amount == 1) {
            this.amtReceived = this.amtReceived + amount;
        }
        else if (amount == 0.25) {
            this.amtReceived = this.amtReceived + amount;
        }
        else if (amount == 0.05) {
            this.amtReceived = this.amtReceived + amount;
        }
        else if (amount == 0.01) {
            this.amtReceived = this.amtReceived + amount;
        }
        else {
            System.out.println("Error : Invalid denomination");
        }
    }

    public double dispenseChange(Balance bal) {
        // use balance of regular vm for change

        if (amtReceived < orderTotal) {
            bal.decreaseBal(amtReceived);
            return amtReceived;
        }
        else {
            bal.decreaseBal(amtReceived - orderTotal);
            return amtReceived - orderTotal;
        }
    }
}

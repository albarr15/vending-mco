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

    public void addToCart(Item item) {
        currentCart.add(item);
        // calculate orderTotal here
    }

    public void removeFromCart(Item item) {
        currentCart.remove(item);
    }

    public void receiveMoney(double amount) {
        this.amtReceived = amount;
    }

    public double dispenseChange() {
        if (amtReceived < orderTotal) {
            return amtReceived;
        }
        else {
            return amtReceived - orderTotal;
        }
    }
}

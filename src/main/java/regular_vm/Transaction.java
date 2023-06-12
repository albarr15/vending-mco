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

    }

    public void removeFromCart(Item item) {

    }

    public void receiveMoney(double amount) {

    }

    public double dispenseChange() {
        // to be modified
        return 0;
    }
}

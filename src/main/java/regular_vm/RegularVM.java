package regular_vm;
import java.util.ArrayList;

public class RegularVM {
    private ArrayList<ItemSlot> listItemSlots;
    private Balance balance;
    private Transaction currentTransaction;

    public void addItemSlot(ItemSlot itemSlot) {
        listItemSlots.add(itemSlot);
    }

    public void displaySlotAvailability(ItemSlot itemSlot) {
        if (itemSlot.checkSlotAvailability()) {
            System.out.println("Slot with " + itemSlot.item.name + "items is NOT AVAILABLE.");
        }
        else {
            System.out.println("Slot with " + itemSlot.item.name + "items is AVAILABLE.");
        }
    }

    public void displayAllSlots() {
        int size = listItemSlots.size();

        // prints each itemSlot with its corresponding stocked item
        for (int i = 0; i < size; i++) {
            if ((listItemSlots.get(i)).checkSlotAvailability()) {
                System.out.println("Slot " + i + " ( OUT OF STOCK )");
            }
            else {
                System.out.println("Slot " + i + " (" + (listItemSlots.get(i)).item.name + ")");
            }
        }
    }

    public void makeTransaction() {
        Transaction transaction = new Transaction();
        this.currentTransaction = transaction;
    }

    public void receivePayment(double payment) {
        this.balance = new Balance(payment);
    }
}

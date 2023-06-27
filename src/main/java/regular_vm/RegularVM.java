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
            System.out.println("Slot with " + itemSlot.getItem() + "items is NOT AVAILABLE.");
        }
        else {
            System.out.println("Slot with " + itemSlot.getItem() + "items is AVAILABLE.");
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
                System.out.println("Slot " + i + " (" + (listItemSlots.get(i)).getItem() + ")");
            }
        }
    }

    public void makeTransaction() {
        Transaction transaction = new Transaction();
        this.currentTransaction = transaction;
    }

    public static void main(String[] args) {
        // assume that user chooses to create a Regular Vending Machine
        RegularVM regularVM = new RegularVM();

        // Instantiate items
        Item chashuPork = new Item("Chashu Pork", 99, 95);
        Item chickenSlices = new Item("Chicken Slices", 95, 153.5);
        Item fishCake = new Item("Fish Cake", 63, 40);
        Item ajitamago = new Item("Ajitamago", 72, 35);
        Item friedTofu = new Item("Fried Tofu", 77, 14.9);
        Item seaweed = new Item("Seaweed", 8.5, 16.5);
        Item corn = new Item("Corn", 76, 40.89);
        Item butter = new Item("Butter", 81, 1.799);

        // Instantiate itemSlots
        ItemSlot itemSlot1 = new ItemSlot();
        ItemSlot itemSlot2 = new ItemSlot();
        ItemSlot itemSlot3 = new ItemSlot();
        ItemSlot itemSlot4 = new ItemSlot();
        ItemSlot itemSlot5 = new ItemSlot();
        ItemSlot itemSlot6 = new ItemSlot();
        ItemSlot itemSlot7 = new ItemSlot();
        ItemSlot itemSlot8 = new ItemSlot();

        // set Item for each itemSlot
        itemSlot1.setItem(chashuPork);
        itemSlot2.setItem(chickenSlices);
        itemSlot3.setItem(fishCake);
        itemSlot4.setItem(ajitamago);
        itemSlot5.setItem(friedTofu);
        itemSlot6.setItem(seaweed);
        itemSlot7.setItem(corn);
        itemSlot8.setItem(butter);

        // set Prices for each itemSlot
        itemSlot1.setPrice(chashuPork.getPrice());
        itemSlot2.setPrice(chickenSlices.getPrice());
        itemSlot3.setPrice(fishCake.getPrice());
        itemSlot4.setPrice(ajitamago.getPrice());
        itemSlot5.setPrice(friedTofu.getPrice());
        itemSlot6.setPrice(seaweed.getPrice());
        itemSlot7.setPrice(corn.getPrice());
        itemSlot8.setPrice(butter.getPrice());

        // fully restock all itemSlots
        for (int i = 0; i < 10; i++) {
            itemSlot1.stockItem();
        }
        for (int i = 0; i < 10; i++) {
            itemSlot2.stockItem();
        }
        for (int i = 0; i < 10; i++) {
            itemSlot3.stockItem();
        }
        for (int i = 0; i < 10; i++) {
            itemSlot4.stockItem();
        }
        for (int i = 0; i < 10; i++) {
            itemSlot5.stockItem();
        }
        for (int i = 0; i < 10; i++) {
            itemSlot6.stockItem();
        }
        for (int i = 0; i < 10; i++) {
            itemSlot7.stockItem();
        }
        for (int i = 0; i < 10; i++) {
            itemSlot8.stockItem();
        }


    }
}

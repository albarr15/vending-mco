package regular_vm;
import java.util.ArrayList;

public class RegularVM {
    private ArrayList<ItemSlot> listItemSlots = new ArrayList<ItemSlot>();
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
                System.out.println("Slot " + (i + 1) + " ( OUT OF STOCK )");
            }
            else {
                System.out.println("Slot " + (i + 1) + " (" + (listItemSlots.get(i)).getItem() + ")");
                System.out.println("Price : " + listItemSlots.get(i).getPrice());
            }
        }
    }

    public void makeTransaction() {
        Transaction transaction = new Transaction();
        this.currentTransaction = transaction;
    }

    public ArrayList<ItemSlot> getListItemSlots () {
        return this.listItemSlots;
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

        // add itemSlots to regularVM
        regularVM.addItemSlot(itemSlot1);
        regularVM.addItemSlot(itemSlot2);
        regularVM.addItemSlot(itemSlot3);
        regularVM.addItemSlot(itemSlot4);
        regularVM.addItemSlot(itemSlot5);
        regularVM.addItemSlot(itemSlot6);
        regularVM.addItemSlot(itemSlot7);
        regularVM.addItemSlot(itemSlot8);

        // set Item for each itemSlot
        itemSlot1.setItem(chashuPork);
        itemSlot2.setItem(chickenSlices);
        itemSlot3.setItem(fishCake);
        itemSlot4.setItem(ajitamago);
        itemSlot5.setItem(friedTofu);
        itemSlot6.setItem(seaweed);
        itemSlot7.setItem(corn);
        itemSlot8.setItem(butter);

        // fully restock all itemSlots
        for (int i = 0; i < regularVM.getListItemSlots().size(); i++) {
            regularVM.getListItemSlots().get(i).fullStockSlot();
        }

        regularVM.displayAllSlots();
    }
}

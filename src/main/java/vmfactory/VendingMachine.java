package vmfactory;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a vending machine.
 * <p>
 * Includes the list of item slots, its balance, the current transaction, and its maintenance.
 * Features include adding an item slot, making a transaction, making its maintenance,
 * displaying slot availability, displaying all slots, and displaying its menus.
 */
public class VendingMachine {
    protected ArrayList<ItemSlot> listItemSlots;
    protected Balance balance;
    protected Maintenance maintenance;
    protected Transaction currentTransaction;

    public VendingMachine() {
        this.listItemSlots = new ArrayList<>();
        this.balance = new Balance();
        this.maintenance = new Maintenance();
    }

    /**
     * Adds an itemSlot in the vending machine's list of itemSlots
     * @param itemSlot is the slot to be added in the list of item slots
     */
    public void addItemSlot(ItemSlot itemSlot) {
        listItemSlots.add(itemSlot);
    }

    /**
     * Creates a new instance of Transaction and assigns it to this
     */
    public void makeTransaction() {this.currentTransaction = new Transaction();}

    /**
     * Finds the itemSlot in which itemName is stored in
     * @param itemName is the name of the item to be searched
     * @return itemSlot if itemName is found in a slot
     *         null if itemName is not found in any existing slots
     */
    public ItemSlot findItemSlot(String itemName) {
        for (int i = 0; i < listItemSlots.size(); i++) {
            if (listItemSlots.get(i).getItemName().equals(itemName)) {
                return listItemSlots.get(i);
            }
        }
        System.out.println("ERROR : Item Slot for item " + itemName + " not found.");
        return null;
    }

    /**
     * Finds the itemSlot's index (from the current vending machine's list of Item Slots) in which itemName is stored in
     * @param itemName is the name of the item to be searched
     * @return i if itemName is found in a slot
     *         null if itemName is not found in any existing slots
     */
    public int findItemSlotIndex(String itemName) {
        for (int i = 0; i < listItemSlots.size(); i++) {
            if (listItemSlots.get(i).getItemName().equals(itemName)) {
                return i;
            }
        }
        System.out.println("ERROR : Item Slot for item " + itemName + " not found.");
        return -1;
    }

    public void displayAllSlots() {
        int size = listItemSlots.size();

        // prints each itemSlot with its corresponding stocked item
        for (int i = 0; i < size; i++) {
            if ((listItemSlots.get(i)).checkSlotAvailability()) {
                System.out.println("Slot " + (i + 1) + " ( OUT OF STOCK )");
            }
            else {
                System.out.printf("%-5s", "[" + listItemSlots.get(i).getItemStock() + "]");
                System.out.printf("%-10s", "Slot " + (i + 1));
                System.out.printf("%-20s", "(" + (listItemSlots.get(i)).getItemName() + ")");
                System.out.printf("%-15s", "Price : " + listItemSlots.get(i).getPrice());
                System.out.printf("%-12s", "Calories : " + listItemSlots.get(i).getItem().getCaloriesAmt());
                System.out.println();
            }
        }
    }

    public int displayTestVM(){
        System.out.println();
        System.out.println("- VENDING MACHINE TESTING -");
        System.out.println("[1] Vending Features");
        System.out.println("[2] Maintenance Features");

        Scanner sc1 = new Scanner(System.in);
        return sc1.nextInt();
    }

    public int displayTestVending() {
        System.out.println();
        System.out.println("- TEST VENDING FEATURES -");
        System.out.println("[1] Receive Payment");
        System.out.println("[2] Dispense Item");
        System.out.println("[3] Produce Change");
        System.out.println("[4] Exit");

        Scanner sc1 = new Scanner(System.in);
        return sc1.nextInt();
    }

    public int displayTestMaintenance() {
        System.out.println();
        System.out.println("- TEST MAINTENANCE FEATURES -");
        System.out.println("[1] Restock / Stock Item");
        System.out.println("[2] Set Price of Item");
        System.out.println("[3] Collect Payment");
        System.out.println("[4] Replenish Money");
        System.out.println("[5] Print Summary of Transactions");
        System.out.println("[6] Exit");

        Scanner sc1 = new Scanner(System.in);
        return sc1.nextInt();
    }

    public ArrayList<ItemSlot> getListItemSlots () {
        return this.listItemSlots;
    }

    public Balance getBalance() {
        return this.balance;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
}

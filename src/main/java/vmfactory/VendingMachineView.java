package vmfactory;

import java.util.ArrayList;
import java.util.Scanner;

public class VendingMachineView {
    public void displayAllSlots(ArrayList<ItemSlot> listItemSlots) {
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
}

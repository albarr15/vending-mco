package regular_vm;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class RegularVM {
    private ArrayList<ItemSlot> listItemSlots = new ArrayList<ItemSlot>();
    private Balance balance;
    private Transaction currentTransaction;
    private TestMenu testMenu;

    public void setTestMenu(TestMenu testMenu) {
        this.testMenu = testMenu;
    }

    public void addItemSlot(ItemSlot itemSlot) {
        listItemSlots.add(itemSlot);
    }

    public void displaySlotAvailability(ItemSlot itemSlot) {
        if (itemSlot.checkSlotAvailability()) {
            System.out.println("Slot with " + itemSlot.getItemName() + "items is NOT AVAILABLE.");
        }
        else {
            System.out.println("Slot with " + itemSlot.getItemName() + "items is AVAILABLE.");
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
                System.out.println("Slot " + (i + 1) + " (" + (listItemSlots.get(i)).getItemName() + ")");
                System.out.println("Price : " + listItemSlots.get(i).getPrice());
                System.out.println("Number of Items : " + listItemSlots.get(i).getItemStock());
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
    public ItemSlot findItemSlot(String itemName) {
        for (int i = 0; i < listItemSlots.size(); i++) {
            if (listItemSlots.get(i).getItemName().equals(itemName)) {
                return listItemSlots.get(i);
            }
        }
        return null;
    }

    public static int displayHomeMenu() {
        int input;
        do {
            System.out.println();
            System.out.println("VENDING MACHINE FACTORY PROGRAM");
            System.out.println();
            System.out.println("[1] Create a Vending Machine");
            System.out.println("[2] Test a Vending Machine");
            System.out.println("[3] Exit");
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            input = scanner.nextInt();
        } while (input != 1 & input != 2 & input != 3);

        return input;
    }

    public static int displayCreateVMMenu(){
        System.out.println();
        System.out.println("- VENDING MACHINE CREATION -");
        System.out.println("! Special Vending Machines are not yet available.");
        System.out.println("Proceed with creating Regular Vending Machine?");
        System.out.println("[1] Yes");
        System.out.println("[2] No");
        Scanner sc1 = new Scanner(System.in);
        return sc1.nextInt();
    }

    public static int displayTestVM(){
        System.out.println();
        System.out.println("- VENDING MACHINE TESTING -");
        System.out.println("[1] Vending Features");
        System.out.println("[2] Maintenance Features");

        Scanner sc1 = new Scanner(System.in);
        return sc1.nextInt();
    }

    public static int displayTestVending() {
        System.out.println();
        System.out.println("- TEST VENDING FEATURES -");
        System.out.println("[1] Receive Payment");
        System.out.println("[2] Dispense Item");
        System.out.println("[3] Produce Change");
        System.out.println("[4] Exit");

        Scanner sc1 = new Scanner(System.in);
        return sc1.nextInt();
    }

    public static int displayTestMaintenance() {
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

    public static void main(String[] args) {
        boolean isExit = false;
        RegularVM currentVM = null;
        RegularVM regularVM = null;

        do {
            int input = displayHomeMenu();

            if (input == 1) {
                // user opted for Vending Machine Creation

                int input1 = displayCreateVMMenu();
                if (input1 == 1) {
                    regularVM = new RegularVM();
                    currentVM = regularVM;
                    currentVM.setTestMenu(new TestMenu(currentVM));

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

                    regularVM.displayAllSlots();
                }
                else {
                    System.out.println("- Rejected Vending Machine Creation -");
                    System.out.println("Going back to Home Menu ...");
                }
            }
            else if (input == 2) {
                // user opted for Vending Machine Testing
                boolean isExitVMTest = false;

                // loop Test Menu until selected to exit
                do {
                    int input2 = displayTestVM();
                    if (currentVM == null) {
                        System.out.println("!!! There are no existing vending machines yet.");
                        System.out.println("- Please create a vending machine first - ");
                        isExitVMTest = true;
                    }
                    else {
                        currentVM.displayAllSlots();
                        if (input2 == 1) {
                            // user opted to test Vending Features
                            boolean isVFExit = false;
                            do {
                                // insert vending features here
                                isVFExit = true;
                            } while (!isVFExit);
                        }
                        else if (input2 == 2) {
                            // user opted to test Maintenance Features
                            int MFinput;
                            boolean isMFExit = false;
                            do {
                                MFinput = displayTestMaintenance();
                                Scanner MFscan = new Scanner(System.in);

                                switch (MFinput) {
                                    case 1:
                                        // Restock / Stock Item
                                        System.out.print("Enter item to stock: ");
                                        String itemName1 = MFscan.next();

                                        // find itemSlot with given item name
                                        ItemSlot itemSlot1 = currentVM.findItemSlot(itemName1);

                                        System.out.print("Enter number of items to stock: ");
                                        int numItems1 = MFscan.nextInt();

                                        currentVM.testMenu.stockItem(itemSlot1, numItems1);
                                        currentVM.displayAllSlots();
                                        break;
                                    case 2:
                                        // Set Price of Item
                                        System.out.print("Enter item to stock: ");
                                        String itemName2 = MFscan.next();

                                        // find itemSlot with given item name
                                        ItemSlot itemSlot2 = currentVM.findItemSlot(itemName2);

                                        System.out.print("Enter price: ");
                                        double price2 = MFscan.nextDouble();

                                        currentVM.testMenu.setPrice(itemSlot2, price2);
                                        currentVM.displayAllSlots();
                                        break;
                                    case 3:
                                        // Collect Payment
                                        break;
                                    case 4:
                                        // Replenish Money
                                        break;
                                    case 5:
                                        // Print Summary of Transactions
                                        currentVM.testMenu.printTransacSummary(currentVM.listItemSlots);
                                        break;
                                    case 6:
                                        // Exit
                                        isMFExit = true;
                                        break;
                                }
                            } while (!isMFExit); // suppose that the key to be entered for exit is 3
                            isExitVMTest = true;
                        }
                        else {
                            System.out.println("Invalid input. Please choose from 1 or 2 only.");
                        }
                    }
                } while (!isExitVMTest);

            }
            else if (input == 3) {
                // exit
                isExit = true;
            }
            else {
                System.out.println("Invalid input. Please choose from 1, 2, or 3 only.");
            }
        } while (!isExit);
    }
}

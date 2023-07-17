package vmfactory;

import java.util.Scanner;

public class VMFactory {

    public int displayHomeMenu() {
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

    public int displayCreateVMMenu(){
        int input1;
        do{
            System.out.println();
            System.out.println("- VENDING MACHINE CREATION -");

            System.out.println("[1] Regular Vending Machine");
            System.out.println("[2] Special Vending Machine");

            Scanner scanner = new Scanner(System.in);
            input1 = scanner.nextInt();
        } while (input1 != 1 & input1 != 2);
        return  input1;
    }

    public static void main(String[] args) {
        VMFactory vmFactory = new VMFactory();

        boolean isExit = false;
        RegularVM currentVM = null;

        do {
            int input = vmFactory.displayHomeMenu();

            switch (input) {
                case 1:
                    // user opted for Vending Machine Creation

                    int input1 = vmFactory.displayCreateVMMenu();
                    if (input1 == 1) {
                        RegularVM regularVM = new RegularVM();
                        currentVM = regularVM;

                        // Instantiate items
                        Item chashuPork = new Item("Chashu Pork", 99, 95,
                                "Topping with Chashu Pork ...");
                        Item chickenSlices = new Item("Chicken Slices", 95, 153,
                                "Topping with Chicken Slices ...");
                        Item fishCake = new Item("Fish Cake", 63, 40,
                                "Adding Fish Cakes ...");
                        Item ajitamago = new Item("Ajitamago", 72, 35,
                                "Adding Ajitamago ...");
                        Item friedTofu = new Item("Fried Tofu", 77, 14,
                                "Topping with Fried Tofu ...");
                        Item seaweed = new Item("Seaweed", 8, 16,
                                "Topping with Seaweed ...");
                        Item corn = new Item("Corn", 76, 40,
                                "Adding corn ...");
                        Item butter = new Item("Butter", 81, 1,
                                "Adding Butter ...");

                        // Instantiate itemSlots
                        ItemSlot itemSlot1 = new ItemSlot(chashuPork);
                        ItemSlot itemSlot2 = new ItemSlot(chickenSlices);
                        ItemSlot itemSlot3 = new ItemSlot(fishCake);
                        ItemSlot itemSlot4 = new ItemSlot(ajitamago);
                        ItemSlot itemSlot5 = new ItemSlot(friedTofu);
                        ItemSlot itemSlot6 = new ItemSlot(seaweed);
                        ItemSlot itemSlot7 = new ItemSlot(corn);
                        ItemSlot itemSlot8 = new ItemSlot(butter);

                        // add itemSlots to regularVM
                        regularVM.addItemSlot(itemSlot1);
                        regularVM.addItemSlot(itemSlot2);
                        regularVM.addItemSlot(itemSlot3);
                        regularVM.addItemSlot(itemSlot4);
                        regularVM.addItemSlot(itemSlot5);
                        regularVM.addItemSlot(itemSlot6);
                        regularVM.addItemSlot(itemSlot7);
                        regularVM.addItemSlot(itemSlot8);

                        regularVM.displayAllSlots();
                    } else {
                        SpecialVM specialVM = new SpecialVM();
                        currentVM = specialVM;

                        // Instantiate items
                        Item chashuPork = new Item("Chashu Pork", 99, 95,
                                "Topping with Chashu Pork ...");
                        Item chickenSlices = new Item("Chicken Slices", 95, 153,
                                "Topping with Chicken Slices ...");
                        Item fishCake = new Item("Fish Cake", 63, 40,
                                "Adding Fish Cakes ...");
                        Item ajitamago = new Item("Ajitamago", 72, 35,
                                "Adding Ajitamago ...");
                        Item friedTofu = new Item("Fried Tofu", 77, 14,
                                "Topping with Fried Tofu ...");
                        Item seaweed = new Item("Seaweed", 8, 16,
                                "Topping with Seaweed ...");
                        Item corn = new Item("Corn", 76, 40,
                                "Adding corn ...");
                        Item butter = new Item("Butter", 81, 1,
                                "Adding Butter ...");

                        // Instantiate itemSlots
                        ItemSlot itemSlot1 = new ItemSlot(chashuPork);
                        ItemSlot itemSlot2 = new ItemSlot(chickenSlices);
                        ItemSlot itemSlot3 = new ItemSlot(fishCake);
                        ItemSlot itemSlot4 = new ItemSlot(ajitamago);
                        ItemSlot itemSlot5 = new ItemSlot(friedTofu);
                        ItemSlot itemSlot6 = new ItemSlot(seaweed);
                        ItemSlot itemSlot7 = new ItemSlot(corn);
                        ItemSlot itemSlot8 = new ItemSlot(butter);

                        // add itemSlots to regularVM
                        specialVM.addItemSlot(itemSlot1);
                        specialVM.addItemSlot(itemSlot2);
                        specialVM.addItemSlot(itemSlot3);
                        specialVM.addItemSlot(itemSlot4);
                        specialVM.addItemSlot(itemSlot5);
                        specialVM.addItemSlot(itemSlot6);
                        specialVM.addItemSlot(itemSlot7);
                        specialVM.addItemSlot(itemSlot8);

                        specialVM.displayAllSlots();
                    }
                    break;

                case 2:
                    // user opted for Vending Machine Testing
                    boolean isExitVMTest = false;

                    // loop Test Menu until selected to exit
                    do {
                        if (currentVM == null) {
                            System.out.println("!!! There are no existing vending machines yet.");
                            System.out.println("- Please create a vending machine first - ");
                            isExitVMTest = true;
                        } else {
                            int input2 = currentVM.displayTestVM();
                            if (input2 == 1) {
                                // user opted to test Vending Features
                                int VFinput;
                                boolean isVFExit = false;
                                currentVM.makeTransaction();

                                do {
                                    VFinput = currentVM.displayTestVending();
                                    Scanner VFscan = new Scanner(System.in);

                                    switch (VFinput) {
                                        case 1:
                                            // Receive Payment
                                            currentVM.getCurrentTransaction().receivePayment(currentVM.getBalance());

                                            break;
                                        case 2:
                                            // Dispense Item
                                            boolean isSuccessful = false;

                                            System.out.println("Please choose an item from the menu below ...");
                                            currentVM.displayAllSlots();

                                            String itemName2 = VFscan.nextLine();

                                            // find itemSlot with given item name
                                            ItemSlot itemSlot2 = currentVM.findItemSlot(itemName2);

                                            try {
                                                isSuccessful = currentVM.getCurrentTransaction().selectItem(itemSlot2);
                                                currentVM.getListItemSlots().get(currentVM.findItemSlotIndex(itemName2)).dispenseItem();
                                            } catch (NullPointerException e) {
                                                System.out.println("Error: Item not found");
                                            }

                                            if (!isSuccessful) {
                                                currentVM.getCurrentTransaction().produceChange(currentVM.getBalance());
                                            }

                                            currentVM.getCurrentTransaction().reset(currentVM.getListItemSlots());
                                            break;
                                        case 3:
                                            // Produce Change
                                            currentVM.getCurrentTransaction().produceChange(currentVM.getBalance());
                                            break;
                                        case 4:
                                            // Exit
                                            isVFExit = true;
                                            break;
                                    }
                                } while (!isVFExit);
                                isExitVMTest = true;

                            } else if (input2 == 2) {
                                // user opted to test getMaintenance() Features
                                int MFinput;
                                boolean isMFExit = false;
                                do {
                                    MFinput = currentVM.displayTestMaintenance();
                                    Scanner MFscan = new Scanner(System.in);

                                    switch (MFinput) {
                                        case 1:
                                            // Restock / Stock Item
                                            System.out.print("Enter item to stock: ");
                                            String itemName1 = MFscan.nextLine();

                                            // find itemSlot with given item name
                                            ItemSlot itemSlot1 = currentVM.findItemSlot(itemName1);

                                            System.out.print("Enter number of items to stock: ");
                                            int numItems1 = MFscan.nextInt();

                                            currentVM.getMaintenance().stockItem(itemSlot1, numItems1);
                                            currentVM.displayAllSlots();
                                            break;
                                        case 2:
                                            // Set Price of Item
                                            boolean isValidPrice = false;

                                            System.out.print("Enter item to price: ");
                                            String itemName2 = MFscan.nextLine();

                                            // find itemSlot with given item name
                                            ItemSlot itemSlot2 = currentVM.findItemSlot(itemName2);

                                            if (itemSlot2 != null) {

                                                int price2 = 0;

                                                while (!isValidPrice) {
                                                    System.out.print("Enter price: ");
                                                    String sPrice2 = MFscan.next();
                                                    try {
                                                        price2 = Integer.parseInt(sPrice2);
                                                        if (price2 < 0) {
                                                            System.out.println("Invalid price. " +
                                                                    "Please enter a positive integer.");
                                                        }
                                                        else {
                                                            isValidPrice = true;
                                                        }
                                                    }
                                                    catch (NumberFormatException e) {
                                                        System.out.println("Invalid price. Please enter an integer.");
                                                    }
                                                }

                                                currentVM.getMaintenance().setPrice(itemSlot2, price2);
                                                currentVM.displayAllSlots();
                                            }
                                            break;
                                        case 3:
                                            // Collect Payment
                                            System.out.println("[1] Collect all money");
                                            System.out.println("[2] Collect specific amount of money");

                                            int input3 = MFscan.nextInt();

                                            switch (input3) {
                                                case 1:
                                                    int collectedMoney1 =
                                                            currentVM.getMaintenance().collectMoney(currentVM.getBalance());
                                                    System.out.println("Collected Money : " + collectedMoney1);
                                                    System.out.println("Remaining balance : " + currentVM.getBalance().getCurrentBal());
                                                    break;
                                                case 2:
                                                    System.out.print("Enter specific amount to be collected: ");
                                                    int amount = MFscan.nextInt();
                                                    int collectedMoney2 =
                                                            currentVM.getMaintenance().collectMoney(currentVM.getBalance(), amount);
                                                    System.out.println("Collected Money : " + collectedMoney2);
                                                    System.out.println("Remaining balance : " + currentVM.getBalance().getCurrentBal());
                                                    break;
                                            }
                                            break;
                                        case 4:
                                            // Replenish Money
                                            System.out.println("Current Balance : " + currentVM.getBalance().getCurrentBal());
                                            System.out.println("[1] Replenish with default stock");
                                            System.out.println("[2] Replenish with entered cash");

                                            int input4 = MFscan.nextInt();

                                            if (input4 == 1) {
                                                System.out.println("Replenishing money with default stock ...");
                                                currentVM.getMaintenance().replenishMoney(currentVM.getBalance());
                                            } else if (input4 == 2) {
                                                System.out.println("Enter money to be deposited: ");
                                                System.out.println("Sample Format: '5 100 500 1000'");

                                                Scanner cashScanner = new Scanner(System.in).useDelimiter("\n");
                                                String cashList = cashScanner.next();

                                                currentVM.getMaintenance().replenishMoney(currentVM.getBalance(), cashList);

                                                System.out.println("Replenishing money with entered cash ...");
                                            }

                                            System.out.println("Current Balance : " + currentVM.getBalance().getCurrentBal());
                                            break;
                                        case 5:
                                            // Print Summary of Transactions
                                            currentVM.getMaintenance().printTransacSummary(currentVM.getListItemSlots());
                                            break;
                                        case 6:
                                            // Exit
                                            isMFExit = true;
                                            break;
                                    }
                                } while (!isMFExit);
                                isExitVMTest = true;
                            } else {
                                System.out.println("Invalid input. Please choose from 1 or 2 only.");
                            }
                        }
                    } while (!isExitVMTest);

                    break;

                case 3:
                    // exit
                    isExit = true;
                    break;

                default:
                    System.out.println("Invalid input. Please choose from 1, 2, or 3 only.");
                    break;

            }
        } while (!isExit);
    }
}

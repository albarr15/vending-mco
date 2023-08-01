package vmfactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Represents the View component in the MVC architecture for the VMFactory program.
 * It handles the user interface of the program.
 * <p>
 * Contains dedicated frames for each major feature such as: Main Menu frame, Vending Machine Creation Frame, VM Testing Frame,
 * Vending Features Frame (covers the checkout and special item frames), and Vending Maintenance Frame (covers the restock, set price,
 * collect payment, replenish money, and print transaction summary frames).
 */
public class VMFactoryView {
    private JFrame mainFrame, vmCreationFrame, vmTestingFrame, vFeaturesFrame, checkoutFrame, specialFrame,
            vMaintenanceFrame, restockFrame, setPriceFrame, collectPayFrame, replenishMoneyFrame, printTransacSummaryFrame;
    private JLabel appHeader, vmCHeader, vmTHeader, vmError, vFeaturesHeader, vMaintenanceHeader,
            vFeaturesSelected, buyItemLabel, vFeaturesMoneyLabel, vFeaturesError, vFeaturesChange, componentPrep,
            restockItemNameLbl, restockItemNumLbl, restockErrorLbl, setPriceLbl, setPriceItemNameLbl, setPriceErrorLbl,
            collectPayCurBalLbl, collectPaySpecLbl, collectPayErrorLbl,
            replenishMoneyErrorLbl, replenishMoneySpecLbl, replenishMoneyCurBalLbl;
    private JButton vmCreationBtn, vmTestBtn, vmExitBtn, regVMCreateBtn, spcVMCreateBtn,
            vFeaturesBtn, selectItemBut, vFeaturesCheckOut, vFeaturesCancel, specialBtn, removeBut,
            finishBtn = new JButton("Finish"), specialCancelBtn = new JButton("Cancel"),
            vMaintenanceBtn, restockBtn, setPriceBtn, collectPayBtn, replenishMoneyBtn, printTransacSummaryBtn, backMaintenanceBtn,
            finishRestockBtn = new JButton("Restock"), backRestockBtn,
            finishSetPriceBtn = new JButton("Set Price"), backSetPriceBtn,
            collectPayAllBtn = new JButton("Collect all money"), collectPaySpecBtn = new JButton("Collect specified amount"),
            finishCollectPayBtn, backCollectPayBtn, replenishMoneyDefBtn = new JButton("Replenish with default stock"),
            finishReplenishMoneyBtn = new JButton("Replenish"), backReplenishMoneyBtn,
            backPrintTransacSummaryBtn;
    private ArrayList<JButton> listSelectItem = new ArrayList<JButton>(), listComponents = new ArrayList<JButton>(), listRemove = new ArrayList<JButton>();
    private JTextField vFeaturesMoneyField, restockItemName, restockItemNum, setPriceItemName, setPriceItem, collectPaySpecTF,
            replenishMoneySpecTF;
    private JTextArea restockItemsList, setPriceItemsList, printTransacSummaryTA;
    private JPanel restockPanel1, restockPanel2, setPricePanel1, setPricePanel2;
    private boolean specialReturn = false;

    public VMFactoryView() {
        this.mainFrame = new JFrame("VMFactory GUI");

        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.mainFrame.setSize(380, 500);

        this.appHeader = new JLabel("VENDING MACHINE FACTORY");

        this.vmCreationBtn = new JButton("Create a Vending Machine");
        this.vmCreationBtn.setPreferredSize(new Dimension(200, 50));
        this.vmTestBtn = new JButton("Test a Vending Machine");
        this.vmTestBtn.setPreferredSize(new Dimension(200, 50));
        this.vmExitBtn = new JButton("Exit");
        this.vmExitBtn.setPreferredSize(new Dimension(200, 50));
        this.vmError = new JLabel("");

        this.mainFrame.add(appHeader);
        this.mainFrame.add(vmCreationBtn);
        this.mainFrame.add(vmTestBtn);
        this.mainFrame.add(vmExitBtn);
        this.mainFrame.add(Box.createRigidArea(new Dimension(300, 10)));
        this.mainFrame.add(vmError);

        this.mainFrame.setVisible(true);

        // code for VM Creation Frame
        this.vmCreationFrame = new JFrame("Vending Machine Creation");
        this.vmCreationFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.vmCHeader = new JLabel("VENDING MACHINE CREATION");

        this.regVMCreateBtn = new JButton("Regular Vending Machine");
        this.regVMCreateBtn.setPreferredSize(new Dimension(200, 50));
        this.spcVMCreateBtn = new JButton("Special Vending Machine");
        this.spcVMCreateBtn.setPreferredSize(new Dimension(200, 50));

        this.vmCreationFrame.add(vmCHeader);
        this.vmCreationFrame.add(regVMCreateBtn);
        this.vmCreationFrame.add(spcVMCreateBtn);

        // code for VM Testing Frame
        this.vmTestingFrame = new JFrame("Vending Machine Testing");
        this.vmTestingFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.vmTHeader = new JLabel("VENDING MACHINE TESTING");

        this.vFeaturesBtn = new JButton("Vending Features");
        this.vFeaturesBtn.setPreferredSize(new Dimension(200, 50));
        this.vMaintenanceBtn = new JButton("Maintenance Features");
        this.vMaintenanceBtn.setPreferredSize(new Dimension(200, 50));

        this.vmTestingFrame.add(vmTHeader);
        this.vmTestingFrame.add(vFeaturesBtn);
        this.vmTestingFrame.add(vMaintenanceBtn);
    }

    /**
     * Sets up the vending features frame wherein the user is shown the list of items available
     * with their properties as well as prompt the user for the cash to be entered and check out
     * @param currentVM is the most recently created vending machine
     */
    public void setupVFeaturesFrame(VendingMachine currentVM) {
        // Header
        this.vFeaturesFrame = new JFrame("Vending Features Testing");
        this.vFeaturesFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        if (currentVM instanceof SpecialVM)
            this.vFeaturesHeader = new JLabel("SPECIAL VENDING FEATURES TESTING");
        else this.vFeaturesHeader = new JLabel("REGULAR VENDING FEATURES TESTING");

        this.vFeaturesFrame.add(vFeaturesHeader);
        this.vFeaturesFrame.add(Box.createRigidArea(new Dimension(300, 10)));

        // Vending machine options
        this.listSelectItem.clear();
        for (ItemSlot slot : currentVM.getListItemSlots()) {
            if (!(currentVM instanceof SpecialVM) || slot.getForSale()) {
                this.selectItemBut = new JButton(slot.getItemName());
                selectItemBut.setPreferredSize(new Dimension(150, 25));
                this.buyItemLabel = new JLabel("Stock: " + slot.getItemStock() + "  /  Price: "
                        + slot.getPrice() + "  /  Calories: " + slot.getItem().getCaloriesAmt());
                this.vFeaturesFrame.add(selectItemBut);
                this.vFeaturesFrame.add(buyItemLabel);
                this.listSelectItem.add(selectItemBut);
            } else if (slot.getItem() instanceof SpecialItem) {
                this.specialBtn = new JButton(slot.getItemName());
                specialBtn.setPreferredSize(new Dimension(150, 25));
                this.buyItemLabel = new JLabel("Special Item : Build-it-yourself!");
                this.vFeaturesFrame.add(specialBtn);
                this.vFeaturesFrame.add(buyItemLabel);
            }
        }
        this.vFeaturesFrame.add(Box.createRigidArea(new Dimension(300, 10)));

        // Selected item display
        this.vFeaturesSelected = new JLabel("Selected Item: ");

        // Money input
        this.vFeaturesMoneyLabel = new JLabel("Input money in proper denominations:");
        this.vFeaturesMoneyField = new JTextField(30);

        // Check out or cancel
        this.vFeaturesCheckOut = new JButton("Check Out");
        vFeaturesCheckOut.setPreferredSize(new Dimension(150, 25));
        this.vFeaturesCancel = new JButton("Cancel");
        vFeaturesCancel.setPreferredSize(new Dimension(150, 25));
        this.vFeaturesCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createVmTestingFrame(getVmTestingFrame());
                vFeaturesFrame.dispose();
            }
        });
        this.vFeaturesError = new JLabel("");
        this.vFeaturesError.setPreferredSize(new Dimension(300, 15));
        this.vFeaturesError.setHorizontalAlignment(SwingConstants.CENTER);
        this.vFeaturesChange = new JLabel("");

        this.vFeaturesFrame.add(vFeaturesSelected);
        this.vFeaturesFrame.add(Box.createRigidArea(new Dimension(300, 10)));
        this.vFeaturesFrame.add(vFeaturesMoneyLabel);
        this.vFeaturesFrame.add(vFeaturesMoneyField);
        this.vFeaturesFrame.add(Box.createRigidArea(new Dimension(300, 10)));
        this.vFeaturesFrame.add(vFeaturesCheckOut);
        this.vFeaturesFrame.add(vFeaturesCancel);
        this.vFeaturesFrame.add(vFeaturesError);
        this.vFeaturesFrame.add(vFeaturesChange);
    }

    /**
     * Sets up the Special Item frame wherein the user is shown the different options for
     * customizing the special Item as well as show the corresponding total price and calories
     * @param currentVM is the most recently created vending machine
     */
    public void setupSpecialFrame(VendingMachine currentVM) {
        // Header
        this.specialFrame = new JFrame("Special Item Builder");
        this.specialFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.vFeaturesHeader = new JLabel("SPECIAL ITEM BUILDER");

        this.specialFrame.add(vFeaturesHeader);
        this.specialFrame.add(Box.createRigidArea(new Dimension(300, 10)));
        // Component options
        this.listComponents.clear();
        for (ItemSlot slot : currentVM.getListItemSlots()) {
            if (!(slot.getItem() instanceof SpecialItem)) {
                this.selectItemBut = new JButton("Add " + slot.getItemName());
                selectItemBut.setPreferredSize(new Dimension(150, 25));
                this.buyItemLabel = new JLabel("Stock: " + slot.getItemStock() +
                        "  /  Price: " + slot.getPrice() +
                        "  /  Calories: " + slot.getItem().getCaloriesAmt());
                this.specialFrame.add(selectItemBut);
                this.specialFrame.add(buyItemLabel);
                this.listComponents.add(selectItemBut);
            }
        }
        this.specialFrame.add(Box.createRigidArea(new Dimension(300, 10)));
        // Current special item
        this.vFeaturesSelected = new JLabel("Current ramen components (click to remove): ");
        this.specialFrame.add(vFeaturesSelected);
        this.listRemove.clear();
        for (Item item : ((SpecialItem) currentVM.getCurrentTransaction().getItemOrdered()).getListComponents()) {
            this.removeBut = new JButton(item.getName());
            removeBut.setPreferredSize(new Dimension(100, 25));
            this.specialFrame.add(removeBut);
            this.listRemove.add(removeBut);
        }
        this.specialFrame.add(Box.createRigidArea(new Dimension(300, 10)));
        this.vFeaturesMoneyLabel = new JLabel("Current total : " + currentVM.getCurrentTransaction().getItemOrdered().getPrice() +
                "  /  Current calories : " + currentVM.getCurrentTransaction().getItemOrdered().getCaloriesAmt());

        // Finish or cancel
        finishBtn.setPreferredSize(new Dimension(150, 25));
        this.finishBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                specialReturn = true;
                System.out.println("Special return true");
            }
        });
        specialCancelBtn.setPreferredSize(new Dimension(150, 25));

        this.vFeaturesError = new JLabel("");
        this.vFeaturesError.setPreferredSize(new Dimension(300, 15));
        this.vFeaturesError.setHorizontalAlignment(SwingConstants.CENTER);

        this.specialFrame.add(vFeaturesMoneyLabel);
        this.specialFrame.add(Box.createRigidArea(new Dimension(380, 10)));
        this.specialFrame.add(finishBtn);
        this.specialFrame.add(specialCancelBtn);
        this.specialFrame.add(vFeaturesError);
    }

    /**
     * Sets up the checkout frame which shows the change and preparation message if checking out is successful,
     * and feedback if unsuccessful
     * @param itemOrdered is the item selected by the user to be checked out
     */
    public void setupCheckoutFrame(Item itemOrdered) {
        // Header
        this.checkoutFrame = new JFrame("Checkout");
        this.checkoutFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.vFeaturesHeader = new JLabel("CHECKOUT");
        this.checkoutFrame.add(vFeaturesHeader);
        this.checkoutFrame.add(Box.createRigidArea(new Dimension(300, 10)));

        // Special Item preparation
        if (itemOrdered instanceof SpecialItem) {
            for (Item item : ((SpecialItem) itemOrdered).getListComponents()) {
                this.componentPrep = new JLabel(item.getPrepMessage());
                this.componentPrep.setPreferredSize(new Dimension(300, 15));
                this.componentPrep.setHorizontalAlignment(SwingConstants.CENTER);
                this.checkoutFrame.add(componentPrep);
            }
            this.componentPrep = new JLabel(itemOrdered.getName() + " done!");
            this.componentPrep.setPreferredSize(new Dimension(300, 15));
            this.componentPrep.setHorizontalAlignment(SwingConstants.CENTER);
            this.checkoutFrame.add(componentPrep);
            this.checkoutFrame.add(Box.createRigidArea(new Dimension(300, 10)));
        }

        // Selected item display
        this.vFeaturesSelected = new JLabel("");
        this.vFeaturesSelected.setPreferredSize(new Dimension(380, 20));
        this.vFeaturesSelected.setHorizontalAlignment(SwingConstants.CENTER);
        this.vFeaturesChange = new JLabel("");

        // Exit
        this.vFeaturesCancel = new JButton("Done");
        vFeaturesCancel.setPreferredSize(new Dimension(150, 25));
        this.vFeaturesCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createVmTestingFrame(getVmTestingFrame());
                checkoutFrame.dispose();
            }
        });

        this.checkoutFrame.add(vFeaturesSelected);
        this.checkoutFrame.add(vFeaturesChange);
        this.checkoutFrame.add(Box.createRigidArea(new Dimension(300, 10)));
        this.checkoutFrame.add(vFeaturesCancel);
    }

    /**
     * Sets up the vending machine maintenance frame with buttons for each feature (restocking, setting price,
     * collecting money, replenishing money, and printing summary of transactions)
     * @param currentVM is the most recently created vending machine
     */
    public void setupVMaintenanceFrame(VendingMachine currentVM) {
        // Header
        this.vMaintenanceFrame = new JFrame("Test Maintenance Features");
        this.vMaintenanceFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.vMaintenanceHeader = new JLabel("TEST MAINTENANCE FEATURES");
        this.vMaintenanceFrame.add(vMaintenanceHeader);

        this.restockBtn = new JButton("Restock / Stock Item");
        this.restockBtn.setPreferredSize(new Dimension(200, 50));
        this.restockBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createRestockFrame(getRestockFrame(), currentVM);
                vMaintenanceFrame.dispose();
            }
        });

        this.setPriceBtn = new JButton("Set Price of Item");
        this.setPriceBtn.setPreferredSize(new Dimension(200, 50));
        this.setPriceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createSetPriceFrame(getSetPriceFrame(), currentVM);
                vMaintenanceFrame.dispose();
            }
        });

        this.collectPayBtn = new JButton("Collect Payment");
        this.collectPayBtn.setPreferredSize(new Dimension(200, 50));
        this.collectPayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createCollectPayFrame(getCollectPayFrame(), currentVM);
                vMaintenanceFrame.dispose();
            }
        });

        this.replenishMoneyBtn = new JButton("Replenish Money");
        this.replenishMoneyBtn.setPreferredSize(new Dimension(200, 50));
        this.replenishMoneyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createReplenishMoneyFrame(getReplenishMoneyFrame(), currentVM);
                vMaintenanceFrame.dispose();
            }
        });

        this.printTransacSummaryBtn = new JButton("Print Transactions Summary");
        this.printTransacSummaryBtn.setPreferredSize(new Dimension(200, 50));
        this.printTransacSummaryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createPrintTransacSummaryFrame(getPrintTransacSummaryFrame(), currentVM);
                getPrintTransacSummaryTA().setText(currentVM.getMaintenance().printTransacSummary(currentVM.getListItemSlots()));
                vMaintenanceFrame.dispose();
            }
        });

        this.backMaintenanceBtn = new JButton("Back");
        this.backMaintenanceBtn.setPreferredSize(new Dimension(200, 50));
        this.backMaintenanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vMaintenanceFrame.dispose();
                createVmTestingFrame(vmTestingFrame);
            }
        });

        this.vMaintenanceFrame.add(restockBtn);
        this.vMaintenanceFrame.add(setPriceBtn);
        this.vMaintenanceFrame.add(collectPayBtn);
        this.vMaintenanceFrame.add(replenishMoneyBtn);
        this.vMaintenanceFrame.add(printTransacSummaryBtn);
        this.vMaintenanceFrame.add(backMaintenanceBtn);
    }

    /**
     * Sets up the Restock frame wherein the user is shown the current list of all slots
     * and prompted for the item name and the number of items to stock
     * @param currentVM is the most recently created vending machine
     */
    public void setupRestockFrame(VendingMachine currentVM) {
        // Header
        this.restockFrame = new JFrame("Restock Item");
        this.restockFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.restockItemName = new JTextField(30);
        this.restockItemNum = new JTextField(30);

        this.restockItemNameLbl = new JLabel("Enter Item Name to stock:      ");
        this.restockItemNumLbl = new JLabel("Enter Number of items to stock: ");

        this.restockItemsList = new JTextArea(" ");
        this.restockItemsList.setPreferredSize(new Dimension(360, 250));
        this.restockItemsList.setText(currentVM.displayAllSlots());
        this.restockItemsList.setEditable(false);

        this.restockItemName = new JTextField();
        this.restockItemName.setColumns(10);
        this.restockItemNum = new JTextField();
        this.restockItemNum.setColumns(10);

        this.finishRestockBtn.setPreferredSize(new Dimension(200, 50));
        this.backRestockBtn = new JButton("Back");
        this.backRestockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restockFrame.dispose();
                createVMaintenanceFrame(vMaintenanceFrame, currentVM);
            }
        });
        this.restockErrorLbl = new JLabel("", SwingConstants.CENTER);
        this.restockErrorLbl.setPreferredSize(new Dimension(350, 30));

        this.restockPanel1 = new JPanel();
        this.restockPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.restockPanel1.add(restockItemNameLbl);
        this.restockPanel1.add(restockItemName);

        this.restockPanel2 = new JPanel();
        this.restockPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.restockPanel2.add(restockItemNumLbl);
        this.restockPanel2.add(restockItemNum);

        this.restockFrame.add(restockPanel1);
        this.restockFrame.add(restockPanel2);
        this.restockFrame.add(finishRestockBtn);
        this.restockFrame.add(restockErrorLbl);
        this.restockFrame.add(restockItemsList);
        this.restockFrame.add(backRestockBtn);
    }

    /**
     * Sets up the Set Price frame wherein the user is shown the current list of all slots
     * and prompted for the item name and the price to be assigned
     * @param currentVM is the most recently created vending machine
     */
    public void setupSetPriceFrame(VendingMachine currentVM) {
        // Header
        this.setPriceFrame = new JFrame("Set Price of Item");
        this.setPriceFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.setPriceItemName = new JTextField(30);
        this.setPriceItem = new JTextField(30);

        this.setPriceItemNameLbl = new JLabel("Enter Item Name to set price:      ");
        this.setPriceLbl = new JLabel("Enter Price of item: ");
        this.setPriceErrorLbl = new JLabel("", SwingConstants.CENTER);
        this.setPriceErrorLbl.setPreferredSize(new Dimension(350, 30));

        this.setPriceItemsList = new JTextArea(" ");
        this.setPriceItemsList.setPreferredSize(new Dimension(330, 230));
        this.setPriceItemsList.setText(currentVM.displayAllSlots());
        this.setPriceItemsList.setEditable(false);

        this.setPriceItemName = new JTextField();
        this.setPriceItemName.setColumns(10);
        this.setPriceItem = new JTextField();
        this.setPriceItem.setColumns(10);

        this.finishSetPriceBtn.setPreferredSize(new Dimension(200, 50));

        this.setPricePanel1 = new JPanel();
        this.setPricePanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPricePanel1.add(setPriceItemNameLbl);
        this.setPricePanel1.add(setPriceItemName);

        this.setPricePanel2 = new JPanel();
        this.setPricePanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPricePanel2.add(setPriceLbl);
        this.setPricePanel2.add(setPriceItem);

        this.backSetPriceBtn = new JButton("Back");
        this.backSetPriceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPriceFrame.dispose();
                createVMaintenanceFrame(vMaintenanceFrame, currentVM);
            }
        });

        this.setPriceFrame.add(setPricePanel1);
        this.setPriceFrame.add(setPricePanel2);
        this.setPriceFrame.add(finishSetPriceBtn);
        this.setPriceFrame.add(setPriceErrorLbl);
        this.setPriceFrame.add(setPriceItemsList);
        this.setPriceFrame.add(backSetPriceBtn);
    }

    /**
     * Sets up the Collect pay frame wherein the user is shown the current balance
     * and prompted for either a specific amount to collect or collect all money in balance
     * @param currentVM is the most recently created vending machine
     */
    public void setupCollectPayFrame(VendingMachine currentVM) {
        // Header
        this.collectPayFrame = new JFrame("Collect Payment");
        this.collectPayFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.collectPayErrorLbl = new JLabel("", SwingConstants.CENTER);
        this.collectPayErrorLbl.setPreferredSize(new Dimension(350, 30));

        this.collectPayCurBalLbl = new JLabel("");
        this.collectPayCurBalLbl.setText("Current balance : " + String.valueOf(currentVM.getBalance().getCurrentBal()));
        this.collectPayCurBalLbl.setPreferredSize(new Dimension(220, 90));

        this.collectPayAllBtn.setPreferredSize(new Dimension(200, 50));

        this.collectPaySpecLbl = new JLabel("Enter specific amount:  ");
        this.collectPaySpecLbl.setPreferredSize(new Dimension(220, 30));

        this.collectPaySpecTF = new JTextField(10);
        ;
        this.collectPaySpecBtn.setPreferredSize(new Dimension(200, 50));

        this.backCollectPayBtn = new JButton("Back");
        this.backCollectPayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collectPayFrame.dispose();
                createVMaintenanceFrame(vMaintenanceFrame, currentVM);
            }
        });

        this.collectPayFrame.add(collectPayCurBalLbl);
        this.collectPayFrame.add(collectPaySpecLbl);
        this.collectPayFrame.add(collectPaySpecTF);
        this.collectPayFrame.add(collectPaySpecBtn);
        this.collectPayFrame.add(collectPayAllBtn);
        this.collectPayFrame.add(collectPayErrorLbl);
        this.collectPayFrame.add(backCollectPayBtn);
    }

    /**
     * Sets up the Replenish money frame wherein the user is shown the current balance
     * and prompted for either a specific amount to replenish or replenish balance using default cash stock
     * @param currentVM is the most recently created vending machine
     */
    public void setupReplenishMoneyFrame(VendingMachine currentVM) {
        // Header
        this.replenishMoneyFrame = new JFrame("Replenish Money");
        this.replenishMoneyFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.replenishMoneyErrorLbl = new JLabel();
        this.replenishMoneyErrorLbl.setPreferredSize(new Dimension(360, 50));

        this.replenishMoneyCurBalLbl = new JLabel("");
        this.replenishMoneyCurBalLbl.setText("Current Balance: " + String.valueOf(currentVM.getBalance().getCurrentBal()));
        this.replenishMoneyCurBalLbl.setPreferredSize(new Dimension(220, 30));

        this.replenishMoneyDefBtn.setPreferredSize(new Dimension(200, 50));

        this.replenishMoneySpecLbl = new JLabel("Enter specific amount:  ");
        this.replenishMoneySpecLbl.setPreferredSize(new Dimension(200, 30));

        this.replenishMoneySpecTF = new JTextField(30);

        this.finishReplenishMoneyBtn.setPreferredSize(new Dimension(200, 50));

        this.backReplenishMoneyBtn = new JButton("Back");
        this.backReplenishMoneyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replenishMoneyFrame.dispose();
                createVMaintenanceFrame(vMaintenanceFrame, currentVM);
            }
        });

        this.replenishMoneyFrame.add(replenishMoneyCurBalLbl);
        this.replenishMoneyFrame.add(replenishMoneySpecLbl);
        this.replenishMoneyFrame.add(replenishMoneySpecTF);
        this.replenishMoneyFrame.add(finishReplenishMoneyBtn);
        this.replenishMoneyFrame.add(replenishMoneyDefBtn);
        this.replenishMoneyFrame.add(replenishMoneyErrorLbl);
        this.replenishMoneyFrame.add(backReplenishMoneyBtn);
    }

    /**
     * Sets up the Print Transaction Summary frame wherein the user is all previous transactions
     * @param currentVM is the most recently created vending machine
     */
    public void setupPrintTransacSummaryFrame(VendingMachine currentVM) {
        // Header
        this.printTransacSummaryFrame = new JFrame("Print Transaction Summary");
        this.printTransacSummaryFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.printTransacSummaryTA = new JTextArea(" ");
        this.printTransacSummaryTA.setPreferredSize(new Dimension(330, 400));
        this.printTransacSummaryTA.setEditable(false);

        this.backPrintTransacSummaryBtn = new JButton("Back");
        this.backPrintTransacSummaryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTransacSummaryFrame.dispose();
                createVMaintenanceFrame(vMaintenanceFrame, currentVM);
            }
        });

        this.printTransacSummaryFrame.add(printTransacSummaryTA);
        this.printTransacSummaryFrame.add(backPrintTransacSummaryBtn);
    }

    // main menu + vm creation screens
    public void setVmCreationBtnListener(ActionListener vmCreationBtnListener) {
        this.vmCreationBtn.addActionListener(vmCreationBtnListener);
    }

    public void setregVmCreateBtnListener(ActionListener actionListener) {
        this.regVMCreateBtn.addActionListener(actionListener);
    }

    public void setspcVmCreateBtnListener(ActionListener actionListener) {
        this.spcVMCreateBtn.addActionListener(actionListener);
    }

    public void setVmTestBtnListener(ActionListener actionListener) {
        this.vmTestBtn.addActionListener(actionListener);
    }

    // vending features testing
    public void setVFeaturesBtnListener(ActionListener actionListener) {
        this.vFeaturesBtn.addActionListener(actionListener);
        this.specialCancelBtn.addActionListener(actionListener);
        this.finishBtn.addActionListener(actionListener);
    }

    public void setSelectItemBtnListener(ArrayList<ActionListener> actionListener) {
        for (int i = 0; i < listSelectItem.size(); i++)
            listSelectItem.get(i).addActionListener(actionListener.get(i));
    }

    public void setCheckOutBtnListener(ActionListener actionListener) {
        this.vFeaturesCheckOut.addActionListener(actionListener);
    }

    public void setSpecialBtnListener(ActionListener actionListener) {
        this.specialBtn.addActionListener(actionListener);
    }

    public void setComponentsBtnListener(ArrayList<ActionListener> actionListener) {
        for (int i = 0; i < listComponents.size(); i++)
            listComponents.get(i).addActionListener(actionListener.get(i));
    }

    public void setRemoveBtnListener(ArrayList<ActionListener> actionListener) {
        for (int i = 0; i < listRemove.size(); i++)
            listRemove.get(i).addActionListener(actionListener.get(i));
    }

    // maintenance testing
    public void setvMaintenanceBtnListener(ActionListener actionListener) {
        this.vMaintenanceBtn.addActionListener(actionListener);
    }

    public void setFinishRestockBtnListener(ActionListener actionListener) {
        this.finishRestockBtn.addActionListener(actionListener);
    }

    public void setFinishSetPriceBtnListener(ActionListener actionListener) {
        this.finishSetPriceBtn.addActionListener(actionListener);
    }

    public void setCollectPaySpecBtnListener(ActionListener actionListener) {
        this.collectPaySpecBtn.addActionListener(actionListener);
    }

    public void setCollectPayAllBtnListener(ActionListener actionListener) {
        this.collectPayAllBtn.addActionListener(actionListener);
    }

    public void setFinishReplenishMoneyBtnListener(ActionListener actionListener) {
        this.finishReplenishMoneyBtn.addActionListener(actionListener);
    }

    public void setReplenishMoneyDefBtn(ActionListener actionListener) {
        this.replenishMoneyDefBtn.addActionListener(actionListener);
    }

    public void setVmExitBtnListener(ActionListener actionListener) {
        this.vmExitBtn.addActionListener(actionListener);
    }

    /**
     * Creates a main frame with a size of 380 by 500 pixels, sets visibility to true,
     * and assigns a default close operation
     * @param mainFrame is the previously initialized mainFrame of the program
     */
    public void createMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.setSize(380, 500);
        this.mainFrame.setVisible(true);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a vending machine creation frame with a size of 380 by 500 pixels, sets visibility to true,
     * and assigns a default close operation
     * @param vmCreationFrame is the previously initialized vmCreationFrame of the program
     */
    public void createVmCreationFrame(JFrame vmCreationFrame) {
        this.vmCreationFrame = vmCreationFrame;
        this.vmCreationFrame.setSize(380, 500);
        this.vmCreationFrame.setVisible(true);
        this.vmCreationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a vending machine testing frame with a size of 380 by 500 pixels, sets visibility to true,
     * and assigns a default close operation
     * @param vmTestingFrame is the previously initialized vmTestingFrame of the program
     */
    public void createVmTestingFrame(JFrame vmTestingFrame) {
        this.vmTestingFrame = vmTestingFrame;
        this.vmTestingFrame.setSize(380, 500);
        this.vmTestingFrame.setVisible(true);
        this.vmTestingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a vending machine features frame with a size of 380 by 500 pixels, sets visibility to true,
     * and assigns a default close operation
     * vFeaturesFrame is also setup here.
     * @param vFeaturesFrame is the previously initialized vFeaturesFrame of the program
     * @param currentVM is the most recently created vending machine
     */
    public void createVFeaturesFrame(JFrame vFeaturesFrame, VendingMachine currentVM) {
        this.vFeaturesFrame = vFeaturesFrame;
        setupVFeaturesFrame(currentVM);
        this.vFeaturesFrame.setSize(380, 500);
        this.vFeaturesFrame.setVisible(true);
        this.vFeaturesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a special item frame with a size of 380 by 500 pixels, sets visibility to true,
     * and assigns a default close operation
     * specialFrame is also setup here
     * @param specialFrame is the previously initialized specialFrame of the program
     * @param currentVM is the most recently created vending machine
     */
    public void createSpecialFrame(JFrame specialFrame, VendingMachine currentVM) {
        this.specialFrame = specialFrame;
        setupSpecialFrame(currentVM);
        this.specialFrame.setSize(380, 1000);
        this.specialFrame.setVisible(true);
        this.specialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a checkout frame with a size of 380 by 500 pixels, sets visibility to true,
     * and assigns a default close operation
     * checkout is also setup here
     * @param checkoutFrame is the previously initialized checkoutFrame of the program
     * @param itemOrdered is the item selected by the user to be checked out
     */
    public void createCheckoutFrame(JFrame checkoutFrame, Item itemOrdered) {
        this.checkoutFrame = checkoutFrame;
        setupCheckoutFrame(itemOrdered);
        this.checkoutFrame.setSize(380, 500);
        this.checkoutFrame.setVisible(true);
        this.checkoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a vending maintenance frame with a size of 380 by 500 pixels, sets visibility to true,
     * and assigns a default close operation
     * vMaintenceFrame is also setup here
     * @param vMaintenanceFrame is the previously initialized vMaintenanceFrame of the program
     * @param currentVM is the most recently created vending machine
     */
    public void createVMaintenanceFrame(JFrame vMaintenanceFrame, VendingMachine currentVM) {
        this.vMaintenanceFrame = vMaintenanceFrame;
        setupVMaintenanceFrame(currentVM);
        this.vMaintenanceFrame.setSize(380, 500);
        this.vMaintenanceFrame.setVisible(true);
        this.vMaintenanceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a restock frame with a size of 380 by 500 pixels, sets visibility to true,
     * and assigns a default close operation
     * restockFrame is also setup here
     * @param restockFrame is the previously initialized restockFrame of the program
     * @param currentVM is the most recently created vending machine
     */
    public void createRestockFrame(JFrame restockFrame, VendingMachine currentVM) {
        this.restockFrame = restockFrame;
        setupRestockFrame(currentVM);
        this.restockFrame.setSize(380, 500);
        this.restockFrame.setVisible(true);
        this.restockFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a set price frame with a size of 380 by 500 pixels, sets visibility to true,
     * and assigns a default close operation
     * setPriceFrame is also setup here
     * @param setPriceFrame is the previously initialized setPriceFrame of the program
     * @param currentVM is the most recently created vending machine
     */
    public void createSetPriceFrame(JFrame setPriceFrame, VendingMachine currentVM) {
        this.setPriceFrame = setPriceFrame;
        setupSetPriceFrame(currentVM);
        this.setPriceFrame.setSize(380, 500);
        this.setPriceFrame.setVisible(true);
        this.setPriceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a collect payment frame with a size of 380 by 500 pixels, sets visibility to true,
     * and assigns a default close operation
     * collectPaymentFrame is also setup here
     * @param collectPayFrame is the previously initialized collectPayFrame of the program
     * @param currentVM is the most recently created vending machine
     */
    public void createCollectPayFrame(JFrame collectPayFrame, VendingMachine currentVM) {
        this.collectPayFrame = collectPayFrame;
        setupCollectPayFrame(currentVM);
        this.collectPayFrame.setSize(380, 500);
        this.collectPayFrame.setVisible(true);
        this.collectPayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a replenish money frame with a size of 380 by 500 pixels, sets visibility to true,
     * and assigns a default close operation
     * replenishMoneyFrame is also setup here
     * @param replenishMoneyFrame is the previously initialized replenishMoneFrame of the program
     * @param currentVM is the most recently created vending machine
     */
    public void createReplenishMoneyFrame(JFrame replenishMoneyFrame, VendingMachine currentVM) {
        this.replenishMoneyFrame = replenishMoneyFrame;
        setupReplenishMoneyFrame(currentVM);
        this.replenishMoneyFrame.setSize(380, 500);
        this.replenishMoneyFrame.setVisible(true);
        this.replenishMoneyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates a print transaction summary frame with a size of 380 by 500 pixels, sets visibility to true,
     * and assigns a default close operation
     * printTransacSummary is also setup here
     * @param printTransacSummaryFrame is the previously initialized printTransacSummaryFrame of the program
     * @param currentVM is the most recently created vending machine
     */
    public void createPrintTransacSummaryFrame(JFrame printTransacSummaryFrame, VendingMachine currentVM) {
        this.printTransacSummaryFrame = printTransacSummaryFrame;
        setupPrintTransacSummaryFrame(currentVM);
        this.printTransacSummaryFrame.setSize(380, 500);
        getPrintTransacSummaryFrame().setVisible(true);
        this.printTransacSummaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JLabel getMainError() {
        return vmError;
    }

    public JFrame getVmCreationFrame() {
        return vmCreationFrame;
    }

    public JFrame getVmTestingFrame() {
        return vmTestingFrame;
    }

    public JFrame getVFeaturesFrame() {
        return vFeaturesFrame;
    }

    public JLabel getSelected() {
        return vFeaturesSelected;
    }

    public JTextField getMoneyField() {
        return vFeaturesMoneyField;
    }

    public JLabel getVFeaturesError() {
        return vFeaturesError;
    }

    public JLabel getVFeaturesChange() {
        return vFeaturesChange;
    }

    public JLabel getRestockErrorLbl() {
        return restockErrorLbl;
    }

    public JLabel getSetPriceErrorLbl() {
        return setPriceErrorLbl;
    }

    public JLabel getCollectPayErrorLbl() {
        return collectPayErrorLbl;
    }

    public JLabel getCollectPayCurBalLbl() {
        return collectPayCurBalLbl;
    }

    public JLabel getReplenishMoneyErrorLbl() {
        return replenishMoneyErrorLbl;
    }

    public JLabel getReplenishMoneyCurBalLbl() {
        return replenishMoneyCurBalLbl;
    }

    public JTextField getRestockItemName() {
        return restockItemName;
    }

    public JTextField getRestockItemNum() {
        return restockItemNum;
    }

    public JTextField getSetPriceItemName() {
        return setPriceItemName;
    }

    public JTextField getSetPriceItem() {
        return setPriceItem;
    }

    public JTextField getCollectPaySpecTF() {
        return collectPaySpecTF;
    }

    public JTextField getReplenishMoneySpecTF() {
        return replenishMoneySpecTF;
    }

    public JTextArea getSetPriceItemsList() {
        return setPriceItemsList;
    }

    public JTextArea getRestockItemsList() {
        return restockItemsList;
    }

    public JTextArea getPrintTransacSummaryTA() {
        return printTransacSummaryTA;
    }

    public JFrame getSpecialFrame() {
        return specialFrame;
    }

    public JFrame getCheckoutFrame() {
        return checkoutFrame;
    }

    public JFrame getvMaintenanceFrame() {
        return vMaintenanceFrame;
    }

    public JFrame getRestockFrame() {
        return restockFrame;
    }

    public JFrame getSetPriceFrame() {
        return setPriceFrame;
    }

    public JFrame getCollectPayFrame() {
        return collectPayFrame;
    }

    public JFrame getReplenishMoneyFrame() {
        return replenishMoneyFrame;
    }

    public JFrame getPrintTransacSummaryFrame() {
        return printTransacSummaryFrame;
    }

    public boolean getSpecialReturn() {
        return specialReturn;
    }

    public void setSpecialReturn(boolean b) {
        this.specialReturn = b;
    }
}

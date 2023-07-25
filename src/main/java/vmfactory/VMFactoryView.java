package vmfactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VMFactoryView {
    private JFrame mainFrame, vmCreationFrame, vmTestingFrame, vFeaturesFrame, vMaintenanceFrame,
                    restockFrame, setPriceFrame, collectPayFrame, replenishMoneyFrame, printTransacSummaryFrame;
    private JLabel appHeader, vmCHeader, vmTHeader, vFeaturesHeader, vMaintenanceHeader,
            restockFbackLbl, restockItemNameLbl, restockItemNumLbl, setPriceLbl, setPriceItemNameLbl, setPriceFbackLbl;
    private JButton vmCreationBtn, vmTestBtn, vmExitBtn, regVMCreateBtn, spcVMCreateBtn,
            vFeaturesBtn, vMaintenanceBtn, restockBtn, setPriceBtn, collectPayBtn, replenishMoneyBtn, printTransacSummaryBtn,
            finishRestockBtn, finishSetPriceBtn;
    private JTextField restockItemName, restockItemNum, setPriceItemName, setPriceItem;
    private JTextArea restockItemsList, setPriceItemsList;
    private JPanel restockPanel1, restockPanel2, setPricePanel1, setPricePanel2;

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

        this.mainFrame.add(appHeader);
        this.mainFrame.add(vmCreationBtn);
        this.mainFrame.add(vmTestBtn);
        this.mainFrame.add(vmExitBtn);

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

    public void setupVFeaturesFrame(VendingMachine currentVM) {
        // Header
        this.vFeaturesFrame = new JFrame("Vending Features Testing");
        this.vFeaturesFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.vFeaturesHeader = new JLabel("VENDING FEATURES TESTING");
        this.vFeaturesFrame.add(vFeaturesHeader);
        this.vFeaturesFrame.add(Box.createRigidArea(new Dimension(300, 10)));

        // Vending machine options
        for(ItemSlot slot : currentVM.getListItemSlots()) {
            JButton button = new JButton(slot.getItemName());
            JLabel label = new JLabel("Stock: " + slot.getItemStock() +
                                      "  /  Price: " + slot.getPrice() +
                                      "  /  Calories: " + slot.getItem().getCaloriesAmt());
            button.setPreferredSize(new Dimension(150, 25));
            this.vFeaturesFrame.add( button );
            this.vFeaturesFrame.add( label );
        }
        this.vFeaturesFrame.add(Box.createRigidArea(new Dimension(300, 10)));

        // Selected item display
        JLabel vFeaturesSelected = new JLabel("Selected Item: ");
        this.vFeaturesFrame.add(vFeaturesSelected);
        this.vFeaturesFrame.add(Box.createRigidArea(new Dimension(300, 10)));

        // Money input
        JLabel vFeaturesMoneyLabel = new JLabel("Input money in proper denominations:");
        JTextField vFeaturesMoney = new JTextField(30);
        this.vFeaturesFrame.add(vFeaturesMoneyLabel);
        this.vFeaturesFrame.add(vFeaturesMoney);
        this.vFeaturesFrame.add(Box.createRigidArea(new Dimension(300, 10)));

        // Check out or cancel
        JButton vFeaturesCheckOut = new JButton("Check Out");
        vFeaturesCheckOut.setPreferredSize(new Dimension(150, 25));
        JButton vFeaturesCancel = new JButton("Cancel");
        vFeaturesCancel.setPreferredSize(new Dimension(150, 25));
        this.vFeaturesFrame.add(vFeaturesCheckOut);
        this.vFeaturesFrame.add(vFeaturesCancel);
    }

    public void setupVMaintenanceFrame() {
        // Header
        this.vMaintenanceFrame = new JFrame("Test Maintenance Features");
        this.vMaintenanceFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.vMaintenanceHeader = new JLabel("TEST MAINTENANCE FEATURES");
        this.vMaintenanceFrame.add(vMaintenanceHeader);

        this.restockBtn = new JButton("Restock / Stock Item");
        this.restockBtn.setPreferredSize(new Dimension(200, 50));
        this.restockBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createRestockFrame(getRestockFrame());
                vMaintenanceFrame.dispose();
            }
        });

        this.setPriceBtn = new JButton("Set Price of Item");
        this.setPriceBtn.setPreferredSize(new Dimension(200, 50));
        this.setPriceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createSetPriceFrame(getSetPriceFrame());
                vMaintenanceFrame.dispose();
            }
        });

        this.collectPayBtn = new JButton("Collect Payment");
        this.collectPayBtn.setPreferredSize(new Dimension(200, 50));
        this.replenishMoneyBtn = new JButton("Replenish Money");
        this.replenishMoneyBtn.setPreferredSize(new Dimension(200, 50));
        this.printTransacSummaryBtn = new JButton("Print Transactions Summary");
        this.printTransacSummaryBtn.setPreferredSize(new Dimension(200, 50));

        this.vMaintenanceFrame.add(restockBtn);
        this.vMaintenanceFrame.add(setPriceBtn);
        this.vMaintenanceFrame.add(collectPayBtn);
        this.vMaintenanceFrame.add(replenishMoneyBtn);
        this.vMaintenanceFrame.add(printTransacSummaryBtn);
    }

    public void setupRestockFrame() {
        // Header
        this.restockFrame = new JFrame("Restock Item");
        this.restockFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.restockItemName = new JTextField(30);
        this.restockItemNum = new JTextField(30);

        this.restockItemNameLbl = new JLabel("Enter Item Name to stock:      ");
        this.restockItemNumLbl = new JLabel("Enter Number of items to stock: ");
        this.restockFbackLbl = new JLabel();
        this.restockFbackLbl.setPreferredSize(new Dimension(220, 30));

        this.restockItemsList = new JTextArea(" ");
        this.restockItemsList.setPreferredSize(new Dimension(330, 230));
        this.restockItemsList.setEditable(false);

        this.restockItemName = new JTextField();
        this.restockItemName.setColumns(10);
        this.restockItemNum = new JTextField();
        this.restockItemNum.setColumns(10);

        this.finishRestockBtn = new JButton("Finish restocking");
        this.finishRestockBtn.setPreferredSize(new Dimension(200, 50));

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
        this.restockFrame.add(restockFbackLbl);
        this.restockFrame.add(restockItemsList);
    }

    public void setupSetPriceFrame() {
        // Header
        this.setPriceFrame = new JFrame("Set Price of Item");
        this.setPriceFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.setPriceItemName = new JTextField(30);
        this.setPriceItem = new JTextField(30);

        this.setPriceItemNameLbl = new JLabel("Enter Item Name to set price:      ");
        this.setPriceLbl = new JLabel("Enter Price of item: ");
        this.setPriceFbackLbl = new JLabel();
        this.setPriceFbackLbl.setPreferredSize(new Dimension(220, 30));

        this.setPriceItemsList = new JTextArea(" ");
        this.setPriceItemsList.setPreferredSize(new Dimension(330, 230));
        this.setPriceItemsList.setEditable(false);

        this.setPriceItemName = new JTextField();
        this.setPriceItemName.setColumns(10);
        this.setPriceItem = new JTextField();
        this.setPriceItem.setColumns(10);

        this.finishSetPriceBtn = new JButton("Finish setting price");
        this.finishSetPriceBtn.setPreferredSize(new Dimension(200, 50));

        this.setPricePanel1 = new JPanel();
        this.setPricePanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPricePanel1.add(setPriceItemNameLbl);
        this.setPricePanel1.add(setPriceItemName);

        this.setPricePanel2 = new JPanel();
        this.setPricePanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPricePanel2.add(setPriceLbl);
        this.setPricePanel2.add(setPriceItem);

        this.setPriceFrame.add(setPricePanel1);
        this.setPriceFrame.add(setPricePanel2);
        this.setPriceFrame.add(finishSetPriceBtn);
        this.setPriceFrame.add(setPriceFbackLbl);
        this.setPriceFrame.add(setPriceItemsList);
    }

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

    public void setVFeaturesBtnListener(ActionListener actionListener) {
        this.vFeaturesBtn.addActionListener(actionListener);
    }
    public void setvMaintenanceBtnListener(ActionListener actionListener) {
        this.vMaintenanceBtn.addActionListener(actionListener);
    }
    public void setSetPriceBtnListener(ActionListener actionListener) {
        this.setPriceBtn.addActionListener(actionListener);
    }
    public void setCollectPayBtnListener(ActionListener actionListener) {
        this.collectPayBtn.addActionListener(actionListener);
    }
    public void setReplenishMoneyBtnListener(ActionListener actionListener) {
        this.replenishMoneyBtn.addActionListener(actionListener);
    }
    public void setPrintTransacSummaryBtnListener(ActionListener actionListener) {
        this.printTransacSummaryBtn.addActionListener(actionListener);
    }


    public void setVmExitBtnListener(ActionListener actionListener) {
        this.vmExitBtn.addActionListener(actionListener);
    }

    public void createMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.setSize(380,500);
        this.mainFrame.setVisible(true);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createVmCreationFrame(JFrame vmCreationFrame) {
        this.vmCreationFrame = vmCreationFrame;
        this.vmCreationFrame.setSize(380,500);
        this.vmCreationFrame.setVisible(true);
        this.vmCreationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createVmTestingFrame(JFrame vmTestingFrame) {
        this.vmTestingFrame = vmTestingFrame;
        this.vmTestingFrame.setSize(380,500);
        this.vmTestingFrame.setVisible(true);
        this.vmTestingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createVFeaturesFrame(JFrame vFeaturesFrame, VendingMachine currentVM) {
        this.vFeaturesFrame = vFeaturesFrame;
        setupVFeaturesFrame(currentVM);
        this.vFeaturesFrame.setSize(380,500);
        this.vFeaturesFrame.setVisible(true);
        this.vFeaturesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createVMaintenanceFrame(JFrame vMaintenanceFrame) {
        this.vMaintenanceFrame = vMaintenanceFrame;
        setupVMaintenanceFrame();
        this.vMaintenanceFrame.setSize(380,500);
        this.vMaintenanceFrame.setVisible(true);
        this.vMaintenanceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createRestockFrame(JFrame restockFrame) {
        this.restockFrame = restockFrame;
        setupRestockFrame();
        this.restockFrame.setSize(380,500);
        this.restockFrame.setVisible(true);
        this.restockFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createSetPriceFrame(JFrame setPriceFrame) {
        this.setPriceFrame = setPriceFrame;
        setupSetPriceFrame();
        this.setPriceFrame.setSize(380,500);
        this.setPriceFrame.setVisible(true);
        this.setPriceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JFrame getMainFrame() {
        return mainFrame;
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

    public JFrame getvMaintenanceFrame() {
        return vMaintenanceFrame;
    }

    public JFrame getRestockFrame() {
        return restockFrame;
    }

    public JFrame getSetPriceFrame() {
        return setPriceFrame;
    }
}

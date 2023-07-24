package vmfactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VMFactoryView {
    private JFrame mainFrame, vmCreationFrame, vmTestingFrame, vFeaturesFrame, vMaintenanceFrame;
    private JLabel appHeader, vmCHeader, vmTHeader, vFeaturesHeader, vMaintenanceHeader;
    private JButton vmCreationBtn, vmTestBtn, vmExitBtn, regVMCreateBtn, spcVMCreateBtn,
            vFeaturesBtn, vMaintenanceBtn, restockBtn, setPriceBtn, collectPayBtn, replenishMoneyBtn, printTransacSummaryBtn;
    private JPanel panel;

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
        this.setPriceBtn = new JButton("Set Price of Item");
        this.setPriceBtn.setPreferredSize(new Dimension(200, 50));
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
    public void setRestockBtnListener(ActionListener actionListener) {
        this.restockBtn.addActionListener(actionListener);
    }
    public void setSetPriceBtnListener(ActionListener actionListener) {
        this.setPriceBtn.addActionListener(actionListener);
    }
    public void setCollectPayBtnListener(ActionListener actionListener) {
        this.collectPayBtn.addActionListener(actionListener);
    }
    public void setReplenishMoneyBtnBtnListener(ActionListener actionListener) {
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
}

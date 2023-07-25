package vmfactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VMFactoryView {
    private JFrame mainFrame, vmCreationFrame, vmTestingFrame, vFeaturesFrame, vMaintenanceFrame,
            restockFrame, setPriceFrame, collectPayFrame, replenishMoneyFrame, printTransacSummaryFrame;
    private JLabel appHeader, vmCHeader, vmTHeader, vmError, vFeaturesHeader, vMaintenanceHeader,
            vFeaturesSelected, buyItemLabel, vFeaturesMoneyLabel, vFeaturesError,
            restockFbackLbl, restockItemNameLbl, restockItemNumLbl, setPriceLbl, setPriceItemNameLbl, setPriceFbackLbl,
            collectPaySpecLbl, collectPayFbackLbl;
    private JButton vmCreationBtn, vmTestBtn, vmExitBtn, regVMCreateBtn, spcVMCreateBtn,
            vFeaturesBtn, selectItemBut, vFeaturesCheckOut, vFeaturesCancel,
            vMaintenanceBtn, restockBtn, setPriceBtn, collectPayBtn, replenishMoneyBtn, printTransacSummaryBtn,
            finishRestockBtn, finishSetPriceBtn, collectPayAllBtn, collectPaySpecBtn, finishCollectPayBtn;
    private ArrayList<JButton> listSelectItem = new ArrayList<JButton>();
    private JTextField vFeaturesMoneyField, restockItemName, restockItemNum, setPriceItemName, setPriceItem, collectPaySpecTF;
    private JTextArea restockItemsList, setPriceItemsList, collectPayCurBal;
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

    public void setupVFeaturesFrame(VendingMachine currentVM) {
        // Header
        this.vFeaturesFrame = new JFrame("Vending Features Testing");
        this.vFeaturesFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        if(currentVM instanceof SpecialVM)
            this.vFeaturesHeader = new JLabel("SPECIAL VENDING FEATURES TESTING");
        else this.vFeaturesHeader = new JLabel("REGULAR VENDING FEATURES TESTING");
        
        this.vFeaturesFrame.add(vFeaturesHeader);
        this.vFeaturesFrame.add(Box.createRigidArea(new Dimension(300, 10)));

        // Vending machine options
        this.listSelectItem.clear();
        for(ItemSlot slot : currentVM.getListItemSlots()) {
            if(!(currentVM instanceof SpecialVM) || slot.getForSale()) {
                this.selectItemBut = new JButton(slot.getItemName());
                selectItemBut.setPreferredSize(new Dimension(150, 25));

                if(slot.getItem() instanceof SpecialItem)
                    this.buyItemLabel = new JLabel("Special Item : Build-it-yourself!");
                else this.buyItemLabel = new JLabel("Stock: " + slot.getItemStock() +
                                                    "  /  Price: " + slot.getPrice() +
                                                    "  /  Calories: " + slot.getItem().getCaloriesAmt());

                this.vFeaturesFrame.add(selectItemBut);
                this.vFeaturesFrame.add(buyItemLabel);
                this.listSelectItem.add(selectItemBut);
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
        
        this.vFeaturesFrame.add(vFeaturesSelected);
        this.vFeaturesFrame.add(Box.createRigidArea(new Dimension(300, 10)));
        this.vFeaturesFrame.add(vFeaturesMoneyLabel);
        this.vFeaturesFrame.add(vFeaturesMoneyField);
        this.vFeaturesFrame.add(Box.createRigidArea(new Dimension(300, 10)));
        this.vFeaturesFrame.add(vFeaturesCheckOut);
        this.vFeaturesFrame.add(vFeaturesCancel);
        this.vFeaturesFrame.add(vFeaturesError);
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
        this.collectPayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createCollectPayFrame(getCollectPayFrame());
                vMaintenanceFrame.dispose();
            }
        });

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

    public void setupCollectPayFrame() {
        // Header
        this.collectPayFrame = new JFrame("Collect Payment");
        this.collectPayFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.collectPayFbackLbl = new JLabel();
        this.collectPayFbackLbl.setPreferredSize(new Dimension(220, 30));

        this.collectPayCurBal = new JTextArea(" ");
        this.collectPayCurBal.setPreferredSize(new Dimension(220, 30));
        this.collectPayCurBal.setEditable(false);

        this.collectPayAllBtn = new JButton("Collect all money");
        this.collectPayAllBtn.setPreferredSize(new Dimension(200, 50));

        this.collectPaySpecLbl = new JLabel("Enter specific amount:  ");
        this.collectPaySpecLbl.setPreferredSize(new Dimension(220, 30));

        this.collectPaySpecTF = new JTextField(30);

        this.collectPaySpecBtn = new JButton("Collect a specified amount of money");
        this.collectPaySpecBtn.setPreferredSize(new Dimension(200, 50));

        this.finishCollectPayBtn = new JButton("Collect");

        this.collectPayFrame.add(collectPaySpecLbl);
        this.collectPayFrame.add(collectPaySpecTF);
        this.collectPayFrame.add(collectPayAllBtn);
        this.collectPayFrame.add(finishCollectPayBtn);
        this.collectPayFrame.add(collectPayFbackLbl);
        this.collectPayFrame.add(collectPayCurBal);
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
    }
    public void setSelectItemBtnListener(ArrayList<ActionListener> actionListener) {
        for(int i=0; i < listSelectItem.size(); i++)
            listSelectItem.get(i).addActionListener(actionListener.get(i));
    }
    public void setCheckOutBtnListener(ActionListener actionListener) {
        this.vFeaturesCheckOut.addActionListener(actionListener);
    }

    // maintenance testing
    public void setvMaintenanceBtnListener(ActionListener actionListener) {
        this.vMaintenanceBtn.addActionListener(actionListener);
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

    public void createCollectPayFrame(JFrame setPriceFrame) {
        this.collectPayFrame = collectPayFrame;
        setupCollectPayFrame();
        this.collectPayFrame.setSize(380,500);
        this.collectPayFrame.setVisible(true);
        this.collectPayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}

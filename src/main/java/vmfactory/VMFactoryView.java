package vmfactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VMFactoryView {
    private JFrame mainFrame, vmCreationFrame, vmTestingFrame, vFeaturesFrame;
    private JLabel appHeader, vmCHeader, vmTHeader, vFeaturesHeader;
    private JButton vmCreationBtn, vmTestBtn, vmExitBtn, regVMCreateBtn, spcVMCreateBtn,
            vFeaturesBtn, vMaintenanceBtn;
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
        
        this.vFeaturesFrame = new JFrame("Vending Features Testing");
        this.vFeaturesFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.vFeaturesHeader = new JLabel("VENDING FEATURES TESTING");

        this.vFeaturesFrame.add(vFeaturesHeader);

        JButton buttone = new JButton( " y e s " );
        buttone.setPreferredSize(new Dimension(200, 30));
        this.vFeaturesFrame.add( buttone );

        for(ItemSlot slot : currentVM.getListItemSlots()) {
            JButton button = new JButton( slot.getItemName() );
            button.setPreferredSize(new Dimension(70, 30));
            this.vFeaturesFrame.add( button );
        }

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
}

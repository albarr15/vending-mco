package vmfactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VMFactoryController {
    private VMFactoryView vmFactoryView;
    private VMFactoryModel vmFactoryModel;
    private ArrayList<ActionListener> listSelectListeners = new ArrayList<ActionListener>();

    public VMFactoryController (VMFactoryView vmFactoryView, VMFactoryModel vmFactoryModel) {
        this.vmFactoryView = vmFactoryView;
        this.vmFactoryModel = vmFactoryModel;

        this.vmFactoryView.setVmCreationBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vmFactoryView.getMainFrame().dispose();
                vmFactoryView.getVmTestingFrame().dispose();

                vmFactoryView.createVmCreationFrame(vmFactoryView.getVmCreationFrame());
            }
        });

        this.vmFactoryView.setregVmCreateBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vmFactoryView.getMainFrame().dispose();
                vmFactoryView.getVmCreationFrame().dispose();

                vmFactoryModel.createRegularVM();
                vmFactoryModel.setupVM();
                vmFactoryView.getMainError().setText("");
                vmFactoryView.createMainFrame(vmFactoryView.getMainFrame());
            }
        });

        this.vmFactoryView.setspcVmCreateBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vmFactoryView.getMainFrame().dispose();
                vmFactoryView.getVmCreationFrame().dispose();

                vmFactoryModel.createSpecialVM();
                vmFactoryModel.setupVM();
                vmFactoryView.getMainError().setText("");
                vmFactoryView.createMainFrame(vmFactoryView.getMainFrame());
            }
        });

        this.vmFactoryView.setVmTestBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(vmFactoryModel.getCurrentVM() instanceof VendingMachine))
                    vmFactoryView.getMainError().setText("Error: No Vending Machine has been created");
                else {
                    vmFactoryView.getMainFrame().dispose();
                    vmFactoryView.getVmCreationFrame().dispose();
                    
                    vmFactoryView.createVmTestingFrame(vmFactoryView.getVmTestingFrame());
               }
            }
        });

        this.vmFactoryView.setVmExitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.vmFactoryView.setVFeaturesBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vmFactoryView.getMainFrame().dispose();
                vmFactoryView.getVmTestingFrame().dispose();

                vmFactoryModel.getCurrentVM().makeTransaction();
                setupSlots();
                vmFactoryView.createVFeaturesFrame(vmFactoryView.getVFeaturesFrame(), vmFactoryModel.getCurrentVM());
                vmFactoryView.setSelectItemBtnListener(listSelectListeners);
                vmFactoryView.setCheckOutBtnListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        VendingMachine currentVM = vmFactoryModel.getCurrentVM();
                        Transaction transac = currentVM.getCurrentTransaction();
                        transac.receivePayment(vmFactoryView.getMoneyField().getText(), currentVM.getBalance());
                        
                        switch(transac.produceChange(currentVM.getBalance(), currentVM.getListItemSlots())) {
                            case 1: 
                                vmFactoryView.getVFeaturesError().setText("Error: No item selected");
                                vmFactoryView.getVFeaturesChange().setText("Returning change: " + transac.getReturned());
                                break;
                            case 2: 
                                vmFactoryView.getVFeaturesError().setText("Error: Insufficient item stock");
                                vmFactoryView.getVFeaturesChange().setText("Returning change: " + transac.getReturned());
                                break;
                            case 3: 
                                vmFactoryView.getVFeaturesError().setText("Error: Insufficient cash input");
                                vmFactoryView.getVFeaturesChange().setText("Returning change: " + transac.getReturned());
                                break;
                            case 4: 
                                vmFactoryView.getVFeaturesError().setText("Error: Insufficient change in machine");
                                vmFactoryView.getVFeaturesChange().setText("Returning change: " + transac.getReturned());
                                break;
                            case 5: 
                                vmFactoryView.getVFeaturesError().setText("Error: Item cannot be sold individually");
                                vmFactoryView.getVFeaturesChange().setText("Returning change: " + transac.getReturned());
                                break;
                            case 0:
                            default:
                                break;
                        }
                    }
                });
            }
        });

        this.vmFactoryView.setvMaintenanceBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vmFactoryView.getMainFrame().dispose();
                vmFactoryView.getVmTestingFrame().dispose();

                vmFactoryView.createVMaintenanceFrame(vmFactoryView.getvMaintenanceFrame(), vmFactoryModel.getCurrentVM());
            }
        });

        this.vmFactoryView.setFinishRestockBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemSlot itemSlot = vmFactoryModel.getCurrentVM().findItemSlot(vmFactoryView.getRestockItemName().getText());
                int price = Integer.parseInt(vmFactoryView.getSetPriceItem().getText());
                vmFactoryModel.getCurrentVM().getMaintenance().setPrice(itemSlot, price);

                vmFactoryView.getRestockItemsList().setText(vmFactoryModel.getCurrentVM().displayAllSlots());
            }
        });
    }

    private void setupSlots() {
        this.listSelectListeners.clear();
        for(int i=0; i < vmFactoryModel.getCurrentVM().getListItemSlots().size(); i++) {
            ItemSlot currentSlot = vmFactoryModel.getCurrentVM().getListItemSlots().get(i);
            if(!(vmFactoryModel.getCurrentVM() instanceof SpecialVM) || currentSlot.getForSale()) {
                ActionListener al = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        vmFactoryView.getSelected().setText("Selected item: " + currentSlot.getItemName());
                        vmFactoryModel.getCurrentVM().getCurrentTransaction().selectItem(currentSlot);
                    }
                };
                this.listSelectListeners.add(al);
                System.out.println(i + " contains " + currentSlot.getItemName());
            }
        }
    }
}

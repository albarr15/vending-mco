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
                if(vmFactoryView.getSpecialFrame() != null)
                    vmFactoryView.getSpecialFrame().dispose();

                if(!vmFactoryView.getSpecialReturn()) {
                    vmFactoryModel.getCurrentVM().makeTransaction();
                    vmFactoryView.setSpecialReturn(false);
                }
                
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
                                vmFactoryView.getVFeaturesFrame().dispose();
                                vmFactoryView.createCheckoutFrame(vmFactoryView.getCheckoutFrame());
                                vmFactoryView.getSelected().setText("Dispensing " + transac.getItemOrdered().getName() + "...");
                                vmFactoryView.getVFeaturesChange().setText("Change: " + transac.getReturned());   
                                break;
                            default:
                                break;
                        }
                    }
                });

                vmFactoryView.setSpecialBtnListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        vmFactoryView.getVFeaturesFrame().dispose();
                        ((SpecialTransaction)vmFactoryModel.getCurrentVM().getCurrentTransaction()).setSpecialItem(new SpecialItem("Ramen"));
                        vmFactoryView.createSpecialFrame(vmFactoryView.getvMaintenanceFrame(), vmFactoryModel.getCurrentVM());
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
                int num = Integer.parseInt(vmFactoryView.getRestockItemNum().getText());
                System.out.println(itemSlot.getItemName());
                System.out.println(num);
                vmFactoryModel.getCurrentVM().getMaintenance().stockItem(itemSlot, num);
                if (vmFactoryModel.getCurrentVM() instanceof SpecialVM) {
                    vmFactoryView.getRestockItemsList().setText(((SpecialVM)vmFactoryModel.getCurrentVM()).displayAllSlots());
                }
                else { vmFactoryView.getRestockItemsList().setText(vmFactoryModel.getCurrentVM().displayAllSlots()); }
            }
        });

        this.vmFactoryView.setFinishSetPriceBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int price = 0;
                ItemSlot itemSlot = vmFactoryModel.getCurrentVM().findItemSlot(vmFactoryView.getSetPriceItemName().getText());

                try {
                    // TODO: Fix bug (shows error but continues to set price of decimal values)
                    price = Integer.parseInt(vmFactoryView.getSetPriceItem().getText());
                }
                catch (NumberFormatException e2) {
                    // add in feedback label
                    System.out.println("Invalid price. Please enter an integer.");
                }

                System.out.println(itemSlot.getItemName());
                System.out.println(price);
                if (vmFactoryModel.getCurrentVM().getMaintenance().setPrice(itemSlot, price).equals("")) {
                    if (vmFactoryModel.getCurrentVM() instanceof SpecialVM) {
                        vmFactoryView.getSetPriceItemsList().setText(((SpecialVM)vmFactoryModel.getCurrentVM()).displayAllSlots());
                    }
                    else { vmFactoryView.getSetPriceItemsList().setText(vmFactoryModel.getCurrentVM().displayAllSlots()); }
                }
                else {
                    // TODO: Add feedback label in view
                    String feedback = vmFactoryModel.getCurrentVM().getMaintenance().setPrice(itemSlot, price);
                    System.out.println(feedback);
                }
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
    
    private void setupComponents() {
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

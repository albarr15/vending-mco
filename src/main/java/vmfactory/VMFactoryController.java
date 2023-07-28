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
                if(vmFactoryView.getSpecialFrame() != null) {
                    if(vmFactoryView.getSpecialReturn()) {
                        vmFactoryView.setSpecialReturn(false);
                        System.out.println("speical transac done");
                    } else {
                        vmFactoryModel.getCurrentVM().getCurrentTransaction().reset(vmFactoryModel.getCurrentVM().getListItemSlots());
                        System.out.println("resetting transaction");
                     }
                } else vmFactoryModel.getCurrentVM().makeTransaction();
                
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
                        setupComponents();
                        vmFactoryView.createSpecialFrame(vmFactoryView.getvMaintenanceFrame(), vmFactoryModel.getCurrentVM());
                        vmFactoryView.setComponentsBtnListener(listSelectListeners);
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
                VendingMachine currentVM = vmFactoryModel.getCurrentVM();
                Maintenance maintenance = currentVM.getMaintenance();

                int price = 0;
                boolean isValid = false;

                ItemSlot itemSlot = currentVM.findItemSlot(vmFactoryView.getSetPriceItemName().getText());
                if (itemSlot == null) {
                    vmFactoryView.getSetPriceErrorLbl().setText("Cannot find item slot for " + vmFactoryView.getSetPriceItemName().getText()
                            + ".");
                }

                try {
                    price = Integer.parseInt(vmFactoryView.getSetPriceItem().getText());
                    isValid = true;
                }
                catch (NumberFormatException e2) {
                    vmFactoryView.getSetPriceErrorLbl().setText("Invalid price. Please enter an integer.");
                }

                System.out.println(itemSlot.getItemName());
                System.out.println(price);

                if (isValid) {
                    switch(maintenance.setPrice(itemSlot, price)) {
                        case 1:
                            vmFactoryView.getSetPriceErrorLbl().setText("Cannot set price. " +
                                    "A Special Item's price must be set according to its components");
                            break;
                        case 2:
                            vmFactoryView.getSetPriceErrorLbl().setText("Invalid price. " +
                                    "Please enter a positive integer.");
                            break;
                        case 3:
                            vmFactoryView.getSetPriceErrorLbl().setText("Invalid price. Please enter an integer.");
                            break;
                        case 0:
                            vmFactoryView.getSetPriceErrorLbl().setText("SETTING PRICE SUCCESSFUL");
                            break;
                        default:
                            break;
                    }
                    if (vmFactoryModel.getCurrentVM() instanceof SpecialVM) {
                        vmFactoryView.getSetPriceItemsList().setText(((SpecialVM)vmFactoryModel.getCurrentVM()).displayAllSlots());
                    }
                    else { vmFactoryView.getSetPriceItemsList().setText(vmFactoryModel.getCurrentVM().displayAllSlots()); }
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
            if(!(currentSlot.getItem() instanceof SpecialItem)) {
                ActionListener al = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Adding " + currentSlot.getItemName());
                        ((SpecialTransaction)vmFactoryModel.getCurrentVM().getCurrentTransaction()).addItem(currentSlot);
                        vmFactoryView.getSpecialFrame().dispose();
                        setupComponents();
                        vmFactoryView.createSpecialFrame(vmFactoryView.getvMaintenanceFrame(), vmFactoryModel.getCurrentVM());
                        vmFactoryView.setComponentsBtnListener(listSelectListeners);
                    }
                };
                this.listSelectListeners.add(al);
                System.out.println(i + " contains " + currentSlot.getItemName());
            }
        }
    }
}

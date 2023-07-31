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
                    vmFactoryView.getSpecialFrame().dispose();	
                    if(!vmFactoryView.getSpecialReturn())	
                        vmFactoryModel.getCurrentVM().getCurrentTransaction().reset(vmFactoryModel.getCurrentVM().getListItemSlots());	
                } else vmFactoryModel.getCurrentVM().makeTransaction();
                
                vmFactoryView.createVFeaturesFrame(vmFactoryView.getVFeaturesFrame(), vmFactoryModel.getCurrentVM());
                setupSlots();
                vmFactoryView.setSelectItemBtnListener(listSelectListeners);
                if(vmFactoryView.getSpecialReturn()) {
                    vmFactoryView.setSpecialReturn(false);
                    vmFactoryView.getSelected().setText("Selected Item: Ramen (Special) / Price: " +
                        vmFactoryModel.getCurrentVM().getCurrentTransaction().getItemOrdered().getPrice());
                }
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
                                vmFactoryView.createCheckoutFrame(vmFactoryView.getCheckoutFrame(), transac.getItemOrdered());
                                vmFactoryView.getSelected().setText("Dispensing " + transac.getItemOrdered().getName() + "...");
                                vmFactoryView.getVFeaturesChange().setText("Change: " + transac.getReturned());
                                vmFactoryModel.getCurrentVM().makeTransaction();
                                break;
                            default:
                                break;
                        }
                    }
                });

                if(vmFactoryModel.getCurrentVM() instanceof SpecialVM)
                    vmFactoryView.setSpecialBtnListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            vmFactoryView.getVFeaturesFrame().dispose();
                            if(vmFactoryModel.getCurrentVM().getCurrentTransaction().getItemOrdered() instanceof SpecialItem)
                                vmFactoryModel.getCurrentVM().getCurrentTransaction().reset(vmFactoryModel.getCurrentVM().getListItemSlots());
                            ((SpecialTransaction)vmFactoryModel.getCurrentVM().getCurrentTransaction()).setSpecialItem(new SpecialItem("Ramen"));
                            
                            vmFactoryView.createSpecialFrame(vmFactoryView.getSpecialFrame(), vmFactoryModel.getCurrentVM());
                            setupComponents();
                            vmFactoryView.setComponentsBtnListener(listSelectListeners);
                            setupRemove();
                            vmFactoryView.setRemoveBtnListener(listSelectListeners);
                        }
                    }); 
            }
        });

        this.vmFactoryView.setvMaintenanceBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VendingMachine currentVM = vmFactoryModel.getCurrentVM();

                vmFactoryView.getMainFrame().dispose();
                vmFactoryView.getVmTestingFrame().dispose();

                vmFactoryView.createVMaintenanceFrame(vmFactoryView.getvMaintenanceFrame(), currentVM);
            }
        });

        this.vmFactoryView.setFinishRestockBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VendingMachine currentVM = vmFactoryModel.getCurrentVM();
                Maintenance maintenance = currentVM.getMaintenance();
                int num = 0;
                boolean isValid = false;

                ItemSlot itemSlot = currentVM.findItemSlot(vmFactoryView.getRestockItemName().getText());
                if (itemSlot == null) {
                    vmFactoryView.getRestockErrorLbl().setText("Cannot find item slot for  " + vmFactoryView.getRestockItemName().getText()
                            + ".");
                }

                try {
                    num = Integer.parseInt(vmFactoryView.getRestockItemNum().getText());
                    if (num < 0) {
                        vmFactoryView.getRestockErrorLbl().setText("Invalid restock number. Please enter a positive integer.");
                    }
                    else { isValid = true; }
                }
                catch (NumberFormatException e1) {
                    vmFactoryView.getRestockErrorLbl().setText("Invalid restock number. Please enter a positive integer.");
                }

                if (isValid) {
                    System.out.println(itemSlot.getItemName());
                    System.out.println(num);

                    int numIgnoredRestocks = vmFactoryModel.getCurrentVM().getMaintenance().stockItem(itemSlot, num);
                    if (numIgnoredRestocks != 0) {
                        vmFactoryView.getRestockErrorLbl().setText("Slot full: Could not stock " + numIgnoredRestocks +
                                " " + itemSlot.getItemName() + ".");
                    }
                    else {
                        vmFactoryView.getRestockErrorLbl().setText("Successfully restocked " + num + " " + itemSlot.getItemName()
                        + ".");
                    }
                    vmFactoryView.getRestockItemsList().setText(vmFactoryModel.getCurrentVM().displayAllSlots());
                }
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

        this.vmFactoryView.setCollectPaySpecBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = 0;
                boolean isValid = false;

                try {
                    amount = Integer.parseInt(vmFactoryView.getCollectPaySpecTF().getText());
                    isValid = true;
                }
                catch (NumberFormatException e1) {
                    vmFactoryView.getCollectPayErrorLbl().setText("Invalid amount. Please enter an integer.");
                }

                if (isValid) {
                    // TODO: create switch cases to display error messages
                    vmFactoryModel.getCurrentVM().getMaintenance().collectMoney(vmFactoryModel.getCurrentVM().getBalance(),
                            amount);
                    vmFactoryView.getCollectPayCurBalLbl().setText("Current Balance : " +
                            vmFactoryModel.getCurrentVM().getBalance().getCurrentBal());
                }
            }
        });

        this.vmFactoryView.setCollectPayAllBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vmFactoryView.getCollectPayErrorLbl().setText("Successfully collected " +
                        vmFactoryModel.getCurrentVM().getBalance().getCurrentBal());
                vmFactoryModel.getCurrentVM().getMaintenance().collectMoney(vmFactoryModel.getCurrentVM().getBalance());
                vmFactoryView.getCollectPayCurBalLbl().setText("Current Balance : " +
                        vmFactoryModel.getCurrentVM().getBalance().getCurrentBal());
            }
        });

        this.vmFactoryView.setFinishReplenishMoneyBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cashList = vmFactoryView.getReplenishMoneySpecTF().getText();

                int numInvalid = vmFactoryModel.getCurrentVM().getMaintenance().replenishMoney(vmFactoryModel.getCurrentVM().getBalance(),
                        cashList);

                if (numInvalid > 0) {
                    vmFactoryView.getReplenishMoneyErrorLbl().setText(numInvalid + " invalid entries were found. Skipped entries.");
                }
                else {
                    vmFactoryView.getReplenishMoneyErrorLbl().setText("REPLENISH MONEY SUCCESSFUL");
                }

                vmFactoryView.getReplenishMoneyCurBalLbl().setText("Current Balance : " +
                        vmFactoryModel.getCurrentVM().getBalance().getCurrentBal());
            }
        });

        this.vmFactoryView.setReplenishMoneyDefBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vmFactoryModel.getCurrentVM().getMaintenance().replenishMoney(vmFactoryModel.getCurrentVM().getBalance());
                vmFactoryView.getReplenishMoneyErrorLbl().setText("SUCCESSFUL : Replenished 20 pcs for each denomination" );
                vmFactoryView.getReplenishMoneyCurBalLbl().setText("Current Balance : " +
                        vmFactoryModel.getCurrentVM().getBalance().getCurrentBal());
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
                        if(vmFactoryModel.getCurrentVM().getCurrentTransaction().getItemOrdered() instanceof SpecialItem)
                            vmFactoryModel.getCurrentVM().getCurrentTransaction().reset(vmFactoryModel.getCurrentVM().getListItemSlots());	
                        
                        if(currentSlot.getItemStock() > 0) {
                            vmFactoryView.getVFeaturesError().setText("");
                            vmFactoryView.getSelected().setText("Selected item: " + currentSlot.getItemName());
                            vmFactoryModel.getCurrentVM().getCurrentTransaction().selectItem(currentSlot);
                        } else vmFactoryView.getVFeaturesError().setText("Error: Item out of stock");
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
                        if(currentSlot.getItemStock() > 0) {
                            vmFactoryView.getVFeaturesError().setText("");
                            System.out.println("Adding " + currentSlot.getItemName());
                            ((SpecialTransaction)vmFactoryModel.getCurrentVM().getCurrentTransaction()).addItem(currentSlot);
                            
                            vmFactoryView.getSpecialFrame().dispose();
                            vmFactoryView.createSpecialFrame(vmFactoryView.getSpecialFrame(), vmFactoryModel.getCurrentVM());
                            setupComponents();
                            vmFactoryView.setComponentsBtnListener(listSelectListeners);
                            setupRemove();
                            vmFactoryView.setRemoveBtnListener(listSelectListeners);
                        } else vmFactoryView.getVFeaturesError().setText("Error: Item out of stock");  
                    }
                };
                this.listSelectListeners.add(al);
                System.out.println(i + " contains " + currentSlot.getItemName());
            }
        }
    }
    private void setupRemove() {
        this.listSelectListeners.clear();
        for(Item item : ((SpecialItem)vmFactoryModel.getCurrentVM().getCurrentTransaction().getItemOrdered()).getListComponents()) {
            ActionListener al = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Removing " + item.getName());
                    ((SpecialTransaction)vmFactoryModel.getCurrentVM().getCurrentTransaction()).removeItem(item.getName(), vmFactoryModel.getCurrentVM().getListItemSlots());
                    vmFactoryView.getSpecialFrame().dispose();
                    vmFactoryView.createSpecialFrame(vmFactoryView.getSpecialFrame(), vmFactoryModel.getCurrentVM());
                    setupComponents();
                    vmFactoryView.setComponentsBtnListener(listSelectListeners);
                    setupRemove();
                    vmFactoryView.setRemoveBtnListener(listSelectListeners);
                }
            };
            System.out.println(" contains " + item.getName());
            this.listSelectListeners.add(al);
        }
    }
}

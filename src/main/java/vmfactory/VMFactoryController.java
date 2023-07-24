package vmfactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VMFactoryController {
    private VMFactoryView vmFactoryView;
    private VMFactoryModel vmFactoryModel;

    public VMFactoryController (VMFactoryView vmFactoryView, VMFactoryModel vmFactoryModel) {
        this.vmFactoryView = vmFactoryView;
        this.vmFactoryModel = vmFactoryModel;

        this.vmFactoryView.setVmCreationBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vmFactoryView.getMainFrame().dispose();

                vmFactoryView.createVmCreationFrame(vmFactoryView.getVmCreationFrame());
            }
        });
    }
}

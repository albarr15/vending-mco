package vmfactory;

public class VMFactoryController {
    private VMFactoryView vmFactoryView;
    private VMFactory vmFactoryModel;

    public VMFactoryController (VMFactoryView vmFactoryView, VMFactory vmFactoryModel) {
        this.vmFactoryView = vmFactoryView;
        this.vmFactoryModel = vmFactoryModel;
    }
}

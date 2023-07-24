package vmfactory;

public class Main {
    public static void main(String[] args) {
        VMFactoryView vmFactoryView = new VMFactoryView();
        VMFactoryModel vmFactoryModel = new VMFactoryModel();
        VMFactoryController vmFactoryController = new VMFactoryController(vmFactoryView, vmFactoryModel);
    }
}

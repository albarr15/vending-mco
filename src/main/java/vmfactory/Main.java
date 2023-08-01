package vmfactory;

/**
 * Represents the Main class of the program where the model, view, and controller class is implemented.
 */
public class Main {
    public static void main(String[] args) {
        VMFactoryView vmFactoryView = new VMFactoryView();
        VMFactoryModel vmFactoryModel = new VMFactoryModel();
        VMFactoryController vmFactoryController = new VMFactoryController(vmFactoryView, vmFactoryModel);
    }
}

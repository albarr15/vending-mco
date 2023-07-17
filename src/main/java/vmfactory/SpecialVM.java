package vmfactory;

public class SpecialVM extends VendingMachine {
    public SpecialVM() {
        super();
    }

    @Override
    public void makeTransaction() {
        this.currentTransaction = new SpecialTransaction();
    }
}

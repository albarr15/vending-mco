package regular_vm;

public class Balance {
    private double currentBal;

    public Balance (double newBal) {
        this.currentBal = newBal;
    }

    public void increaseBal(double incBal) {
        this.currentBal = currentBal + incBal;
    }

    public void decreaseBal(double decBal) {
        this.currentBal = currentBal - decBal;
    }

    public void setCurrentBal(double newBal) {
        this.currentBal = newBal;
    }

    public double getCurrentBal() {
        return this.currentBal;
    }
}

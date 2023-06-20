package regular_vm;
import java.util.ArrayList;

public class Balance {
    private double currentBal;
    private ArrayList<Double> listDenominations = new ArrayList<Double>();

    public Balance (ArrayList<Double> listDenominations1) {
        this.listDenominations.addAll(listDenominations1);
    }

    public ArrayList<Double> getListDenominations() {
        return this.listDenominations;
    }

    public void increaseBal(double incBal) {
        this.currentBal = currentBal + incBal;
    }

    public void decreaseBal(double decBal) {
        this.currentBal = currentBal - decBal;
    }

    public double getCurrentBal() {
        return this.currentBal;
    }
}

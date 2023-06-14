package regular_vm;

public class Item {
    String name;
    double caloriesAmt;

    public Item(String name, double caloriesAmt) {
        this.name = name;
        this.caloriesAmt = caloriesAmt;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCaloriesAmt(double caloriesAmt) {
        this.caloriesAmt = caloriesAmt;
    }

    public String getName() {
        return this.name;
    }
    public double getCaloriesAmt() {
        return this.caloriesAmt;
    }
}

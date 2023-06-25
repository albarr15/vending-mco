package regular_vm;

public class Item {
    private String name;
    private double caloriesAmt;
    private double price;

    public Item(String name, double caloriesAmt, double price) {
        this.name = name;
        this.caloriesAmt = caloriesAmt;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setCaloriesAmt(double caloriesAmt) {
        this.caloriesAmt = caloriesAmt;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }
    
    public double getCaloriesAmt() {
        return this.caloriesAmt;
    }

    public double getPrice() {
        return this.price;
    }
}

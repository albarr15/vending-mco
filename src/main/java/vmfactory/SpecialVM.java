package vmfactory;

public class SpecialVM extends VendingMachine {
    public SpecialVM() {
        super();
    }

    @Override
    public void makeTransaction() {
        this.currentTransaction = new SpecialTransaction();
    }

    @Override public void displayAllSlots() {
        int size = listItemSlots.size();

        // prints each itemSlot with its corresponding stocked item
        for (int i = 0; i < size; i++) {
            if(listItemSlots.get(i).getForSale()) {
                if ((listItemSlots.get(i)).checkSlotAvailability()) {
                    System.out.println("Slot " + (i + 1) + " ( OUT OF STOCK )");
                }
                else {
                    System.out.printf("%-5s", "[" + listItemSlots.get(i).getItemStock() + "]");
                    System.out.printf("%-10s", "Slot " + (i + 1));
                    System.out.printf("%-20s", "(" + (listItemSlots.get(i)).getItemName() + ")");
                    System.out.printf("%-15s", "Price : " + listItemSlots.get(i).getPrice());
                    System.out.printf("%-12s", "Calories : " + listItemSlots.get(i).getItem().getCaloriesAmt());
                    if(listItemSlots.get(i).getItem() instanceof SpecialItem)
                        System.out.printf("\t  (Special)");
                    System.out.println();
                }
            }
            
        }
    }
}

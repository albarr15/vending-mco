package vmfactory;
import java.util.StringJoiner;

/**
 * Represents a Special vending machine.
 * <p>
 *     Inherits properties and methods of regularVM (VendingMachine class)
 * Features include making a special transaction, and displaying all for sale slots.
 */
public class SpecialVM extends VendingMachine {
    public SpecialVM() {
        super();
    }

    @Override
    public void makeTransaction() {
        this.currentTransaction = new SpecialTransaction();
    }

    public void displayForSaleSlots() {
        StringJoiner displaySlots = new StringJoiner("");
        int size = listItemSlots.size();

        // prints each itemSlot with its corresponding stocked item
        for (int i = 0; i < size; i++) {
            if(listItemSlots.get(i).getForSale()) {
                if ((listItemSlots.get(i)).checkSlotAvailability()) {
                    displaySlots.add("Slot " + (i + 1) + " ( OUT OF STOCK )" + "\n");
                }
                else {
                    displaySlots.add(String.format("%-5s", "[" + listItemSlots.get(i).getItemStock() + "]"));
                    displaySlots.add(String.format("%-10s", "Slot " + (i + 1)));
                    displaySlots.add(String.format("%-20s", "(" + (listItemSlots.get(i)).getItemName() + ")"));
                    displaySlots.add(String.format("%-15s", "Price : " + listItemSlots.get(i).getPrice()));
                    displaySlots.add(String.format("%-12s", "Calories : " + listItemSlots.get(i).getItem().getCaloriesAmt()));
                    if(listItemSlots.get(i).getItem() instanceof SpecialItem)
                        displaySlots.add(String.format("\t  (Special)"));
                    displaySlots.add("\n");
                }
            }
        }
    }
}

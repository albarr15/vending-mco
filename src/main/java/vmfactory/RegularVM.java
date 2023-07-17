package vmfactory;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Represents one Regular vending machine.
 * <p>
 * Includes the list of item slots, its balance, the current transaction, and its maintenance.
 * Features include adding an item slot, making a transaction, making its maintenance,
 * displaying slot availability, displaying all slots, and displaying its menus.
 */
public class RegularVM extends VendingMachine {
    private ArrayList<ItemSlot> listItemSlots;
    private Balance balance;
    private Transaction currentTransaction;
    private Maintenance maintenance;

    public RegularVM() {
        super();
    }
}

package vmfactory;

import java.util.ArrayList;

/** Represents a special Item to be stored in a Special Vending Machine
 * <p>
 * Features include adding and removing a component, and printing preparation message
 */
public class SpecialItem extends Item{
    private ArrayList<Item> listComponents = new ArrayList<Item>();

    public SpecialItem(String name) {
        super(name,0,0,null);
    }

    public ArrayList<Item> getListComponents() {
        return this.listComponents;
    }

    /**
     * Adds a component to the special item's list of components
     * @param item is the item to be added onto the list of components
     */
    public void addComponent(Item item) {
        listComponents.add(item);
        this.setCaloriesAmt(this.getCaloriesAmt() + item.getCaloriesAmt());
        this.setPrice(this.getPrice() + item.getPrice());
    }

    /**
     * Removes a component from the special item's list of components
     * @param itemName is the item to be removed from the existing list of components
     */
    public Item removeComponent(String itemName) {
        Item removedItem = null;

        // find item with itemName from listComponents
        for (int i = 0; i < listComponents.size(); i++) {
            if (listComponents.get(i).getName().equals(itemName)) {
                removedItem = listComponents.get(i);
                this.setCaloriesAmt(this.getCaloriesAmt() - removedItem.getCaloriesAmt());
                this.setPrice(this.getPrice() - removedItem.getPrice());
                listComponents.remove(removedItem);
                break;
            }
        }

        return removedItem;
    }

    /**
     * Prints the preparation message for each of the components in the list of Components
     */
    public void printPreparation() {
        for (Item listComponent : listComponents) {
            System.out.println(listComponent.getPrepMessage());
        }
    }
}

package vmfactory;

import java.util.ArrayList;

public class SpecialItem extends Item{
    private ArrayList<Item> listComponents;

    public SpecialItem(String name) {
        super(name,0,0,null);
    }

    public ArrayList<Item> getListComponents() {
        return this.listComponents;
    }

    public void addComponent(Item item) {
        listComponents.add(item);
        this.setCaloriesAmt(this.getCaloriesAmt() + item.getCaloriesAmt());
        this.setPrice(this.getPrice() + item.getPrice());
    }

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

    public void printPreparation() {
        for (Item listComponent : listComponents) {
            System.out.println("Preparing component" + listComponent.getName() + "...");
        }
    }
}

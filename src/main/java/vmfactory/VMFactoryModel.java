package vmfactory;

/**
 * Represents the Vending Machine Factory where regular and special vending machines can be created as well as.
 * set up with default items.
 * <p>
 * Includes the current vending machine (the most recently created)
 * Features include creating a regular vending machine, creating a special regular vending machine,
 * and setting up a vending machine.
 */

public class VMFactoryModel {

    private VendingMachine currentVM;

    public void createRegularVM() {
        this.currentVM = new VendingMachine();
    }

    public void createSpecialVM() {
        this.currentVM = new SpecialVM();
    }

    /**
     * Sets up a vending machine with default items and itemslots with their
     * corresponding name, caloriesAmt, price, and prepMessage
     */
    public void setupVM() {
        Item chashuPork = new Item("Chashu Pork", 99, 95,
                "Topping with Chashu Pork ...");
        Item chickenSlices = new Item("Chicken Slices", 95, 153,
                "Topping with Chicken Slices ...");
        Item fishCake = new Item("Fish Cake", 63, 40,
                "Adding Fish Cakes ...");
        Item ajitamago = new Item("Ajitamago", 72, 35,
                "Adding Ajitamago ...");
        Item friedTofu = new Item("Fried Tofu", 77, 14,
                "Topping with Fried Tofu ...");
        Item seaweed = new Item("Seaweed", 8, 16,
                "Topping with Seaweed ...");
        Item corn = new Item("Corn", 76, 40,
                "Adding corn ...");
        Item butter = new Item("Butter", 81, 1,
                "Adding Butter ...");

        // Instantiate itemSlots
        ItemSlot itemSlot1 = new ItemSlot(chashuPork, true);
        ItemSlot itemSlot2 = new ItemSlot(chickenSlices, true);
        ItemSlot itemSlot3 = new ItemSlot(fishCake, false);
        ItemSlot itemSlot4 = new ItemSlot(ajitamago, true);
        ItemSlot itemSlot5 = new ItemSlot(friedTofu, true);
        ItemSlot itemSlot6 = new ItemSlot(seaweed, true);
        ItemSlot itemSlot7 = new ItemSlot(corn, false);
        ItemSlot itemSlot8 = new ItemSlot(butter, false);

        // add itemSlots to VM
        currentVM.addItemSlot(itemSlot1);
        currentVM.addItemSlot(itemSlot2);
        currentVM.addItemSlot(itemSlot3);
        currentVM.addItemSlot(itemSlot4);
        currentVM.addItemSlot(itemSlot5);
        currentVM.addItemSlot(itemSlot6);
        currentVM.addItemSlot(itemSlot7);
        currentVM.addItemSlot(itemSlot8);

        if (currentVM instanceof SpecialVM) {
            Item ramenNoodles = new Item("Ramen Noodles", 77, 14,
                    "Blanching Ramen Noodles ...");
            Item ramenBroth = new Item("Ramen broth", 50, 40,
                    "Pouring ramen broth...");
            Item ramen = new SpecialItem("Ramen");

            ItemSlot itemSlot9 = new ItemSlot(ramenNoodles, true);
            ItemSlot itemSlot10 = new ItemSlot(ramenBroth, false);
            ItemSlot itemSlot11 = new ItemSlot(ramen, true);

            currentVM.addItemSlot(itemSlot9);
            currentVM.addItemSlot(itemSlot10);
            currentVM.addItemSlot(itemSlot11);
        }
    }

    public VendingMachine getCurrentVM() {
        return this.currentVM;
    }
}
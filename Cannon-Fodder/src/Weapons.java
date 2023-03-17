import java.util.ArrayList;

public class Weapons {

    protected ArrayList<Item> items;

    public Weapons() {
        items = new ArrayList<Item>(); //weapons inventory
    }

    public Weapons(String name, double weight, double value, ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() { return items; }
    public void setItems(ArrayList<Item> items) { this.items = items;}

    public void CreateWeapons() {

        // Weapons -> Sword
        Item Sword1 = new Item("Fire Sword", "sword", 6.0, 4.0);
        items.add(Sword1);

        Item Sword2 = new Item("Light Sword","sword", 10.0, 2.0);
        items.add(Sword2);
        // Sword's weight will decrease strength but the Sword itself will increase the strength, so we can ignore it.
        // -----------------------------------------------------------------------------------------------------------

        // Weapons -> Shield
        Item Shield1 = new Item("Tower Shield", "shield", 5.0, 3.0);
        items.add(Shield1);

        Item Shield2 = new Item("Captain America's Shield", "shield", 8.0, 5.0);
        items.add(Shield2);
        // Shields decrease strength because of the weight and increase vitality
        // -----------------------------------------------------------------------------------------------------------

        // Weapons -> Wands
        Item Wand1 = new Item("Wood Wand", "wand", 2.0, 1.0);
        items.add(Wand1);

        Item Wand2 = new Item("Magical Wand", "wand", 1.0, 0.0);
        items.add(Wand2);
        // Wands increase vitality
        // -----------------------------------------------------------------------------------------------------------
    }
}
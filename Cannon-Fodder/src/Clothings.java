import java.util.ArrayList;

public class Clothings {

    protected ArrayList<Item> items; //armors inventory

    public Clothings() {
        items = new ArrayList<Item>();
    }
    public Clothings(String name, double weight, double value, ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() { return items; }
    public void setItems(ArrayList<Item> items) { this.items = items; }

    public void CreateClothings() {

        // Clothings -> Armors
        Item Armor1 = new Item("Wood Armor", "armor", 4, 3);
        items.add(Armor1);

        Item Armor2 = new Item("Iron Armor", "armor", 7, 6);
        items.add(Armor2);
        // Armors decrease strength because of the weight and increase vitality
        // -----------------------------------------------------------------------------------------------------------

    }
}

